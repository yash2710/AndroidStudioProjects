// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;

import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.appconfig.ImageProfileMatrix;
import com.flipkart.android.response.appconfig.ImageProfileValues;
import com.flipkart.android.utils.NetworkMonitor;
import com.flipkart.android.utils.StringUtils;
import java.util.Map;

public class OMUImageValue
{

    private Map imageMap;

    public OMUImageValue()
    {
    }

    private static String getImageForBaseUrl(String s, String s1)
    {
        if (FlipkartApplication.getImageProfileMatrix() == null) goto _L2; else goto _L1
_L1:
        ImageProfileValues imageprofilevalues;
        int i;
        imageprofilevalues = (ImageProfileValues)((Map)FlipkartApplication.getImageProfileMatrix().getImageConfig().get("default")).get(s);
        i = NetworkMonitor.isNetworkFast();
        String s2;
        String s3;
        {
            if (i != 2)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            String s4;
            ImageProfileData imageprofiledata;
            String s5;
            try
            {
                s4 = imageprofilevalues.getG3();
            }
            catch (Exception exception)
            {
                return null;
            }
        }
        s3 = null;
        if (s4 == null)
        {
            break MISSING_BLOCK_LABEL_142;
        }
        imageprofiledata = ImageProfileData.parseString(s4);
        s2 = s1.replace("{@height}", imageprofiledata.height).replace("{@width}", imageprofiledata.width).replace("{@quality}", imageprofiledata.quality);
        break; /* Loop/switch isn't completed */
        if (i != 3)
        {
            break MISSING_BLOCK_LABEL_117;
        }
        s4 = imageprofilevalues.getG2();
        continue; /* Loop/switch isn't completed */
        s5 = imageprofilevalues.getWifi();
        s4 = s5;
        if (true) goto _L4; else goto _L3
_L4:
        break MISSING_BLOCK_LABEL_51;
_L2:
        s2 = null;
_L3:
        s3 = s2;
        return s3;
    }

    public String fetchBestImageUrl(String s, String s1, int i)
    {
label0:
        {
            String s2;
            String s3;
            String s4;
            try
            {
                if (imageMap == null || imageMap.size() == 0)
                {
                    break MISSING_BLOCK_LABEL_73;
                }
                s2 = getImageForBaseUrl(s, (String)imageMap.get(s1));
            }
            catch (Exception exception)
            {
                return null;
            }
            s3 = s2;
            try
            {
                if (!StringUtils.isNullOrEmpty(s3))
                {
                    break label0;
                }
                s4 = fetchUrl(i);
            }
            catch (Exception exception1)
            {
                return s3;
            }
            s3 = s4;
        }
        return s3;
        return null;
    }

    public String fetchUrl(int i)
    {
        if (i > 240) goto _L2; else goto _L1
_L1:
        String s = (String)imageMap.get("240");
_L4:
        if (StringUtils.isNullOrEmpty(s))
        {
            s = (String)imageMap.get("720");
        }
        return s;
_L2:
        if (i <= 320)
        {
            s = (String)imageMap.get("320");
        } else
        if (i <= 480)
        {
            s = (String)imageMap.get("480");
        } else
        if (i <= 720)
        {
            s = (String)imageMap.get("720");
        } else
        {
            s = null;
            if (i <= 1080)
            {
                s = (String)imageMap.get("1080");
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public Map getImageMap()
    {
        return imageMap;
    }

    public void setImageMap(Map map)
    {
        imageMap = map;
    }

    private class ImageProfileData
    {

        public String height;
        public String quality;
        public String width;

        public static ImageProfileData parseString(String s)
        {
            String as[] = s.split(",");
            if (as.length == 3)
            {
                ImageProfileData imageprofiledata = new ImageProfileData();
                imageprofiledata.height = as[0];
                imageprofiledata.width = as[1];
                imageprofiledata.quality = as[2];
                return imageprofiledata;
            } else
            {
                return null;
            }
        }

        public ImageProfileData()
        {
        }
    }

}
