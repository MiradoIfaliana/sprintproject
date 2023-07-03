package test_framework.test_framework;
import framework.annotation.*;
import framework.*;
import java.util.*;

@Scope(annote="non")
public class Dept {    
    int id;
    String nom;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    @Url(url_map="findall")
    public ModelView find_all(){
       ModelView mv=new ModelView();
       mv.addItem("1","String1");
       mv.addItem("2","String2");
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
    @Url(url_map="findDept",parameters = {"id"})
    public ModelView find(int id){
        ModelView mv=new ModelView();
        mv.addItem("1","String1");
        mv.addItem("2","String2");
        mv.setUrl("page1.jsp");
        return mv;
    }
    @Url(url_map="findDept",parameters = {"id","nom"})
     public ModelView find(int id,String nom){
        ModelView mv=new ModelView();
        mv.addItem("1","String1");
        mv.addItem("2","String2");
        mv.setUrl("page1.jsp");
        return mv;
     }



}
