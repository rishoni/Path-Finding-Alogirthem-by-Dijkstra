/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxPackage;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.Optional;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Rishoni De Silva 
 * student id : 2016357
 */
public class FXMain extends Application {

    int rowNum = 20;
    boolean start = false;
    boolean end = false;
    int colNum = 20;
    static int p;
    NodeClass startNode = null;

    static Rectangle[][] rec; //rectangle gui
    //   static NodeClass[] cell;
    static ArrayList<NodeClass> pathList = new ArrayList<>();//add nodes in to path list
    static ArrayList<NodeClass> closedList = new ArrayList<>();//add vsited nodes in to closed list

    private static int[][] matrix //2D arrays holds each tiles weights
            = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1},
                {4, 4, 1, 4, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1},
                {4, 4, 4, 4, 4, 4, 1, 1, 2, 3, 3, 3, 2, 1, 1, 1, 1, 1, 1, 1},
                {4, 4, 4, 4, 4, 4, 1, 1, 2, 3, 3, 3, 3, 2, 1, 1, 1, 1, 1, 1},
                {1, 1, 4, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 2, 1, 1, 2, 2, 1, 1},
                {1, 4, 4, 1, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 1},
                {4, 2, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1},
                {1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4},
                {1, 1, 2, 3, 3, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4},
                {1, 2, 3, 3, 3, 3, 2, 2, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 1},
                {1, 2, 3, 2, 2, 2, 3, 2, 4, 1, 1, 1, 4, 4, 4, 4, 2, 1, 1, 1},
                {1, 2, 2, 1, 1, 1, 4, 4, 4, 4, 1, 1, 4, 4, 4, 1, 1, 1, 1, 1},
                {1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 4, 4, 4, 4, 1, 1, 1, 2, 2, 0, 0, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 0, 0, 0, 1, 1, 0, 0, 0},
                {1, 2, 2, 2, 2, 2, 1, 1, 1, 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            };

// public static void show(int[][] newArray, int which, int x1, int y1, int x2, int y2) {
//        int N = newArray.length;
//        System.out.println(newArray.length);
//     
//        for (int i = 0; i < N; i++)
//            for (int j = 0; j < N; j++)
//                if (newArray[i][j] == which)
//                    if ((i == x1 && j == y1) ||(i == x2 && j == y2)) {
//                        rec[j][i].setFill(Color.GREENYELLOW);
//                        System.out.println(  rec[i][j]);
//                    }
//               
//    }
    @Override
    public void start(Stage primaryStage) {

        double width = 30; //grid width
        Pane gridPane = new Pane();
        
      
Image image = new Image("image/land.png");
       
        
        int nValue = matrix.length;
ImagePattern imagePattern = new ImagePattern(image);
        Rectangle[][] recObj = new Rectangle[nValue][nValue];//declaring rectangle object  with the given length
        

ImageView imageView = new ImageView(image);
    

        for (int i = 0; i < nValue; i++) { //generating the grid
            for (int j = 0; j < nValue; j++) {
                recObj[i][j] = new Rectangle();
                recObj[i][j].setX(j * width);
                /**
                 * generating the grid
                 * 
                 * 
                 */
                recObj[i][j].setY(i * width);
                recObj[i][j].setWidth(width);
                recObj[i][j].setHeight(width);

                //recObj[i][j].setFill(new ImagePattern(image));
          recObj[i][j].setStroke(Color.BLACK);
                
             gridPane.getChildren().addAll(recObj[i][j] );
       
        
                int colorTile = matrix[i][j];//placing given colors in to gird

                switch (colorTile) { //accorging to the weight change the tiles
                    case 0:
                        recObj[i][j].setFill(Color.BLACK);//0 weight tiles
                       // recObj[i][j].setFill(Color.TRANSPARENT);
                        break;

                    case 1:
                        recObj[i][j].setFill(Color.WHITE);//1 weight tiles
                        break;

                    case 2:
                        recObj[i][j].setFill(Color.LIGHTGREY);//2 weight tiles
                        break;

                    case 3:
                        recObj[i][j].setFill(Color.DARKGREY);//3 weight tiles
                        break;

                    case 4:
                        recObj[i][j].setFill(Color.GREY);//4 weight tiles
                        break;
                }
                // gridPane.getChildren().addAll((recObj[i][j]),imageView);
   //  recObj[i][j].setFill(new ImagePattern(image));
            }
        
        }
        
    
        /**
         * Create alert box to select a heuristic first
         *
         */
        Alert alertBoxOne = new Alert(AlertType.INFORMATION);
        alertBoxOne.getHeight();
        alertBoxOne.setTitle("Select An Option");
        alertBoxOne.setHeaderText("Distance Based Matrics");
        alertBoxOne.setContentText("Select One option!");

        /**
         * Apply css styling for the alert box
         *
         */
        DialogPane dialogPane = alertBoxOne.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("myDialog.css").toExternalForm()); //linking to css styling sheet
        dialogPane.getStyleClass().add("myDialog");
        /**
         * declaring buttons
         *
         */
        ButtonType ManhattanBtn = new ButtonType("Manhattan");
        ButtonType EuclideanBtn = new ButtonType("Euclidean");
        ButtonType ChebyshevBtn = new ButtonType(" Chebyshev");
        ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alertBoxOne.getButtonTypes().setAll(ManhattanBtn, EuclideanBtn, ChebyshevBtn, cancelBtn);

        Optional<ButtonType> result = alertBoxOne.showAndWait(); //returning the declaring buttons
