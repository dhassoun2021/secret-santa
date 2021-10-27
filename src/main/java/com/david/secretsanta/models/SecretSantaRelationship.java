package com.david.secretsanta.models;

import java.util.Objects;

public class SecretSantaRelationship {

    private final PersonFamily personFamily;
    private final PersonFamily secretSanta;

    public SecretSantaRelationship(PersonFamily personFamily, PersonFamily secretSanta) {
        this.personFamily = personFamily;
        this.secretSanta = secretSanta;
    }

    public PersonFamily getPersonFamily() {
        return personFamily;
    }

    public PersonFamily getSecretSanta() {
        return secretSanta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecretSantaRelationship that = (SecretSantaRelationship) o;
        return Objects.equals(personFamily, that.personFamily) && Objects.equals(secretSanta, that.secretSanta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personFamily, secretSanta);
    }

    @Override
    public String toString() {
        return "SecretSantaRelationship{" +
                "personFamily=" + personFamily +
                ", secretSanta=" + secretSanta +
                '}';
    }
}
