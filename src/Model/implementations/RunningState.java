package Model.implementations;

import Controller.ParkController;
import Model.contracts.AttractionType;
import Model.contracts.State;

public class RunningState implements State {
    private Attraction attraction;
    private ParkController controller;
    public RunningState(Attraction attraction, ParkController controller) {
        this.attraction = attraction;
        this.controller = controller;
    }
    @Override
    public void start(){
        if(attraction.getState() == this){
            controller.logAttractionEvents(attraction.getAttractionType() + " is already running", attraction.getAttractionType());
        }else{
            controller.logAttractionEvents("Starting " +  attraction.getAttractionType(), attraction.getAttractionType());
            attraction.setState(attraction.getRunningState());
            attraction.setIsRunning(true);
        }
    }

    @Override
    public void stop(){
        if(attraction.getState() == attraction.getStoppedState()){
            controller.logAttractionEvents("The " +  attraction.getAttractionType() + " is already stopped", attraction.getAttractionType());

        }else{
            controller.logAttractionEvents("Stopping " +  attraction.getAttractionType() , attraction.getAttractionType());
            attraction.setState(attraction.getStoppedState());
        }

    }

    @Override
    public void initiateMaintenance(){
        if(attraction.getState() == attraction.getRunningState()){
            controller.logAttractionEvents("The " +  attraction.getAttractionType() + " is running, stop it first in order to do the maintenance", attraction.getAttractionType());
        }else if(attraction.getState() == this){
            controller.logAttractionEvents("The " +  attraction.getAttractionType() + " is already under maintenance", attraction.getAttractionType());
        }else{
            controller.logAttractionEvents("Starting maintenance for  " +  attraction.getAttractionType(), attraction.getAttractionType());
        }
    }
}
