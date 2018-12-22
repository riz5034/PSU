# Project: Project Diamond
# Purpose Details: Unit test methods for App 4
# Course: IST 411
# Author: Team 2
# Date Developed: 12/4/18
# Last Date Changed: 12/4/18
# Rev: 0

import unittest
from app4Pyro import App4Pyro

class App4PyroTest(unittest.TestCase):
	"""
	Test methods for Pyro ORB
	"""
	def test_sendURI(self):
		"""
		Test method for sendURI
		Compares boolean value True to the actual boolean value
		"""
		test = App4Pyro()
		result = test.sendURI()
		self.assertTrue(result)

	def test_getPayload(self):
		"""
		Test method for getPayload
		Compares boolean value True to the actual boolean value
		"""
		test = App4Pyro()
		test.sendURI()
		result = test.getPayload()
		self.assertTrue(result)
