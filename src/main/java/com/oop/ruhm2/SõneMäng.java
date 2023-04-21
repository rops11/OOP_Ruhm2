package com.oop.ruhm2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class SõneMäng extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static int mängi(Mängija mängija, SõneLugeja sõneLugeja, SõneAnalüsaator sõneAnalüsaator, String proovitavSõna, List<HBox> arvamisKastid, TextField tekstiRida, Label teade) throws IOException {
        String vihje = sõneAnalüsaator.annaVihje(proovitavSõna);
        int tulemus = sõneAnalüsaator.kontrolliVastust(proovitavSõna);

        if (tulemus == -1) {
            return -1;
        }

        String[] tükid = vihje.split("");
        for (int i = 0; i < tükid.length; i++) {
            char täht = tükid[i].charAt(0);
            char pärisTäht = proovitavSõna.toUpperCase().charAt(i);

            StackPane täheKast = (StackPane) arvamisKastid.get(sõneAnalüsaator.getKatse()).getChildren().get(i);
            Rectangle kast = (Rectangle) täheKast.getChildren().get(0);
            Text tekst = (Text) täheKast.getChildren().get(1);

            tekst.setText(Character.toString(pärisTäht));
            if (täht == '_') {
                kast.setFill(Color.rgb(59,59,59));
            } else if (Character.isLowerCase(täht)) {
                kast.setFill(Color.rgb(201,159,25));
            } else if (Character.isUpperCase(täht)) {
                kast.setFill(Color.rgb(25, 201, 25));
            }
        }

        switch (tulemus) {
            case 0 : {
                teade.setText("Vale vastus arva uuesti");
                break;
            }
            case 1 : {
                mängija.setVõite(mängija.getVõite()+1);
                teade.setText("Õige vastus! " + "\nKas soovite jätkata? (jah/ei)");
                return 1;
            }
            default : {
                break;
            }
        }

        sõneAnalüsaator.setKatse(sõneAnalüsaator.getKatse()+1);

        if (sõneAnalüsaator.getKatse() >= sõneAnalüsaator.getLubatudKatsied()) {
            mängija.setKaotusi(mängija.getKaotusi()+1);
            teade.setText("Katsete arv on täis. Sõna oli " + sõneAnalüsaator.getÕigeVastus()
                + "\nKas soovite jätkata? (jah/ei)");
            return 1;
        }

        return 0;
    }

    public static List<HBox> uusMänguväli(BorderPane juurpaigutus,int lubatuidKatsied, int vastusePikkus) {
        //Kõikide arvamiste paigutus ülevalt alla
        VBox kõikArvamisedKast = new VBox();
        kõikArvamisedKast.setSpacing(2);
        kõikArvamisedKast.setAlignment(Pos.BOTTOM_CENTER);

        List<HBox> arvamisKastid = new ArrayList<>();
        for (int i = 0; i < lubatuidKatsied; i++) {
            HBox arvamiseKast = new HBox();
            arvamiseKast.setSpacing(2);

            for (int j = 0; j < vastusePikkus; j++) {
                Rectangle kast = new Rectangle(50,50);
                kast.setFill(Color.TRANSPARENT);
                kast.setStroke(Color.rgb(59,59,59));
                kast.setStrokeWidth(2);

                Text tekst = new Text("");
                tekst.setFont(Font.font("Arial", FontWeight.BOLD, 30));
                tekst.setFill(Color.rgb(255,255,255));

                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(kast, tekst);

                arvamiseKast.getChildren().add(stackPane);
            }

            kõikArvamisedKast.getChildren().add(arvamiseKast);
            arvamisKastid.add(arvamiseKast);
        }

        juurpaigutus.setCenter(kõikArvamisedKast);

        return arvamisKastid;
    }
    @Override
    public void start(Stage stage) throws IOException {
        int lubatuidKatsied = 5;

        SõneLugeja sõneLugeja = new SõneLugeja("testSõnad.txt");
        Mängija mängija = new Mängija();
        AtomicReference<SõneAnalüsaator> sõneAnalüsaator = new AtomicReference<>(mängija.uusMäng(sõneLugeja.arvatavSõna(), lubatuidKatsied));
        AtomicBoolean mängKäib = new AtomicBoolean(true);

        //Tekitame teate paneeli
        Label teade = new Label("Alusta sõna arvamist!\n ");
        teade.setTextAlignment(TextAlignment.CENTER);
        teade.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        StackPane teatePaneel = new StackPane();
        teatePaneel.getChildren().add(teade);

        //Tekitame juur paigutuse ja mänguvälja
        BorderPane juurpaigutus = new BorderPane();
        final List<HBox>[] arvamisKastid = new List[]{uusMänguväli(juurpaigutus, lubatuidKatsied, sõneAnalüsaator.get().getÕigeVastus().length())};

        //Tekitame sisestuskasti:
        TextField tekstiRida = new TextField();
        tekstiRida.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String tekst = tekstiRida.getText();
                try {
                    if (mängKäib.get() == true) {
                        if (mängi(mängija, sõneLugeja, sõneAnalüsaator.get(), tekst, arvamisKastid[0], tekstiRida, teade) == 1) {
                            mängKäib.set(false);
                        };
                    } else {
                        if (tekst.equals("jah")) {
                            sõneAnalüsaator.set(mängija.uusMäng(sõneLugeja.arvatavSõna(), 5));
                            arvamisKastid[0] = uusMänguväli(juurpaigutus, lubatuidKatsied, sõneAnalüsaator.get().getÕigeVastus().length());
                            stage.sizeToScene();
                            mängKäib.set(true);
                        } else if (tekst.equals("ei")) {
                            teade.setText("Võite ristist mängu sulgeda \ntulemus on salvestatud");
                            tekstiRida.setDisable(true);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                tekstiRida.setText("");
            }
        });

        juurpaigutus.setTop(teatePaneel);
        juurpaigutus.setBottom(tekstiRida);

        // Koostame üldise aknastruktuuri:
        Scene scene = new Scene(juurpaigutus);
        scene.setFill(Color.rgb(25,25,25));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Sõna Mäng");
        stage.show();
    }
}
