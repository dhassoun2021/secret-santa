package com.david.secretsanta.services;

import com.david.secretsanta.models.PersonFamily;
import com.david.secretsanta.models.SecretSantaRelationship;
import com.david.secretsanta.services.constraint.SecretSantaConstraint;
import com.david.secretsanta.services.constraint.SenderNotSecretSantaConstraint;
import com.david.secretsanta.util.RandomNumberGenerator;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


public class SecretSantaGeneratorImpl implements SecretSantaGenerator {

    @Override
    public Set<SecretSantaRelationship> generate(Set<PersonFamily> personFamilies) {
        checkParameter(personFamilies);
        final Set<SecretSantaRelationship> secretSantaRelationships = new HashSet<>();
        final Map<Long, PersonFamily> numberPersonFamily = attributeNumberToParticipant(personFamilies);
        final Set<Long> numbersPersonIgnored = new HashSet<>();

        final List <SecretSantaConstraint> constraints = new ArrayList<>();
        constraints.add(new SenderNotSecretSantaConstraint());
        numberPersonFamily.keySet().stream().forEach(k -> {
            long numberSecretSanta = computeRandomNumberSecretSanta(k, numberPersonFamily.size(), numbersPersonIgnored,constraints);
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

    private long _computeRandomNumberSecretSanta(Long personNumber, int maxValue, Set<Long> numbersPersonIgnored) {
        // if person processing is avant dernier, if last person number was not choosen as secret Santa
        // this last person number should be choosen for avant dernier person
        long maybeLastValue = personNumber + 1;
        if (maybeLastValue == maxValue) {
            if (!numbersPersonIgnored.contains(maybeLastValue)) {
                return maybeLastValue;
            }
        }
        return RandomNumberGenerator.generate(numbersPersonIgnored, maxValue);
    }

    /**
     * For each person number we choose randomly a number corresponding to a secret santa
     * @param personNumber personNumber of person which will be affected to a secret santa
     * @param maxValue maxValue of number to be generate randomly
     * @param numbersPersonIgnored set of number will be ignored for generate number randomly
     * @param constraints list of constraint to apply
     * @return number corresponding to secret santa choosen
     */
    private long computeRandomNumberSecretSanta(Long personNumber, int maxValue, Set<Long> numbersPersonIgnored,List <SecretSantaConstraint> constraints) {
        Set<Long> numbersIgnoredAfterApplyConstraint = numbersPersonIgnored.stream().collect(Collectors.toSet());
        constraints.stream().forEach(c->numbersIgnoredAfterApplyConstraint.addAll(c.apply(personNumber)));
        long numberPersonSanta = _computeRandomNumberSecretSanta(personNumber, maxValue, numbersIgnoredAfterApplyConstraint);
        numbersPersonIgnored.add(numberPersonSanta);
        return numberPersonSanta;
    }
}
