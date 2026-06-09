# Java LeetCode Solution

Java solutions for LeetCode-style problems, organized by topics.

## Project Layout

- `src/main/java/com/jasperwang/leetcode`: solution classes grouped by category, such as `ArrayQuestion`, `StringQuestion`, `DPQuestion`, `TreeQuestion`, and `ListQuestion`
- `src/test/java/com/jasperwang/leetcode`: JUnit 5 tests
- `src/main/resources`: supporting resources, including SQL examples
- `pom.xml`: Maven build configuration

## Requirements

- JDK 26 or newer
- Maven 3.9 or newer
- IntelliJ IDEA, optional but recommended for browsing and running individual tests

The project is configured with:

- Java release: `26`
- Test framework: JUnit 5
- Build tool: Maven

## Command Line Setup

From the repository root:

```powershell
java -version
mvn -version
```

If Java or Maven is installed but not on `PATH`, set them for the current PowerShell session:

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-26.0.1'
$env:PATH="$env:JAVA_HOME\bin;C:\Program Files\apache-maven-3.9.16\bin;$env:PATH"
```

Adjust the paths if your local JDK or Maven installation is in a different directory.

## Running Tests

Run the full test suite:

```powershell
mvn test
```

Run one test class:

```powershell
mvn -Dtest=StringTest test
```

Run one test method:

```powershell
mvn -Dtest=StringTest#testCipher test
```

Maven will download JUnit and build plugins on the first run, so the first test run requires internet access.

## IntelliJ Setup

1. Open the repository root in IntelliJ.
2. Import the project as a Maven project when prompted.
3. Set the Project SDK to JDK 26.
4. Confirm the Maven tool window can load `pom.xml`.
5. Run tests from `src/test/java/com/jasperwang/leetcode` using the gutter icons or the test class context menu.

If IntelliJ shows dependency errors after Maven changes, reload the Maven project from the Maven tool window. If needed, invalidate caches and reopen the project.

## Adding Solutions

Add new methods to the category class that best matches the problem domain:

- Array and matrix problems: `ArrayQuestion`
- String problems: `StringQuestion`
- Dynamic programming problems: `DPQuestion`
- Linked list problems: `ListQuestion`
- Tree and graph/tree traversal problems: `TreeQuestion`
- Math and bit manipulation problems: `MathQuestion`
- Backtracking problems: `BackTrackQuestion`
- Greedy problems: `GreedyQuestion`
- Design problems: `DesignQuestion`

Add or update tests under the matching package in `src/test/java/com/jasperwang/leetcode`.
