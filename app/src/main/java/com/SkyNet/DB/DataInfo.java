package com.SkyNet.DB;

public class DataInfo {

    static String ip = "10.152.8.50";

    static String url = "jdbc:mysql://"+ip+":3306/lbq";

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        DataInfo.ip = ip;
        url = "jdbc:mysql://"+ip+":3306/lbq";
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        DataInfo.url = url;
    }
}
