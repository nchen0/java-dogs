package com.lambda.javadogs;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data // Creates Getters, setters, toString for us
@Entity
public class Dogs {
    private @Id @GeneratedValue Long id;
    private String breed;
    private int weight;
    private boolean apartmentDog;

    public Dogs() {

    }

    public Dogs(String breed, int weight, boolean apartmentDog) {
        this.breed = breed;
        this.weight = weight;
        this.apartmentDog = apartmentDog;
    }


}
