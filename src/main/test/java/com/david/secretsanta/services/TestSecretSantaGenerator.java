package com.david.secretsanta.services;

import com.david.secretsanta.models.PersonFamily;
import com.david.secretsanta.models.SecretSantaRelationship;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TestSecretSantaGenerator {

    @Test
    public void generateSecretSanta () {
        Set<PersonFamily> persons = new HashSet<>();
        persons.add(new PersonFamily("1","MARTIN","Philippe","1"));
        persons.add(new PersonFamily("2","MARTIN","Jean","1"));
        persons.add(new PersonFamily("3","MARTIN","Paul","1"));
        SecretSantaGenerator secretSantaGenerator = new SecretSantaGeneratorImpl();
        Set<SecretSantaRelationship> secretSantaRelationships = secretSantaGenerator.generate(persons);

    }
}
