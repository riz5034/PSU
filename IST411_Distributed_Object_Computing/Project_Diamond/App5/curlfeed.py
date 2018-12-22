import sys, json, urllib.request, datetime, logging
from App5 import app5constants
#Libray to use to feed the log to the monogo
class CurlFeed:
    App5=''
    Status= ''
    App=''
    Info =''

    logging.basicConfig(filename='App5CurlFeed.log', level=logging.ERROR)

    def __init__(self, App, Status, Info):
        self.App5 = 'http://'+app5constants.APP5_URL+':'+app5constants.APP5_PORT+'/'+app5constants.DATABASE
        self.App=App
        self.Status = Status
        self.Info = Info

    def send(self):
        try:
            timestamp =str(datetime.datetime.now())
            payload = {"Timestamp":timestamp,"App":self.App,"Status":self.Status,"Info":self.Info}
            parms = json.dumps(payload).encode('utf8')
            #print(parms)
            #print(self.App5)
            req = urllib.request.Request(self.App5,data=parms,headers = {'content-type': 'application/json'})
            #print('req works')
            response = urllib.request.urlopen(req)
            #print(response.read().decode('utf8'))
            return True
        except:
            e = sys.exc_info()[0]
            print("Log exception:",e)
            logging.error(e)
            raise

    def getApp(self):
        print('App5:',self.App5)
        print('App:',self.App)

    def getStatus(self):
        print('Status:',self.Status)

    def getInfo(self):
        print('Info:',self.Info)

