#Project: Project Diamond App1
#Purpose Details: Retrieve a JSON payload from the internet and send it to App2 using TLS. It will also retrieve an encrypted payload from App4 and save the JSON payload to a text file.
#Course: IST 411
#Author: Team 2
#Date Developed: 11/1/18
#Last Date Changed: 11/1/18
#Rev: 0

import socket, sys, ssl, urllib.request, json, logging
from App5.curlfeed import CurlFeed

class App1:
	"""
	App1 can retrieve a JSON payload from the internet, send it to App2 using TL, retrieve an encrypted payload from App4, and save the JSON payload to a text file
	"""
	def __init__(self,url,param):
		"""
		Construct new App1 object

		:param url: URL to pull JSON payload from
		:param param: Parameter for URL to pull JSON payload from
		:return: Returns nothing
		"""

		self.url = url
		self.param = param

	def retrieve_json(self):
		"""
		Retrieve a JSON payload from a given URL and parameter

		:return: Returns a JSON payload
		"""

		try:
			response = urllib.request.urlopen(self.url + self.param)
			payload = response.read()
			jsonPayload = json.loads(payload.decode('utf-8'))
			curlFeed = CurlFeed("App1", "Success", "Retrieved JSON payload from URL")
			curlFeed.send()
			return jsonPayload
		except:
			# Catch all exceptions
			e = sys.exc_info()[0]
			print("Error:%s"%e)
			jsonPayload = {}
			logging.error(e)
			curlFeed = CurlFeed("App1", "Failed", "Failed to retrieve JSON payload from URL")
			curlFeed.send()
			return jsonPayload


	def send_payload(self, payload):
		"""
		Sends JSON payload  to App2 using TLS

		:param payload: The JSON payload
		:return: Returns true if successful, false if failed
		"""

		try:
			s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
			ssl_sock = ssl.wrap_socket(s, ca_certs="server.crt", cert_reqs=ssl.CERT_REQUIRED)
			ssl_sock.connect(('localhost', 8080))
			curlFeed = CurlFeed("App1", "Success", "Sending Json Payload")
			curlFeed.send()
			ssl_sock.send((json.dumps(payload).encode()))
			ssl_sock.close()
			return True
		except:
			# Catch all exception
			e = sys.exc_info()[0]
			print("Error:%s"%e)
			logging.error(e)
			curlFeed = CurlFeed("App1", "Failed", "Failed to send JSON payload")
			curlFeed.send()
			ssl_sock.close()
			return False

	def receieve_payload(self):
		"""
		Needs to be implemented when App4 is completed
		"""

		print("abc")

	def save_payload(self, payload):
		"""
		Writes JSON payload to text file

		:param payload: The JSON payload
		:return: Returns true if successful, false if failed
		"""

		try:
			with open('json.txt', 'w') as outFile:
				outFile.write(json.dumps(payload))
				curlFeed = CurlFeed("App1","Success","Saved JSON payload")
				curlFeed.send()
				return True
		except:
			# Catch all exceptions
			e = sys.exc_info()[0]
			print("Error:%s"%e)
			logging.error(e)
			curlFeed = CurlFeed("App1","Failed","Failed to save JSON payload")
			curlfeed.send()
			return False

def main():
	logging.basicConfig(filename='App1GenLog.log', level=logging.ERROR)
	app1 = App1('https://jsonplaceholder.typicode.com','/posts/1')
	print("Retrieving JSON payload from URL...\n")
	payload = app1.retrieve_json()
	print(payload)
	print("\nSending payload to App2...\n")
	app1.send_payload(payload)
	print("Saving payload to text file...\n")
	app1.save_payload(payload)

if __name__ == '__main__':
	main()
