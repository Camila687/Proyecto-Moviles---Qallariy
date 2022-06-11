package com.example.qallariy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qallariy.IAxiliarNegocio;
import com.example.qallariy.R;
import com.example.qallariy.models.Negocio;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NegocioAdapterRecyclerView extends RecyclerView.Adapter<NegocioAdapterRecyclerView.NeogocioViewHolder> {

    private List<Negocio>negocios=new ArrayList<>();
    private Context context;
    private ArrayList<Negocio> negociosArrayList;
    private IAxiliarNegocio iAxiliarNegocio;

    public NegocioAdapterRecyclerView(IAxiliarNegocio iAxiliarNegocio,ArrayList<Negocio> negocios) {
        this.iAxiliarNegocio=iAxiliarNegocio;
        this.negocios = negocios;
        this.negociosArrayList=negocios;
    }


    @NonNull
    @Override
    public NegocioAdapterRecyclerView.NeogocioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_neogocio,parent,false);

        return new NeogocioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NeogocioViewHolder holder, int position) {
        Negocio negocio = negocios.get(position);
        holder.txtCodigoMostrar.setText(String.valueOf(negocio.getCodigo()));
        Picasso.get().load(negocio.getPicture()).into(holder.pictureCard);
        holder.userNameCard.setText(negocio.getName());
        holder.descriptionNegocio.setText(negocio.getDescription());
        holder.desCategoria.setText(negocio.getCategoria());
        holder.btnEditNegocio.setOnClickListener(new eventoEditar(negocio));
        holder.btnDeleteNegocio.setOnClickListener(new eventoEliminar(negocio));



    }

    @Override
    public int getItemCount() {
        return negocios.size();
    }

    public void agregarNegocio(Negocio negocio) {
        negocios.add(negocio);
        this.notifyDataSetChanged();
    }

    public void eliminarNegocio(Negocio negocio) {
        negocios.remove(negocio);
        this.notifyDataSetChanged();
    }

    class eventoEditar implements View.OnClickListener {

        private Negocio negocio;
        public eventoEditar(Negocio negocio){
            this.negocio=negocio;
        }

        @Override
        public void onClick(View v) {
            iAxiliarNegocio.OpcionEditar(negocio);

        }
    }

    class eventoEliminar implements View.OnClickListener {
        private Negocio negocio;

        public eventoEliminar(Negocio negocio){
            this.negocio=negocio;
        }

        @Override
        public void onClick(View view) {
            iAxiliarNegocio.OpcionEliminar(negocio);

        }
    }


    public class NeogocioViewHolder extends RecyclerView.ViewHolder{
        private TextView txtCodigoMostrar;
        private ImageView pictureCard;
        private TextView userNameCard;
        private TextView descriptionNegocio;
        private TextView desCategoria;

        private Button btnEditNegocio, btnDeleteNegocio;

        public NeogocioViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCodigoMostrar=(TextView)itemView.findViewById(R.id.txtCodigoMostrar);
            pictureCard=(ImageView)itemView.findViewById(R.id.pictureCard);
            userNameCard=(TextView)itemView.findViewById(R.id.userNamecard);
            descriptionNegocio=(TextView)itemView.findViewById(R.id.descriptionNegocio);
            desCategoria=(TextView)itemView.findViewById(R.id.desCategoria);



            btnEditNegocio=itemView.findViewById(R.id.btnEditNegocio);
            btnDeleteNegocio=itemView.findViewById(R.id.btnDeleteNegocio);
        }
    }
}
