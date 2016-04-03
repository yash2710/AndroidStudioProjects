// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.WidgetData;
import com.flipkart.android.response.component.layout.LayoutContainer;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.response.wrapper.ResponseWrapper;
import com.flipkart.logging.FkLogger;
import com.google.mygson.Gson;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

// Referenced classes of package com.flipkart.android.utils:
//            GZipCompressorUtil

public class GsonUtils
{

    public GsonUtils()
    {
    }

    public static Object getResponse(Type type, byte abyte0[], boolean flag)
    {
        Gson gson = FlipkartApplication.getGsonInstance();
        if (!flag) goto _L2; else goto _L1
_L1:
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(GZipCompressorUtil.decompress(abyte0));
_L5:
        InputStreamReader inputstreamreader = new InputStreamReader(bytearrayinputstream);
        if (type != com/flipkart/android/response/productInfo/ProductInfo && type != com/flipkart/android/response/component/data/WidgetData && type != com/flipkart/android/response/component/layout/LayoutContainer) goto _L4; else goto _L3
_L3:
        return gson.fromJson(inputstreamreader, type);
_L2:
        bytearrayinputstream = new ByteArrayInputStream(abyte0);
          goto _L5
        Exception exception;
        exception;
        FkLogger.printStackTrace(exception);
_L7:
        return null;
_L4:
        ResponseWrapper responsewrapper = (ResponseWrapper)gson.fromJson(inputstreamreader, type);
        if (responsewrapper == null) goto _L7; else goto _L6
_L6:
        Object obj = responsewrapper.getResponse();
        return obj;
    }
}
