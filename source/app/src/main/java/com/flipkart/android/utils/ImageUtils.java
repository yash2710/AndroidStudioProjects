// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.customvalues.Image;
import com.flipkart.android.response.productInfo.ProductImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.utils:
//            NetworkMonitor, ScreenMathUtils, StringUtils

public class ImageUtils
{

    public ImageUtils()
    {
    }

    public static int dpToPx(int i)
    {
        return Math.round(FlipkartApplication.getAppContext().getResources().getDisplayMetrics().density * (float)i);
    }

    public static ProductImage fetchBestImage(int i, int j, String s, Map map)
    {
        int k;
        if (i > 650)
        {
            j = 650;
        }
        k = NetworkMonitor.isNetworkFast();
        if (k == 3)
        {
            j /= 2;
        }
        if (map != null) goto _L2; else goto _L1
_L1:
        Map map1;
        return null;
_L2:
        if ((map1 = (Map)map.get(s)) == null || map1.size() == 0) goto _L1; else goto _L3
_L3:
        Iterator iterator;
        ProductImage productimage;
        ProductImage productimage1;
        ArrayList arraylist;
        iterator = map1.keySet().iterator();
        productimage = null;
        productimage1 = null;
        arraylist = null;
_L7:
        while (iterator.hasNext()) 
        {
            ProductImage productimage3 = (ProductImage)map1.get((String)iterator.next());
            if (productimage3 != null)
            {
label0:
                {
                    if (productimage3.getActualHeight() >= j)
                    {
                        break label0;
                    }
                    Iterator iterator1;
                    int l;
                    ProductImage productimage2;
                    int i1;
                    int j1;
                    ProductImage productimage4;
                    ArrayList arraylist1;
                    if (arraylist == null)
                    {
                        arraylist1 = new ArrayList();
                    } else
                    {
                        arraylist1 = arraylist;
                    }
                    arraylist1.add(productimage3);
                    arraylist = arraylist1;
                    productimage = productimage3;
                }
            }
        }
          goto _L4
        if (k != 3) goto _L6; else goto _L5
_L5:
label1:
        {
            if (productimage1 != null)
            {
                break label1;
            }
            productimage = productimage3;
            productimage1 = productimage3;
        }
          goto _L7
        if (productimage3.getActualHeight() > productimage1.getActualHeight()) goto _L6; else goto _L8
_L8:
        productimage4 = productimage3;
_L12:
        productimage1 = productimage4;
        productimage = productimage3;
          goto _L7
_L4:
        if (productimage1 == null) goto _L10; else goto _L9
_L9:
        productimage = productimage1;
_L11:
        return productimage;
_L10:
        if (arraylist != null && arraylist.size() > 0)
        {
            iterator1 = arraylist.iterator();
            l = 0;
            while (iterator1.hasNext()) 
            {
                productimage2 = (ProductImage)iterator1.next();
                if (l < productimage2.getActualHeight() * productimage2.getActualWidth())
                {
                    j1 = productimage2.getActualHeight() * productimage2.getActualWidth();
                    productimage = productimage2;
                    i1 = j1;
                } else
                {
                    i1 = l;
                }
                l = i1;
            }
        }
        if (true) goto _L11; else goto _L6
_L6:
        productimage4 = productimage1;
          goto _L12
    }

    public static String fetchBestImage(Map map, int i, int j)
    {
        int k = 650;
        int l;
        int i1;
        Iterator iterator;
        ProductImage productimage;
        if (i > k)
        {
            j = k;
        } else
        {
            k = i;
        }
        if (NetworkMonitor.isNetworkFast() == 3)
        {
            int i2 = k / 2;
            j /= 2;
            l = i2;
        } else
        {
            l = k;
        }
        i1 = 0;
        iterator = map.keySet().iterator();
        productimage = null;
        while (iterator.hasNext()) 
        {
            ProductImage productimage1 = (ProductImage)map.get((String)iterator.next());
            int j1 = productimage1.getActualHeight();
            int k1 = productimage1.getActualWidth();
            String s;
            int l1;
            if (j1 < j && k1 < l && i1 < j1 * k1)
            {
                l1 = j1 * k1;
            } else
            {
                productimage1 = productimage;
                l1 = i1;
            }
            i1 = l1;
            productimage = productimage1;
        }
        s = null;
        if (productimage != null)
        {
            s = productimage.getUrl();
        }
        return s;
    }

    public static String getImageUrl(Image image)
    {
        int i;
        if (image == null)
        {
            break MISSING_BLOCK_LABEL_83;
        }
        i = ScreenMathUtils.getScreenDpi(FlipkartApplication.getAppContext());
        image.getDp320();
        if (i > 240) goto _L2; else goto _L1
_L1:
        String s = image.getDp240();
_L4:
        return s;
_L2:
        if (i <= 320)
        {
            return image.getDp320();
        }
        if (i <= 480)
        {
            return image.getDp480();
        }
        if (i <= 720)
        {
            return image.getDp720();
        }
        s = image.getDp1080();
        if (!StringUtils.isNullOrEmpty(s)) goto _L4; else goto _L3
_L3:
        return image.getDp720();
        return "";
    }
}
