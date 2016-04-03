// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.internal:
//            fg, fk

public class fh
    implements android.os.Parcelable.Creator
{

    public fh()
    {
    }

    static void a(fg fg1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.a(parcel, 1, fg1.xN, i, false);
        b.c(parcel, 1000, fg1.xM);
        b.a(parcel, 2, fg1.xO, false);
        b.a(parcel, 3, fg1.xP);
        b.G(parcel, j);
    }

    public fg[] D(int i)
    {
        return new fg[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return l(parcel);
    }

    public fg l(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        String s = null;
        fk afk[] = null;
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
                    afk = (fk[])com.google.android.gms.common.internal.safeparcel.a.b(parcel, k, fk.CREATOR);
                    break;

                case 1000: 
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
                    break;

                case 2: // '\002'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
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
                return new fg(j, afk, s, flag);
            }
        } while (true);
    }

    public Object[] newArray(int i)
    {
        return D(i);
    }
}
