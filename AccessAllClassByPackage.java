package model;

import java.io.File;
import java.sql.*;
import java.util.Vector;
import java.lang.annotation.*;
import annotation.*;

import java.lang.reflect.*;

public class AccessAllClassByPackage {

   public Class[] getAllClassByPackage(String packageAbsolute,String packageracine) throws Exception {
      File folder = new File(packageAbsolute);
      if(folder.exists()==false){ throw new Exception("package : \""+packageAbsolute+"\" n'existe pas ou introuvable"); }
      if(folder.isDirectory()==false){ throw new Exception("package : \""+packageAbsolute+"\" n'existe pas ou introuvable"); }
      File[] files = folder.listFiles();
      String nfile = "";
      String nameClass = "";
      String packg = packageracine.replace('\\', '.');
      packg = packg.replace('/', '.');
      Vector vclasses = new Vector();
      for (int i = 0; i < files.length; i++) {
         nfile = files[i].getName();
         if (nfile.length() >= 6) {
            System.out.println("----------------"+nfile+"--------------------3");
            if ( files[i].isFile()==true && nfile.substring(nfile.length() - 6, nfile.length()).compareTo(".class") == 0) {
               // System.out.print(nfile+" / ");
               nameClass = nfile.substring(0, nfile.length() - 6);
               System.out.print(packageracine+"."+nameClass);
               vclasses.add(Class.forName(packg+ "." + nameClass));
               System.out.println(vclasses.elementAt(vclasses.size()-1));
               // System.out.println(vclasses.elementAt( vclasses.size()-1 )+" \n");
            }
         }
      }
      if (vclasses.size() < 1) {    return null;   }
      Class[] classes = new Class[vclasses.size()];
      for (int i = 0; i < vclasses.size(); i++) {     classes[i] = (Class) vclasses.elementAt(i);     }
      return classes;
   }
   public void path(){
      String path ="D:\\ITUS4\\mrNaina\\sprint--0\\etu1786\\model";
      String racine="etu1786\\model";
      String path2=path.substring(0,path.length()-racine.length()-1);
      System.out.println(path2);
      String path3=path.substring(path2.length()+1, path.length());
      System.out.println(path3);
   }

   public Class[] getAllClassinAllpackageBypackageRacine(String packageAbsolute,String packageRacine)throws Exception {
      File file = new File(packageAbsolute);
      File[] files0=null;
      Vector vfile=new Vector();
      Vector vclass=new Vector();
      Class [] classes=null;
      classes=this.getAllClassByPackage(packageAbsolute,packageRacine);

      String path2=packageAbsolute.substring(0,packageAbsolute.length()-packageRacine.length()-1);
      System.out.println(path2);
      String path3=packageAbsolute.substring(path2.length()+1, packageAbsolute.length());
      System.out.println(path3);

      if(classes!=null){   for(int j=0;j<classes.length;j++){ vclass.add(classes[j]); }   }
      File[] files=file.listFiles();
      for(int i=0;i<files.length;i++){
         if( files[i].isDirectory() ){

            files0=files[i].listFiles();
            if(files0!=null){    for(int j=0;j<files0.length;j++){ vfile.add(files0[j]); }   }
            System.out.println("-----"+files[i].getPath());

            path3=files[i].getPath().substring(path2.length()+1, files[i].getPath().length());
            System.out.println(path3);

            classes=this.getAllClassByPackage(files[i].getPath(), path3);
            if(classes!=null){   for(int j=0;j<classes.length;j++){ vclass.add(classes[j]); }   }

         }
         if(i==files.length-1){//rehefa le tapitra ny tour rehetra
            if(vfile.size()>0){ //sady nisy ny repertoire tao ambaniny no nisy lst fichier tao amin'io repertoire io
               i=0; //avareny hi-boucler ndray
               files=new File[vfile.size()];//ito ndray le ho-bouclena
               for(int j=0;j<vfile.size();j++){ files[j]=(File)vfile.elementAt(j); }
               vfile.clear(); //videna ndray aloha
            }
         }
      }
      if(vclass.size()<1){ return null; }
      Class[] repclass=new Class[vclass.size()];
      for(int i=0;i<vclass.size();i++){   repclass[i]=(Class)vclass.elementAt(i);   }
      return repclass;
      //getAnnotation Methode by nom-->omena le class misy an'le method
   }

