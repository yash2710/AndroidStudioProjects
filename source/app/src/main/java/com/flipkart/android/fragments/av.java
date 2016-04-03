// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import java.util.Timer;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductPageImageGallaryFragment, aB

final class av
    implements android.view.View.OnClickListener
{

    private ProductPageImageGallaryFragment a;

    av(ProductPageImageGallaryFragment productpageimagegallaryfragment)
    {
        a = productpageimagegallaryfragment;
        super();
    }

    public final void onClick(View view)
    {
        if (a.c == null)
        {
            return;
        }
        if (!a.c.isDone())
        {
            a.c.cancel();
        }
        if (!a.e)
        {
            a.hideProductImageThumbnails(a.a);
            return;
        } else
        {
            a.c = new aB(a);
            a.b.schedule(a.c, 5000L);
            a.showProductImageThumbnails(a.a);
            return;
        }
    }
}
