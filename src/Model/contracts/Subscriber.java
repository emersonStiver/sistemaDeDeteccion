package Model.contracts;

import Model.implementations.Fault;

public interface Subscriber {
    void subscribe(TopicType topic);
    void unsubscribe(TopicType topic);
    void receiveMessage(TopicType topic, Fault fault);
}
