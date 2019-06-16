package com.SkyNet.weather;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.SkyNet.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    HttpClient client;
    //参数键值对集合
    private ArrayList<BasicNameValuePair> param;
    //参数键值对列表，有多少个参数的情况下适用
    private ArrayList<BasicNameValuePair> paramList;
//    private EditText etCityName;
    String weatherURL = "http://v.juhe.cn/weather/index?";
    //httpclient对象的参数，用作给http连接设置参数
    HttpParams params;
    EditText cityName;
    Button select;
    TextView text;
    ListView lv;
    WeatherAdapter adapter;
    //查询结果合集
    ArrayList<WeatherInfo> weatherList;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String result = msg.obj.toString();
                    addWeather(result);
                    adapter = new WeatherAdapter(getApplicationContext(), weatherList);
                    lv.setAdapter(adapter);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        lv = (ListView) findViewById(R.id.weatherlist);
        cityName = (EditText) findViewById(R.id.cityname);
        select = (Button) findViewById(R.id.select);
        text = (TextView) findViewById(R.id.text);
        client = new DefaultHttpClient();

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paramCreate();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String requestURL = weatherURL;
                        String result = null;
                        //用get方法字符串拼接为url
                        for (int i = 0; i < param.size(); i++) {
                            requestURL = requestURL + param.get(i).getName() + "=" +
                                    param.get(i).getValue() + "&";
                        }
                        System.out.println(requestURL);
                        //建立get请求
                        HttpGet request = new HttpGet(requestURL);

                        try {
                            //发送请求
                            HttpResponse httpResponse = client.execute(request);
                            //当请求返回码为200时连接成功
                            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                                result = EntityUtils.toString(httpResponse.getEntity());
                                System.out.println(result);
                            } else if (httpResponse.getStatusLine().getStatusCode() == 404) {
                                result = "连接失败！页面未找到";
                            } else {
                                result = "连接失败";
                            }
                            //查询完成之后发送信息更新UI
                            Message m = new Message();
                            m.what = 1;
                            m.obj = result;
                            handler.sendMessage(m);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }).start();
            }
        });
    }

    private void paramCreate() {
        param = new ArrayList<BasicNameValuePair>();
        BasicNameValuePair param1 = new BasicNameValuePair(
                "key", "4d87479b9e0dfbfafb934739c9a59874");
        BasicNameValuePair param2 = new BasicNameValuePair(
                "dtype", "json");
        BasicNameValuePair param3 = new BasicNameValuePair(
                "cityname", cityName.getText().toString());
        BasicNameValuePair param4 = new BasicNameValuePair(
                "format", "2");
        param.add(param1);
        param.add(param2);
        param.add(param3);
        param.add(param4);
    }


    private void addWeather(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject resultJson = jsonObject.getJSONObject("result");
            JSONArray futureWeather = resultJson.getJSONArray("future");
            weatherList = new ArrayList<WeatherInfo>();
            for (int i = 0; i < futureWeather.length(); i++) {
                JSONObject futureJson = futureWeather.getJSONObject(i);
                String temp = futureJson.getString("temperature");
                String weather = futureJson.getString("weather");
                String wind = futureJson.getString("wind");
                String week = futureJson.getString("week");
                String date = futureJson.getString("date");
                WeatherInfo futureInfo = new WeatherInfo(temp, weather, wind, week, date);
                weatherList.add(futureInfo);
            }
            for (int i = 0; i < weatherList.size(); i++) {
                System.out.println(weatherList.get(i).getTemp());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
