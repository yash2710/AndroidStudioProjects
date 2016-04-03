// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.TouchView;

import android.os.Handler;
import java.util.TimerTask;

// Referenced classes of package ru.truba.touchgallery.TouchView:
//            TouchImageView

final class d extends TimerTask
{

    private TouchImageView a;

    private d(TouchImageView touchimageview)
    {
        a = touchimageview;
        super();
    }

    d(TouchImageView touchimageview, byte byte0)
    {
        this(touchimageview);
    }

    public final void run()
    {
        TouchImageView.g(a).sendEmptyMessage(0);
    }
}
