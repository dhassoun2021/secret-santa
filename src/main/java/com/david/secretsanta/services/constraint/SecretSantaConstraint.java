package com.david.secretsanta.services.constraint;

import java.util.Set;

public interface SecretSantaConstraint {


    Set<Long> apply(Long numberPerson);
}
