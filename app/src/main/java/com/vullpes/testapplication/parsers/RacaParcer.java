package com.vullpes.testapplication.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class RacaParcer {

    public static List<String> parseDados(String content) {

        JSONObject jsonObject, message;
        List<String> racas = new ArrayList<>();

        try {
            jsonObject = new JSONObject(content);
            message = (JSONObject) jsonObject.get("message");

            Iterator<String> it = message.keys();
            while(it.hasNext()){
                racas.add(it.next());
            }

        } catch (JSONException e) {

            e.printStackTrace();

        }

        return racas;
    }

}
