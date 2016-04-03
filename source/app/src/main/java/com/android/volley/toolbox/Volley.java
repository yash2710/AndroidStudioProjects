// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.http.AndroidHttpClient;
import com.android.volley.RequestQueue;
import java.io.File;

// Referenced classes of package com.android.volley.toolbox:
//            HurlStack, BasicNetwork, DiskBasedCache, HttpClientStack, 
//            HttpStack

public class Volley
{

    public Volley()
    {
    }

    public static RequestQueue newRequestQueue(Context context)
    {
        return newRequestQueue(context, null, -1);
    }

    public static RequestQueue newRequestQueue(Context context, int i)
    {
        return newRequestQueue(context, null, i);
    }

    public static RequestQueue newRequestQueue(Context context, HttpStack httpstack, int i)
    {
        File file;
        String s;
        file = new File(context.getCacheDir(), "volley");
        s = "volley/0";
        String s2;
        String s1 = context.getPackageName();
        PackageInfo packageinfo = context.getPackageManager().getPackageInfo(s1, 0);
        s2 = (new StringBuilder()).append(s1).append("/").append(packageinfo.versionCode).toString();
        s = s2;
_L2:
        BasicNetwork basicnetwork;
        RequestQueue requestqueue;
        if (httpstack == null)
        {
            if (android.os.Build.VERSION.SDK_INT >= 9)
            {
                httpstack = new HurlStack();
            } else
            {
                httpstack = new HttpClientStack(AndroidHttpClient.newInstance(s));
            }
        }
        basicnetwork = new BasicNetwork(httpstack);
        if (i <= 0)
        {
            requestqueue = new RequestQueue(new DiskBasedCache(file), basicnetwork);
        } else
        {
            requestqueue = new RequestQueue(new DiskBasedCache(file), basicnetwork, i);
        }
        requestqueue.start();
        return requestqueue;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        if (true) goto _L2; else goto _L1
_L1:
    }
}
