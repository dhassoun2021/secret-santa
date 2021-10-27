package com.david.secretsanta.services;

import com.david.secretsanta.models.PersonFamily;
import com.david.secretsanta.models.SecretSantaRelationship;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TestSecretSantaGenerator {

    @Test
    public void generateSecretSanta () {
        Set<PersonFamily> persons = new HashSet<>();
        persons.add(new PersonFamily("1","MARTIN","Philippe","1"));
        persons.add(new PersonFamily("2","MARTIN","Jean","1"));
        persons.add(new PersonFamily("3","MARTIN","Paul","1"));
        SecretSantaGenerator secretSantaGenerator = new SecretSantaGeneratorImpl();
        Set<SecretSantaRelationship> secretSantaRelationships = secretSantaGenerator.generate(persons);
        Assert.assertNotNull(secretSantaRelationships);
        Assert.assertEquals(3,secretSantaRelationships.size());

        //check each person is not its own secret santa
        assertEachPersonIsNotItsOwnSecretSanta(secretSantaRelationships);

        //check secret santa are distincts
        assertSecretSantaAreDistincts(secretSantaRelationships);
    }

    private void assertEachPersonIsNotItsOwnSecretSanta (Set<SecretSantaRelationship> secretSantaRelationships) {
        Assert.assertEquals(0,secretSantaRelationships.stream().filter(secretSantaRelationship -> secretSantaRelationship.getPersonFamily().equals(secretSantaRelationship.getSecretSanta())).count());
    }

    private void assertSecretSantaAreDistincts (Set<SecretSantaRelationship> secretSantaRelationships) {
        Set <String> idsSanta = secretSantaRelationships.stream().map(secretSantaRelationship -> secretSantaRelationship.getSecretSanta().getId()).collect(Collectors.toSet());
        Assert.assertEquals(secretSantaRelationships.size(),idsSanta.size());
    }
}
