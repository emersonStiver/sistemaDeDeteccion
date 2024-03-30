package Model.implementations;

import Controller.ParkController;
import Model.contracts.AttractionType;
import Model.contracts.MessageBroker;
import Model.contracts.TopicType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MontanaRusa extends Attraction  {
    private Cabina[] nCabina;
    public MontanaRusa(int id, MessageBroker messageBroker, ParkController controller) {
        super(id, AttractionType.MONTANA_RUSA , messageBroker,controller);
        this.nCabina = new Cabina[4];
        for (int i = 0; i < nCabina.length; i++) {
            nCabina[i] = new Cabina(getMessageBroker(), 4, i, AttractionType.MONTANA_RUSA);
        }
    }
    @Override
    public void start( ){ getState().start(); }

    @Override
    public void stop( ){
        getState().stop();
    }
    @Override
    public void startMaintanance(){
        getState().initiateMaintenance();
    }

    @Override
    public boolean isUnderMaintenance( ){
        return getIsUnderMaintenance();
    }

    @Override
    public boolean isRunning( ){
        return getIsRunning();
    }

    @Override
    public boolean isStopped( ){
        return getIsStopped();
    }

    @Override
    void initiateError(int id){
        nCabina[id].sendMessage();
    }



    @Override
    public void subscribe(TopicType topic){
        getMessageBroker().subscribe(topic, this);
    }

    @Override
    public void unsubscribe(TopicType topic){
        getMessageBroker().unsubscribe(TopicType.AVERIA, this);
    }

    @Override
    public void receiveMessage(TopicType topic, Fault fault){
        if(topic.equals(TopicType.AVERIA)){
            if(fault.getAttractionType().equals(AttractionType.MONTANA_RUSA)){
                getParkController().logAttractionEvents("Error con la montaña rusa en el vagon: " +  fault.getId(), this.getAttractionType());
                setStopped(true);
                setIsUnderMaintenance(true);
                setState(getMaintenanceState());
                nCabina[fault.getId()].setIsEnabled(false);
                getFaultMap().put(fault.getId() ,fault);
            }
        }else if(topic.equals(TopicType.REPAIRMENT)){
            if(fault.getAttractionType().equals(AttractionType.MONTANA_RUSA)){
                getFaultMap().remove(fault.getId());
                nCabina[fault.getId()].setIsEnabled(true);
                if(getFaultMap().size() == 0){
                    setIsUnderMaintenance(false);
                    setState(getStoppedState());
                    getParkController().logAttractionEvents("The Rueda Noria has been repared", this.getAttractionType());
                }
            }
        }
    }

}
