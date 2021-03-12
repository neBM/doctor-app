# Logical Model

## users
Store the username and password for each of the users that can access the system
| email   | password | firstName | lastName | phoneNumber | address | gender | dateOfBirth | type |
| ------- | -------- | --------- | -------- | ----------- | ------- | ------ | ----------- | ---- |
| primary |          |           |          |             |         |        |             |      |

This tables only purpose is to store a list of all the users that have access to the system. This meant that we stored basic information that everyone would have such as phone numbers, addresses, dob's, etc. in the table. Because the user would use their email to sign in, each user would be reprepsented by `email` so we chose this as the primry key. None of the other information is used to referance another table and isn't going to have any uniqueness so this was the only key in the table. We also decided to add a type column to the table of the type `ENUM` that would signify the level of the user - it would accept two types: `DOCTOR` and ` PATIENT` and their accesslevels would be set repectively in the front end. We also decided to store the passwords in the table using a hashing algorthm so that if the table leacked, the users passwords would be secure.

## patientDetials
A table of patients (joined to users table)
| patientEmail     | assignedDoctor   |
| ---------------- | ---------------- |
| primary, foreign | foreign          |

This table stores the additional data that patients have - what doctor they are assigned to. Because each patient can be assigned to one doctor and many doctors get assigned to many patients, this is a one-to-many relationship. The patient's id - the email - is the identifying information so their email is used as the primary key to identify the record and the foregin key relates to the users table. The doctorsEmail is a foregin key assigned to the `users` table `email`.

## accessRecords
Records who accessed each feature and when
| id      | email   | timestamp | event |
| ------- | ------- | --------- | ----- |
| primary | foreign |           |       |

This table is used to store the activities of each user when interacting with the ui. Because one user can interact with many things, this is a one-to-many relationship with each user being identified with the `email`. This means that there would be many duplicate emails, therefore a autoincrement attribute: `id`, is needed. We also decided that `event` be a ENUM storing either '*VIEW_MESSAGES*', '*VIEW_PATIENTS*', '*VIEW_BOOKINGS*', '*VIEW_VISTS*', '*VIEW_NEWVIST*', '*VIEW_WELCOMESCREEN*' - each storing an event of when the user views each frame in the ui.


## messages
Store messages to and from users to display on the welcome page
| id      | to      | from    | message | read |
| ------- | ------- | ------- | ------- | ---- |
| primary | foreign | foreign |         |      |

This table is used to store the messages from and to doctors and patients. Because a patient can send a message to a doctor (and vice versa), they can't be used as a composite key so a autoincrement attribute: `id`, is needed. We also added two foreign keys: `to` and `from`, to store the messages sender and recipient so that the system knows where who needs to receive the message and so that the recipient knows where to reply to. These are foreign keys relating to the `users` table. We also decided to include a `read` attribute so that a user can easily decern between messages they have read and have yet to read - this would be stored using  a *tinyint*: 0 representing unread, 1 representing read.

## bookings
Stores when a patient meets a doctor
| id      | doctor   | patient           | timestamp | duration |
| ------- | -------- | ----------------- | --------- | -------- |
| primary | foreign  | foreign, unique   | unique    |          |

This table is used to store when a patient has booked an appointment with a doctor. At the clients request, this table is used to store bookings in the future. Because, only a patient with an assigned doctor can make a booking, the patient attribute is a foreign key to the `patientDetails` table. This also applies for the doctors attribute - a forgeign key to the `users` `email` attribute. We also desided that, because a two bookings can't be made by one patient at the same time, there be a composite unuiqe key applied to patient and timestamp.

## visitDetails
Stores the notes taken when a patient meets a doctor
| id      | patientEmail    | doctor | timestamp | visitNotes | prescriptionName | prescriptionQuantity |
| ------- | --------------- | ------ | --------- | ---------- | ---------------- | -------------------- |
| primary | foreign, unique |        | unique    |            |                  |                      |

This table is used to store when a patient has booked an appointment with a doctor. At the clients request, this table is used to store bookings in the past. This is a duplicate of the bookings table with additional attributes for visit notes and prescription details: name and quantity. Because a patient can't attend two visits at the same time, a composite unuiqe key constraint is applied to `timestamp` and `patientEmail`. We also decided that `patientEmail` be a foreign key to the `patientDetails` tables `email` because a patient can't have make a visit without first being assigned to a doctor.