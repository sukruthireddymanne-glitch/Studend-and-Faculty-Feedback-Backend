@REM ----------------------------------------------------------------------------
@REM Maven Wrapper Script for Campus Issue Portal
@REM ----------------------------------------------------------------------------
@echo off
setlocal

set MAVEN_HOME=C:\Users\HOME\.m2\wrapper\dists\apache-maven-3.9.16-bin\5grr65jo27hi51sujmtcldfovl\apache-maven-3.9.16

if exist "%MAVEN_HOME%\bin\mvn.cmd" (
    call "%MAVEN_HOME%\bin\mvn.cmd" %*
) else (
    where mvn >nul 2>nul
    if %ERRORLEVEL% equ 0 (
        call mvn %*
    ) else (
        echo Error: Maven could not be found. Please ensure Maven is installed or set MAVEN_HOME.
        exit /b 1
    )
)
