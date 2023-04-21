package com.oop.ruhm2;

import java.util.HashMap;
import java.util.Map;

public class SõneAnalüsaator {
    //Insediväljad
    private String õigeVastus;
    private int lubatudKatsied;
    private int katse;

    //Konstruktor
    public SõneAnalüsaator(String õigeVastus, int lubatudKatsied) {
        this.õigeVastus = õigeVastus.toLowerCase();
        this.lubatudKatsied = lubatudKatsied;
        this.katse = 0;
    }

    //Get
    public String getÕigeVastus() {
        return õigeVastus;
    }

    public int getLubatudKatsied() {
        return lubatudKatsied;
    }

    public int getKatse() {
        return katse;
    }

    //Set
    public void setKatse(int katse) {
        this.katse = katse;
    }

    //Meetodid
    public Map<Character, Integer> unikaalseteSümboliteArv(String sõne) {
        //loeme kokku mitu erinevat tähte sõnes esineb
        HashMap<Character, Integer> tagasta = new HashMap<>();
        for (int i = 0; i < sõne.length(); i++) {
            char täht = sõne.charAt(i);
            if (tagasta.containsKey(täht)) {
                tagasta.put(täht, tagasta.get(täht) + 1);
            } else {
                tagasta.put(täht, 1);
            }
        }

        return tagasta;
    }

    public String annaVihje(String pakkumine) {
        //Kontrollime, mis tähed õigesti arvati
        String vihje = "";
        String[] tähedpakkumine = pakkumine.split("");
        String[] tähedÕigeVastus = õigeVastus.split("");
        Map<Character, Integer> uniaalseteSümboliteArvVastus = unikaalseteSümboliteArv(õigeVastus);

        //Kuvab suurt tähte, kui täht on õiges kohas
        //Kuvab väikest tähte, kui sisaldab tähte, aga täht on vales kohas
        //Kuvab "_", kui tähte ei ole üldse sõnas või samu tähti on üleliia.
        for (int i = 0; i < tähedpakkumine.length; i++) {
            char otsitavSümbol = tähedpakkumine[i].charAt(0);

            if (tähedpakkumine[i].equals(tähedÕigeVastus[i])) {
                vihje += tähedpakkumine[i].toUpperCase();
                uniaalseteSümboliteArvVastus.put(otsitavSümbol, uniaalseteSümboliteArvVastus.get(otsitavSümbol) - 1);
                if (uniaalseteSümboliteArvVastus.get(otsitavSümbol) < 1) {
                    uniaalseteSümboliteArvVastus.remove(otsitavSümbol);
                }
            }
            else if (uniaalseteSümboliteArvVastus.containsKey(otsitavSümbol)) {
                vihje += tähedpakkumine[i].toLowerCase();
                uniaalseteSümboliteArvVastus.put(otsitavSümbol, uniaalseteSümboliteArvVastus.get(otsitavSümbol) - 1);
                if (uniaalseteSümboliteArvVastus.get(otsitavSümbol) < 1) {
                    uniaalseteSümboliteArvVastus.remove(otsitavSümbol);
                }
            }
            else {
                vihje += "_";
            }
        }

        //System.out.println(vihje);
        return vihje;
    }
    public int kontrolliVastust(String pakkumine) {
        /*
        -1 vale sisend
        0 vale vastus
        1 õige vastus
         */

        //ei pea oluliseks, kas mängija sisestab suuri või väikeseid tähti
        pakkumine = pakkumine.toLowerCase();

        //Kontrollime, et sõne pikkus oleks sama, mis vastusel
        if (õigeVastus.length() != pakkumine.length()) {
            System.out.println("Sõne pikkus ei klapi (oodati " + õigeVastus.length() + " tähte, saadi " + pakkumine.length() + " tähte)");
            return -1;
        }

        //Kontrollime, kas vastati õigesti
        if (pakkumine.equals(õigeVastus)) {
            return 1; //Sõna on ära arvatud
        }

        return 0; //Sõna ei ole ära arvatud
    }
}

