<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="test_framework.*" %>
<%@ page import="framework.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.reflect.Method" %>
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
    <h1> <%/*
        if(request.getAttribute("message")!=null){ 
            out.println(request.getAttribute("message")); 
        } */%></h1>
    <h2>Hello</h2>
    <form action="findall">
        <input type="hidden" name="findall" value="msg1">
        <p><input type="submit" value="valide"></p>
    </form>
    <p><a href="save?message=hoho">Valider</a></p>
    <hr></hr>

    <div style="background-color:grey; padding:10px; width:400px;">
        <form action="findall">
            <p><input type="texte" placeholder="idemp" name="idemp" ></p>
            <p><input type="texte" placeholder="nom" name="nom" ></p>
            <p><input type="texte" placeholder="nee" name="nee" ></p>
            <select name="genre"><option value="">genre</option><option value="M">Masculin</option><option value="F">Feminin</option></select>
            <p><input type="submit" value="valide"></p>
        </form>
    </div>

    <hr></hr>
    <div style="background-color:grey; padding:10px; width:400px;">
        <h2>sprint 8</h2>
        <form action="findClient">
            <p><input type="texte" placeholder="id" name="id" ></p>
            <p><input type="texte" placeholder="nee" name="nee" ></p>
            <p><input type="submit" value="valide"></p>
        </form>
    </div>
    <% 
    if (request.getAttribute("vClassMethod")!=null){
        Vector vCM=(Vector)request.getAttribute("vClassMethod");
        Object[] objCM=null;
        Method meth=null;
        Class cl=null;
        for(int i=0;i<vCM.size();i++){
            objCM=(Object[])vCM.elementAt(i);
            cl=(Class)objCM[0];
            meth=(Method)objCM[1];
            out.println("<p> Class :"+cl.getSimpleName()+" / method :"+meth.getName()+"</p>");
        }
    }%>

    <hr></hr>
    <div style="background-color:grey; padding:10px; width:400px;">
        <h2>sprint 9</h2>
        <form action="findall" method="post" enctype="multipart/form-data" >
            <input type="file" name="fileUpload" />
            <input type="submit" value="Upload" />
        </form>
        
    </div>
    

</body>
</html>