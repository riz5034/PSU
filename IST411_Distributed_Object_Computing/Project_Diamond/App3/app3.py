
#Project: Project Diamond
#Team 2
#App3
import gzip, shutil, pysftp, sys, hashlib, hmac, base64, smtplib, json, Pyro4
from App5.curlfeed import CurlFeed
from email.mime.text import MIMEText
cnopts = pysftp.CnOpts()
cnopts.hostkeys= None
cinfo = {'cnopts':cnopts, 'host':'oz-ist-linux-fa18-411', 'username':'ftpuser', 'password':'test1234', 'port':103}


try:
	with pysftp.Connection(**cinfo) as sftp:
		print("Connection made")

		''' The sftp recieving the payload file'''
		def sftp(self):
			try:
				print("Recieving payload file")
				data = sftp.get("json.txt")
			except:
				print("Log exception",sys.exc_info()[0])

				with open("json.txt", "r") as json_data:
				curlFeed = CurlFeed("App3", "Success", "Opened JSON file")
				curlFeed.send()
				key = "This is a key"
				message = json.load(json_data)
				curlFeed = CurlFeed("App3", "Success", "Loaded JSON data")
				curlFeed.send()
				print(repr(message))
				key = bytes(key, "UTF-8")
				message = bytes(repr(message), "UTF-8")
				sha256_digester = hmac.new(key, message, hashlib.sha256)
				print(sha256_digester)
				sha256_signature = sha256_digester.digest()
				curlFeed = CurlFeed("App3", "Success", "Hashed JSON data")
				curlFeed.send()
				print(sha256_signature)

		''' Team2 email address'''
		def email_address(self):
			try:
				fromAddress = 'bjj5172@psu.edu'
				toAddress1 = 'bjj5172@psu.edu'
				toAddress2 = 'zvs5039@psu.edu'
				toAddress3 = 'bmp5495@psu.edu'
				toAddress4 = 'fqp5042@psu.edu'
				toAddress5 = 'rnd5089@psu.edu'
				toAddress6 = 'riz5034@psu.edu'
			except:
				print("Log exception:",sys.exc_info()[0])

		''' Subject that will be in the email adress'''
		def email_subjet(self):
			try:
				subject = "Hey Team"
				msg = MIMEText(repr(message))
			except:
				print("Log exception",sys.exc_info()[0])

		''' msg - The message that will be recieved in all the email'''
		def email_msg(self):
			try:
				msg['Subject'] = 'Subject'
				msg['From'] = fromAddress
				msg['To Brian'] = toAddress1
				msg['To Zach'] = toAddress2
				msg['To Bijal'] = toAddress3
				msg['To Francheska'] = toAddress4
				msg['To Rachel'] = toAddress5
				msg['To Ricky'] = toAddress6
			except:
				print("Log exception",sys.exc_info()[0])

		''' Sending the email'''
		def email_send(self):
			try:
				s = smtplib.SMTP_SSL('authsmtp.psu.edu', 465)
				s.sendmail(fromAddress,[toAddress1], msg.as_string())
				s.sendmail(fromAddress,[toAddress2], msg.as_string())
				s.sendmail(fromAddress,[toAddress3], msg.as_string())
				s.sendmail(fromAddress,[toAddress4], msg.as_string())
				s.sendmail(fromAddress,[toAddress5], msg.as_string())
				s.sendmail(fromAddress,[toAddress6], msg.as_string())
				curlFeed = CurlFeed("App3", "Success", "Sent JSON payload to email addresses")
				curlFeed.send()
			except exception as e:
				print("Log exception",sys.exc_info()[0])
				print("Error %s" %e.args[0])
				curlFeed = CurlFeed("App3", "Failed", e.args[0])
				curlFeed.send()

		''' uri-The uri the user has to iput from App4'''
		def pyro_payload(self):
			try:
				uri = input("Please enter the uri: ").strip()
				transformer = Pyro4.Proxy(uri)
				print("Sending JSON Payload to app4")
				print(transformer.get_payload(repr(message)))
				curlFeed = CurlFeed("App3", "Success", "Sent JSON payload to App4")
				curlFeed.send()
			except:
				print("Log exception",sys.exc_info()[0])

				try:
					with open('json.txt', 'rb') as f_in:
						try:
							with gzip.open('json.txt.gz', 'wb') as f_out:
								print("Now compressing the JSON Payload")
								shutil.copyfileobj(f_in, f_out)
								print("The file has been compressed")
								curlFeed = CurlFeed("App3", "Success", "Compressed JSON file")
								curlFeed.send()
						except:
							print("Log exception 1:", sys.exc_info()[0])
				except:
					print("Log exception 2:", sys.exc_info()[0])
		except:
			print("Log exception 3:", sys.exc_info()[0])
except:
	print("Log exception 4:", sys.exc_info()[0])
