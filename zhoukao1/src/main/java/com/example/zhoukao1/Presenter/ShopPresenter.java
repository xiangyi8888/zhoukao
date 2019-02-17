package com.example.zhoukao1.Presenter;

import com.example.zhoukao1.bean.ShopBean;
import com.example.zhoukao1.contract.ShopContract;
import com.example.zhoukao1.model.ShopModel;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class ShopPresenter extends ShopContract.conPresenter {

    private ShopContract.conView view;
    private WeakReference<ShopContract.conView> wr;
    private ShopModel shopModel;

    public  ShopPresenter(ShopContract.conView view){
        wr=new WeakReference<>(view);
        this.view=wr.get();
        shopModel=new ShopModel();
    }
    @Override
    public void getshopData(HashMap<String, String> params) {
        shopModel.getshopData(params, new ShopModel.getResponceDataCallback() {
            @Override
            public void Success(String res) {
                if (view!=null){
                    ShopBean shopBean=new Gson().fromJson(res,ShopBean.class);
                    view.shopDataSuccess(shopBean);
                }
            }

            @Override
            public void Failure(String msg) {
                if (view!=null){
                    view.Frailure(msg);
                }
            }
        });
    }

    @Override
    public void getshopYiang(String id) {

    }

    public  void uBindView(){
        if (view!=null){
            wr.clear();
            wr=null;
            view=null;
        }
    }
}
