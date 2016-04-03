// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.android;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.Utility;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import org.json.JSONObject;

// Referenced classes of package com.facebook.android:
//            FacebookError

public final class Util
{

    private static final String UTF8 = "UTF-8";

    public Util()
    {
    }

    public static Bundle decodeUrl(String s)
    {
        int i;
        Bundle bundle;
        i = 0;
        bundle = new Bundle();
        if (s == null) goto _L2; else goto _L1
_L1:
        String as[];
        int j;
        as = s.split("&");
        j = as.length;
_L8:
        if (i >= j) goto _L2; else goto _L3
_L3:
        String as1[] = as[i].split("=");
        if (as1.length != 2) goto _L5; else goto _L4
_L4:
        bundle.putString(URLDecoder.decode(as1[0], "UTF-8"), URLDecoder.decode(as1[1], "UTF-8"));
          goto _L6
_L5:
        try
        {
            if (as1.length == 1)
            {
                bundle.putString(URLDecoder.decode(as1[0], "UTF-8"), "");
            }
        }
        catch (UnsupportedEncodingException unsupportedencodingexception) { }
          goto _L6
_L2:
        return bundle;
_L6:
        i++;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public static String encodePostBody(Bundle bundle, String s)
    {
        if (bundle == null)
        {
            return "";
        }
        StringBuilder stringbuilder = new StringBuilder();
        Iterator iterator = bundle.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s1 = (String)iterator.next();
            Object obj = bundle.get(s1);
            if (obj instanceof String)
            {
                stringbuilder.append((new StringBuilder("Content-Disposition: form-data; name=\"")).append(s1).append("\"\r\n\r\n").append((String)obj).toString());
                stringbuilder.append((new StringBuilder("\r\n--")).append(s).append("\r\n").toString());
            }
        } while (true);
        return stringbuilder.toString();
    }

