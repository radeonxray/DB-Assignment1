# DB-Assignment1
Assignment 1 - Simple DB with Hashmap-based Index

- Build a simple_db in the programming language of your choice.
- Implement a Hashmap-based index.
- Implement functionality to store your data on disk in a binary file.
- Implement functionality to read your data from disk again, so that you can reinstantiate your database after a shutdown.

Hint: You do not want to serialize and deserialize the an in memory Hashmap containing all data directly. Instead, you in memory index based on a Hashmap contains information on where in you database file a piece of information is stored.

-----

Project created using IntelliJ and Java SDK 10.

Upon openning and setting up the project, if the project won't work out of the box (using Intellij), try the following:
- Set the "Project compiler output" to the projects "out"-folder, through the File -> Project Structure -> Project Settings -> Project
- Set the "src"-folder as the Source folder, through the File -> Project Structure -> Project Settings -> Modules

Project should now be setup to compile and run correctly.
Right-click in the "HashMapping.java"-file and the "HashMapping.main()" should now be launchable. 

