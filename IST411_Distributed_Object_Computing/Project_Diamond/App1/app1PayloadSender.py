#Project: Project Diamond
#Purpose Details: Send a payload to App2 using TLS
#Course: IST 411
#Author: Team 2
#Date Developed: 11/1/18
#Last Date Changed: 11/30/18
#Rev: 1

import sys, socket, ssl, json, logging
import settings
sys.path.append('../')
from App5.curlfeed import CurlFeed

class App1PayloadSender:
	"""
	Contains methods to send a payload to another application using TLS
	"""
	def __init__(self):
		"""
		Constructor for new App1PayloadSender object
		"""
		socket = None


	def setup_connection(self):
		"""
		Setup connection for TLS
		:return: Returns true if successful, false if failed
		"""
		try:
			s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
			self.socket = ssl.wrap_socket(s, ca_certs = settings.CERT, cert_reqs = ssl.CERT_REQUIRED)
			self.socket.connect((settings.HOSTNAME, settings.PORT_NUMBER))
			curlFeed = CurlFeed("App1", "Success", "Connected to server")
			curlFeed.send()
			return True
		except:
			# Catch all exceptions
			e = sys.exc_info()[0]
			print("Error:%s"%e)
			logging.error(e)
			curlFeed = CurlFeed("App1", "Failed", "Failed to connect to server")
			curlFeed.send()
			return False


	def send_payload(self, payload):
		"""
		Sends JSON payload  to App2 using TLS
		:param payload: The JSON payload
		:return: Returns true if successful, false if failed
		"""
		try:
			self.socket.send((json.dumps(payload).encode()))
			curlFeed = CurlFeed("App1", "Success", "Sent payload to App 2")
			self.socket.close()
			return True
		except:
			# Catch all exceptions
			e = sys.exc_info()[0]
			print("Error:%s"%e)
			logging.error(e)
			curlFeed = CurlFeed("App1", "Failed", "Failed to send JSON payload")
			curlFeed.send()
			self.socket.close()
			return False

	def send(self, payload):
		"""
		Creates a connection the server and sends the payload
		:param payload: The JSON payload
		"""
		self.setup_connection()
		self.send_payload(payload)

