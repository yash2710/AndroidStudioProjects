// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.drive.internal:
//            OnListEntriesResponse

public class ak
    implements android.os.Parcelable.Creator
{

    public ak()
    {
    }

    static void a(OnListEntriesResponse onlistentriesresponse, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, onlistentriesresponse.xM);
        b.a(parcel, 2, onlistentriesresponse.JA, i, false);
        b.a(parcel, 3, onlistentriesresponse.IP);
        b.G(parcel, j);
    }

    public OnListEntriesResponse al(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        DataHolder dataholder = null;
        int j = 0;
        boolean flag = false;
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

                case 1: // '\001'
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
                    break;

                case 2: // '\002'
                    dataholder = (DataHolder)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, DataHolder.CREATOR);
                    break;

                case 3: // '\003'
                    flag = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder("Overread allowed size end=")).append(i).toString(), parcel);
            } else
            {
                return new OnListEntriesResponse(j, dataholder, flag);
            }
        } while (true);
    }

    public OnListEntriesResponse[] bh(int i)
    {
        return new OnListEntriesResponse[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return al(parcel);
    }

    public Object[] newArray(int i)
    {
        return bh(i);
    }
}
