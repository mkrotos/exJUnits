package com.krotos;

public class TempConverter {

    public double fahrToCels(double fahr) {
        return 5.0 / 9 * (fahr - 32);
    }

    public double celsToFahr(double cels) {
        return 9.0 / 5 * cels + 32;
    }
}
