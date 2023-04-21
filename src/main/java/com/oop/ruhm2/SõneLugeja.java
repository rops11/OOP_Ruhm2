package com.oop.ruhm2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class SõneLugeja {
    private String failitee;

    public SõneLugeja(String failitee){
        this.failitee = failitee;
    }

    public String[] loeFail() throws IOException {
        //Tagastame kõik sõnad, mida mängus kasutame
        List<String> read = Files.readAllLines(Path.of(failitee), StandardCharsets.UTF_8);
        String[] sõnadeMassiiv = new String[read.size()];
        for (int i = 0; i < sõnadeMassiiv.length; i++) {
            sõnadeMassiiv[i] = read.get(i);
        }
        return sõnadeMassiiv;
    }
    public String arvatavSõna() throws IOException {
        //Valime suvaliselt uue sõna, mida peab mängija ära arvama
        String[] sõnadeMassiiv = loeFail();
        Random random = new Random();
        int suvalineArv = random.nextInt(0,sõnadeMassiiv.length);
        return sõnadeMassiiv[suvalineArv];
    }

}

