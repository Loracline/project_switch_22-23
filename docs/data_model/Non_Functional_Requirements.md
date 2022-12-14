**Revision History**


| Version  | Date         | Description                 | Author |
|----------|--------------|-----------------------------|-------|
| v.01     | Dec 07, 2022 | First draft. To be refined. | João Serra, Cristiana Moreira, Bárbara Oliveira, Margarida Ferreira, Sofia Oliveira |
| v.02     | Dec 12, 2022 | Update and refine.          | Caroline Dantas, Jussara Oliveira |




**Introduction**

This document lists the non-functional requirements of the software management product.

**Functionality**

Logging

All user operations, as well as system messages, either warning or error, must be saved in log files on the server, identified with at least the following levels: debug, info, warn, error.

Saved passwords

Passwords should not be openly saved to the database allowing them to be read or decrypted by a user with access to the database.

Language and location

All text, as well as date formats and decimal formats, must be configured so that you can have more than one language in the application and changed in runtime.

**Usability**

Responsive Layout

The development of the user interface should be responsive, that is, adapt the design of the forms to the type of device that is used by the user. For different devices, with different screen sizes, such as a computer and a mobile phone, the layout should adapt and display the information in a different layout to maximize the usability and user experience of the application.

**Reliability**

*Not defined*

**Performance**

Session timeout

If there is a period of inactivity of more than 30 minutes, the session should be automatically

**Supportability**

*Not defined*

**Implementation**

*Not defined*

**Interface**

*Not defined*

**Operations**

*Not defined*

**Packaging**

*Not defined*

**Legal**

*Not defined*
