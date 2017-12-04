# RoomFinder

Welcome to the UOIT RoomFinder app! This app helps students at the UOIT North Campus find an empty room to study in. 

# Mobile Application
In order to run the app, download the 'android' folder then run the app on an Android device or emulator using an IDE such as Android Studio. 

The app has three functions:
- Quick Search: will find all the empty rooms for the current time.
- Advanced Search: will find all the empty rooms for the day and time entered by the user.
- Room Schedule: will look up the schedule for a room specified by the user.

# API
In addition the app, our API could be used to find empty rooms, although the data is not as presentable as it is on the mobile app.

Our API supports 6 different requests which offer different results:

Firstly, the URL of the API is the following:
http://roomfinder-187219.appspot.com/ \n
This URL does not redirect anywhere. The following paths must be enter after it to receive results:

/quick?key=[key]&day=[day]&time=[time] \n
Will return a list of all available rooms for the specified day and time along with the ratings associated with that room.

/schedule?key=[key]&room=[room]&day=[day] \n
Will return the schedule for specified room on a specific day.

/byname?key=[key]&day=[day]&time=[time]&room=[room] \n
Will return the remaining classes for a room after the time specified on that day.

/enterreview?key=[key]&room=[room]&review=[review] \n
Used for entering a review for a room.

/getreview?key=[key]&room=[room] \n
Gets the reviews for a specified room.

/updateratings?key=[key]&room=[room]&wifi=[rating]&quiet=[rating]&seat=[rating]&overall=[rating]&total=[rating] \n
Used to udate the ratings for a room after the user has rated it.

- [Key] = dsproj
- [Day] = Monday, Tuesday,..., Friday
- [Time] = 24 hr time where the colon is replaced with a colon. Example: 2:10 PM would be entered as 14.10
- [Room] = Room name at UOIT north campus. Example: UA1220 
- [Rating] = Value between 1-5.
