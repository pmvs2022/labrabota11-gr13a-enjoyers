package com.example.cpptest;


import static org.junit.Assert.*;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class ShopUnitTest {

    private ArrayList<Shop> shops = new ArrayList<Shop>(Arrays.asList(
            new Shop(0, 0),
            new Shop(1, 1),
            new Shop(2, 2),
            new Shop(3, 3)
    ));

    @Test
    public void getAddressID_correctResultReturned() {
        for (int i = 0; i<shops.size();i++){
            assertEquals(i,shops.get(i).getAddressID());
        }
    }

    @Test
    public void getImageID_correctResultReturned() {
        for (int i = 0; i<shops.size();i++){
            assertEquals(i,shops.get(i).getImageID());
        }
    }
}
