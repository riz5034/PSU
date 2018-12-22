#Project: Project Diamond
#Team 2
#App4 Test

import unittest
import os, json, pika, logging, Pyro4
from app4 import App4

class TestApp4(unittest.TestCase):
	'''
	This will test the methods used in App3
	'''
	
	def test_get_payload(self, payload):
		'''
		This will test the get_payload method in App3
		'''
		payload = {"userId": 1, "id": 1}
		result =  app4.get_payload(payload)
		self.assertTrue(result)
		
#	def test_connection(self):
		'''
		This will test the connection method in App3
		'''
		
if __name__ =='__main__':
	unittest.main()

