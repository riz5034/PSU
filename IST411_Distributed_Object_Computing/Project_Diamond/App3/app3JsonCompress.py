'''
Project: Project Diamond
Team  Team 2
App 3 Hashin
'''
import json, sys, base64, logging, gzip, shutil
from App5.curlfeed import CurlFeed
class App3JsonCompress:
    '''
    Create the Campress file
    '''
    logging.basicConfig(filename='App3JasonCompress.log', level = logging.ERROR)
    mlocation = ''
    olocation = ''

    def __init__(self,read,output):
        '''
        Parms read - read file location or name
        parms output file location name
        '''

        self.mlocation = read
        self.olocation = output

    def CompressFile(self):
        '''
        Compress the text file 
        '''
        try :
            with open(self.mlocation,'rb') as f_in:
                with gzip.open(self.olocation,'wb') as f_out:
                    print("Now compressing the JSON Payload")
                    shutil.copyfileobj(f_in, f_out)
                    print("The file has been compressed")
                    curlFeed = CurlFeed("App3", "Success", "Compressed JSON file")
                    curlFeed.send()
            return True
        except:
            e = sys.exc_info()[0]
            logging.error(e)
            curlFeed = CurlFeed("App3", "Failed", e)
            curlFeed.send()
            raise

    def getRead(self):
        '''
        Get the read location
        '''
        return self.mlocation

    def getOutput(self):
        '''
        Get the output location 
        '''
        return self.olocation

