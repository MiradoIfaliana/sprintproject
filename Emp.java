package etu1786.model;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Vector;
import annotation.*;
public class Emp {
    int idemp;
    String nom;
    String nee;
    String genre;
    @Url(url_map="save")
    public void insert(){
        
    }
    
    @Url(url_map="findall")
    public void findEmp(){

    }
    public void update(){
        
    }
    @Url(url_map="findall")
    public void searchEmp(){
        
    }
    

}
