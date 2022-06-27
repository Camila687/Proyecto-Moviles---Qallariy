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

import com.example.qallariy.IAxiliarProductList;
import com.example.qallariy.R;
import com.example.qallariy.models.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapterRecyclerView extends RecyclerView.Adapter<ProductListAdapterRecyclerView.ProductListViewHolder>{
    private List<Producto> business=new ArrayList<>();
    private Context context;
    private ArrayList<Producto> productosArrayList;
    private IAxiliarProductList iAxiliarProductList;

    public ProductListAdapterRecyclerView(IAxiliarProductList iAxiliarProductList,ArrayList<Producto> business) {
        this.iAxiliarProductList=iAxiliarProductList;
        this.business = business;
        this.productosArrayList=business;
    }


    @NonNull
    @Override
    public ProductListAdapterRecyclerView.ProductListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_product_list,parent,false);

        return new ProductListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductListAdapterRecyclerView.ProductListViewHolder holder, int position) {
        Producto producto = business.get(position);
        holder.txtCodigoMostrarPLista.setText(String.valueOf(producto.getCodigo()));
        Picasso.get().load(producto.getImage()).error(R.drawable.notfound).into(holder.pictureCardPLista);
        holder.userNameCardPLista.setText(producto.getNombreP());
        holder.descriptionPLista.setText(producto.getDescripcion());
        holder.desPrecioPLista.setText(String.valueOf(producto.getPrecio()));
        holder.desCantidadPLista.setText(String.valueOf(producto.getCantidad()));

        //holder.btnDetalle.setOnClickListener(new ListaAdapterRecyclerView.eventoDetalle(negocio));
    }

    @Override
    public int getItemCount() {
        return business.size();
    }

    public class ProductListViewHolder extends RecyclerView.ViewHolder{
        private TextView txtCodigoMostrarPLista;
        private ImageView pictureCardPLista;
        private TextView userNameCardPLista;
        private TextView descriptionPLista;
        private TextView desPrecioPLista;
        private TextView desCantidadPLista;

        //private Button btnDetalle;


        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCodigoMostrarPLista=(TextView)itemView.findViewById(R.id.txtCodigoMostrarPD);
            pictureCardPLista=(ImageView)itemView.findViewById(R.id.pictureCardProductD);
            userNameCardPLista=(TextView)itemView.findViewById(R.id.userNamecardPD);
            descriptionPLista=(TextView)itemView.findViewById(R.id.descriptionProdctD);
            desPrecioPLista=(TextView)itemView.findViewById(R.id.precioDescD);
            desCantidadPLista=(TextView)itemView.findViewById(R.id.cantidadDescD);

            //btnDetalle=(Button) itemView.findViewById(R.id.btnDetalle);


        }
    }
}
