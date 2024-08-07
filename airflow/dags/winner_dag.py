import sys
from os.path import dirname, abspath

from airflow import DAG
from datetime import datetime, timedelta
from dateutil.relativedelta import relativedelta
from airflow.operators.python import PythonOperator
from airflow.models.param import Param
import calendar
from airflow.operators.python import get_current_context
from airflow.operators.bash import BashOperator
from airflow.hooks.mysql_hook import MySqlHook

BASE_DIR = dirname(dirname(dirname(abspath(__file__))))
sys.path.append(BASE_DIR)

default_args = {
    'owner': 'Bidding Team',
    'depends_on_past': False,
    'start_date': datetime(2024, 8, 8),
    'email_on_failure': False,
    'email_on_retry': False,
    'retries': 1,
    'retry_delay': timedelta(minutes=15),
}

dag = DAG(
    'bidding-winner',
    tags=["bidding-winner", "bidding"],
    default_args=default_args,
    schedule_interval='30 20 * * *',
    catchup=False,
    max_active_runs=1,
    params={
        "date": Param(
            f"{datetime.today().date()}",
            type="string",
            format="date",
            title="Custom Date",
            description="Please select a date, It will archive the data of previous of previous month of selected date.",
        ),
    },
)

bid_winner = BashOperator(
    task_id= 'bidding_winner',
    bash_command= 'python3 /opt/airflow/scripts/bidding_winner.py',
    dag=dag,
)

print_data >> bid_winner