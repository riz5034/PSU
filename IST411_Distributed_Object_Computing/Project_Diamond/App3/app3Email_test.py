
# Email test

import unittest, pysftp, sys, json
from app3Email import App3Email
from email.mime.text import MIMEText

class TestAppEmail(unittest.TestCase):
    def test_fromAddress(self):
        '''
        Testing, from sender has the same email address
        '''
        fromAddress = 'bjj5172@psu.edu'
        self.assertEqual('bjj5172@psu.edu', fromAddress)

    def test_toAddress(self):
        '''
        Testing if email address that is being sent is the same
        '''
        toAddress = 'fqp5042@psu.edu'
        self.assertEqual('fqp5042@psu.edu', toAddress)

    def test_subject(self):
        '''
        Testing email subject is the same
        '''
        subject = "Testing subject"
        self.assertEqual("Testing subject", subject)

    def test_emailSent(self):
        paylod = {"userId": 1, "id": 1, "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"}
        result = App3Email.emailSent(payload)
        self.assertTrue(result)
