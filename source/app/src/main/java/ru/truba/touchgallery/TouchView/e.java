// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.TouchView;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import java.lang.ref.WeakReference;

// Referenced classes of package ru.truba.touchgallery.TouchView:
//            TouchImageView

final class e extends Handler
{

    private final WeakReference a;

    e(TouchImageView touchimageview)
    {
        a = new WeakReference(touchimageview);
    }

    public final void handleMessage(Message message)
    {
        ((TouchImageView)a.get()).performClick();
        if (TouchImageView.h((TouchImageView)a.get()) != null)
        {
            TouchImageView.h((TouchImageView)a.get()).onClick((View)a.get());
        }
    }
}
