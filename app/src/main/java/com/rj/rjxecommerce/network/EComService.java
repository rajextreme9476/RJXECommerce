package com.rj.rjxecommerce.network;


import com.rj.rjxecommerce.model.GetProductListResponse;
import com.rj.rjxecommerce.productdetails.GetProductDetailsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface EComService {


    @Headers({"Accept: application/json"})
    @GET("productList")
    Observable<GetProductListResponse> getProductLists();

    @Headers({"Accept: application/json"})
    @GET("productDetail")
    Observable<GetProductDetailsResponse> getProductDetails();

}