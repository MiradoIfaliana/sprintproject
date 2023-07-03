<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="test_framework.*" %>
<%@ page import="framework.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>front</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/Contact-Form-Clean.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>
<body>
    <h1> <%
        if(request.getAttribute("message")!=null){ 
            out.println(request.getAttribute("message")); 
        } %></h1>
    
        <%   
        if(request.getAttribute("hashmap")!=null){ 
            HashMap MappingUrls=(HashMap)request.getAttribute("hashmap");
            Mapping mapp=null;
            for(int i=0;i<MappingUrls.size();i++){
            mapp=(Mapping)MappingUrls.get(String.valueOf(i+1));
            out.println("<h2>class : "+mapp.getClassName()+" | method : "+mapp.getMethodName()+" </h2>");
            }
        }
        %>
    <h1>Les donnees recuperer</h1>
    <%
    if(request.getAttribute("1")!=null){ 
        out.println("<h2>"+ request.getAttribute("1")+"</h2>"); 
    }
    if(request.getAttribute("2")!=null){ 
        out.println("<h2>"+request.getAttribute("2")+"</h2>"); 
    }
    %>

    <h1>Les Objets creer via le formulaire</h1>
    <%
    if(request.getAttribute("objects")!=null){ 
        Object[] objects=(Object[])request.getAttribute("objects");
        for(int i=0;i<objects.length;i++){
            out.println("<p>"+objects[i]+"</p>"); 
        }
    }
    %>

</body>

</html>