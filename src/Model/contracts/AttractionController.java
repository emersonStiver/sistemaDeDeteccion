package Model.contracts;

public interface AttractionController {
    void startAttraction(int id);
    void stopAttraction(int id);
    void reportError(String attraction, int id);
    void repairAttraction(int code);
}
