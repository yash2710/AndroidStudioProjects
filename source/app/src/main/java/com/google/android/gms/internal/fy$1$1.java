// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.gms.internal:
//            fw

class t> extends fw
{

    final s yv;

    public void a(Status status, ParcelFileDescriptor parcelfiledescriptor)
    {
        yu.(new nit>(status, parcelfiledescriptor));
    }

    s(s s, com.google.android.gms.common.api. )
    {
        yv = s;
        super();
    }
}
