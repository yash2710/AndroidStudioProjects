// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.UGC;

import com.flipkart.android.DB.UGC;
import com.flipkart.android.DB.UGCDao;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.utils.GZipCompressorUtil;
import com.flipkart.android.utils.HashUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.volley.request.UGC.params.UGCParam;

// Referenced classes of package com.flipkart.android.volley.request.UGC:
//            UGCRequest

final class b extends Thread
{

    private boolean a;
    private byte b[];
    private UGCRequest c;

    b(UGCRequest ugcrequest, boolean flag, byte abyte0[])
    {
        c = ugcrequest;
        a = flag;
        b = abyte0;
        super();
    }

    public final void run()
    {
        UGCDao ugcdao;
        UGC ugc;
        ugcdao = new UGCDao(FlipkartApplication.getAppContext());
        if (!a)
        {
            break MISSING_BLOCK_LABEL_53;
        }
        ugc = new UGC(HashUtils.md5(UGCRequest.a(c).generateURI()), ScreenMathUtils.getCurrentLinuxTimeInSeconds(), b);
_L1:
        ugcdao.create(ugc);
        return;
        try
        {
            ugc = new UGC(HashUtils.md5(UGCRequest.a(c).generateURI()), ScreenMathUtils.getCurrentLinuxTimeInSeconds(), GZipCompressorUtil.compress(b));
        }
        catch (Exception exception)
        {
            return;
        }
          goto _L1
    }
}
