// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.TouchView;


final class g
    implements InputStreamWrapper.InputStreamProgressListener
{

    private UrlTouchImageView.ImageLoadTask a;

    g(UrlTouchImageView.ImageLoadTask imageloadtask)
    {
        a = imageloadtask;
        super();
    }

    public final void onProgress(float f, long l, long l1)
    {
        UrlTouchImageView.ImageLoadTask imageloadtask = a;
        Integer ainteger[] = new Integer[1];
        ainteger[0] = Integer.valueOf((int)(100F * f));
        UrlTouchImageView.ImageLoadTask.a(imageloadtask, ainteger);
    }
}
