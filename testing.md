# Normal
| Test Desc | Input Data | Expected Result | 
| --------- | ---------- | --------------- |
|Logging in with correct details| Correct email and password| Should log the user in and take them to the welcome screen, displaying any new messages theyve recieved|
|Attempt to enter a booking for a date and time that is in the future| dd/mm/yy that is in the future| A booking should be created for the patient and theyre doctor and the data and time specified in the booking|
|Attempt to assign a new doctor to a patient| Selecting a new doctor| The patient should be assigned to the new doctor successfully, a confirmation message should be sent to the patient once this has happened|
|Attempt to add a previous visit with a date in the past| dd/mm/yy in the past| The previous visit should be accepted along with any additional notes the doctor|
|Attempt to view any bookings that a doctor has coming up| Access the view bookings page| The user should be taken to a page that shows any bookings they have coming up, prioritizing the ones they have that day|
|Attempt to view the previous visits page| Access the previous visits| The user should be taken to their previous visits page|
|Attempt to enter additional notes for a previous visit| Enter data into a previous visit. e.g.prescriptions and doctor notes| Any notes the doctor enters should be added to the previous visit notes, a confirmation message should be sent once this has happened|
|Attempt to edit notes from a previous visit| Editing data from a previous visit. e.g.prescriptions and doctor notes| Any editions the doctor makes should update the previous visit notes, a confirmation message should be sent once this has happened|
|Attempt to access data for a patient assigned to me| Access patients data| Doctor should be allowed access to their patients data and the access should be logged in the system with who accessed the data and when|
|Attempt to view my patients previous visit details and prescriptions| View previous visit details| Doctor should be allowed access to the data and should be taken to a screen showing said data|
|Assign a new patient to a doctor when they first make a booking| Assign a doctor| A doctor should be assigned to the patient in the database, a confirmation should be sent to the patient once this has happened|
# Extreme
| Test Desc | Input Data | Expected Result | 
| --------- | ---------- | --------------- |
|Attempt to enter a booking for todays date| dd/mm/yy that is today| The booking should be accepted|
|Attempt to assign a the same doctor to a patient theyre already assigned to| Selecting the same doctor| Nothing should happen and the user should be taken back to welcome screen|
|Attempt to add a booking for a doctor that already has a booking at that time| dd/mm/yy that is already taken| Booking should not be added and the patient should have an error message appear that tells them to enter a different time/date|
|Logging in with either the correct username and password but not both correct. e.g. Correct username but incorrect password| Username and password | The user should be denied access and an error message should appear stating that the username or password is incorrect|
|Attempt to send a confirmation message to a user (patient or doctor)| Confirmation message | A message should appear when the user next logs in showing that they recieved a new message|
# Erroneous
| Test Desc | Input Data | Expected Result | 
| --------- | ---------- | --------------- |
|Logging in with incorrect details| Incorrect email and password| Error message appears stating that theyve entered the wrong details and to try again|
|Attempt to enter a booking for a date that is in the past| dd/mm/yy that is in the past| Error message will appear stating that the booking will not be added and the doctor should be taken back to the welcome screen|
|Attempt to add a previous visit with a date from the future| dd/mm/yy| Error message should appear stating that this visit has not occured yet and therefore cannot be added to the previous visits table|
|Attempt to access data for a patient assigned to a different doctor| Access patients data| Doctor should not be allowed access and the attempted access should be logged as well with who tried to access and when|
|Attempt to edit previous visit data for a patient assigned to a different doctor| Edit patients data | Doctor should not be allowed to edit and of the data and any changes they make shouldnt be implemented, the system should also log that the doctor attempted to edit this data and when they tried |
|Attempt to enter previous visit data for a patient assigned to a different doctor| Enter patients data | Doctor should not be allowed to enter any data and no changes should be implemented, the system should also log that the doctor attempted to enter this data and when they tried |
