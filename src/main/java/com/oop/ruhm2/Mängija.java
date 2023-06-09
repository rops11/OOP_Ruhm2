package com.oop.ruhm2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mängija {
    private int võite;
    private int kaotusi;

    public Mängija() throws FileNotFoundException {
        File file = new File("skoor.txt");
        Scanner reader = new Scanner(file);
        String rida = reader.nextLine();
        String[] ridaTykeldatud = rida.split(" ");
        if (ridaTykeldatud.length == 2){
            this.võite = Integer.parseInt(ridaTykeldatud[0]);
            this.kaotusi = Integer.parseInt(ridaTykeldatud[1]);
        } else {
            throw new FailiErind("Peab olema 2 värtust skoor.txt failis");
        }
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

    public int skoor(){
        return (this.võite - this.kaotusi);
    }

    public SõneAnalüsaator uusMäng(String arvatavSõna, int katseid) {
        //System.out.println("Uue arvatava sõna pikkus on " + arvatavSõna.length() + " tähte.");
        return new SõneAnalüsaator(arvatavSõna,katseid);
    }
}

