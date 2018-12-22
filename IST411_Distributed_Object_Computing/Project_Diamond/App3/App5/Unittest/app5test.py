
import unittest
import os
from eve import Eve
from pymongo import MongoClient
import run
import curlfeed

MONGO_DBNAME = 'Team_2'
MONGO_HOST = 'localhost'
MONGO_PORT = 27017


class Base_Test(unittest.TestCase):
    def apply(self):
        if 'PORT' in os.environ:
            del(os.environ['PORT'])



class Eve_Test(Base_Test):
    def test_eve(self):
        self.assertTrue(type(run.app) is Eve)

class CurlfeedTest(unittest.TestCase):
    def testApp5(self):
        self.assertEqual('http://127.0.0.1:5010/log', curlfeed.getTestApp5('http://127.0.0.1:5010/log'))

    def testApp1(self):
        self.assertEqual('app1', curlfeed.getTestApp('app1'))

    def testApp2(self):
        self.assertEqual('app2', curlfeed.getTestApp('app2'))

    def test_status_Success(self):
        self.assertEqual('Success', curlfeed.getTestStatus('Success'))

    def test_status_Failure(self):
        self.assertEqual('Failure', curlfeed.getTestStatus('Failure'))

    def test_info(self):
        self.assertEqual('This is a test', curlfeed.getTestInfo('This is a test'))
