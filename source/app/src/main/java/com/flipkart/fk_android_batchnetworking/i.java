// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

// Referenced classes of package com.flipkart.fk_android_batchnetworking:
//            GroupPriorityQueue

final class i extends Handler
{

    private GroupPriorityQueue a;

    i(GroupPriorityQueue grouppriorityqueue, Looper looper)
    {
        a = grouppriorityqueue;
        super(looper);
    }

    public final void handleMessage(Message message)
    {
        switch (message.what)
        {
        default:
            return;

        case 0: // '\0'
        case 1: // '\001'
            removeMessages(0);
            break;
        }
        GroupPriorityQueue.a(a);
    }
}
