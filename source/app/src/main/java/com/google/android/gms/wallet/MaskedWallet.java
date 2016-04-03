// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.UserAddress;
import com.google.android.gms.internal.hm;

// Referenced classes of package com.google.android.gms.wallet:
//            k, Address, InstrumentInfo, LoyaltyWalletObject, 
//            OfferWalletObject

public final class MaskedWallet
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new k();
    String aiQ;
    String aiR;
    String aiT;
    Address aiU;
    Address aiV;
    String aiW[];
    UserAddress aiX;
    UserAddress aiY;
    InstrumentInfo aiZ[];
    LoyaltyWalletObject ajC[];
    OfferWalletObject ajD[];
    private final int xM;

    private MaskedWallet()
    {
        xM = 2;
    }

    MaskedWallet(int i, String s, String s1, String as[], String s2, Address address, Address address1, 
            LoyaltyWalletObject aloyaltywalletobject[], OfferWalletObject aofferwalletobject[], UserAddress useraddress, UserAddress useraddress1, InstrumentInfo ainstrumentinfo[])
    {
        xM = i;
        aiQ = s;
        aiR = s1;
        aiW = as;
        aiT = s2;
        aiU = address;
        aiV = address1;
        ajC = aloyaltywalletobject;
        ajD = aofferwalletobject;
        aiX = useraddress;
        aiY = useraddress1;
        aiZ = ainstrumentinfo;
    }

    public static Builder newBuilderFrom(MaskedWallet maskedwallet)
    {
        hm.f(maskedwallet);
        return ng().setGoogleTransactionId(maskedwallet.getGoogleTransactionId()).setMerchantTransactionId(maskedwallet.getMerchantTransactionId()).setPaymentDescriptions(maskedwallet.getPaymentDescriptions()).setInstrumentInfos(maskedwallet.getInstrumentInfos()).setEmail(maskedwallet.getEmail()).setLoyaltyWalletObjects(maskedwallet.getLoyaltyWalletObjects()).setOfferWalletObjects(maskedwallet.getOfferWalletObjects()).setBuyerBillingAddress(maskedwallet.getBuyerBillingAddress()).setBuyerShippingAddress(maskedwallet.getBuyerShippingAddress());
    }

    public static Builder ng()
    {
        MaskedWallet maskedwallet = new MaskedWallet();
        maskedwallet.getClass();
        return maskedwallet. new Builder(null);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final Address getBillingAddress()
    {
        return aiU;
    }

    public final UserAddress getBuyerBillingAddress()
    {
        return aiX;
    }

    public final UserAddress getBuyerShippingAddress()
    {
        return aiY;
    }

    public final String getEmail()
    {
        return aiT;
    }

    public final String getGoogleTransactionId()
    {
        return aiQ;
    }

    public final InstrumentInfo[] getInstrumentInfos()
    {
        return aiZ;
    }

    public final LoyaltyWalletObject[] getLoyaltyWalletObjects()
    {
        return ajC;
    }

    public final String getMerchantTransactionId()
    {
        return aiR;
    }

    public final OfferWalletObject[] getOfferWalletObjects()
    {
        return ajD;
    }

    public final String[] getPaymentDescriptions()
    {
        return aiW;
    }

    public final Address getShippingAddress()
    {
        return aiV;
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        k.a(this, parcel, i);
    }


    private class Builder
    {

        final MaskedWallet ajE;

        public final MaskedWallet build()
        {
            return ajE;
        }

        public final Builder setBillingAddress(Address address)
        {
            ajE.aiU = address;
            return this;
        }

        public final Builder setBuyerBillingAddress(UserAddress useraddress)
        {
            ajE.aiX = useraddress;
            return this;
        }

        public final Builder setBuyerShippingAddress(UserAddress useraddress)
        {
            ajE.aiY = useraddress;
            return this;
        }

        public final Builder setEmail(String s)
        {
            ajE.aiT = s;
            return this;
        }

        public final Builder setGoogleTransactionId(String s)
        {
            ajE.aiQ = s;
            return this;
        }

        public final Builder setInstrumentInfos(InstrumentInfo ainstrumentinfo[])
        {
            ajE.aiZ = ainstrumentinfo;
            return this;
        }

        public final Builder setLoyaltyWalletObjects(LoyaltyWalletObject aloyaltywalletobject[])
        {
            ajE.ajC = aloyaltywalletobject;
            return this;
        }

        public final Builder setMerchantTransactionId(String s)
        {
            ajE.aiR = s;
            return this;
        }

        public final Builder setOfferWalletObjects(OfferWalletObject aofferwalletobject[])
        {
            ajE.ajD = aofferwalletobject;
            return this;
        }

        public final Builder setPaymentDescriptions(String as[])
        {
            ajE.aiW = as;
            return this;
        }

        public final Builder setShippingAddress(Address address)
        {
            ajE.aiV = address;
            return this;
        }

        private Builder()
        {
            ajE = MaskedWallet.this;
            super();
        }

        Builder(_cls1 _pcls1)
        {
            this();
        }
    }

}
