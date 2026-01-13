@echo off
echo Running StudentDBViewer (Windows)...

:: Compile
javac -cp .;postgresql-42.7.8.jar StudentDBViewer.java

:: Run
java -cp .;postgresql-42.7.8.jar StudentDBViewer
