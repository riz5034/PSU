
#Project: Project Diamond
#Team 2
#App2
import pysftp, base64, hashlib, hmac, sys, socket, ssl, json
from App5.curlfeed import CurlFeed
cnopts = pysftp.CnOpts()
cnopts.hostkeys = None
cinfo = {'cnopts': cnopts, 'host':'oz-ist-linux-fa18-411', 'username':'ftpuser', 'password':'test1234', 'port':103}

class App2:
	'''
	Description- This app acts like a server, and is required to be initialized after starting up app5 run.py.
	'''
	def get_connection():
		try:
			"""
			Connects the JSON payload
			"""
			global dataJSON
			s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
			ssl_sock = ssl.wrap_socket(s, server_side = True, certfile = "server.crt", keyfile = "server.key")
			ssl_sock.bind(('localhost', 8080))
			ssl_sock.listen(5)
			curlFeed = CurlFeed("App2", "Success", "Started server")
			curlFeed.send()
			print("ciphers:" + str(ssl_sock.cipher()))
			while True:
				print("accept SSL connections from the outside")
				(clientsocket, address) = ssl_sock.accept()
				data = clientsocket.recv(1024)
				dataJSON = json.loads(data.decode('utf-8'))
				print(json.dumps(dataJSON))
				curlFeed = CurlFeed("App2", "Success", "Recieved JSON payload")
				curlFeed.send()
		except:
			print("Log exception:", sys.exc_info()[0])
			curlFeed = CurlFeed("App2", "Failed", sys.exc_info()[0])
			curlFeed.send()
	def hash(dataJSON):
		'''
		Hashes the JSON Payload
		'''
		try:
			key = "This is a key"
			print(key)
			key = bytes(key, 'UTF-8')
			dataJSON_bytes = bytes(json.dumps(dataJSON), 'UTF-8')
			sha256_digester = hmac.new(key, dataJSON_bytes, hashlib.sha256)
			print(sha256_digester)
			sha256_signature = sha256_digester.digest()
			print("Hashing JSON Payload")
			print(sha256_signature)
			curlFeed = CurlFeed("App2", "Success", "Hashed JSON Payload")
			curlFeed.send()
			return True
		except:
			print("Log exception:", sys.exc_info()[0])
			curlFeed = CurlFeed("App2", "Failed", sys.exc_info()[0])
			curlFeed.send()
			return False
	def send_SFTP(dataJSON):
		'''
		Sends the JSON Payload to APP3 using SFTP Security
		'''
		try:
			with pysftp.Connection(**cinfo) as sftp:
				print("Connection made")
				print("Sending JSON payload to App3")
				sftp.put('json.txt')
				curlFeed = CurlFeed("App2", "Success", "Sent JSON payload to App3")
				return True
		except:
			print("Log exception:", sys.exc_info()[0])
			curlFeed = CurlFeed("App2", "Failed", sys.exc_info()[0])
			curlFeed.send()
			return False
	if __name__ == '__main__':
		get_connection()
		#print(dataJSON)
		#hash(dataJSON)
		#send_SFTP(dataJSON)
