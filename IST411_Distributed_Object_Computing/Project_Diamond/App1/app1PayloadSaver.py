
import sys, socket, ssl, json, logging
import settings
sys.path.append('../')
from App5.curlfeed import CurlFeed

class App1PayloadSaver:
	"""
	Contains  method to save a payload
	"""
	def save_payload(self,payload):
		"""
		Writes JSON payload to text file
		:param payload: The JSON payload
		:return: Returns true if successful, false if failed
		"""
		try:
			with open('json.txt', 'w') as outFile:
				outFile.write(json.dumps(payload))
				curlFeed = CurlFeed("App1","Success","Saved Json payload")
				curlFeed.send()
				return True
		except:
			# Catch all exceptions
			e = sys.exc_info()[0]
			print("Error:%s"%e)
			logging.error(e)
			curlFeed = CurlFeed("App1","Failed","Failed to save JSON payload")
			curlfeed.send()
			return False

if __name__ =='__main__':
	a = App1PayloadSaver()
	payload = {'name':'bijal'}
	a.save_payload(payload)

