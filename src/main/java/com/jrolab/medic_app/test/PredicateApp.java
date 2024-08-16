package com.jrolab.medic_app.test;

import java.util.function.Predicate;

public class PredicateApp {
    public static void main(String[] args) {
        Predicate<Integer> checkAgeNumber = x -> x >= 18;

        System.out.println(checkAgeNumber.test(32));
        System.out.println(checkAgeNumber.test(17));
    }

}
