import pika
import settings
import sys
sys.path.append("../")
from App5.curlfeed import CurlFeed
class App1RabbitmqReceiver:
	'''
	Contains method to receive payload rabbitMQ
	'''
	def receive_payloadqueue(self):
		'''
		Receives paylaod from queue 
		Return true when it receives the payload
		Return false when payload is not received
		'''
		try:
			connection = pika.BlockingConnection(pika.ConnectionParameters(host=settings.HOSTNAME))
			channel = connection.channel()
			channel.queue_declare(queue = 'Team2')
			curlFeed = CurlFeed("App1", "Success", "Successfully receiving rabbitmq payload from app4")
			curlFeed.send()

			def callback(ch, method, properties, body):
				print("Received %r \n" % body)
				channel.stop_consuming()
			channel.basic_consume(callback, queue='Team2', no_ack = True)
			channel.start_consuming()
			curlFeed = CurlFeed("App1", "Success", "Successfully receiving rabbitmq payload from app4")
			curlFeed.send()
			return True
		except Exception as e:
			print(e)
			curlFeed = CurlFeed("App1", "Failure", "Failed to receive rabbitmq payload from  App4")
			curlFeed.send()
			return False

if __name__== '__main__':
	a = App1RabbitmqReceiver()
	print("Checking queue...")
	a.receive_payloadqueue()

