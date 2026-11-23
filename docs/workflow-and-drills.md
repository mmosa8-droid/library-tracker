# GitHub workflow and recovery drills

## One-account practice plan
The assignment's two-person review requirement cannot be truthfully completed by one account. Use this as a solo practice adaptation and ask the instructor whether it is accepted. Do not claim a second student reviewed or approved your PR.

## Branch / issue plan
Create Issue 1: Add and list books.
Branch: `feature/add-list-books`
Suggested commits:
1. `feat(books): add book model`
2. `feat(books): implement adding books`
3. `feat(books): list all books`

Create Issue 2: Search and remove books.
Branch: `feature/search-remove`
Suggested commits:
1. `feat(books): search by title and author`
2. `feat(books): remove book by id`
3. `test(books): handle empty and invalid input`

Open PRs against `main`; include `Closes #1` or the correct issue number in the description. Review your own diff as a solo checklist; do not represent it as partner approval. Merge and delete the feature branch after the instructor-approved workflow.

## Conflict and rebase
1. Create two branches from the same `main`.
2. Edit the same lines in `print_banner()` in `src/main/java/Main.java` on both branches.
3. Commit and push both branches.
4. Merge the first branch.
5. Merge the second branch and resolve the conflict manually. Remove all `<<<<<<<`, `=======`, and `>>>>>>>` markers, keeping the intended final banner.
6. For the rebase demonstration, create a local branch and make two commits. Run `git fetch origin` then `git rebase origin/main`. Capture `git log --graph --oneline --decorate --all` before and after.

## Fix-it drills (perform in a disposable branch/repository; take your own screenshots)
### 1. Commit on wrong branch and move it
```bash
git log --oneline -1
git switch correct-branch
git cherry-pick <commit-sha>
git switch wrong-branch
git reset --hard HEAD~1
```
Only use `reset --hard` after confirming the commit is safely copied and the working tree has no needed changes.

### 2. Forgot a file — amend
```bash
git add forgotten-file
git commit --amend --no-edit
```
Use this only before the commit is shared, or coordinate before rewriting shared history.

### 3. Stash to switch branches
```bash
git stash push -m "work in progress"
git switch another-branch
git switch original-branch
git stash pop
```

### 4. Push rejected — pull then push
```bash
git pull --rebase origin your-branch
git push origin your-branch
```
Resolve any conflicts before continuing.

### 5. Accidentally tracked `.env`
```bash
git rm --cached .env
echo .env >> .gitignore
git add .gitignore
git commit -m "chore: stop tracking environment file"
```
If `.env` contained a real secret, rotate it; removing it from the latest commit does not erase it from history.

## Screenshots
Take screenshots from your own terminal/GitHub after actually performing each drill. Label them `E1` through `E5`. Do not fabricate screenshots or claim a command ran if it did not.
