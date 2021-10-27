package com.david.secretsanta.services;

import com.david.secretsanta.models.PersonFamily;
import com.david.secretsanta.models.SecretSantaRelationship;
import com.david.secretsanta.util.RandomNumberGenerator;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


public class SecretSantaGeneratorImpl implements SecretSantaGenerator {

    @Override
    public Set<SecretSantaRelationship> generate(Set<PersonFamily> personFamilies) {
        checkParameter(personFamilies);
        Set<SecretSantaRelationship> secretSantaRelationships = new HashSet<>();
        Map<Long, PersonFamily> numberPersonFamily = attributeNumberToParticipant(personFamilies);
        Set<Long> numbersPersonIgnored = new HashSet<>();
        numberPersonFamily.keySet().stream().forEach(k -> {
            long numberSecretSanta = computeRandomNumberSecretSanta(k, numberPersonFamily.size(), numbersPersonIgnored);
            PersonFamily secretSanta = numberPersonFamily.get(numberSecretSanta);
            PersonFamily sender = numberPersonFamily.get(k);
            secretSantaRelationships.add(new SecretSantaRelationship(sender, secretSanta));
        });

        return secretSantaRelationships;
    }

    private void checkParameter(Set<PersonFamily> personFamilies) {
        if (personFamilies == null || personFamilies.size() < 2) {
            throw new IllegalArgumentException("Members in group should be at least 2");
        }
    }

    private Map<Long, PersonFamily> attributeNumberToParticipant(Set<PersonFamily> personFamilies) {
        Map<Long, PersonFamily> numberPersonFamily = new HashMap<>();
        AtomicLong numberAttributed = new AtomicLong(0);
        personFamilies.forEach(p -> {
            numberPersonFamily.put(numberAttributed.incrementAndGet(), p);
        });
        return numberPersonFamily;
    }

    private long _computeRandomNumberSecretSanta (Long personNumber,int maxValue, Set<Long> numbersPersonIgnored ) {
        long maybeLastValue = personNumber + 1;
        if (maybeLastValue == maxValue) {
            if (!numbersPersonIgnored .contains(maybeLastValue)) {
                return maybeLastValue;
            }
        }
        return RandomNumberGenerator.generate(numbersPersonIgnored ,maxValue);
    }

    private long computeRandomNumberSecretSanta(Long personNumber,int maxValue, Set<Long> numbersPersonIgnored ) {
        boolean isPersonProcessingSanta = false;
        if (numbersPersonIgnored .contains(personNumber)) {
            isPersonProcessingSanta = true;
        } else {
            numbersPersonIgnored .add(personNumber);
        }
        long numberPersonSanta = _computeRandomNumberSecretSanta(personNumber,maxValue,numbersPersonIgnored );
        if (!isPersonProcessingSanta) {
            numbersPersonIgnored .remove(personNumber);
        }
        numbersPersonIgnored .add(numberPersonSanta);
        return numberPersonSanta;
    }
}
