// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;


// Referenced classes of package com.viewpagerindicator:
//            UnderlinePageIndicator

final class n
    implements Runnable
{

    private UnderlinePageIndicator a;

    n(UnderlinePageIndicator underlinepageindicator)
    {
        a = underlinepageindicator;
        super();
    }

    public final void run()
    {
        if (UnderlinePageIndicator.a(a))
        {
            a.post(UnderlinePageIndicator.d(a));
        }
    }
}
