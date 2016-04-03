// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.query.internal.LogicalFilter;

// Referenced classes of package com.google.android.gms.drive.query:
//            Query, SortOrder

public class a
    implements android.os.Parcelable.Creator
{

    public a()
    {
    }

    static void a(Query query, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1000, query.xM);
        b.a(parcel, 1, query.KE, i, false);
        b.a(parcel, 3, query.KF, false);
        b.a(parcel, 4, query.KG, i, false);
        b.G(parcel, j);
    }

    public Query aD(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        String s = null;
        LogicalFilter logicalfilter = null;
        int j = 0;
        SortOrder sortorder = null;
        do
        {
            if (parcel.dataPosition() < i)
            {
                int k = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(k))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
                    break;

                case 1000: 
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
                    break;

                case 1: // '\001'
                    logicalfilter = (LogicalFilter)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, LogicalFilter.CREATOR);
                    break;

                case 3: // '\003'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 4: // '\004'
                    sortorder = (SortOrder)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, SortOrder.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a((new StringBuilder("Overread allowed size end=")).append(i).toString(), parcel);
            } else
            {
                return new Query(j, logicalfilter, s, sortorder);
            }
        } while (true);
    }

    public Query[] bz(int i)
    {
        return new Query[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return aD(parcel);
    }

    public Object[] newArray(int i)
    {
        return bz(i);
    }
}
