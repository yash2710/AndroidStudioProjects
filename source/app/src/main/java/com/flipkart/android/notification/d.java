// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.RequestFuture;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.notification.data.NotificationDataPacket;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.StringUtils;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.flipkart.android.notification:
//            e, FlipkartNotificationManager

final class d extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private android.support.v4.app.NotificationCompat.Builder a;
    private NotificationDataPacket b;

    d(android.support.v4.app.NotificationCompat.Builder builder, NotificationDataPacket notificationdatapacket)
    {
        a = builder;
        b = notificationdatapacket;
    }

    private static e a(e e1)
    {
        if (e1 == null) goto _L2; else goto _L1
_L1:
        String s = e1.getUrl();
        if (StringUtils.isNullOrEmpty(s)) goto _L2; else goto _L3
_L3:
        RequestFuture requestfuture;
        requestfuture = RequestFuture.newFuture();
        ImageRequest imagerequest = new ImageRequest(s, requestfuture, 0, 0, null, requestfuture);
        FlipkartApplication.getRequestQueue().add(imagerequest);
        long l;
        if (AppConfigUtils.getInstance().getPNImageDownloadTimeOut() == null)
        {
            break MISSING_BLOCK_LABEL_80;
        }
        l = AppConfigUtils.getInstance().getPNImageDownloadTimeOut().longValue();
_L4:
        e1.setBitmap((Bitmap)requestfuture.get(l, TimeUnit.MILLISECONDS));
_L2:
        return e1;
        l = 15000L;
          goto _L4
        Exception exception;
        exception;
        exception.printStackTrace();
        return e1;
    }

    public void _nr_setTrace(Trace trace)
    {
        try
        {
            _nr_trace = trace;
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    protected final transient e doInBackground(e ae[])
    {
        return a(ae[0]);
    }

    protected final volatile Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "d#doInBackground", null);
_L1:
        e e1 = doInBackground((e[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return e1;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "d#doInBackground", null);
          goto _L1
    }

    protected final void onPostExecute(e e1)
    {
        if (e1 == null || e1.getBitmap() == null || a == null || b == null) goto _L2; else goto _L1
_L1:
        if (!e1.getTag().contains("icon")) goto _L4; else goto _L3
_L3:
        a.setLargeIcon(e1.getBitmap());
_L6:
        ((NotificationManager)FlipkartNotificationManager.a().getSystemService("notification")).notify((new StringBuilder()).append(b.getNotificationId()).toString(), 1, a.build());
_L2:
        return;
_L4:
        if (e1.getTag().contains("bigImage"))
        {
            android.support.v4.app.NotificationCompat.BigPictureStyle bigpicturestyle = new android.support.v4.app.NotificationCompat.BigPictureStyle();
            bigpicturestyle.setBigContentTitle(b.getTitle());
            bigpicturestyle.setSummaryText(b.getMessageExtra());
            bigpicturestyle.bigPicture(e1.getBitmap());
            a.setStyle(bigpicturestyle);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected final volatile void onPostExecute(Object obj)
    {
        TraceMachine.enterMethod(_nr_trace, "d#onPostExecute", null);
_L1:
        onPostExecute((e)obj);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "d#onPostExecute", null);
          goto _L1
    }
}
