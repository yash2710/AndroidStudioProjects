// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

// Referenced classes of package com.google.android.gms.drive.query:
//            b

public class SortOrder
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new b();
    final List KK;
    final int xM;

    SortOrder(int i, List list)
    {
        xM = i;
        KK = list;
    }

    private SortOrder(List list)
    {
        this(1, list);
    }

    SortOrder(List list, _cls1 _pcls1)
    {
        this(list);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        b.a(this, parcel, i);
    }

}
