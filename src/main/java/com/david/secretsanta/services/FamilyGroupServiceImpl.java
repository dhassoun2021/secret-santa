package com.david.secretsanta.services;

import com.david.secretsanta.dao.FamilyGroupDao;
import com.david.secretsanta.models.FamilyGroup;

import java.util.UUID;

public class FamilyGroupServiceImpl implements FamilyGroupService{

    private final FamilyGroupDao familyGroupDao;

    public FamilyGroupServiceImpl (FamilyGroupDao familyGroupDao) {
        this.familyGroupDao = familyGroupDao;
    }

    @Override
    public FamilyGroup createFamilyGroup(String name) {
        String idFamilyGroup = UUID.randomUUID().toString();
        return familyGroupDao.createFamilyGroup(new FamilyGroup(idFamilyGroup, name));
    }
}
