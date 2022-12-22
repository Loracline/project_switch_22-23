# Use-Case Texts

### Revision History

| Version | Date     | Description                   | Author                           |
|---------|----------|-------------------------------|----------------------------------|
| v.01    | 14/12/22 | Identification of UC 001-015. | Cristiana Moreira, Rui Pinho     |
| v.02    | 15/12/22 | Identification of UC 016-028. | Bárbara Oliveira, João Magalhães |
| v.03    | 22/12/22 | Use-case grooming.            | Bárbara Oliveira                 |


### Introduction

This document lists the use-case texts identified in the *Vision Document* of the software management product.

## [UC001] To create new profile
Administrator creates new profile. System requests data. Administrator enters profile data. System notifies that profile is created.

## [UC002] To register new account
Administrator creates new account. System requests data (name, e-mail, phone number). Administrator enters account data. System notifies that account is created.

## [UC003] To change profile
Administrator changes account profile. System requests data (e-mail, desired profile). Administrator enters data. System notifies profile was updated.

## [UC004] To list all accounts
Administrator request list of all accounts and status. System returns data.

## [UC005] To search account by e-mail
Administrator searches account by e-mail. System returns account matching the given e-mail.

## [UC006] To search account by profile
Administrator searches account by profile. System returns list of accounts with desired profile.

## [UC007] To activate user account
Administrator request account information. System returns account information. Administrator change the status to *Active*. System notifies that status was updated.

## [UC008] To inactive user account
Administrator request account information. System returns account information. Administrator change the status to *Inactive*. System notifies that status was updated.

## [UC009] To create project
Manager creates new project. System requests data (name, description, etc.). Manager enters project data. System notifies that project was created.

## [UC010] To manage project
Project Manager (PM) edits project information. System notifies that project information is updated.

## [UC011] To search project by manager
Manager searches project by code, name, etc. System returns list of all projects matching the criteria.

## [UC012] To search project by user
User searches project by code, name, etc. System returns a list of all projects where user is/was enrolled.

## [UC013] To create user story (US)
Product Owner (PO) creates new US. System requests data (US number, actor, priority in product backlog, etc.). PO enters US data. System notifies that new US is created and added to product backlog.

## [UC014] To create sprint
User in project requests access to product backlog data. System returns all US. User defines US for the sprint. System notifies that sprint is created.

## [UC015] To groom US
PO requests access to product backlog data. System returns US. PO decomposes US. System notifies that US was replaced.

## [UC016] To review sprint
PO requests access to sprint's finished US. System returns US. PO returns US to product backlog if needed. System notifies that US remains in product backlog.

## [UC017] To create task for US
User in project creates task related to US. System requests task data. User enters task information. System notifies that new task is created for given US. 

## [UC018] To update task in US
User in project updates information about task. System requests information (update description). User enters data. System notifies that task is updated. 

## [UC019] To finish task in US
User in project registers tasks finished during sprint. System notifies that task is finished and returns US with unfinished tasks to product backlog.

## [UC020] To allocate resource in project
Manager assigns resource to project. System requests data (starting date, ending date, cost per hour and percentage of allocation). Manager enters data. System notifies that resource is added to project.

## [UC021] To choose project activity's viewing mode
User in project chooses type of viewing mode. System returns data in viewing mode selected.

## [UC022] To generate allocation report by Manager
Manager requests allocation report of any member of any project. System generates allocation report.

## [UC023] To generate allocation report by PM, PO or Scrum Master (SM)
PM, PO or SM requests allocation report of any member of projects he/she/they are involved in. System generates allocation report.

## [UC024] To generate allocation report by TM
TM requests personal allocation report of projects he/she/they are involved in. System generates allocation report.

## [UC025] To generate KPI report by Manager
Manager requests KPI report of any project. System asks to choose between XML file or screen. Manager selects option. System reports CPI and SPI in chosen format. 

## [UC026] To generate KPI report by PM or PO
PM or PO requests KPI report of project he/she/they are involved in. System asks to choose between XML file or screen. PM selects option. System reports CPI and SPI in chosen format.

## [UC027] To keep record of Scrum ceremony 
User in projects starts time counter when ceremony begins. System starts recording. User finishes time counter when ceremony ends. System stops recording and keeps record. 

## [UC028] To import project created in legacy platform
Manager imports project created in legacy platform. System checks if project is not violating current business rules. Manager confirms the import. System notifies project is successfully imported. 