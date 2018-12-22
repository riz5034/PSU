import unittest
from app1PayloadSaver import App1PayloadSaver

class App1PayloadSaverTest(unittest.TestCase):
	"""
	Test method to save JSON payload
	"""
	def test_save_payload(self):
		"""Test method for save_payload in App1
		Match expected payload with actual payload
		"""
		payload = {"test": "something"}
		app = App1PayloadSaver()
		result = app.save_payload(payload)
		self.assertTrue(result)

