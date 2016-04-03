// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.FileConflictEvent;

// Referenced classes of package com.google.android.gms.drive.internal:
//            OnEventResponse

public class aj
    implements android.os.Parcelable.Creator
{

    public aj()
    {
    }

    static void a(OnEventResponse oneventresponse, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, oneventresponse.xM);
        b.c(parcel, 2, oneventresponse.Iq);
        b.a(parcel, 3, oneventresponse.Jy, i, false);
        b.a(parcel, 4, oneventresponse.Jz, i, false);
        b.G(parcel, j);
    }

    public OnEventResponse ak(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        ChangeEvent changeevent = null;
        int j = 0;
        int k = 0;
        FileConflictEvent fileconflictevent = null;
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
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
                    break;

                case 3: // '\003'
                    changeevent = (ChangeEvent)com.google.android.gms.common.internal.safeparcel.a.a(parcel, l, ChangeEvent.CREATOR);
                    break;

                case 4: // '\004'
                    fileconflictevent = (FileConflictEvent)com.google.android.gms.common.internal.safeparcel.a.a(parcel, l, FileConflictEvent.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder("Overread allowed size end=")).append(i).toString(), parcel);
            } else
            {
                return new OnEventResponse(k, j, changeevent, fileconflictevent);
            }
        } while (true);
    }

    public OnEventResponse[] bg(int i)
    {
        return new OnEventResponse[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return ak(parcel);
    }

    public Object[] newArray(int i)
    {
        return bg(i);
    }
}
