// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.mediameasurement;

import android.util.Log;

// Referenced classes of package com.adobe.adms.mediameasurement:
//            ADMS_MediaItem, ADMS_MediaMeasurement

final class a extends Thread
{

    public boolean a;
    public ADMS_MediaItem b;
    private long c;

    private a()
    {
        c = 1000L;
        a = false;
    }

    a(byte byte0)
    {
        this();
    }

    public final void run()
    {
        try
        {
            while (!a) 
            {
                if (b.lastEventType == 1)
                {
                    b.m.playerEvent(b.name, 3, -1D, 0, null, -1D, null);
                }
                Thread.sleep(c);
            }
        }
        catch (InterruptedException interruptedexception)
        {
            Log.d("ADMS_MediaMeasurement", (new StringBuilder("AppMeasurement Error : Background Thread Interrupted : ")).append(interruptedexception.getMessage()).toString());
        }
    }
}
