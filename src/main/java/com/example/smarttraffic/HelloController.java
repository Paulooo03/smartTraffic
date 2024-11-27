package com.example.smarttraffic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;

import java.util.Arrays;

public class HelloController {

    @FXML
    private Button getRoute;
    @FXML
    private MenuItem sixam, eightam, tenam, threepm, fivepm, sevenpm;
    @FXML
    private Circle pointA, pointB;
    @FXML
    private Circle LaSalle, Novaliches;
    @FXML
    private Polyline quirino, ortigas, taft, rodriguez, quezon, araneta, mesa, domingo;

    //creating of road info
    //let quirino = q, let ortigas = o, let taft = t, let rodriquez = r, let araneta = a, let mesa = m, let domingo = d, let road = {distance, traffic lights}

    int[] q = {3500, 7, 0};
    int[] o = {12000, 5, 0};
    int[] t = {8900, 8, 0};
    int[] r = {5000, 4, 0};
    int[] a = {6300, 9, 0};
    int[] m = {10000, 11, 0};
    int[] d = {6000, 4, 0};
    int[] qz = {7000, 6, 0};

    int[][] routes = {q, o, t, r, a, m, d, qz};
    private Polyline[] polylines;

    @FXML
    public void initialize() {
        polylines = new Polyline[]{quirino, ortigas, taft, rodriguez, araneta, mesa, domingo, quezon};
    }

    public void onClicksix(ActionEvent actionEvent) {
        q[2] = 60;
        o[2] = 50;
        t[2] = 80;
        r[2] = 50;
        a[2] = 70;
        m[2] = 45;
        d[2] = 60;
        qz[2] = 70;
    }

    public void onClickeightam(ActionEvent actionEvent) {
        q[2] = 70;
        o[2] = 60;
        t[2] = 85;
        r[2] = 65;
        a[2] = 65;
        m[2] = 55;
        d[2] = 70;
        qz[2] = 75;
    }

    public void onClicktenam(ActionEvent actionEvent) {
        q[2] = 30;
        o[2] = 40;
        t[2] = 20;
        r[2] = 30;
        a[2] = 40;
        m[2] = 25;
        d[2] = 30;
        qz[2] = 20;
    }

    public void onClickthreepm(ActionEvent actionEvent) {
        q[2] = 50;
        o[2] = 40;
        t[2] = 65;
        r[2] = 45;
        a[2] = 50;
        m[2] = 75;
        d[2] = 55;
        qz[2] = 30;
    }

    public void onClickfivepm(ActionEvent actionEvent) {
        q[2] = 75;
        o[2] = 80;
        t[2] = 75;
        r[2] = 80;
        a[2] = 65;
        m[2] = 55;
        d[2] = 70;
        qz[2] = 50;
    }

    public void onClicksevenpm(ActionEvent actionEvent) {
        q[2] = 80;
        o[2] = 45;
        t[2] = 76;
        r[2] = 60;
        a[2] = 60;
        m[2] = 55;
        d[2] = 75;
        qz[2] = 80;
    }

    //main algorithm starts here
    public void getRoute(ActionEvent actionEvent) {
        double distanceMean = (double) (q[0] + o[0] + t[0] + r[0] + a[0] + m[0] + d[0] + qz[0]) / 7;
        double lightsMean = (double) (q[1] + o[1] + t[1] + r[1] + a[1] + m[1] + d[1] + qz[1]) / 7;
        double trafficMean = (double) (q[2] + o[2] + t[2] + r[2] + a[2] + m[2] + d[2]+ qz[2]) / 7;

        // Reset all polyline colors first to avoid keeping any previous highlight
        for (Polyline polyline : polylines) {
            polyline.setStroke(Color.BLACK);  // Set default color (black) or any other color
        }

        for (int i = 0; i < 8; i++) {
            if (routes[i][0] >= distanceMean && routes[i][1] >= lightsMean && routes[i][2] >= trafficMean) {
                polylines[i].setStroke(Color.RED);  // Highlight A (or any other color)
            } else if (routes[i][0] < distanceMean && routes[i][1] >= lightsMean && routes[i][2] >= trafficMean) {
                polylines[i].setStroke(Color.GREEN);  // Highlight B
            } else if (routes[i][0] >= distanceMean && routes[i][1] >= lightsMean && routes[i][2] < trafficMean) {
                polylines[i].setStroke(Color.YELLOW);  // Highlight C
            } else if (routes[i][0] >= distanceMean && routes[i][1] < lightsMean && routes[i][2] >= trafficMean) {
                polylines[i].setStroke(Color.BLUE);  // Highlight D
            } else if (routes[i][0] < distanceMean && routes[i][1] < lightsMean && routes[i][2] >= trafficMean) {
                polylines[i].setStroke(Color.PURPLE);  // Highlight E
            } else if (routes[i][0] < distanceMean && routes[i][1] >= lightsMean && routes[i][2] < trafficMean) {
                polylines[i].setStroke(Color.ORANGE);  // Highlight F
            } else if (routes[i][0] >= distanceMean && routes[i][1] < lightsMean && routes[i][2] < trafficMean) {
                polylines[i].setStroke(Color.CYAN);  // Highlight G
            } else if (routes[i][0] < distanceMean && routes[i][1] < lightsMean && routes[i][2] < trafficMean) {
                polylines[i].setStroke(Color.BLACK);  // Highlight H
            }
        }
    }
}
