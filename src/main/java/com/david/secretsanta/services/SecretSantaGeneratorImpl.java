package com.david.secretsanta.services;

import com.david.secretsanta.models.PersonFamily;
import com.david.secretsanta.models.SecretSantaRelationship;
import com.david.secretsanta.util.RandomNumberGenerator;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


public class SecretSantaGeneratorImpl implements SecretSantaGenerator {

    @Override
    public Set<SecretSantaRelationship> generate(Set<PersonFamily> personFamilies) {
        Set<SecretSantaRelationship> secretSantaRelationships = new HashSet<>();
        Map<Long, PersonFamily> numberPersonFamily = attributeNumberToParticipant(personFamilies);
        Set<Long> numberNotToBeProcessed = new HashSet<>();
        numberPersonFamily.keySet().stream().forEach(k -> {
            PersonFamily personFamilyRecipient = getSecretsSanta(k, numberPersonFamily, numberNotToBeProcessed);
            PersonFamily secretSanta = numberPersonFamily.get(k);
            secretSantaRelationships.add(new SecretSantaRelationship(personFamilyRecipient, secretSanta));
        });

        return secretSantaRelationships;
    }

    private Map<Long, PersonFamily> attributeNumberToParticipant(Set<PersonFamily> personFamilies) {
        Map<Long, PersonFamily> numberPersonFamily = new HashMap<>();
        AtomicLong numberAttributed = new AtomicLong(0);
        personFamilies.forEach(p -> {
            numberPersonFamily.put(numberAttributed.incrementAndGet(), p);
        });
        return numberPersonFamily;
    }

    private PersonFamily getSecretsSanta(Long personNumber, Map<Long, PersonFamily> numberPersonFamily, Set<Long> numberNotToBeProcessed) {
        numberNotToBeProcessed.add(personNumber);
        long numberPerson = RandomNumberGenerator.generate(numberNotToBeProcessed);
        numberNotToBeProcessed.add(numberPerson);
        return numberPersonFamily.get(numberPerson);
    }
}
