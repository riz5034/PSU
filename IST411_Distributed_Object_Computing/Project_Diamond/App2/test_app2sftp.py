#Project Diamond
#Team 2
#App 2 stfp Test

import base64, hashlib, hmac, pysftp, socket, ssl, json, unittest
from app2Sftp import App2SFTP

class App2SFTPTest(unittest.TestCase):
	'''
	This class will test the methods derived from App2stfp
	'''
	result = App2SFTP()
	def test_SFTP(self):
		'''
		This method will test for hash in App2
		It will compare the boolean value True to the initial boolean value
		'''
		#Define in Bytes

		#Instantiate
		payload = self.result.send_SFTP()

		#Test
		self.assertTrue(payload)

if __name__=='__main__':
	unittest.main()
