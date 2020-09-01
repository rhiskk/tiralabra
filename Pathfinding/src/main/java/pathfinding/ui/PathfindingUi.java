package pathfinding.ui;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import pathfinding.domain.PathfindingService;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pathfinding.io.MapScanner;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

/**
 * GUI.
 * 
 */
public class PathfindingUi extends Application {

    private PathfindingService ps;
    private MapScanner ms;
    private char[][] map;
    private double mplier; //map coordinate multiplier
    private GraphicsContext mapDrawer;
    private Alert alert;
    private DecimalFormat decimalFormat;
    
    @Override
    public void init() throws Exception {
        map = new char[0][0];
        ps = new PathfindingService();
        ms = new MapScanner();
        mplier = 1.0;
        decimalFormat = new DecimalFormat("#.###");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        
        //alert
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid coordinates");
        
        BorderPane bPane = new BorderPane();
        Canvas mapCanvas = new Canvas(512, 512);
        mapDrawer = mapCanvas.getGraphicsContext2D();
        
        //labels
        Label bfsLength = new Label("BFS length: ");
        bfsLength.setPrefWidth(120);
        Label aStarLength = new Label("A* length: ");
        aStarLength.setPrefWidth(120);
        Label jpsLength = new Label("JPS length: ");
        jpsLength.setPrefWidth(120);
        
        Label bfsPerformance = new Label("BFS time: ");
        bfsPerformance.setPrefWidth(100);
        Label aStarPerformance = new Label("A* time: ");
        aStarPerformance.setPrefWidth(100);
        Label jpsPerformance = new Label("JPS time: ");
        jpsPerformance.setPrefWidth(100);
        Label mapsLabel = new Label("Select map: ");
        
        //text fields
        TextField startX = new TextField("0");
        startX.setPrefWidth(100);
        TextField startY = new TextField("0");
        startY.setPrefWidth(100);
        TextField endX = new TextField("0");
        endX.setPrefWidth(100);
        TextField endY = new TextField("0");
        endY.setPrefWidth(100);
        
        //buttons
        Button pathBfs = new Button("BFS path");
        pathBfs.setPrefWidth(70);
        Button pathAStar = new Button("A* path");
        pathAStar.setPrefWidth(70);
        Button pathJps = new Button("JPS path");
        pathJps.setPrefWidth(70);
        Button testBfs = new Button("Time BFS");
        testBfs.setPrefWidth(70);
        Button testAStar = new Button("Time A*");
        testAStar.setPrefWidth(70);
        Button testJps = new Button("Time JPS");
        testJps.setPrefWidth(70);
        Button clear = new Button("Clear paths");
        
        //radio buttons
        ToggleGroup group = new ToggleGroup();
        RadioButton setStart = new RadioButton("Start");
        setStart.setStyle("-fx-text-fill: red;");
        setStart.setPrefWidth(60);
        RadioButton setEnd = new RadioButton("End");
        setEnd.setStyle("-fx-text-fill: blue;");
        setEnd.setPrefWidth(60);
        
        setStart.setToggleGroup(group);
        setStart.setSelected(true);
        setEnd.setToggleGroup(group);
        
        //files
        File[] files = ps.getFiles();
        
        //choice box
        ChoiceBox maps = new ChoiceBox(FXCollections.observableArrayList(files));
        
        //init map
        maps.setValue(files[0]);
        setMap(files[0]);
        drawMap();
        
        HBox startBox = new HBox();
        startBox.getChildren().addAll(setStart, startX, startY);
        startBox.setSpacing(10);
        
        HBox endBox = new HBox();
        endBox.getChildren().addAll(setEnd, endX, endY);
        endBox.setSpacing(10);
        
        HBox bfsBox = new HBox();
        bfsBox.getChildren().addAll(pathBfs, bfsLength, testBfs, bfsPerformance);
        bfsBox.setSpacing(10);
        
        HBox aStarBox = new HBox();
        aStarBox.getChildren().addAll(pathAStar, aStarLength, testAStar, aStarPerformance);
        aStarBox.setSpacing(10);
        
        HBox jpsBox = new HBox();
        jpsBox.getChildren().addAll(pathJps, jpsLength, testJps, jpsPerformance);
        jpsBox.setSpacing(10);
        
        VBox box = new VBox();
        box.getChildren().addAll(startBox, endBox, bfsBox, aStarBox, jpsBox, clear);
        box.setSpacing(8);
        box.setPadding(new Insets(10, 10, 10, 30));
        box.setPrefWidth(400);
        
        HBox mapBox = new HBox(mapsLabel, maps);  
        Pane mapPane = new Pane(mapCanvas);
        
        bPane.setTop(mapBox);
        bPane.setLeft(mapPane);
        bPane.setCenter(box);
        bPane.setPrefWidth(1000);
        bPane.setPadding(new Insets(10, 10, 10, 10));
        
        maps.getSelectionModel().selectedIndexProperty().addListener(( ov, value, new_value) -> {
            setMap(files[new_value.intValue()]);
            drawMap();
        });
        
        clear.setOnAction((event) -> {
            setMap((File) maps.getValue());
            drawMap();
        });
        
        mapCanvas.setOnMouseClicked((event) -> {
            RadioButton selected = (RadioButton) group.getSelectedToggle();
            int j = (int) (event.getY() / mplier);
            int i = (int) (event.getX() / mplier);
            if (selected.equals(setStart)) {
                if (!invalid(j, i)) {    
                    mapDrawer.setFill(Color.WHITE);
                    mapDrawer.fillRect(Integer.valueOf(startX.getText()) * mplier,
                            Integer.valueOf(startY.getText()) * mplier, 3, 3);
                    startX.setText(String.valueOf(i));
                    startY.setText(String.valueOf(j));           
                    mapDrawer.setFill(Color.RED);
                    mapDrawer.fillRect(i * mplier, j * mplier, 3, 3);
                    setStart.setSelected(false);
                    setEnd.setSelected(true);
                }
            } else {
                if (!invalid(j, i)) {
                    mapDrawer.setFill(Color.WHITE);
                    mapDrawer.fillRect(Integer.valueOf(endX.getText()) * mplier,
                            Integer.valueOf(endY.getText()) * mplier, 3, 3);
                    endX.setText(String.valueOf((int) (event.getX() / mplier)));
                    endY.setText(String.valueOf((int) (event.getY() / mplier)));
                    mapDrawer.setFill(Color.BLUE);
                    mapDrawer.fillRect(i * mplier, j * mplier, 3, 3);
                    setEnd.setSelected(false);
                    setStart.setSelected(true);
                }      
            }  
        });
        
        pathBfs.setOnAction((event) -> {
            if (!setCoordinates(startY, startX, endY, endX)) {
                alert.showAndWait();
                return;
            }
            double length = ps.bfsPathLength();
            if (length == -1) {
                bfsLength.setText("Path not found");
                return;
            }
            bfsLength.setText("BFS length: " + decimalFormat.format(length));
            bfsLength.setTextFill(Color.BLUE);
            char[][] path = ps.getBPath();
            drawPath(path);
        });

        pathAStar.setOnAction((event) -> {
           if (!setCoordinates(startY, startX, endY, endX)) {
                alert.showAndWait();
                return;
            }
            double length = ps.aStarPathLength();
            if (length == -1) {
                aStarLength.setText("Path not found");
                return;
            }
            aStarLength.setText("A* length: " + decimalFormat.format(length));
            aStarLength.setTextFill(Color.RED);
            char[][] path = ps.getAPath();
            drawPath(path);
        });

        pathJps.setOnAction((event) -> {
            if (!setCoordinates(startY, startX, endY, endX)) {
                alert.showAndWait();
                return;
            }
            double length = ps.jpsPathLength();
            if (length == -1) {
                jpsLength.setText("Path not found");
                return;
            }
            jpsLength.setText("JPS length: " + decimalFormat.format(length));
            jpsLength.setTextFill(Color.GREEN);
            char[][] path = ps.getJPath();
            drawPath(path);

        });

        testBfs.setOnAction((event) -> {
            bfsPerformance.setText("BFS time: " + (int) ps.bfsPerformance() + " ms");
        });

        testAStar.setOnAction((event) -> {
            aStarPerformance.setText("A* time: " + (int) ps.aStarPerformance() + " ms");
        });

        testJps.setOnAction((event) -> {
            jpsPerformance.setText("JPS time: " + (int) ps.jpsPerformance() + " ms");
        });
             
        Scene scene = new Scene(bPane);
        stage.setScene(scene);
        stage.setTitle("Pathfinding");
        stage.show();
    }
    
