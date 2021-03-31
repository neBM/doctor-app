# Compiling and Building
## Dependancies
* Maven
* Java Development Kit (JDK)
```bash
apt install maven default-jdk
```
## Steps
Run this command in bash:
```bash
mvn clean compile assembly:single
```
Jar can be found in `target/booking_app-[version]-jar-with-dependencies.jar`:
```bash
java -jar ./target/booking_app-1.0-SNAPSHOT-jar-with-dependencies.jar
```

# User Manual
## Features
<ul><li> The system should allow a user to log in with their username and password, and log out. Everytime the user logs in, the system should show the user’s new messages on the welcome screen</li>
<li> The system should allow a doctor to view their bookings by entering a month and year.</li>
<li> The system should allow a doctor to view their own patients with their summary information, e.g. name, phone number.</li>
<li> The system should allow a doctor to view the visit details and prescriptions regarding a past booking, for which the doctor provided visit details and prescriptions.</li>
<li> The system should allow a doctor to enter visit details and prescriptions regarding a past booking. The system should then send confirmation messages to the patient and the doctor.</li>
<li> The system should allow a doctor to edit the visit details and prescriptions regarding a past booking, for which the doctor provided visit details and prescriptions. The system should then send confirmation messages to the patient and the doctor</li>
<li> The system should allow a doctor to assign a new doctor to a patient using the list of all doctors. The system should then send confirmation messages to the patient and both doctors</li>
<li> The system should log all access from a user, i.e. who accessed what functionality and when.</li><ul><br>

## Feature: Authentication
For a doctor to sign in the application, they first need open the 'Login Page'. From here they can type their username and password in the textfields provided, then click 'login'

## Feature: View Booking
For a doctor to use the view booking feature, they first need to click on the 'Bookings' tab at the top of the application. From the bookings tab, they can click on the 'Filter' button. This will then the 'Booking Filter' window. From here, doctors can then filter their bookings by month and year from the dropdown lists provided.

## Feature: View Patient's information
For a doctor to view their patient's information, they first need to click on the 'Patient's List' tab on the top panel. This will show them a list of their patients. From here, they can click on 'View Summary Information' under the patient's name. This will open another window called 'Patients Summary Information' which show the patient's information.

## Feature: View Visit Details & Prescriptions
For a doctor to view their past visit details and prescriptions, they first need to click on the 'Bookings' tab at the top panel. From here, they can click on the 'Past Booking' button at the bottom left of the application. This will open a new window called 'Past Bookings'. It will show a list of the video details and prescription.

## Feature: Enter Visit Details & Prescriptions
For a doctor to create a new visit, they first need to click on the 'Add Visit & Prescription' tab on the top right of the application. From here they have access to several attributes that are required to create a visit. These include the patient's full name, the doctor's full name, the date and time of visit, any visit notes, prescription name and the prescription quantity. Once they have completed filling out the form, they can then click submit.

## Feature: Edit Visit Details & Prescriptions
For a doctor to edit the visit details and prescriptions, they first need to click on the 'Bookings' tab. From here they can click on the 'Past Booking' button at the bottom of the application. This will open a new window called 'Past Bookings', which will show a list of their past bookings. From here, they can click the 'Edit' button under the booking they would like to change. This will allow the doctor to make the neccessary changes to the visit.

## Feature: Assign Doctor to Patient
For a doctor to assign a new doctor to a patient, they first need to click on the 'Patient's List' tab at the top of the application. This will show a list of their patients. From here, they can click on the 'View Summary Information' button under their patient's name. This will open a new window called 'Patient's Summary Information'. From here, there will be a dropdown list containing the doctors available with a 'Assign New Doctor' button to confirm the new doctor that has been selected.

## Feature: Authorisation
Doctor's actions will be logged in the SQL database under the 'Access Record' table with their Name, Timestamp and Event recorded


