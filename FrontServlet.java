package framework;
import javax.servlet.*;
import javax.servlet.ServletContext;
import javax.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.*;
import test_framework.*;

import javax.sound.midi.SysexMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import framework.ModelView;
import framework.Utilitaire;
public class FrontServlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    HashMap<String,Mapping> MappingUrls;
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
//     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//          processRequest(request, response);
//     }
//     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//          processRequest(request, response);
//     }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
            
          //PrintWriter out=response.getWriter();
        try{
          String url = request.getRequestURL().toString();
          //System.out.println("URL : "+url);
          String uri = request.getRequestURI();
          //System.out.println("URI : "+uri);
          String[] uris=uri.split("/");
          String annotmethod=uris[uris.length-1];
          //System.out.println("annotation method : "+annotmethod);
          request.setAttribute("message",annotmethod);
          AccessAllClassByPackage access=new AccessAllClassByPackage(); 
          //System.out.println(new Client().getClass().getPackage());
          //-------------------------------------------------------------webxml
          DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
          DocumentBuilder builder=factory.newDocumentBuilder();
          //File xmlFile = new File("D:\\ITUS4\\mrNaina\\sprint--0\\web.xml");
          File xmlFile = new File(this.getServletContext().getRealPath("/WEB-INF/web.xml"));
          Document document = builder.parse(xmlFile);
      
          Element rootElement= document.getDocumentElement();

          NodeList nodeList=rootElement.getElementsByTagName("path-after-WEB-INF");
          Element element=(Element)nodeList.item(0); //de le 1er satria iray ihany ny path-after-WEB-INF
          String path=element.getTextContent();
          //System.out.println(path);

          nodeList=rootElement.getElementsByTagName("package-root-of-class");
          element=(Element)nodeList.item(0); 
          String pkgroot=element.getTextContent();
          //System.out.println(pkgroot);
          //-------------------------------------------------------------
          Vector vcm=access.getClassAndtheMethodinPackagebyAnnotationvalue(this.getServletContext().getRealPath("/WEB-INF"+path),pkgroot,annotmethod, "url_map");
          if(vcm==null){ vcm=new Vector(); }
          Mapping[] map=new Mapping[vcm.size()];
          Object[] objcm=null;
          Class classe=null;
          Method method=null;
          this.MappingUrls=new HashMap<>();
          for(int i=0;i<vcm.size();i++){
            objcm=(Object[])vcm.elementAt(i); classe=(Class)objcm[0]; method=(Method)objcm[1];

            //System.out.println(objcm[0].toString()+" | "+objcm[1].toString());
            map[i]=new Mapping();
            map[i].setClassName(classe.getSimpleName());
            map[i].setMethodName(method.getName());
            map[i].setTheclass(classe);

            MappingUrls.put(String.valueOf(i+1), map[i]);
          }
          request.setAttribute("hashmap",MappingUrls);

          String dispach="index.jsp";
          Mapping mapp=null;
          Class cl=null;
          String namereturn="";
          ModelView mv=null;
          Utilitaire util=new Utilitaire();
          Vector vObjs=new Vector();
          for(int i=0;i<MappingUrls.size();i++){ //tetezina le zavatra rehetra anatin'ilay MappingUrls azo avy amin'ilay package sy anotation teo ambony.
            mapp=MappingUrls.get(String.valueOf(i+1));
            //System.out.println("<h2>class : "+mapp.getClassName()+" | method : "+mapp.getMethodName()+" </h2>");
            cl=mapp.getTheclass();
            namereturn=cl.getMethod(mapp.getMethodName()).getReturnType().getSimpleName(); //de alaina le anaran'le type de retour
            if(namereturn.compareTo("ModelView")==0){ //ka jerena raha ModelView le type de retour

              mv=(ModelView)cl.getDeclaredMethod(mapp.getMethodName()).invoke(cl.newInstance()); //alaina le ModelView 
              if(mv.getData()!=null){
                for(int u=0;u<mv.getData().size();u++){
                  request.setAttribute(""+(u+1) , mv.getData().get((u+1)+"")); //(u+1)--->ici mon key est de String mais ce sont de chiffre et il commence par 1
                }
              }
              dispach=mv.getUrl();
            }
          }
          //------------------------------maka an'le valeur avy @ formulaire
          Class[] classes=access.getAllClassinAllpackageBypackageRacine(this.getServletContext().getRealPath("/WEB-INF"+path), pkgroot);
          Object[] objects=util.createAllIfExisteRequest(classes, request);
          request.setAttribute("objects", objects);
          //---------------------------------
          request.getRequestDispatcher(dispach).forward(request, response);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        //jar cf framework.jar framework/
        //jar cf test_framework.war test_framework/


            
    }
}
