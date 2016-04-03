// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;

import java.util.Vector;

public abstract class j
{

    protected String a;
    protected Vector b;

    public j()
    {
    }

    public final void a(Object obj)
    {
        try
        {
            b.add(obj);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public final void a(Vector vector)
    {
        try
        {
            b.addAll(vector);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public final Vector e()
    {
        return b;
    }

    public final void f()
    {
        try
        {
            b.clear();
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }
}
