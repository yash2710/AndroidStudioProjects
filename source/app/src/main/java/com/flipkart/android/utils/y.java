// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import com.flipkart.android.datahandler.SessionVDataHandler;
import com.flipkart.android.response.session.SessionResponse;

final class y extends SessionVDataHandler
{

    y()
    {
    }

    public final void errorReceived(int i, int j, String s)
    {
    }

    public final void resultReceived(SessionResponse sessionresponse, boolean flag)
    {
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((SessionResponse)obj, flag);
    }
}
