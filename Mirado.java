package test_framework.test_framework.packe2;
import framework.*;
import java.util.*;
import framework.annotation.*;
@Scope(annote="singleton")
public class Mirado {
    int idmirado;
    String nom;
    String nee;
    String genre;

    int nbAppel;

    public int getNbAppel() {
        return nbAppel;
    }

    public void setNbAppel(int nbAppel) {
        this.nbAppel = nbAppel;
    }
    public void plusplusnbAppel(){ nbAppel=nbAppel+1; }
    
    public int getIdmirado() {
        return idmirado;
    }

    public void setIdmirado(int idmirado) {
        this.idmirado = idmirado;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNee() {
        return nee;
    }

    public void setNee(String nee) {
        this.nee = nee;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
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
