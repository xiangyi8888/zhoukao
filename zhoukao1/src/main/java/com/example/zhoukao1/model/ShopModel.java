package com.example.zhoukao1.model;

import com.example.zhoukao1.api.Api;
import com.example.zhoukao1.contract.ShopContract;
import com.example.zhoukao1.retrofitutils.RetrofitCallback;
import com.example.zhoukao1.retrofitutils.RetrofitUtils;

import java.util.HashMap;

public class ShopModel implements ShopContract.conModel {
    @Override
    public void getshopData(HashMap<String, String> params, final ShopModel.getResponceDataCallback callback) {
         String keyword=params.get("keyword");
         String page=params.get("page");
         String count=params.get("count");
        RetrofitUtils.initRetrofitUtils().doGet(Api.selectUrl + "?keyword=" + keyword + "&page=" + page + "&count=" + count, new RetrofitCallback() {
            @Override
            public void Success(String response) {
                if (callback!=null){
                    callback.Success(response);
                }
            }

            @Override
            public void Failure(String msg) {

                if (callback!=null){
                    callback.Failure(msg);
                }
            }
        });

    }

    @Override
    public void getshopYiang(HashMap<String, String> params, ShopModel.getResponceDataCallback callback) {

    }

    public interface getResponceDataCallback{
        void Success(String res);
        void Failure(String msg);
    }
}
