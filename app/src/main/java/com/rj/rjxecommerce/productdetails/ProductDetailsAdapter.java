package com.rj.rjxecommerce.productdetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.rj.rjxecommerce.R;
import com.rj.rjxecommerce.model.GetProductListResponse;
import com.rj.rjxecommerce.utils.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsAdapter extends RecyclerView.Adapter<ProductDetailsAdapter.ProductDetailsViewHolder> {

    Context context;
    List<GetProductDetailsResponse.Datum> datumList;
    RequestOptions requestOptions = new RequestOptions();

    public ProductDetailsAdapter(Context context,  List<GetProductDetailsResponse.Datum> datumList ) {
        this.context = context;
        this.datumList = datumList;
    }


    @NonNull
    @Override
    public ProductDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.inflate_product_details, viewGroup, false);
        requestOptions.placeholder(R.drawable.placeholder);

        return new ProductDetailsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailsViewHolder viewHolder, int pos) {
        viewHolder.tvProductName.setText(datumList.get(pos).getName());
        viewHolder.tvItemName.setText(datumList.get(pos).getCategory_name());
        viewHolder.tvPrice.setText(context.getString(R.string.rs )+" "+datumList.get(pos).getPrice());
        viewHolder.tvSize.setText(context.getString(R.string.qty) +" "+datumList.get(pos).getQty());

        GlideApp.with(context).applyDefaultRequestOptions(requestOptions)
                .load(datumList.get(pos).getImage()).into(viewHolder.ivProduct);
    }



    @Override
    public int getItemCount() {
        return datumList.size();
    }

    public class ProductDetailsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvProductName)
        TextView tvProductName;

        @BindView(R.id.tvItemName)
        TextView tvItemName;

        @BindView(R.id.tvPrice)
        TextView tvPrice;

        @BindView(R.id.tvSize)
        TextView tvSize;

        @BindView(R.id.ivProduct)
        AppCompatImageView ivProduct;



        public ProductDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

