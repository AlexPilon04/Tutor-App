# Student-Tutor Application

## by Alex Pilon  

The application will allow students looking for tutoring help to find  
qualified tutors to help them with their studies. Students will be able to
book appointments with tutors. There will also be a rating system so that
each student can assign a rating to each tutor. *Each* tutor will have the 
following information available :

- General Information (name,degree,fun facts...etc)
- A rating (an average of all ratings given)
- Courses/subjects they are able to tutor
- Time slots when they are available to tutor
- Fees that they charge for their services

I'm interested in this project because a friend mentioned  
that in the future he was interested in making a company that could 
connect students with tutors. I agreed to help him, so this is a good way
to get some experience coding a similar type of project.

## User Stories

- As a user, I want to be able to add a tutor to the list of available tutors
- As a user, I want to be able to view a list of all tutors
- As a user, I want to be able to give a rating to a tutor
- As a user, I want to be able to narrow down the tutor list by searching based on faculty
- As a user, I want to be able to save all the tutors if I want 
- As a user, I want to be able to load all the tutors if I want

- As a user, I want to be able to narrow down the tutor list by searching based on rating
- As a user, I want to be able to book a meeting with a tutor
- As a user, I want to be able to create an account
- As a user, I want to be able to view more details about a chosen tutor

## Instructions For Grader
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by typing a name
  into the name text field and a faculty into the faculty text field beside it. finally click on the add button to add
  the tutor to the database.
- You can generate the second required action related to the user story "narrowing down the tutor list by faculty" 
  typing a faculty into the faculty text field next to the filter checkbox. Clicking the filter checkbox should filter
  the tutors.
- You can locate my visual component by using the filter checkbox correctly, this will make a cat appear in bot right
- You can save the state of my application by clicking the save button
- You can load the state of my application by clicking the load button
- you can also rate a tutor by typing in the tutor's name and a valid integer.

## Phase 4: Task 2
- Tutors were loaded
  Fri Dec 01 02:20:00 PST 2023
  New Tutor Matt has been added
  Fri Dec 01 02:20:00 PST 2023
  New Tutor Steven has been added
  Fri Dec 01 02:20:00 PST 2023
  New Tutor Paul has been added
  Fri Dec 01 02:20:00 PST 2023
  New Tutor Tyler has been added
  Fri Dec 01 02:20:08 PST 2023
  New Tutor Peter has been added
  Fri Dec 01 02:20:13 PST 2023
  Peter Has been rated 4
  Fri Dec 01 02:20:22 PST 2023
  Tutors have been filtered by : Arts
  Fri Dec 01 02:20:31 PST 2023
  New Tutor Dave has been added
  Fri Dec 01 02:20:34 PST 2023
  Tutors were saved

## Phase 4: Task 3
- I would refactor all the createXButton methods in Gui. This is because all the methods have repeated code
  with the only difference between them being the button text, button location and the command for the
  actionListener. By passing in a string, and a set of coordinates I could eliminate most of these duplicates
  and replace them with a simple CreateXButton method. I would also probably change the rating and faculty fields
  in Tutor. I would turn them into their own classes. This would allow these new classes to be responsible 
  for all actions that involve Rating and Faculty, instead of having to create methods inside TutorApp, Gui, etc.
  that deal with these fields in tutors. 
