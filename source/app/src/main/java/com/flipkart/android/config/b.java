// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.config;

import com.flipkart.android.datahandler.ImageMatrixConfigDataHandler;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.appconfig.ImageProfileMatrix;

// Referenced classes of package com.flipkart.android.config:
//            ConfigHelper

final class b extends ImageMatrixConfigDataHandler
{

    private ConfigHelper a;

    b(ConfigHelper confighelper)
    {
        a = confighelper;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        super.errorReceived(i, j, s);
    }

    public final void resultReceived(ImageProfileMatrix imageprofilematrix, boolean flag)
    {
        FlipkartApplication.setImageProfileMatrix(imageprofilematrix);
        ConfigHelper.a(a, imageprofilematrix);
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((ImageProfileMatrix)obj, flag);
    }
}
