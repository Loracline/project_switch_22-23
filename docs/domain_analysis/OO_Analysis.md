## Use Case


| **Candidate Conceptual Classes**                                  || 
|:------------------------------------------------------------------|:--------------------|
| **Category**                                                      | **Candidate Classes** |
| (Business) Transactions                                           ||
| Transaction line items                                            ||
| Products or services related to a transaction or transaction line | Project, Task|
| Transaction records/registers                                     | KPI report, Report |
| Roles of people or organizations                                  | Account, User, Administrator, Profile, Manager, Role, Project Manager, Produc Owner, Team|
| Places                                                            | System|
| Noteworthy events                                                 ||
| Physical objects                                                  ||
| Description of things                                             | Profile, User Story|
| Catalogs                                                          ||
| Containers of things                                              | Product backlog, Sprint backlog, sprint|
| Elements of containers                                            | Project, Account, User story, Sprint|
| (Other) organizations                                             ||
| Other (external) systems                                          |Legacy platform|
| Records of finance, work, contracts, legal matters                ||
| Financial instruments                                             ||
| Documents mentioned/used to perform some work                     ||






# OO Analysis

|**Version**| **Date**     | **Description**             |**Author**|
|-----------|--------------|-----------------------------|----------|
|v.01 (elaboration and specification?)| Dec 14, 2022 | First draft. To be revised. |DevTeam|

## Attributes from potential classes

| **Entity**     | **Attribute**                                                                                                                                                       |
|----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Account        | name, photo (optional), password, email, status, profile                                                                                                            |
| Administrator  | accountCode                                                                                                                                                         |
| Manager        | accountCode                                                                                                                                                         |
| Product backlog | UScode                                                                                                                                                              |
| Product Owner  | accountCode                                                                                                                                                         |
| Profile        | name, permissions                                                                                                                                                   |
| Project        | Code, Project manager, Name, Description, Start date, Sprint Duration, Number of sprints, End date, Customer, Typology, PO, SM, Project team, Porject status, Budget |
| Role           | code, description                                                                                                                                                   |
| Sprint         | code, start date, end date                                                                                                                                          |
| Sprint backlog | code, UScode                                                                                                                                                        |                                                                                                                                                       
| Task           | Name, Description, US, Start date, end date, Hours spent, Effort estimate, Percentage of execution, type, status, Responsible.                                      |
| User           | accountCode                                                                                                                                                         |
| User Story     | code, Actor, US text, Acceptance criterias                                                                                                                          |

| Concept A       | Association                              | Concept B              |
|-----------------|------------------------------------------|------------------------|
| Account         | signs in/accesses                        | System                 |
| Account         | has                                      | Profile                |
| Account         | is created by                            | Administrator          |
| Account         | enters                                   | Credentials            |
| Account         | can search for                           | Projects               |
| Administrator   | associates profile to                    | Account                |
| Administrator   | accessess                                | Account information    |
| Administrator   | registers (trough a form)                | Account                |
| Administrator   | activate/disable                         | Account                |
| Administrator   | edits                                    | Account information    |
| Adminstrator    | is an                                    | Account                |
| Adminstrator    | list                                     | Account                |
| Adminstrator    | seacrchs available                       | Account                |
| Comment         | recorded by                              | System                 |
| Effort          | is estimated in each                     | Sprint                 |
| Effort          | is reported in each                      | Task                   |
| Legacy platform | saves project from                       | System                 |
| Manager         | is an                                    | Account                |
| Manager         | get KPI report from                      | System                 |
| Manager         | checks                                   | User(resources)        |
| Manager         | edits (add User)                         | Project                |
| Manager         | is                                       | Profile                |
| Manager         | allows user acess                        | Project                |
| Manager         | defines                                  | Role                   |
| Manager         | associates                               | Resources              |
| Manager         | creates/edits                            | Project information    |
| Manager         | has access to all                        | Projects               |
| Manager         | has access to all allocated              | User                   |
| Product Owner   | access                                   | US                     |
| Product Owner   | returns                                  | US                     |
| Product Owner   | can reject implementation                | US                     |
| Product Owner   | is an                                    | User Role              |
| Product Owner   | creates                                  | Task                   |
| Product Owner   | decompose severall US into more detailed | US                     |
| Profile         | is created by                            | Administrator          |
| Profile         | has                                      | Permissions            |
| Profile         | is divided into                          | Administrator          |
| Profile         | is divided into                          | User                   |
| Profile         | is divided into                          | Manager                |
| Project         | has                                      | Information            |
| Project backlog | has a set of all                         | US                     |
| Project Manager | get KPI report from                      | System                 |
| Project Manager | get KPI report from                      | System                 |
| Project Manager | is an                                    | User Role              |
| Project Team    | is a part of                             | Project                |
| Project Team    | registers completed                      | Task                   |
| Role            | participates                             | Sprint                 |
| Scrum Master    | is an                                    | User Role              |
| Scrum Master    | creates                                  | Task                   |
| Sprint backlog  | during sprint has a set of               | US                     |
| Sprint planning | defines effort estimate for each         | US                     |
| System          | records minutes from cerimonies in       | System                 |
| System          | generates KPI reports to                 | Manager                |
| System          | allows                                   | Profile  configuration |
| System          | creates                                  | Session                |
| System          | has                                      | Administration Area    |
| System          | allows                                   | Project Registration   |
| System          | allows                                   | Project Search         |
| System          | manages resources to                     | Projects               |
| Task            | updated by                               | System                 |
| Team            | creates                                  | Task                   |
| Team Member     | is an                                    | User Role              |
| Team Member     | creates                                  | Task                   |
| US              | returns to                               | Product Backlog        |
| US              | removed by                               | System                 |
| US              | is returned by                           | System                 |
| US              | is recorded by                           | System                 |
| US              | is created by                            | Product Owner          |
| US              | results in severall                      | Task                   |
| User            | updates                                  | Task                   |
| User            | adds                                     | Comment                |
| User            | views status of the                      | Project                |
| User            | has allocation time calculated by        | System                 |
| User            | get personal report from                 | System                 |
| User            | has                                      | Roles                  |
| User            | logs out                                 | Session                |
| User            | edits                                    | Profile data           |
| User            | creates                                  | Password               |
| User            | resets/changes                           | Password               |
| User            | has access to his own                    | Information            |