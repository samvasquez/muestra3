package com.sena.edu.co;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sena.edu.co.controladores.CtrlUsuarios;
import com.sena.edu.co.utilidades.DBSQLite;
import com.sena.edu.co.utilidades.ListAdapter;

import java.util.ArrayList;

public class MainListaUsuarios extends AppCompatActivity {

    private ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lista_usuarios);

        cargarlista();
    }

    private void cargarlista() {
        DBSQLite db=new DBSQLite(this,null, null,1);
        ArrayList <CtrlUsuarios> datos=new ArrayList<CtrlUsuarios>();

        datos =db.listarDBUsuarios();

        if(!datos.isEmpty()){
            lista=(ListView) findViewById(R.id.lstUsuarios);
            lista.setAdapter(new ListAdapter(this,R.layout.lst_usuarios,datos){
                @Override
                public void onEntrada(Object entradas, View view){
                    if (entradas!=null){
                        TextView login=(TextView)
                                view.findViewById(R.id.lstUlogin);
                        if(login != null){
                            login.setText(((CtrlUsuarios)entradas).getLogin());

                        }
                        TextView rol=(TextView)  view.findViewById(R.id.lstURol);
                        if (rol!=null){
                            if(((CtrlUsuarios)entradas).getRol()==1){
                                rol.setText("Administrador");

                            }else {
                                rol.setText("usuarios");
                            }
                        }
                        TextView estado=(TextView)  view.findViewById(R.id.lstUEstado);
                        if(estado!=null){
                            if(((CtrlUsuarios)entradas).getEstado()==1){
                                rol.setText("Activo");

                            }else{
                                rol.setText("Inactivo");

                            }
                        }
                        ImageView ima=(ImageView) view.findViewById(R.id.lstUImagen);
                        if (ima != null) {

                            if(((CtrlUsuarios)entradas).getRol()==1){
                                int idm= getResources().getIdentifier("icoadmin","drawable",getPackageName());
                                ima.setBackgroundResource(idm);
                            }else {
                                int idm=getResources().getIdentifier("icouser","drawable", getPackageName());
                                ima.setBackgroundResource(idm);

                            }
                        }

                    }
                }
            });
        }
    }

    public void btnCrearUsuario(View view) {

    }
}
