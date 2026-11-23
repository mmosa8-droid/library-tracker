# Library Tracker (Java)

A small command-line library tracker for the Git/GitHub assignment. It can add, list, search, and remove books. Book data is stored in `books.json`.

## Author
- Momen Mousa (individual work, approved by the course instructor on 2026-11-23)
## Requirements
- Java 17+
- Maven 3.8+

## Run
```bash
mvn compile exec:java
```

## Git workflow
Use `main` as the stable branch. Create short-lived feature branches, commit small changes using Conventional Commits, push, and open a pull request. Link each PR to its issue using `Closes #<issue-number>`.

> This assignment was done individually, with the instructor's approval. Pull requests were reviewed by the author only.

## Features
- Add a book (title and author)
- List all books
- Search title or author (case-insensitive)
- Remove a book by ID
- Persist changes to `books.json`
