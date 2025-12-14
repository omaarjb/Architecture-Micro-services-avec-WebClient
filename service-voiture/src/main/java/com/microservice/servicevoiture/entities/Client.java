package com.microservice.servicevoiture.entities;

public class Client {
    private Long id;
    private String nom;
    private Float age;

    public Client() {
    }

    public Client(Long id, String nom, Float age) {
        this.id = id;
        this.nom = nom;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Float getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAge(Float age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return java.util.Objects.equals(id, client.id) &&
                java.util.Objects.equals(nom, client.nom) &&
                java.util.Objects.equals(age, client.age);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, nom, age);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", age=" + age +
                '}';
    }
}