package com.david.secretsanta.util;

import java.util.Random;
import java.util.Set;

public final class RandomNumberGenerator {


    public static long generate(Set<Long> numbersToFilter) {
        long generatedLong = new Random().nextLong();
        if (numbersToFilter == null ) {
            return generatedLong;
        }
        while (numbersToFilter.contains(generatedLong)) {
            generatedLong = new Random().nextLong();
        }
        return generatedLong;
    }
}
