package com.example.kazajii.local;

public class Pharmacie {

    public String Name;
    public String Description;
    public double  Longitude;
    public  double Latitude;
    public int Image;
    public String Ville;
    public String Quartier;

    public Pharmacie(String name, String description, double longitude, double latitude ,String ville,String quartier) {

        Name = name;
        Description = description;
        Longitude = longitude;
        Latitude = latitude;
        Image = R.drawable.icon2;
        Ville =  ville;
        Quartier = quartier;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getQuartier() {
        return Quartier;
    }

    public void setQuartier(String quartier) {
        Quartier = quartier;
    }
}
