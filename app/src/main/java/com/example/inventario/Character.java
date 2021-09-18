package com.example.inventario;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Character {


    private int id;
     private String name;
    private String status;
    private String species;
    private String gender;
    private String image;


    public Character(int id, String name, String status, String species, String gender, String image){
        this.setId(id);
        this.setName(name);
        this.getStatus();
        this.setSpecies(species);
        this.setGender(gender);
        this.setImage(image);

    }


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}

