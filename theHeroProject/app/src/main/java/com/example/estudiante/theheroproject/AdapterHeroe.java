package com.example.estudiante.theheroproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

class AdapterHeroe extends BaseAdapter {
    private final ArrayList<hero> listaNueva;
    private final LayoutInflater layoutInflater;

    public AdapterHeroe(Context aContext, ArrayList<hero> listData) {
        this.listaNueva = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listaNueva.size();
    }

    @Override
    public Object getItem(int position) {
        return listaNueva.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View v, ViewGroup vg) {
        ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.lista, null);
            holder = new ViewHolder();
            holder.uNombre = v.findViewById(R.id.txtNameHeroe);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.uNombre.setText(listaNueva.get(position).getNombre());
        return v;
    }

    static class ViewHolder {
        TextView uNombre;
    }
}
