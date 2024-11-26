package com.example.smarttraffic;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.*;

public class HelloController {

    @FXML
    private Line qp, pl, lj, ht, ph, ta, a1t, ya1, zy, sz, va1, qv, hj, vh, zv, sq, aj;
    @FXML
    private Circle aa, hh, jj, ll, pp, qq, ss, tt, vv, yy, zz, a1;

    private HashMap<String, String[]> relations;
    private String firstClickedCircle = null;
    private String secondClickedCircle = null;

    public HelloController() {
        relations = new HashMap<>();
        relations.put("aa", new String[]{"aj", "ta"});
        relations.put("hh", new String[]{"hj", "ph", "ht", "vh"});
        relations.put("jj", new String[]{"aj", "hj", "lj"});
        relations.put("ll", new String[]{"lj", "pl"});
        relations.put("pp", new String[]{"ph", "pl", "qp"});
        relations.put("qq", new String[]{"qp", "sq", "qv"});
        relations.put("ss", new String[]{"sq", "sz"});
        relations.put("tt", new String[]{"ht", "a1t"});
        relations.put("vv", new String[]{"vh", "qv", "zv", "va1"});
        relations.put("yy", new String[]{"zy", "ya1"});
        relations.put("zz", new String[]{"sz", "zv", "zy"});
        relations.put("a1", new String[]{"a1t", "va1", "ya1"});
    }

    public void aaClicked(MouseEvent mouseEvent) { handleCircleClick("aa"); }
    public void hhClicked(MouseEvent mouseEvent) { handleCircleClick("hh"); }
    public void jjClicked(MouseEvent mouseEvent) { handleCircleClick("jj"); }
    public void llClicked(MouseEvent mouseEvent) { handleCircleClick("ll"); }
    public void ppClicked(MouseEvent mouseEvent) { handleCircleClick("pp"); }
    public void qqClicked(MouseEvent mouseEvent) { handleCircleClick("qq"); }
    public void ssClicked(MouseEvent mouseEvent) { handleCircleClick("ss"); }
    public void ttClicked(MouseEvent mouseEvent) { handleCircleClick("tt"); }
    public void vvClicked(MouseEvent mouseEvent) { handleCircleClick("vv"); }
    public void yyClicked(MouseEvent mouseEvent) { handleCircleClick("yy"); }
    public void zzClicked(MouseEvent mouseEvent) { handleCircleClick("zz"); }
    public void a1Clicked(MouseEvent mouseEvent) { handleCircleClick("a1"); }

    private void handleCircleClick(String circleName) {
        if (firstClickedCircle == null) {
            firstClickedCircle = circleName;
            handleFirstCircleClick(circleName);
        } else if (firstClickedCircle.equals(circleName)) {
            resetLines();
            firstClickedCircle = null;
            secondClickedCircle = null;
        } else {
            secondClickedCircle = circleName;
            highlightExtendedRelations(firstClickedCircle, secondClickedCircle);
            handleFirstCircleClick(firstClickedCircle);
        }
    }

    private void handleFirstCircleClick(String circleName) {
        resetLines();
        String[] relatedCircleNames = relations.get(circleName);
        if (relatedCircleNames != null) {
            for (String relatedCircleName : relatedCircleNames) {
                Line lineToHighlight = findLineBetweenCircles(circleName, relatedCircleName);
                if (lineToHighlight != null) {
                    lineToHighlight.setStroke(Color.GREEN);
                    lineToHighlight.setStrokeWidth(3);
                }
            }
        }
    }

    private void highlightExtendedRelations(String startCircle, String endCircle) {
        resetLines();
        Set<List<String>> allPaths = findAllPaths(startCircle, endCircle);

        for (List<String> path : allPaths) {
            for (int i = 0; i < path.size() - 1; i++) {
                Line pathLine = findLineBetweenCircles(path.get(i), path.get(i + 1));
                if (pathLine != null) {
                    pathLine.setStroke(Color.YELLOW);
                    pathLine.setStrokeWidth(3);
                }
            }
        }

        firstClickedCircle = null;
        secondClickedCircle = null;
    }

    private Set<List<String>> findAllPaths(String start, String end) {
        Set<List<String>> paths = new HashSet<>();
        Queue<List<String>> queue = new LinkedList<>();
        queue.add(Arrays.asList(start));

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String last = path.get(path.size() - 1);

            if (last.equals(end)) {
                paths.add(path);
            }

            String[] neighbors = relations.get(last);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (!path.contains(neighbor)) {
                        List<String> newPath = new ArrayList<>(path);
                        newPath.add(neighbor);
                        queue.add(newPath);
                    }
                }
            }
        }
        return paths;
    }

    private Line findLineBetweenCircles(String circle1, String circle2) {
        Map<String, Line> circleLineMap = new HashMap<>();
        circleLineMap.put("aa_aj", aj);
        circleLineMap.put("aa_ta", ta);
        circleLineMap.put("hh_hj", hj);
        circleLineMap.put("hh_ph", ph);
        circleLineMap.put("hh_ht", ht);
        circleLineMap.put("hh_vh", vh);
        circleLineMap.put("jj_aj", aj);
        circleLineMap.put("jj_hj", hj);
        circleLineMap.put("jj_lj", lj);
        circleLineMap.put("ll_lj", lj);
        circleLineMap.put("ll_pl", pl);
        circleLineMap.put("pp_ph", ph);
        circleLineMap.put("pp_pl", pl);
        circleLineMap.put("pp_qp", qp);
        circleLineMap.put("qq_qp", qp);
        circleLineMap.put("qq_sq", sq);
        circleLineMap.put("qq_qv", qv);
        circleLineMap.put("ss_sq", sq);
        circleLineMap.put("ss_sz", sz);
        circleLineMap.put("tt_ht", ht);
        circleLineMap.put("tt_a1t", a1t);
        circleLineMap.put("vv_vh", vh);
        circleLineMap.put("vv_qv", qv);
        circleLineMap.put("vv_zv", zv);
        circleLineMap.put("vv_va1", va1);
        circleLineMap.put("yy_zy", zy);
        circleLineMap.put("yy_ya1", ya1);
        circleLineMap.put("zz_sz", sz);
        circleLineMap.put("zz_zv", zv);
        circleLineMap.put("zz_zy", zy);
        circleLineMap.put("a1_a1t", a1t);
        circleLineMap.put("a1_va1", va1);
        circleLineMap.put("a1_ya1", ya1);

        Line line = circleLineMap.get(circle1 + "_" + circle2);
        if (line == null) {
            line = circleLineMap.get(circle2 + "_" + circle1);
        }

        return line;
    }

    private void resetLines() {
        Line[] allLines = {qp, pl, lj, ht, ph, ta, a1t, ya1, zy, sz, va1, qv, hj, vh, zv, sq, aj};
        for (Line line : allLines) {
            line.setStroke(Color.BLACK);
            line.setStrokeWidth(1);
        }
    }
}
