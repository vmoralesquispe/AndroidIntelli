package com.example.intellisoftgloriaaccesomovil.BD;

import java.io.Serializable;

public class Acompañantes implements Serializable {

    private String TipoDoc;
    private Integer NumDoc;
    private Integer NumDocVisita;
    private String Nombres;
    private String Paterno;
    private String Materno;
    private String Correo;
    private String Empresa;
    private Integer RUC;
    private String VisitaTecnica;
    private String Seguro;
    private String SOAT;
    private String Poliza;
    private String SCTR;
    private String Induc_Seg;

    public Acompañantes(String tipoDoc, Integer numDoc, Integer numDocVisita, String nombres,
                        String paterno, String materno, String correo, String empresa, Integer RUC,
                        String visitaTecnica, String seguro, String SOAT, String poliza, String SCTR,
                        String induc_Seg) {
        TipoDoc = tipoDoc;
        NumDoc = numDoc;
        NumDocVisita = numDocVisita;
        Nombres = nombres;
        Paterno = paterno;
        Materno = materno;
        Correo = correo;
        Empresa = empresa;
        this.RUC = RUC;
        VisitaTecnica = visitaTecnica;
        Seguro = seguro;
        this.SOAT = SOAT;
        Poliza = poliza;
        this.SCTR = SCTR;
        Induc_Seg = induc_Seg;
    }

    public Acompañantes() {
    }

    public String getTipoDoc() {
        return TipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        TipoDoc = tipoDoc;
    }

    public Integer getNumDoc() {
        return NumDoc;
    }

    public void setNumDoc(Integer numDoc) {
        NumDoc = numDoc;
    }

    public Integer getNumDocVisita() {
        return NumDocVisita;
    }

    public void setNumDocVisita(Integer numDocVisita) {
        NumDocVisita = numDocVisita;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getPaterno() {
        return Paterno;
    }

    public void setPaterno(String paterno) {
        Paterno = paterno;
    }

    public String getMaterno() {
        return Materno;
    }

    public void setMaterno(String materno) {
        Materno = materno;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }

    public Integer getRUC() {
        return RUC;
    }

    public void setRUC(Integer RUC) {
        this.RUC = RUC;
    }

    public String getVisitaTecnica() {
        return VisitaTecnica;
    }

    public void setVisitaTecnica(String visitaTecnica) {
        VisitaTecnica = visitaTecnica;
    }

    public String getSeguro() {
        return Seguro;
    }

    public void setSeguro(String seguro) {
        Seguro = seguro;
    }

    public String getSOAT() {
        return SOAT;
    }

    public void setSOAT(String SOAT) {
        this.SOAT = SOAT;
    }

    public String getPoliza() {
        return Poliza;
    }

    public void setPoliza(String poliza) {
        Poliza = poliza;
    }

    public String getSCTR() {
        return SCTR;
    }

    public void setSCTR(String SCTR) {
        this.SCTR = SCTR;
    }

    public String getInduc_Seg() {
        return Induc_Seg;
    }

    public void setInduc_Seg(String induc_Seg) {
        Induc_Seg = induc_Seg;
    }
}
