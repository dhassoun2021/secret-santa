package com.david.secretsanta.services.constraint;

import java.util.HashSet;
import java.util.Set;


public class SenderNotSecretSantaConstraint implements SecretSantaConstraint{


    @Override
    public Set<Long> apply(Long numberPerson ) {
        Set<Long> numbersIgnoredAfterApplyConstraint = new HashSet<>();
        numbersIgnoredAfterApplyConstraint.add(numberPerson);
        return numbersIgnoredAfterApplyConstraint;
    }
}
