// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.analytics;


public final class ImageDownloadRequestPage extends Enum
{

    public static final ImageDownloadRequestPage HomeClp;
    public static final ImageDownloadRequestPage ProductList;
    public static final ImageDownloadRequestPage Random;
    private static final ImageDownloadRequestPage a[];

    private ImageDownloadRequestPage(String s, int i)
    {
        super(s, i);
    }

    public static ImageDownloadRequestPage valueOf(String s)
    {
        return (ImageDownloadRequestPage)Enum.valueOf(com/flipkart/android/analytics/ImageDownloadRequestPage, s);
    }

    public static ImageDownloadRequestPage[] values()
    {
        return (ImageDownloadRequestPage[])a.clone();
    }

    static 
    {
        HomeClp = new ImageDownloadRequestPage("HomeClp", 0);
        ProductList = new ImageDownloadRequestPage("ProductList", 1);
        Random = new ImageDownloadRequestPage("Random", 2);
        ImageDownloadRequestPage aimagedownloadrequestpage[] = new ImageDownloadRequestPage[3];
        aimagedownloadrequestpage[0] = HomeClp;
        aimagedownloadrequestpage[1] = ProductList;
        aimagedownloadrequestpage[2] = Random;
        a = aimagedownloadrequestpage;
    }
}
