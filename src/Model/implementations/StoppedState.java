package Model.implementations;

import Controller.ParkController;
import Model.contracts.State;

public class StoppedState implements State {
    private Attraction attraction;
    private ParkController controller;
    public StoppedState(Attraction attraction, ParkController controller) {
        this.attraction = attraction;
        this.controller = controller;
    }
    @Override
    public void start( ){
        if(attraction.getState() == attraction.getMaintenanceState()){
            controller.logAttractionEvents("The " +  attraction.getAttractionType() + " is under maintenance, can't be started", attraction.getAttractionType());
        }else {
            controller.logAttractionEvents("Starting " +  attraction.getAttractionType(), attraction.getAttractionType());
            attraction.setState(attraction.getRunningState());
        }
    }

    @Override
    public void stop(){
        if(attraction.getState() == this){
            controller.logAttractionEvents("The " +  attraction.getAttractionType() + " is already stopped", attraction.getAttractionType());
        }else if(attraction.getState() == attraction.getMaintenanceState()){
            controller.logAttractionEvents("The " +  attraction.getAttractionType() + " is already stopped, because it is under maintenance", attraction.getAttractionType());
        }else{
            controller.logAttractionEvents("The " +  attraction.getAttractionType() + " is stopped", attraction.getAttractionType());

        }
    }

    @Override
    public void initiateMaintenance( ){
        if(attraction.getState() == this){
            controller.logAttractionEvents("Starting maintenance for  " +  attraction.getAttractionType(), attraction.getAttractionType());
        }else if(attraction.getState() == attraction.getMaintenanceState()){
            controller.logAttractionEvents(attraction.getAttractionType() + " is already under maintenance" , attraction.getAttractionType());
        }else{
            controller.logAttractionEvents("Stopping " +  attraction.getAttractionType(), attraction.getAttractionType());
            controller.logAttractionEvents("Starting maintenance for  " +  attraction.getAttractionType(), attraction.getAttractionType());
        }
    }
}
