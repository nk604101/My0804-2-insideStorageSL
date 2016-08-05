package com.work.hsinwei.my0804_2_insidestoragesl;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hsinwei on 2016/8/4.
 */
public class dataInfoDAOImpl implements dataInfoDAO {
    static ArrayList<dataInfo> mylist;
    Context context;
    public dataInfo(Context context)
    {
        this.context=context;
        String fName="dataInfo";
        File readFile = new File(context.getFilesDir()+File.separator+fName);
        char[] buffer = new char[1];
        FileReader fr = null;
        StringBuilder sb = new StringBuilder();

        try {
            fr= new FileReader(readFile);
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
        String data = sb.toString();
        if(data.length()==0)
        {
            mylist=new ArrayList<>();
        }
        else
        {
            Gson gson =new Gson();
            String str = gson.toJson(data,new TypeToken<ArrayList<dataInfo>>(){}.getType());
            Log.d("T0805-DataGson",str);
        }
    }

    @Override
    public void addDataInfo(dataInfo d) {

    }

    @Override
    public void delDataInfo(dataInfo d) {

    }

    @Override
    public void updtaeDataInfo(dataInfo d) {

    }

    @Override
    public List getAllDataInfo() {

        return mylist;
    }
}
