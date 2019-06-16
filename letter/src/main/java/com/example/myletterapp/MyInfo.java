package com.example.myletterapp;

public class MyInfo {
    private String Title;      //首字母存入
    private boolean HeaderFlag;//是否是标题的判断
    private String Id;         //该条数据的id
    private String HanZi;      //该条数据的内容（城市名称）
    private boolean TouchFlag; //拖动下一条为标题时标志

    public boolean isHeaderFlag() {
        return HeaderFlag;
    }

    public void setHeaderFlag(boolean headerFlag) {
        HeaderFlag = headerFlag;
    }

    public String getHanZi() {
        return HanZi;
    }

    public void setHanZi(String hanZi) {
        HanZi = hanZi;
    }

    public boolean isTouchFlag() {
        return TouchFlag;
    }

    public void setTouchFlag(boolean touchFlag) {
        TouchFlag = touchFlag;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
