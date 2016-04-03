// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.view.View;

// Referenced classes of package com.google.zxing.client.android:
//            HelpActivity

final class k
    implements android.view.View.OnClickListener
{

    private HelpActivity a;

    k(HelpActivity helpactivity)
    {
        a = helpactivity;
        super();
    }

    public final void onClick(View view)
    {
        a.finish();
    }
}
