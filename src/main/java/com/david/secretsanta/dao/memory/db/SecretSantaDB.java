package com.david.secretsanta.dao.memory.db;

import com.david.secretsanta.models.FamilyGroup;
import com.david.secretsanta.models.PersonFamily;

import java.util.HashMap;
import java.util.Map;

public final class SecretSantaDB {

    public final static Map<String, FamilyGroup> familyGroupDatas = new HashMap<>();

    public final static Map<String, PersonFamily> personFamilyDatas = new HashMap<>();

}
