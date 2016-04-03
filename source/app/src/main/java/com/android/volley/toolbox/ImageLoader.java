// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.util.HashMap;

// Referenced classes of package com.android.volley.toolbox:
//            i, k, j, e, 
//            f

public class ImageLoader
{

    private final RequestQueue a;
    private final Context b;
    private int c;
    private final ImageCache d;
    private final ImageCache e;
    private final HashMap f;
    private final HashMap g;
    private final Handler h;
    private Runnable i;

    public ImageLoader(RequestQueue requestqueue, ImageCache imagecache, Context context)
    {
        c = 100;
        f = new HashMap();
        g = new HashMap();
        h = new Handler(Looper.getMainLooper());
        a = requestqueue;
        d = imagecache;
        e = null;
        b = context;
    }

    public ImageLoader(RequestQueue requestqueue, ImageCache imagecache, ImageCache imagecache1, Context context)
    {
        c = 100;
        f = new HashMap();
        g = new HashMap();
        h = new Handler(Looper.getMainLooper());
        a = requestqueue;
        d = imagecache;
        e = imagecache1;
        b = context;
    }

    static Runnable a(ImageLoader imageloader, Runnable runnable)
    {
        imageloader.i = null;
        return null;
    }

    private static String a(String s, int l, int i1)
    {
        return (new StringBuilder(12 + s.length())).append("#W").append(l).append("#H").append(i1).append(s).toString();
    }

    static HashMap a(ImageLoader imageloader)
    {
        return imageloader.f;
    }

    private static void a()
    {
        if (Looper.myLooper() != Looper.getMainLooper())
        {
            throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
        } else
        {
            return;
        }
    }

    static void a(ImageLoader imageloader, String s, Bitmap bitmap)
    {
        imageloader.d.putBitmap(s, bitmap);
        i l = new i(imageloader, s, bitmap);
        Void avoid[] = new Void[0];
        k k1;
        if (!(l instanceof AsyncTask))
        {
            l.execute(avoid);
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)l, avoid);
        }
        k1 = (k)imageloader.f.remove(s);
        if (k1 != null)
        {
            k.a(k1, bitmap);
            imageloader.a(s, k1);
        }
    }

    static void a(ImageLoader imageloader, String s, VolleyError volleyerror)
    {
        k k1 = (k)imageloader.f.remove(s);
        if (k1 != null && volleyerror != null)
        {
            k1.setError(volleyerror);
            imageloader.a(s, k1);
        }
    }

    private void a(String s, k k1)
    {
        g.put(s, k1);
        if (i == null)
        {
            i = new j(this);
            h.postDelayed(i, c);
        }
    }

    static RequestQueue b(ImageLoader imageloader)
    {
        return imageloader.a;
    }

    static ImageCache c(ImageLoader imageloader)
    {
        return imageloader.e;
    }

    static HashMap d(ImageLoader imageloader)
    {
        return imageloader.g;
    }

    public static ImageListener getImageListener(ImageView imageview, int l, int i1)
    {
        return new e(i1, imageview, l);
    }

    public ImageContainer get(String s, ImageListener imagelistener)
    {
        return get(s, imagelistener, 0, 0);
    }

    public ImageContainer get(String s, ImageListener imagelistener, int l, int i1)
    {
        a();
        String s1 = a(s, l, i1);
        Bitmap bitmap = d.getBitmap(s1);
        if (bitmap != null)
        {
            ImageContainer imagecontainer = new ImageContainer(bitmap, s, null, null);
            imagelistener.onResponse(imagecontainer, true);
            return imagecontainer;
        }
        ImageContainer imagecontainer1 = new ImageContainer(null, s, s1, imagelistener);
        f f1 = new f(this, imagecontainer1, imagelistener, s1, s, l, i1);
        String as[] = {
            s1
        };
        if (!(f1 instanceof AsyncTask))
        {
            f1.execute(as);
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)f1, as);
        }
        imagelistener.onResponse(imagecontainer1, true);
        return imagecontainer1;
    }

    public boolean isCached(String s, int l, int i1)
    {
        a();
        String s1 = a(s, l, i1);
        return d.getBitmap(s1) != null;
    }

    protected void sendErrorBroadcast(String s)
    {
        Intent intent = new Intent();
        intent.putExtra("url", s);
        intent.setAction("com.flipkart.android.rukminierror");
        b.sendBroadcast(intent);
    }

    public void setBatchedResponseDelay(int l)
    {
        c = l;
    }

    private class ImageCache
    {

        public abstract Bitmap getBitmap(String s);

        public abstract void putBitmap(String s, Bitmap bitmap);
    }


    private class ImageContainer
    {

        private Bitmap a;
        private final ImageListener b;
        private final String c;
        private final String d;
        private ImageLoader e;

        static Bitmap a(ImageContainer imagecontainer, Bitmap bitmap)
        {
            imagecontainer.a = bitmap;
            return bitmap;
        }

        static ImageListener a(ImageContainer imagecontainer)
        {
            return imagecontainer.b;
        }

        public void cancelRequest()
        {
            if (b != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            k k1 = (k)ImageLoader.a(e).get(c);
            if (k1 == null)
            {
                break; /* Loop/switch isn't completed */
            }
            if (k1.removeContainerAndCancelIfNecessary(this))
            {
                ImageLoader.a(e).remove(c);
                return;
            }
            if (true) goto _L1; else goto _L3
_L3:
            k k2 = (k)ImageLoader.d(e).get(c);
            if (k2 != null)
            {
                k2.removeContainerAndCancelIfNecessary(this);
                if (k.a(k2).size() == 0)
                {
                    ImageLoader.d(e).remove(c);
                    return;
                }
            }
            if (true) goto _L1; else goto _L4
_L4:
        }

        public Bitmap getBitmap()
        {
            return a;
        }

        public String getRequestUrl()
        {
            return d;
        }

        public ImageContainer(Bitmap bitmap, String s, String s1, ImageListener imagelistener)
        {
            e = ImageLoader.this;
            super();
            a = bitmap;
            d = s;
            c = s1;
            b = imagelistener;
        }
    }


    private class ImageListener
        implements com.android.volley.Response.ErrorListener
    {

        public abstract void onResponse(ImageContainer imagecontainer, boolean flag);
    }

}
