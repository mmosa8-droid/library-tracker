# Assignment Written Answers

## Part A — Version Control Systems

**Local VCS:**
A local version control system saves the history of files on one computer. One risk is losing all the saved work if the computer breaks or the files are deleted.

**Centralized VCS:**
A centralized system keeps the project on one main server. Team members connect to it to work on the project. The main risk is that if the server stops working, the team may not be able to access the project.

**Distributed VCS:**
A distributed system gives each user a copy of the project and its history. One risk is that team members may make different changes and get conflicts when they combine their work.

**Snapshots vs. deltas:**
Git saves the state of the project in each commit. This is called a snapshot. It does not simply save a list of changes from the previous version. Git also saves space by reusing files that have not changed.

**Modified, staged, and committed:**
Modified means the file has been changed in the working tree. Staged means the changes have been selected and placed in the staging area, ready to be committed. Committed means the changes have been saved in Git history inside the `.git` folder.

**Why is every clone a backup?**
Every Git clone contains the project and its saved history. If one copy is lost, another clone can help restore the project. However, changes that were not committed or pushed may still be lost.

## Part F — Reflection

For this project, I would usually use merge when I want to bring changes from a branch into the main branch and keep the history of the work. I would use rebase when I want to update my branch with the latest changes from main and keep the history linear, knowing that it creates new commits with new hashes.

The golden rule of rebasing is not to rebase commits that other people are already using. Rebase can change commit history, so it is better to use it on my own work before sharing it with others.

I prefer `--force-with-lease` over `--force` because it gives more protection when pushing changes. It checks if the remote branch has changed before updating it, and it refuses to push if there are commits I have not seen. This helps avoid overwriting work that someone else has pushed.

For a small project like the library tracker, I think GitHub Flow is the better choice. It is simple and easy to follow. I create a branch for each feature, make changes, open a pull request, and then merge it into main. GitFlow is more suitable for bigger projects that have planned releases and different release branches. For this project, GitHub Flow is enough and keeps the work organized without making it complicated.
