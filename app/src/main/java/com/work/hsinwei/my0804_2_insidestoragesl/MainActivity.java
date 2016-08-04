package com.work.hsinwei.my0804_2_insidestoragesl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File f = getCacheDir();
        Log.d("T0804-File",f.toString());
        String fName = "mydata";
        FileOutputStream fOut = null;
        try {
            //儲存檔案
            fOut=openFileOutput(fName,MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write("測試輸入資料");
            osw.close();
            //讀取檔案
            File readFile = new File(getFilesDir()+File.separator+fName);
            char[] buffer = new char[1];
            FileReader fr = null;
            StringBuilder sb = new StringBuilder();

            fr= new FileReader(readFile);

            Log.d("T0804-Char",""+buffer);
            Log.d("T0804-Char",""+buffer[0]);

            while (fr.read(buffer) != -1)
            {
                sb.append(new String(buffer));
            }
            fr.close();
            Log.d("T0804-FileR",sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //DAO
        dataInfoDAO dao =new dataInfoDAOImpl();
        dao.addDataInfo(new dataInfo("name","addresss","telephone"));
    }
    //Json 資料轉檔存入
    public void click1(View v)
    {
        String fName = "mydata.Json";
        FileOutputStream fOut = null;
        JSONArray array= new JSONArray();
        JSONObject obj;
        try {
            obj=new JSONObject();
            obj.put("name","名字");
            obj.put("addr","地址");
            obj.put("tel","電話");
            array.put(obj);

            obj=new JSONObject();
            obj.put("name","名字2");
            obj.put("addr","地址2");
            obj.put("tel","電話2");
            //Log.d("T0804-JsonS",obj.toString());
            array.put(obj);
            Log.d("T0804-JsonS",array.toString());

            fOut=openFileOutput(fName,MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(array.toString());
            osw.close();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Gson
    public void click2(View v)
    {
        ArrayList<dataInfo> mlist = new ArrayList<>();
        dataInfo d1= new dataInfo("Jo","aaa","12345");
        dataInfo d2= new dataInfo("Bo","bbb","22222");

        mlist.add(d1);
        mlist.add(d2);

        Gson gson =new Gson();
        String str = gson.toJson(mlist,new TypeToken<ArrayList<dataInfo>>(){}.getType());
        Log.d("T0804-Gson",str);

        String fName = "mydata2.Json";
        FileOutputStream fOut = null;

        try {
            fOut=openFileOutput(fName,MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(str);
            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
