**Sprint 1:**

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
    - Tasks
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
            - Adding a getMessages method to display on welcome screen.


**Sprint 2:**
  - The system should allow a doctor to view their bookings by entering a month and year
    - Tasks
      - Front End:
        - Create a button for bookings
        - Create a pop window that is form used to enter the booking month and year
        - Add two entries which takes a month and year
        - On the form pop window have a button to return to the welcome screen
        - Have a button to submit the entries from the form
        - Create a pop window that shows the result of the month and year entered
        -Have a button to return to the welcome screen
    
      - Back End:
        - Create a class for bookings
        - Create fields for id, doctor, patient and timestamp
        - Have the constructor initialise the fields
        - Create a getDoctor method that returns the doctor
        - Create a getPatient method that returns the patient
        - Create a getTimestamp method that returns the timestamp
        - Create a getBooking method that takes a month and year as parameters and returns a set of bookings matching the month and year



- The system should allow a doctor to view their own patients with their summary information, e.g. name, phone number.
  - Tasks
    - Front End:
      - On the doctor's patients list window have it display all the doctors patients
      - Have a button on the doctor's patients list that allows the user to return to the welcome screen
      - Next to each patient have a button to view summary information  
      - When the view summary information is clikced it will create a new pop up window for the patient selected
      - On the patient's pop up window display email, first and last name, phone number, address, gender, date of birth
      - have a button on the patient's pop window that allows you to return to the welcome screen
    - Back End:
      - Create a PatientDetail class
      - Have patient's email and assigned doctor's email as fields
      - Have the fields initialised in the constructor
      - Create a getPatientEmail method that returns the patient's email
      - Create a getDoctorEmail that returns the doctor's email 
      - in the model class create a getPatientsList method that takes a doctor's email as a parameter and returns a set of patients to whom the doctor 
        is assigned to
      - In the user class create getter methods for: 
        - a getFirstName method that returns the first name of the user
        - a getLastName method that returns the last name of the user
        - a getPhoneNumber method that returns the user's phone number 
        - a getAddress method that returns the user's address 
        - a getGender method that returns the user's gender 
        - a getDateOfBirth method that returns the user's date of birth 


- The system should allow a doctor to view the visit details and prescriptions regarding a past booking, for which the doctor provided visit details 
  and prescriptions.

  - Tasks
    - Front End:
      - A button for view visit details on the welcome screen 
      - A pop up window for  visit details 
      - show all the past visit details performed by the user (the doctor)
      - A return button to the welcome screen
    
    - Back End:
      - An SQL query that retrieves the visit details for a given doctor 
      - Create a visitDetails class
      - Add all the necessary fields for the visitDetails class and initiate them in the constructor 
      - A getVisitDetails method that takes a user as a parameter and returns all the visits for that user


**Sprint 3:**
- The system should allow a doctor to edit the visit details and prescriptions regarding a past booking, for which the doctor provided visit details and prescriptions. The system should then send confirmation messages to the patient and the doctor.
  - Tasks 
    - Front End:
      - On the visitDetails pop window add a edit button that allows you to modify a specific visit 
      - When the button is clicked it brings you to a window that is similar to the form where you enter the visit details except the form entries are 
        already filled with what was previously written, the user can then modify the entries and then click the submit button which will result in 
        updating the visit details
      - When the submit button is clicked the user receives a confirmation message that the visit details has successfully been added  



- The system should allow a doctor to assign a new doctor to a patient using the list of all doctors. The system should then send confirmation messages to the patient and both doctors.

- The system should log all access from a user, i.e. who accessed what functionality and when.
    
















