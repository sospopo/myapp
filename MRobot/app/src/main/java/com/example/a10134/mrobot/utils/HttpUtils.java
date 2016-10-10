package com.example.a10134.mrobot.utils;

import com.example.a10134.mrobot.Bean.ChatMessage;
import com.example.a10134.mrobot.Bean.Result;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

public class HttpUtils {
    private static String URL = "http://www.tuling123.com/openapi/api";
    private static String API_KEY = "b5e25c7895914e8dae2a03cbb9597eb4";

    public static ChatMessage sendMessage(String msg){
        ChatMessage chatMessage = new ChatMessage();
        Gson gson = new Gson();
        String jsonMsg = doGet(msg);
        try {
            Result result = null;
            result = gson.fromJson(jsonMsg, Result.class);
            chatMessage.setMsg(result.getText());
        }catch (JsonSyntaxException e){
            e.printStackTrace();
            chatMessage.setMsg("服务器繁忙，请稍后再试！");
        }
        chatMessage.setDate(new Date());
        chatMessage.setType(ChatMessage.Type.INCOMING);
        return chatMessage;
    }


    private static String doGet(String msg){
        String result = "";
        String url = null;
        try {
            url = setParams(msg);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            java.net.URL urlCon = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlCon.openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

            InputStream in = conn.getInputStream();

            StringBuffer sb = StreamTool.readInputStream(in);

            result = sb.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    private static String setParams(String msg) throws UnsupportedEncodingException {
        String url = "";
        url = URL + "?key=" + API_KEY + "&info=" + URLEncoder.encode(msg,"UTF-8");
        return url;
    }
}
