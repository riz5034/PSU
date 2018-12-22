#Project : Project Diamond
#purpose Details : Unit test method for app4
#course : IST 411
#Author : Team2
#Date Developed  : 12/4/18
#Last Date Changed : 12/4/18
#Rev :0

import unittest

from app4Sendrabbitmq import App4SendRabbitmq

class App4SendRabbitmqTest(unittest.TestCase):
	"""
	Test methods for send Rabbitmq paylaod
	""" 
	def test_send_payloadqueue(self):
		"""
		Test method for send_payloadqueue
		compares boolean value True to the actual boolean value
		"""
		app4sender = App4SendRabbitmq()
		result = app4sender.send_payloadqueue()
		self.asserTrue(result)
