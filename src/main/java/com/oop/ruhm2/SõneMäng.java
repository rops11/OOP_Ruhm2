package com.oop.ruhm2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class SõneMäng extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static void mängi(Mängija mängija, SõneLugeja sõneLugeja, SõneAnalüsaator sõneAnalüsaator, String proovitavSõna, List<HBox> arvamisKastid) throws IOException {
        String vihje = sõneAnalüsaator.annaVihje(proovitavSõna);

        //Mäng lõpeb, kuna mängija arvas sõna ära
        switch (sõneAnalüsaator.kontrolliVastust(proovitavSõna)) {
            case 0 : {
                String[] tükid = vihje.split("");
                for (int i = 0; i < tükid.length; i++) {
                    char täht = tükid[i].charAt(0);
                    char pärisTäht = proovitavSõna.toUpperCase().charAt(i);
                    //System.out.println(tükid[i]);
                    StackPane täheKast = (StackPane) arvamisKastid.get(sõneAnalüsaator.getKatse()).getChildren().get(i);
                    Rectangle kast = (Rectangle) täheKast.getChildren().get(0);
                    Text tekst = (Text) täheKast.getChildren().get(1);
                    tekst.setText(Character.toString(pärisTäht));
                    if (täht == '_') {
                        kast.setFill(Color.rgb(59,59,59));
                    } else if (Character.isUpperCase(täht)) {
                        kast.setFill(Color.rgb(201,159,25));
                    } else if (Character.isLowerCase(täht)) {
                        kast.setFill(Color.rgb(25, 201, 25));
                    }
                }
                System.out.println("vale");
                break;
            }
            case 1 : {
                mängija.setVõite(mängija.getVõite()+1);
                System.out.println("võit");
                break;
            }
            default : {
                break;
            }
        }

        sõneAnalüsaator.setKatse(sõneAnalüsaator.getKatse()+1);

        /*if (sõneAnalüsaator.kontrolliVastust(proovitavSõna) == 1) {
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
        }*/

        /*//Mäng lõpeb, kuna mängijal said katsed otsa
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
        }*/
    }
    @Override
    public void start(Stage stage) throws IOException {
        int lubatuidKatsied = 5;

        SõneLugeja sõneLugeja = new SõneLugeja("testSõnad.txt");
        Mängija mängija = new Mängija();

        //System.out.println("Mängija peab ära arvama õige sõna. \n1) Suur täht tähistab äraarvatud tähte\n2) Väike täht tähistab tähte, mis on vales kohas\n3) \"_\" tähistab, et sellist tähte pole sõnas");
        SõneAnalüsaator sõneAnalüsaator = mängija.uusMäng(sõneLugeja.arvatavSõna(), lubatuidKatsied);

        /*Group group = new Group();

        Scene scene = new Scene(group, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/

        //Kõikide arvamiste paigutus ülevalt alla
        VBox kõikArvamisedKast = new VBox();
        kõikArvamisedKast.setSpacing(2);

        //Arvamise paigutus horisontaalne
        List<HBox> arvamisKastid = new ArrayList<>();
        for (int i = 0; i < lubatuidKatsied; i++) {
            HBox arvamiseKast = new HBox();
            arvamiseKast.setSpacing(2);

            for (int j = 0; j < sõneAnalüsaator.getÕigeVastus().length(); j++) {
                Rectangle kast = new Rectangle(50,50);
                kast.setFill(Color.TRANSPARENT);
                kast.setStroke(Color.rgb(59,59,59));
                kast.setStrokeWidth(2);

                Text tekst = new Text("");
                tekst.setFont(Font.font("Arial", FontWeight.BOLD, 30));
                tekst.setFill(Color.rgb(255,245,245));

                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(kast, tekst);

                arvamiseKast.getChildren().add(stackPane);
            }

            kõikArvamisedKast.getChildren().add(arvamiseKast);
            arvamisKastid.add(arvamiseKast);
        }

        //Tekitame sisestuskasti:
        TextField tekstiRida = new TextField();
        tekstiRida.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String tekst = tekstiRida.getText();
                try {
                    mängi(mängija, sõneLugeja, sõneAnalüsaator, tekst, arvamisKastid);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                tekstiRida.setText("");
            }
        });

        BorderPane juurpaigutus = new BorderPane();
        juurpaigutus.setTop(kõikArvamisedKast);
        //juurpaigutus.setLeft(vasemadNupud);
        //juurpaigutus.setRight(paremadNupud);
        juurpaigutus.setBottom(tekstiRida);

        // Koostame üldise aknastruktuuri:
        Scene scene = new Scene(juurpaigutus);
        scene.setFill(Color.rgb(25,25,25));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Sõna Mäng");
        stage.show();

        /*while (true) {
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
        }*/
    }
}
