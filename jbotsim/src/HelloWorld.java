/**
 * Depassement du vehicule SEME 10/11/2020
 * @author Dorine Tabary
 * @version jbotsim 1.2.0
 **/


import io.jbotsim.core.Node;
import io.jbotsim.core.Topology;
import io.jbotsim.ui.JViewer;

import java.awt.*;

public class HelloWorld{
    public static void main(String[] args){

        Topology tp = new Topology();
        JViewer jv = new JViewer(tp);
        jv.getJTopology().addBackgroundPainter(new MyBackgroundPainter());
        tp.setNodeModel("VehiculeInFront", OppositeVehicle.class);
        //tp.setNodeModel("Node", Node.class);
        //tp.setNodeModel("StoppedNodes", StoppedNode.class);
        tp.setNodeModel("AutonomousCar", theCar.class);


        Node s1 = new StoppedNode();
        Node s2 = new StoppedNode();
        Node s3 = new StoppedNode();
        Node c = new theCar();
        tp.addNode(200, 225, s1);
        tp.addNode(500, 225, s2);
        tp.addNode(580, 225, s3);
        tp.start();
    }
}