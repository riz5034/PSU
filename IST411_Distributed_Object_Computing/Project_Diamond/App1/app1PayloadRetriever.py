# Project: Project Diamond
# Purpose Details: Retrieve a JSON payload from the internet
# Course: IST 411
# Author: Team 2
# Date Developed: 11/1/18
# Last Date Changed: 11/30/18
# Rev: 1

import sys, urllib.request, json, logging
import settings
sys.path.append('../')
from App5.curlfeed import CurlFeed

class App1PayloadRetriever:
	"""
	Contains methods to retrieve a JSON payload from the internet
	"""
	def __init__(self):
		"""
		Default constructor for new App1PayloadRetriever object
		:return: Returns nothing
		"""

		self.url = settings.URL
		self.param = settings.PARAM

	def retrieve_json(self):
		"""
		Retrieves a JSON payload givena URL and parameter
		:return: Returns a JSON payload
		"""

		try:
			response = urllib.request.urlopen(self.url + self.param)
			payload = response.read()
			jsonPayload = json.loads(payload.decode('utf-8'))
			curlFeed = CurlFeed("App1", "Success", "Retrieved JSON payload from URL")
			curlFeed.send()
			return jsonPayload
		except:
			print("error hit")
			# Catch all exceptions
			e = sys.exc_info()[0]
			print("Error:%s"%e)
			logging.error(e)
			curlFeed = CurlFeed("App1", "Failed", "Failed to retrieve JSON payload from URL")
			curlFeed.send()
			return {}

if __name__ == '__main__':
	logging.basicConfig(filename='App1Log.log', level=logging.ERROR)
	retriever = App1PayloadRetriever()
	payload = retriever.retrieve_json()
	print(payload)
