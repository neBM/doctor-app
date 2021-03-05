# Attendance
| Name  |  24/02/2021  | 26/02/2021 | 02/03/2021 | 04/03/2021 |
| ----- | :----------: | :--------: | :--------: | :--------: |
| Ben   |   &#x2611;   |  &#x2611;  |  &#x2611;  |  &#x2611;  | 
| Hayden|   &#x2611;   |  &#x2611;  |  &#x2611;  |  &#x2611;  | 
| Robin |   &#x2611;   |  &#x2611;  |  &#x2611;  |  &#x2611;  | 
| Sean  |   &#x2611;   |  &#x2611;  |  &#x2611;  |  &#x2611;  | 


# Scrum Meeting One
| Name   | What I did Yesterday | What tasks I will do today | Any isssues? |
| ------ | -------------------- | -------------------------- | ------------ |
| Ben    | <ul><li>test if the input (email and password) matches what is in the database. Two tests, first check email exist in database, second test check password matches email password in database, if both test are true return true to front end developer, else return false.</li><li>given the user(doctor email) retrive unread new messages. Your task is to provide the sorted table of messages to the front end developer.</li><li>Log each action from the user in a table. Time when accessed welcome screen, everytime the user interacts/clicks a function on the interafect a new entry is added to the log database.</li><li>Create a database for patients and have a functionality that allows you to add to it.</li></ul> | <ul><li>Have a method that checks if the new input date and time does not conflict with a pre-existing entry.</li></ul> | <ul><li>Needed to rebase out erronious commits in history.</li><li>Needed to reset commit spelling mistakes in history.</li></ul>
| Hayden | <ul><li> Created Normal, Extreme and Erronious testing plans</li> <li> Created vc plan</li><li> Implemented if the email and password is correct new pop up window to the welcome screen if incorrect pop up a window stating the input provided is wrong. </li></ul> | <ul><li> Implement retrieve new messages and display them (which you receive from back end). </li> <li>  Implement display the messages on the screen,like gmail or outlook for example.  </li></ul> | <ul> <li>Issue with updating when a message was read on the server </li></ul> |
| Robin  | <ul><li>Create a database for patients and have a functionality that allows you to add to it.(ben created the add method)</li><li>Create a database for patients visit details and prescriptions identifiable by id/email of the patient.</li><li>If it passes the test successfully add the new info in the corresponding database of the patient.</li><li>Add a message to the message database staying the info has been added with the info that has been added.</li></ul> | <ul><li>Adding a getMessages method to display on welcome screen.</li></ul> | <ul><li>Establishing connection with the SQL server</li><li>Passing the java parameters as an sql query to the database.</li></ul> 
| Sean   | <ul><li> Implemented GUI for the Login Interface<li></li>Added message button and panel(brings the user currently to a blank page)</li><li>Added My patients list button (brings the user currently to a blank page)</li><li>Added Future booking button (brings the user currently to a blank page)</li><li>Added Add visit details & prescriptions button</li></ul> | <ul><li> Have a window that allows to enter necessary requirements for the attributes of the table and then press submit.</li><li>If entry check is true then submition proceeds and the user is returned to the welcomed screen where the new message is displayed that the added information/message has successfully been added.</li><li>If entry check returns false, pop up a window stating what entry is incorrect and allow user to reenter info. </li></ul>  | <ul><li>Fixing missing SQL driver</li></ul> |

# Scrum Meeting Two
| Name   | What I did Yesterday | What tasks I will do today | Any isssues? |
| ------ | -------------------- | -------------------------- | ------------ |
| Ben    | <ul><li>Have a method that checks if the new input date and time does not conflict with a pre-existing entry.</li></ul> | <ul><li>Rebase out #0e54d8985406f6ae5e7e97d35e3e0e7bbf549132 and #8a2554ef6502e7c2d969754f1cfca8d2e1a78030 from feature/authentication</li><li>Export DB<li>Merge feature/authentication and feature/newVisit to master</li></ul> | <ul><li>#0e54d8985406f6ae5e7e97d35e3e0e7bbf549132 and #8a2554ef6502e7c2d969754f1cfca8d2e1a78030 still exists in master branch after merge</li></ul> |
| Hayden | <ul> <li> Implemented retrieve new messages and display them (which you receive from back end). </li><li> Implement display the messages on the screen,like gmail or outlook for example. </li> </ul> | <ul> <li> Follow the testing plan to find any potential bugs with the log in and messages page </li> </ul> | |
| Robin  | <ul><li>Adding a getMessages method to display on welcome screen.</li></ul> | <ul><li>Follow the testing plan to find any bugs in adding or updating the sql database from the java program.</li></ul> | 
| Sean   | <ul><li>Added DataTimeException, DataTruncation and SQLIntergrityConstraintViolation exceptions </li> <li>Added Error window functionality </li></ul> | <ul><li>Follow the testing plan to find any potential bugs in add visit functionality</li><ul>

# Scrum Meeting Three
| Name   | What I did Yesterday | What tasks I will do today | Any isssues? |
| ------ | -------------------- | -------------------------- | ------------ |
| Ben    | <ul><li>Rebase out #0e54d8985406f6ae5e7e97d35e3e0e7bbf549132 and #8a2554ef6502e7c2d969754f1cfca8d2e1a78030 from feature/authentication</li><li>Export DB<li>Merge feature/authentication and feature/newVisit to master</li></ul> | <ul><li>Make a new branch for feature/viewPatient and feature/viewBooking</li><li>Rebase out #0e54d8985406f6ae5e7e97d35e3e0e7bbf549132 and #8a2554ef6502e7c2d969754f1cfca8d2e1a78030 from master</li><ul> | <ul><li>Cherry-picked commits get their commit dates changed after rebasing even though the date of the origional commit was done at a different time</li></ul> |
| Hayden | |<ul> <li> Continue testing and making changes for front end features of sprint 1 that developed </li> <li> Front end for the system should allow a doctor to view their own patients with their summary information, e.g. name, phone number. </li> </ul> | |
| Robin  | <ul><li>Checked for bugs in the message and user class</li></ul> | <ul><li>Create the tasks list for the next three stories</li></ul> | 
| Sean   | <ul><li>Fixed SQL query in addMessage() method #973ea13a</li></ul> | <ul><li>Continue testing front-end features for sprint 1</li><li>Make a start on sprint 2 user stories</li></ul> | 

# Scrum Meeting Four
| Name   | What I did Yesterday | What tasks I will do today | Any isssues? |
| ------ | -------------------- | -------------------------- | ------------ |
| Ben    | <ul><li>Created new branches for feature/viewPatient and feature/viewBooking</li><li>Exported db</li><li>Rebased out #0e54d8985406f6ae5e7e97d35e3e0e7bbf549132 and #8a2554ef6502e7c2d969754f1cfca8d2e1a78030 from feature/authentication</li></ul> | <ul><li>Implement getter for a set of bookings that apply to a user</li><li>Fix #6</li><li>Refactor previous sprint</li><li>Create maven project</li><li>Implement filter for ui's messages to display messages relevent only to the logged in user</li></ul> |  |
| Hayden | <ul> <li> Assisted with making changes to the message display from sprint 1 and making it only show that users incoming messages instead of all messages </li> </ul> | <ul> <li> Begin designing the page to allow doctors to view their patients along with their summary information</li> </ul> |  |
| Robin  | <ul><li>Wrote the tasks for 2 user stories</li></ul> | <ul><li>Write the tasks for the remaining</li><li>Code the sql query needed to retrieve all the needed information for viewPatient</li></ul> | 
| Sean   | |  | 
