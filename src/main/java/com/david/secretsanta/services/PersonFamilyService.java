package com.david.secretsanta.services;

import com.david.secretsanta.models.PersonFamily;

import java.util.Optional;


public interface PersonFamilyService {

    PersonFamily createPersonFamily (String name,String forename,String idFamilyGroup) ;

    PersonFamily findPersonFamiliy (String idPerson);

}
