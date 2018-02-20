package com.giant.jamie.webapi;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by G96937 on 2018/2/6.
 */

public class WebAPITask extends AsyncTask<String, Void, String> {

    private String TAG = WebAPITask.class.getSimpleName();
    private String request;

    public WebAPITask(String request){

        this.request = request;

    }

    @Override
    protected String doInBackground(String... strings) {

//        Log.i(TAG, request);
        String result = "";

        try {

            URL url = new URL(strings[0]);
            switch(request){

                case "POST":
                    result = postToURL(url);
                    break;

                case "GET":
                    result = getFromURL(url);
                    break;

                default:
                    break;

            }


        }catch (Exception e){

            Log.i(TAG, e.toString());

        }

        Log.i(TAG, result);
        return result;

    }

    private String postToURL(URL url) {

        String result = "";

        HttpURLConnection httpURLConnection = null;

        try {

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type","application/json");

            JSONObject object = new JSONObject();

            object.put("nickname", "Lioooooooooon");
            object.put("weight", "1550");
            object.put("height", "155");
            object.put("gender", "male");
            object.put("ftp", "150");
            object.put("birthday", "0000-00-00");
            object.put("userID", "0");
//                Log.i(TAG, object.toString());

            OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
            wr.write(object.toString());

            wr.flush();
            wr.close();

            httpURLConnection.connect();

//            Log.i(TAG, httpURLConnection.getResponseCode()+"_"+httpURLConnection.getDoOutput());

            if (httpURLConnection.getResponseCode() == 200) {

//                    Log.i(TAG, httpURLConnection.getResponseMessage());

                    //getting the response from the server
                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    int data = inputStreamReader.read();

                    while (data != -1) {

                        char current = (char) data;
                        result += current;
                        data = inputStreamReader.read();

                    }

                    inputStream.close();

                    Log.i(TAG, result+"");

            }
        }catch(Exception e){

            Log.i(TAG, e.toString());

        }finally {

            if (httpURLConnection != null)
                httpURLConnection.disconnect();

        }

        return result;

    }

    private String getFromURL(URL url){

        String result = "";
        try{
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            if(httpURLConnection.getResponseCode() == 200) {

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();
                while (data != -1) {

                    char current = (char) data;
                    result += current;
                    data = inputStreamReader.read();

                }

                Log.i(TAG, result);

            }


        }catch (Exception e){

            Log.i(TAG, e.toString());

        }

        return result;

    }

}
