// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.TouchView;


final class a
    implements InputStreamWrapper.InputStreamProgressListener
{

    private FileTouchImageView.ImageLoadTask a;

    a(FileTouchImageView.ImageLoadTask imageloadtask)
    {
        a = imageloadtask;
        super();
    }

    public final void onProgress(float f, long l, long l1)
    {
        FileTouchImageView.ImageLoadTask imageloadtask = a;
        Integer ainteger[] = new Integer[1];
        ainteger[0] = Integer.valueOf((int)(100F * f));
        FileTouchImageView.ImageLoadTask.a(imageloadtask, ainteger);
    }
}
