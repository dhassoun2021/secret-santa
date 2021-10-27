package com.david.secretsanta.services;

import com.david.secretsanta.models.PersonFamily;
import com.david.secretsanta.models.SecretSantaRelationship;

import java.util.Set;

public interface SecretSantaGenerator {

    Set<SecretSantaRelationship> generate (Set <PersonFamily> personFamilies);
}
