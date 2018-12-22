'''
Project: Project Diamond
Team  Team 2
App 3 Email
'''
import json
from email.mime.text import MIMEText


class App3Email:
    subject = "Hey Team"
    message = ['From','To Brian','To Zach','To Bijal','To Francheska','To Rachel','To Ricky']
    msg = MIMEText(repr(message))
    msg = ' '
    #send
    s = smtplib.SMTP_SSL('authsmtp.psu.edu', 465)
    '''
    Created the Email for app3
    '''
    def email_adress(self):
        '''
         Team 2 email adress
        '''
        try:
            msg.self

            with open('jsonEmail.json','r') as json_data:
                dataEmail = json.loads(json_data)

            return True
        except:
            print("Log exception:",sys.exc_info()[0])

    def email_msg(self):
        '''
        msg - The message that will be recieved in all the email
        '''
        try:
            msg.(message[0])
            msg.(message[1])
            msg.(message[2])
            msg.(message[3])
            msg.(message[4])
            msg.(message[5])
            msg.(message[6])
            msg.(message[7])
            return True
        except:
            print("Log exception:",sys.exc_info()[0])

    def email_send(self):
        '''
        Sending the email
        '''
        try:
            s.sendmail(fromAddress,[toAddress1], msg.as_string())
            s.sendmail(fromAddress,[toAddress2], msg.as_string())
            s.sendmail(fromAddress,[toAddress3], msg.as_string())
            s.sendmail(fromAddress,[toAddress4], msg.as_string())
            s.sendmail(fromAddress,[toAddress5], msg.as_string())
            s.sendmail(fromAddress,[toAddress6], msg.as_string())
            curlFeed = CurlFeed("App3", "Success", "Sent JSON payload to email addresses")
            curlFeed.send()
            return True
        except:
            e = sys.exc_info()[0]
            logging.error(e)
            print("Error %s" %e.args[0])
            curlFeed = CurlFeed("App3", "Failed", e.args[0])
            curlFeed.send()
            raise

