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
            long numberSecretSanta = getSecretsSanta(k, numberPersonFamily.size(), numberNotToBeProcessed);
            PersonFamily secretSanta = numberPersonFamily.get(numberSecretSanta);
            PersonFamily sender = numberPersonFamily.get(k);
            secretSantaRelationships.add(new SecretSantaRelationship(sender, secretSanta));
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

    private long computeRandomNumberSecretSanta (Long personNumber,int maxValue, Set<Long> numberNotToBeProcessed) {
        long maybeLastValue = personNumber + 1;
        if (maybeLastValue == maxValue) {
            if (!numberNotToBeProcessed.contains(maybeLastValue)) {
                return maybeLastValue;
            }
        }
        return RandomNumberGenerator.generate(numberNotToBeProcessed,maxValue);
    }

    private long getSecretsSanta(Long personNumber,int maxValue, Set<Long> numberNotToBeProcessed) {
        boolean isPersonProcessingSanta = false;
        if (numberNotToBeProcessed.contains(personNumber)) {
            isPersonProcessingSanta = true;
        } else {
            numberNotToBeProcessed.add(personNumber);
        }
        long numberPersonSanta = computeRandomNumberSecretSanta(personNumber,maxValue,numberNotToBeProcessed);
        if (!isPersonProcessingSanta) {
            numberNotToBeProcessed.remove(personNumber);
        }
        numberNotToBeProcessed.add(numberPersonSanta);
        return numberPersonSanta;
    }
}
