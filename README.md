# Email Validator

A lightweight Java email validation library that validates an email address using **syntax checks** and **DNS MX record lookup**.

The library checks whether an email has a valid basic structure and whether its domain has an **MX (Mail Exchange) DNS record**, which indicates that the domain is configured to receive email.

## Features

* Validate basic email syntax
* Detect invalid special characters
* Detect consecutive `@` characters
* Detect consecutive dots (`..`)
* Detect invalid `.@` and `@.` patterns
* Verify that the email contains `@`
* Extract the email domain
* Perform DNS **MX record lookup**
* Can be distributed and reused as a `.jar` dependency
* No external Java library is required

---

## How It Works

The validator performs two main types of validation:

### 1. Syntax Validation

The email is checked for invalid patterns such as:

```text
user@@gmail.com
user..name@gmail.com
user.@gmail.com
user@gmail..com
user@.gmail.com
user!name@gmail.com
```

Only the following characters are allowed by the current implementation:

```text
A-Z
a-z
0-9
@
.
```

### 2. MX Record Validation

After the syntax check passes, the domain is extracted.

For example:

```text
user@gmail.com
```

The domain is:

```text
gmail.com
```

The library then performs a DNS MX lookup:

```text
gmail.com → MX record
```

If an MX record exists, the validator returns `true`.

---

# Installation

## Option 1 — Use the JAR File

Download the JAR file from the repository's **Releases** section or from the repository itself.

Add the JAR to your Java project's classpath.

### IntelliJ IDEA

1. Open your project.
2. Go to:

```text
File → Project Structure
```

3. Select:

```text
Libraries
```

4. Click:

```text
+
```

5. Select the downloaded `.jar` file.
6. Apply the changes.

You can now import the validator:

```java
import com.Ankit.email_validator;
```

---

# Usage

Example:

```java
import com.Ankit.email_validator;

public class Main {

    public static void main(String[] args) {

        String email = "example@gmail.com";

        boolean result = email_validator.emailValidator(email);

        System.out.println(result);
    }
}
```

Output:

```text
true
```

---

# Examples

### Valid Email

```java
email_validator.emailValidator("example@gmail.com");
```

Returns:

```text
true
```

### Invalid Special Character

```java
email_validator.emailValidator("example!@gmail.com");
```

Returns:

```text
false
```

### Double `@`

```java
email_validator.emailValidator("example@@gmail.com");
```

Returns:

```text
false
```

### Consecutive Dots

```java
email_validator.emailValidator("example..test@gmail.com");
```

Returns:

```text
false
```

### Invalid `.@`

```java
email_validator.emailValidator("example.@gmail.com");
```

Returns:

```text
false
```

### Invalid `@.`

```java
email_validator.emailValidator("example@gmail.com.");
```

Returns:

```text
false
```

---

# API

## `emailValidator(String email)`

Validates the supplied email address.

### Parameters

| Parameter | Type     | Description               |
| --------- | -------- | ------------------------- |
| `email`   | `String` | Email address to validate |

### Returns

```java
boolean
```

Returns:

* `true` → email passes syntax validation and the domain has an MX record
* `false` → email fails validation or MX lookup fails

### Example

```java
boolean valid = email_validator.emailValidator("user@gmail.com");
```

---

## `MXLookup(String domain)`

Performs an MX DNS lookup for the supplied domain.

### Parameters

```java
String domain
```

### Returns

```java
boolean
```

Returns `true` if an MX record exists.

### Example

```java
boolean exists = email_validator.MXLookup("gmail.com");
```

---

# Project Structure

```text
Email-Validator/
│
├── src/
│   └── com/
│       └── Ankit/
│           └── email_validator.java
│
├── email-validator.jar
│
└── README.md
```

---


# Important Note

This library performs **basic email validation** and **domain MX validation**.

An MX record only indicates that the domain has mail-exchange configuration. It **does not guarantee that the specific email address actually exists**.

For example:

```text
random-user@gmail.com
```

may pass the MX check because `gmail.com` has valid MX records, even if that specific mailbox does not exist.

Therefore, this library should be considered a **basic email/domain validator**, not a complete mailbox verification system.

---

# Requirements

* Java 8 or higher
* Internet/DNS access for MX record validation

The MX lookup requires network/DNS access.

---

# License

This project is available for educational and personal use.

You can modify and extend the project according to your requirements.

---

# Author

**Ankit Sirsatia**

GitHub:
`https://github.com/AnkitSirsatia`
