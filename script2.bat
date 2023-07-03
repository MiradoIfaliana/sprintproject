javac -d . *.java
jar cf framework.jar framework/
@echo off
setlocal
rem Vérifier si le fichier framework.jar existe dans la destination
if exist "D:\ITUS4\mrNaina\sprint-10\test_framework\web-sprint\WEB-INF\lib\framework.jar" (
    rem Supprimer le fichier existant
    del "D:\ITUS4\mrNaina\sprint-10\test_framework\web-sprint\WEB-INF\lib\framework.jar"
)
copy "framework.jar" "D:\ITUS4\mrNaina\sprint-10\test_framework\web-sprint\WEB-INF\lib"

rem Vérifier si le répertoire test_framework existe dans la destination
if exist "D:\ITUS4\mrNaina\sprint-10\test_framework\web-sprint\WEB-INF\classes\test_framework" (
    rem Supprimer le répertoire existant
    rmdir /s /q "D:\ITUS4\mrNaina\sprint-10\test_framework\web-sprint\WEB-INF\classes\test_framework"
)
rem Copier le répertoire test_framework depuis le répertoire courant vers la destination
xcopy /E /I /Y "test_framework\test_framework" "D:\ITUS4\mrNaina\sprint-10\test_framework\web-sprint\WEB-INF\classes\test_framework\test_framework"

rem Vérifier si le répertoire test_framework existe dans la destination
if exist "D:\apache-tomcat-8\webapps\web-sprint" (
    rem Supprimer le répertoire existant
    rmdir /s /q "D:\apache-tomcat-8\webapps\web-sprint"
)
rem Copier le répertoire test_framework depuis le répertoire courant vers la destination
xcopy /E /I /Y "test_framework\web-sprint" "D:\apache-tomcat-8\webapps\web-sprint"
endlocal
