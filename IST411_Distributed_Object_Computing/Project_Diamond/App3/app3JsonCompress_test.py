import unittest, json, hashlib, hmac, sys, base64, logging
from app3JsonCompress import App3JsonCompress
class TestApp3JsonCompress(unittest.TestCase):

    read = 'json.txt'
    output ='json.txt.gz'
    compresstest = App3JsonCompress(read,output)

    def test_getRead(self):
        '''
        Test to see if we get the read file location
        '''
        self.assertEqual(self.compresstest.getRead(),self.read)

    def test_getOutput(self):
        '''
        Test to see if we get the output file location
        '''
        self.assertEqual(self.compresstest.getOutput(),self.output)

    def test_CompressFile(self):
        '''
        Compress the file and return true when it works
        '''
        self.assertTrue(self.compresstest.CompressFile())
