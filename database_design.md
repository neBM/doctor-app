# Conceptual Model

## users
Store the username and password for each of the users that can access the system
| email | password | firstName | lastName | phoneNumber | address | gender | dateOfBirth | type |
| ----- | -------- | --------- | -------- | ----------- | ------- | ------ | ----------- | ---- |

## patientDetials
A table of patients (joined to users table)
| patientEmail | assignedDoctor |
| ------------ | -------------- |

## accessRecords
Records who accessed each feature and when
| id | email | timestamp | event |
| -- | ----- | --------- | ----- |

## messages
Store messages to and from users to display on the welcome page
| id | to | from | message | read |
| -- | -- | ---- | ------- | ---- |

## bookings
Stores when a patient meets a doctor
| id | doctor | patient | timestamp | duration |
| -- | ------ | ------- | --------- | -------- |

## visitDetails
Stores the notes taken when a patient meets a doctor
| id | patientEmail | doctor | visitDate | visitNotes | prescriptionName | prescriptionQuantity |
| -- | ------------ | ------ | --------- | ---------- | ---------------- | -------------------- |
