// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned.rss.expanded.decoders;


// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            m

final class l
{

    private int a;
    private m b;

    l()
    {
        a = 0;
        b = m.a;
    }

    final int a()
    {
        return a;
    }

    final void a(int i)
    {
        a = i;
    }

    final void b(int i)
    {
        a = i + a;
    }

    final boolean b()
    {
        return b == m.b;
    }

    final boolean c()
    {
        return b == m.c;
    }

    final void d()
    {
        b = m.a;
    }

    final void e()
    {
        b = m.b;
    }

    final void f()
    {
        b = m.c;
    }
}
