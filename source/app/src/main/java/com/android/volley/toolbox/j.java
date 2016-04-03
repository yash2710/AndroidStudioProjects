// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

// Referenced classes of package com.android.volley.toolbox:
//            ImageLoader, k

final class j
    implements Runnable
{

    private ImageLoader a;

    j(ImageLoader imageloader)
    {
        a = imageloader;
        super();
    }

    public final void run()
    {
        for (Iterator iterator = ImageLoader.d(a).values().iterator(); iterator.hasNext();)
        {
            k k1 = (k)iterator.next();
            Iterator iterator1 = k.a(k1).iterator();
            while (iterator1.hasNext()) 
            {
                ImageLoader.ImageContainer imagecontainer = (ImageLoader.ImageContainer)iterator1.next();
                if (ImageLoader.ImageContainer.a(imagecontainer) != null)
                {
                    if (k1.getError() == null)
                    {
                        ImageLoader.ImageContainer.a(imagecontainer, k.b(k1));
                        ImageLoader.ImageContainer.a(imagecontainer).onResponse(imagecontainer, false);
                    } else
                    {
                        ImageLoader.ImageContainer.a(imagecontainer).onErrorResponse(k1.getError());
                    }
                }
            }
        }

        ImageLoader.d(a).clear();
        ImageLoader.a(a, null);
    }
}
