package com.example.atfalna.atfalna_app1;

import android.app.Application;

/**
 * Created by amr1 on 3/16/2018.
 */

public class GloablV extends Application {

    private String email_user_login;
    private String user_id_login;
    private String user_name_login;

    // map_f
    private String Lat_f;
    private String Lng_f;

    // map_m
    private String Lat_m;
    private String Lng_m;

    public String getLat_m() {
        return Lat_m;
    }

    public void setLat_m(String lat_m) {
        Lat_m = lat_m;
    }

    public String getLng_m() {
        return Lng_m;
    }

    public void setLng_m(String lng_m) {
        Lng_m = lng_m;
    }



    public String getLng_f() {
        return Lng_f;
    }

    public void setLng_f(String lng_f) {
        Lng_f = lng_f;
    }

    public String getLat_f() {

        return Lat_f;
    }

    public void setLat_f(String lat_f) {
        Lat_f = lat_f;
    }

    public String getUser_id_login() {
        return user_id_login;
    }

    public void setUser_id_login(String user_id_login) {
        this.user_id_login = user_id_login;
    }

    public String getUser_name_login() {
        return user_name_login;
    }

    public void setUser_name_login(String user_name_login) {
        this.user_name_login = user_name_login;
    }

    public String getEmail_user_login() {
        return email_user_login;
    }

    public void setEmail_user_login(String email_user_login) {
        this.email_user_login = email_user_login;
    }

    private String ip_url ;



    public String getIp_url() {
        return ip_url;
    }

    public void setIp_url(String ip_url) {
        this.ip_url = ip_url;
    }



}
