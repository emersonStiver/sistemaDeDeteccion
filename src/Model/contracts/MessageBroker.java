package Model.contracts;

import Model.implementations.Fault;

public interface MessageBroker {
    void subscribe(TopicType topic, Subscriber subscriber);
    void unsubscribe(TopicType topic, Subscriber subscriber);
    void publish(TopicType topic, Fault Fault);

}
