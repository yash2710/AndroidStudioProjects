// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

// Referenced classes of package com.google.android.gms.drive.internal:
//            OpenContentsRequest

public class ar
    implements android.os.Parcelable.Creator
{

    public ar()
    {
    }

    static void a(OpenContentsRequest opencontentsrequest, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, opencontentsrequest.xM);
        b.a(parcel, 2, opencontentsrequest.Iu, i, false);
        b.c(parcel, 3, opencontentsrequest.Hy);
        b.G(parcel, j);
    }

    public OpenContentsRequest as(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        DriveId driveid = null;
        int j = 0;
        int k = 0;
        do
        {
            if (parcel.dataPosition() < i)
            {
                int l = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(l))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, l);
                    break;

                case 1: // '\001'
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
                    break;

                case 2: // '\002'
                    driveid = (DriveId)com.google.android.gms.common.internal.safeparcel.a.a(parcel, l, DriveId.CREATOR);
                    break;

                case 3: // '\003'
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder("Overread allowed size end=")).append(i).toString(), parcel);
            } else
            {
                return new OpenContentsRequest(j, driveid, k);
            }
        } while (true);
    }

    public OpenContentsRequest[] bo(int i)
    {
        return new OpenContentsRequest[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return as(parcel);
    }

    public Object[] newArray(int i)
    {
        return bo(i);
    }
}
