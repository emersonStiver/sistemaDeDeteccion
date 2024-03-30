package Model.implementations;

import Model.contracts.AttractionType;
import Model.contracts.MessageBroker;
import Model.contracts.Publisher;
import Model.contracts.TopicType;

public class Cabina implements Publisher {
    private int id;
    private boolean isEnabled;
    private int capacity;
    private MessageBroker messageBroker;
    private AttractionType attributeType;
    public Cabina(MessageBroker messageBroker, int capacity, int id, AttractionType attributeType) {
        this.capacity = capacity;
        this.messageBroker = messageBroker;
        this.isEnabled = true;
        this.attributeType = attributeType;
        this.id = id;

    }
    public boolean getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    @Override
    public void sendMessage(){
        messageBroker.publish(TopicType.AVERIA,
                 Fault.builder()
                 .id(this.id)
                .message("Averia in cabina " +  this.id)
                .attractionType(this.attributeType)
                .build());
        setIsEnabled(false);
    }

}
