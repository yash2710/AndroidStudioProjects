// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.services;

import com.flipkart.android.datahandler.LocationVDataHandler;
import com.flipkart.android.response.location.LocationResponse;

// Referenced classes of package com.flipkart.android.services:
//            LocationService

final class a extends LocationVDataHandler
{

    a(LocationService locationservice)
    {
    }

    public final void resultReceived(LocationResponse locationresponse, boolean flag)
    {
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((LocationResponse)obj, flag);
    }
}
