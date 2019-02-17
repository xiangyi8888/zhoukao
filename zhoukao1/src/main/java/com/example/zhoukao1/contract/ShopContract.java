package com.example.zhoukao1.contract;

import com.example.zhoukao1.bean.ShopBean;
import com.example.zhoukao1.bean.ShopYiang;
import com.example.zhoukao1.model.ShopModel;

import java.util.HashMap;

public interface ShopContract {


    abstract class conPresenter{
        public abstract void getshopData(HashMap<String,String> params);
        public abstract void getshopYiang(String id);
    }

    interface conModel{
        void getshopData(HashMap<String,String> params,ShopModel.getResponceDataCallback callback);
        void getshopYiang(HashMap<String,String> params,ShopModel.getResponceDataCallback callback);

    }
    interface conView{
        void shopDataSuccess(ShopBean shopBean);
        void shopYiangSuccess(ShopYiang shopYiang);
        void Frailure(String msg);
    }


}
