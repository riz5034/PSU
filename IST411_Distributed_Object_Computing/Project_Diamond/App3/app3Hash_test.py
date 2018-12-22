import unittest, json, hashlib, hmac, sys, base64, logging
from app3Hash import App3Hash
class TestApp3Hash(unittest.TestCase):
    key = 'hello'
    message = 'test'
    hashmactest = App3Hash(key,message)
    def test_getKey(self):
        '''
        Trying test the get key method
        '''
        self.assertEqual(self.hashmactest.getKey(), self.key)

    def test_getMessage(self):
        '''
        Test to see if the same message come 
        '''
        self.assertEqual(self.hashmactest.getMessage(), self.message)

    def test_messageEngrpt(self):
        '''
        Test to see if we get return true for hash
        '''
        self.assertTrue(self.hashmactest.messageEngrpt())

    def test_getSignature(self):
        '''
        See if the signature is the same for same key and message
        '''
        key = 'zack'
        lkey = bytes(self.key,"UTF-8")
        lmessage = bytes(self.message, "UTF-8")
        digester = hmac.new(lkey,lmessage, hashlib.sha256)
        signature = digester.digest()
        self.hashmactest.messageEngrpt()
        self.assertEqual(self.hashmactest.getSignature(),signature)
        
