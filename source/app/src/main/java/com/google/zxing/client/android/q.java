// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// Referenced classes of package com.google.zxing.client.android:
//            n

final class q extends BroadcastReceiver
{

    private n a;

    private q(n n1)
    {
        a = n1;
        super();
    }

    q(n n1, byte byte0)
    {
        this(n1);
    }

    public final void onReceive(Context context, Intent intent)
    {
label0:
        {
            if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction()))
            {
                boolean flag;
                if (intent.getIntExtra("plugged", -1) <= 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break label0;
                }
                a.a();
            }
            return;
        }
        n.a(a);
    }
}
