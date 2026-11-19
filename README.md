# JavaPOE Part 1

## Project Overview

This project was developed as part of the Programming 1A Portfolio of Evidence (POE) using Java. Part 1 focuses on developing a console-based user registration and login system.
The application allows a user to register by providing their first name, last name, username, password, and cellphone number. The system validates the information entered by the user according to the required rules, including username formatting, password complexity, and cellphone number validation.
After registration, the user can log in using their registered username and password. The application checks the entered login details against the registered information and provides appropriate confirmation or error messages depending on whether authentication is successful.
The project also includes unit testing to test important functionality within the login system. The application was developed using beginner-level Java programming concepts, including classes, methods, conditional statements, user input, validation, and basic testing,
there will be a YouTube video with a better detailed explanation of how the project came together.

## Features

- User registration
- Username validation
- Password complexity validation
- Cellphone number validation
- User login and authentication
- Confirmation and error messages
- Unit testing

## Technologies Used

- Java
- NetBeans
- JUnit 4.13.2
- Hamcrest 1.3

## Project Structure

- `JavaPOEPart1.java` – Main program that runs the application
- `Login.java` – Contains the registration and login functionality
- `LoginIT.java` – Contains the unit tests

## How to Run

1. Open the project in NetBeans.
2. Run `JavaPOEPart1.java`.
3. Follow the instructions displayed in the console.
4. Enter the required registration and login information.

# Java POE - Part 1 & Part 2: QuickChat Application

## Overview
This project is an advanced Java console application developed for the Programming 1A module (PROG5121). 
**QuickChat** handles user registration, secure login authentication, message validation, string manipulation (generating message hashes and unique IDs), and JSON data storage.

## FINAL POE Part 3: Stored Messages Application

Java messaging application extended for **PROG5121 POE Part 3** with array-based message handling, JSON storage/reading, search/delete/report features, and automated unit testing.

## Project Structure & Testing
* `src/javaopoe/part1/` — Core application and message-handling source files (`StoredMessages.java`, `Sendmessage.java`, `Login.java`, `JavaPOEPart1.java`).
* `test/javaopoe/part1/` — JUnit test classes (`StoredMessagesTest.java`).
* `messages.json` — Stored message data file.
* Automated tests run via GitHub Actions (`TestJava.yml`).

## Attribution
* External references, regex validation, and JSON reading/writing logic follow standard Java/JSON documentation and AI-assisted guidance.
*

## Features Implemented
### Part 1
* **User Registration**: Username validation (must contain an underscore and be max 5 characters) and password complexity checks.
* **Cell Phone Validation**: Regular expression (regex) checks ensuring South African phone numbers adhere to the `+27` format.
* **Login Authentication**: Verification of user credentials with proper feedback messages.

### Part 2
* **Interactive Menu**: Numeric menu options allowing users to navigate through the application.
* **Loop Structures**: Robust `while` and `for` loops to handle bulk message entry based on user specifications.
* **String Manipulation**: Automatic generation of unique **Message IDs** and **Message Hashes** using substring and loop counters.
* **JSON File Storage**: Successfully saves processed messages into a `messages.json` data file.
* **Automated Unit Tests**: Comprehensive JUnit test classes (`LoginIT.java`) testing core application logic and data validation.

## Part 3 
* Populates arrays for sent messages, disregarded messages, stored messages (read from JSON), message hashes, and message IDs.
* Adds a fourth menu option (**Stored Messages**) to:
  * Display sender and recipient of stored messages.
  * Display the longest stored message.
  * Search by message ID and display the recipient and message.
  * Search all messages for a specific recipient.
  * Delete a message by message hash.
  * Display a full message report.

## Technologies Used
* **Java** (NetBeans IDE)
* **JUnit** for Unit Testing
* **JSON** for Data Persistence
* **Git & GitHub** for Version Control and Code Management

## Author

Marvel Akinwumi
