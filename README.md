# 📝 Blog Platform API

<p align="center">
  <img src="https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-4.0.4-brightgreen?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring%20Security-7.0-brightgreen?style=for-the-badge&logo=springsecurity&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/JWT-Auth-purple?style=for-the-badge&logo=jsonwebtokens&logoColor=white"/>
  <img src="https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven&logoColor=white"/>
</p>

<p align="center">
  A secure, production-ready <strong>RESTful Blog Platform API</strong> built with Spring Boot 4, Spring Security, JWT authentication, and MySQL.
</p>

---

## 📌 Table of Contents

- [Prerequisites](#-prerequisites)
- [Step 1 — Download the Project](#-step-1--download-the-project)
- [Step 2 — Open in IntelliJ IDEA](#-step-2--open-in-intellij-idea)
- [Step 3 — Setup MySQL Database](#-step-3--setup-mysql-database)
- [Step 4 — Configure the Project](#-step-4--configure-the-project)
- [Step 5 — Run the Server](#-step-5--run-the-server)
- [Step 6 — Admin Usage](#-step-6--admin-usage)
- [Step 7 — Normal User Usage](#-step-7--normal-user-usage)
- [API Endpoints Reference](#-api-endpoints-reference)
- [Push to GitHub](#-push-to-github)

---

## ✅ Prerequisites

Before you begin, make sure the following are installed on your machine:

| Tool | Download Link |
|------|--------------|
| Java JDK 17 or above | https://adoptium.net |
| IntelliJ IDEA (Community or Ultimate) | https://www.jetbrains.com/idea/download |
| MySQL 8.0 | https://dev.mysql.com/downloads/installer |
| MySQL Workbench (optional, recommended) | https://dev.mysql.com/downloads/workbench |
| Postman (for API testing) | https://www.postman.com/downloads |
| Git | https://git-scm.com/downloads |

---

## 📥 Step 1 — Download the Project

### Option A — Download as ZIP (No Git required)

1. Go to the GitHub repository page
2. Click the green **`<> Code`** button (top right of the file list)
3. Click **`Download ZIP`**
4. Once downloaded, **right-click** the ZIP file
5. Select **`Extract All`** (Windows) or double-click (Mac)
6. Choose a folder you can easily find, for example:
   ```
   C:\Users\YourName\Projects\blog-platform
   ```

### Option B — Clone with Git (Recommended)

Open your terminal (Command Prompt / Git Bash / Terminal) and run:

```bash
git clone https://github.com/your-username/blog-platform.git
cd blog-platform
```

---

## 💻 Step 2 — Open in IntelliJ IDEA

1. Open **IntelliJ IDEA**
2. On the welcome screen click **`Open`**
3. Navigate to the extracted/cloned folder (e.g. `blog-platform`) and click **`OK`**
4. IntelliJ will detect it as a **Maven project** — click **`Load Maven Project`** if prompted
5. Wait for IntelliJ to **index the project and download dependencies** (watch the bottom progress bar)

> ⚠️ This may take 2–5 minutes the first time as Maven downloads all libraries.

6. Once done, make sure the project structure looks like this in the left panel:

```
blog-platform/
├── src/
│   └── main/
│       ├── java/com/blog/blogplatform/
│       │   ├── controller/
│       │   ├── dto/
│       │   ├── entity/
│       │   ├── repository/
│       │   ├── security/
│       │   ├── service/
│       │   └── BlogPlatformApplication.java
│       └── resources/
│           └── application.properties
└── pom.xml
```

---

## 🗄 Step 3 — Setup MySQL Database

### Using MySQL Workbench

1. Open **MySQL Workbench**
2. Connect to your local MySQL server (usually `localhost:3306`)
3. Click the **SQL editor** tab
4. Run the following command:

```sql
CREATE DATABASE blog_db;
```

5. Click the **⚡ Execute** button or press `Ctrl + Enter`
6. You should see `blog_db` appear in the **Schemas** panel on the left

### Using Terminal / Command Prompt

```bash
mysql -u root -p
```

Enter your MySQL password, then run:

```sql
CREATE DATABASE blog_db;
SHOW DATABASES;
EXIT;
```

---

## ⚙️ Step 4 — Configure the Project

1. In IntelliJ, navigate to:
   ```
   src → main → resources → application.properties
   ```

2. Update the file with your MySQL credentials:

```properties
# Database connection
spring.datasource.url=jdbc:mysql://localhost:3306/blog_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD_HERE

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Server
server.port=8080
spring.application.name=blog-platform
```

> 🔑 Replace `YOUR_MYSQL_PASSWORD_HERE` with your actual MySQL password.
> If you have no password set, leave it blank: `spring.datasource.password=`

---

## ▶️ Step 5 — Run the Server

### In IntelliJ IDEA

1. In the left panel, navigate to:
   ```
   src → main → java → com → blog → blogplatform → BlogPlatformApplication.java
   ```
2. Right-click on `BlogPlatformApplication.java`
3. Click **`Run 'BlogPlatformApplication'`**

### OR using the Terminal inside IntelliJ

Press `Alt + F12` to open the IntelliJ terminal and run:

```bash
mvn spring-boot:run
```

### ✅ Server started successfully when you see this in the console:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \

 :: Spring Boot ::                (v4.0.4)

INFO : Tomcat started on port 8080 (http)
INFO : Started BlogPlatformApplication in 3.2 seconds
```

Now open your browser and go to:

```
http://localhost:8080/
```

You should see:

```json
{
  "app": "Blog Platform API",
  "status": "running",
  "version": "1.0.0"
}
```

> ✅ This confirms your server is live and ready.

---

## 🔐 Step 6 — Admin Usage

### 6.1 — Register the Admin account

Open **Postman** and send this request:

**Method:** `POST`
**URL:** `http://localhost:8080/api/auth/register`

**Headers tab:**
```
Content-Type: application/json
```

**Body tab → raw → JSON:**
```json
{
  "username": "admin",
  "email": "admin@blog.com",
  "password": "admin123"
}
```

**Expected Response:**
```json
{
  "message": "User registered successfully"
}
```

---

### 6.2 — Assign Admin role in MySQL

After registering, open **MySQL Workbench** and run:

```sql
USE blog_db;
UPDATE users SET role = 'ROLE_ADMIN' WHERE email = 'admin@blog.com';
```

---

### 6.3 — Admin Login

**Method:** `POST`
**URL:** `http://localhost:8080/api/auth/login`

**Body:**
```json
{
  "email": "admin@blog.com",
  "password": "admin123"
}
```

**Expected Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1..."
}
```

> 📋 Copy this full token — you will use it in the `Authorization` header for all admin actions.

---

### 6.4 — Admin: Create a Blog Post

**Method:** `POST`
**URL:** `http://localhost:8080/api/posts`

**Headers:**
```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
Content-Type: application/json
```

**Body:**
```json
{
  "title": "Welcome to the Blog",
  "content": "This is the first official post on our platform.",
  "summary": "Admin's welcome post"
}
```

---

### 6.5 — Admin: Update a Post

**Method:** `PUT`
**URL:** `http://localhost:8080/api/posts/1`

**Headers:**
```
Authorization: Bearer <your_admin_token>
Content-Type: application/json
```

**Body:**
```json
{
  "title": "Updated Welcome Post",
  "content": "This content has been updated by the admin.",
  "summary": "Updated summary"
}
```

---

### 6.6 — Admin: Delete a Post

**Method:** `DELETE`
**URL:** `http://localhost:8080/api/posts/1`

**Headers:**
```
Authorization: Bearer <your_admin_token>
```

**Expected Response:**
```json
{
  "message": "Post deleted"
}
```

---

## 👤 Step 7 — Normal User Usage

### 7.1 — Register a Normal User

**Method:** `POST`
**URL:** `http://localhost:8080/api/auth/register`

**Body:**
```json
{
  "username": "john",
  "email": "john@example.com",
  "password": "password123"
}
```

---

### 7.2 — Normal User Login

**Method:** `POST`
**URL:** `http://localhost:8080/api/auth/login`

**Body:**
```json
{
  "email": "john@example.com",
  "password": "password123"
}
```

Copy the token from the response.

---

### 7.3 — Read All Posts (no token needed)

**Method:** `GET`
**URL:** `http://localhost:8080/api/posts`

**Expected Response:**
```json
[
  {
    "id": 1,
    "title": "Welcome to the Blog",
    "content": "This is the first official post on our platform.",
    "summary": "Admin's welcome post",
    "createdAt": "2026-03-25T10:00:00"
  }
]
```

---

### 7.4 — Read a Single Post (no token needed)

**Method:** `GET`
**URL:** `http://localhost:8080/api/posts/1`

---

### 7.5 — Search Posts (no token needed)

**Method:** `GET`
**URL:** `http://localhost:8080/api/posts/search?keyword=welcome`

---

### 7.6 — Write a New Post (token required)

**Method:** `POST`
**URL:** `http://localhost:8080/api/posts`

**Headers:**
```
Authorization: Bearer <john's_token>
Content-Type: application/json
```

**Body:**
```json
{
  "title": "My First Post",
  "content": "Hello world, this is my first blog post!",
  "summary": "An introduction post"
}
```

> ✅ Normal users can only **edit or delete their own posts** — they cannot modify other users' posts.

---

## 📡 API Endpoints Reference

| Method | Endpoint | Auth Required | Description |
|--------|----------|:---:|-------------|
| `GET` | `/` | ❌ | API health check |
| `POST` | `/api/auth/register` | ❌ | Register new user |
| `POST` | `/api/auth/login` | ❌ | Login and get JWT token |
| `GET` | `/api/posts` | ❌ | Get all posts |
| `GET` | `/api/posts/{id}` | ❌ | Get a single post |
| `GET` | `/api/posts/search?keyword=` | ❌ | Search posts by title |
| `POST` | `/api/posts` | ✅ | Create a new post |
| `PUT` | `/api/posts/{id}` | ✅ | Update your own post |
| `DELETE` | `/api/posts/{id}` | ✅ | Delete your own post |

> All protected endpoints require this header:
> ```
> Authorization: Bearer <your_jwt_token>
> ```

---

## 🐙 Push to GitHub

### Step 1 — Create a new repository on GitHub

1. Go to [https://github.com](https://github.com) and sign in
2. Click **`+`** (top right) → **`New repository`**
3. Fill in:
    - **Repository name:** `blog-platform`
    - **Description:** `RESTful Blog Platform API — Spring Boot, JWT, MySQL`
    - **Visibility:** Public *(so others can see it)*
    - ❌ Do **NOT** tick "Add a README file" (you already have one)
4. Click **`Create repository`**

---

### Step 2 — Add a `.gitignore` file

In your project's **root folder** (same level as `pom.xml`), create a new file named `.gitignore` and paste this inside:

```gitignore
# Maven build output
target/
*.class

# IntelliJ IDEA files
.idea/
*.iml
*.iws
*.ipr

# Logs
*.log

# OS files
.DS_Store
Thumbs.db
```

> This prevents build files and IDE settings from being uploaded to GitHub.

---

### Step 3 — Open Terminal in IntelliJ

Press **`Alt + F12`** in IntelliJ to open the built-in terminal.

---

### Step 4 — Run these commands one by one

#### Initialize Git

```bash
git init
```

#### Set your identity (first time only)

```bash
git config --global user.name "Your Full Name"
git config --global user.email "your-email@example.com"
```

#### Stage all your project files

```bash
git add .
```

#### Commit with a message

```bash
git commit -m "Initial commit - Blog Platform API with Spring Boot, JWT and MySQL"
```

#### Rename branch to main

```bash
git branch -M main
```

#### Connect to your GitHub repository

```bash
git remote add origin https://github.com/YOUR_GITHUB_USERNAME/blog-platform.git
```

> ⚠️ Replace `YOUR_GITHUB_USERNAME` with your actual GitHub username.

#### Push your code to GitHub

```bash
git push -u origin main
```

When prompted:
- **Username:** your GitHub username
- **Password:** paste your **Personal Access Token** (see below)

---

### Step 5 — Generate a Personal Access Token (PAT)

GitHub no longer accepts your account password for push. You need a token:

1. Go to GitHub → **Settings** (top right avatar)
2. Scroll down to **Developer settings** (left sidebar)
3. Click **Personal access tokens** → **Tokens (classic)**
4. Click **Generate new token (classic)**
5. Give it a name like `blog-platform-push`
6. Set expiration to **90 days**
7. Check the **`repo`** checkbox
8. Click **Generate token**
9. **Copy the token immediately** — you won't see it again!
10. Paste it as your password when `git push` asks

Designed and developed by Disha Jaiswal

