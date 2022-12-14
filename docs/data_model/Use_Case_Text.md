# Use-Case Texts

## UC1: To Change Profile
Administrator requests users list. System returns users list. Administrator
changes profile of account. System notifies the change.

## UC2: To Inactive Account
Administrator requests users list. System returns users list. The System 
displays the User Account information currently stored for the User.
The Administrator enters a button that turns the User Account status to
“Inactive” and requests the System to save the information. The System validates
and notifies the Administrator that the status has been updated.







## UC1: Register Account
Administrator accesses System. Administrator enters required account data (name,
e-mail, phone number). System validates and saves entered account data. System 
automatically associates created account with "User" profile. System notifies 
that new account has been created successfully. 


## UC4: Create Profile
Administrator accesses System. Administrator enters required profile data (name, 
permissions). System validates and saves entered profile data. System notifies 
that new profile has been created successfully.


## UC5: Create Password
Account accesses System for the first time. System requests password creation. 
Account enters password. System checks if password is valid. System notifies 
that new password has been created successfully. 


## UC6: To Change Profile
Administrator requests users list. System returns users list. Administrator 
changes profile of account. System notifies the change.


## UC7: Edit Account
The Account accesses the System. The System displays the information currently 
stored. The Account enters the desired User Account information values and 
requests the System to save the entered values. The System validates the entered 
User Account information. The System notifies the Account that the account has 
been updated.


## UC8: Reset Password
The User accesses a form through a link in the Login Zone. The System displays 
the page where the User inserts their account e-mail and enters a button. The 
System sends a message to the User’s mailbox with a link. The User accesses the 
link that forward them to a page where they can enter the new password. The 
System validates the entered information. The System notifies the User that the 
password has been updated.


# UC9: User Management
The Administrator requests the access to the list of all users in the System. 
The System displays all user accounts registered in the System and their status.
The Administrator enters the e-mail or the profile of the User Account they are 
searching for. The System displays the matching User Account information.


## UC10: Activate Account
The Administrator accesses the System. The Administrator requests the access to 
the list of all users in the System and selects the desired User Account. The 
System displays the User Account information currently stored for the User. 
The Administrator enters a button that turns the User Account status to “Active” 
and requests the System to save the information. The System validates and 
notifies the Administrator that the status has been updated.





# UC12: Project creation
The Manager creates a project in the System entering information specific for 
this project. System should allow project registration.


## UC13: Project management
The User with the Project Manager role can modify part of the project 
information. System records the changes.


## UC14: Project Search
User searches for a project in the System. System verifies if the User is part 
of the project to show its content. Manager searches for project in the System 
and System returns a list of all projects.


## UC15: US registration
Product Owner registers in the System user stories related to a project. Product 
Owner add user stories to product backlog. System records product backlog.


## UC16: Sprint Backlog Definition
Project Team access product backlog to get user stories in the product backlog. 
System returns all user stories. Project Team defines user stories for the 
sprint backlog and estimates effort for each user stories. System records sprint 
backlog.


## UC17: Product backlog grooming
Product Owner requests access to product backlog. System returns the product 
backlog and breaks user story into multiple user stories. System replaces the 
old user story with new ones.


## UC18: Sprint review
Team registers in the system tasks finished during the sprint. System removes 
user stories done from the product backlog. System returns unfinished user 
stories to the product backlog. Product Owner requests access to the sprint 
finished user stories. System returns user stories. Product Owner may return a 
user story to the product backlog.


## UC19: Tasks for US
Team members create tasks related to US. System records tasks. During the sprint 
users update tasks. System records updated tasks.


## UC20: Task update
A user access information about a task. System grants access. User update 
information. System generates comments where attachment files can be added. User 
accesses the comment and completes it with necessary changes. System generates 
record.


## UC21: Project allocation
Manager access project. System grants access. Manager checks available 
resources. Manager assigns resources to project. System generate form with start 
date, end date, cost per hour and percentage of allocation. Manager fills form. 
System calculates allocation time for each resource in different projects.


## UC22: Project activity
User access project. System grants access. User views the status of activities 
of the project. User chooses type of viewing mode. System returns data in 
viewing mode selected.


## UC23: To Generate Allocation Report by Manager
Account requests allocation report of any member of any project. System 
generates allocation report.

## UC23: To Generate Allocation Report by Project Manager
Account requests allocation report of any member of projects he/she is involved in. System 
generates allocation report.

## UC23: To Generate Allocation Report by Product Owner
Account requests allocation report of projects he/she is involved in. System
generates allocation report.

## UC23: To Generate Allocation Report by Scrum Master
Account requests allocation report of projects he/she is involved in. System
generates allocation report.


## UC23: To Generate Allocation Report by Team Member
Account requests allocation report of projects he/she is involved in. System
generates allocation report.


## UC: To Generate KPI Report by Manager
Manager requests KPI report of any project. System provides report in XML file 
or on screen. Manager selects XML file or on screen. System reports CPI and SPI 
in chosen format. 

## UC: To Generate KPI Report by Project Manager
Project Manager requests KPI report of project he/she/they is involved. System 
provides report in XML file or on screen. Manager selects XML file or on screen. 
System reports CPI and SPI in chosen format. 

## UC: To Generate KPI Report by Product Owner
Product Owner requests KPI report of project he/she/they is involved. System
provides report in XML file or on screen. Manager selects XML file or on screen.
System reports CPI and SPI in chosen format. 