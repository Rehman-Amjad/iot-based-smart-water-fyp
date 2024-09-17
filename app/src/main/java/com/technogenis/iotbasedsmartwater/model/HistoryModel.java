package com.technogenis.iotbasedsmartwater.model;

public class HistoryModel {

    String Id;
    String Dated;
    String TDSSensor;
    String TemperatureSensor;
    String TurbiditySensor;
    String Waterlavel;
    String Timed;

    public HistoryModel(String id, String dated, String TDSSensor, String temperatureSensor, String turbiditySensor, String waterlavel, String timed) {
        Id = id;
        Dated = dated;
        this.TDSSensor = TDSSensor;
        TemperatureSensor = temperatureSensor;
        TurbiditySensor = turbiditySensor;
        Waterlavel = waterlavel;
        Timed = timed;
    }

    public HistoryModel() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDated() {
        return Dated;
    }

    public void setDated(String dated) {
        Dated = dated;
    }

    public String getTDSSensor() {
        return TDSSensor;
    }

    public void setTDSSensor(String TDSSensor) {
        this.TDSSensor = TDSSensor;
    }

    public String getTemperatureSensor() {
        return TemperatureSensor;
    }

    public void setTemperatureSensor(String temperatureSensor) {
        TemperatureSensor = temperatureSensor;
    }

    public String getTurbiditySensor() {
        return TurbiditySensor;
    }

    public void setTurbiditySensor(String turbiditySensor) {
        TurbiditySensor = turbiditySensor;
    }

    public String getWaterlavel() {
        return Waterlavel;
    }

    public void setWaterlavel(String waterlavel) {
        Waterlavel = waterlavel;
    }

    public String getTimed() {
        return Timed;
    }

    public void setTimed(String timed) {
        Timed = timed;
    }
}
