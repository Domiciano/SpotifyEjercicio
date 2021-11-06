package edu.co.icesi.firestoreejemplo.activities;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HTTPSWebUtilDomi {

    public String GETrequest(String title) {
        try {
            URL page = new URL("https://api.spotify.com/v1/search?q="+title+"&type=track");
            HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer BQA9uzSqEFBIIUtnpwinsx6wVDI64fmDViiIjKddReoKNtNVnj230Xaes9G8AwZlyZNQy39m5jHH3aZQH7D6yYGUqW-Je-yBF7RGxFH_cJuMmbuHEGUCirDCKhnAZlxoIdCHfa10LXhN0YmjX-ECGfXlvQCBYG-9eNo8jw");


            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            is.close();
            baos.close();
            connection.disconnect();
            return new String(baos.toByteArray(), "UTF-8");
        }catch (IOException ex){
            ex.printStackTrace();
            return "";
        }
    }

    public String POSTrequest(String url, String json) {
        try {
            URL page = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("accept", "application/json");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();

            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            is.close();
            baos.close();
            os.close();
            connection.disconnect();
            return new String(baos.toByteArray(), "UTF-8");
        }catch (IOException ex){
            ex.printStackTrace();
            return "";
        }
    }


    public static final String FCM_KEY = "AAAA5Y28dsA:APA91bHvrWHBdEZIbpl2hmPufyiNgJp1ZNiCenoAb58dM2ydWkGTx5cYtQzlVewJUL2PL0s1Rkir0mTLKtEA0vrrHyeG1bID3HuCd0UVtO91bcuciPPWxZcSsosmRGmGV9N4tEeH90Gr";

    public String POSTtoFCM(String json) {
        try {
            URL page = new URL("https://fcm.googleapis.com/fcm/send");
            HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("Authorization", "key="+FCM_KEY);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();

            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            is.close();
            baos.close();
            os.close();
            connection.disconnect();
            return new String(baos.toByteArray(), "UTF-8");
        }catch (IOException ex){
            ex.printStackTrace();
            return "";
        }
    }

    public String PUTrequest(String url, String json) {
        try {
            URL page = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();

            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            is.close();
            baos.close();
            os.close();
            connection.disconnect();
            return new String(baos.toByteArray(), "UTF-8");
        }catch (IOException ex){
            ex.printStackTrace();
            return "";
        }

    }

    public String DELETErequest(String url) {
        try {
            URL page = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) page.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            is.close();
            baos.close();
            connection.disconnect();

            return new String(baos.toByteArray(), "UTF-8");
        }catch (IOException ex){
            ex.printStackTrace();
            return "";
        }

    }

}