// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.notification.data;

import com.flipkart.android.response.component.data.customvalues.Image;

// Referenced classes of package com.flipkart.android.notification.data:
//            NotificationType

public class NotificationDataPacket
{

    private String a;
    private long b;
    private String c;
    private boolean d;
    private boolean e;
    private boolean f;
    private NotificationType g;
    private String h;
    private String i;
    private String j;
    private String k;
    private Image l;
    private String m;
    private String n;
    private Image o;

    public NotificationDataPacket(String s, long l1, String s1, boolean flag, boolean flag1, boolean flag2, 
            NotificationType notificationtype, String s2, String s3, String s4, String s5, Image image, Image image1, 
            String s6, String s7)
    {
        a = s;
        b = l1;
        c = s1;
        d = flag;
        e = flag1;
        f = flag2;
        g = notificationtype;
        h = s2;
        i = s3;
        j = s4;
        k = s5;
        l = image;
        m = s6;
        n = s7;
        o = image1;
    }

    public String getAction()
    {
        return m;
    }

    public Image getBigImage()
    {
        return o;
    }

    public long getExpiry()
    {
        return b;
    }

    public Image getIconImage()
    {
        return l;
    }

    public String getMessage()
    {
        return j;
    }

    public String getMessageExtra()
    {
        return k;
    }

    public String getNotificationId()
    {
        return a;
    }

    public String getOmniture()
    {
        return n;
    }

    public String getPayload()
    {
        return h;
    }

    public String getRelativeTo()
    {
        return c;
    }

    public String getTitle()
    {
        return i;
    }

    public NotificationType getType()
    {
        return g;
    }

    public boolean isDoPostbackOnDismiss()
    {
        return f;
    }

    public boolean isDoPostbackOnRead()
    {
        return e;
    }

    public boolean isDoPostbackOnShow()
    {
        return d;
    }

    public void setAction(String s)
    {
        m = s;
    }

    public void setBigImage(Image image)
    {
        o = image;
    }

    public void setDoPostbackOnDismiss(boolean flag)
    {
        f = flag;
    }

    public void setDoPostbackOnRead(boolean flag)
    {
        e = flag;
    }

    public void setDoPostbackOnShow(boolean flag)
    {
        d = flag;
    }

    public void setExpiry(long l1)
    {
        b = l1;
    }

    public void setIconImage(Image image)
    {
        l = image;
    }

    public void setMessage(String s)
    {
        j = s;
    }

    public void setMessageExtra(String s)
    {
        k = s;
    }

    public void setNotificationId(String s)
    {
        a = s;
    }

    public void setOmniture(String s)
    {
        n = s;
    }

    public void setPayload(String s)
    {
        h = s;
    }

    public void setRelativeTo(String s)
    {
        c = s;
    }

    public void setTitle(String s)
    {
        i = s;
    }

    public void setType(NotificationType notificationtype)
    {
        g = notificationtype;
    }

    public String toString()
    {
        return (new StringBuilder("Notification id : ")).append(a).append("\nrelative to: ").append(c).append("\ntype : ").append(g).append("action : ").append(m).append("expiry : ").append(b).toString();
    }
}
