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

import com.example.qallariy.IAxiliarProducto;
import com.example.qallariy.R;
import com.example.qallariy.models.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapterRecyclerView extends RecyclerView.Adapter<ProductoAdapterRecyclerView.ProductoViewHolder>{
    private List<Producto> business=new ArrayList<>();
    private Context context;
    private ArrayList<Producto> productosArrayList;
    private IAxiliarProducto iAxiliarProducto;

    public ProductoAdapterRecyclerView(IAxiliarProducto iAxiliarProducto,ArrayList<Producto> business) {
        this.iAxiliarProducto=iAxiliarProducto;
        this.business = business;
        this.productosArrayList=business;
    }


    @NonNull
    @Override
    public ProductoAdapterRecyclerView.ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_productos,parent,false);

        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductoAdapterRecyclerView.ProductoViewHolder holder, int position) {
        Producto producto = business.get(position);
        holder.txtCodigoMostrarP.setText(String.valueOf(producto.getCodigo()));
        Picasso.get().load(producto.getImage()).error(R.drawable.notfound).into(holder.pictureCardP);
        holder.userNameCardP.setText(producto.getNombreP());
        holder.descriptionP.setText(producto.getDescripcion());
        holder.desPrecioP.setText(String.valueOf(producto.getPrecio()));
        holder.desCantidadP.setText(String.valueOf(producto.getCantidad()));




        holder.btnEditProduct.setOnClickListener(new ProductoAdapterRecyclerView.eventoEditar(producto));
        holder.btnDeleteProduct.setOnClickListener(new ProductoAdapterRecyclerView.eventoEliminar(producto));



    }

    @Override
    public int getItemCount() {
        return business.size();
    }

    public void agregarProducto(Producto producto) {
        business.add(producto);
        this.notifyDataSetChanged();
    }

    public void eliminarProducto(Producto producto) {
        business.remove(producto);
        this.notifyDataSetChanged();
    }

    class eventoEditar implements View.OnClickListener {

        private Producto producto;
        public eventoEditar(Producto producto){
            this.producto=producto;
        }

        @Override
        public void onClick(View v) {
            iAxiliarProducto.OpcionEditarProducto(producto);

        }
    }

    class eventoEliminar implements View.OnClickListener {
        private Producto producto;

        public eventoEliminar(Producto producto){
            this.producto=producto;
        }

        @Override
        public void onClick(View view) {
            iAxiliarProducto.OpcionEliminarProducto(producto);

        }
    }


    public class ProductoViewHolder extends RecyclerView.ViewHolder{
        private TextView txtCodigoMostrarP;
        private ImageView pictureCardP;
        private TextView userNameCardP;
        private TextView descriptionP;
        private TextView desPrecioP;
        private TextView desCantidadP;



        private Button btnEditProduct, btnDeleteProduct;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCodigoMostrarP=(TextView)itemView.findViewById(R.id.txtCodigoMostrarP);
            pictureCardP=(ImageView)itemView.findViewById(R.id.pictureCardProduct);
            userNameCardP=(TextView)itemView.findViewById(R.id.userNamecardP);
            descriptionP=(TextView)itemView.findViewById(R.id.descriptionProdct);
            desPrecioP=(TextView)itemView.findViewById(R.id.precioDesc);
            desCantidadP=(TextView)itemView.findViewById(R.id.cantidadDesc);



            btnEditProduct=itemView.findViewById(R.id.btnEditProduct);
            btnDeleteProduct=itemView.findViewById(R.id.btnDeleteProduct);
        }
    }
}
