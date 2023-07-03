package test_framework.packe1.packe2;
import framework.*;
import java.util.*;
import framework.annotation.*;
public class Mirado {
    int idmirado;
    String nom;
    String nee;
    String genre;
    @Url(url_map="save")
    public void insert(){
        
    }
    
    @Url(url_map="findall")
    public void searchMirado(){
    }
    public void update(){
        
    }
    @Url(url_map="findall")
    public ModelView whereMirado(){
        ModelView mv=new ModelView();
        mv.addItem("1","Class Mirado");
        mv.setUrl("page1.jsp");
        return mv;
    }
    

}
