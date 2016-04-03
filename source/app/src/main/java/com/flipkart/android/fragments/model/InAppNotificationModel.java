// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments.model;

import android.content.Context;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.customwidgetitemvalue.Action;
import com.flipkart.android.response.inAppNotification.InAppNotification;
import com.flipkart.android.response.productInfo.ProductImage;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.google.mygson.Gson;
import com.google.mygson.internal.LinkedTreeMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class InAppNotificationModel
{

    private String a;
    private String b;
    private String c;
    private boolean d;
    private boolean e;
    private Action f;
    private long g;
    private String h;
    private String i;
    private String j;
    private String k;

    public InAppNotificationModel()
    {
    }

    public static InAppNotificationModel getModel(InAppNotification inappnotification, Context context)
    {
        InAppNotificationModel inappnotificationmodel;
        ProductImage productimage;
        ProductImage productimage1;
        if (inappnotification == null)
        {
            return null;
        }
        inappnotificationmodel = new InAppNotificationModel();
        inappnotificationmodel.setTitle(inappnotification.getTitle());
        inappnotificationmodel.setSubTitle(inappnotification.getText());
        if (inappnotification.isSharable())
        {
            Action action = inappnotification.getAction();
            if (action != null)
            {
                inappnotificationmodel.setShareUrl(action.getUrl());
            }
        }
        inappnotificationmodel.setShareable(inappnotification.isSharable());
        inappnotificationmodel.setAction(inappnotification.getAction());
        inappnotificationmodel.setTimeStamp(inappnotification.getTimestamp().longValue());
        inappnotificationmodel.setNotificationId(inappnotification.getId());
        inappnotificationmodel.setNotificationType(inappnotification.getType());
        long l = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - inappnotification.getTimestamp().longValue());
        boolean flag;
        Iterator iterator;
        String s2;
        int i1;
        String s3;
        if (l == 0L)
        {
            inappnotificationmodel.setTime(" 1m ago");
        } else
        if (l <= 60L)
        {
            inappnotificationmodel.setTime((new StringBuilder(" ")).append(l).append("m ago").toString());
        } else
        if (l < 1440L)
        {
            inappnotificationmodel.setTime((new StringBuilder(" ")).append(l / 60L).append("h ago").toString());
        } else
        if (l >= 1440L)
        {
            inappnotificationmodel.setTime((new StringBuilder(" ")).append(l / 1440L).append("d ago").toString());
        } else
        if (l >= 0x80520L)
        {
            inappnotificationmodel.setTime((new StringBuilder(" ")).append(l / 0x80520L).append("y ago").toString());
        }
        if (!inappnotification.isRead())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        inappnotificationmodel.setNew(flag);
        if (inappnotification.getImages() == null) goto _L2; else goto _L1
_L1:
        iterator = inappnotification.getImages().keySet().iterator();
        productimage = null;
        productimage1 = null;
_L4:
        ProductImage productimage2;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        s2 = (String)iterator.next();
        if (StringUtils.isNullOrEmpty(s2))
        {
            break MISSING_BLOCK_LABEL_719;
        }
        Object obj = inappnotification.getImages().get(s2);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_719;
        }
        if (obj instanceof String)
        {
            i1 = ScreenMathUtils.getScreenDpi(FlipkartApplication.getAppContext());
            s3 = (String)inappnotification.getImages().get((new StringBuilder()).append(i1).toString());
            if (i1 > 720 && StringUtils.isNullOrEmpty(s3))
            {
                s3 = (String)inappnotification.getImages().get("720");
            }
            if (!StringUtils.isNullOrEmpty(s3))
            {
                inappnotificationmodel.setImageUrl(s3);
            } else
            {
                inappnotificationmodel.setImageUrl("");
            }
            return inappnotificationmodel;
        }
        if (!(obj instanceof LinkedTreeMap))
        {
            break MISSING_BLOCK_LABEL_719;
        }
        productimage2 = (ProductImage)FlipkartApplication.getGsonInstance().fromJson(FlipkartApplication.getGsonInstance().toJson(obj), com/flipkart/android/response/productInfo/ProductImage);
        if (productimage2 == null)
        {
            break MISSING_BLOCK_LABEL_719;
        }
        if (productimage2.getActualWidth() <= ScreenMathUtils.dpToPx(75, context) && productimage2.getActualHeight() <= ScreenMathUtils.dpToPx(75, context))
        {
            if (productimage == null)
            {
                productimage = productimage2;
            } else
            {
                if (productimage2.getActualHeight() * productimage2.getActualWidth() <= productimage.getActualHeight() * productimage.getActualWidth())
                {
                    break MISSING_BLOCK_LABEL_719;
                }
                productimage = productimage2;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (productimage1 == null)
        {
            productimage1 = productimage2;
            continue; /* Loop/switch isn't completed */
        }
        if (productimage2.getActualHeight() * productimage2.getActualWidth() >= productimage1.getActualHeight() * productimage1.getActualWidth())
        {
            break MISSING_BLOCK_LABEL_719;
        }
_L5:
        productimage1 = productimage2;
        if (true) goto _L4; else goto _L3
_L3:
        if (productimage1 != null)
        {
            String s1 = productimage1.getUrl();
            if (!StringUtils.isNullOrEmpty(s1))
            {
                inappnotificationmodel.setImageUrl(s1);
            } else
            {
                inappnotificationmodel.setImageUrl("");
            }
            return inappnotificationmodel;
        }
        if (productimage != null)
        {
            String s = productimage.getUrl();
            if (!StringUtils.isNullOrEmpty(s))
            {
                inappnotificationmodel.setImageUrl(s);
            } else
            {
                inappnotificationmodel.setImageUrl("");
            }
            return inappnotificationmodel;
        }
        inappnotificationmodel.setImageUrl("");
_L2:
        return inappnotificationmodel;
        productimage2 = productimage1;
          goto _L5
    }

    public Action getAction()
    {
        return f;
    }

    public String getImageUrl()
    {
        return i;
    }

    public String getNotificationId()
    {
        return a;
    }

    public String getNotificationType()
    {
        return h;
    }

    public String getShareUrl()
    {
        return j;
    }

    public String getSubTitle()
    {
        return c;
    }

    public String getTime()
    {
        return k;
    }

    public long getTimeStamp()
    {
        return g;
    }

    public String getTitle()
    {
        return b;
    }

    public boolean isNew()
    {
        return e;
    }

    public boolean isShareable()
    {
        return d;
    }

    public void setAction(Action action)
    {
        f = action;
    }

    public void setImageUrl(String s)
    {
        i = s;
    }

    public void setNew(boolean flag)
    {
        e = flag;
    }

    public void setNotificationId(String s)
    {
        a = s;
    }

    public void setNotificationType(String s)
    {
        h = s;
    }

    public void setShareUrl(String s)
    {
        j = s;
    }

    public void setShareable(boolean flag)
    {
        d = flag;
    }

    public void setSubTitle(String s)
    {
        c = s;
    }

    public void setTime(String s)
    {
        k = s;
    }

    public void setTimeStamp(long l)
    {
        g = l;
    }

    public void setTitle(String s)
    {
        b = s;
    }
}
