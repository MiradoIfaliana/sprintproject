1)framework.jar :
-->mettre framework.jar dans le repertoire lib du web application
2)Annotation:
-pour utiliser l'annotation : import framework.annotation;
-nom de la class : Url
-pour annoter une methode : @Url(url_map="valeurAnnotation")

3) Web.xml :
    <servlet>
        <servlet-name>FrontServlet</servlet-name>
        <servlet-class>framework.FrontServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>FrontServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <session-config>
        <session-timeout>
            30
        </session-timeout>
    </session-config>

    <path>
	<path-after-WEB-INF>/classes/test_framework</path-after-WEB-INF> ------>le package se trouvant les classes ou les repertoirs des classes pour le teste a partir du repertoir classes
        <package-root-of-class>test_framework</package-root-of-class> ---------> le package racine de tous les repertoir ou les classes pour le teste
    </path>
