// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.graphics.Bitmap;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;

// Referenced classes of package com.android.volley.toolbox:
//            HttpHeaderParser

public class ImageRequest extends Request
{

    private static final Object e = new Object();
    private final com.android.volley.Response.Listener a;
    private final android.graphics.Bitmap.Config b;
    private final int c;
    private final int d;

    public ImageRequest(String s, com.android.volley.Response.Listener listener, int i, int j, android.graphics.Bitmap.Config config, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(0, s, errorlistener);
        setRetryPolicy(new DefaultRetryPolicy(1000, 2, 2.0F));
        a = listener;
        b = config;
        c = i;
        d = j;
    }

    private static int a(int i, int j, int k, int l)
    {
        if (i == 0 && j == 0)
        {
            i = k;
        } else
        {
            if (i == 0)
            {
                return (int)(((double)j / (double)l) * (double)k);
            }
            if (j != 0)
            {
                double d1 = (double)l / (double)k;
                if (d1 * (double)i > (double)j)
                {
                    return (int)((double)j / d1);
                }
            }
        }
        return i;
    }

    private static int b(int i, int j, int k, int l)
    {
        double d1 = Math.min((double)i / (double)k, (double)j / (double)l);
        float f;
        for (f = 1.0F; (double)(f * 2.0F) <= d1; f *= 2.0F) { }
        return (int)f;
    }

    protected void deliverResponse(Bitmap bitmap)
    {
        a.onResponse(bitmap);
    }

    protected volatile void deliverResponse(Object obj)
    {
        deliverResponse((Bitmap)obj);
    }

    public com.android.volley.Request.Priority getPriority()
    {
        return com.android.volley.Request.Priority.LOW;
    }

    protected Response parseNetworkResponse(NetworkResponse networkresponse)
    {
        Object obj = e;
        obj;
        JVM INSTR monitorenter ;
        byte abyte0[];
        android.graphics.BitmapFactory.Options options;
        abyte0 = networkresponse.data;
        options = new android.graphics.BitmapFactory.Options();
        if (c != 0 || d != 0) goto _L2; else goto _L1
_L1:
        Bitmap bitmap1;
        options.inPreferredConfig = b;
        bitmap1 = BitmapFactoryInstrumentation.decodeByteArray(abyte0, 0, abyte0.length, options);
_L5:
        if (bitmap1 != null) goto _L4; else goto _L3
_L3:
        Response response1 = Response.error(new ParseError(networkresponse));
        Response response2 = response1;
_L6:
        obj;
        JVM INSTR monitorexit ;
        return response2;
_L2:
        int k;
        int l;
        Bitmap bitmap;
        options.inJustDecodeBounds = true;
        BitmapFactoryInstrumentation.decodeByteArray(abyte0, 0, abyte0.length, options);
        int i = options.outWidth;
        int j = options.outHeight;
        k = a(c, d, i, j);
        l = a(d, c, j, i);
        options.inJustDecodeBounds = false;
        options.inSampleSize = b(i, j, k, l);
        bitmap = BitmapFactoryInstrumentation.decodeByteArray(abyte0, 0, abyte0.length, options);
        if (bitmap == null)
        {
            break MISSING_BLOCK_LABEL_289;
        }
        if (bitmap.getWidth() <= k && bitmap.getHeight() <= l)
        {
            break MISSING_BLOCK_LABEL_289;
        }
        bitmap1 = Bitmap.createScaledBitmap(bitmap, k, l, true);
        bitmap.recycle();
          goto _L5
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
        Response response;
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(networkresponse.data.length);
        aobj[1] = getUrl();
        VolleyLog.e("Caught OOM for %d byte image, url=%s", aobj);
        response = Response.error(new ParseError(outofmemoryerror));
        obj;
        JVM INSTR monitorexit ;
        return response;
        Exception exception;
        exception;
        throw exception;
        bitmap1 = bitmap;
          goto _L5
_L4:
        Response response3 = Response.success(bitmap1, HttpHeaderParser.parseCacheHeaders(networkresponse));
        response2 = response3;
          goto _L6
    }

}
