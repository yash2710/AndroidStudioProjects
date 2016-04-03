// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.wallet:
//            l, CountrySpecification, Cart

public final class MaskedWalletRequest
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new l();
    String aiL;
    String aiR;
    boolean ajF;
    boolean ajG;
    boolean ajH;
    String ajI;
    String ajJ;
    boolean ajK;
    boolean ajL;
    com.google.android.gms.wallet.CountrySpecification ajM[];
    boolean ajN;
    boolean ajO;
    ArrayList ajP;
    Cart aja;
    private final int xM;

    MaskedWalletRequest()
    {
        xM = 3;
        ajN = true;
        ajO = true;
    }

    MaskedWalletRequest(int i, String s, boolean flag, boolean flag1, boolean flag2, String s1, String s2, 
            String s3, Cart cart, boolean flag3, boolean flag4, CountrySpecification acountryspecification[], boolean flag5, boolean flag6, 
            ArrayList arraylist)
    {
        xM = i;
        aiR = s;
        ajF = flag;
        ajG = flag1;
        ajH = flag2;
        ajI = s1;
        aiL = s2;
        ajJ = s3;
        aja = cart;
        ajK = flag3;
        ajL = flag4;
        ajM = acountryspecification;
        ajN = flag5;
        ajO = flag6;
        ajP = arraylist;
    }

    public static Builder newBuilder()
    {
        MaskedWalletRequest maskedwalletrequest = new MaskedWalletRequest();
        maskedwalletrequest.getClass();
        return maskedwalletrequest. new Builder(null);
    }

    public final boolean allowDebitCard()
    {
        return ajO;
    }

    public final boolean allowPrepaidCard()
    {
        return ajN;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final ArrayList getAllowedCountrySpecificationsForShipping()
    {
        return ajP;
    }

    public final com.google.android.gms.wallet.CountrySpecification[] getAllowedShippingCountrySpecifications()
    {
        return ajM;
    }

    public final Cart getCart()
    {
        return aja;
    }

    public final String getCurrencyCode()
    {
        return aiL;
    }

    public final String getEstimatedTotalPrice()
    {
        return ajI;
    }

    public final String getMerchantName()
    {
        return ajJ;
    }

    public final String getMerchantTransactionId()
    {
        return aiR;
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final boolean isBillingAgreement()
    {
        return ajL;
    }

    public final boolean isPhoneNumberRequired()
    {
        return ajF;
    }

    public final boolean isShippingAddressRequired()
    {
        return ajG;
    }

    public final boolean shouldRetrieveWalletObjects()
    {
        return ajK;
    }

    public final boolean useMinimalBillingAddress()
    {
        return ajH;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        l.a(this, parcel, i);
    }


    private class Builder
    {

        final MaskedWalletRequest ajQ;

        public final Builder addAllowedCountrySpecificationForShipping(CountrySpecification countryspecification)
        {
            if (ajQ.ajP == null)
            {
                ajQ.ajP = new ArrayList();
            }
            ajQ.ajP.add(countryspecification);
            return this;
        }

        public final Builder addAllowedCountrySpecificationsForShipping(Collection collection)
        {
            if (collection != null)
            {
                if (ajQ.ajP == null)
                {
                    ajQ.ajP = new ArrayList();
                }
                ajQ.ajP.addAll(collection);
            }
            return this;
        }

        public final MaskedWalletRequest build()
        {
            return ajQ;
        }

        public final Builder setAllowDebitCard(boolean flag)
        {
            ajQ.ajO = flag;
            return this;
        }

        public final Builder setAllowPrepaidCard(boolean flag)
        {
            ajQ.ajN = flag;
            return this;
        }

        public final Builder setCart(Cart cart)
        {
            ajQ.aja = cart;
            return this;
        }

        public final Builder setCurrencyCode(String s)
        {
            ajQ.aiL = s;
            return this;
        }

        public final Builder setEstimatedTotalPrice(String s)
        {
            ajQ.ajI = s;
            return this;
        }

        public final Builder setIsBillingAgreement(boolean flag)
        {
            ajQ.ajL = flag;
            return this;
        }

        public final Builder setMerchantName(String s)
        {
            ajQ.ajJ = s;
            return this;
        }

        public final Builder setMerchantTransactionId(String s)
        {
            ajQ.aiR = s;
            return this;
        }

        public final Builder setPhoneNumberRequired(boolean flag)
        {
            ajQ.ajF = flag;
            return this;
        }

        public final Builder setShippingAddressRequired(boolean flag)
        {
            ajQ.ajG = flag;
            return this;
        }

        public final Builder setShouldRetrieveWalletObjects(boolean flag)
        {
            ajQ.ajK = flag;
            return this;
        }

        public final Builder setUseMinimalBillingAddress(boolean flag)
        {
            ajQ.ajH = flag;
            return this;
        }

        private Builder()
        {
            ajQ = MaskedWalletRequest.this;
            super();
        }

        Builder(_cls1 _pcls1)
        {
            this();
        }
    }

}
