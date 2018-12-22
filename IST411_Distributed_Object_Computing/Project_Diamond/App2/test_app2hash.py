#Project Diamond
#Team 2
#App 2

import pysftp, base64, hashlib, hmac, sys, json, ssl, unittest
from app2hash import App2Hash

class App2HashTest(unittest.TestCase):
	'''
	This class will test the methods defined in App2hash
	'''
	key = "key"
	message = "Yo"

	def test_hash(self):
		'''
		This will be the test method for the hash in App2
		It will compare the boolean value True to the initial boolean value
		'''

		#Define Bytes
		key ="Hi"
		message = "message"
		self.testHash = App2Hash(key, message)
		result = self.testHash.hash()

		#Test the result
		self.assertTrue(result)

if __name__=='__main__':
	unittest.main()
