package com.example.intellisoftgloriaaccesomovil.BD;

import android.provider.BaseColumns;

public class Utilidades {

    public static final class TB_Empleado implements BaseColumns{

        //Constantes campos tabla empleado----------------------------------------------
        public static final String TABLA_EMPLEADO="empleado";
        public static final String CAMPO_IDEMPLEADO="IdEmpleado";
        public static final String CAMPO_CODIGOINTERNO="CodigoInterno";
        public static final String CAMPO_TIPODOCUMENTO="TipoDocumento";
        public static final String CAMPO_NUMERODOCUMENTO="NumeroDocumento";
        public static final String CAMPO_NOMBRE="Nombre";
        public static final String CAMPO_APELLIDOPATERNO="ApellidoPaterno";
        public static final String CAMPO_APELLIDOMATERNO="ApellidoMaterno";
        public static final String CAMPO_SEDE="Sede";
        public static final String CAMPO_CODIGOTARJETA="CodigoTarjeta";
        public static final String CAMPO_FOTO="Foto";

    public static final String CREAR_TABLA_EMPLEADO="CREATE TABLE " +
            ""+TABLA_EMPLEADO+" ("+CAMPO_IDEMPLEADO+" INTEGER PRIMARY KEY, "+CAMPO_CODIGOINTERNO+" TEXT,"+CAMPO_TIPODOCUMENTO+" TEXT," +
            ""+CAMPO_NUMERODOCUMENTO+" TEXT, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_APELLIDOPATERNO+" TEXT," +
            ""+CAMPO_APELLIDOMATERNO+" TEXT, "+CAMPO_SEDE+" TEXT, "+CAMPO_CODIGOTARJETA+" TEXT, "+CAMPO_FOTO+" BLOB)";

    }

    public static final class TB_Empleado_BIO implements BaseColumns{

        //Constantes campos tabla empleadobio--------------------------------------------
        public static final String TABLA_EMPLEADO_BIO="empleadobio";
        public static final String CAMPO_ID_HUELLA="IdHuella";
        public static final String CAMPO_ID_EMPLEADO="IdEmpleado";
        public static final String CAMPO_IMAGEN_HUELLA="ImagenHuella";
        public static final String CAMPO_IMAGEN_HUELLA_TMP="ImagenHuellaTemp";

    public static final String CREAR_TABLA_EMPLEADO_BIO="CREATE TABLE " +
            ""+TABLA_EMPLEADO_BIO+" ("+CAMPO_ID_HUELLA+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_ID_EMPLEADO+" INTEGER, "+CAMPO_IMAGEN_HUELLA+" TEXT,"+CAMPO_IMAGEN_HUELLA_TMP+" TEXT)";

    }


    public static final class TB_Marca implements BaseColumns {

        //Constantes campos tabla marca--------------------------------------------------
        public static final String TABLA_MARCA="marca";
        public static final String CAMPO_IDEMPLEADO="IdEmpleado";
        public static final String CAMPO_FECHA_ACCESO="FechaAcceso";
        public static final String CAMPO_TIPO="Tipo";
        public static final String CAMPO_PLACA_VEHICULO="PlacaVehiculo";
        public static final String CAMPO_IND_SYNC_CENTRA="IndSyncCentra";

    public static final String CREAR_TABLA_MARCA="CREATE TABLE " +
            ""+TABLA_MARCA+" ("+CAMPO_IDEMPLEADO+" INTEGER PRIMARY KEY AUTOINCREMENT, " +CAMPO_FECHA_ACCESO+" NUMERIC, "+CAMPO_TIPO+" TEXT, "
            +CAMPO_PLACA_VEHICULO+" TEXT,"+CAMPO_IND_SYNC_CENTRA+" NUMERIC)";

    }

    public static final class TB_Visita implements BaseColumns {

