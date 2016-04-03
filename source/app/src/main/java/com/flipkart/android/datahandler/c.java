// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;


// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

final class c
    implements com.android.volley.Response.Listener
{

    private BaseVDataHandler a;

    c(BaseVDataHandler basevdatahandler)
    {
        a = basevdatahandler;
        super();
    }

    public final void onResponse(Object obj)
    {
        a.resultReceived(obj, false);
    }
}
