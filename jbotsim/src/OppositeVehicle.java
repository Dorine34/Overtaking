/**
 * Depassement du vehicule SEME 10/11/2020
 * @author Dorine Tabary
 * @version jbotsim 1.2.0
 **/

/*Car in front*/



import io.jbotsim.core.Message;
import io.jbotsim.core.Color;
import io.jbotsim.core.Node;

public class OppositeVehicle extends Node {

    boolean informed;
    boolean accident ;


    @Override
    public void onStart() {
        setIcon("/car.png");
        setIconSize(30);
        informed = false;
        accident=false;
        //setDirection(Math.random()*2*Math.PI);
        setDirection(3.14);
        setColor(Color.yellow);

        setCommunicationRange(150);
    }
    @Override
    public void onClock(){
        if (accident)
        {
            move(0);
        }
        else
        {

            move(2);
        }
        wrapLocation();


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