        //Constantes campos tabla visita--------------------------------------------------
        public static final String TABLA_VISITA="visita";
        public static final String CAMPO_TIPDOC="Tipo_Documento";
        public static final String CAMPO_NUMDOC="Numero_Documento";
        public static final String CAMPO_NOMBRES="Nombres";
        public static final String CAMPO_PATERNO="Apellido_Paterno";
        public static final String CAMPO_MATERNO="Apellido_Materno";
        public static final String CAMPO_CORREO="Correo_Electronico";
        public static final String CAMPO_EMPRESA="Empresa";
        public static final String CAMPO_RUC="RUC";
        public static final String CAMPO_ENTRADAFECHAHORA="Entrada_FechaHora";
        public static final String CAMPO_SALIDAFECHAHORA="Salida_FechaHora";
        public static final String CAMPO_CONTACTO="Contacto";
        public static final String CAMPO_MOTIVO="Motivo";
        public static final String CAMPO_VISITA_TECNICA="Visita_Tecnica";
        public static final String CAMPO_SEGURO="Seguro";
        public static final String CAMPO_SOAT="SOAT";
        public static final String CAMPO_POLIZA="Poliza";
        public static final String CAMPO_SCTR="SCTR";
        public static final String CAMPO_INDUC_SEG="Induc_Seg";

        public static final String CREAR_TABLA_VISITA="CREATE TABLE " +
                ""+TABLA_VISITA+" ("+CAMPO_TIPDOC+" TEXT, "+CAMPO_NUMDOC+" INTEGER PRIMARY KEY, "+CAMPO_NOMBRES+" TEXT, "
                +CAMPO_PATERNO+" TEXT, "+CAMPO_MATERNO+" TEXT, "+CAMPO_CORREO+" TEXT, "+CAMPO_EMPRESA+" TEXT, "+CAMPO_RUC+" INTEGER," +
                " "+CAMPO_ENTRADAFECHAHORA+" NUMERIC, "+CAMPO_SALIDAFECHAHORA+" NUMERIC, "+CAMPO_CONTACTO+" TEXT, " +
                ""+CAMPO_MOTIVO+" TEXT, "+CAMPO_VISITA_TECNICA+" TEXT, "+CAMPO_SEGURO+" TEXT, "+CAMPO_SOAT+" TEXT, "+CAMPO_POLIZA+" TEXT, " +
                ""+CAMPO_SCTR+" TEXT, "+CAMPO_INDUC_SEG+" TEXT)";
        
    }

    public static final class TB_Acompañantes implements BaseColumns {

        //Constantes campos tabla acompañantes------------------------------------------
        public static final String TABLA_ACOMPAÑANTES="acompañantes";
        public static final String CAMPO_TIPDOC="TipoDocumentoAcompañantes";
        public static final String CAMPO_NUMDOC="NumeroDocumento";
        public static final String CAMPO_NUMDOCVISITA="NumeroDocumentoVisita";
        public static final String CAMPO_NOMBRES="Nombres";
        public static final String CAMPO_PATERNO="ApellidoPaterno";
        public static final String CAMPO_MATERNO="ApellidoMaterno";
        public static final String CAMPO_CORREO="CorreoElectronico";
        public static final String CAMPO_EMPRESA="Empresa";
        public static final String CAMPO_RUC="RUC";
        public static final String CAMPO_VISITA_TECNICA="Visita_Tecnica";
        public static final String CAMPO_SEGURO="Seguro";
        public static final String CAMPO_SOAT="SOAT";
        public static final String CAMPO_POLIZA="Poliza";
        public static final String CAMPO_SCTR="SCTR";
        public static final String CAMPO_INDUC_SEG="Induc_Seg";

        public static final String CREAR_TABLA_ACOMPAÑANTES="CREATE TABLE " +
                ""+TABLA_ACOMPAÑANTES+" ("+CAMPO_TIPDOC+" TEXT, "+CAMPO_NUMDOC+" INTEGER PRIMARY KEY, " +CAMPO_NUMDOCVISITA+" INTEGER, " +CAMPO_NOMBRES+" TEXT, " +
                "" +CAMPO_PATERNO+" TEXT, "+CAMPO_MATERNO+" TEXT, "+CAMPO_CORREO+" TEXT, "+CAMPO_EMPRESA+" TEXT, "+CAMPO_RUC+" INTEGER ," +
                ""+CAMPO_VISITA_TECNICA+" TEXT, "+CAMPO_SEGURO+" TEXT, "+CAMPO_SOAT+" TEXT, "+CAMPO_POLIZA+" TEXT, " +
                ""+CAMPO_SCTR+" TEXT, "+CAMPO_INDUC_SEG+" TEXT)";
    }
}