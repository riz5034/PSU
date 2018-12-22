import unittest
from app1RabbitmqReceiver import App1RabbitmqReceiver

class App1RabbitmqReceiverTest(unittest.TestCase):
	def test_receive_payloadqueue(self):
		app1receiver = App1RabbitmqReceiver()
		result = app1receiver.receive_payloadqueue()
		self.assertTrue(result)
