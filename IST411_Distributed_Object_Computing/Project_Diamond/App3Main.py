'''
Project : Diamond Project
Class: Ist 411
Team : Team 2
rev: 1.00.00.12
'''
from App3.app3Hash import App3Hash
from App3.app3JsonCompress import App3JsonCompress
from App3.app3Pyro import App3Pyrp
from App3.app3Sftp import app3sftp
from email.mime.text import MIMEText
import gzip, shutil, pysftp, sys, hashlib, hmac, base64, smtplib, json, Pyro4
from App5.curlfeed import CurlFeed
def Emailsent(message):
    '''
    Create email to sent
    '''
    fromAddress = 'bjj5172@psu.edu'
    toAddress1 = 'bjj5172@psu.edu'
    toAddress2 = 'zvs5039@psu.edu'
    toAddress3 = 'bmp5495@psu.edu'
    toAddress4 = 'fqp5042@psu.edu'
    toAddress5 = 'rnd5089@psu.edu'
    toAddress6 = 'riz5034@psu.edu'
    subject = "Hey Team"
    msg = MIMEText(repr(message))
    msg['Subject'] = 'Subject'
    msg['From'] = fromAddress
    msg['To Brian'] = toAddress1
    msg['To Zach'] = toAddress2
    msg['To Bijal'] = toAddress3
    msg['To Francheska'] = toAddress4
    msg['To Rachel'] = toAddress5
    msg['To Ricky'] = toAddress6
    try:
        s = smtplib.SMTP_SSL('authsmtp.psu.edu',465)
        s.sendmail(fromAddress,[toAddress1], msg.as_string())
        s.sendmail(fromAddress,[toAddress2], msg.as_string())
        s.sendmail(fromAddress,[toAddress3], msg.as_string())
        s.sendmail(fromAddress,[toAddress4], msg.as_string())
        s.sendmail(fromAddress,[toAddress5], msg.as_string())
        s.sendmail(fromAddress,[toAddress6], msg.as_string())
        curlFeed = CurlFeed("App3", "Success", "Sent JSON payload to email addresses")
        curlFeed.send()
        return True
    except exception as e:
        print("Error %s" %e.args[0])
        curlFeed = CurlFeed("App3", "Failed", e.args[0])
        curlFeed.send()
        return False
def Sftpsend():
    '''
    Created a app3sftp object and recevie sftp load
    '''
    lapp3stp=app3sftp()
    lapp3stp.sftp()
    return True

def jasonRead():
    '''
    return the message 
    '''
    with open("json.txt", "r") as json_data:
        curlFeed = CurlFeed("App3", "Success", "Opened JSON file")
        curlFeed.send()
        message = json.load(json_data)
        print(type(message))
    return message

def hashmessage(message):
    '''
    take message and hash the message 
    '''
    key = "This is a key"
    hashmessage = App3Hash(key,message)
    hashmessage.messageEngrpt()
    return True

def Compress():
    '''
    Compress the the file and compress the file to creatin name
    '''
    filelocation = 'json.txt'
    outputfile = 'json.txt.gz'
    compressfile = App3JsonCompress(filelocation,outputfile)
    compressfile.CompressFile()
    return True

def Pryojob(message):
    '''
    Create a pryo object using message as payload
    '''
    proapp3 = App3Pyrp()
    print("this before Pryo class and this type:",type(message))
    proapp3.pyro_payload(message)
    return True

def main():
    '''
    This run the whole app
    '''
    Sftpsend()
    message = jasonRead()
    hashmessage(message)
    Emailsent(message)
    Pryojob(message)
    Compress()
    return True

if __name__ == "__main__":
        main()
