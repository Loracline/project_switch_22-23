# Use-Case Texts

## UC1: To create new profile
The administrator creates new profile. System returns data (name, permissions). Administrator enters profile data. System notifies that profile is created.

## UC2: To register new account
The administrator creates new account. System returns data (name, email, phone number and photo). Administrator enters account data. System notifies that account is created.

## UC3: To change profile
The administrator changes account profile. System requests data (e-mail, desired profile). Administrator enters data. System notifies profile was updated.

## UC4: To list all accounts
The administrator request list of all accounts and status. System returns data.

## UC5: To search account by email
The Administrator searches account by email. System returns data.

## UC6: To search accounts by profile
The Administrator searches user accounts by profile user. System returns list of accounts.

## UC7: To activate user account
Administrator request account information. System returns account information. Administrator change the status to inactive. System notifies that status was updated.

## UC8: To inactive user account
Administrator request account information. System returns account information. Administrator change the status to inactive. System notifies that status was updated.

## UC9: To create project
The manager creates a new project. System returns data (name, description etc.). The manager enters project data. The system notifies that the project was created.

## UC10: To manage project
The project manager edit project information. The system notifies that  the project information is updated.

## UC11: To search project by manager
Manager searches project by code, name etc. System returns a list of all projects.

## UC12: To search project by user
User searches project by code, name etc. System returns a list of all projects where user is/was enrolled.

## UC13: To create US
Product Owner create new US. System returns data (US number, actor, priority in product backlog, etc). Product owner enters US data. System notifies that new US is created and added to product backlog.

## UC14: To create sprint
Team request access to product backlog data. System returns all user stories. Team defines user stories for the sprint. System notifies that sprint is created.

## UC15: To groom US
Product Owner request access to product backlog data. System returns user stories. Product Owner decompose user story. System notifies that the user story was replaced.

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