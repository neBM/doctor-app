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
<li> The system should allow a doctor to enter visit details and prescriptions regarding a past booking.</li>
<li> The system should then send confirmation messages to the patient and the doctor</li>
<li> The system should allow a doctor to view the visit details and prescriptions regarding a past booking, for which the doctor provided visit details and prescriptions.</li>
<li> The system should allow a doctor to edit the visit details and prescriptions regarding a past booking, for which the doctor provided visit details and prescriptions. The system should then send confirmation messages to the patient and the doctor.</li>
<li> The system should allow a doctor to assign a new doctor to a patient using the list of all doctors. The system should then send confirmation messages to the patient and both doctors.</li>
<li> The system should log all access from a user, i.e. who accessed what functionality and when.</li><ul><br>

## Feature: Authentication
For a doctor to sign in the application, they first need open the 'Login Page'. From here they can type their username and password in the textfields provided, then click 'login'

