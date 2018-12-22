#Project: Project Diamond
#Team 2
#App2
import sys, pysftp
sys.path.append("../")
from App5.curlfeed import CurlFeed
cnopts = pysftp.CnOpts()
cnopts.hostkeys = None
cinfo = {'cnopts':cnopts, 'host':'oz-ist-linux-fa18-411', 'username':'ftpuser', 'password':'test1234', 'port':103}
class App2SFTP:
	'''
	Description: This sends the payload to App3 using SFTP security
	'''
	def send_SFTP(self):
		'''
		SFTP recieves payload
		'''
		try:
			with pysftp.Connection(**cinfo) as sftp:
				print("Connection made")
				print("Sending the JSON Payload to App3")
				sftp.put('json.txt')
				curlFeed = CurlFeed("App2", "Success", "Successfully Sent SFTP Payload to App3")
				curlFeed.send()
				return True
		except:
			print("Log exception:", sys.exc_info()[0])
			curlFeed = CurlFeed("App2", "Failure", "Failed to send SFTP Payload to App3")
			curlFeed.send()
