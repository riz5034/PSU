'''
Project: Lab Python3
Purpose Details: Run eve server to send payload from Mongodb
Course: IST 411
Date Developed: SEP 30 2018
Last Date Changed: SEP 30 2018
REV:.00
'''
import sys
'''
app = Eve()-Initilize the eve server

'''
try:
	from eve import Eve
	app = Eve()

	if __name__ == '__main__':
		app.run()
except:
	print("Log exception:",sys.exc_info()[0])
