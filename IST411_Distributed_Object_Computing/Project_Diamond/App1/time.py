import time, datetime

time1 = datetime.datetime.now()
print(time1)
time.sleep(3)
time2 = datetime.datetime.now()
print(time2)
print(time2 - time1)
