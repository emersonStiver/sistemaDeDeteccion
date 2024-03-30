package Model.implementations;

import Model.contracts.MessageBroker;
import Model.contracts.Publisher;

public class TorniqueteEntrada implements Publisher {
    private final MessageBroker messageBroker;

    public TorniqueteEntrada(MessageBroker messageBroker){
        this.messageBroker = messageBroker;
    }
    @Override
    public void sendMessage(){
        //messageBroker.publish(topic, fault);
    }
}
