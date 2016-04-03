// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.flipkart.android.customviews:
//            EnhancedListView

final class l extends Handler
{

    private EnhancedListView a;

    private l(EnhancedListView enhancedlistview)
    {
        a = enhancedlistview;
        super();
    }

    l(EnhancedListView enhancedlistview, byte byte0)
    {
        this(enhancedlistview);
    }

    public final void handleMessage(Message message)
    {
        if (message.what == EnhancedListView.g(a))
        {
            a.discardUndo();
        }
    }
}
