package com.sena.edu.co;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sena.edu.co.controladores.CtrlUsuarios;
import com.sena.edu.co.utilidades.DBSQLite;

import java.util.List;

public class MainLogin extends AppCompatActivity {

    private EditText eUsuario , eContrasena ;
    private Intent ir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        eUsuario = (EditText) findViewById(R.id.ediLogin);
        eContrasena = (EditText) findViewById(R.id.ediContrasena);
    }
    public void validarUsuario(){
        eUsuario.setError(null);
        eContrasena.setError(null);
        String vusuario = eUsuario.getText().toString();
        String vcontrasena = eContrasena.getText().toString();

        boolean cancelar= false;
        View focusView=null;

        if(TextUtils.isEmpty(vusuario)){
            eUsuario.setError(" Usuario no valido");
            focusView=eUsuario;
            cancelar=true;
        }
        if(TextUtils.isEmpty(vcontrasena)){
            eContrasena.setError(" Contraseña no valida");
            focusView=eContrasena;
            cancelar=true;
        }
        if(cancelar){focusView.requestFocus();
        }else{
            List<CtrlUsuarios>listaUsuarioValidar;
            DBSQLite base = new DBSQLite( this, null, null, 1);
            listaUsuarioValidar = base.loginUsuario(vusuario,vcontrasena);
            if(!listaUsuarioValidar.isEmpty()){
                Toast.makeText(this,"iniciode sesion correcto",Toast.LENGTH_SHORT).show();
                ir =new Intent( this, MainMenu.class);
                startActivity(ir);
            }else{
                Toast.makeText(this,"inicio de sesion incorrecto",Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void BtnInicioSesion (View view){

        validarUsuario();
    }
    public void BtnRegistrarUsuario(View view){
        Intent ir =new Intent( this, MainRegistroUsuarios.class);
        startActivity(ir);
    }

}