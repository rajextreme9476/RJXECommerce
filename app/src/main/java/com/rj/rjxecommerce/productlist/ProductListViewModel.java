package com.rj.rjxecommerce.productlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.rj.rjxecommerce.app.ApplicationEcom;
import com.rj.rjxecommerce.model.GetProductListResponse;
import com.rj.rjxecommerce.network.EComService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ProductListViewModel extends ViewModel {
    private Context context;
    private MutableLiveData<GetProductListResponse> getProductListResponseMutableLiveData;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LiveData<GetProductListResponse> loadProductList(Context context) {
           this.context=context;
        if (getProductListResponseMutableLiveData == null) {
            getProductListResponseMutableLiveData = new MutableLiveData<GetProductListResponse>();
            getProductListTask();

        }
        return getProductListResponseMutableLiveData;
    }

    private void getProductListTask() {

        ApplicationEcom appController = ApplicationEcom.create(context);
        EComService eComService = appController.getEcomService();
        Disposable disposable = eComService.getProductLists()
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetProductListResponse>() {
                    @Override
                    public void accept(GetProductListResponse getProductListResponse) throws Exception {
                        getProductListResponseMutableLiveData.setValue(getProductListResponse);
                       /* updateUserDataList(userResponse.getPeopleList());
                        progressBar.set(View.GONE);
                        userLabel.set(View.GONE);
                        userRecycler.set(View.VISIBLE);*/
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //Snackbar.make(, "Something went wrong!", Snackbar.LENGTH_SHORT).show();
                        Log.e("throwable", "" + throwable.getMessage());
                      /*  messageLabel.set(context.getString(R.string.error_message_loading_users));
                        progressBar.set(View.GONE);
                        userLabel.set(View.VISIBLE);
                        userRecycler.set(View.GONE);*/
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
}
