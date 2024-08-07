import sys
from os.path import dirname, abspath

import mysql.connector
import mysql.connector
from airflow.models import Variable
from mysql.connector import Error

BASE_DIR = dirname(dirname(abspath(__file__)))
sys.path.append(BASE_DIR)

BIDDING_DB_CONNECTION = "BIDDING_DB_CONNECTION"

device_db = Variable.get(BIDDING_DB_CONNECTION, deserialize_json=True)
print(f"Db details{device_db}")
DB_USER = device_db['MYSQL_USER']
DB_PASSWORD = device_db['MYSQL_PASSWORD']
DB_HOST = device_db['MYSQL_HOST']
DB_NAME = device_db['MYSQL_DATABASE']


def mysql_connect():
    try:
        connection = mysql.connector.connect(host=DB_HOST,
                                             database=DB_NAME,
                                             user=DB_USER,
                                             password=DB_PASSWORD)
        if connection.is_connected():
            return connection

    except Error as e:
        print("Error while connecting to MySQL", e)
        raise e

    return connection


def execute_select_query(query):
    try:
        connection = mysql_connect()
        print(f"connection{connection}")
        cursor = connection.cursor()
        cursor.execute(query)
        records = cursor.fetchall()
        cursor.close()
        connection.close()
        print(f"records{records}")
        return records
    except Error as e:
        raise e


def execute_update_query(query):
    try:
        conn = mysql_connect()
        cursor = conn.cursor()
        cursor.execute(query)  # Corrected this line
        conn.commit()  # Ensure changes are committed to the database
        cursor.close()
        conn.close()
    except Exception as e:
        raise e

