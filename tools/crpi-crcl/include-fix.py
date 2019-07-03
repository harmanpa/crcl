#!/usr/bin/python

import sys, re

while True:
    line = sys.stdin.readline()
    if line == "":
        break
    # find any include directive lines
    match = re.match('^\s*\#\s*include', line)
    if match:
        # convert any backslashes to slashes
        line = match.string.replace('\\', '/')
        # eliminate any leading path relatives
        line = re.sub('\.\./+', '', line)

    # print the original or its replacement
    sys.stdout.write(line)
    
sys.exit(0)





