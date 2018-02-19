# email-spam-checker
Library for checking whether email is spam or not

## Prerequisites:
- Java JDK
- Apache Maven 

## Install
Because this project not pushed to any public maven repos, you should install it first locally
```bash
    mvn clean install
```

add this project as dependency of your project
```xml
    <dependency>
        <groupId>co.mailtarget.domainchecker</groupId>
        <artifactId>email-scam-checker</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
```
## Usage
### Java / Kotlin
Add this line of code 
```
    EmailSpamChecker.checkSpamAndReturnHashSet(str)
```
or
```
    EmailSpamChecker.isSpam(str)
```
