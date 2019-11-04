package com.vullpes.testapplication.parsers;

import android.graphics.Bitmap;

import com.vullpes.testapplication.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FotosParcer {

    public static List<Bitmap> parseDados(String content) {

        JSONObject jsonObject, message;
        JSONArray dados;
        List<Bitmap> fotos = new ArrayList<>();

        try {

            jsonObject = new JSONObject(content);
            dados = (JSONArray) jsonObject.get("message");

            for (int i = 0; i< dados.length(); i++){
                fotos.add(Utils.getBitmapFromURL((String) dados.get(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fotos;
    }

}
