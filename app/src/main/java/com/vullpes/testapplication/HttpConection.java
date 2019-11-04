package com.vullpes.testapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

public class HttpConection {

    public  static String getDados(){

        BufferedReader reader = null;
        String uri = "https://dog.ceo/api/breeds/list/all";

        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();
        }
        catch(UnknownHostException e){
            e.printStackTrace();
            return "Connection Error";
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    public static String getDados(String nome,String numero){

        BufferedReader reader = null;
        String uri = "https://dog.ceo/api/breed/"+nome+"/images/random/"+numero;

        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();
        }
        catch(UnknownHostException e){
            e.printStackTrace();
            return "Connection Error";
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }






}
