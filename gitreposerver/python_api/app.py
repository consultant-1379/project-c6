from flask import Flask
from flask import request
from flask_cors import *
import traceback
from gitAnalysisBackUp import processGitProcess, setRepoID
app = Flask(__name__)

@app.route('/')
@cross_origin(supports_credentials=True)
def requestToCreateCSV():
    gitRepo = request.args.get('repo')
    repoID = request.args.get('repo_id')

    print(repoID)
    setRepoID(repoID)
    check = processGitProcess(gitRepo)
    if check:
        return "Successfully create a csv file/got repo ID"
    return "-1"

    


# @app.route('/getRepoID', methods = ['POST'])
# def getRepoID():
#     repoID = request.args.get('repo_id')

#     print("Repo ID:", repoID)

#     setRepoID(repoID)

#     return "Successfully retrieved repo ID"


if __name__ == '__main__':
    app.run()
