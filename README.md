# SauceDemo Automation Test

This project automates the following workflow on the SauceDemo website:

1. Login to https://www.saucedemo.com/ with username: `standard_user` and password: `secret_sauce`
2. Print the description and price of the first product
3. Click on the menu on the left side of the page and log out

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## Running the Test

### Option 1: Using Maven
```bash
mvn test
```

### Option 2: Using TestNG directly
```bash
mvn exec:java -Dexec.mainClass="com.bookstore.TestRunner"
```

### Option 3: Using the TestRunner class
```bash
mvn compile exec:java -Dexec.mainClass="com.bookstore.TestRunner"
```

## Configuration

The test behavior can be configured in `src/main/resources/config.properties`:

- `headless=false` - Set to `true` to run browser in headless mode
- `slowMo=1000` - Delay between actions in milliseconds
- `baseUrl=https://www.saucedemo.com` - Base URL for the application

## Test Output

The test will output:
- Login confirmation
- First product description and price
- Logout confirmation

## Project Structure

- `src/test/java/com/bookstore/SauceDemoTest.java` - Main test class
- `src/main/java/com/bookstore/TestRunner.java` - Test runner utility
- `testng.xml` - TestNG configuration
- `src/main/resources/config.properties` - Test configuration



