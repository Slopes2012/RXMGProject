# RXMGProject
Hello RXMG Team,
First of all, thank you for the opportunity and the challenge. It was a great opportunity for me to learn something new and get familiar with tools that you use in your company and also a great opportunity for me to show you my skill set using tools that are already familiar to me. 
I solved the task in two parts. 
1. Cucumber - selenium - jUnit - Java framework (Most test cases)
2. Cypress - JavaScript framerwork (1 test case)
Althought I did not use cypress to solve the entire challenge I wanted to show my interest by learning some basic steps to get up to speed. 

My observations for this challenge :
Given the requirements, I built 9 test scenarios. 8 test are executed successfully and there is one failure.
Test are written based on the general knowledge that I gained by using the application. The test have different point of assertions and use generated data to create the accounts.
Failure occurs when a user creates a post, this post is never displayed under the "Your Feed" section. 

Steps to reproduce:
Pre requisites:
If new user : User is sign up and logged in
If existing user: User is logged in
1. While in the Home page
2.Select "New Post"
3.Fill out the "new post" fields:
	A.Title
	B.About
	C.Article body
	D.Tags
4.Select Publish Article
5.Select Home
6.Validate you are in the "Your Feed" section (letters should be green and will be underlined)
Actual result:"Your feed" section shows "No articles are here... yet" 
Expected:Articles written by the user should be displayed in the "Your feed" section.

Another un expected behavior I noticed was that newly created users or existing users appear not to have the profile image working. Image appears as a "img" icon with the User's user name. 

Steps to reproduce:
Pre requisites:
If new user : User is sign up and logged in
If existing user: User is logged in
1. While in the Home page
Actual result: The top right corner shows an "Image" icon with the user's username instead of their picture. It appears as the user's username is displayed twice. 
Expected: If above mentioned behavior is not by design, it would be a more pleaset experience for the user to have an actual image or icon instead of their username displayed twice. 

Something different that I notice is that there is not "Sucess" message for when users creates accounts, post, or perform other actions like deleting posting as well. Even thought this does not break the application in any way and it is not a functional failure, it would be a more pleasant experience for the user to have validation for when actions happened succesfully. 

About the framework implementation, I built methods and test scenarios steps in a way that was dsynamic and help minimize code redundancy. 
I would be glad to answer questions if the team has any and I would appreciate any feedback. 
Looking forward to hear back from you,

Sandra.