//way to get the responsive value

        gridPane.setOnMouseClicked(new EventHandler<MouseEvent>() {//pane rec onclick listner
            @Override
            public void handle(MouseEvent value) {

                int Ai = 0, Aj = 0, Bi = 0, Bj = 0; //vars to get the node i ,j values
                if (!start) {
                    double posX = value.getX(); //get the value of x in start node
                    double posY = value.getY();//get the value of y in end node

                    Aj = (int) (posX / width);
                    Ai = (int) (posY / width);
                    startNode = new NodeClass(Aj, Ai);//get x and y

                    recObj[Ai][Aj].setFill(Color.BLUEVIOLET);//turn start node color in to...

                    start = true;
                } else if (start && !end) {
                    double posZ = value.getY();
                    /**
                     * get the value of x in start node and y in the endNode
                     */
                    double posK = value.getX();
                    Bi = (int) (posZ / width);
                    Bj = (int) (posK / width);

                    recObj[Bi][Bj].setFill(Color.BLUEVIOLET);//turn end node color in to...

                    NodeClass endNode = new NodeClass(Bj, Bi);//add end node x,y
                    end = true;
                    //fef();
                    try {
                        Thread.sleep(1000);

                        Stopwatch timer = new Stopwatch();//to get the elapsed time

                        

                        Stopwatch totalTime = new Stopwatch(); //to get the elapsed time

                        int[][] Array
                                = {
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1},
                                    {4, 4, 1, 4, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1},
                                    {4, 4, 4, 4, 4, 4, 1, 1, 2, 3, 3, 3, 2, 1, 1, 1, 1, 1, 1, 1},
                                    {4, 4, 4, 4, 4, 4, 1, 1, 2, 3, 3, 3, 3, 2, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 4, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 2, 1, 1, 2, 2, 1, 1},
                                    {1, 4, 4, 1, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 1},
                                    {4, 2, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1},
                                    {1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4},
                                    {1, 1, 2, 3, 3, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4},
                                    {1, 2, 3, 3, 3, 3, 2, 2, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 1},
                                    {1, 2, 3, 2, 2, 2, 3, 2, 4, 1, 1, 1, 4, 4, 4, 4, 2, 1, 1, 1},
                                    {1, 2, 2, 1, 1, 1, 4, 4, 4, 4, 1, 1, 4, 4, 4, 1, 1, 1, 1, 1},
                                    {1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                                    {1, 1, 4, 4, 4, 4, 1, 1, 1, 2, 2, 0, 0, 1, 1, 1, 1, 1, 1, 0},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 0, 0, 0, 1, 1, 0, 0, 0},
                                    {1, 2, 2, 2, 2, 2, 1, 1, 1, 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                                    {2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                                };
                                        /*Reading the coordinates 
                                                                for points A and B on the
                                                                    input squared grid.
                                                                                                    */
                        System.out.println("start - x Node- " + startNode.getX() + " y Node - " + startNode.getY());//get the start , x,y cordinates 
                        System.out.println("start - x Node- " + endNode.getX() + " y Node- " + endNode.getY());////get the end , x,y cordinates 
System.out.println("Elapsed time = " + timer.elapsedTime());
                        if (result.get() == ManhattanBtn) { //when user clicks ManhattanBtn 
                            try {

                                PathFind pathFindObj = new PathFind(startNode, endNode);//passing the values of start node and end node 
                                Stopwatch aStarTime = new Stopwatch();//start Timing 
                                pathFindObj.dijkstraMan(Array);//passing the array in to dijkstra manhattan method
                    //            System.out.println("Elapsed time  Dijkstra's Search= " + aStarTime.elapsedTime());//counting time begining to end  in dijkstra

                                Stopwatch drawTime = new Stopwatch();//counting time for draw path
                                Pane paneTwo = pathFindObj.drawpath(Array);//new pane to get the path between two start and end nodes
                                Scene sceneNew = new Scene(paneTwo, 600, 600);
                                primaryStage.setTitle("grid");
                                primaryStage.setScene(sceneNew);
                                primaryStage.show();
                                
                               /**
                                * new alert box to display statistics
                                * 
                                */

                                Alert alertt = new Alert(AlertType.INFORMATION);
  System.out.println("Elapsed time  Dijkstra's Search= " + aStarTime.elapsedTime());
                                System.out.println("Elapsed time Draw Path= " + drawTime.elapsedTime());//start end time which cost when draw the path
                                 System.out.println("Elapsed time Total= " + totalTime.elapsedTime());//total time of start to end in event
                                System.out.println("Cost " + endNode.getMoveCost());
                                
                                alertt.setTitle("Results");
                                alertt.setHeaderText("Manhattan Distance");
                                alertt.setContentText("Elapsed time Draw Path= " + drawTime.elapsedTime() + "\n" + "Elapsed time  Dijkstra's Search= " + aStarTime.elapsedTime() + "\n" + "Elapsed time Total= " + totalTime.elapsedTime() + "\n" + "Move Cost = " + endNode.getMoveCost());

                               /**
                                * applying css styling sheet for the alert box
                                */
                                DialogPane dialogPaneCss = alertt.getDialogPane();
                                dialogPaneCss.getStylesheets().add(
                                        getClass().getResource("myDialog.css").toExternalForm());
                                dialogPaneCss.getStyleClass().add("myDialog");
                                
                                          
                                alertt.showAndWait();


//pf.drawpath(newArray);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("The system not percolates start point to end point");
                            }
                        } else if (result.get() == EuclideanBtn) {
                            try {

                                PathFind pathFindObj = new PathFind(startNode, endNode);//passing the values of start node and end node 
                                Stopwatch aStarTime = new Stopwatch();//start timimg
                                pathFindObj.dijkstraEuc(Array);
                             
                                Stopwatch draw = new Stopwatch();
                                Pane pane = pathFindObj.drawpath(Array);
                                Scene sceneNew = new Scene(pane, 600, 600);
                                primaryStage.setTitle("grid");
                                primaryStage.setScene(sceneNew);
                                primaryStage.show();
                                Alert alertt = new Alert(AlertType.INFORMATION);

                                DialogPane dialogPane = alertt.getDialogPane();
                                dialogPane.getStylesheets().add(
                                        getClass().getResource("myDialog.css").toExternalForm());
                                dialogPane.getStyleClass().add("myDialog");

                                
                                 
                                alertt.setTitle("Results");
                                alertt.setHeaderText("Euclidean Distance");
                                alertt.setContentText("Elapsed time Draw Path= " + draw.elapsedTime() + "\n" + "Elapsed time  Dijkstra's Search= " + aStarTime.elapsedTime() + "\n" + "Elapsed time Total= " + totalTime.elapsedTime() + "\n" + "Move Cost = " + endNode.getMoveCost());

                                   System.out.println("Elapsed time Draw Path= " + draw.elapsedTime());
   System.out.println("Elapsed time  Dijkstra's Search= " + aStarTime.elapsedTime());//

                                System.out.println("Elapsed time Total= " + totalTime.elapsedTime());
                                System.out.println("Count " + endNode.getMoveCost());//getiing the cost between start node and end node


                          
                                
                                  alertt.showAndWait(); //show the alter box

                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("The system not percolates start point to end point");
                            }

                        } else if (result.get() == ChebyshevBtn) {
                            try {

                                PathFind pf = new PathFind(startNode, endNode);
                                Stopwatch aStar = new Stopwatch();
                                pf.dijkstraChe(Array);

                                Stopwatch draw = new Stopwatch();
                                Pane pane = pf.drawpath(Array);
                                Scene sceneNew = new Scene(pane, 600, 600);
                                primaryStage.setTitle("grid");
                                primaryStage.setScene(sceneNew);
                                primaryStage.show();

                                Alert alertt = new Alert(AlertType.INFORMATION);
                                alertt.setTitle("Results");
                                alertt.setHeaderText(" Chebyshev Distance");
                                alertt.setContentText("Elapsed time Draw Path= " + draw.elapsedTime() + "\n" + "Elapsed time  Dijkstra's Search= " + aStar.elapsedTime() + "\n" + "Elapsed time Total= " + totalTime.elapsedTime() + "\n" + "Move Cost = " + endNode.getMoveCost());

                                DialogPane dialogPane = alertt.getDialogPane();
                                dialogPane.getStylesheets().add(
                                        getClass().getResource("myDialog.css").toExternalForm());
                                dialogPane.getStyleClass().add("myDialog");

                           

//pf.drawpath(newArray);
                                System.out.println("Elapsed time Draw Path= " + draw.elapsedTime());
                                System.out.println("Elapsed time  Dijkstra's Search= " + aStar.elapsedTime());
                                System.out.println("Elapsed time Total= " + totalTime.elapsedTime());
                                System.out.println("Count " + endNode.getMoveCost());

                                     alertt.showAndWait();
                                     
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("The system not percolates start point to end point");
                            }
                        } else if (result.get() == cancelBtn) {
                            System.exit(0); //exit the programme 

                        }

                    } catch (InterruptedException ex) {
                        Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        });

        Scene scene = new Scene(gridPane, 600, 600);

        primaryStage.setTitle("Grid");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);
        Scanner sc = new Scanner(System.in);

        //     show(newArray,1,Ai,Aj,Bi,Bj);
    }

}
