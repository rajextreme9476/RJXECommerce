package com.rj.rjxecommerce.productdetails;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.rj.rjxecommerce.R;
import com.rj.rjxecommerce.network.AppConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;

public class ProductDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rvProductDetails)
    RecyclerView recyclerView;

    String categoryID;

    ProductDetailsAdapter productDetailsAdapter;

    @BindView(R.id.tvProductEmpty)
    TextView tvProductEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initRecyclerView();
        getCategoryID();
        getProductDetails();
    }

    private void getCategoryID() {
        Bundle extras = getIntent().getExtras();
        if(extras != null)
            categoryID = extras.getString(AppConstants.CATEGORY_ID);

        Log.e("TAG", "getCategoryID: "+categoryID );
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) mLayoutManager).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void getProductDetails() {
        ProductDetailsViewModel productListViewModel = ViewModelProviders.of(this).get(ProductDetailsViewModel.class);
        productListViewModel.loadProductList(this).observe(this, new Observer<GetProductDetailsResponse>() {
            @Override
            public void onChanged(@Nullable GetProductDetailsResponse getProductDetailsResponse) {

                List<GetProductDetailsResponse.Datum> datumList= new ArrayList<>();

                for(int i = 0 ; i < getProductDetailsResponse.getData().size();i++){

                    if(getProductDetailsResponse.getData().get(i).getCategory_id().equals(categoryID))
                    {
                        Log.e("**"+getProductDetailsResponse.getData().get(i).getCategory_id(), "onChanged: "+i );
                        datumList.add(getProductDetailsResponse.getData().get(i));
                   //     getProductDetailsResponse.getData().remove(i);
                    }else {
                        getProductDetailsResponse.getData().remove(i);

                    }

                }

                productDetailsAdapter = new ProductDetailsAdapter(ProductDetailsActivity.this, datumList);
                recyclerView.setAdapter(productDetailsAdapter);

                if(datumList.isEmpty()){
                    tvProductEmpty.setVisibility(View.VISIBLE);
                }else {tvProductEmpty.setVisibility(View.GONE);}
            }
        });
    }

}
