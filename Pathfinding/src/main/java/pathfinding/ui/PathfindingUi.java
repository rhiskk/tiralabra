package pathfinding.ui;

import java.io.FileNotFoundException;
import javafx.application.Application;
import pathfinding.domain.PathfindingService;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * Väliakainen tekstikäyttöliittymä
 */
public class PathfindingUi extends Application {

    private PathfindingService ps;
    private char[][] map;

    @Override
    public void init() throws Exception {
        ps = new PathfindingService();
        map = ps.getMap();

    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {

        BorderPane bPane = new BorderPane();
        Pane pane = new Pane();

        Label bfsLength = new Label("BFS length: ");
        Label aStarLength = new Label("A* length: ");
        Label jpsLength = new Label("JPS length: ");
        Label bfsPerformance = new Label("BFS time: ");
        Label aStarPerformance = new Label("A* time: ");
        Label jpsPerformance = new Label("JPS time: ");

        VBox results = new VBox();
        results.getChildren().addAll(bfsLength, aStarLength, jpsLength, bfsPerformance, aStarPerformance, jpsPerformance);

        Label start = new Label("alkupiste");
        Label end = new Label("loppupiste");

        TextField startX = new TextField("0");
        TextField startY = new TextField("0");
        TextField endX = new TextField("0");
        TextField endY = new TextField("0");

        HBox coordinates = new HBox();
        coordinates.getChildren().addAll(start, startX, startY, end, endX, endY);

        Button setCoordinates = new Button("aseta koordinaatit");
        Button pathBfs = new Button("lyhin bfs");
        Button pathAStar = new Button("lyhin A*");
        Button pathJps = new Button("lyhin jps");
        Button testBfs = new Button("tehokkuus bfs");
        Button testAStar = new Button("tehokkuus A*");
        Button testJps = new Button("tehokkuus jps");
        //Button clear = new Button("Clear paths");

        HBox actions = new HBox();
        actions.getChildren().addAll(setCoordinates, pathBfs, pathAStar, pathJps, testBfs, testAStar, testJps);

        VBox box = new VBox();
        box.getChildren().addAll(coordinates, results, actions);

        bPane.setLeft(pane);
        bPane.setRight(box);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[j][i] == '@') {
                    Rectangle square = new Rectangle(i / 2, j / 2, 1, 1);
                    square.setFill(Color.BLACK);
                    pane.getChildren().add(square);
                }
            }
        }

        Scene scene = new Scene(bPane);

        setCoordinates.setOnAction((event) -> {
            ps.setStart(Integer.valueOf(startY.getText()), Integer.valueOf(startX.getText()));
            ps.setEnd(Integer.valueOf(endY.getText()), Integer.valueOf(endX.getText()));
        });

        /*clear.setOnAction((event) -> {
            pane.getChildren().clear();
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[j][i] == '@') {
                        Rectangle square = new Rectangle(i / 2, j / 2, 1, 1);
                        square.setFill(Color.BLACK);
                        pane.getChildren().add(square);
                    }
                }
            }
        });*/

        pathBfs.setOnAction((event) -> {
            bfsLength.setText(ps.bfsPathLength());
            char[][] path = ps.getBPath();
            for (int i = 0; i < path.length; i++) {
                for (int j = 0; j < path.length; j++) {
                    if (path[j][i] == 'b') {
                        Rectangle rectangle = new Rectangle(i / 2, j / 2, 2, 2);
                        rectangle.setFill(Color.BLUE);
                        pane.getChildren().add(rectangle);
                    }
                }
            }
        });

        pathAStar.setOnAction((event) -> {
            aStarLength.setText(ps.aStarPathLength());
            char[][] path = ps.getAPath();
            for (int i = 0; i < path.length; i++) {
                for (int j = 0; j < path.length; j++) {
                    if (path[j][i] == 'a') {
                        Rectangle rectangle = new Rectangle(i / 2, j / 2, 2, 2);
                        rectangle.setFill(Color.RED);
                        pane.getChildren().add(rectangle);
                    }
                }
            }
        });

        pathJps.setOnAction((event) -> {
            jpsLength.setText(ps.jpsPathLength());
            char[][] path = ps.getJPath();
            for (int i = 0; i < path.length; i++) {
                for (int j = 0; j < path.length; j++) {
                    if (path[j][i] == 'j') {
                        Rectangle rectangle = new Rectangle(i / 2, j / 2, 2, 2);
                        rectangle.setFill(Color.GREEN);
                        pane.getChildren().add(rectangle);
                    }
                    if (path[j][i] == 'p') {
                        Rectangle rectangle = new Rectangle(i / 2, j / 2, 2, 2);
                        rectangle.setFill(Color.MAGENTA);
                        pane.getChildren().add(rectangle);
                    }

                }
            }

        });

        testBfs.setOnAction((event) -> {
            bfsPerformance.setText(ps.bfsPerformance());
        });

        testAStar.setOnAction((event) -> {
            aStarPerformance.setText(ps.aStarPerformance());
        });

        testJps.setOnAction((event) -> {
            jpsPerformance.setText(ps.jpsPerformance());
        });

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }
}
