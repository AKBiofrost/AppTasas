package com.portafolio.appdivisas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.portafolio.appdivisas.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter< ListAdapter.ViewHolder> {
    /*------------------------------------------------------------------*/
    private List<ListElement> Mdata;
    private LayoutInflater mLayoutInflater;
    private Context mContext;



    public ListAdapter(List<ListElement> itemList,Context context){
        this.mLayoutInflater=LayoutInflater.from(context);
        this.mContext=context;
        this.Mdata=itemList;

    }

    @Override
    public int getItemCount() {return Mdata.size();}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mLayoutInflater.inflate(R.layout.cardview, null);
        return new ViewHolder(view);


    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.binData(Mdata.get(position));
    }

public void setItems(List<ListElement> items){Mdata= items; }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView Title;
        TextView Description;

        ViewHolder(View itemView){
            super(itemView);
            //Title=(TextView)itemView.findViewById(R.id.titulo);
           // Description=(TextView)itemView.findViewById(R.id.moneda);

        }

        void binData(final ListElement item){

            Title.setText(item.getDinero());
            Description.setText(item.getValue());

        }

    }


}
