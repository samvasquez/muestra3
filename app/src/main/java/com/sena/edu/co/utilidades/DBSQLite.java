package com.sena.edu.co.utilidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.sena.edu.co.controladores.CtrlUsuarios;

import java.util.ArrayList;
import java.util.List;

public class DBSQLite extends SQLiteOpenHelper{
private static final String dbName ="user.db";
private static  final int dbVersion= 4;


    public DBSQLite( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dllUsuario ="CREATE TABLE usuarios(";
        dllUsuario       +="id INTEGER PRIMARY KEY AUTOINCREMENT,";
        dllUsuario       +="login TEXT,";
        dllUsuario       +="contrasena TEXT,";
        dllUsuario       +="fecha TEXT,";
        dllUsuario       +="estado INTEGER,";
        dllUsuario       +="rol INTEGER)";

        String insertUsuarios="INSERT INTO usuarios(login, contrasena, fecha, estado, rol)" ;

        insertUsuarios       += "SELECT 'admin', 'admin', '2023-06-08',1,1 UNION ALL ";
        insertUsuarios       += "SELECT 'usuario', 'usuario', '2023-06-08',1,2; ";

        db.execSQL(dllUsuario);
        db.execSQL(insertUsuarios);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion, int newVersion) {
        String dllUsuario = "DROP TABLE IF EXISTS usuarios";
        db.execSQL(dllUsuario);
        onCreate(db);

    }
    public List<CtrlUsuarios> loginUsuario(String usu, String con) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, login, contrasena, fecha, estado, rol FROM usuarios WHERE login = '" + usu + "' AND contrasena = '" + con + "'", null);
        List<CtrlUsuarios> listaUsuario = new ArrayList<>();
        while (cursor.moveToNext()) {
            CtrlUsuarios ctrlUsuarios = new CtrlUsuarios();
            ctrlUsuarios.setId(cursor.getInt(0));
            ctrlUsuarios.setLogin(cursor.getString(1));
            ctrlUsuarios.setContrasena(cursor.getString(2));
            ctrlUsuarios.setFecha(cursor.getString(3));
            ctrlUsuarios.setEstado(cursor.getInt(4));
            ctrlUsuarios.setRol(cursor.getInt(5));
            listaUsuario.add(ctrlUsuarios);
        }
        cursor.close();
        db.close();
        return listaUsuario;
    }

    public void insertUsuario(CtrlUsuarios ctrlusuarios) {
        ContentValues values = new ContentValues();

        // values.put("id", ctrlUsuarios.getId());

        values.put("login", ctrlusuarios.getLogin());
        values.put("contrasena", ctrlusuarios.getContrasena());
        values.put("fecha", ctrlusuarios.getFecha());
        values.put("estado", ctrlusuarios.getEstado());
        values.put("rol", ctrlusuarios.getRol());

        SQLiteDatabase base = this.getWritableDatabase();
        base.insert("usuarios", null, values);
        base.close();
    }

    public ArrayList<CtrlUsuarios> listarDBUsuarios() {
        ArrayList<CtrlUsuarios> usuarios =new ArrayList<CtrlUsuarios>();
        SQLiteDatabase base = this. getWritableDatabase();
        String consulta ="SELECT id, login, contrasena, fecha, estado, rol FROM usuarios order by id";
        Cursor cursor= base.rawQuery(consulta,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                CtrlUsuarios ctrl =new CtrlUsuarios();
                ctrl.setLogin(cursor.getString(1));
                ctrl.setContrasena(cursor.getString(2));
                ctrl.setEstado(cursor.getInt(4));
                ctrl.setRol(cursor.getInt(5));

                usuarios.add(ctrl);
                cursor.moveToNext();
            }
        }
        cursor.close();
        base.close();

        return usuarios;
    }
}