import re
from pydriller import Repository
from pydriller.metrics.process.hunks_count import HunksCount
import csv
import json
import threading

#https://github.com/rise-summer/team6-education
#https://github.com/kevinniland/MD5-message-digest-algorithm
# repo = 'https://github.com/rise-summer/team6-education'
#repo = 'https://github.com/kevinniland/Ericsson-Coding'  # Small repo, useful for quick testing
repoHashCode = 0
count = 0
numberOfContributor = 0
commitDateTimeList =[]
numOfLineAdded = 0
numOfLineRemoved = 0
maxOfChangeSet = 0
totalOfChangeSet = 0
maxOfChurn = 0
sinceDateTime = None
toDateTime = None
contributorsDict = dict()
averageChangeSet = 0
averageCodeChurn = 0
contributionPerContributor = dict()
MinorCount = 0
HunkCount = 0

lock = threading.Lock()

def getRepoID():
    return repoHashCode

def setRepoID(repoID):
    global repoHashCode

    repoHashCode = int(repoID)

def compareFileSize(value):
    global maxOfChangeSet
    if value > maxOfChangeSet:
        maxOfChangeSet = value
    else:
        maxOfChangeSet = maxOfChangeSet

def calculateAverageFileSize(size,num):
    return size/num

def compareChurn(a,b):
    global maxOfChurn
    churn = a - b
    if churn > maxOfChurn:
        maxOfChurn = churn
    else:
        maxOfChurn = maxOfChurn

    
def averageChurn(totalAdd,totalRemove,num):
    return (totalAdd-totalRemove)/num

def getDateTime(num,timeValue):
    global sinceDateTime
    global toDateTime
    if num == 1:
        sinceDateTime=timeValue
    else:
        toDateTime=timeValue

def calculateContribution(totalModified):
    global contributorsDict
    for key,value in contributorsDict.items():
        contributorsDict[key] = round(100*(value) / totalModified,2)
    return contributorsDict

def verifyNumOfMinor():
    numOfMinor = 0
    for value in contributorsDict.values():
        if value < 5:
            numOfMinor+=1
    return numOfMinor

def transferContributorsFormat():
    stepOne = "$".join(map(str, contributionPerContributor.items()))
    contributionString = stepOne.replace(",",":")
    contributionString = contributionString.replace("(","")
    contributionString = contributionString.replace(")","")
    contributionString = contributionString.replace("'","")
    contributionString = contributionString.replace(" ","")
    return contributionString

def subtask(date,insertions,deletions,files,authorName):
    global count
    global commitDateTimeList
    global numOfLineAdded
    global numOfLineRemoved
    global totalOfChangeSet
    global contributorsDict
    
    count+=1
    commitDateTimeList.append(date)
    getDateTime(count,date)
    numOfLineAdded+=insertions
    numOfLineRemoved+=deletions
    totalOfChangeSet+=files
    compareFileSize(files)
    compareChurn(int(insertions),int(deletions))
    author = authorName.strip()

    line_modified = insertions - deletions
    contributorsDict[author]=contributorsDict.setdefault(author,0)+line_modified

    
def processGitProcess(repo):
    lock.acquire()
    try:
        print("into the processGitProcess method")
        global count
        global commitDateTimeList
        global numOfLineAdded
        global numOfLineRemoved
        global totalOfChangeSet
        global maxOfChangeSet
        global maxOfChurn
        global contributorsDict
        global averageChangeSet
        global averageCodeChurn
        global contributionPerContributor
        global MinorCount
        global HunkCount


        # Init
        count = 0
        commitDateTimeList = []
        numOfLineAdded = 0
        numOfLineRemoved = 0
        totalOfChangeSet = 0
        maxOfChangeSet = 0
        maxOfChurn = 0
        contributorsDict = dict()
        averageChangeSet = 0
        averageCodeChurn = 0
        contributionPerContributor = dict()
        MinorCount = 0
        HunkCount = 0

        threads = []
        i = 0

        repoHashCode = getRepoID()

        for commit in Repository(repo).traverse_commits():
            i += 1
            print(i)

            t = threading.Thread(target=subtask,args=(commit.committer_date,commit.insertions,commit.deletions,commit.files,commit.author.name))
            threads.append(t)

        for t in threads:
            t.start()
            #count+=1
            #commitDateTimeList.append(commit.committer_date)
            #getDateTime(count,commit.committer_date)
            #numOfLineAdded+=commit.insertions
            #numOfLineRemoved+=commit.deletions
            #totalOfChangeSet+=commit.files
            #compareFileSize(commit.files)
            #compareChurn(int(commit.insertions),int(commit.deletions))
            #author = commit.author.name.strip()

            #line_modified = commit.insertions - commit.deletions
            #contributorsDict[author]=contributorsDict.setdefault(author,0)+line_modified

            # print("-------------------------------------------------------------------------------------------")
            # print("The Author: %s / The Commit Date: %s / The No.%d" % (commit.author.name,commit.committer_date,count))

        print("Repo hash code: %d" % (repoHashCode))
        print("total num commits: %d" % (count))
        print("total line added: %d / total line removed: %d" % (numOfLineAdded,numOfLineRemoved))
        print("max change set: %d" % maxOfChangeSet)

        averageChangeSet = calculateAverageFileSize(totalOfChangeSet,count)
        print("average change set: %d" % averageChangeSet)
        print("max churn: %d" % maxOfChurn)
        averageCodeChurn = averageChurn(numOfLineAdded,numOfLineRemoved,count)
        print("average churn: %d" % averageCodeChurn)

        totalLines = numOfLineAdded - numOfLineRemoved
        contributionPerContributor = calculateContribution(totalLines)
        print('Contribution of per contributor: %s' % contributionPerContributor)
        print('Number of contributor: %d' % len(contributionPerContributor))
        MinorCount = verifyNumOfMinor()
        print('Number of Minor contributor: %d' % MinorCount)


        hunks_metric = HunksCount(path_to_repo=repo, since=sinceDateTime, to=toDateTime)
        HunkCount = sum(hunks_metric.count().values())
        print('Number of Hunk: %d' % HunkCount)

        processCSVTransfer()
        return True
    except BaseException:
        print('not found the url')
    finally:
        lock.release()


def processCSVTransfer():
    print("Transfering to csv file......")

    csvData = []

    csvData.extend((repoHashCode, count , "$".join(map(str,commitDateTimeList)),numOfLineAdded,numOfLineRemoved,maxOfChangeSet,averageChangeSet,
                maxOfChurn,averageCodeChurn,len(contributionPerContributor),MinorCount,transferContributorsFormat(),HunkCount))

    __headersList = ['Repo ID', 'NumberOfCommits', 'Commit Dates', 'Lines added', 'Lines removed', 'Max change sets', 'Average change sets', 'Max code churn',
                 'Average code churn','Count of Contributor','Count of Minor Contributor','ContributionPerContributor','Hunk Count']

    createCSV(__headersList,csvData)
    print("Transfering to csv file Done")



def createCSV(headersList, csvData):
    with open("metrics.csv", "w", encoding="UTF8") as f:
            writer = csv.writer(f)

            writer.writerow(headersList)
            writer.writerow(csvData)
