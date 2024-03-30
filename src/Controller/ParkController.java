package Controller;

import Model.contracts.*;
import Model.implementations.*;
import view.AttractionLogPanel;
import view.OperatorsLogCenter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParkController implements EventController{
    MessageBroker messageBroker;
    private CustomExecutorService maintenancePool;
    private ExecutorService executorService;
    private Subscriber breakdownReceptionCenter;
    private OperatorsLogCenter operatorCenterPanel;
    private Subscriber parkController;
    private AttractionLogPanel logsMontana, logsRueda;
    public ParkController(OperatorsLogCenter operatorsPanel, AttractionLogPanel logMontana, AttractionLogPanel logRueda){
        this.operatorCenterPanel = operatorsPanel;
        this.logsRueda = logRueda;
        this.logsMontana = logMontana;
        this.messageBroker = NotificationCenter.getInstance();
        this.parkController = new ParkAttractionController(messageBroker, this);
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.breakdownReceptionCenter = BreakdownReceptionCenter
                .builder()
                .name("Central de reception de averias")
                .messageBroker(messageBroker)
                .maintenanceOperatorPool(executorService)
                .parkController(this)
                .build();
        this.breakdownReceptionCenter.subscribe(TopicType.AVERIA);
    }

    @Override
    public void reportError(String attraction, int id ){
        AttractionController controller = (AttractionController) parkController;
        controller.reportError(attraction, id);
    }

    @Override
    public void startAttraction(int id ){
        AttractionController controller = (AttractionController) parkController;
        controller.startAttraction(id);
    }

    @Override
    public void stopAttraction(int id ){
        AttractionController controller = (AttractionController) parkController;
        controller.stopAttraction(id);
    }
    @Override
    public void repairAttraction(int i){
        AttractionController controller = (AttractionController) parkController;
        controller.repairAttraction(i);
    }

    @Override
    public void postEnd(Fault fault){
        operatorCenterPanel.addLog(fault.toString());
        operatorCenterPanel.addLog("\n");
        operatorCenterPanel.addLog("\n");
    }
    @Override
    public void postStart(Fault fault){
        operatorCenterPanel.addLog(fault.toString());
        operatorCenterPanel.addLog("\n");
        operatorCenterPanel.addLog("\n");
    }

    public void logAttractionEvents(String msg, AttractionType type){
        if(type.equals(AttractionType.MONTANA_RUSA)){
            System.out.println("ENTRE A MOTNANA");
            logsMontana.logMessage(msg +  " \n");
        }else if(type.equals(AttractionType.RUEDA_NORIA)){
            System.out.println("Entre a rueda");
            logsRueda.logMessage(msg + " \n");
        }
    }

}
