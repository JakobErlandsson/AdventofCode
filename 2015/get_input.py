import requests
import os.path

def get(day):
    if os.path.isfile('day{}.txt'.format(day)):
        f = open("day{}.txt".format(day), "r")
        puzzle_input = f.read()
    else: 
        url = "https://adventofcode.com/2015/day/{}/input".format(day)
        cookie = {"session": "53616c7465645f5f7d273718917e83269d10252ce74b4f041124c210943531da183eb2a000867bfcfb75bdaa09b31e9c"}
        res = requests.get(url, cookies=cookie)
        puzzle_input = res.text
        f = open("day{}.txt".format(day), "w")
        f.write(puzzle_input)
    f.close()

    return puzzle_input[:-1] if puzzle_input[-1] == '\n' else puzzle_input