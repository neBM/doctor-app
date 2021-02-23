
- The system should allow a user to log in with their username and password, and 
  log out. Every time the user logs in, the system should show the user’s new 
  messages on the welcome screen. 
- The system should log all access from a user, i.e. who accessed what 
  functionality and when. 

    - Tasks
        - Front End:
            - If the email and password is correct new pop up window to the
              welcome screen if incorrect pop up a window stating the  input
              provided is wrong.  
            - Retrieve new messages and display them (which you receive from back 
              end).         
            - Display the messages on the screen, like gmail or outlook for 
              example. 
            - Have a button for: 
                - message(brings the user currently to a blank page) 
                - My patients list (brings the user currently to a blank page) 
                - Future booking (brings the user currently to a blank page) 
                - Add visit details & prescriptions 
        - Back End:
            - test if the input (email and password) matches what is in the 
              database. Two tests, first check email exist in database, second 
              test check password matches email password in database, if both 
              test are true return true to front end developer, else return false.
            - given the user(doctor email) retrive unread new messages. Your task 
              is to provide the sorted table of messages to the front end 
              developer.
            - Log each action from the user in a table. Time when accessed 
              welcome screen, everytime the user interacts/clicks a function on 
              the interafect a new entry is added to the log database.   



- The system should allow a doctor to enter visit details and prescriptions 
  regarding a past booking. The system should then send confirmation messages to 
  the patient and the doctor.
    - Tasks:
        - Front End:
            - Have a window that allows to enter necessary  requirements for the 
              attributes of the table and then press submit.
            - If entry check is true then submition proceeds and the user is 
              returned to the welcomed screen where the new message is displayed 
              that the added information/message has successfully been added.
            - If entry check returns false,  pop up a window stating what entry 
              is incorrect and allow user to reenter info.   
        - Back End:
            - Create a database for patients and have a functionality that 
              allows you to add to it.
            - Create a database for patients visit details and prescriptions    
              identifiable by id/email of the patient.   
            - Have a method that checks if the new input date and time does not 
              conflict with a pre-existing entry.
            - Have a method that checks the email/id and name do correspond to 
              the email/id  saved in the database.
            - If it passes the test successfully add the new info in the 
              corresponding database of the patient.     
            - Add a message to the message database staying the info has been 
              added with the info that has been added. 



















