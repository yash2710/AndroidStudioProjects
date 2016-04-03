// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;

import android.content.Context;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

// Referenced classes of package crittercism.android:
//            f, a, g

public class c
{

    private final DefaultHttpClient a;
    private final String b;

    public c()
    {
        b = "";
        a = a();
    }

    public c(String s, Context context)
    {
        b = s;
        a = a();
    }

    private String a(HttpRequestBase httprequestbase)
    {
        DefaultHttpClient defaulthttpclient;
        HttpResponse httpresponse;
        a.getConnectionManager().closeExpiredConnections();
        defaulthttpclient = a;
        if (defaulthttpclient instanceof HttpClient)
        {
            break MISSING_BLOCK_LABEL_122;
        }
        httpresponse = defaulthttpclient.execute(httprequestbase);
_L1:
        int j = httpresponse.getStatusLine().getStatusCode();
        switch (j)
        {
        default:
            String s;
            try
            {
                crittercism/android/c.getCanonicalName();
                (new StringBuilder("executeHttpRequest error=")).append(j).append(" - ").append(httpresponse.getStatusLine().toString());
                httpresponse.getEntity().consumeContent();
            }
            catch (ConnectTimeoutException connecttimeoutexception)
            {
                crittercism/android/c.getClass().getCanonicalName();
                throw new f(crittercism.android.a.a(11), f.a.b);
            }
            catch (IOException ioexception)
            {
                httprequestbase.abort();
                for (int i = 0; i < httprequestbase.getAllHeaders().length; i++)
                {
                    Header header = httprequestbase.getAllHeaders()[i];
                    (new StringBuilder()).append(header.getName()).append(": ").append(header.getValue());
                }

                if (ioexception.getMessage() != null && ioexception.getMessage().toLowerCase().contains("no route to host"))
                {
                    throw new f(crittercism.android.a.a(10), f.a.a);
                }
                if (ioexception.getStackTrace()[0].toString().contains("java.net.InetAddress.lookupHostByName"))
                {
                    crittercism/android/c.getClass().getCanonicalName();
                    throw new f(crittercism.android.a.a(10), f.a.a);
                } else
                {
                    throw ioexception;
                }
            }
            catch (Exception exception)
            {
                crittercism/android/c.getClass().getCanonicalName();
                (new StringBuilder("Neither ConnectTimeoutException nor IOException.  Instead: ")).append(exception.getClass().getName());
                return "";
            }
            return "";

        case 200: 
            break;
        }
        break MISSING_BLOCK_LABEL_136;
        httpresponse = HttpInstrumentation.execute((HttpClient)defaulthttpclient, httprequestbase);
          goto _L1
        s = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
        return s;
    }

    private static DefaultHttpClient a()
    {
        SchemeRegistry schemeregistry = new SchemeRegistry();
        schemeregistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        X509HostnameVerifier x509hostnameverifier = SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
        SSLSocketFactory sslsocketfactory = SSLSocketFactory.getSocketFactory();
        sslsocketfactory.setHostnameVerifier((X509HostnameVerifier)x509hostnameverifier);
        schemeregistry.register(new Scheme("https", sslsocketfactory, 443));
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setStaleCheckingEnabled(basichttpparams, false);
        HttpConnectionParams.setConnectionTimeout(basichttpparams, 20000);
        HttpConnectionParams.setSoTimeout(basichttpparams, 20000);
        HttpConnectionParams.setSocketBufferSize(basichttpparams, 8192);
        HttpClientParams.setRedirecting(basichttpparams, false);
        return new DefaultHttpClient(new ThreadSafeClientConnManager(basichttpparams, schemeregistry), basichttpparams);
    }

