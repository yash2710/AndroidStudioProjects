// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.ArrayList;
import java.util.Collection;

// Referenced classes of package com.google.android.gms.wallet:
//            MaskedWalletRequest, Cart

public final class <init>
{

    final MaskedWalletRequest ajQ;

    public final fication addAllowedCountrySpecificationForShipping(CountrySpecification countryspecification)
    {
        if (ajQ.ajP == null)
        {
            ajQ.ajP = new ArrayList();
        }
        ajQ.ajP.add(countryspecification);
        return this;
    }

    public final ajQ addAllowedCountrySpecificationsForShipping(Collection collection)
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

    public final ajQ setAllowDebitCard(boolean flag)
    {
        ajQ.ajO = flag;
        return this;
    }

    public final ajQ setAllowPrepaidCard(boolean flag)
    {
        ajQ.ajN = flag;
        return this;
    }

    public final ajQ setCart(Cart cart)
    {
        ajQ.aja = cart;
        return this;
    }

    public final ajQ setCurrencyCode(String s)
    {
        ajQ.aiL = s;
        return this;
    }

    public final ajQ setEstimatedTotalPrice(String s)
    {
        ajQ.ajI = s;
        return this;
    }

    public final ajQ setIsBillingAgreement(boolean flag)
    {
        ajQ.ajL = flag;
        return this;
    }

    public final ajQ setMerchantName(String s)
    {
        ajQ.ajJ = s;
        return this;
    }

    public final ajQ setMerchantTransactionId(String s)
    {
        ajQ.aiR = s;
        return this;
    }

    public final ajQ setPhoneNumberRequired(boolean flag)
    {
        ajQ.ajF = flag;
        return this;
    }

    public final ajQ setShippingAddressRequired(boolean flag)
    {
        ajQ.ajG = flag;
        return this;
    }

    public final ajQ setShouldRetrieveWalletObjects(boolean flag)
    {
        ajQ.ajK = flag;
        return this;
    }

    public final ajQ setUseMinimalBillingAddress(boolean flag)
    {
        ajQ.ajH = flag;
        return this;
    }

    private fication(MaskedWalletRequest maskedwalletrequest)
    {
        ajQ = maskedwalletrequest;
        super();
    }

    ajQ(MaskedWalletRequest maskedwalletrequest, ajQ ajq)
    {
        this(maskedwalletrequest);
    }
}
