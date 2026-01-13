@echo off
echo Running StudentDBViewer (Windows)...

:: Compile
javac -cp .;ojdbc11.jar StudentDBViewer.java

:: Run
java -cp .;ojdbc11.jar StudentDBViewer
