// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.analytics;


public final class ImageOrientation extends Enum
{

    public static final ImageOrientation LANDSCAPE;
    public static final ImageOrientation None;
    public static final ImageOrientation PORTRAIT;
    private static final ImageOrientation a[];

    private ImageOrientation(String s, int i)
    {
        super(s, i);
    }

    public static ImageOrientation valueOf(String s)
    {
        return (ImageOrientation)Enum.valueOf(com/flipkart/android/analytics/ImageOrientation, s);
    }

    public static ImageOrientation[] values()
    {
        return (ImageOrientation[])a.clone();
    }

    static 
    {
        LANDSCAPE = new ImageOrientation("LANDSCAPE", 0);
        PORTRAIT = new ImageOrientation("PORTRAIT", 1);
        None = new ImageOrientation("None", 2);
        ImageOrientation aimageorientation[] = new ImageOrientation[3];
        aimageorientation[0] = LANDSCAPE;
        aimageorientation[1] = PORTRAIT;
        aimageorientation[2] = None;
        a = aimageorientation;
    }
}
