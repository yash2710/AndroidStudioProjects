// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.PlatformServiceClient;

final class  extends PlatformServiceClient
{

    protected final void populateRequestBundle(Bundle bundle)
    {
        bundle.putString("com.facebook.platform.extra.INSTALLDATA_PACKAGE", getContext().getPackageName());
    }

    (Context context, String s)
    {
        super(context, 0x10004, 0x10005, 0x1332b3a, s);
    }
}
