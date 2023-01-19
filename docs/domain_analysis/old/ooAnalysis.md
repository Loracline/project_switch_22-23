# OO Analysis Document

| **Version** | **Date**     | **Description**                                              | **Author** |
|-------------|--------------|--------------------------------------------------------------|------------|
| v.01        | Dec 14, 2022 | Document supporting the construction of the Domain Model v0. | DevTeam    |


## Identifying potential conceptual classes
**Candidate Conceptual Classes**

| **Class Type**                                                    | **Candidate Classes**                                                                            |
|-------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|
| (Business) Transactions                                           |                                                                                                  |
| Transaction line items                                            |                                                                                                  |
| Products or services related to a transaction or transaction line | Project, Task                                                                                    |
| Transaction records/registers                                     | KPI Report, Report                                                                               |
| Roles of people or organizations                                  | Account, User, Administrator, Profile, Manager, Role, Project Manager, Product Owner,Team Member |
| Places                                                            | System                                                                                           |
| Noteworthy events                                                 |                                                                                                  |
| Physical objects                                                  |                                                                                                  |
| Description of things                                             | Profile, User Story                                                                              |
| Catalogs                                                          |                                                                                                  |                                                                                                                                                       
| Containers of things                                              | Product Backlog, Sprint Backlog, Sprint                                                          |
| Elements of containers                                            | Project, Account, User Story, Sprint                                                             |
| (Other) Organizations                                             |                                                                                                  |
| Other (external) systems                                          | Legacy Platform                                                                                  |
| Records of finance, work, contracts, legal matters                |                                                                                                  |
| Financial instruments                                             |                                                                                                  |
| Documents mentioned/used to perform some work                     |                                                                                                  |


## Identifying attributes from potential conceptual classes
**Candidate Attributes of Conceptual Classes**

| **Entity**      | **Attribute**                                                                                                                                                                   |
|-----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Account         | name, photo (optional), password, email, status, profile                                                                                                                        |
| Administrator   | accountCode                                                                                                                                                                     |
| Manager         | accountCode                                                                                                                                                                     |
| Product Backlog | usCode                                                                                                                                                                          |
| Product Owner   | accountCode                                                                                                                                                                     |
| Profile         | name, permissions                                                                                                                                                               |
| Project         | code, projectManager, name, description, startDate, sprintDuration, numberOfSprints, endDate, customer, typology, productOwner, scrumMaster, projectTeam, projectStatus, budget |
| Role            | code, description                                                                                                                                                               |
| Sprint          | code, startDate, endDate                                                                                                                                                        |
| Sprint Backlog  | code, usCode                                                                                                                                                                    |                                                                                                                                                       
| Task            | name, description, userStory, startDate, endDate, hoursSpent, effortEstimate, percentageOfExecution, type, status, responsible                                                  |
| User            | accountCode                                                                                                                                                                     |
| User Story      | code, actor, usText, acceptanceCriteria                                                                                                                                         |


## Identifying associations between potential conceptual classes
**Possible Associations Between Conceptual Classes**

| Concept A       | Association                             | Concept B             |
|-----------------|-----------------------------------------|-----------------------|
| Account         | signs in/accesses                       | System                |
| Account         | has                                     | Profile               |
| Account         | is created by                           | Administrator         |
| Account         | enters                                  | Credentials           |
| Account         | can search for                          | Projects              |
| Administrator   | associates profile to                   | Account               |
| Administrator   | accesses                                | Account Information   |
| Administrator   | registers (through a form)              | Account               |
| Administrator   | activate/disable                        | Account               |
| Administrator   | edits                                   | Account Information   |
| Administrator   | is an                                   | Account               |
| Administrator   | list                                    | Account               |
| Administrator   | searches available                      | Account               |
| Comment         | recorded by                             | System                |
| Effort          | is estimated in each                    | Sprint                |
| Effort          | is reported in each                     | Task                  |
| Legacy Platform | saves project from                      | System                |
| Manager         | is an                                   | Account               |
| Manager         | get KPI report from                     | System                |
| Manager         | checks                                  | User (Resources)      |
| Manager         | edits (add User)                        | Project               |
| Manager         | is                                      | Profile               |
| Manager         | allows user access                      | Project               |
| Manager         | defines                                 | Role                  |
| Manager         | associates                              | Resources             |
| Manager         | creates/edits                           | Project Information   |
| Manager         | has access to all                       | Projects              |
| Manager         | has access to all allocated             | User                  |
| Product Owner   | access                                  | US                    |
| Product Owner   | returns                                 | US                    |
| Product Owner   | can reject implementation               | US                    |
| Product Owner   | is an                                   | User Role             |
| Product Owner   | creates                                 | Task                  |
| Product Owner   | decompose several US into more detailed | US                    |
| Profile         | is created by                           | Administrator         |
| Profile         | has                                     | Permissions           |
| Profile         | is divided into                         | Administrator         |
| Profile         | is divided into                         | User                  |
| Profile         | is divided into                         | Manager               |
| Project         | has                                     | Information           |
| Project Backlog | has a set of all                        | US                    |
| Project Manager | get KPI report from                     | System                |
| Project Manager | get KPI report from                     | System                |
| Project Manager | is an                                   | User Role             |
| Project Team    | is a part of                            | Project               |
| Project Team    | registers completed                     | Task                  |
| Role            | participates                            | Sprint                |
| Scrum Master    | is an                                   | User Role             |
| Scrum Master    | creates                                 | Task                  |
| Sprint Backlog  | during sprint has a set of              | US                    |
| Sprint Planning | defines effort estimate for each        | US                    |
| System          | records minutes from ceremonies in      | System                |
| System          | generates KPI reports to                | Manager               |
| System          | allows                                  | Profile Configuration |
| System          | creates                                 | Session               |
| System          | has                                     | Administration Area   |
| System          | allows                                  | Project Registration  |
| System          | allows                                  | Project Search        |
| System          | manages resources to                    | Projects              |
| Task            | updated by                              | System                |
| Team            | creates                                 | Task                  |
| Team Member     | is an                                   | User Role             |
| Team Member     | creates                                 | Task                  |
| US              | returns to                              | Product Backlog       |
| US              | removed by                              | System                |
| US              | is returned by                          | System                |
| US              | is recorded by                          | System                |
| US              | is created by                           | Product Owner         |
| US              | results in several                      | Task                  |
| User            | updates                                 | Task                  |
| User            | adds                                    | Comment               |
| User            | views status of the                     | Project               |
| User            | has allocation time calculated by       | System                |
| User            | get personal report from                | System                |
| User            | has                                     | Roles                 |
| User            | logs out                                | Session               |
| User            | edits                                   | Profile Data          |
| User            | creates                                 | Password              |
| User            | resets / changes                        | Password              |
| User            | has access to his own                   | Information           |