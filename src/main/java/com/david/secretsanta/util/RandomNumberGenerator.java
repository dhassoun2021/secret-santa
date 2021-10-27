package com.david.secretsanta.util;

import java.util.Optional;
import java.util.Random;
import java.util.Set;

public final class RandomNumberGenerator {


    public static long generate(Set<Long> numbersToFilter, int maxValueRandom) {
        Random random = new Random();
        int limitValue = maxValueRandom + 1;
        long generatedLong = random.longs(1, limitValue).findFirst().getAsLong();
        //long generatedLong = new Random().longs(1,3).n;
        if (numbersToFilter == null) {
            return generatedLong;
        }
        while (numbersToFilter.contains(generatedLong)) {
            random = new Random();
            generatedLong = random.longs(1, limitValue).findFirst().getAsLong();
        }
        return generatedLong;
    }
}
