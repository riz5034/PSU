#Project: Project Diamond
#Purpose Details: Retrieve a JSON payload from the internet and send it to App2 using TLS. It will also retrieve an encrypted payload from App4 and save the JSON payload to a text file.
#Course: IST 411
#Author: Team 2
#Date Developed: 11/1/18
#Last Date Changed: 11/30/18
#Rev: 0

import sys, logging, datetime
from app1PayloadRetriever import App1PayloadRetriever
from app1PayloadSender import App1PayloadSender
from app1PayloadSaver import App1PayloadSaver
from app1RabbitmqReceiver import App1RabbitmqReceiver

def main():
	init_time = datetime.datetime.now()
	logging.basicConfig(filename='App1GenLog.log', level=logging.ERROR)
	print("Retrieving JSON payload from URL...\n")
	payload = App1PayloadRetriever().retrieve_json()
	print("Sending payload to App2...\n")
	sender = App1PayloadSender().send(payload)
	print("Saving payload to text file...\n")
	saver = App1PayloadSaver().save_payload(payload)
	print("Retrieving AES encrypted payload from queue...\n")
	receiver = App1RabbitmqReceiver()
	receiver.receive_payloadqueue()
	end_time = datetime.datetime.now()
	elapsed_time = end_time - init_time
	print("Elapsed Time: ", elapsed_time)


if __name__ == '__main__':
	main()
