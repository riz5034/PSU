#Project : Project Diamond
#Pupose Details : Use Rabbitmq to send payload to app1
#Course: IST411
#Author : Team 2
#Date Deceloped : 12/3/18
#Last Date Changed:12/4/18
#rev : 0

import pika
import sys
import logging
import datetime
import time
sys.path.append("../")
from App5.curlfeed import CurlFeed

class App4SendRabbitmq:
	global jsonPayload
	'''
	Contains method to send a jsonpayload usin rabbitmq to app1
	'''
	def send_payloadqueue(self, payload):
		"""
		Makes connection to app3Pyro
		Pass the uri to app3
		Send payload to app1 via rabbitmq
		"""
		try:
			jsonPayload = format(payload)
			print(jsonPayload)
			print("Connecting to the localhost...")
			time.sleep(2)
			connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
			channel = connection.channel()
			print("Successfully connected to the queue channel...")
			time.sleep(1)
			channel.queue_declare(queue='HelloTeam2!')
			channel.basic_publish(exchange='',
                               	routing_key='Hello', body=jsonPayload)
			print(" [x] Sent the payload back to app1.")
			curlFeed = CurlFeed("App4", "Success", "Successfully Sent rabbitmq payload to  App1")
			curlFeed.send()
			connection.close()
		except Exception as e:
			print(e)
			curlFeed = CurlFeed("App4", "Failure", "Failed to send rabbitmq payload to App1")
			curlFeed.send()

if __name__=='__main__':
	a = App4SendRabbitmq()
	payload = {'name':'bijal'}
	a.send_payloadqueue(payload)
