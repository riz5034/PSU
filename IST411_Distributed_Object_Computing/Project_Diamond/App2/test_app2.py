
#Project: Project Diamond
#Team 2
#App2Test

import base64, hashlib, hmac, pysftp, socket, ssl, json, unittest
from app2 import App2

class App2Test(unittest.TestCase):
	'''
	Tests the methods defined in App2
	'''
	def test_hash(self):
		'''
		Test method for hash in App2
		Compares boolean value True to the actual boolean value
		'''
		payload = {"userId": 1, "id": 1, "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"}
		result = App2.hash(payload)
		self.assertTrue(result)
	def test_send_SFTP(self):
		'''
		Test method for hash in App2
		Compares boolean value True to the actual boolean value
		'''
		payload = {"userId": 1, "id": 1, "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"}
		result = App2.send_SFTP(payload)
		self.assertTrue(result)
if __name__=='__main__':
	unittest.main()
