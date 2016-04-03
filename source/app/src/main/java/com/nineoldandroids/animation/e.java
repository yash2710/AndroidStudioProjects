// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;

import java.util.ArrayList;

// Referenced classes of package com.nineoldandroids.animation:
//            c, Animator

final class e
    implements Cloneable
{

    public Animator a;
    public ArrayList b;
    public ArrayList c;
    public ArrayList d;
    public ArrayList e;
    public boolean f;

    public e(Animator animator)
    {
        b = null;
        c = null;
        d = null;
        e = null;
        f = false;
        a = animator;
    }

    public final void addDependency(c c1)
    {
        if (b == null)
        {
            b = new ArrayList();
            d = new ArrayList();
        }
        b.add(c1);
        if (!d.contains(c1.a))
        {
            d.add(c1.a);
        }
        e e1 = c1.a;
        if (e1.e == null)
        {
            e1.e = new ArrayList();
        }
        e1.e.add(this);
    }

    public final e clone()
    {
        e e1;
        try
        {
            e1 = (e)super.clone();
            e1.a = a.clone();
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            throw new AssertionError();
        }
        return e1;
    }

    public final volatile Object clone()
    {
        return clone();
    }
}
