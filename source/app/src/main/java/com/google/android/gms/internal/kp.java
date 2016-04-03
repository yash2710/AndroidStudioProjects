// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.google.android.gms.plus.model.moments.Moment;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            hy, kq, kn

public final class kp extends hy
    implements SafeParcelable, Moment
{

    public static final kq CREATOR = new kq();
    private static final HashMap acr;
    private final Set acs;
    private String adf;
    private kn adn;
    private kn ado;
    private String qX;
    private String xG;
    private final int xM;

    public kp()
    {
        xM = 1;
        acs = new HashSet();
    }

    kp(Set set, int i, String s, kn kn1, String s1, kn kn2, String s2)
    {
        acs = set;
        xM = i;
        xG = s;
        adn = kn1;
        adf = s1;
        ado = kn2;
        qX = s2;
    }

    public kp(Set set, String s, kn kn1, String s1, kn kn2, String s2)
    {
        acs = set;
        xM = 1;
        xG = s;
        adn = kn1;
        adf = s1;
        ado = kn2;
        qX = s2;
    }

    protected final boolean a(hy.a a1)
    {
        return acs.contains(Integer.valueOf(a1.fN()));
    }

    protected final Object aF(String s)
    {
        return null;
    }

    protected final boolean aG(String s)
    {
        return false;
    }

    protected final Object b(hy.a a1)
    {
        switch (a1.fN())
        {
        case 3: // '\003'
        default:
            throw new IllegalStateException((new StringBuilder("Unknown safe parcelable id=")).append(a1.fN()).toString());

        case 2: // '\002'
            return xG;

        case 4: // '\004'
            return adn;

        case 5: // '\005'
            return adf;

        case 6: // '\006'
            return ado;

        case 7: // '\007'
            return qX;
        }
    }

    public final int describeContents()
    {
        kq _tmp = CREATOR;
        return 0;
    }

    public final boolean equals(Object obj)
    {
label0:
        {
            if (!(obj instanceof kp))
            {
                return false;
            }
            if (this == obj)
            {
                return true;
            }
            kp kp1 = (kp)obj;
            hy.a a1;
label1:
            do
            {
                for (Iterator iterator = acr.values().iterator(); iterator.hasNext();)
                {
                    a1 = (hy.a)iterator.next();
                    if (!a(a1))
                    {
                        continue label1;
                    }
                    if (kp1.a(a1))
                    {
                        if (!b(a1).equals(kp1.b(a1)))
                        {
                            return false;
                        }
                    } else
                    {
                        return false;
                    }
                }

                break label0;
            } while (!kp1.a(a1));
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
        return kD();
    }

    public final String getId()
    {
        return xG;
    }

    public final ItemScope getResult()
    {
        return adn;
    }

    public final String getStartDate()
    {
        return adf;
    }

    public final ItemScope getTarget()
    {
        return ado;
    }

    public final String getType()
    {
        return qX;
    }

    final int getVersionCode()
    {
        return xM;
    }

    public final boolean hasId()
    {
        return acs.contains(Integer.valueOf(2));
    }

    public final boolean hasResult()
    {
        return acs.contains(Integer.valueOf(4));
    }

    public final boolean hasStartDate()
    {
        return acs.contains(Integer.valueOf(5));
    }

    public final boolean hasTarget()
    {
        return acs.contains(Integer.valueOf(6));
    }

    public final boolean hasType()
    {
        return acs.contains(Integer.valueOf(7));
    }

    public final int hashCode()
    {
        Iterator iterator = acr.values().iterator();
        int i = 0;
        while (iterator.hasNext()) 
        {
            hy.a a1 = (hy.a)iterator.next();
            int j;
            if (a(a1))
            {
                j = i + a1.fN() + b(a1).hashCode();
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

    final kn kB()
    {
        return adn;
    }

    final kn kC()
    {
        return ado;
    }

    public final kp kD()
    {
        return this;
    }

    final Set kk()
    {
        return acs;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        kq _tmp = CREATOR;
        kq.a(this, parcel, i);
    }

    static 
    {
        HashMap hashmap = new HashMap();
        acr = hashmap;
        hashmap.put("id", hy.a.j("id", 2));
        acr.put("result", hy.a.a("result", 4, com/google/android/gms/internal/kn));
        acr.put("startDate", hy.a.j("startDate", 5));
        acr.put("target", hy.a.a("target", 6, com/google/android/gms/internal/kn));
        acr.put("type", hy.a.j("type", 7));
    }
}
