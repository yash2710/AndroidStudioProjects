// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;


// Referenced classes of package com.flipkart.android.response.productInfo:
//            PromiseWidgetImage

public class PromiseWidget
{

    String body;
    String bullet;
    PromiseWidgetImage endImage;
    PromiseWidgetImage startImage;

    public PromiseWidget()
    {
    }

    public String getBody()
    {
        return body;
    }

    public String getBullet()
    {
        return bullet;
    }

    public BulletType getBulletEnum()
    {
        if (bullet.equalsIgnoreCase(BulletType.CROSS.toString()))
        {
            return BulletType.CROSS;
        }
        if (bullet.equalsIgnoreCase(BulletType.TICK.toString()))
        {
            return BulletType.TICK;
        }
        if (bullet.equalsIgnoreCase(BulletType.LINE.toString()))
        {
            return BulletType.LINE;
        }
        if (bullet.equalsIgnoreCase(BulletType.NONE.toString()))
        {
            return BulletType.NONE;
        } else
        {
            return BulletType.NONE;
        }
    }

    public PromiseWidgetImage getEndImage()
    {
        return endImage;
    }

    public PromiseWidgetImage getStartImage()
    {
        return startImage;
    }

    public void setBody(String s)
    {
        body = s;
    }

    public void setBullet(String s)
    {
        bullet = s;
    }

    public void setEndImage(PromiseWidgetImage promisewidgetimage)
    {
        endImage = promisewidgetimage;
    }

    public void setStartImage(PromiseWidgetImage promisewidgetimage)
    {
        startImage = promisewidgetimage;
    }

    private class BulletType extends Enum
    {

        private static final BulletType $VALUES[];
        public static final BulletType CROSS;
        public static final BulletType LINE;
        public static final BulletType NONE;
        public static final BulletType TICK;
        private final String bulletType;

        public static BulletType valueOf(String s)
        {
            return (BulletType)Enum.valueOf(com/flipkart/android/response/productInfo/PromiseWidget$BulletType, s);
        }

        public static BulletType[] values()
        {
            return (BulletType[])$VALUES.clone();
        }

        public final String toString()
        {
            return bulletType;
        }

        static 
        {
            TICK = new BulletType("TICK", 0, "TICK");
            CROSS = new BulletType("CROSS", 1, "CROSS");
            LINE = new BulletType("LINE", 2, "LINE");
            NONE = new BulletType("NONE", 3, "NONE");
            BulletType abullettype[] = new BulletType[4];
            abullettype[0] = TICK;
            abullettype[1] = CROSS;
            abullettype[2] = LINE;
            abullettype[3] = NONE;
            $VALUES = abullettype;
        }

        private BulletType(String s, int i, String s1)
        {
            super(s, i);
            bulletType = s1;
        }
    }

}
