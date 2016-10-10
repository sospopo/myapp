package com.example.a10134.mrobot.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class StreamTool {

    public static StringBuffer readInputStream(InputStream inputStream) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer sb = new StringBuffer();
        String str;
        while ((str=reader.readLine()) != null) {
           sb.append(str);
        }
        inputStream.close();
        if(reader != null){
            reader.close();
        }
        return sb;
    }
}
