package com.david.secretsanta.services;

import com.david.secretsanta.dao.PersonFamilyDao;
import com.david.secretsanta.models.PersonFamily;

import java.util.UUID;

public class PersonFamilyServiceImpl implements  PersonFamilyService {

    private final PersonFamilyDao personFamilyDao;

    public PersonFamilyServiceImpl (PersonFamilyDao personFamilyDao) {
        this.personFamilyDao = personFamilyDao;
    }

    @Override
    public PersonFamily createPersonFamily(String name, String forename, String idFamilyGroup) {
        String idPerson = UUID.randomUUID().toString();
        return personFamilyDao.createPersonFamily(new PersonFamily(idPerson,name,forename,idFamilyGroup));
    }

    @Override
    public PersonFamily findPersonFamiliy(String idPerson) {
       PersonFamily personFamily = personFamilyDao.findPersonFamiliy(idPerson);
       return  personFamily;
    }
}
