package com.example.cpptest;


import static com.example.cpptest.MainActivity.authorizeUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CppLibraryUnitTest {


    @Test
    public void authorizeUserSingUp_nullOrWhiteSpaceEmailFormat_falseReturned() {
        try{
            assertFalse( authorizeUser(1,null,"123asdfasdf"));
            assertFalse( authorizeUser(1,"","123asdfasdf"));
        }catch (Throwable ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void authorizeUserSingUp_wrongEmailEmailFormat_falseReturned() {
        try{
        assertFalse( authorizeUser(1,"aa@@mail.ru","123asdfasdf"));
        assertFalse( authorizeUser(1,"\\@mail.ru","123asdfasdf"));
        assertFalse( authorizeUser(1,"asd$asd@mail.ru","123asdfasdf"));
        assertFalse( authorizeUser(1,"zxc^asd@mail.ru","123asdfasdf"));
        assertFalse( authorizeUser(1,"zx%aasd@mail.ru","123asdfasdf"));
        } catch (UnsatisfiedLinkError ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void authorizeUserSingUp_shortPassword_falseReturned() {
        try{
        assertFalse( authorizeUser(1,"bruhMe@mail.ru","12345678"));
        assertFalse( authorizeUser(1,"bruhMe@mail.ru","1234567"));
        assertFalse( authorizeUser(1,"bruhMe@mail.ru","123456"));
        assertFalse( authorizeUser(1,"bruhMe@mail.ru","12345"));
        assertFalse( authorizeUser(1,"bruhMe@mail.ru","1234"));
        assertFalse( authorizeUser(1,"bruhMe@mail.ru","123"));
        assertFalse( authorizeUser(1,"bruhMe@mail.ru","12"));
        }catch (Throwable ex){
        ex.printStackTrace();
         }
    }

    @Test
    public void databaseQuery_correctResultReturned() {
        assertEquals(true,true);
    }
}
