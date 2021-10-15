import requests
import os.path

def get(day):
    if os.path.isfile('day{}.txt'.format(day)):
        f = open("day{}.txt".format(day), "r")
        puzzle_input = f.read()
    else: 
        url = "https://adventofcode.com/2015/day/{}/input".format(day)
        cookie = {"session": "53616c7465645f5f573763b18387bf4d84b81021908f1bde1bec63fc346829ea2e7701e510111f33974484b9ed9e250a"}
        res = requests.get(url, cookies=cookie)
        puzzle_input = res.text
        f = open("day{}.txt".format(day), "w")
        f.write(puzzle_input)
    f.close()

    return puzzle_input[:-1] if puzzle_input[-1] == '\n' else puzzle_input