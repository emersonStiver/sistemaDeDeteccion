package Controller;

import Model.implementations.Fault;

public interface EventController {

    void reportError(String attraction, int id );

    void startAttraction( int id  );
    void stopAttraction( int id );

    void postEnd(Fault fault);
    void postStart(Fault fault);

    void repairAttraction(int i);
}
