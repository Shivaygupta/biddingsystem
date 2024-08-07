import sys
from os.path import dirname, abspath

BASE_DIR = dirname(dirname(abspath(__file__)))
sys.path.append(BASE_DIR)

from scripts.connection_utils import execute_select_query
from scripts.connection_utils import execute_update_query
from airflow.hooks.mysql_hook import MySqlHook
from airflow.utils.email import send_email

def select_valid_bid_slot():
    try:
        query = '''select bid_slot_id from bid_slot where bid_completed = 0;'''
        bid_slot_list = execute_select_query(query)
    except Exception as e:
        raise e
    return bid_slot_list


def select_winner(bid_slot_id_list):

    if len(bid_slot_id_list) == 1:
        bid_id_condition = f"bid_slot_id = '{bid_slot_id_list[0]}'"
    else:
        bid_slot_tuple = tuple(bid_slot_id_list)
        bid_id_condition = f"bid_slot_id in {bid_slot_tuple}"
    try:
        query = f"""SELECT bidder_id FROM bid
                    WHERE bidding_price = (SELECT MAX(bidding_price) FROM bid WHERE {bid_id_condition})
                      AND {bid_id_condition}
                    ORDER BY created_at;"""
        winner_id_list = execute_select_query(query)
    except Exception as e:
        raise e

    try:
        query = f"""UPDATE bid SET status = 'ALLOCATED' WHERE bidder_id in {winner_id_list} """
                execute_update_query(query)
    except Exception as e:
        raise e

    return winner_id_list


def select_winner_email(winner_id_list):
    if len(winner_id_list) == 1:
            winner_id_condition = f"id = '{winner_id_list[0]}'"
        else:
            winner_id_tuple = tuple(winner_id_condition)
            winner_id_condition = f"id in {winner_id_tuple}"
        try:
            query = f""SELECT email from bidder where {winner_id_condition}""
            winner_email_list = execute_select_query(query)
        except Exception as e:
            raise e

    return winner_email_list

def send_email(winner_email_list):
    for email in range(len(winner_email_list)):
        send_email(
            to = str(winner_email_list[email]),
            subject = 'Congrats: You are the winner',
            html_content = f"""
                            <p>Congrats on winning email, Please check your application for more details.
                            """
        )

def update_bid_slot_and_bid_table(bid_slot_list):
     try:
         query = f"""UPDATE bid SET status = 'REJECTED' WHERE bid_slot_id in {bid_slot_list} """
         execute_update_query(query)
     except Exception as e:
             raise e

if __name__ == "__main__":
    bid_slot_list = select_valid_bid_slot()
    winner_id_list = select_winner(bid_slot_list)
    winner_email_list= select_winner_email(winner_id_list)
    update_bid_slot_and_bid_table(bid_slot_list)