package com.example.buymefirst.GridViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buymefirst.Modal.Product;
import com.example.buymefirst.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Product> products;
    private LayoutInflater inflater;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        // get length of products
        return products == null ? 0 : products.size();
    }

    @Override
    public Object getItem(int i) {
        // get specified product
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return products.get(i).getIdInList();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view == null) {
            view = inflater.inflate(R.layout.product_clolumn_grid, null);
        }

        Product product = products.get(i);
        ImageView productImg = view.findViewById(R.id.idIVProductImg);
        TextView priceTV = view.findViewById(R.id.idTVProductPrice);
        TextView nameTV = view.findViewById(R.id.idTVProductName);

        Picasso.get().load(product.getImageURL()).into(productImg);
        priceTV.setText(String.format("$%s", product.getPrice().toString()));
        nameTV.setText(
                product.getName().length() > 12 ?
                        product.getName().substring(0, 12) + "...":
                        product.getName()
        );

        return view;
    }


}
