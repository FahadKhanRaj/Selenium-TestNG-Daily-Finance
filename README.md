# 🔐 DailyFinance QA Automation Project

This project automates and verifies key user workflows for [DailyFinance](https://dailyfinance.roadtocareer.net/) using Selenium WebDriver, TestNG, JavaFaker, and Allure reports.

---

## 🚀 Features Tested

### ✅ 1. **User Registration**
- Navigate to the [DailyFinance site](https://dailyfinance.roadtocareer.net/)
- Register a new user with a unique Gmail (`gmailuser+randomdigit@gmail.com`)
- ✅ Assert: A **Congratulations** email is received via Gmail using **Rest Assured** . 

---

### 🚀 2. **Password Reset - Negative Tests**
- Click on the **Reset Password** link
- Perform **two negative test cases**, such as:
  - Submitting an empty email field
  - Entering a non-registered email
- ✅ Assert: Proper error messages are displayed for both cases

---

### 🚀 3. **Password Reset - Positive Test**
- Enter the **valid Gmail** account used during registration
- Click **Send Reset Link**
- ✅ Assert: Reset email is sent to your inbox

---

### 🚀 4. **Retrieve Email using GMail OAUTH 2.0 & Set New Password**
- Open the **Gmail inbox** using OAUTH 2.0
- Retrieve the **password reset email**
- Get the link to reset the password
- Follow the link and set a **new password**

---

### 🚀 5. **Login with New Password**
- Use the **updated credentials** to login
- ✅ Assert: Login is successful with the new password

---

### 🚀 6. **Add Items**
- Add **2 financial items**:
  1. One with **all fields filled**
  2. One with **only mandatory fields**
- ✅ Assert: Both items are visible in the item list

---

### 🚀 7. **Update Email**
- Navigate to the **User Profile**
- Update the user’s email to a **new Gmail address**

---

### 🚀 8. **Login with Updated Email**
- Log out
- Try logging in with the:
  - ✅ **New email** → Login should succeed
  - ❌ **Old email** → Login should fail
- Assert with assertion.

---

### 🚀 9. **Admin Login**
- Logout and login using the **Admin credentials**
- ✅ Admin credentials must be securely passed.

---

### 🔍 10. **Search Updated Email**
- Search the **updated Gmail** in the **Admin Dashboard**
- ✅ Assert: Updated email appears in the user list

---

### 📄 11. **Bulk Registration from CSV**
- Read user data from a CSV file (3 new users)
- Register each user
- ✅ Assert: All users are successfully registered

---

## 🚀 Tech Stack

- Java
- TestNG
- Selenium WebDriver
- JavaFaker
- JSON.simple
- Allure Reports

---

## 🚀 Reporting

Run the following commands to generate and view the Allure report:

```bash
allure generate allure-results --clean -o allure-report
allure open allure-report
```

## 🚀 Video Demonstration

Watch the full test run in action:

🎥 [Click to Watch](https://drive.google.com/file/d/1pId_U8nY1DVs3UHNUGCB86vTUr6z9kOf/view?usp=sharing)

## 🚀 Allure Report Screenshots

![testNG SS1](https://github.com/user-attachments/assets/67e9a008-188b-4691-a6fa-2de5285b055a)

![testNG SS3](https://github.com/user-attachments/assets/41e67051-b71e-4c0a-8621-9f19ff1df198)


