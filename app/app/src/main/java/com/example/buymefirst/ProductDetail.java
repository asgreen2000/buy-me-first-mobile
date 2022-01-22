package com.example.buymefirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.buymefirst.Modal.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;


public class ProductDetail extends AppCompatActivity {

    Product product;
    FirebaseFirestore db;
    String collectionName = "products";
    ImageView productImg;
    TextView productName, productPrice, productInStock, backBtn;
    LinearLayout llProductDetail;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productImg = findViewById(R.id.idIVProductImg);
        productName = findViewById(R.id.idTVProductName);
        productPrice = findViewById(R.id.idTVProductPrice);
        productInStock = findViewById(R.id.idTVInStock);
        llProductDetail = findViewById(R.id.idLLProductDetail);
        progressBar = findViewById(R.id.idPBLoading);
        backBtn = findViewById(R.id.idTVBack);

        db = FirebaseFirestore.getInstance();

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            getProductDetail(extra.getString("id"));
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDetail.this.finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    void getProductDetail(String id) {
        progressBar.setVisibility(View.VISIBLE);
        llProductDetail.setVisibility(View.GONE);
        db.collection(collectionName).document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot data = task.getResult();

                    ProductDetail.this.product = new Product(data.getId(),
                            data.get("name").toString(),
                            data.get("imageURL").toString(),
                            data.get("type").toString(),
                            data.get("description").toString(),
                            Integer.parseInt(data.get("price").toString()),
                            Integer.parseInt(data.get("inStock").toString())
                    );
                    updateViewData();
                    llProductDetail.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    void updateViewData() {

        if (product != null) {
            Picasso.get().load(product.getImageURL()).into(productImg);

        }
    }
}