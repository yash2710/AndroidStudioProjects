// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.app.PendingIntent;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

// Referenced classes of package com.google.android.gms.drive.internal:
//            AddEventListenerRequest

public class a
    implements android.os.Parcelable.Creator
{

    public a()
    {
    }

    static void a(AddEventListenerRequest addeventlistenerrequest, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, addeventlistenerrequest.xM);
        b.a(parcel, 2, addeventlistenerrequest.Hz, i, false);
        b.c(parcel, 3, addeventlistenerrequest.Iq);
        b.a(parcel, 4, addeventlistenerrequest.Ir, i, false);
        b.G(parcel, j);
    }

    public AddEventListenerRequest R(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        DriveId driveid = null;
        int k = 0;
        PendingIntent pendingintent = null;
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
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
                    break;

                case 2: // '\002'
                    driveid = (DriveId)com.google.android.gms.common.internal.safeparcel.a.a(parcel, l, DriveId.CREATOR);
                    break;

                case 3: // '\003'
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
                    break;

                case 4: // '\004'
                    pendingintent = (PendingIntent)com.google.android.gms.common.internal.safeparcel.a.a(parcel, l, PendingIntent.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a((new StringBuilder("Overread allowed size end=")).append(i).toString(), parcel);
            } else
            {
                return new AddEventListenerRequest(k, driveid, j, pendingintent);
            }
        } while (true);
    }

    public AddEventListenerRequest[] aM(int i)
    {
        return new AddEventListenerRequest[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return R(parcel);
    }

    public Object[] newArray(int i)
    {
        return aM(i);
    }
}
