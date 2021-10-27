package com.david.secretsanta.services.constraint;

import java.util.Set;
import java.util.stream.Collectors;

public class SenderNotSecretSantaConstraint implements SecretSantaConstraint{


    @Override
    public Set<Long> apply(Long numberPerson,Set<Long> numbersIgnored ) {
        Set<Long> numbersIgnoredAfterApplyConstraint = numbersIgnored.stream().collect(Collectors.toSet());
        numbersIgnoredAfterApplyConstraint.add(numberPerson);
        return numbersIgnoredAfterApplyConstraint;
    }
}
