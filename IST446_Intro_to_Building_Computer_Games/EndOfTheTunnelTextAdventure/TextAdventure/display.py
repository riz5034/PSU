import sys, time, random

# Print text slowly
# Source: https://stackoverflow.com/questions/4099422/printing-slowly-simulate-typing
def slow_print(str):
    for letter in str:
        sys.stdout.write(letter)
        sys.stdout.flush()
        time.sleep(0.029)
    print("")
