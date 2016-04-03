// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.wearable.internal:
//            m

public class n
    implements android.os.Parcelable.Creator
{

    public n()
    {
    }

    static void a(m m1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, m1.xM);
        b.a(parcel, 2, m1.getUri(), i, false);
        b.a(parcel, 4, m1.nm(), false);
        b.a(parcel, 5, m1.getData(), false);
        b.G(parcel, j);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return cx(parcel);
    }

    public m cx(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        android.os.Bundle bundle = null;
        Uri uri = null;
        int j = 0;
        byte abyte0[] = null;
        do
        {
            if (parcel.dataPosition() < i)
            {
                int k = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(k))
                {
                case 3: // '\003'
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
                    break;

                case 1: // '\001'
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
                    break;

                case 2: // '\002'
                    uri = (Uri)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, Uri.CREATOR);
                    break;

                case 4: // '\004'
                    bundle = com.google.android.gms.common.internal.safeparcel.a.q(parcel, k);
                    break;

                case 5: // '\005'
                    abyte0 = com.google.android.gms.common.internal.safeparcel.a.r(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder("Overread allowed size end=")).append(i).toString(), parcel);
            } else
            {
                return new m(j, uri, bundle, abyte0);
            }
        } while (true);
    }

    public m[] eg(int i)
    {
        return new m[i];
    }

    public Object[] newArray(int i)
    {
        return eg(i);
    }
}
