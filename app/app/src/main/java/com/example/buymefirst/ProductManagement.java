package com.example.buymefirst;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.buymefirst.GridViewAdapter.ProductAdapter;
import com.example.buymefirst.Modal.Product;
import com.example.buymefirst.Modal.ProductType;
import com.example.buymefirst.RecycleViewAdapter.ProductTypeAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Map;

public class ProductManagement {
    private Context context;
    private FirebaseFirestore db;
    private ArrayList<ProductType> productTypeArrayList;
    private ArrayList<Product> productArrayList;
    private ProductTypeAdapter productTypeAdapter;
    private ProductAdapter productAdapter;
    private String typeFilterID = "";
    private String typeFilterName = "";
    private TextView typeFilterTV;
    private View view;

    public ProductManagement(Context context, View view) {
        this.context = context;
        this.view = view;
        db = FirebaseFirestore.getInstance();
        productTypeArrayList = new ArrayList<>();
        productTypeAdapter = new ProductTypeAdapter(context, productTypeArrayList, this);
        productArrayList = new ArrayList<>();
        productAdapter = new ProductAdapter(context, productArrayList);
        typeFilterTV = view.findViewById(R.id.idTVFilterName);
    }


    public ArrayList<ProductType> getProductTypeArrayList() {
        return productTypeArrayList;
    }

    public void setProductTypeArrayList(ArrayList<ProductType> productTypeArrayList) {
        this.productTypeArrayList = productTypeArrayList;
    }

    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }

    public void setProductArrayList(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    public ProductTypeAdapter getProductTypeAdapter() {
        return productTypeAdapter;
    }

    public void setProductTypeAdapter(ProductTypeAdapter productTypeAdapter) {
        this.productTypeAdapter = productTypeAdapter;
    }

    public ProductAdapter getProductAdapter() {
        return productAdapter;
    }

    public void setProductAdapter(ProductAdapter productAdapter) {
        this.productAdapter = productAdapter;
    }

    public String getTypeFilterID() {
        return typeFilterID;
    }

    public void setTypeFilter(String typeFilterID, String typeFilterName) {
        this.typeFilterID = typeFilterID;
        this.typeFilterName = typeFilterName;
        typeFilterTV.setText(this.typeFilterName.toString());
        updateProductData();
    }

    void updateProductTypeData() {

        db.collection("types").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                productTypeArrayList.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {

                    Map<String, Object> data = document.getData();
                    ProductType productType = new ProductType(document.getId(),
                            data.get("typeName").toString(),
                            data.get("typeImage").toString()
                    );

                    productTypeArrayList.add(productType);
                }

                if (productTypeArrayList.size() > 0) {
                    ProductType productType = productTypeArrayList.get(0);
                    this.setTypeFilter(productType.getId(), productType.getTypeName());
                }

                updateProductData();

                productTypeAdapter.notifyDataSetChanged();
            }
            else {

            }
        });
    }
    void updateProductData() {


        db.collection("products").whereEqualTo("type", typeFilterID).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                productArrayList.clear();
                int i = 0;
                for (QueryDocumentSnapshot document : task.getResult()) {

                    Map<String, Object> data = document.getData();

                    Product product = new Product(document.getId(),
                            data.get("name").toString(),
                            data.get("imageURL").toString(),
                            data.get("type").toString(),
                            data.get("description").toString(),
                            Integer.parseInt(data.get("price").toString()),
                            Integer.parseInt(data.get("inStock").toString())
                    );

                    product.setIdInList(i++);
                    productArrayList.add(product);
                }

                productAdapter.notifyDataSetChanged();
            }
            else {

            }
        });
    }
}
