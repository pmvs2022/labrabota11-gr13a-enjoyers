package com.example.cpptest;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;


public class ShopActivity extends AppCompatActivity {

    private Intent intent;
    private Location nemiga_shop = new Location("");
    private Location railway_station_shop = new Location("");
    private Location vostok_shop = new Location("");
    private Location oktyabrskaya_shop = new Location("");

    private ImageView ivShopImage;
    private TextView tvShopAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_shop);
        ivShopImage = (ImageView) findViewById(R.id.iv_shop_image);
        tvShopAddress = (TextView) findViewById(R.id.tv_shop_address);
        MyLocationListener.SetUpLocationListener(this);

        nemiga_shop.setLongitude(53.902440);
        nemiga_shop.setLatitude(27.552947);
        railway_station_shop.setLongitude(53.890772);
        railway_station_shop.setLatitude(27.552037);
        vostok_shop.setLongitude(53.935874);
        vostok_shop.setLatitude(27.654413);
        oktyabrskaya_shop.setLongitude(53.900963);
        oktyabrskaya_shop.setLatitude(27.559223);

        Shop[] shops = {new Shop(R.string.nemiga, R.drawable.nemiga),
                new Shop(R.string.railway_station, R.drawable.railway_station),
                new Shop(R.string.vostok, R.drawable.vostok),
                new Shop(R.string.oktyabrskaya, R.drawable.oktyabrskaya)};
        Location[] shopLocations = {nemiga_shop, railway_station_shop, vostok_shop, oktyabrskaya_shop};
        int closestShopID = 0;
        double minDistance = MyLocationListener.imHere.distanceTo(shopLocations[0]);
        for (int i = 1; i < shops.length; ++i) {
            double distance = MyLocationListener.imHere.distanceTo(shopLocations[i]);
            if (minDistance > distance) {
                minDistance = distance;
                closestShopID = i;
            }
        }
        ivShopImage.setImageResource(shops[closestShopID].getImageID());
        tvShopAddress.setText(shops[closestShopID].getAddressID());
    }


}
