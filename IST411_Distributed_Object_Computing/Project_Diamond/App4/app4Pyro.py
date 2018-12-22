# Project: Project Diamond
# Purpose Details: Use Pyro ORB to send an object for App3 to use
# Course: IST 411
# Author: Team 2
# Date Developed: 12/4/18
# Last Date Changed: 12/4/18
# Rev: 0

import sys, Pyro4, logging
sys.path.append('../')
from App5.curlfeed import CurlFeed

class App4Pyro:
	"""
	Contains methods to send an object using Pyro ORB and recieve a payload
	"""
	def __init__(self):
		self.payload = []

	def sendURI(self):
		"""
		Sends a URI to an application to recieve a PayloadSender object
		:return: Returns true if successful, false if failed
		"""
		try:
			@Pyro4.expose
			class PayloadSender(object):
				"""
				Contains methods to attach a payload and close the daemon attached
				"""
				def __init__(self, daemon, payload):
					self.daemon = daemon
					self.payload = payload
				def send_pyro(self, payload):
					"""
					Appends a payload to the class
					:param payload: The payload to append
					:return: Returns true if successful and false if failed
					"""
					try:
						self.payload.append(payload)
						curlFeed = CurlFeed("App3", "Success", "Attached payload to send to App 4")
						curlFeed.send()
						return True
					except:
						e = sys.exc_info()[0]
						print("Error:%s" %e)
						logging.error(e)
						curlFeed = CurlFeed("App3", "Failed", "Failed to attach payload to send to App4")
						curlFeed.send()
						return False

				@Pyro4.oneway # in case call returns later than daemon.shutdown
				def shutdown(self):
					"""
					Shuts down the daemon passed
					"""
					try:
						print("Shutting down Pyro ORB...\n")
						self.daemon.shutdown()
						curlFeed = CurlFeed("App3", "Success", "Shutdown daemon")
						curlFeed.send()
						return True
					except:
						e = sys.exc_info()[0]
						print("Error:%s" %e)
						logging.error(e)
						curlFeed = CurlFeed("App3", "Failed", "Failed to shutdown daemon")
						curlFeed.send()
						return False

			daemon = Pyro4.Daemon()
			uri = daemon.register(PayloadSender(daemon, self.payload))
			print("Ready... Object uri =", uri)
			print("Ready to send object using pyro")
			daemon.requestLoop()
			daemon.close()
			print("Daemon closed.\n")
			print("Here's the payload:")
			print(self.payload[0])
			print("")
			curlFeed = CurlFeed("App4", "Success", "Retrieved payload with Pyro object")
			curlFeed.send()
			return True
		except:
			e = sys.exc_info()[0]
			print("Error:%s" %e)
			logging.error(e)
			curlFeed = CurlFeed("App3", "Failed", "Failed to retrieve payload with Pyro object")
			curlFeed.send()
			return False

	def getPayload(self):
		"""
		Returns the payload stored
		:return: The payload
		"""
		return self.payload[0]

if __name__ == '__main__':
	test = App4Pyro()
	test.sendURI()
	print(test.getPayload())
