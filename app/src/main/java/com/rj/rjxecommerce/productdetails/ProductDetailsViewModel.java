package com.rj.rjxecommerce.productdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.rj.rjxecommerce.app.ApplicationEcom;
import com.rj.rjxecommerce.network.EComService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ProductDetailsViewModel extends ViewModel {

    private Context context;
    private MutableLiveData<GetProductDetailsResponse> mutableLiveData;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LiveData<GetProductDetailsResponse> loadProductList(Context context) {
           this.context=context;
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<GetProductDetailsResponse>();
            getProductListTask();
        }
        return mutableLiveData;
    }

    private void getProductListTask() {

        ApplicationEcom appController = ApplicationEcom.create(context);
        EComService eComService = appController.getEcomService();
        Disposable disposable = eComService.getProductDetails()
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetProductDetailsResponse>() {
                    @Override
                    public void accept(GetProductDetailsResponse getProductDetailsResponse) throws Exception {
                        mutableLiveData.setValue(getProductDetailsResponse);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //Snackbar.make(, "Something went wrong!", Snackbar.LENGTH_SHORT).show();
                        Log.e("throwable", "" + throwable.getMessage());
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
