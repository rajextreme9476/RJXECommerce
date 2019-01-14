package com.rj.rjxecommerce.app;

import android.app.Application;
import android.content.Context;

import com.rj.rjxecommerce.network.ApiFactory;
import com.rj.rjxecommerce.network.EComService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class ApplicationEcom extends Application
{
    private static ApplicationEcom instance;
    private EComService usersService;
    private Scheduler scheduler;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static synchronized ApplicationEcom getInstance() {
        return instance;
    }


    private static ApplicationEcom get(Context context) {
        return (ApplicationEcom) context.getApplicationContext();
    }

    public static ApplicationEcom create(Context context) {
        return ApplicationEcom.get(context);
    }

    public EComService getEcomService() {
        if (usersService == null) {
            usersService = ApiFactory.getInstance().create(instance);
        }

        return usersService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setUserService(EComService usersService) {
        this.usersService = usersService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

}
