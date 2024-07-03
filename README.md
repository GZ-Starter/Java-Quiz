# FISAC (mini project): OOP-II  
Department of Data Science & Computer Applications  
MCA-II Semester (Jan-2024)  

## Problem Statement:  
An educational institution aims to streamline its admission entrance test process by automating the 
creation of Multiple-Choice Questions (MCQs) question papers from a database of questions. Each 
question paper should contain a minimum of five (which can be user-defined) MCQs, with each MCQ 
having four options. Students are permitted to choose only one option per question. Upon submission, 
the system will automatically evaluate the answers, display the student's score on-screen, and store it in 
the database.  

Create a GUI application in Java for conducting an admission entrance test consisting of MultipleChoice Questions (MCQs) with the following requirements:

1. Login Window:  
• The application should open a login window for user authentication.  
• Users need to enter their credentials (username and password) for validation.  
• After successful login, the application should navigate to a new page.  
• This new page should contain a set of multiple-choice questions (MCQs).  

2. MCQ Page:
• Display 5 MCQs on this page, each with a question and multiple radio buttons for choosing options.  
• Users should be able to select one option per question.  
• Provide a "Submit" button for users to submit their answers.  

3. Result Display:
After the user has attempted all the questions and submitted their answers, the application should calculate and display the total score and store the same in the database for each student.

----
## Overview: 
The Admission Entrance Test Automation System is a Java-based application designed to streamline the admission process of an educational institution. The system consists of a user-friendly Graphical User Interface (GUI) that facilitates user authentication, question presentation, answer submission, and result display.  

#### Login Module:
The application initiates with a login window where users are prompted to enter their credentials—username and password. The system validates these credentials against a MongoDB database containing user information. Upon successful authentication, the user gains access to the main quiz interface.  

#### MCQ Page (Quiz Module):
After logging in, the user is presented with a set of Multiple-Choice Questions (MCQs). Each question is accompanied by four options, displayed as radio buttons, allowing the user to select a single answer per question. Navigation between questions is facilitated by "Next" and "Previous" buttons, enhancing user interaction and ease of use.  

#### Result Display (Score Module):
Upon completing the quiz and submitting the answers, the system calculates the total score based on the correct answers. The result, along with a personalized thank you message addressing the user by name, is displayed on the screen. Furthermore, the user's score is updated and stored in the _**MongoDB**_ database for future reference.  

![image](https://github.com/GZ-Starter/Java-Quiz/assets/126936908/ce40d094-e6db-4e3b-80e3-22966d7cceb0)  
![image](https://github.com/GZ-Starter/Java-Quiz/assets/126936908/7c5b0277-6f9b-4fa1-a627-d8219af3ac88)  
![image](https://github.com/GZ-Starter/Java-Quiz/assets/126936908/4bc31d9e-7872-421b-9110-62ca59a05a35)  
![image](https://github.com/GZ-Starter/Java-Quiz/assets/126936908/c6e87ec8-00ce-43b3-9f41-64845c748ef7)






