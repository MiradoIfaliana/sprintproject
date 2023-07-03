javac -d . *.java
jar cf framework.jar framework/
@echo off
setlocal
rem Vérifier si le fichier framework.jar existe dans la destination
if exist "D:\apache-tomcat-8\webapps\spint_web\WEB-INF\lib\framework.jar" (
    rem Supprimer le fichier existant
    del "D:\apache-tomcat-8\webapps\spint_web\WEB-INF\lib\framework.jar"
)
copy "framework.jar" "D:\apache-tomcat-8\webapps\spint_web\WEB-INF\lib"

if exist "D:\apache-tomcat-8\webapps\spint_web\WEB-INF\web.xml" (
    rem Supprimer le fichier existant
    del "D:\apache-tomcat-8\webapps\spint_web\WEB-INF\web.xml"
)
copy "D:\ITUS4\mrNaina\sprint-11\test_framework\web.xml" "D:\apache-tomcat-8\webapps\spint_web\WEB-INF"

rem Vérifier si le répertoire test_framework existe dans la destination
if exist "D:\apache-tomcat-8\webapps\spint_web\WEB-INF\classes\test_framework" (
    rem Supprimer le répertoire existant
    rmdir /s /q "D:\apache-tomcat-8\webapps\spint_web\WEB-INF\classes\test_framework"
)
rem Copier le répertoire test_framework depuis le répertoire courant vers la destination
xcopy /E /I /Y "test_framework" "D:\apache-tomcat-8\webapps\spint_web\WEB-INF\classes\test_framework"
if exist "D:\apache-tomcat-8\webapps\spint_web\WEB-INF\WEB-INF\classes\test_framework\web.xml" (
    del "D:\apache-tomcat-8\webapps\spint_web\WEB-INF\WEB-INF\classes\test_framework\web.xml"
)
endlocal
