package Model.implementations;

import Controller.ParkController;
import Model.contracts.AttractionType;
import Model.contracts.MessageBroker;
import Model.contracts.State;
import Model.contracts.Subscriber;

import java.util.HashMap;
import java.util.Map;

public abstract class Attraction implements Subscriber {
    private Map<Integer, Fault> faultList = new HashMap<>();
    private int id;
    private AttractionType type;
    private MessageBroker messageBroker;
    private boolean isUnderMaintenance, isRunning, isStopped;
    private State running, stopped, maintenanceState, state;
    private ParkController controller;

    public Attraction(int id, AttractionType type, MessageBroker messageBroker, ParkController controller) {
        this.id = id;
        this.type = type;
        this.messageBroker = messageBroker;
        this.controller = controller;
        this.running = new RunningState(this,controller );
        this.stopped = new StoppedState(this,controller);
        this.maintenanceState = new MaintenanceState(this,controller);
        this.state = this.stopped;
        this.isUnderMaintenance = false;
        this.isRunning = false;
        this.isStopped = true;
    }
    public ParkController getParkController(){
        return  this.controller;
    }
    public AttractionType getAttractionType() {
        return this.type;
    }
    public Map<Integer, Fault> getFaultMap() {
        return this.faultList;
    }
    public State getState() {
        return state;
    }
    public boolean getIsRunning() {
        return isRunning;
    }
    public boolean getIsStopped() {
        return isStopped;
    }
    public boolean getIsUnderMaintenance() {
        return isUnderMaintenance;
    }
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    public void setStopped(boolean stopped){
        this.isStopped = stopped;
    }
    public void setIsUnderMaintenance(boolean isUnderMaintenance) {
        this.isUnderMaintenance = isUnderMaintenance;
    }
    public State getRunningState(){
        return running;
    }
    public State getStoppedState(){
        return stopped;
    }
    public State getMaintenanceState(){return maintenanceState;}
    public void setState(State state) {
        this.state = state;
    }


    public int getId( ){
        return id;
    }

    public MessageBroker getMessageBroker( ){
        return this.messageBroker;
    }

    abstract void start();
    abstract void stop();

    public abstract void startMaintanance();

    abstract boolean isUnderMaintenance();
    abstract boolean isRunning();
    abstract boolean isStopped();

    abstract void initiateError(int id);
}
