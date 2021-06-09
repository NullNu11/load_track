package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;
import com.example.myapplication.JsonData.getsJsonData;
import com.example.myapplication.Presenter.TrackPre;
import com.example.myapplication.R;
import com.example.myapplication.View.trackView;
import com.example.myapplication.dao.TrackDao;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GaodeActivity extends AppCompatActivity implements trackView {

    MapView mMapView = null;
    AMap aMap = null;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    getsJsonData getsJsonData = new getsJsonData();
                    TrackDao trackDaoData = getsJsonData.trackJson((String) msg.obj);
                    UIDesign(trackDaoData);

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaode);

        mMapView = findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        showMap();
    }
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        mMapView.onSaveInstanceState(outState);
//    }
    void showMap() {
        //初始化地图控制器对象
        aMap = mMapView.getMap();

        UiSettings mUiSettings = null;//定义一个UiSettings对象
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
        //缩放
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setZoomPosition(0);
        //指南针
        //mUiSettings.setCompassEnabled(true);
        //比例尺
        //mUiSettings.setScaleControlsEnabled(true);
        //定位
        mUiSettings.setMyLocationButtonEnabled(false); //显示默认的定位按钮
        //定位蓝点
        MyLocationStyle myLocationStyle;

        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        //蓝点样式
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.icon));

        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.setLocationSource(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        //aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(10));
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动
        //aMap.setOnCameraChangeListener(this);
        TrackPre trackPre = new TrackPre(GaodeActivity.this);
        trackPre.addTrack(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.d("获取失败", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Message message = new Message();
                message.what = 1;
                message.obj = responseData;
                handler.sendMessage(message);
            }
        });

        trackPre.findTrid(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("111111111111111111", response.body().string());
            }
        });
    }

    @Override
    public String getUserid() {

        return "18817786730";
    }

    @Override
    public String getTrid() {
        return "4387f42f-d4e0-4e12-8b3b-3a5348b6d9ab";
    }

    void UIDesign(TrackDao trackDaoData) {
        TextView time = findViewById(R.id.timeView);
        TextView mile = findViewById(R.id.mileView);
        TextView hash = findViewById(R.id.hashView);
        TextView car = findViewById(R.id.carView);
        TextView block = findViewById(R.id.blockView);
        //time.setText(trackDaoData.getTime());
        block.setText(trackDaoData.getDevid());
        //time.setText("2021-05-04 10:37:20");
        time.setText(trackDaoData.getTime());
        mile.setText(trackDaoData.getMile() + "");
        hash.setText(trackDaoData.getTrid());
        //car.setText(trackDaoData.getDevstr());
        car.setText("川·A06490");
        aMap.addPolyline(new PolylineOptions().
                addAll(trackDaoData.getLatLngs()).width(10).color(Color.argb(255, 255, 1, 1)));
    }

}




