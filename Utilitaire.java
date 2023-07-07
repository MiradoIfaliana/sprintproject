package framework;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Vector;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.*;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import javax.swing.SortingFocusTraversalPolicy;

import javax.sound.midi.SysexMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.*;

import framework.AccessAllClassByPackage;
import framework.Mapping;
import framework.annotation.*;;

public class Utilitaire {
    public String to_getAttribu(String nomAttribu,String typeField)
    {
          String getAttribMaj="get";
          if(typeField.compareToIgnoreCase("boolean")==0){  getAttribMaj="is";  }
          String attrib=nomAttribu.substring(0,1).toUpperCase(); //rendre en majuscul la premiere lettre du nomAtribu
          String restattrib=nomAttribu.substring(1,nomAttribu.length());  //prendre les lettres a partir du 2e lettre
          getAttribMaj=getAttribMaj.concat(attrib+restattrib);  //fusionner pour avoir le nom de fonction "getAtribu"
                       //Method m=c.getMethod("getVar1");
          return getAttribMaj;
    }
    public String to_setAttribu(String nomAttribu)
    {
          String getAttribMaj="set";
          String attrib=nomAttribu.substring(0,1).toUpperCase(); //rendre en majuscul la premiere lettre du nomAtribu
          String restattrib=nomAttribu.substring(1,nomAttribu.length());  //prendre les lettres a partir du 2e lettre=
          getAttribMaj=getAttribMaj.concat(attrib+restattrib); //fusionner pour avoir le nom de fonction "getAtribu"
           //Method m=c.getMethod("getVar1");
          return getAttribMaj;
    }

      public Object valuOfString(String type, String value){
            Object object=value;
            if(type.compareTo("int")==0){
                  object=Integer.valueOf(value);
            }else if(type.compareTo("long")==0){
                  object=Long.valueOf(value);
            }else if(type.compareTo("float")==0){
                  object=Float.valueOf((value));
            }else if(type.compareTo("char")==0){
                  String str=value;
                  object=str.charAt(0);
            }else if(type.compareTo("double")==0){
                  object=Double.valueOf(value);
            }else if(type.compareTo("LocalDate")==0){ //annee-mois-jours
                  String [] strDt=value.split("-");
                  String[] forJour=strDt[2].split(" ");
                  LocalDate date=LocalDate.of(Integer.valueOf(strDt[0]), Integer.valueOf(strDt[1]) , Integer.valueOf(forJour[0]) );
                  object=date;
            }else if(type.compareTo("Date")==0){
                  Date date=Date.valueOf(value);
                  object=date;
            }else if(type.compareTo("Time")==0){
                  Time time=Time.valueOf(value);
                  object=time;
            }else if(type.compareTo("boolean")==0){
                  object=Boolean.valueOf(value);
            }
            return object;
      }

      public Object getDefaultValue(Object obj){
            // boolean : valeur par défaut est false.
            // byte : valeur par défaut est 0.
            // short : valeur par défaut est 0.
            // int : valeur par défaut est 0.
            // long : valeur par défaut est 0L.
            // float : valeur par défaut est 0.0f.
            // double : valeur par défaut est 0.0.
            // char : valeur par défaut est '\u0000' (caractère nul).
            Object o=null;
            if(obj instanceof Boolean){ return false; }
            if(obj instanceof Byte){ o=(byte)0; }
            if(obj instanceof Short){ o=(short)0; }
            if(obj instanceof Integer){ o=0; }
            if(obj instanceof Long){ o=0L; }
            if(obj instanceof Float){ o=0.0f; }
            if(obj instanceof Double){ o=0.0; }
            if(obj instanceof Character){ o='\u0000'; }
            return o;
      }

      public void resetDefaultValueFieldOfObject(Object object)throws Exception{
            if(object!=null){
                  Field[] fields=object.getClass().getDeclaredFields();
                  for(int i=0;i<fields.length;i++){
                        if(fields[i].getName().compareTo("nbAppel")!=0){
                              fields[i].setAccessible(true);
                              fields[i].set( object , getDefaultValue(fields[i].get(object)) );  //set par la valeur par defaut
                              fields[i].setAccessible(false);
                        }
                  }
            }
      }




