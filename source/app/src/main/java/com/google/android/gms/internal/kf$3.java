// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;

// Referenced classes of package com.google.android.gms.internal:
//            kf, ke

class <init> extends <init>
{

    final Uri abk;
    final kf abm;

    protected void a(Context context, ke ke)
    {
        kf.b(context, ke, new <init>(this), abk, null);
    }

    (kf kf1, Uri uri)
    {
        abm = kf1;
        abk = uri;
        super(null);
    }
}
