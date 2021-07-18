package erecrutement.finances.gov.ma.MEF.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPage;

    private String intitled;
    private String font;
    private String couleur;
    private String image;
    private String type;
    private String titreComposante;

    public Page(String intitled, String font, String couleur, String image, String type, String titreComposante) {
        this.intitled = intitled;
        this.font = font;
        this.couleur = couleur;
        this.image = image;
        this.type = type;
        this.titreComposante = titreComposante;
    }

    public Page(){

    }

    public int getIdPage() {
        return idPage;
    }

    public void setIdPage(int idPage) {
        this.idPage = idPage;
    }

    public String getIntitled() {
        return intitled;
    }

    public void setIntitled(String intitled) {
        this.intitled = intitled;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitreComposante() {
        return titreComposante;
    }

    public void setTitreComposante(String titreComposante) {
        this.titreComposante = titreComposante;
    }

    @Override
    public String toString() {
        return "Page{" +
                "intitled='" + intitled + '\'' +
                ", font='" + font + '\'' +
                ", couleur='" + couleur + '\'' +
                ", image='" + image + '\'' +
                ", type='" + type + '\'' +
                ", titreComposante='" + titreComposante + '\'' +
                '}';
    }
}
