# Conceptual Model

## Users
Store the username and password for each of the users that can access the system
| email | name | tel |
| ----- | ---- | --- |

## Doctors
A table of doctors (joined to users table)
| email | password |
| ----- | -------- |

## Patients
A table of patients (joined to users table)
| email | details |
| ----- | ------- |

## Accesses
Records who accessed each feature and when
| email | timestamp | module |
| ----- | --------- | ------ |

## Messages
Store messages to and from users to display on the welcome page
| to | from | message | read |
| -- | ---- | ------- | ---- |

## Bookings
Stores when a patient meets a doctor
| doctor | patient | timestamp | duration |
| ------ | ------- | --------- | -------- |

## Doctors Visit Details and Description
Stores the notes taken when a patient meets a doctor
| doctorsNotes | prescriptionName | prescriptionQty |
| ------------ | ---------------- | --------------- |
