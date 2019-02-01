def containA(txtFile):
    fileRead = open(txtFile, 'r')
    indexList = []
    for line in fileRead:
        for index, character in enumerate(line):
            if character == 'a':
                indexList.append(index+1)
    fileRead.close()
    if indexList == []:
        return "no A's"
    else:
        return "A's in indeces", indexList


def containAB(txtFile):
    fileRead = open(txtFile, 'r')
    indexList = []
    for line in fileRead:
        for index, character in enumerate(line):
            if character == 'a':
                if line[index+1] == 'b':
                    indexList.append(index+2)
    fileRead.close()
    if indexList == []:
        return "no AB's"
    else:
        return "AB's in indeces", indexList


def containAA(txtFile):
    fileRead = open(txtFile, 'r')
    indexList = []
    for line in fileRead:
        for index, character in enumerate(line):
            if character == 'a':
                if line[index+1] == 'a':
                    indexList.append(index+2)
    fileRead.close()
    if indexList == []:
        return "no AA's"
    else:
        return "AA's in indeces", indexList


def containABC(txtFile):
    fileRead = open(txtFile, 'r')
    indexList = []
    for line in fileRead:
        for index, character in enumerate(line):
            if character == 'a':
                if line[index+1] == 'b':
                    if line[index+2] == 'c':
                        indexList.append(index+3)
    fileRead.close()
    if indexList == []:
        return "no ABC's"
    else:
        return "ABC's in indeces", indexList


def containAApattern(txtFile):
    fileRead = open(txtFile, 'r')
    indexList = []
    tempString = ''
    for line in fileRead:
        for index, character in enumerate(line):
            if character == 'a':
                tempString += character
            else:
                if 'aa' in tempString:  # no need for index+1 because it adds
                    indexList.append(index)   # index after actual placement
                tempString = ''
    fileRead.close()
    if indexList == []:
        return "no AA*AA's"
    else:
        return "AA*AA's in indeces", indexList


'''def containAAB(txtFile):
    fileRead = open(txtFile, 'r')
    indexList = []
    tempString = ''
    for line in fileRead:
        for index, character in enumerate(line):
            if character == 'a':
                tempString += character
            else:
                if 'aa' in tempString:
                    indexList.append(index)
                tempString = ''
    fileRead.close()
    if indexList == []:
        return "no AA*AA's"
    else:
        return "AA*AA's in indeces", indexList'''


# begin main
print(containA('assg1test.txt'))
print(containAB('assg1test.txt'))
print(containAA('assg1test.txt'))
print(containABC('assg1test.txt'))
print(containAApattern('assg1test.txt'))
