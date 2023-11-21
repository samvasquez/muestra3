package com.sena.edu.co.controladores;

public class CtrlUsuarios {
    private String login;
    private String contrasena;
    private String fecha;
    private int id;
    private int estado;
    private int rol;
    public CtrlUsuarios(){}
    public CtrlUsuarios(String login, String contrasena,String fecha, int estado, int rol ){
        this.login=login;
        this.contrasena=contrasena;
        this.fecha=fecha;
        this.estado=estado;
        this.rol=rol;

    }
    public String getLogin(){
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public String getFecha(){
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public  int getEstado(){
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }
}
