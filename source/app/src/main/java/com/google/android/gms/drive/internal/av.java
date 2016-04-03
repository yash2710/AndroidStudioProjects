// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

// Referenced classes of package com.google.android.gms.drive.internal:
//            SetResourceParentsRequest

public class av
    implements android.os.Parcelable.Creator
{

    public av()
    {
    }

    static void a(SetResourceParentsRequest setresourceparentsrequest, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, setresourceparentsrequest.xM);
        b.a(parcel, 2, setresourceparentsrequest.JF, i, false);
        b.b(parcel, 3, setresourceparentsrequest.JG, false);
        b.G(parcel, j);
    }

    public SetResourceParentsRequest aw(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        DriveId driveid = null;
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

                case 1: // '\001'
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
                    break;

                case 2: // '\002'
                    driveid = (DriveId)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, DriveId.CREATOR);
                    break;

                case 3: // '\003'
                    arraylist = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k, DriveId.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder("Overread allowed size end=")).append(i).toString(), parcel);
            } else
            {
                return new SetResourceParentsRequest(j, driveid, arraylist);
            }
        } while (true);
    }

    public SetResourceParentsRequest[] bs(int i)
    {
        return new SetResourceParentsRequest[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return aw(parcel);
    }

    public Object[] newArray(int i)
    {
        return bs(i);
    }
}
