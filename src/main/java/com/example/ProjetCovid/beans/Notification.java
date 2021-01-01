package com.example.ProjetCovid.beans;

public class Notification {
    private String idNotif;
    private String about;
    private String dateSending;
    private String idUser;
    private String isReading;

    public String getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(String idNotif) {
        this.idNotif = idNotif;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDateSending() {
        return dateSending;
    }

    public void setDateSending(String dateSending) {
        this.dateSending = dateSending;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIsReading() {
        return isReading;
    }

    public void setIsReading(String isReading) {
        this.isReading = isReading;
    }
}
