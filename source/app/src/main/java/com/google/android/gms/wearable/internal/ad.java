// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.IInterface;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.c;

// Referenced classes of package com.google.android.gms.wearable.internal:
//            ab, ao, b

public interface ad
    extends IInterface
{

    public abstract void a(ab ab);

    public abstract void a(ab ab, Uri uri);

    public abstract void a(ab ab, Asset asset);

    public abstract void a(ab ab, PutDataRequest putdatarequest);

    public abstract void a(ab ab, c c1);

    public abstract void a(ab ab, ao ao);

    public abstract void a(ab ab, b b1);

    public abstract void a(ab ab, String s, String s1, byte abyte0[]);

    public abstract void b(ab ab);

    public abstract void b(ab ab, Uri uri);

    public abstract void c(ab ab);

    public abstract void c(ab ab, Uri uri);

    public abstract void d(ab ab);

    public abstract void e(ab ab);

    public abstract void f(ab ab);

    public abstract void g(ab ab);

    public abstract void h(ab ab);
}
