package com.example.administrator.mybombtest;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/3/11 0011.
 */

public class Person extends BmobObject {
    private String name;
    private int age;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
