// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import com.google.android.gms.identity.intents.model.UserAddress;

// Referenced classes of package com.google.android.gms.wallet:
//            MaskedWallet, Address, InstrumentInfo, LoyaltyWalletObject, 
//            OfferWalletObject

public final class <init>
{

    final MaskedWallet ajE;

    public final MaskedWallet build()
    {
        return ajE;
    }

    public final ajE setBillingAddress(Address address)
    {
        ajE.aiU = address;
        return this;
    }

    public final ddress setBuyerBillingAddress(UserAddress useraddress)
    {
        ajE.aiX = useraddress;
        return this;
    }

    public final ddress setBuyerShippingAddress(UserAddress useraddress)
    {
        ajE.aiY = useraddress;
        return this;
    }

    public final ajE setEmail(String s)
    {
        ajE.aiT = s;
        return this;
    }

    public final ajE setGoogleTransactionId(String s)
    {
        ajE.aiQ = s;
        return this;
    }

    public final ajE setInstrumentInfos(InstrumentInfo ainstrumentinfo[])
    {
        ajE.aiZ = ainstrumentinfo;
        return this;
    }

    public final ajE setLoyaltyWalletObjects(LoyaltyWalletObject aloyaltywalletobject[])
    {
        ajE.ajC = aloyaltywalletobject;
        return this;
    }

    public final ajE setMerchantTransactionId(String s)
    {
        ajE.aiR = s;
        return this;
    }

    public final ajE setOfferWalletObjects(OfferWalletObject aofferwalletobject[])
    {
        ajE.ajD = aofferwalletobject;
        return this;
    }

    public final ajE setPaymentDescriptions(String as[])
    {
        ajE.aiW = as;
        return this;
    }

    public final ajE setShippingAddress(Address address)
    {
        ajE.aiV = address;
        return this;
    }

    private ddress(MaskedWallet maskedwallet)
    {
        ajE = maskedwallet;
        super();
    }

    ajE(MaskedWallet maskedwallet, ajE aje)
    {
        this(maskedwallet);
    }
}
