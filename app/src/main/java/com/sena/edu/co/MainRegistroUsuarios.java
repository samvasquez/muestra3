package com.sena.edu.co;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sena.edu.co.controladores.CtrlUsuarios;
import com.sena.edu.co.utilidades.DBSQLite;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainRegistroUsuarios extends AppCompatActivity {
    private EditText editUsuarios, editContrasena, editContrasenaVer,editFecha;

    private Spinner sRol,sEstado;
    private DatePickerDialog mFecha;
    private SimpleDateFormat dateFormat;

    @Override
     protected  void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro_usuarios);

        initVariables();
    }

    private void initVariables() {
        editUsuarios      =(EditText) findViewById(R.id.regUsuario);
        editContrasena    =(EditText) findViewById(R.id.regContrasena);
        editContrasenaVer =(EditText) findViewById(R.id.regContrasenaVer);

        editFecha=(EditText) findViewById(R.id.regFecha);
        editFecha.setInputType(InputType.TYPE_NULL);

        dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        editFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar miCalendario=Calendar.getInstance();
                mFecha=new DatePickerDialog(MainRegistroUsuarios.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int año, int mes, int dia) {
                        Calendar mFecha=Calendar.getInstance();
                        mFecha.set(año,mes,dia);
                        editFecha.setText(dateFormat.format(mFecha.getTime()));



                    }
                }, miCalendario.get(Calendar.YEAR),
                        miCalendario.get(Calendar.MONTH),
                        miCalendario.get(Calendar.DAY_OF_MONTH));
                mFecha.show();

















            }
        });
                sRol=(Spinner) findViewById(R.id.rsRol);
        sEstado=(Spinner)  findViewById(R.id.rsEstado);


    }

    public void validarUsuario(){
        editUsuarios.setError(null);
        editContrasena.setError(null);
        editContrasenaVer.setError(null);

        String valUsuario=editUsuarios.getText().toString();
        String valContrasena=editContrasena.getText().toString();
        String valContrasenaVer=editContrasenaVer.getText().toString();

        boolean cancelar= true;

        View focusView=null;

        if (TextUtils.isEmpty(valUsuario)){
            editUsuarios.setError("usuario no valido");
            focusView=editUsuarios;
            cancelar=true;

        }
        if (TextUtils.isEmpty(valContrasena)){
            editContrasena.setError("contraseña no valida");
            focusView=editContrasena;
            cancelar=true;
        }
        if(TextUtils.isEmpty(valContrasenaVer)){
            editContrasenaVer.setError("las contraseñas deben ser iguales");
            focusView=editContrasenaVer;
            cancelar=true;

        }
        if(cancelar){
            focusView.requestFocus();

        }else {
            DBSQLite db = new DBSQLite(this,null,null,1);
            CtrlUsuarios ctrlusu = new CtrlUsuarios(
                    valUsuario,
                    valContrasena,
                    editFecha.getText().toString(),
                    sEstado.getSelectedItemPosition(),
                    sRol.getSelectedItemPosition()
            );
            db.insertUsuario(ctrlusu);
            Toast.makeText(this,"usuario registrado correctamente",Toast.LENGTH_SHORT).show();

        }

    }
    public void btnConfirmar(View view){
        validarUsuario();
    }

}