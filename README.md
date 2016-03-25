---
#ASSIGNMENT 3: Testing the Adventure Game
###Unit testing with Black Box techniques. Unit testing with White Box techniques.
- DUE Wednesday 9 March 2016, 11:55 PM

---
##TEAM 'GitSum':
- Colt Darien
- Tyler Hall
- Brad Wirtz

---
##DESCRIPTION:

#### Playing the Adventure Game, you (the player) visit a series of interconnected underground rooms in which treasure is hidden. You can move from room to room searching for treasure. The objective is to bring the treasure out of the cave. Some rooms have doors that are locked. To open locked doors, you must find the key to locked doors, which are placed somewhere in the labyrinth. As you go from room to room, you can
- look at the room,
- go into an adjacent room or through an adjacent door,
- pick up an object in the room, or
- drop an object that you are carrying.

####Besides the functionality outlined in the game description, you must develop tests for the following list of classes and methods:
- class Door: enter
- class Player: go, pickUp, drop
- class Room: addItem, removeItem, enter, exit

---
##HOW TO: COMPILE
	- javac -d . *.java			(Compiles all files into new directory)
##HOW TO: TEST
	- java cs314_A3Test.AllTests		(Runs all Junit tests)
##HOW TO: RUN
	- java cs314_A3.AdventureGame       	(Command Line (CMD) Interface)
	- java cs314_A3.AdventureGameView       (Graphical (GUI) Interface)

---
##PART 1: Team Portion of Grade: Overview.txt/test reports (45pts)

--[  ] Names of all team members: 2 pts

--[  ] Specific contributions by each team member: 2 pts

--[  ] Time spent by each team member: 2 pts

--[  ] The CSU Honor Pledge: 2 pts "I have not given, received, or used any unauthorized assistance." with the team members' names listed below.

--[  ] Test information: 37 pts

	-[  ] BB results for Split User Story A:

		-[  ] Number of BB tests

		-[  ] Coverage metrics: % class, % method, % line, % branch

		-[  ] Number defects found and number of defects fixed

		-[  ] List of all of defects found:

			-[  ] For each defect, identify and describe the test (include some way to identify the test in the JUnit test class comments at the beginning of the class file and use these identities in the overview file), its inputs, and the expected and actual behavior.

	-[  ] WB results for Split User Story B:

		-[  ] Number of WB tests

		-[  ] Coverage metrics: % class, % method, % line, % branch

		-[  ] Number of defects found by WB tests and number of defects fixed

		-[  ] List of all of defects found:

			-[  ] For each defect, identify and describe the test as for BB tests, its inputs, and the expected and actual behavior. 

	-[  ] If relevant, explain why you could not achieve 100% branch coverage.


---
##PART 2: Team Portion of Grade: Testing Quality & Completeness --- Finding the program failures (20pts)

--[  ] Tests must run: 10 pts

	-[  ] tests on class Room: enter, exit, addItem, removeItem

	-[  ] tests on class Player: go, pickUp, drop

	-[  ] tests on class Door: enter

	-[  ] AllTests.java

--[  ] Defects found and fixed: 10 pts

	-[  ] as documented in the overview.txt file

	-[  ] JUnit files are documented according to Split User Story A, Acceptance Criteria (AC) 3 and Split User Story B, AC 2. 


---
##PART 3: Team Portion of Grade: Completeness of submission (15pts)

--[  ] Jar file: 10 pts.

	-[  ] The jar file must have separate packages for the game source code and for tests. Source code must be in a package called cs314_A3 and test source code must be in a package called cs314_A3Test.

	-[  ] On extracting from the jar file, apart from the .project file, there must be 2 directories, src and UserStories. The src directory must contain the overview.txt file, and 2 packages: cs314_A3 and cs314_A3Test. All source code for the game must be in cs314_A3 and all test code for the game must be in cs314_A3Test. The UserStories directory must contain all the user stories for A3, each in a separate text file. Split story A from the A3 assignment page must be documented in a file called A3-US-1A.txt and split story B in a file called A3-US1B.txt. Any additional split stories and all tasks must be documented in the same file as the split user story from which they were derived.

	-[  ] No class files or other unneeded files are in your jar file.

--[  ] Compile/Run:

	-[  ] Files should compile successfully and without warnings.

	-[  ] Run: Your program should start without warnings and take user input without crashing. AllTests.java must run all of your JUnit tests, and they all must pass.

--[  ] User story files: 5 pts.

	-[  ] Each user story file must contain the user story, acceptance criteria, any split stories, story point estimates, and all tasks needed to complete the user story, along with their estimated times and actual time taken.


---
##PART 4: Individual Portion of Grade: Development Process (20pts)

--[  ] GitHub logs show at least 1 branch for each student on the team: 7 pts.

	-[  ] Branch name is descriptive of the changes made, including a way to tell which student is working on it.

	-[  ] Branch commits occur often, with a small amount of changes in each commit.

	-[  ] Branch commit logs are descriptive of the changes made, and include the User Story and task to which they refer.

	-[  ] Multiple pushes have been made to each student branch.

	-[  ] No work is done directly on the master branch.

--[  ] GitHub logs show the student has requested reviews of their proposed changes to the master: 5 pts.

--[  ] GitHub logs show the student has reviewed their teammates proposed changes to the master: 5 pts.

--[  ] GitHub logs show that all student branches have been merged into the master: 3 pts.
