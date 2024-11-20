# FIRST Tech CMS

Repository containing all data for the Cambridge Maths School [FIRST Tech Challenge](https://www.firstinspires.org/robotics/ftc).

## Structure

Please use typical Java naming conventions throughout the repository.

- Files: `PascalCase.java`
- Directories: `lowercaseconcatenated`

## Git LFS

This repository uses Git LFS to store large files. Git LFS comes installed with Git for Windows. To install Git LFS on Linux or Unix, run the following command:

```bash
sudo apt-get install git-lfs
git lfs install
```

Git LFS means that binary files (like images, videos, and other large files) are stored in a separate location to the main repository, and are not tracked by Git. This means that the repository is much smaller and faster to manage.

To track a file with Git LFS, run the following command:

```bash
git lfs track "path/to/file"
```

This will create a `.gitattributes` in the active directory that will tell Git LFS to track the file. You can then add and commit the file as normal.