package etu1786.framework.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
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

          String url = request.getRequestURL().toString();
          System.out.println("URL : "+url);
          String contextPath = request.getContextPath();
          System.out.println("Context path : "+contextPath);
          String[] pathParts = contextPath.split("/");

          String sprintWeb = pathParts[pathParts.length - 1];
          System.out.println("sprint : "+sprintWeb);

          request.setAttribute("message",url+"<br>"+contextPath+"<br>"+sprintWeb);
          request.getRequestDispatcher("page1.jsp").forward(request, response);
            
    }

}
