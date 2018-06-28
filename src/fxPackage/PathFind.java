/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxPackage;

/**
 *
 * @author Rishoni De Silva
 * student id : 2016357
 */
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class PathFind {

    List<NodeClass> openList = new ArrayList<>();
    List<NodeClass> closedList = new ArrayList<>();
    List<NodeClass> path = new ArrayList<>();

    NodeClass start;
    NodeClass end;
    Boolean isTrue;

    public PathFind(NodeClass start, NodeClass end) {
        this.start = start;
        this.end = end;
    }

    public void dijkstraMan(int newArray[][]) {

        int N = newArray.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newArray[i][j] == 0) {
                    closedList.add(new NodeClass(i, j));
                }
            }
        }

        start.setMoveCost(0);

        start.setHeuristic(manhatanDistance(start.getX(), start.getY(), end.getX(), end.getY()));
        //  start.setHeuristic((int) euclideanDistance(start.getX(),start.getY(),end.getX(),end.getY()));
        //  start.setHeuristic(chebyshevDistance(start.getX(),start.getY(),end.getX(),end.getY()));
        closedList.add(start);
        try {
            findNeighboursMan(newArray, start);
        } catch (Exception e) {
        }
        while (true) {
            NodeClass node = openList.get(0);
            for (NodeClass n : openList) {
                if (node.getMoveCost() > n.getMoveCost()) {//node.getMoveCost() > n.getMoveCost() &&
                    node = n;
                }
            }

            openList.remove(node);
            closedList.add(node);

            try {
                findNeighboursMan(newArray, node);
            } catch (Exception e) {
                break;
            }
        }

        for (NodeClass n : closedList) {
            //  StdDraw.setPenColor(StdDraw.GREEN);
            //   StdDraw.filledSquare(n.getY(),a.length-n.getX()-1, .5)
        }
        System.out.println("");
    }

    public void dijkstraChe(int newArray[][]) {

        int N = newArray.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newArray[i][j] == 0) {
                    closedList.add(new NodeClass(i, j));
                }
            }
        }

        start.setMoveCost(0);

        //      start.setHeuristic(manhatanDistance(start.getX(),start.getY(),end.getX(),end.getY()));
        //  start.setHeuristic((int) euclideanDistance(start.getX(),start.getY(),end.getX(),end.getY()));
        start.setHeuristic(chebyshevDistance(start.getX(), start.getY(), end.getX(), end.getY()));
        closedList.add(start);
        try {
            findNeighboursChe(newArray, start);
        } catch (Exception e) {
        }
        while (true) {
            NodeClass node = openList.get(0);
            for (NodeClass n : openList) {
                if (node.getMoveCost() > n.getMoveCost()) {//node.getMoveCost() > n.getMoveCost() &&
                    node = n;
                }
            }

            openList.remove(node);
            closedList.add(node);

            try {
                findNeighboursChe(newArray, node);
            } catch (Exception e) {
                break;
            }
        }

        for (NodeClass n : closedList) {
            //  StdDraw.setPenColor(StdDraw.GREEN);
            //   StdDraw.filledSquare(n.getY(),a.length-n.getX()-1, .5)
        }

    }

    public void dijkstraEuc(int newArray[][]) {

        int N = newArray.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newArray[i][j] == 0) {
                    closedList.add(new NodeClass(i, j));
                }
            }
        }

        start.setMoveCost(0);

        //    start.setHeuristic(manhatanDistance(start.getX(),start.getY(),end.getX(),end.getY()));
        start.setHeuristic((int) euclideanDistance(start.getX(), start.getY(), end.getX(), end.getY()));
        //  start.setHeuristic(chebyshevDistance(start.getX(),start.getY(),end.getX(),end.getY()));
        closedList.add(start);
        try {
            findNeighboursEuc(newArray, start);
        } catch (Exception e) {
        }
        while (true) {
            NodeClass node = openList.get(0);
            for (NodeClass n : openList) {
                if (node.getMoveCost() > n.getMoveCost()) {//node.getMoveCost() > n.getMoveCost() &&
                    node = n;
                }
            }

            openList.remove(node);
            closedList.add(node);

            try {
                findNeighboursEuc(newArray, node);
            } catch (Exception e) {
                break;
            }
        }

        for (NodeClass n : closedList) {
            //  StdDraw.setPenColor(StdDraw.GREEN);
            //   StdDraw.filledSquare(n.getY(),a.length-n.getX()-1, .5)
        }

    }

    public void findNeighboursMan(int[][] newArray, NodeClass node) throws Exception {
        calculateCostMan(node.getX(), node.getY() - 1, newArray, node);   // down
        calculateCostMan(node.getX(), node.getY() + 1, newArray, node);   // up
        calculateCostMan(node.getX() - 1, node.getY(), newArray, node);   //left
        calculateCostMan(node.getX() + 1, node.getY(), newArray, node);   //right

        //diaganally =====================================================================================
        calculateCostMan(node.getX() - 1, node.getY() - 1, newArray, node);

        calculateCostMan(node.getX() + 1, node.getY() - 1, newArray, node);

        calculateCostMan(node.getX() + 1, node.getY() + 1, newArray, node);

        calculateCostMan(node.getX() - 1, node.getY() + 1, newArray, node);

        // ==============================================================================================
    }

    public void findNeighboursEuc(int[][] newArray, NodeClass node) throws Exception {
        calculateCostEuc(node.getX(), node.getY() - 1, newArray, node);   // down
        calculateCostEuc(node.getX(), node.getY() + 1, newArray, node);   // up
        calculateCostEuc(node.getX() - 1, node.getY(), newArray, node);   //left
        calculateCostEuc(node.getX() + 1, node.getY(), newArray, node);   //right

        //diaganally =====================================================================================
        calculateCostEuc(node.getX() - 1, node.getY() - 1, newArray, node);

        calculateCostEuc(node.getX() + 1, node.getY() - 1, newArray, node);

        calculateCostEuc(node.getX() + 1, node.getY() + 1, newArray, node);

        calculateCostEuc(node.getX() - 1, node.getY() + 1, newArray, node);

        // ==============================================================================================
    }

    public void findNeighboursChe(int[][] newArray, NodeClass node) throws Exception {
        calculateCostChey(node.getX(), node.getY() - 1, newArray, node);   // down
        calculateCostChey(node.getX(), node.getY() + 1, newArray, node);   // up
        calculateCostChey(node.getX() - 1, node.getY(), newArray, node);   //left
        calculateCostChey(node.getX() + 1, node.getY(), newArray, node);   //right

        //diaganally =====================================================================================
        calculateCostChey(node.getX() - 1, node.getY() - 1, newArray, node);

        calculateCostChey(node.getX() + 1, node.getY() - 1, newArray, node);

        calculateCostChey(node.getX() + 1, node.getY() + 1, newArray, node);

        calculateCostChey(node.getX() - 1, node.getY() + 1, newArray, node);

        // ==============================================================================================
    }

    public Pane drawpath(int newArray[][]) {
        path.add(end);
        Pane pane = new Pane();

        NodeClass n = closedList.get(closedList.size() - 1);
        path.add(n);
        while (true) {
            if (n.getParent() != null) {
                n = n.getParent();
                path.add(n);
                if (n.equals(start)) {
                    break;
                }
            }
        }

        NodeClass s = path.get(0);
        List<NodeClass> shortestPath = new ArrayList<>();
        shortestPath.add(s);
        for (int i = 1; i < path.size(); i++) {
            /*    System.out.println(path.get(i));
                 if(!(s.getX()==path.get(i).getX() ||  s.getY()==path.get(i).getY())){
                     System.out.println(path.get(i));
                     if(path.get(i).getY()>s.getY() && path.get(i).getX()<s.getX()){
                         System.out.println("H");
                         topToBottomPathSmooth(a,path.get(i),shortestPath);
                    }else if(path.get(i).getY()<s.getY() && path.get(i).getX()<s.getX()){
                         System.out.println("S");
                         topToBottomPathSmooth(a,path.get(i),shortestPath);
                    }else if(path.get(i).getY()>s.getY() && path.get(i).getX()>s.getX()){
                         System.out.println("P");
                          bottomToTopPathSmooth(a,path.get(i),shortestPath);
                      }else {
                         System.out.println("D");
                         bottomToTopPathSmooth(a,path.get(i),shortestPath);
                      }

                }

             */

            s = path.get(i);
            shortestPath.add(s);
        }
        for (NodeClass node : shortestPath) {
            // StdDraw.setPenColor(StdDraw.PINK);
            // StdDraw.filledSquare(node.getY(),a.length-node.getX()-1, .5);
        }

        double width = 30;
        int x = newArray.length;

        Rectangle[][] recNew = new Rectangle[x][x];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                recNew[i][j] = new Rectangle();
                recNew[i][j].setX(j * width);
                recNew[i][j].setY(i * width);
                recNew[i][j].setWidth(width);
                recNew[i][j].setHeight(width);
                recNew[i][j].setFill(null);
                recNew[i][j].setStroke(Color.BLACK);

                pane.getChildren().add(recNew[i][j]);

                int arrNew = newArray[i][j];
                switch (arrNew) {
                    case 0:
                        recNew[i][j].setFill(Color.BLACK);
                        break;

                    case 1:
                        recNew[i][j].setFill(Color.WHITE);
                        break;

                    case 2:
                        recNew[i][j].setFill(Color.LIGHTGRAY);
                        break;

                    case 3:
                        recNew[i][j].setFill(Color.DARKGRAY);
                        break;

                    case 4:
                        recNew[i][j].setFill(Color.GREY);
                        break;

                }

            }
        }
        for (NodeClass node : shortestPath) {
            // StdDraw.setPenColor(StdDraw.BLUE);
            try {

                recNew[node.getY()][node.getX()].setFill(Color.CORAL);
                recNew[node.getY()][node.getX()].setStroke(Color.BLUE);

                //   StdDraw.line(node.getParent().getY(), a.length-node.getParent().getX()-1, node.getY(), a.length-node.getX()-1);
            } catch (Exception e) {
            }

        }
        recNew[start.getY()][start.getX()].setFill(Color.BLUEVIOLET);

        recNew[end.getY()][end.getX()].setFill(Color.BLUEVIOLET);

        return pane;

