package test_framework;
import framework.annotation.*;
import framework.*;

public class Dept {    
    int id;
    String nom;

    @Url(url_map="findall")
    public ModelView find_all(){
       ModelView mv=new ModelView();
       mv.setUrl("page1.jsp");
       return mv;
    }
    @Url(url_map="save")
    public void insert(){
        
    }
    public void update(){
        
    }
    @Url(url_map="findall")
    public void search(){
    }


}
