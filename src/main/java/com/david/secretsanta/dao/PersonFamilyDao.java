package com.david.secretsanta.dao;

import com.david.secretsanta.models.PersonFamily;

import java.util.Optional;
import java.util.Set;

public interface PersonFamilyDao {

    PersonFamily createPersonFamily (PersonFamily personFamily);

    Set<PersonFamily> getAllMembersFamily (String idGroupFamily);

    PersonFamily findPersonFamiliy (String idPerson);
}
