package com.example.buymefirst.RecycleViewAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buymefirst.Modal.ProductType;
import com.example.buymefirst.ProductManagement;
import com.example.buymefirst.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProductType> productTypeArrayList;
    private ProductManagement productManagement;
    private int maxTextSize = 0;


    public ProductTypeAdapter(Context context, ArrayList<ProductType> productTypeArrayList, ProductManagement productManagement) {
        this.context = context;
        this.productTypeArrayList = productTypeArrayList;
        this.productManagement = productManagement;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.type_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductType productType = productTypeArrayList.get(position);
        Picasso.get().load(productType.getTypeImage()).into(holder.typeImage);
        holder.typeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productManagement.setTypeFilter(productType.getId(), productType.getTypeName());
            }
        });
        holder.typeName.setText(productType.getTypeName());
    }

    @Override
    public int getItemCount() {
        return productTypeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView typeName;
        private ImageView typeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            typeImage = itemView.findViewById(R.id.idIVTypeImage);
            typeName = itemView.findViewById(R.id.idTVTypeName);
        }
    }
}
