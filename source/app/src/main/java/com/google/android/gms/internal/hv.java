// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            hw

public final class hv
    implements SafeParcelable, hy.b
{

    public static final hw CREATOR = new hw();
    private final HashMap GW;
    private final HashMap GX;
    private final ArrayList GY;
    private final int xM;

    public hv()
    {
        xM = 1;
        GW = new HashMap();
        GX = new HashMap();
        GY = null;
    }

    hv(int i, ArrayList arraylist)
    {
        xM = i;
        GW = new HashMap();
        GX = new HashMap();
        GY = null;
        a(arraylist);
    }

    private void a(ArrayList arraylist)
    {
        a a1;
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); f(a1.GZ, a1.Ha))
        {
            a1 = (a)iterator.next();
        }

    }

    public final String a(Integer integer)
    {
        String s = (String)GX.get(integer);
        if (s == null && GW.containsKey("gms_unknown"))
        {
            s = "gms_unknown";
        }
        return s;
    }

    public final int describeContents()
    {
        hw _tmp = CREATOR;
        return 0;
    }

    public final hv f(String s, int i)
    {
        GW.put(s, Integer.valueOf(i));
        GX.put(Integer.valueOf(i), s);
        return this;
    }

    final ArrayList fD()
    {
        ArrayList arraylist = new ArrayList();
        String s;
        for (Iterator iterator = GW.keySet().iterator(); iterator.hasNext(); arraylist.add(new a(s, ((Integer)GW.get(s)).intValue())))
        {
            s = (String)iterator.next();
        }

        return arraylist;
    }

    public final int fE()
    {
        return 7;
    }

    public final int fF()
    {
        return 0;
    }

    public final Object g(Object obj)
    {
        return a((Integer)obj);
    }

    final int getVersionCode()
    {
        return xM;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        hw _tmp = CREATOR;
        hw.a(this, parcel, i);
    }


    private class a
        implements SafeParcelable
    {

        public static final hx CREATOR = new hx();
        final String GZ;
        final int Ha;
        final int versionCode;

        public final int describeContents()
        {
            hx _tmp = CREATOR;
            return 0;
        }

        public final void writeToParcel(Parcel parcel, int i)
        {
            hx _tmp = CREATOR;
            hx.a(this, parcel, i);
        }


        a(int i, String s, int j)
        {
            versionCode = i;
            GZ = s;
            Ha = j;
        }

        a(String s, int i)
        {
            versionCode = 1;
            GZ = s;
            Ha = i;
        }
    }

}
