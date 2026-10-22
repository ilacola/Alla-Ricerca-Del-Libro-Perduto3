package it.unicam.cs.mpgc.rpg129638.view;

import it.unicam.cs.mpgc.rpg129638.model.Stanza;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SchermataGioco extends VBox {

    public SchermataGioco(Stanza stanza, Runnable azioneTornaAlMenu) {
        Label titolo = new Label("Stanza 1");
        titolo.getStyleClass().add("titolo");

        Button tornaAlMenu = new Button("Menu");
        tornaAlMenu.setOnAction(evento -> azioneTornaAlMenu.run());

        setAlignment(Pos.CENTER);
        setSpacing(20);
        getChildren().addAll(titolo, new VistaStanza(stanza), tornaAlMenu);
    }
}