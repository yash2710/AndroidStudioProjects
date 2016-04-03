// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.wallet:
//            b

public final class Cart
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new b();
    String aiK;
    String aiL;
    ArrayList aiM;
    private final int xM;

    Cart()
    {
        xM = 1;
        aiM = new ArrayList();
    }

    Cart(int i, String s, String s1, ArrayList arraylist)
    {
        xM = i;
        aiK = s;
        aiL = s1;
        aiM = arraylist;
    }

    public static Builder newBuilder()
    {
        Cart cart = new Cart();
        cart.getClass();
        return cart. new Builder(null);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final String getCurrencyCode()
    {
        return aiL;
    }

    public final ArrayList getLineItems()
    {
        return aiM;
    }

    public final String getTotalPrice()
    {
        return aiK;
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        b.a(this, parcel, i);
    }


    private class Builder
    {

        final Cart aiN;

        public final Builder addLineItem(LineItem lineitem)
        {
            aiN.aiM.add(lineitem);
            return this;
        }

        public final Cart build()
        {
            return aiN;
        }

        public final Builder setCurrencyCode(String s)
        {
            aiN.aiL = s;
            return this;
        }

        public final Builder setLineItems(List list)
        {
            aiN.aiM.clear();
            aiN.aiM.addAll(list);
            return this;
        }

        public final Builder setTotalPrice(String s)
        {
            aiN.aiK = s;
            return this;
        }

        private Builder()
        {
            aiN = Cart.this;
            super();
        }

        Builder(_cls1 _pcls1)
        {
            this();
        }
    }

}
