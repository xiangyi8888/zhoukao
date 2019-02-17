package com.example.zhoukao1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zhoukao1.Presenter.ShopPresenter;
import com.example.zhoukao1.adapter.ShopDataAdapter;
import com.example.zhoukao1.bean.ShopBean;
import com.example.zhoukao1.bean.ShopYiang;
import com.example.zhoukao1.contract.ShopContract;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements ShopContract.conView,XRecyclerView.LoadingListener {
    private Unbinder bind;

    @BindView(R.id.rv)
    XRecyclerView rv;
    @BindView(R.id.but_name)
    Button but_name;
    @BindView(R.id.et_name)
    EditText et_name;
    private int page=1;
    private String count="5";
    private ShopPresenter shopPresenter;
    private ShopDataAdapter shopDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        shopPresenter = new ShopPresenter(this);
        rv.setLoadingListener(this);
        rv.setLoadingMoreEnabled(true);
        rv.setLayoutManager(new GridLayoutManager(this,2));
    }
    @OnClick(R.id.but_name)
    public void but(View view){
        paserResult();
    }

    private void paserResult() {
        String s=et_name.getText().toString();
        HashMap<String,String> params=new HashMap<>();
        params.put("keyword",s);
        params.put("page",page+"");
        params.put("count",count);
        shopPresenter.getshopData(params);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void shopDataSuccess(ShopBean shopBean) {
        if (shopBean!=null){
            if (page==1){
                rv.refreshComplete();
                shopDataAdapter=new ShopDataAdapter(shopBean.result,this);
                rv.setAdapter(shopDataAdapter);
            }else{//加载
                if (shopDataAdapter==null) {
                    shopDataAdapter=new ShopDataAdapter(shopBean.result,this);
                    rv.setAdapter(shopDataAdapter);
                }else{
                    shopDataAdapter.addData(shopBean.result);
                }
                rv.loadMoreComplete();
            }
        }
    }

    @Override
    public void shopYiangSuccess(ShopYiang shopYiang) {

    }

    @Override
    public void Frailure(String msg) {

    }

    @Override
    public void onRefresh() {
        page=1;
        paserResult();
    }

    @Override
    public void onLoadMore() {
        page++;
        paserResult();
    }
}
