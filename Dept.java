package test_framework;
import framework.annotation.*;

public class Dept {    
    int id;
    String nom;

    @Url(url_map="findall")
    public void find_all(){

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
