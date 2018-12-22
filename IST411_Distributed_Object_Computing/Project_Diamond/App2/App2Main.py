#Project: Project Diamond
#Team 2
#App2
import sys, socket, ssl, json
sys.path.append("../")
from App5.curlfeed import CurlFeed
from app2hash import App2Hash
from app2Sftp import App2SFTP
class App2Main:
	'''
	created SSL for app2
	'''
	def get_connection():
		try:
			'''
			SFTP getting connection
			'''
			s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
			ssl_sock = ssl.wrap_socket(s, server_side = True, certfile = "server.crt", keyfile= "server.key")
			ssl_sock.bind(('localhost', 8080))
			ssl_sock.listen(5)
			curlFeed = CurlFeed("App2", "Success", "Started server")
			curlFeed.send()
			print("ciphers:" + str(ssl_sock.cipher()))
			while True:
				print("Accept SSL Connections from the outside")
				(clientsocket, address) = ssl_sock.accept()
				data = clientsocket.recv(1024)
				dataJSON = json.loads(data.decode('utf-8'))
				print(json.dumps(dataJSON))
				curlFeed = CurlFeed("App2", "Success", "Recieved JSON payload")
				curlFeed.send()
				return dataJSON
		except:
			print("Log exception:", sys.exc_info()[0])
			curlFeed = CurlFeed("App2", "Failed", sys.exc_info()[0])
			curlFeed.send()
	def hashPayload(dataJSON):
		'''
		Hashes JSON Payload
		'''
		key = "This is a key"
		hashPayload = App2Hash(key, dataJSON)
		hashPayload.hash()
		curlFeed = CurlFeed("App2", "Success", "Successfully Hashed the JSON Payload")
		return True
	def SFTPSend():
		'''
		Send JSON Payload to App3
		'''
		sftp = App2SFTP()
		sftp.send_SFTP()
	if __name__=='__main__':
		'''
		This runs app2
		'''
		dataJSON = get_connection()
		print(dataJSON)
		hashPayload(dataJSON)
		SFTPSend()
