// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.internal:
//            jl, jm, hk

public final class jk
    implements SafeParcelable
{

    public static final jl CREATOR = new jl();
    private final int Va;
    private final int Wa;
    private final jm Wb;
    private final int xM;

    jk(int i, int j, int k, jm jm1)
    {
        xM = i;
        Va = j;
        Wa = k;
        Wb = jm1;
    }

    public final int describeContents()
    {
        jl _tmp = CREATOR;
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof jk))
            {
                return false;
            }
            jk jk1 = (jk)obj;
            if (Va != jk1.Va || Wa != jk1.Wa || !Wb.equals(jk1.Wb))
            {
                return false;
            }
        }
        return true;
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final int hashCode()
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(Va);
        aobj[1] = Integer.valueOf(Wa);
        return hk.hashCode(aobj);
    }

    public final int jc()
    {
        return Va;
    }

    public final int je()
    {
        return Wa;
    }

    public final jm jf()
    {
        return Wb;
    }

    public final String toString()
    {
        return hk.e(this).a("transitionTypes", Integer.valueOf(Va)).a("loiteringTimeMillis", Integer.valueOf(Wa)).a("placeFilter", Wb).toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        jl _tmp = CREATOR;
        jl.a(this, parcel, i);
    }

}
