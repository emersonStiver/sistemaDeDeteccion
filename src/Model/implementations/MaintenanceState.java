package Model.implementations;

import Controller.ParkController;
import Model.contracts.State;

public class MaintenanceState  implements State {
    private Attraction attraction;
    private ParkController controller;
    public MaintenanceState(Attraction attraction, ParkController controller) {
        this.attraction = attraction;
        this.controller = controller;
    }
    @Override
    public void start( ){
        if(!attraction.isUnderMaintenance()){
            attraction.setState(attraction.getRunningState());
            controller.logAttractionEvents("Starting " +  attraction.getAttractionType(), attraction.getAttractionType());
        }else {
            controller.logAttractionEvents(attraction.getAttractionType() + " is still under maintenance", attraction.getAttractionType());
        }
    }

    @Override
    public void stop(){
        controller.logAttractionEvents( attraction.getAttractionType() +" is already stopped", attraction.getAttractionType());
    }

    @Override
    public void initiateMaintenance( ){
        controller.logAttractionEvents(attraction.getAttractionType() + " is already under maintenance", attraction.getAttractionType());

    }
}
