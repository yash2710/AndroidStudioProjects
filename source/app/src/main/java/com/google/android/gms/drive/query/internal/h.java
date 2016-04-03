// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.drive.query.internal:
//            LogicalFilter, Operator, FilterHolder

public class h
    implements android.os.Parcelable.Creator
{

    public h()
    {
    }

    static void a(LogicalFilter logicalfilter, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1000, logicalfilter.xM);
        b.a(parcel, 1, logicalfilter.KL, i, false);
        b.b(parcel, 2, logicalfilter.KY, false);
        b.G(parcel, j);
    }

    public LogicalFilter aL(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        Operator operator = null;
        int j = 0;
        java.util.ArrayList arraylist = null;
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
                    operator = (Operator)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, Operator.CREATOR);
                    break;

                case 2: // '\002'
                    arraylist = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k, FilterHolder.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder("Overread allowed size end=")).append(i).toString(), parcel);
            } else
            {
                return new LogicalFilter(j, operator, arraylist);
            }
        } while (true);
    }

    public LogicalFilter[] bH(int i)
    {
        return new LogicalFilter[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return aL(parcel);
    }

    public Object[] newArray(int i)
    {
        return bH(i);
    }
}
