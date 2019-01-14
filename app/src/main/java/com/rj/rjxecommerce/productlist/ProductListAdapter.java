package com.rj.rjxecommerce.productlist;

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

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    Context context;
    List<GetProductListResponse.Datum> datumList;
    RequestOptions requestOptions = new RequestOptions();


    public ProductListAdapter(Context context, GetProductListResponse getProductListResponse) {
        this.context = context;
        this.datumList = getProductListResponse.getData();
    }


    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.inflate_product_list, viewGroup, false);
        requestOptions.placeholder(R.drawable.placeholder);

        return new ProductListViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder viewHolder, int pos) {

        viewHolder.tvProductName.setText(datumList.get(pos).getName());
        viewHolder.tvCategoryName.setText(datumList.get(pos).getCategory_name());
        viewHolder.tvPrice.setText(context.getString(R.string.rs )+" "+datumList.get(pos).getPrice());
        viewHolder.tvQty.setText(context.getString(R.string.qty) +" "+datumList.get(pos).getQty());

        GlideApp.with(context).applyDefaultRequestOptions(requestOptions)
                .load(datumList.get(pos).getImage()).into(viewHolder.ivProductAvatar);

    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    public class ProductListViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tvProductName)
        TextView tvProductName;

        @BindView(R.id.tvCategoryName)
        TextView tvCategoryName;

        @BindView(R.id.tvPrice)
        TextView tvPrice;

        @BindView(R.id.tvQty)
        TextView tvQty;

        @BindView(R.id.ivProductAvatar)
        AppCompatImageView ivProductAvatar;



        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

