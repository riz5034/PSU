# Project: Project Diamond
# Purpose Details: Unit test methods for App1
# Course: IST 411
# Author: Team 2
# Date Developed 11/3/18
# Last Date Changed: 11/30/18
# Rev: 1

import unittest, time
from app1PayloadSender import App1PayloadSender

class App1PayloadSenderTest(unittest.TestCase):
	"""
	Test method to send payload to App 2
	"""

	def test_send_payload(self):
		"""
		Test method for send_payload in App1
		Compares boolean value True to the actual boolean value
		"""
		payload = {"userId": 1, "id": 1, "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"}
		app = App1PayloadSender()
		app.setup_connection()
		result = app.send_payload(payload)
		self.assertTrue(result)
