package com.example.qallariy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qallariy.IAxiliarLista;
import com.example.qallariy.R;
import com.example.qallariy.models.Negocio;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListaAdapterRecyclerView extends RecyclerView.Adapter<ListaAdapterRecyclerView.ListaViewHolder>{

    private List<Negocio> business=new ArrayList<>();
    private Context context;
    private ArrayList<Negocio> negociosArrayList;
    private IAxiliarLista iAxiliarLista;

    public ListaAdapterRecyclerView(IAxiliarLista iAxiliarLista,ArrayList<Negocio> business) {
        this.iAxiliarLista=iAxiliarLista;
        this.business = business;
        this.negociosArrayList=business;
    }


    @NonNull
    @Override
    public ListaAdapterRecyclerView.ListaViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ver_negocios,parent,false);

        return new ListaAdapterRecyclerView.ListaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListaViewHolder holder, int position) {
        Negocio negocio = business.get(position);
        holder.txtCodigoMostrarLista.setText(String.valueOf(negocio.getCodigo()));
        Picasso.get().load(negocio.getPicture()).error(R.drawable.notfound).into(holder.pictureCardLista);
        holder.userNameCardLista.setText(negocio.getName());
        holder.descriptionNegocioLista.setText(negocio.getDescription());
        holder.desCategoriaLista.setText(negocio.getCategoria());

        holder.btnDetalle.setOnClickListener(new eventoListarProducto(negocio));
    }

    class eventoListarProducto implements View.OnClickListener {
        private Negocio negocio;

        public eventoListarProducto(Negocio negocio){
            this.negocio=negocio;
        }

        @Override
        public void onClick(View view) {
            iAxiliarLista.OpcionDetalle(negocio);

        }
    }

    @Override
    public int getItemCount() {
        return business.size();
    }

    public class ListaViewHolder extends RecyclerView.ViewHolder{
        private TextView txtCodigoMostrarLista;
        private ImageView pictureCardLista;
        private TextView userNameCardLista;
        private TextView descriptionNegocioLista;
        private TextView desCategoriaLista;

        private Button btnDetalle;

        public ListaViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCodigoMostrarLista=(TextView)itemView.findViewById(R.id.txtCodigoMostrarLista);
            pictureCardLista=(ImageView)itemView.findViewById(R.id.pictureCardLista);
            userNameCardLista=(TextView)itemView.findViewById(R.id.userNamecardLista);
            descriptionNegocioLista=(TextView)itemView.findViewById(R.id.descriptionNegocioLista);
            desCategoriaLista=(TextView)itemView.findViewById(R.id.desCategoriaLista);



            btnDetalle=itemView.findViewById(R.id.btnDetalle);
        }
    }
}