//        StdDraw.setPenColor(StdDraw.DARK_GRAY);
//        StdDraw.filledCircle(start.getY(),a.length-start.getX()-1, .5);
//        StdDraw.setPenColor(StdDraw.BLACK);
//        StdDraw.text(start.getY(),a.length-start.getX()-1,"A");
//        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.filledCircle(end.getY(),a.length-end.getX()-1, .5);
//        StdDraw.setPenColor(StdDraw.BLACK);
//        StdDraw.text(end.getY(),a.length-end.getX()-1,"B");
    }

    /*

    private void topToBottomPathSmooth(boolean [][]a ,Node path,List<Node> shortestPath){

            try {
                if (a[path.getX()+1][path.getY()]) {
                    shortestPath.add(new Node(path.getX()+1, path.getY()));
                }else {
                    shortestPath.add(new Node(path.getX(), path.getY()-1));
                }
            }catch (ArrayIndexOutOfBoundsException e){
                if (a[path.getX()][path.getY()-1]){
                    shortestPath.add(new Node(path.getX(), path.getY()-1));
                }
            }
    }

    private void bottomToTopPathSmooth(boolean [][]a ,Node path,List<Node> shortestPath){
        try {
            if (a[path.getX()-1][path.getY()]) {
                shortestPath.add(new Node(path.getX()-1, path.getY()));
            }else {
                shortestPath.add(new Node(path.getX(), path.getY()+1));
            }
        }catch (ArrayIndexOutOfBoundsException e){
            if (a[path.getX()][path.getY()+1]){
                shortestPath.add(new Node(path.getX(), path.getY()+1));
            }
        }
    }

     */

    public void calculateCostMan(int x, int y, int[][] newArray, NodeClass parent) throws Exception {
        int N = newArray.length;
        if (x < 0 || x >= N) {
            return;
        }
        if (y < 0 || y >= N) {
            return;
        }
        //  if(!newArray[x][y])return;

        NodeClass n = new NodeClass(x, y);

        if (newArray[y][x] != 0) { //ignore the black cells

            double gValue = manhatanDistance(parent.getX(), parent.getY(), x, y) + newArray[y][x]; //draw the path wth coordinates with using weights

            //    double gValue =euclideanDistance(parent.getX(),parent.getY(),x,y);
            //int gValue=chebyshevDistance(parent.getX(),parent.getY(),x,y);
//        for(int j =0; j <3;j++){
//            if(j==0){
            // double gValue =euclideanDistance(parent.getX(),parent.getY(),x,y);
            if (n.equals(end)) {
                end.setParent(parent);
                end.setMoveCost(gValue + parent.getMoveCost());
                end.setParent(parent);
                throw new Exception("Goal Found");
            }

            if (!(openList.contains(n) || closedList.contains(n))) {
//             n.setHeuristic(manhatanDistance(x, y, end.getX(), end.getY()));
//              n.setHeuristic(euclideanDistance(x, y, end.getX(), end.getY()));
//            n.setHeuristic(chebyshevDistance(x, y, end.getX(), end.getY()));
                n.setParent(parent);
                n.setMoveCost(gValue + parent.getMoveCost());
                openList.add(n);
            }

//            }else{
//             System.out.println( "manhatan path was not found");  
//            }
//            openList.clear();
//        }
        } else {

        }
    }

    public void calculateCostEuc(int x, int y, int[][] newArray, NodeClass parent) throws Exception {
        int N = newArray.length;
        if (x < 0 || x >= N) {
            return;
        }
        if (y < 0 || y >= N) {
            return;
        }
        //  if(!newArray[x][y])return;

        NodeClass n = new NodeClass(x, y);

        if (newArray[y][x] != 0) {

            //double gValue = manhatanDistance(parent.getX(),parent.getY(),x,y);
            double gValue = euclideanDistance(parent.getX(), parent.getY(), x, y) + newArray[y][x]; //draw the path wth coordinates with using weights
            //int gValue=chebyshevDistance(parent.getX(),parent.getY(),x,y);

//        for(int j =0; j <3;j++){
//            if(j==0){
            // double gValue =euclideanDistance(parent.getX(),parent.getY(),x,y);
            if (n.equals(end)) {
                end.setParent(parent);
                end.setMoveCost(gValue + parent.getMoveCost());
                end.setParent(parent);
                throw new Exception("Goal Found");
            }

            if (!(openList.contains(n) || closedList.contains(n))) {
//             n.setHeuristic(manhatanDistance(x, y, end.getX(), end.getY()));
//              n.setHeuristic(euclideanDistance(x, y, end.getX(), end.getY()));
//            n.setHeuristic(chebyshevDistance(x, y, end.getX(), end.getY()));
                n.setParent(parent);
                n.setMoveCost(gValue + parent.getMoveCost());
                openList.add(n);
            }

//            }else{
//             System.out.println( "manhatan path was not found");  
//            }
//            openList.clear();
//        }
        } else {

        }

    }

    public void calculateCostChey(int x, int y, int[][] newArray, NodeClass parent) throws Exception {
        int N = newArray.length;
        if (x < 0 || x >= N) {
            return;
        }
        if (y < 0 || y >= N) {
            return;
        }
        //  if(!newArray[x][y])return;

        NodeClass n = new NodeClass(x, y);

        if (newArray[y][x] != 0) {
            //double gValue = manhatanDistance(parent.getX(),parent.getY(),x,y);
            //double gValue =euclideanDistance(parent.getX(),parent.getY(),x,y);
            double gValue = chebyshevDistance(parent.getX(), parent.getY(), x, y) + newArray[y][x];

//        for(int j =0; j <3;j++){
//            if(j==0){
            // double gValue =euclideanDistance(parent.getX(),parent.getY(),x,y);
            if (n.equals(end)) {
                end.setParent(parent);
                end.setMoveCost(gValue + parent.getMoveCost());
                end.setParent(parent);
                throw new Exception("Goal Found");
            }

            if (!(openList.contains(n) || closedList.contains(n))) {
//             n.setHeuristic(manhatanDistance(x, y, end.getX(), end.getY()));
//              n.setHeuristic(euclideanDistance(x, y, end.getX(), end.getY()));
//            n.setHeuristic(chebyshevDistance(x, y, end.getX(), end.getY()));
                n.setParent(parent);
                n.setMoveCost(gValue + parent.getMoveCost());
                openList.add(n);
            }

//            }else{
//             System.out.println( "manhatan path was not found");  
//            }
//            openList.clear();
//        }
        } else {

        }

    }

    public int manhatanDistance(int x1, int y1, int x2, int y2) {
        int distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);
        return distance;
    }

    public double euclideanDistance(int x1, int y1, int x2, int y2) {
        double distance = Math.round((Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2))) * 10) / 10.0;
        return distance;
    }

    public int chebyshevDistance(int x1, int y1, int x2, int y2) {
        int distance = Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
        return distance;

    }

}
