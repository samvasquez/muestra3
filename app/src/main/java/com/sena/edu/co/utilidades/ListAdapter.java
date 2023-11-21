package com.sena.edu.co.utilidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public  abstract class ListAdapter extends BaseAdapter {
    private ArrayList<?>entradas;
    private int R_layout_IdView;
    private Context mContext;

    public ListAdapter(Context context, int R_layout_IdView, ArrayList<?> entradas){
        super();
        this.mContext=context;
        this.entradas=entradas;
        this.R_layout_IdView=R_layout_IdView;
    }
    @Override
    public int getCount(){
        return entradas.size();
    }
    @Override
    public Object getItem(int position){
        return entradas.get(position);
    }
    @Override
    public long getItemId(int position){
        return  position;
    }
    @Override
    public View getView(int position, View view, ViewGroup vGroup){
        if (view==null){
            LayoutInflater vi=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=vi.inflate(R_layout_IdView,null);

        }
        onEntrada(entradas.get(position),view);
        return view;
    }

    public abstract void onEntrada(Object entradas, View view); {
    }


}
