package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.example.myapplication.tools.getsJsonData;
import com.example.myapplication.Presenter.TrackPre;
import com.example.myapplication.R;
import com.example.myapplication.View.trackView;
import com.example.myapplication.dao.TrackDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GaodeActivity extends AppCompatActivity implements trackView, GeocodeSearch.OnGeocodeSearchListener {

    MapView mMapView = null;
    AMap aMap = null;
    int iiiiiii=1;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    getsJsonData getsJsonData = new getsJsonData();
                    List<TrackDao> trackDaoData = getsJsonData.trackJson((String) msg.obj);
                    UIDesign(trackDaoData.get(iiiiiii));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaode);
        Intent intent=getIntent();
        //详细的条目数
        iiiiiii=intent.getIntExtra("index",2);

        mMapView = findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        showMap();
    }

    void showMap() {
        //初始化地图控制器对象
        aMap = mMapView.getMap();
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
                Log.d("5555555555555555", responseData);
                handler.sendMessage(message);
            }
        });

        trackPre.findTrid(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("11111111199999999999", response.body().string());

            }
        });
    }

    //请求循环列表的数据 的userid
    @Override
    public String getUserid() {
        SharedPreferences userInfo = getSharedPreferences("TrackDao", MODE_PRIVATE);
        String userid = userInfo.getString("userid", "0");
        if (userid != "0")
            return userid;
        else {
            Toast.makeText(GaodeActivity.this,"请先登录",Toast.LENGTH_LONG).show();
            return null;
        }
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
        car.setText(trackDaoData.getDevstr());
        aMap.moveCamera(CameraUpdateFactory.zoomTo((float) 14.5));
        //获取轨迹数据的中心点
        int size=trackDaoData.getLatLngs().size()/2;
        LatLng latLng = new LatLng(trackDaoData.getLatLngs().get(size/2).latitude,trackDaoData.getLatLngs().get(size/2).longitude);
        aMap. moveCamera(CameraUpdateFactory.changeLatLng(latLng));//这个是关键  如果不设置的话中心点是北京，
        aMap.addPolyline(new PolylineOptions().
                addAll(trackDaoData.getLatLngs()).width(10).color(Color.argb(255, 255, 1, 1)));



        //获取逆地址编码
        GeocodeSearch geocoderSearch = null;
        try {
            geocoderSearch = new GeocodeSearch(this);
        } catch (AMapException e) {
            e.printStackTrace();
        }
        geocoderSearch.setOnGeocodeSearchListener(this);
        //初始点和结束点
        LatLonPoint start = new LatLonPoint(trackDaoData.getLatLngs().get(0).latitude, trackDaoData.getLatLngs().get(0).longitude);
        RegeocodeQuery query = new RegeocodeQuery(start, 100,GeocodeSearch.AMAP);
        //geocoderSearch.getFromLocationAsyn(query);
        Log.d("555555559", String.valueOf(size));
        LatLonPoint end = new LatLonPoint(trackDaoData.getLatLngs().get(size-1).latitude, trackDaoData.getLatLngs().get(size-1).longitude);
        RegeocodeQuery query1 = new RegeocodeQuery(end, 100,GeocodeSearch.AMAP);
        geocoderSearch.getFromLocationAsyn(query1);

    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

        Log.d("555555555555556", regeocodeResult.getRegeocodeAddress().getNeighborhood());

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }
}




