// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.fk_android_batchnetworking:
//            Group, Data, BatchNetworking, a, 
//            GroupPriorityQueue

final class c extends Handler
{

    private Group a;

    c(Group group, Looper looper)
    {
        a = group;
        super(looper);
    }

    public final void handleMessage(Message message)
    {
        switch (message.what)
        {
        default:
            return;

        case 2: // '\002'
            if (a.b != null)
            {
                int i = 0;
                while (i < a.b.size()) 
                {
                    Data data = (Data)a.b.get(i);
                    Group.a(a).remove(data);
                    if (data.getCacheState() == Data.DataCacheState.CSTATE_CACHED)
                    {
                        try
                        {
                            BatchNetworking.getDefaultInstance().a().removeData(data.getEventId());
                        }
                        catch (Exception exception)
                        {
                            FkLogger.printStackTrace(exception);
                        }
                    }
                    i++;
                }
                Group.a(a, 0);
                a.b.clear();
            }
            BatchNetworking.getDefaultInstance().getGroupPriorityQueue().getNotificationHandler().sendEmptyMessage(1);
            return;

        case 3: // '\003'
            Group.a(a, 0);
            break;
        }
        if (a.b != null)
        {
            a.b.clear();
        }
        BatchNetworking.getDefaultInstance().getGroupPriorityQueue().getNotificationHandler().sendEmptyMessage(-1);
    }
}
