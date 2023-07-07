<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>

<%
  // Obtenez le conteneur de la servlet
  ServletContext context = request.getServletContext();

  // Obtenez l'objet Part correspondant au fichier téléchargé
  Part filePart = request.getPart("file");

  // Récupérez le nom du fichier
  String fileName = filePart.getSubmittedFileName();

  // Définissez le chemin de destination pour enregistrer le fichier téléchargé
  String uploadPath = context.getRealPath("") + File.separator + "uploads" + File.separator + fileName;

  // Créez le flux de sortie pour écrire le fichier téléchargé
  OutputStream outStream = new FileOutputStream(uploadPath);

  // Lisez les données du flux d'entrée et écrivez-les dans le flux de sortie
  InputStream fileContent = filePart.getInputStream();
  byte[] buffer = new byte[4096];
  int bytesRead;
  while ((bytesRead = fileContent.read(buffer)) != -1) {
    outStream.write(buffer, 0, bytesRead);
  }

  // Fermez les flux
  fileContent.close();
  outStream.close();
%>

<p>Le fichier a été téléchargé avec succès !</p>
