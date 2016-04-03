// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.testandtarget;

import java.util.TimerTask;

// Referenced classes of package com.adobe.adms.testandtarget:
//            Mbox, MboxFactory

final class a extends TimerTask
{

    private Mbox a;

    private a(Mbox mbox)
    {
        a = mbox;
    }

    a(Mbox mbox, byte byte0)
    {
        this(mbox);
    }

    public final void run()
    {
        a.getFactory().disable();
        a.callOnLoadConsumers(null);
    }
}
