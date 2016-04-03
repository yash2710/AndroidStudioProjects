// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.seller;

import com.flipkart.android.DB.Seller;
import com.flipkart.android.DB.SellerDao;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.utils.GZipCompressorUtil;
import com.flipkart.android.utils.HashUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.volley.request.seller.params.SellerParam;

// Referenced classes of package com.flipkart.android.volley.request.seller:
//            SellerRequest

final class b extends Thread
{

    private boolean a;
    private byte b[];
    private SellerRequest c;

    b(SellerRequest sellerrequest, boolean flag, byte abyte0[])
    {
        c = sellerrequest;
        a = flag;
        b = abyte0;
        super();
    }

    public final void run()
    {
        SellerDao sellerdao;
        Seller seller;
        sellerdao = new SellerDao(FlipkartApplication.getAppContext());
        if (!a)
        {
            break MISSING_BLOCK_LABEL_53;
        }
        seller = new Seller(HashUtils.md5(SellerRequest.a(c).generateURI()), ScreenMathUtils.getCurrentLinuxTimeInSeconds(), b);
_L1:
        sellerdao.create(seller);
        return;
        try
        {
            seller = new Seller(HashUtils.md5(SellerRequest.a(c).generateURI()), ScreenMathUtils.getCurrentLinuxTimeInSeconds(), GZipCompressorUtil.compress(b));
        }
        catch (Exception exception)
        {
            return;
        }
          goto _L1
    }
}