    /**
    * Creates an ASCII-grid from the .map-file and sets it as the current map.
    * @param file .map file
    */
    private void setMap(File file) {
        try {
            map = ms.scan(file);
            ps.setMap(map);
            mplier = (double) 512/map.length;
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR");
        }     
    }
    
    /**
    * Draws the map on the canvas.
    */
    private void drawMap() {
        mapDrawer.clearRect(0, 0, 512, 512);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[j][i] == '@') {
                    mapDrawer.setFill(Color.BLACK);
                    mapDrawer.fillRect(i * mplier, j * mplier, 1 * mplier, 1 * mplier);
                } else {
                    mapDrawer.setFill(Color.WHITE);
                    mapDrawer.fillRect(i * mplier, j * mplier, 1 * mplier, 1 * mplier);
                }
            }
        }
    }
    
    
    /**
    * Sets the start and end point.
    * 
    * @param startY start y textfield
    * @param startX start x textfield
    * @param endY end y textfield
    * @param endX end x textfield
    */
    private boolean setCoordinates(TextField startY, TextField startX, TextField endY, TextField endX) {
        int sy = Integer.valueOf(startY.getText());
        int sx = Integer.valueOf(startX.getText());
        int ey = Integer.valueOf(endY.getText());
        int ex = Integer.valueOf(endX.getText());
        if (invalid(sy, sx) || invalid(ey, ex)) {
            return false;
        }
        ps.setStart(sy, sx);
        ps.setEnd(ey, ex);
        
        return true;
    }
    
    private boolean invalid(int y, int x) {
        return (y < 0 || x < 0 || y >= map.length
                || x >= map[0].length || map[y][x] == '@');
    }
    
    
    /**
    * Draws a path on the canvas.
    * 
    * @param path path as an ASCII-grid
    */
    private void drawPath(char[][] path) {
        if (path == null) {
            return;
        }
        double size = 2 * mplier;
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[0].length; j++) {
                switch (path[j][i]) {
                    case 'b':
                        mapDrawer.setFill(Color.BLUE);
                        mapDrawer.fillRect(i * mplier, j * mplier, size, size);
                        break;
                    case 'a':
                        mapDrawer.setFill(Color.RED);
                        mapDrawer.fillRect(i * mplier, j * mplier, size, size);
                        break;
                    case 'j':
                        mapDrawer.setFill(Color.GREEN);
                        mapDrawer.fillRect(i * mplier, j * mplier, size, size);
                        break;
                    case 'p':
                        mapDrawer.setFill(Color.MAGENTA);
                        mapDrawer.fillRect(i * mplier, j * mplier, size, size);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }
}
