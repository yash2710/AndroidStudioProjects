// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.config;

import android.content.Context;
import android.content.res.Resources;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.ConfigDataHandler;
import com.flipkart.android.datahandler.ImageMatrixConfigDataHandler;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.appconfig.ConfigResponseData;
import com.flipkart.android.response.appconfig.ImageProfileMatrix;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.MiscUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.google.mygson.Gson;

// Referenced classes of package com.flipkart.android.config:
//            FlipkartPreferenceManager, a, b

public class ConfigHelper
{

    private Context a;

    public ConfigHelper(Context context)
    {
        a = context;
    }

    static void a(ConfigHelper confighelper, ConfigResponseData configresponsedata)
    {
        if (configresponsedata != null)
        {
            AppConfigUtils.getInstance().putConfigData(configresponsedata);
        }
    }

    static void a(ConfigHelper confighelper, ImageProfileMatrix imageprofilematrix)
    {
        if (imageprofilematrix != null)
        {
            String s = FlipkartApplication.getGsonInstance().toJson(imageprofilematrix);
            FlipkartPreferenceManager.instance().saveImageMatrix(s);
        }
    }

    public static ImageProfileMatrix getImageMatrixfFromPreferences()
    {
        String s = FlipkartPreferenceManager.instance().getImageMatrix();
        if (StringUtils.isNullOrEmpty(s))
        {
            s = MiscUtils.readConfigFromAssets("ImageMatrixJson");
        }
        if (!StringUtils.isNullOrEmpty(s))
        {
            return (ImageProfileMatrix)FlipkartApplication.getGsonInstance().fromJson(s, com/flipkart/android/response/appconfig/ImageProfileMatrix);
        } else
        {
            return null;
        }
    }

    public void readConfig()
    {
        if (a != null)
        {
            (new a(this)).readConfig();
        }
    }

    public void readImageMatrixConfig()
    {
        b b1;
        long l;
label0:
        {
            if (a != null)
            {
                b1 = new b(this);
                ImageProfileMatrix imageprofilematrix = getImageMatrixfFromPreferences();
                l = -1L;
                if (imageprofilematrix != null)
                {
                    FlipkartApplication.setImageProfileMatrix(imageprofilematrix);
                    l = imageprofilematrix.getHashCode();
                }
                if (!FlipkartApplication.getAppContext().getResources().getBoolean(0x7f08000b))
                {
                    break label0;
                }
                b1.fetchImageMatrix(ScreenMathUtils.getScreenDpiStringOneUp(FlipkartApplication.getAppContext()), l, new AnalyticData(null, null, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil()));
            }
            return;
        }
        b1.fetchImageMatrix(ScreenMathUtils.getScreenDpiString(FlipkartApplication.getAppContext()), l, new AnalyticData(null, null, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil()));
    }
}
