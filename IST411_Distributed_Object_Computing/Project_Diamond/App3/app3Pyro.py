'''
Project: Project Diamond
Team  Team 2
App 3 Pyro
'''
import sys ,Pyro4
sys.path.append('../')
from App5.curlfeed import CurlFeed
class App3Pyrp:
	'''
	Created the Pyro for app3
	'''
	def pyro_payload(self,message):
		'''
		uri - The uri the user has to input from App4
		'''
		try:
			uri = input("Please enter the uri: ").strip()
			transformer = Pyro4.Proxy(uri)
			print("Sending JSON Payload to app4")
			transformer.send_pyro(message)
			transformer.shutdown()
			#print(transformer.get_payload(repr(message)))
			curlFeed = CurlFeed("App3", "Success", "Sent JSON payload to App4")
			curlFeed.send()
			return True
		except:
			print("Log exception",sys.exc_info()[0])

if __name__ == '__main__':
	payload = {"Name": "Ricky"}
	test = App3Pyrp().pyro_payload(payload)
