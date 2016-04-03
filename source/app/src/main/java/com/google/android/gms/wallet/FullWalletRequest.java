// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.wallet:
//            g, Cart

public final class FullWalletRequest
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new g();
    String aiQ;
    String aiR;
    Cart aja;
    private final int xM;

    FullWalletRequest()
    {
        xM = 1;
    }

    FullWalletRequest(int i, String s, String s1, Cart cart)
    {
        xM = i;
        aiQ = s;
        aiR = s1;
        aja = cart;
    }

    public static Builder newBuilder()
    {
        FullWalletRequest fullwalletrequest = new FullWalletRequest();
        fullwalletrequest.getClass();
        return fullwalletrequest. new Builder(null);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final Cart getCart()
    {
        return aja;
    }

    public final String getGoogleTransactionId()
    {
        return aiQ;
    }

    public final String getMerchantTransactionId()
    {
        return aiR;
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        g.a(this, parcel, i);
    }


    private class Builder
    {

        final FullWalletRequest ajb;

        public final FullWalletRequest build()
        {
            return ajb;
        }

        public final Builder setCart(Cart cart)
        {
            ajb.aja = cart;
            return this;
        }

        public final Builder setGoogleTransactionId(String s)
        {
            ajb.aiQ = s;
            return this;
        }

        public final Builder setMerchantTransactionId(String s)
        {
            ajb.aiR = s;
            return this;
        }

        private Builder()
        {
            ajb = FullWalletRequest.this;
            super();
        }

        Builder(_cls1 _pcls1)
        {
            this();
        }
    }

}
