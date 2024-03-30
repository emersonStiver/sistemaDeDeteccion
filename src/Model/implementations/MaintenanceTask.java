package Model.implementations;

import Controller.ParkController;
import Model.contracts.MessageBroker;
import Model.contracts.TopicType;

import java.util.Random;

public class MaintenanceTask implements  Runnable{
    private Fault fault;
    private MessageBroker messageBroker;
    private ParkController parkController;
    public MaintenanceTask(Fault fault, MessageBroker messageBroker, ParkController parkController){
        this.parkController = parkController;
        this.fault = fault;
        this.messageBroker = messageBroker;
    }

    @Override
    public void run(){
        try {
            parkController.postStart(fault);
            Thread.sleep(new Random().nextLong(2000,10000));
            fault.setIsSolved(true);
            messageBroker.publish(TopicType.REPAIRMENT, fault);
            parkController.postEnd(fault);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
