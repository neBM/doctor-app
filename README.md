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
cd Solution && mvn clean compile assembly:single
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

## Feature: View booking
For a doctor to use the view booking feature, they first need to click on the 'Bookings' tab at the top of the application. From the bookings tab, they can click on the 'Filter' button. This will then the 'Booking Filter' window. From here, doctors can then filter their bookings by month and year from the dropdown lists provided.

## Feature: View Patient's information
//

## Feature: View Visit Details & Prescriptions
//

## Feature: Enter Visit Details & Prescriptions
For a doctor to create a new visit, they first need to click on the 'Add Visit & Prescription' tab on the top right of the application. From here they have access to several attributes that are required to create a visit. These include the patient's full name, the doctor's full name, the date and time of visit, any visit notes, prescription name and the prescription quantity. Once they have completed filling out the form, they can then click submit.

## Feature: Edit Visit Details & Prescriotions
//

## Feature: Assign Doctor to Patient
//

## Feature: Authorisation
//


