package Model.contracts;

import Model.implementations.MaintenanceTask;

public interface CustomExecutorService {
    void submit(MaintenanceTask runnable);
    void shutDown();
}
