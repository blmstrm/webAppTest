#Imports
import os
import datetime
import sys
import itertools
import pymongo

#Mongo configuration
MONGO_HOST = "localhost"
MONGO_PORT = 27017

NR_OF_ENTRIES = 200
MEETING_COUNTER = 0
THIS_MOMENT = datetime.datetime.now()
EVENT_ID = 0

#Event object
class Event():
	def __init__(self):
		global MEETING_COUNTER
		global THIS_MOMENT
		global	EVENT_ID

		self.id = EVENT_ID
		self.start = str(THIS_MOMENT)
		self.end = str(THIS_MOMENT + datetime.timedelta(hours=1))
		self.title = "Meeting " + str(MEETING_COUNTER)

		EVENT_ID += 1
		MEETING_COUNTER += 1
		THIS_MOMENT += datetime.timedelta(hours=1)
	
		
#Stylish repeater wrap
def repeat(f,N):
	for _ in itertools.repeat(None, N):
		f()
	

#Create JSON object and Insert
def createInsert():
	tmpEvent = Event()
	mdb.events.insert(tmpEvent.__dict__)

#Print error method
def printError(ErrorType):
	sys.stderr.write("\nEXCEPTION: Caught "+ErrorType+" error.\n")

if __name__ == "__main__":
	
	#Connect to mongoDB
	mngConnection = pymongo.Connection(MONGO_HOST,MONGO_PORT)
	mdb = mngConnection.events

	#Generate new event object and dump json in mongoDB NR_OF_ENTRIES times
	repeat(createInsert,NR_OF_ENTRIES)
	
	#Disconnect to mongoDB
	mngConnection.close()




