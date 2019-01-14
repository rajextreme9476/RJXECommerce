package com.rj.rjxecommerce.productlist;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rj.rjxecommerce.R;
import com.rj.rjxecommerce.model.GetProductListResponse;
import com.rj.rjxecommerce.network.AppConstants;
import com.rj.rjxecommerce.productdetails.ProductDetailsActivity;
import com.rj.rjxecommerce.utils.RecyclerItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductListFragment extends Fragment {


    @BindView(R.id.rvProductList)
    RecyclerView recyclerView;




    ProductListAdapter productListAdapter;;

    public ProductListFragment() {
        // Required empty public constructor
    }

    GetProductListResponse getProductListResponse = new GetProductListResponse();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,rootView);
        initRecyclerView();
        getProductList();
        return rootView;
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void initRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) mLayoutManager).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),ProductDetailsActivity.class);
                intent.putExtra(AppConstants.CATEGORY_ID,getProductListResponse.getData().get(position).getCategory_id());
                startActivity(intent);

            }
        }));
    }

    private void getProductList() {
        ProductListViewModel productListViewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);
        productListViewModel.loadProductList(getActivity()).observe(this, new Observer<GetProductListResponse>() {
            @Override
            public void onChanged(@Nullable GetProductListResponse getProductListResponseList) {

                getProductListResponse = getProductListResponseList;
                productListAdapter = new ProductListAdapter(getContext(), getProductListResponse);
                recyclerView.setAdapter(productListAdapter);
            }
        });
    }
}
