// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;

// Referenced classes of package com.google.android.gms.drive.internal:
//            CloseContentsRequest

public class f
    implements android.os.Parcelable.Creator
{

    public f()
    {
    }

    static void a(CloseContentsRequest closecontentsrequest, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, closecontentsrequest.xM);
        b.a(parcel, 2, closecontentsrequest.Iw, i, false);
        b.a(parcel, 3, closecontentsrequest.Iz, false);
        b.G(parcel, j);
    }

    public CloseContentsRequest V(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        Contents contents = null;
        int j = 0;
        Boolean boolean1 = null;
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
                    contents = (Contents)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, Contents.CREATOR);
                    break;

                case 3: // '\003'
                    boolean1 = com.google.android.gms.common.internal.safeparcel.a.d(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder("Overread allowed size end=")).append(i).toString(), parcel);
            } else
            {
                return new CloseContentsRequest(j, contents, boolean1);
            }
        } while (true);
    }

    public CloseContentsRequest[] aQ(int i)
    {
        return new CloseContentsRequest[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return V(parcel);
    }

    public Object[] newArray(int i)
    {
        return aQ(i);
    }
}
