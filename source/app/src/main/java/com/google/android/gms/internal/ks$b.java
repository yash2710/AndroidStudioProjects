// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            hy, kv, hv

public final class adN extends hy
    implements SafeParcelable, com.google.android.gms.plus.model.people.n.Cover
{

    public static final kv CREATOR = new kv();
    private static final HashMap acr;
    private final Set acs;
    private a adL;
    private b adM;
    private int adN;
    private final int xM;

    protected final boolean a(ple.n.Cover cover)
    {
        return acs.contains(Integer.valueOf(cover.fN()));
    }

    protected final Object aF(String s)
    {
        return null;
    }

    protected final boolean aG(String s)
    {
        return false;
    }

    protected final Object b(fN fn)
    {
        switch (fn.fN())
        {
        default:
            throw new IllegalStateException((new StringBuilder("Unknown safe parcelable id=")).append(fn.fN()).toString());

        case 2: // '\002'
            return adL;

        case 3: // '\003'
            return adM;

        case 4: // '\004'
            return Integer.valueOf(adN);
        }
    }

    public final int describeContents()
    {
        kv _tmp = CREATOR;
        return 0;
    }

    public final boolean equals(Object obj)
    {
label0:
        {
            if (!(obj instanceof CREATOR))
            {
                return false;
            }
            if (this == obj)
            {
                return true;
            }
            CREATOR creator = (CREATOR)obj;
            CREATOR creator1;
label1:
            do
            {
                for (Iterator iterator = acr.values().iterator(); iterator.hasNext();)
                {
                    creator1 = (acr)iterator.next();
                    if (!a(creator1))
                    {
                        continue label1;
                    }
                    if (creator.a(creator1))
                    {
                        if (!b(creator1).equals(creator.b(creator1)))
                        {
                            return false;
                        }
                    } else
                    {
                        return false;
                    }
                }

                break label0;
            } while (!creator.a(creator1));
            return false;
        }
        return true;
    }

    public final HashMap fG()
    {
        return acr;
    }

    public final Object freeze()
    {
        return kQ();
    }

    public final com.google.android.gms.plus.model.people.n.Cover.CoverInfo getCoverInfo()
    {
        return adL;
    }

    public final com.google.android.gms.plus.model.people.n.Cover.CoverPhoto getCoverPhoto()
    {
        return adM;
    }

    public final int getLayout()
    {
        return adN;
    }

    final int getVersionCode()
    {
        return xM;
    }

    public final boolean hasCoverInfo()
    {
        return acs.contains(Integer.valueOf(2));
    }

    public final boolean hasCoverPhoto()
    {
        return acs.contains(Integer.valueOf(3));
    }

    public final boolean hasLayout()
    {
        return acs.contains(Integer.valueOf(4));
    }

    public final int hashCode()
    {
        Iterator iterator = acr.values().iterator();
        int i = 0;
        while (iterator.hasNext()) 
        {
            acs acs1 = (acr)iterator.next();
            int j;
            if (a(acs1))
            {
                j = i + acs1.fN() + b(acs1).hashCode();
            } else
            {
                j = i;
            }
            i = j;
        }
        return i;
    }

    public final boolean isDataValid()
    {
        return true;
    }

    final a kO()
    {
        return adL;
    }

    final b kP()
    {
        return adM;
    }

    public final adM kQ()
    {
        return this;
    }

    final Set kk()
    {
        return acs;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        kv _tmp = CREATOR;
        kv.a(this, parcel, i);
    }

    static 
    {
        HashMap hashmap = new HashMap();
        acr = hashmap;
        hashmap.put("coverInfo", a("coverInfo", 2, com/google/android/gms/internal/ks$b$a));
        acr.put("coverPhoto", a("coverPhoto", 3, com/google/android/gms/internal/ks$b$b));
        acr.put("layout", a("layout", 4, (new hv()).f("banner", 0), false));
    }

    public b()
    {
        xM = 1;
        acs = new HashSet();
    }

    b(Set set, int i, a a1, b b1, int j)
    {
        acs = set;
        xM = i;
        adL = a1;
        adM = b1;
        adN = j;
    }
}
