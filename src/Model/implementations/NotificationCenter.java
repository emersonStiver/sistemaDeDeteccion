package Model.implementations;

import Model.contracts.MessageBroker;
import Model.contracts.Subscriber;
import Model.contracts.TopicType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class NotificationCenter implements MessageBroker {
    private static volatile NotificationCenter instance;
    private ReentrantReadWriteLock lock;
    private ReentrantReadWriteLock.ReadLock readLock;
    private ReentrantReadWriteLock.WriteLock writeLock;
    private Map<TopicType, Set<Subscriber>> topics;
    public static NotificationCenter getInstance(){
        //Optimization
        if(instance == null){
            synchronized (NotificationCenter.class){
                //Verification
                if(instance == null){
                    return instance = new NotificationCenter();
                }
            }
        }
        return instance;
    }
    private NotificationCenter() {
        this.topics  = new HashMap<>();
        this.lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
    }

    @Override
    public void subscribe(TopicType topic, Subscriber subscriber){
        this.writeLock.lock();
        this.topics.computeIfAbsent(topic, t -> new HashSet<>()) .add(subscriber);
        this.writeLock.unlock();
    }

    @Override
    public void unsubscribe(TopicType topic, Subscriber subscriber){
        this.writeLock.lock();
        Set<Subscriber> subscribers = topics.get(topic);
        if(subscribers != null){
            subscribers.remove(subscriber);
        }
        this.writeLock.unlock();
    }
    @Override
    public void publish(TopicType topic, Fault fault){
        this.readLock.lock();
        Set<Subscriber> subs = topics.get(topic);
        if(subs != null){
            for(Subscriber subscriber : subs){
                subscriber.receiveMessage(topic, fault);
            }
        }
        this.readLock.unlock();
    }
}
