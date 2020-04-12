package com.example.intellisoftgloriaaccesomovil.BD;

import java.io.Serializable;
import java.util.Arrays;

public class EmpleadoBio implements Serializable {

    public static Integer IdHuella;
    public static Integer IdEmpleado;
    public static String ImagenHuella;
    public static byte[] ImagenHuellaTemp;

    public EmpleadoBio(Integer idHuella, Integer idEmpleado, String imagenHuella, byte[] imagenHuellaTemp){
        IdHuella = idHuella;
        IdEmpleado = idEmpleado;
        ImagenHuella = imagenHuella;
        ImagenHuellaTemp = imagenHuellaTemp;
    }

    public EmpleadoBio(String name, byte[] data, int len) {
        this.ImagenHuella = name;
        this.ImagenHuellaTemp = Arrays.copyOf(data, len);
    }

    public static Integer getIdHuella() {
        return IdHuella;
    }

    public static void setIdHuella(Integer idHuella) {
        IdHuella = idHuella;
    }

    public static Integer getIdEmpleado() {
        return IdEmpleado;
    }

    public static void setIdEmpleado(Integer idEmpleado) {
        IdEmpleado = idEmpleado;
    }

    public static String getImagenHuella() {
        return ImagenHuella;
    }

    public static void setImagenHuella(String imagenHuella) {
        ImagenHuella = imagenHuella;
    }

    public static byte[] getImagenHuellaTemp() {
        return ImagenHuellaTemp;
    }

    public static void setImagenHuellaTemp(byte[] imagenHuellaTemp) {
        ImagenHuellaTemp = imagenHuellaTemp;
    }
}