   public Class[] getAllClassByAnnotation(Class annotationclass, String packageAbsolute, String packagePath) throws Exception {
      Class[] classes = getAllClassByPackage(packageAbsolute,packagePath);
      Vector vclasses = new Vector();
      if (classes == null) {
         return null;
      }
      Annotation annotation = null;
      for (int i = 0; i < classes.length; i++) {
         annotation = classes[i].getAnnotation(annotationclass); // null si classes[i] n'a pas d' "annotationclass"
         if (annotation != null) {
            vclasses.add(classes[i]);
         } // !=null -> ce claase a cette notation
      }
      if (vclasses.size() < 1) {
         return null;
      }
      Class[] classesAnnot = new Class[vclasses.size()];
      // System.out.println("----------------Class annoter
      // "+annotationclass.getName()+" : ");
      for (int i = 0; i < vclasses.size(); i++) {
         classesAnnot[i] = (Class) vclasses.elementAt(i);
         // System.out.println(classesAnnot[i]);
      }
      return classesAnnot;
   }

   public Field[] getAllFieldsOfObjectByAnnotation(Object object, Class annotationclass) throws Exception {
      Field[] fields = object.getClass().getDeclaredFields();
      Annotation annotation = null;
      Vector vfields = new Vector();
      for (int i = 0; i < fields.length; i++) {
         annotation = fields[i].getAnnotation(annotationclass);
         if (annotation != null) {
            vfields.add(fields[i]);
         }
      }
      if (vfields.size() < 1) {
         return null;
      }
      Field[] fieldsAnnot = new Field[vfields.size()];
      // System.out.println("----------------Field annoter "+annotationclass+" : ");
      for (int i = 0; i < vfields.size(); i++) {
         fieldsAnnot[i] = (Field) vfields.elementAt(i);
         // System.out.println(fieldsAnnot[i]);
      }
      return fieldsAnnot;
   }

   public Method[] getAllMethodsOfObjectByAnnotation(Object object, Class annotationClass) throws Exception {
      Method[] methods = object.getClass().getDeclaredMethods();
      Annotation annotation = null;
      Vector vmethods = new Vector();
      for (int i = 0; i < methods.length; i++) {
         annotation = methods[i].getAnnotation(annotationClass);
         if (annotation != null) {
            vmethods.add(methods[i]);
         }
      }
      if (vmethods.size() < 1) {
         return null;
      }
      Method[] methodsAnnot = new Method[vmethods.size()];
      for (int i = 0; i < vmethods.size(); i++) {
         methodsAnnot[i] = (Method) vmethods.elementAt(i);
      }
      return methodsAnnot;
   }
   public Method[] getAllMethodsOfObjectByAnnotationname(Object object, String annotationname) throws Exception {
      Method[] methods = object.getClass().getDeclaredMethods();
      Annotation[] annotations = null;
      Annotation annotation=null;
      Vector vmethods = new Vector();
      for (int i = 0; i < methods.length; i++) {
         annotations =methods[i].getAnnotations(); //recuperer les annotations du methods[i] pour la comparaison si dessous
         if (annotations != null) {
            for(int u=0;u<annotations.length;u++){
               if(annotations[u].getClass().getSimpleName().compareTo(annotationname)==0){ //comparaison des chaques annotations du method au annotation name donnee
                  vmethods.add(methods[i]);
                  u=annotations.length; //si il trouve qu'une seul , on arrete le boucle
               }
            }
         }
      }
      if (vmethods.size() < 1) { return null;   }
      Method[] methodsAnnot = new Method[vmethods.size()];
      for (int i = 0; i < vmethods.size(); i++) {
         methodsAnnot[i] = (Method) vmethods.elementAt(i);
      }
      return methodsAnnot;
   }
   public Method[] getAllMethodsOfObjectByAnnotationvalue(Object object, String annotationvalue,String nameMethod) throws Exception {
      Method[] methods = object.getClass().getDeclaredMethods();
      Annotation[] annotations = null;
      Annotation annotation=null;
      Vector vmethods = new Vector();
      Object objvalue=null;
      for (int i = 0; i < methods.length; i++) {
         annotations =methods[i].getAnnotations(); //recuperer les annotations du methods[i] pour la comparaison si dessous
         if (annotations != null) {
            for(int u=0;u<annotations.length;u++){
               //System.out.println(methods[i] +" "+annotations[u]+" "+nameMethod);
               objvalue=this.getValueAnnotation(methods[i],annotations[u].annotationType(), nameMethod);
               //System.out.println(objvalue);
               if(objvalue!=null){
                  if(objvalue.toString().compareTo(annotationvalue)==0){ //comparaison des chaques annotations du method au annotation name donnee
                     vmethods.add(methods[i]);
                     u=annotations.length; //si il trouve qu'une seul , on arrete le boucle
                  }
               }
            }
         }
      }
      if (vmethods.size() < 1) { return null;   }
      Method[] methodsAnnot = new Method[vmethods.size()];
      for (int i = 0; i < vmethods.size(); i++) {
         methodsAnnot[i] = (Method) vmethods.elementAt(i);
      }
      return methodsAnnot;
   }

