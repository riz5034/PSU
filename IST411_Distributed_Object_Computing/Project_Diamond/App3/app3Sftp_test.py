import unittest, pysftp,sys
from app3Sftp import app3sftp

class TestApp3Sftp(unittest.TestCase):
    def test_sftp(self):
        '''
        Testing the sftp
        '''
        app3sftptest=app3sftp()
        self.assertTrue(app3sftptest.sftp())
