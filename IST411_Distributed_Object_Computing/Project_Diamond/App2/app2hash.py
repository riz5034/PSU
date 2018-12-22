#Project: Project Diamond
#Team 2
#App2
import pysftp, base64, hashlib, hmac, sys, socket, ssl, json

class App2Hash:
	'''
	This app hashes the JSON Payload
	'''
	dataJSON = ''
	key = ''
	signature = ''

	def __init__(self,key,dataJSON):
		'''
		Parms :
		Key : the key to hash
		message: message to hmac
		'''
		self.key = key
		self.dataJSON  = dataJSON
		print(type(self.dataJSON))
		print(type(dataJSON))
	def hash(self):
		'''
		Hashes the JSON Payload
		'''
		try:
			app2key = bytes(self.key,"UTF-8")
			app2message = bytes(repr(self.dataJSON), "UTF-8")
			sha256_digester = hmac.new(app2key, app2message, hashlib.sha256)
			print(sha256_digester)
			self.sha256_signature = sha256_digester.digest()
			print("Hashing JSON Payload")
			print(self.sha256_signature)
			return True
		except:
			print("Log exception:", sys.exc_info()[0])
			return False
	def getKey(self):
		return self.key
	def getMessage(self):
		return self.message
	def getSignature(self):
		return self.signature
