package com.oop.ruhm2;

import java.io.IOException;
import java.util.Scanner;
public class SõneMäng {
    public static void main(String[] args) throws IOException {
        SõneLugeja sõneLugeja = new SõneLugeja("testSõnad.txt");
        Mängija mängija = new Mängija();

        System.out.println("Mängija peab ära arvama õige sõna. \n1) Suur täht tähistab äraarvatud tähte\n2) Väike täht tähistab tähte, mis on vales kohas\n3) \"_\" tähistab, et sellist tähte pole sõnas");
        SõneAnalüsaator sõneAnalüsaator = mängija.uusMäng(sõneLugeja.arvatavSõna(), 5);

        while (true) {
            Scanner sisestatudSõna = new Scanner(System.in);
            String proovitavSõna = sisestatudSõna.nextLine();
            sõneAnalüsaator.setKatse(sõneAnalüsaator.getKatse()+1);

            //Mäng lõpeb, kuna mängija arvas sõna ära
            if (sõneAnalüsaator.kontrolliVastust(proovitavSõna)) {
                mängija.setVõite(mängija.getVõite()+1);
                System.out.println("\nSõna on ära arvatud - "+sõneAnalüsaator.getÕigeVastus());
                System.out.println("Kas soovite jätkata? (ei/jah)");
                Scanner sisend = new Scanner(System.in);
                String otsus = sisend.nextLine();

                switch (otsus) {
                    case "jah" : sõneAnalüsaator = mängija.uusMäng(sõneLugeja.arvatavSõna(), 5); break;
                    case "ei" : System.out.println("võite: "+mängija.getVõite()+"; kaotusi: "+mängija.getKaotusi()+"; skoor: "+(mängija.getVõite()-mängija.getKaotusi())); return;
                    default : throw new RuntimeException("Tegevus ei ole lubatud!");
                }
            }

            //Mäng lõpeb, kuna mängijal said katsed otsa
            if (sõneAnalüsaator.getKatse() >= sõneAnalüsaator.getLubatudKatsied()) {
                mängija.setKaotusi(mängija.getKaotusi()+1);
                System.out.println("\nKatsete arv on täis. Sõna oli "+sõneAnalüsaator.getÕigeVastus());
                System.out.println("Kas soovite jätkata? (ei/jah)");
                Scanner sisend = new Scanner(System.in);
                String otsus = sisend.nextLine();

                switch (otsus) {
                    case "jah" : sõneAnalüsaator = mängija.uusMäng(sõneLugeja.arvatavSõna(), 5); break;
                    case "ei" : System.out.println("võite: "+mängija.getVõite()+"; kaotusi: "+mängija.getKaotusi()+"; skoor: "+(mängija.getVõite()-mängija.getKaotusi())); return;
                    default : throw new RuntimeException("Tegevus ei ole lubatud!");
                }
            }
        }
    }
}
