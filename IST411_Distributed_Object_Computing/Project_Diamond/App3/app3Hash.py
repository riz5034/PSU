
'''
Project: Project Diamond
Team  Team 2
App 3 Hashin
'''
import json, hashlib, hmac, sys, base64, logging
from App5.curlfeed import CurlFeed
class App3Hash:
    '''
    Created the Hash for App3
    '''
    logging.basicConfig(filename='App3Hash.log', level = logging.ERROR)
    message = ''
    key = ''
    signature = ''

    def __init__(self,key,message):
        '''
        Parms :
            Key : the key to hmac
            message: message to hmac
        '''
        self.key = key
        self.message  = message
        print("This message as con",type(self.message))
        print("this message in hash class",type(message))

    def messageEngrpt(self):
       '''
        Hash the message with the key and with sha256
       '''
       try:
           lkey = bytes(self.key,"UTF-8")
           lmessage = bytes(repr(self.message), "UTF-8")
           digester = hmac.new(lkey , lmessage , hashlib.sha256)
           print(digester)
           self.signature = digester.digest()
           curlFeed = CurlFeed("App3", "Success", "Hashed JSON data")
           curlFeed.send()
           print(self.signature)
           return True
       except:
           e = sys.exc_info()[0]
           logging.error(e)
           curlFeed = CurlFeed("App3", "Failed", e)
           curlFeed.send()
           raise 

    def getKey(self):
        '''
        Return the Key used to hash
        '''
        return self.key

    def getMessage(self):
        '''
        Return what message need to be hash
        '''
        return self.message

    def getSignature(self):
        '''
        Return the hash signature afters messageEngrpt return
        '''
        return self.signature