    public final String a(String s, JSONObject jsonobject)
    {
        HttpPost httppost;
        httppost = new HttpPost(s);
        httppost.addHeader("User-Agent", b);
        httppost.addHeader("Content-Type", "application/json");
        if (jsonobject instanceof JSONObject) goto _L2; else goto _L1
_L1:
        String s1 = jsonobject.toString();
_L5:
        StringBuilder stringbuilder;
        httppost.setEntity(new ByteArrayEntity(s1.getBytes("UTF8")));
        stringbuilder = new StringBuilder("JSON Object for request: ");
        if (jsonobject instanceof JSONObject) goto _L4; else goto _L3
_L3:
        String s3 = jsonobject.toString();
_L6:
        stringbuilder.append(s3);
        (new StringBuilder("httpPost Entity is: ")).append(httppost.getEntity().getContent().toString());
        return a(((HttpRequestBase) (httppost)));
_L2:
        String s2;
        try
        {
            s1 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error encoding http params");
        }
          goto _L5
_L4:
        s2 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        s3 = s2;
          goto _L6
    }

    public final String a(String s, JSONObject jsonobject, String as[], String s1)
    {
        int i;
        String s2;
        HttpPost httppost;
        i = 0;
        new String();
        byte abyte0[] = new byte[30];
        (new Random()).nextBytes(abyte0);
        s2 = g.a(abyte0);
        (new StringBuilder("boundary = ")).append(s2);
        httppost = new HttpPost(s);
        httppost.addHeader("User-Agent", b);
        httppost.addHeader("Content-Type", (new StringBuilder("multipart/form-data; boundary=")).append(s2).append("; charset=\"utf-8\"").toString());
        httppost.addHeader("Accept", "text/plain, application/json");
        httppost.addHeader("Content-Disposition", "form-data");
        ByteArrayOutputStream bytearrayoutputstream;
        StringBuilder stringbuilder;
        StringBuilder stringbuilder1;
        bytearrayoutputstream = new ByteArrayOutputStream();
        stringbuilder = new StringBuilder();
        stringbuilder.append((new StringBuilder("--")).append(s2).append("\n").toString());
        stringbuilder.append((new StringBuilder("Content-Disposition: form-data; name=\"")).append(s1).append("json\";\n").toString());
        stringbuilder.append("Content-type: application/json\n\n");
        stringbuilder1 = new StringBuilder();
        if (jsonobject instanceof JSONObject) goto _L2; else goto _L1
_L1:
        String s3 = jsonobject.toString();
_L6:
        stringbuilder.append(stringbuilder1.append(s3).append("\n").toString());
        bytearrayoutputstream.write(stringbuilder.toString().getBytes("UTF-8"));
_L7:
        FileInputStream fileinputstream;
        byte abyte1[];
        if (i >= as.length)
        {
            break MISSING_BLOCK_LABEL_522;
        }
        File file = new File(as[i]);
        StringBuilder stringbuilder2 = new StringBuilder();
        stringbuilder2.append((new StringBuilder("--")).append(s2).append("\n").toString());
        stringbuilder2.append((new StringBuilder("Content-Disposition: form-data; name=\"")).append(s1).append(Integer.toString(i)).append("\"; filename=\"").append(file.getName()).append("\";\n").toString());
        stringbuilder2.append("Content-type: application/octet-stream\n\n");
        bytearrayoutputstream.write(stringbuilder2.toString().getBytes("UTF-8"));
        fileinputstream = new FileInputStream(file);
        abyte1 = new byte[8192];
_L5:
        int j = fileinputstream.read(abyte1);
        if (j < 0) goto _L4; else goto _L3
_L3:
        Exception exception;
        bytearrayoutputstream.write(abyte1, 0, j);
          goto _L5
        try
        {
            StringBuilder stringbuilder4 = new StringBuilder();
            stringbuilder4.append((new StringBuilder("--")).append(s2).append("--").toString());
            bytearrayoutputstream.write(stringbuilder4.toString().getBytes("UTF-8"));
            httppost.setEntity(new ByteArrayEntity(bytearrayoutputstream.toByteArray()));
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception) { }
        return a(((HttpRequestBase) (httppost)));
_L2:
        s3 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
          goto _L6
_L4:
        stringbuilder3 = new StringBuilder();
        stringbuilder3.append("\n");
        bytearrayoutputstream.write(stringbuilder3.toString().getBytes("UTF-8"));
        fileinputstream.close();
        bytearrayoutputstream.close();
        i++;
          goto _L7
    }
}
