/**
 * Depassement du vehicule SEME 10/11/2020
 * @author Dorine Tabary
 * @version jbotsim 1.2.0
 **/

/*Autonomous car*/

import io.jbotsim.core.Message;
import io.jbotsim.core.Color;
import io.jbotsim.core.Node;

public class theCar extends Node {

    boolean informed;
    boolean right;
    boolean left;
    boolean depassement;
    boolean redCar ;
    boolean yellowCar ;
    boolean voitureEnFace ;

    boolean accident ;

    @Override
    public void onStart() {
        setIcon("/car.png");
        setIconSize(30);
        informed = false;
        //setDirection(Math.random()*2*Math.PI);
        setDirection(0);
        right=true;
        left=false;
        depassement = false;
        redCar= false;
        yellowCar= false;
        voitureEnFace= false;
        accident= false;

        setCommunicationRange(150);



    }
    @Override
    public void onClock(){
        //Vehicle speed
        if ((!voitureEnFace)&& (!accident))
        {
            move(2);
        }
        else
        {
            move(0);
        }

        wrapLocation();

        //Neighbors met
        for (int i=0; i<this.getInNeighbors().size();i++)
        {
            if (this.getInNeighbors().get(i).getColor().equals(Color.RED))
            {
                redCar=true;
            }
            if (this.getInNeighbors().get(i).getColor().equals(Color.YELLOW))
            {
                yellowCar=true;
            }
        }

        //vehicule in front
        if(this.hasNeighbors() &&yellowCar &&redCar && right)
        {
            System.out.println("WAITING FOR OVERTAKING");
            voitureEnFace=true;
        }
        if(this.hasNeighbors() &&!yellowCar &&redCar )
        {
            voitureEnFace=false;
        }

        //accident
        if(this.hasNeighbors() &&yellowCar &&redCar && left)
        {
            System.out.println("ACCIDENT");
            accident=true;
            this.setColor(Color.black);
        }

        //Overtaking
        if(this.hasNeighbors()&&right &&redCar  )
        {
            System.out.println("OVERTAKING");
            setDirection(-0.7);
            right=false;
        }

        if (this.getLocation().y < 175)
        {
            setDirection(0);
            left=true;
            depassement =true;
        }

        if(!this.hasNeighbors()&&left)
        {
            setDirection(0.7);
            left=false;
        }

        if ((this.getLocation().y > 225) && depassement)
        {
            System.out.println("END OF OVERTAKING");
            setDirection(0);
            right=true;
            depassement=false;
        }



        redCar=false;
        yellowCar=false;

    }
    @Override
    public void onSelection() {
        informed = true;
        setColor(Color.RED);
        sendAll(new Message("I'm here"));
    }

    @Override
    public void onMessage(Message message) {
        if (! informed) {
            informed = true;
            setColor(Color.RED);
            sendAll(new Message(message.getContent()));
        }
    }
}