      public Object createtheInstanceClasseIfExisteRequest(Class classe,File xmlfile,HashMap<Class,Object> instances,HttpServletRequest request)throws Exception, ServletException, IOException {
            //le class anaty servlet no ijerena azy, singleton sa tsia
            Object object=null;
            AccessAllClassByPackage access=new AccessAllClassByPackage();
            Field fie=null;
            if(classe!=null){
                  if(access.isClassAnnotedAndWithValue(classe, Scope.class, "annote", "singleton")==true){ //si singleton
                        if(instances.containsKey(classe)==true){ //si le cle (key) existe
                              if(instances.get(classe)==null){ 
                                    instances.replace(classe, classe.newInstance()); //ajouter l'instance si il y a encore
                              }
                        }else{ //si non on l'ajoute
                              instances.put(classe, classe.newInstance());
                        }
                        object=instances.get(classe); //prends l'instance dans l'hashmap
                        try{
                              object.getClass().getDeclaredMethod("plusplusnbAppel").invoke(object); //nbAppel++
                              fie=object.getClass().getDeclaredField("nbAppel");
                              fie.setAccessible(true);
                              System.out.println("nombre appel de "+object.getClass().getSimpleName()+"--------- >"+fie.get(object) );
                              fie.setAccessible(false);
                        }catch(Exception ex){
                                    ex.printStackTrace();
                        }

                  }else{ //si ce n'est pas singleton
                        object=classe.newInstance(); 
                  }
            }else{ return null; }
            resetDefaultValueFieldOfObject(object); //fields to default value

            Field[] fields= classe.getDeclaredFields();
            String rqsVal="";
            Object param=null;
            boolean exist=false;
            Part filePart=null;
            if(request!=null){
                  for(int i=0;i<fields.length;i++){
                        //raha FileUpload
                        if(fields[i].getType().getSimpleName().compareTo("FileUpload")==0){
                              //System.out.println(fields[i].getName());
                              try{
                                    filePart = request.getPart(fields[i].getName());
                              }catch(Exception ex){
                                    //ex.printStackTrace();
                              }
                              if(filePart!=null){
                                    InputStream fileContent = filePart.getInputStream();
                                    //----------------------------------------------------UPLOAD DU FILE
                                    DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
                                    DocumentBuilder builder=factory.newDocumentBuilder();
                                    //File xmlFile = new File("D:\\ITUS4\\mrNaina\\sprint--0\\web.xml");
                                    File xmlFile = xmlfile;//new File(this.getServletContext().getRealPath("/WEB-INF/web.xml"));
                                    Document document = builder.parse(xmlFile);
                                    Element rootElement= document.getDocumentElement();
                                    String fileName = filePart.getSubmittedFileName();
                                    //System.out.println(fileName);
                                    // Définissez le chemin de destination pour enregistrer le fichier téléchargé
                                    NodeList nodeList=rootElement.getElementsByTagName("upload-path");
                                    Element element=(Element)nodeList.item(0); 
                                    String uploadPath = element.getTextContent();
                                    System.out.println(uploadPath);
                                    // Créez le flux de sortie pour écrire le fichier téléchargé
                                   filePart.write(uploadPath+"\\"+fileName);//sauvgarder le fichier
                                   //------------------------------w---------------------------------------------------
                                    ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                                    byte[] buffer = new byte[4096];
                                    int bytesRead;
                                    while ((bytesRead = fileContent.read(buffer)) != -1) {
                                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                                    }
                                    byte[] filebytes=byteArrayOutputStream.toByteArray();
                                    //-----creer le fileUpload
                                    FileUpload fileUpload=new FileUpload();
                                    fileUpload.setFilename(filePart.getSubmittedFileName());
                                    fileUpload.setBytes(filebytes);     
                                    param=fileUpload;     
                                    //set
                                    object.getClass().getDeclaredMethod( to_setAttribu(fields[i].getName()) ,fields[i].getType() ).invoke(object,param);
                                    // Fermez les flux
                                    byteArrayOutputStream.close();
                                    fileContent.close();
                                    exist=true;
                              }
                        }else{
                              rqsVal=request.getParameter(fields[i].getName());
                              if(rqsVal!=null && rqsVal!=""){
                                    //param=fields[i].getType().cast(rqsVal);
                                    param=valuOfString(fields[i].getType().getSimpleName(), rqsVal);
                                    object.getClass().getDeclaredMethod( to_setAttribu(fields[i].getName()) ,fields[i].getType() ).invoke(object,param);
                                    exist=true;
                              }
                        }
                  }
            }
            if(exist==false){ return null; }
            return object;
    }

    public Object[] createInstanceAllclassesIfExisteRequest(Class[] classes,File xmlfile ,HashMap<Class,Object> instances,HttpServletRequest request)throws Exception, ServletException, IOException {
      Vector vObjs=new Vector();
      Object obj=null;
      for(int i=0;i<classes.length;i++){ 
            obj=createtheInstanceClasseIfExisteRequest(classes[i],xmlfile,instances,request);
            if(obj!=null){ vObjs.add(obj); } 
      }
      if(vObjs.size()<1){ return null;}
      Object[] objects=new Object[vObjs.size()];
      for(int i=0;i<vObjs.size();i++){   objects[i]=vObjs.elementAt(i); }
      return objects;
      }
    public Vector<Object[]> getTheClassAndMethodByRequest(HashMap<String,Mapping> MappingUrls,HttpServletRequest request)throws Exception, ServletException, IOException {
      if(MappingUrls==null){return null;}
      else if(MappingUrls.size()<1){return null;}
      Vector vClassMethod=new Vector<Object[]>();
      Object[] objClaMeth=null;
      Mapping mapping=null;
      Class theClass=null;
      Method theMethod=null;
      Parameter[] parametre=null;
      int nbrequestExist=0;
      AccessAllClassByPackage access=new AccessAllClassByPackage();
      for(int i=0;i<MappingUrls.size();i++){
            mapping=(Mapping)MappingUrls.get(String.valueOf(i+1));
            theClass=mapping.getTheclass();
            theMethod=mapping.getThemethod();
            parametre=theMethod.getParameters();
            //System.out.println("method------:"+theMethod+"|----class:"+theClass.getName());
            String[] parametersname=(String[])access.getValueAnnotation(theMethod,Url.class , "parameters");
            
            if(parametersname!=null){
                  //System.out.println("length------------------------:"+parametersname.length);
                  if(parametersname.length>0){
                        for(int j=0;j<parametersname.length;j++){
                              //System.out.println("name------------------------:"+parametersname[j]);
                              if( request.getParameter(parametersname[j])!=null ){ //raha mi-existe le request mitovy @ le nom an'le parametre an'le fonction     
                                    nbrequestExist++;
                              }
                        }
                        if(nbrequestExist==parametersname.length){
                              objClaMeth=new Object[2];
                              objClaMeth[0]=theClass;
                              objClaMeth[1]=theMethod;
                              vClassMethod.add(objClaMeth);
                        }
                        nbrequestExist=0;
                  }
            }
      }
      if(vClassMethod.size()<1){return null;}
      return vClassMethod;
    }
   

}