    public static String encodeUrl(Bundle bundle)
    {
        if (bundle == null)
        {
            return "";
        }
        StringBuilder stringbuilder = new StringBuilder();
        Iterator iterator = bundle.keySet().iterator();
        boolean flag = true;
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s = (String)iterator.next();
            if (bundle.get(s) instanceof String)
            {
                if (flag)
                {
                    flag = false;
                } else
                {
                    stringbuilder.append("&");
                }
                stringbuilder.append((new StringBuilder()).append(URLEncoder.encode(s)).append("=").append(URLEncoder.encode(bundle.getString(s))).toString());
            }
        } while (true);
        return stringbuilder.toString();
    }

    public static String openUrl(String s, String s1, Bundle bundle)
    {
        HttpURLConnection httpurlconnection;
        Bundle bundle1;
        BufferedOutputStream bufferedoutputstream;
        if (s1.equals("GET"))
        {
            s = (new StringBuilder()).append(s).append("?").append(encodeUrl(bundle)).toString();
        }
        Utility.logd("Facebook-Util", (new StringBuilder()).append(s1).append(" URL: ").append(s).toString());
        httpurlconnection = (HttpURLConnection)HttpInstrumentation.openConnection((new URL(s)).openConnection());
        httpurlconnection.setRequestProperty("User-Agent", (new StringBuilder()).append(System.getProperties().getProperty("http.agent")).append(" FacebookAndroidSDK").toString());
        if (s1.equals("GET"))
        {
            break MISSING_BLOCK_LABEL_562;
        }
        bundle1 = new Bundle();
        Iterator iterator = bundle.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s4 = (String)iterator.next();
            Object obj = bundle.get(s4);
            if (obj instanceof byte[])
            {
                bundle1.putByteArray(s4, (byte[])obj);
            }
        } while (true);
        if (!bundle.containsKey("method"))
        {
            bundle.putString("method", s1);
        }
        if (bundle.containsKey("access_token"))
        {
            bundle.putString("access_token", URLDecoder.decode(bundle.getString("access_token")));
        }
        httpurlconnection.setRequestMethod("POST");
        httpurlconnection.setRequestProperty("Content-Type", (new StringBuilder("multipart/form-data;boundary=")).append("3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f").toString());
        httpurlconnection.setDoOutput(true);
        httpurlconnection.setDoInput(true);
        httpurlconnection.setRequestProperty("Connection", "Keep-Alive");
        httpurlconnection.connect();
        bufferedoutputstream = new BufferedOutputStream(httpurlconnection.getOutputStream());
        bufferedoutputstream.write((new StringBuilder("--")).append("3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f").append("\r\n").toString().getBytes());
        bufferedoutputstream.write(encodePostBody(bundle, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f").getBytes());
        bufferedoutputstream.write((new StringBuilder()).append("\r\n").append("--").append("3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f").append("\r\n").toString().getBytes());
        if (!bundle1.isEmpty())
        {
            for (Iterator iterator1 = bundle1.keySet().iterator(); iterator1.hasNext(); bufferedoutputstream.write((new StringBuilder()).append("\r\n").append("--").append("3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f").append("\r\n").toString().getBytes()))
            {
                String s3 = (String)iterator1.next();
                bufferedoutputstream.write((new StringBuilder("Content-Disposition: form-data; filename=\"")).append(s3).append("\"").append("\r\n").toString().getBytes());
                bufferedoutputstream.write((new StringBuilder("Content-Type: content/unknown")).append("\r\n").append("\r\n").toString().getBytes());
                bufferedoutputstream.write(bundle1.getByteArray(s3));
            }

        }
        break MISSING_BLOCK_LABEL_552;
        Exception exception;
        exception;
        bufferedoutputstream.close();
        throw exception;
        bufferedoutputstream.flush();
        bufferedoutputstream.close();
        String s2;
        try
        {
            s2 = read(httpurlconnection.getInputStream());
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            return read(httpurlconnection.getErrorStream());
        }
        return s2;
    }

    public static JSONObject parseJson(String s)
    {
        if (s.equals("false"))
        {
            throw new FacebookError("request failed");
        }
        if (s.equals("true"))
        {
            s = "{value : true}";
        }
        JSONObject _tmp = JVM INSTR new #267 <Class JSONObject>;
        JSONObject jsonobject = JSONObjectInstrumentation.init(s);
        if (jsonobject.has("error"))
        {
            JSONObject jsonobject1 = jsonobject.getJSONObject("error");
            throw new FacebookError(jsonobject1.getString("message"), jsonobject1.getString("type"), 0);
        }
        if (jsonobject.has("error_code") && jsonobject.has("error_msg"))
        {
            throw new FacebookError(jsonobject.getString("error_msg"), "", Integer.parseInt(jsonobject.getString("error_code")));
        }
        if (jsonobject.has("error_code"))
        {
            throw new FacebookError("request failed", "", Integer.parseInt(jsonobject.getString("error_code")));
        }
        if (jsonobject.has("error_msg"))
        {
            throw new FacebookError(jsonobject.getString("error_msg"));
        }
        if (jsonobject.has("error_reason"))
        {
            throw new FacebookError(jsonobject.getString("error_reason"));
        } else
        {
            return jsonobject;
        }
    }

    public static Bundle parseUrl(String s)
    {
        String s1 = s.replace("fbconnect", "http");
        Bundle bundle;
        try
        {
            URL url = new URL(s1);
            bundle = decodeUrl(url.getQuery());
            bundle.putAll(decodeUrl(url.getRef()));
        }
        catch (MalformedURLException malformedurlexception)
        {
            return new Bundle();
        }
        return bundle;
    }

    private static String read(InputStream inputstream)
    {
        StringBuilder stringbuilder = new StringBuilder();
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream), 1000);
        for (String s = bufferedreader.readLine(); s != null; s = bufferedreader.readLine())
        {
            stringbuilder.append(s);
        }

        inputstream.close();
        return stringbuilder.toString();
    }

    public static void showAlert(Context context, String s, String s1)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle(s);
        builder.setMessage(s1);
        builder.create().show();
    }
}
