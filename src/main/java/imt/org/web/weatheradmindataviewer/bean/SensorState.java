package imt.org.web.weatheradmindataviewer.bean;
//@TODO placer cette classe au bonne endroit
public enum SensorState {
    DECONNECTE("Déconnecté"),
    NORMAL("Normal"),
    ALERTE("En alerte");

    private String name="";

    SensorState(String name){
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
