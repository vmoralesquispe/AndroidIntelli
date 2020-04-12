package com.example.intellisoftgloriaaccesomovil.BD;

import java.io.Serializable;

public class Marca implements Serializable {

    private Integer idEmpleado;
    private Float dateTime;
    private String tipo;
    private String placaVehiculo;
    private Float indSyncCentra;



    public Marca() {
    }

    public Marca(Integer idEmpleado, Float dateTime, String tipo, String placaVehiculo, Float indSyncCentra) {
        this.idEmpleado = idEmpleado;
        this.dateTime = dateTime;
        this.tipo = tipo;
        this.placaVehiculo = placaVehiculo;
        this.indSyncCentra = indSyncCentra;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Float getDateTime() {
        return dateTime;
    }

    public void setDateTime(Float dateTime) {
        this.dateTime = dateTime;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public Float getIndSyncCentra() {
        return indSyncCentra;
    }

    public void setIndSyncCentra(Float indSyncCentra) {
        this.indSyncCentra = indSyncCentra;
    }
}
