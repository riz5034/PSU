from Crypto.Cipher import AES
import json

class App4aesEncryption:
	def aes_encryption():

		try:
			data = open('plaintextPayloadPatel.json', 'r')
			data = data.read()
			print(data)

			pad = b' '
			key = 'This is a key123This is a key123'
			key = key.encode('utf-8')
			iv = 'This is an IV456'
			iv = iv.encode('utf-8')
			object = AES.new(key, AES.MODE_CBC, iv)

			plaintext = data.encode('utf-8')

			length = 16 - (len(plaintext)%16)
			plaintext += length*pad
			print("Padded json payload")
			print(plaintext, "\n")

			ciphertext = object.encrypt(plaintext)
			print("Encrypted json payload")
			print(ciphertext, "\n")

			with open('encryptedPayloadPatel.aes', 'wb') as fo:
				fo.write(ciphertext)
			print("Encrypted JSON saved to file: encryptedPayloadPatel.aes")


		except Exception as e:
			print(e)