   public Object getValueAnnotation(Object object, Class annotationClass, String nameMethod) throws Exception {
      Annotation annotation = object.getClass().getAnnotation(annotationClass);
      if (object instanceof Field) {
         Field field = (Field) object;
         annotation = field.getAnnotation(annotationClass);
      }else if( object instanceof Method ){
         Method method = (Method) object;
         annotation = method.getAnnotation(annotationClass);
      }
      // System.out.println(annotation.annotationType());
      if(annotation==null){ return null; }
      Method[] m = annotation.annotationType().getDeclaredMethods();
      Object value = null;
      for (int i = 0; i < m.length; i++) {
         if (nameMethod.compareTo(m[i].getName()) == 0) {
            value = m[i].invoke(annotation);
            // System.out.println("valeur -----> "+value);
         }
      }
      return value;
   }

   public Object[] getAllValueAnnotation(Object object, Class annotationClass) throws Exception {
      Annotation annotation = object.getClass().getAnnotation(annotationClass);
      if (object instanceof Field) {
         Field field = (Field) object;
         annotation = field.getAnnotation(annotationClass);
      }else if( object instanceof Method ){
         Method method = (Method) object;
         annotation = method.getAnnotation(annotationClass);
      }
      // System.out.println(annotation.annotationType());
      if (annotation == null) {
         return null;
      }
      Method[] m = annotation.annotationType().getDeclaredMethods();
      Object[] values = new Object[m.length];
      for (int i = 0; i < m.length; i++) {

         values[i] = m[i].invoke(annotation);
         // System.out.println("valeur -----> "+values[i]);
      }
      return values;
   }

   public Vector<Object[]> getClassAndtheMethodinPackagebyAnnotationname(String packageAbsolute,String pckgracine,String annotationname)throws Exception{
      Class[] classes=this.getAllClassinAllpackageBypackageRacine(packageAbsolute,pckgracine);
      Vector  vCM=new Vector<Object[]>();
      Method[] methods=null;
      Object[] objCM=null;
      if(classes==null){ return null; }
      for(int i=0;i<classes.length;i++){
         methods=this.getAllMethodsOfObjectByAnnotationname(classes[i].newInstance(), annotationname);
         if(methods!=null){ 
            for(int u=0; u< methods.length ;u++){
               objCM=new Object[2];
               objCM[0]=classes[i];
               objCM[1]=methods[u];
               vCM.add(objCM);
            }
         }
      }
      if(vCM.size()<1){ return null; }
      return vCM;
   }  

   public Vector<Object[]> getClassAndtheMethodinPackagebyAnnotationvalue( String packageAbsolute,String pckgracine,String annotationvalue, String nameMethod)throws Exception{
      Class[] classes=this.getAllClassinAllpackageBypackageRacine(packageAbsolute , pckgracine);
      Vector  vCM=new Vector<Object[]>();
      Method[] methods=null;
      Object[] objCM=null;
      if(classes==null){ return null; }
      for(int i=0;i<classes.length;i++){
         methods=this.getAllMethodsOfObjectByAnnotationvalue(classes[i].newInstance(), annotationvalue,nameMethod);
         if(methods!=null){ 
            for(int u=0; u< methods.length ;u++){
               objCM=new Object[2];
               objCM[0]=classes[i];
               objCM[1]=methods[u];
               vCM.add(objCM);
            }
         }
      }
      if(vCM.size()<1){ return null; }
      return vCM;
   }  
   // Field[] fields=annotation.annotationType().getDeclaredFields();
   // for(int i=0;i<fields.length;i++){
   // System.out.println(fields[i]);
   // }

   // comment ajouter et recuperer valeur field d'un Annotation

}
