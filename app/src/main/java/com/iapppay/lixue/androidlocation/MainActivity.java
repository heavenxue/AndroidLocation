package com.iapppay.lixue.androidlocation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iapppay.lixue.loacationutil.HalopayLocation;
import com.iapppay.lixue.loacationutil.HalopayLocationListener;
import com.iapppay.lixue.loacationutil.LocationAPI;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button button_location;
    private TextView tv_location_base;

    private LocationAPI locationAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();
        button_location.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                locationAPI = new LocationAPI(getApplicationContext());
                locationAPI.getChangeCurLocation(new HalopayLocationListener() {

                    @Override
                    public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onProviderEnabled(String paramString) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onProviderDisabled(String paramString) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onLocationFail(String paramString) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onLocationChanged(HalopayLocation paramYongcheLocation) {
                        // TODO Auto-generated method stub
                        Log.i("纬度：", "" + paramYongcheLocation.getLatitude());//纬度
                        Log.i("经度：", "" + paramYongcheLocation.getLongitude());//经度
                    }
                });
                tv_location_base.setText("纬度:" + LocationAPI.getLastKnownLocation().getLatitude() + ",经度：" + LocationAPI.getLastKnownLocation().getLongitude());
            }
        });
    }

    private void initView(){
        button_location = (Button) findViewById(R.id.button_location);
        tv_location_base = (TextView) findViewById(R.id.tv_location_base);
    }

}
