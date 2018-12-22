# Project: Project Diamond
# Purpose Details: Unit test methods for App1
# Course: IST 411
# Author: Team 2
# Date Developed 11/3/18
# Last Date Changed: 11/30/18
# Rev: 1

import unittest
from app1PayloadRetriever import App1PayloadRetriever

class App1PayloadRetrieverTest(unittest.TestCase):
	"""
	Test method for retrieve_json method
	Compares an expected payload to an actual payload
	"""
	def test_retrieve_json(self):
		"""
		Test method for retrieve_json method in App1
		Compares an expected payload to the actual payload
		"""
		expectedPayload = {"userId": 1, "id": 1, "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"}
		app = App1PayloadRetriever()
		actualPayload = app.retrieve_json()
		self.assertEqual(actualPayload, expectedPayload)
