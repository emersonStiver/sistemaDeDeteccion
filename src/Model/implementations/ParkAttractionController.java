package Model.implementations;

import Controller.ParkController;
import Model.contracts.AttractionController;
import Model.contracts.MessageBroker;
import Model.contracts.Subscriber;
import Model.contracts.TopicType;

import java.sql.SQLOutput;

public class ParkAttractionController implements Subscriber, AttractionController {
    private final MessageBroker messageBroker;
    private Attraction[] attractions;
    public ParkAttractionController(MessageBroker messageBroker, ParkController controller){
        this.messageBroker = messageBroker;

        MontanaRusa m = new MontanaRusa(1, this.messageBroker, controller);
        m.subscribe(TopicType.AVERIA);
        m.subscribe(TopicType.REPAIRMENT);

        RuedaNoria r = new RuedaNoria(2, this.messageBroker,controller );
        r.subscribe(TopicType.AVERIA);
        r.subscribe(TopicType.REPAIRMENT);

        attractions = new Attraction[]{m,r};
    }
    @Override
    public void reportError(String attraction, int id){
        if(attraction.equals("Montana")){
            attractions[0].initiateError(id);
        }else if(attraction.equals("Rueda")){
            attractions[1].initiateError(id);
        }
    }
    @Override
    public void subscribe(TopicType topic){
        messageBroker.subscribe(topic, this);
    }

    @Override
    public void unsubscribe(TopicType topic){
        messageBroker.unsubscribe(topic, this);
    }

    @Override
    public void receiveMessage(TopicType topic, Fault fault){

    }
    public void startAttraction(int code){
        for(Attraction attraction: attractions){
            if(attraction.getId() == code){
                attraction.start();
            }
        }
    }
    public void stopAttraction(int code){
        for(Attraction attraction: attractions){
            if(attraction.getId() == code){
                attraction.stop();
            }
        }
    }

    public void repairAttraction(int code){
        for(Attraction attraction: attractions){
            if(attraction.getId() == code){
                attraction.startMaintanance();
            }
        }
    }
}
