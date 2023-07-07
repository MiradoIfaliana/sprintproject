package test_framework.test_framework;
import  framework.annotation.*;

public class Client {

    int idclient;
    String nom;
    String nee;
    String genre;
    public int getIdclient() {
        return idclient;
    }
    public void setIdclient(int idclient) {
        this.idclient = idclient;
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
    
    @Url(url_map="findClient",parameters = {"idClient"})
    public void find(int idClient){
       System.out.println("idclient = "+idclient);
    }
     @Url(url_map="findClient",parameters = {"nom","nee"})
    public void find(String nom,String nee){
        String[] a={"haha","hoho"};
        System.out.println("nom = "+nom +" nee = "+nee);
     }
      @Url(url_map="findClient",parameters={"id","nee"})
     public void find(int id,String nee){
         //System.out.println("nom = "+nom +" nee = "+nee);
      }

}
