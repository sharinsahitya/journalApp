# Journal Application (Spring Boot + MongoDB)

## 📖 Project Overview

This project is a REST API-based Journal Management Application developed using Spring Boot and MongoDB.

The application allows users to:

- Create a User
- View all Users
- Create Journal Entries
- Retrieve Journal Entries
- Delete Journal Entries
- Update Journal Entries
- Store journal entries associated with a specific user using MongoDB DBRef.

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data MongoDB
- MongoDB
- Lombok
- Maven
- Postman

---

## 📌 Features Implemented

### User APIs

- Create User
- Get All Users
- Update User

### Journal APIs

- Create Journal Entry
- Get All Journal Entries of a User
- Get Journal Entry by Id
- Delete Journal Entry
- Update Journal Entry

---

## 📂 Project Structure

```
Controller
    |
Service
    |
Repository
    |
MongoDB
```

---

## 🗂 MongoDB Collections

### users

Stores user details.

Example:

```json
{
  "_id": "...",
  "userName": "vipul",
  "password": "12345",
  "journalEntries": [
      DBRef(...)
  ]
}
```

---

### journal_entries

Stores journal entry details.

Example

```json
{
    "_id":"...",
    "title":"My First Journal",
    "content":"Learning Spring Boot",
    "date":"2026-11-19T10:30:00"
}
```

---

# DBRef Concept

## What is DBRef?

DBRef (Database Reference) is a MongoDB feature used to create a relationship between two collections.

Instead of embedding the complete JournalEntry document inside the User document, MongoDB stores only a reference to it.

Example:

```java
@DBRef
private List<JournalEntry> journalEntries = new ArrayList<>();
```

When a journal entry is created:

1. JournalEntry is saved in the `journal_entries` collection.
2. A reference to that journal entry is added to the `journalEntries` list inside the User document.
3. This creates a one-to-many relationship between User and JournalEntry.

### Example

User Collection

```json
{
   "userName":"vipul",
   "journalEntries":[
      DBRef("journal_entries","654dxxxxx")
   ]
}
```

Journal Collection

```json
{
   "_id":"654dxxxxx",
   "title":"Day 1",
   "content":"Learning MongoDB"
}
```

---

# MongoDB Shell (mongosh) Commands

## Start MongoDB Shell

```javascript
mongosh
```

## Show Databases

```javascript
show dbs
```

## Select Database

```javascript
use journaldb
```

## Check Current Database

```javascript
db
```

## Show Collections

```javascript
show collections
```

---

## View Data

View all users

```javascript
db.users.find()
```

Pretty format

```javascript
db.users.find().pretty()
```

View one user

```javascript
db.users.findOne()
```

Count documents

```javascript
db.users.countDocuments()
```

---

## Insert Data

```javascript
db.users.insertOne({
    userName:"vipul",
    password:"12345"
})
```

---

## Update Data

```javascript
db.users.updateOne(
   {userName:"vipul"},
   {$set:{password:"newPassword"}}
)
```

---

## Delete One User

```javascript
db.users.deleteOne({
    userName:"vipul"
})
```

---

## Delete All Journal Entries

```javascript
db.journal_entries.deleteMany({})
```

---

## Delete All Users

```javascript
db.users.deleteMany({})
```

---

## Drop Collection

```javascript
db.users.drop()
```

---

## Drop Database

```javascript
db.dropDatabase()
```

---

# Testing APIs Using Postman

## Create User

POST

```
http://localhost:8080/user
```

Body

```json
{
    "userName":"vipul",
    "password":"12345"
}
```

---

## Create Journal Entry

POST

```
http://localhost:8080/journal/vipul
```

Body

```json
{
    "title":"My First Journal",
    "content":"Learning Spring Boot"
}
```

---

## Learning Resources

### Spring Boot + MongoDB Playlist

https://www.youtube.com/watch?v=Cx81dki8BTA&list=PLA3GkZPtsafacdBLdd3p1DyRd5FGfr3Ue

---

db.journal_entries.find()
[
{
_id: ObjectId('6aff027b496a002225b8b2da'),
date: ISODate('2026-11-19T14:06:51.049Z'),
_class: 'net.engineeringdigest.journalApp.entity.JournalEntry'
},
{
_id: ObjectId('6aff0287496a002225b8b2db'),
date: ISODate('2026-11-19T14:07:03.492Z'),
_class: 'net.engineeringdigest.journalApp.entity.JournalEntry'
},
{
_id: ObjectId('6aff0300496a002225b8b2de'),
title: '00',
content: '02',
date: ISODate('2026-11-19T14:09:04.599Z'),
_class: 'net.engineeringdigest.journalApp.entity.JournalEntry'
}
]

db.users.find()
[
{
_id: ObjectId('6aff02a4496a002225b8b2dc'),
userName: 'vipul',
password: '12345',
journalEntries: [ DBRef('journal_entries', ObjectId('6aff0300496a002225b8b2de')) ],
_class: 'net.engineeringdigest.journalApp.entity.User'
},
{
_id: ObjectId('6aff02af496a002225b8b2dd'),
userName: 'ram',
password: '1845',
journalEntries: [],
_class: 'net.engineeringdigest.journalApp.entity.User'
}
]