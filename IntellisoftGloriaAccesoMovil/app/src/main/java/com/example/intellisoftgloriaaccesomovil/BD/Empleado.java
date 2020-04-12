package com.example.intellisoftgloriaaccesomovil.BD;

import java.io.Serializable;

public class Empleado implements Serializable {

    private Integer idEmpleado;
    private String codInterno;
    private String tipoDocumento;
    private String numDocumento;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String sede;
    private String codTarjeta;
    private byte[] foto;

    public Empleado(Integer idEmpleado, String codInterno, String tipoDocumento, String numDocumento, String nombre,
                    String apellidoPaterno, String apellidoMaterno, String sede, String codTarjeta, byte[] foto) {
        this.idEmpleado = idEmpleado;
        this.codInterno = codInterno;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.sede = sede;
        this.codTarjeta = codTarjeta;
        this.foto = foto;
    }

    public Empleado() {

    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCodInterno() {
        return codInterno;
    }

    public void setCodInterno(String codInterno) {
        this.codInterno = codInterno;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getCodTarjeta() {
        return codTarjeta;
    }

    public void setCodTarjeta(String codTarjeta) {
        this.codTarjeta = codTarjeta;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
