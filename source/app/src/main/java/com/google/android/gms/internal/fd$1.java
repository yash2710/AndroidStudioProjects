// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;

// Referenced classes of package com.google.android.gms.internal:
//            fd

final class 
    implements android.os.lable.Creator
{

    public final fd[] C(int i)
    {
        return new fd[i];
    }

    public final Object createFromParcel(Parcel parcel)
    {
        return k(parcel);
    }

    public final fd k(Parcel parcel)
    {
        return new fd(parcel);
    }

    public final Object[] newArray(int i)
    {
        return C(i);
    }

    ()
    {
    }
}
