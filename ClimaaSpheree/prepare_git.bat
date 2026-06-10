@echo off
echo ===================================================
echo     Preparing ClimaaSpheree Git Repository
echo ===================================================

:: Check if git is installed
where git >nul 2>nul
if %errorlevel% neq 0 (
    echo [ERROR] Git is not installed or not in your PATH.
    echo Please install Git from https://git-scm.com/ and try again.
    pause
    exit /b 1
)

:: Initialize git if needed
if not exist .git (
    echo [1/4] Initializing Git repository...
    git init
) else (
    echo [1/4] Git repository already initialized.
)

:: Set main branch
echo [2/4] Setting default branch to 'main'...
git branch -M main

:: Stage files
echo [3/4] Staging files...
git add .

:: Commit files
echo [4/4] Creating initial commit...
git commit -m "Initial commit: ClimaaSpheree multi-module Android weather application"

echo ===================================================
echo     Success! Repository is ready to push.
echo ===================================================
echo To push to your GitHub repository, run the following:
echo   git remote add origin [YOUR_GITHUB_REPO_URL]
echo   git push -u origin main
echo ===================================================
pause
