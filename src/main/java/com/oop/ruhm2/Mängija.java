package com.oop.ruhm2;

public class Mängija {
    private int võite;
    private int kaotusi;

    public Mängija() {
        this.võite = 0;
        this.kaotusi = 0;
    }

    public int getVõite() {
        return võite;
    }

    public void setVõite(int võite) {
        this.võite = võite;
    }

    public int getKaotusi() {
        return kaotusi;
    }

    public void setKaotusi(int kaotusi) {
        this.kaotusi = kaotusi;
    }

    public SõneAnalüsaator uusMäng(String arvatavSõna, int katseid) {
        System.out.println("Uue arvatava sõna pikkus on " + arvatavSõna.length() + " tähte.");
        return new SõneAnalüsaator(arvatavSõna,katseid);
    }
}

