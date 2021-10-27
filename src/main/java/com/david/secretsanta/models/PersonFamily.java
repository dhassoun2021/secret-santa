package com.david.secretsanta.models;

import java.util.Objects;

public class PersonFamily {

    private final String id;
    private final String surname;
    private final String forename;
    private final String idGroupFamily;

    public PersonFamily(String id, String surname, String forename, String idGroupFamily) {
        this.id = id;
        this.surname = surname;
        this.forename = forename;
        this.idGroupFamily = idGroupFamily;
    }

    public String getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getForename() {
        return forename;
    }

    public String getIdGroupFamily() {
        return idGroupFamily;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonFamily that = (PersonFamily) o;
        return id.equals(that.id) && surname.equals(that.surname) && forename.equals(that.forename) && idGroupFamily.equals(that.idGroupFamily);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, forename, idGroupFamily);
    }
}
