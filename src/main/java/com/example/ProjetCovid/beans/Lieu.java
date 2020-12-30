package com.example.ProjetCovid.beans;

/**
 * Represente un lieu
 */
public class Lieu {
    private String idLieu;
    private String nom;
    private String adresse;
    private String coordGPS;

    public String getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(String idLieu) {
        this.idLieu = idLieu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCoordGPS() {
        return coordGPS;
    }

    public void setCoordGPS(String coordGPS) {
        this.coordGPS = coordGPS;
    }
}
