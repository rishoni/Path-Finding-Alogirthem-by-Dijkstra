/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxPackage;

/**
 *
 * @author Rishoni De Silve
         * student id : 2016357
 */

public class NodeClass {
    private int heuristic;
    private double moveCost;
    private int x;
    private int y;
    private NodeClass parent;

    public NodeClass(){

    }

    public NodeClass(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public double getMoveCost() {
        return moveCost;
    }

    public void setMoveCost(double moveCost) {
        this.moveCost = moveCost;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public NodeClass getParent() {
        return parent;
    }

    public void setParent(NodeClass parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        NodeClass n= (NodeClass) obj;
        if(n.getX()==x && n.getY()==y){

            return true;
        }else {
            return false;
        }

    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        //String p = (parent!=null)?" || "+parent.toString()+ " || ":"Root";
        return x + " "+ y+" "+moveCost+" "+heuristic+" ";
    }
}

