import sys
from app4Pyro import App4Pyro
from app4Sendrabbitmq import App4SendRabbitmq
sys.path.append("../")
from App5.curlfeed import CurlFeed


def main():
	pyro = App4Pyro()
	print("Sending URI for App3 to use\n")
	pyro.sendURI()
	print("\nRecieving payload from App3\n")
	payload = pyro.getPayload()
	print(payload)
	print("\nSending payload to queue\n")
	rabbit = App4SendRabbitmq()
	rabbit.send_payloadqueue(payload)

if __name__ == "__main__":
	main()
