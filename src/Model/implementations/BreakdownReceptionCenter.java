package Model.implementations;

import Controller.ParkController;
import Model.contracts.CustomExecutorService;
import Model.contracts.MessageBroker;
import Model.contracts.Subscriber;
import Model.contracts.TopicType;

import java.util.concurrent.ExecutorService;

public class BreakdownReceptionCenter implements Subscriber {
    private MessageBroker messageBroker;
    private String name;
    private ExecutorService maintenanceOperatorPool;
    private ParkController parkController;

    public BreakdownReceptionCenter(String name, MessageBroker messageBroker, ExecutorService maintenanceOperatorPool, ParkController parkController){
        this.messageBroker = messageBroker;
        this.name = name;
        this.maintenanceOperatorPool = maintenanceOperatorPool;
        this.parkController = parkController;
    }

    @Override
    public void subscribe(TopicType topic){
        this.messageBroker.subscribe(topic, this);
    }

    @Override
    public void unsubscribe(TopicType topic){
        this.messageBroker.unsubscribe(topic, this);
    }

    @Override
    public void receiveMessage(TopicType topic, Fault fault){
        if(topic.equals(TopicType.AVERIA)){
            MaintenanceTask maintenanceTask = new MaintenanceTask(fault, messageBroker, parkController);
            maintenanceOperatorPool.submit(maintenanceTask);
        }else if(topic.equals(TopicType.REPAIRMENT)){

        }


    }
    public static Builder builder(){
        return new BreakdownReceptionCenter.Builder();
    }

    public static class Builder{
        private MessageBroker messageBrokerBuilder;
        private String nameBuilder;
        private ParkController parkController;
        private ExecutorService maintenanceOperatorPoolBuilder;

        public Builder messageBroker(MessageBroker messageBroker){
            this.messageBrokerBuilder = messageBroker;
            return this;
        }
        public Builder name(String name){
            this.nameBuilder = name;
            return this;
        }
        public Builder parkController(ParkController parkController){
            this.parkController = parkController;
            return this;
        }
        public Builder maintenanceOperatorPool(ExecutorService maintenanceOperatorPool){
            this.maintenanceOperatorPoolBuilder = maintenanceOperatorPool;
            return this;
        }
        public BreakdownReceptionCenter build(){
            if(this.nameBuilder == null){
                this.nameBuilder = "";
            }
            if(messageBrokerBuilder==null){
                throw new IllegalArgumentException("Missing message broker");
            }
            if(maintenanceOperatorPoolBuilder==null){
                throw new IllegalArgumentException("Missing maintenance operator pool");
            }
            return new BreakdownReceptionCenter(nameBuilder, messageBrokerBuilder, maintenanceOperatorPoolBuilder, parkController);
        }
    }


}
