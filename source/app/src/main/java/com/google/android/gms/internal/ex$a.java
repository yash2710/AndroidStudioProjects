// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;

class setBaseContext extends MutableContextWrapper
{

    private Context lz;
    private Activity sG;

    public Context cf()
    {
        return sG;
    }

    public void setBaseContext(Context context)
    {
        lz = context.getApplicationContext();
        Activity activity;
        if (context instanceof Activity)
        {
            activity = (Activity)context;
        } else
        {
            activity = null;
        }
        sG = activity;
        super.setBaseContext(lz);
    }

    public void startActivity(Intent intent)
    {
        if (sG != null)
        {
            sG.startActivity(intent);
            return;
        } else
        {
            intent.setFlags(0x10000000);
            lz.startActivity(intent);
            return;
        }
    }

    public (Context context)
    {
        super(context);
        setBaseContext(context);
    }
}
