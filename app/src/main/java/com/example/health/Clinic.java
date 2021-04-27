package com.example.health;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class Clinic implements Serializable {

    String name;
    String doctor;
    String service;



    public Clinic(String name, String doctor,String service) {
        this.name = name;
        this.doctor= doctor;
        this.service=service;

    }



    @NonNull
    @Override
    public String toString() {
        return name + "\n" + "Available Doctor: "+doctor + "\n" + "Speciality: "+service;
    }
}
