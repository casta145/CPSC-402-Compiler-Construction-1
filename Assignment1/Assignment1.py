'''
def readABC(txtFile):
    fileRead = open(txtFile, 'r')
    # for loop to find the letter that occurs the most
    for line in fileRead:
        for character in line:
            print (character)
            if character.isalpha():
                if character == 'a':
                    print (line.index(character))
                    if line[line.index(character)+1] == 'b':
                        if line[line.index(character)+2] == 'c':
                            return 'yes'
    fileRead.close()
    return 'no'
'''


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
    for line in fileRead:
        for index, character in enumerate(line):
            if character == 'a':
                if line[index+1] == 'a':
                    indexAdd = 0
                    for x in line[index+1]:
                        if x != 'a':
                            break
                        indexAdd += 1
                    indexList.append(index+indexAdd+1)
    fileRead.close()
    if indexList == []:
        return "no ABC's"
    else:
        return "ABC's in indeces", indexList


# begin main
print(containA('assg1test.txt'))
print(containAB('assg1test.txt'))
print(containAA('assg1test.txt'))
print(containABC('assg1test.txt'))
print(containAApattern('assg1test.txt'))

'''
string = 'abbccabc'
indexList = []
for index, character in enumerate(string):
    if character == 'a':
        indexList.append(index)
print (indexList)
'''
