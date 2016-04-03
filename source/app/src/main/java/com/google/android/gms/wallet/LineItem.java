// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.wallet:
//            i

public final class LineItem
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new i();
    String aiK;
    String aiL;
    String aje;
    String ajf;
    int ajg;
    String description;
    private final int xM;

    LineItem()
    {
        xM = 1;
        ajg = 0;
    }

    LineItem(int j, String s, String s1, String s2, String s3, int k, String s4)
    {
        xM = j;
        description = s;
        aje = s1;
        ajf = s2;
        aiK = s3;
        ajg = k;
        aiL = s4;
    }

    public static Builder newBuilder()
    {
        LineItem lineitem = new LineItem();
        lineitem.getClass();
        return lineitem. new Builder(null);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final String getCurrencyCode()
    {
        return aiL;
    }

    public final String getDescription()
    {
        return description;
    }

    public final String getQuantity()
    {
        return aje;
    }

    public final int getRole()
    {
        return ajg;
    }

    public final String getTotalPrice()
    {
        return aiK;
    }

    public final String getUnitPrice()
    {
        return ajf;
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final void writeToParcel(Parcel parcel, int j)
    {
        i.a(this, parcel, j);
    }


    private class Builder
    {

        final LineItem ajh;

        public final LineItem build()
        {
            return ajh;
        }

        public final Builder setCurrencyCode(String s)
        {
            ajh.aiL = s;
            return this;
        }

        public final Builder setDescription(String s)
        {
            ajh.description = s;
            return this;
        }

        public final Builder setQuantity(String s)
        {
            ajh.aje = s;
            return this;
        }

        public final Builder setRole(int j)
        {
            ajh.ajg = j;
            return this;
        }

        public final Builder setTotalPrice(String s)
        {
            ajh.aiK = s;
            return this;
        }

        public final Builder setUnitPrice(String s)
        {
            ajh.ajf = s;
            return this;
        }

        private Builder()
        {
            ajh = LineItem.this;
            super();
        }

        Builder(_cls1 _pcls1)
        {
            this();
        }
    }

}
