// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.wearable;

import com.flipkart.logging.FkLogger;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.flipkart.android.wearable:
//            WearableDataSender, b

final class c
    implements ResultCallback
{

    c(b b)
    {
    }

    public final volatile void onResult(Result result)
    {
        onResult((com.google.android.gms.wearable.DataApi.DataItemResult)result);
    }

    public final void onResult(com.google.android.gms.wearable.DataApi.DataItemResult dataitemresult)
    {
        if (!dataitemresult.getStatus().isSuccess())
        {
            FkLogger.error(WearableDataSender.a(), (new StringBuilder("ERROR: failed to putDataItem, status code: ")).append(dataitemresult.getStatus().getStatusCode()).toString());
        }
    }
}
