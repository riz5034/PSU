'''
Project : Diamond Project
Class: Ist 411
Team : Team 2
rev: 1.00.00.12
'''
import App3Main
import unittest
class App3MainTest(unittest.TestCase):
	'''
	Tests the methods defined in App3Main
	'''
	def test_EmailSent(self):
		'''
		Tests methods for email in App3
		'''
		payload = {"userId": 1, "id": 1, "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit", "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"}
		result = App3Main.Emailsent(payload)
		self.assertTrue(result)
	def test_Sftpsend(self):
		'''
		Tests methods for SFTP recieving
		'''
		result = App3Main.Sftpsend()
		self.assertTrue(result)
	def test_jasonRead(self):
		'''
		Tests methods for opening JSON payload
		'''
		payload = {"userId": 1, "id": 1, "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit", "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"}
		actualPayload = App3Main.jasonRead()
		self.assertEqual(actualPayload, payload)
	def test_Compress(self):
		'''
		Tests the payload compression
		'''
		result = App3Main.Compress()
		self.assertTrue(result)
	def test_Pyrojob(self):
		'''
		Tests turning the JSON payload into an object
		'''
		payload = {"userId": 1, "id": 1, "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"}
		result = App3Main.Pryojob(payload)
		self.assertTrue(result)
	def test_hashmessage(self):
		'''
		Tests the JSON payload hashing
		'''
		payload = {"userId": 1, "id": 1, "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"}
		result = App3Main.hashmessage(payload)
		self.assertTrue(result)
if __name__=='__main__':
	unittest.main()

