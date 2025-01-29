# Live Football World Cup Scoreboard Library

## Task requirements
You are working in a sports data company, and we would like you to develop a new Live Football
World Cup Scoreboard library that shows all the ongoing matches and their scores.
The scoreboard supports the following operations:
1. Start a new match, assuming initial score 0 – 0 and adding it the scoreboard.
   This should capture following parameters:
   a. Home team
   b. Away team
2. Update score. This should receive a pair of absolute scores: home team score and away
   team score.
3. Finish match currently in progress. This removes a match from the scoreboard.
4. Get a summary of matches in progress ordered by their total score. The matches with the
   same total score will be returned ordered by the most recently started match in the
   scoreboard.

### For example, if following matches are started in the specified order and their scores respectively updated:
- a. Mexico 0 - Canada 5
- b. Spain 10 - Brazil 2
- c. Germany 2 - France 2
- d. Uruguay 6 - Italy 6
- e. Argentina 3 - Australia 1

### The summary should be as follows:
1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2


### Questions
1) Is there a limit on the number of matches on the board min/max?
2) Can a match start with a score other than 0:0 and are there any limits on the score?
3) Can teams play on a neutral stadium, how should they be designated (which of them is home and which is away)?
4) What are the limits on adding matches? Is it possible to add a duplicate match (Germany - France/Germany - France) or its reverse version (Germany - France/France- Germany) to one scoreboard.
5) After the match starts, the game goes without breaks, i.e. always in progress until the end?
6) Are there any limits by the match time?

## System requirements
- Java 21 (if you have a different version installed, see Selecting Java Version)
- Gradle (the wrapper is included, so no separate installation is required)

## Configuration
Setting Active Version of Java
If you have a different version of Java installed, you can temporarily set the required version for this project:

- Linux / macOS:
   ```bash
   export JAVA_HOME=$(/usr/libexec/java_home -v 21)
   ```

Setting Java Version in `gradle.properties`
You can also specify the required Java version in gradle.properties to ensure Gradle uses the correct JDK:

- Linux / macOS:
   ```properties
   org.gradle.java.home=/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home
   ```

## Running the Project
### Build and Run

1. Make sure you have Java 21 installed.
2. Clone the repository.
3. Run the project using Gradle:
 ```bash
   ./gradlew run
 ```

### Running Tests

To execute all project tests, run: 
 ```bash
   ./gradlew test
 ```

## Logging

The project uses Log4j2. By default, logging is set to INFO level.

Example output:

```
2025-01-29 23:59:22 [main] DEBUG FootballScoreboardImpl - Match: Mexico 0 - Canada 5 score updated.

```