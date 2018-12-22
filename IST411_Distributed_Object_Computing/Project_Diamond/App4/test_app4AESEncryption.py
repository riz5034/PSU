# Pro  ject: Project Diamond
# Purpose Details: Unit test methods for App 4
# Course: IST 411
# Author: Team 2
# Date Developed: 12/4/18
# Last Date Changed: 12/4/18
# Rev: 0

import unittest
from app4AESEncryption import App4AESEncryption

class App4AESEncryption_test(unittest.TestCase):
	"""
	Test methof for payload encryption
	"""
	def test_get_paylod(self):
		"""
		Tess method for get paylaod
		"""
		payload = {'bijal':'patel'}
		test = App4AESEncryption()
		result = test.getPayload(payload)
		self.assertTrue(result)

	def test_encrypt(self):
		"""
		Test method for encrypted payload
		"""
		payload ={'bijal':'patel'}
		test = App4AESEncryption()
		test.getPayload(payload)
		result = test.encrypt()
		self.assertTrue(result)

	def test_save_paylaod(self):
		"""
		Test file for saving encrypted file 
		"""
		payload = {'bijal':'patel'}
		test = App4AESEncryption()
		test.getPayload(payload)
		test.encrypt()
		result = test.savePayload()
		self.assertTrue(result)
