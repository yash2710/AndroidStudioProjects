// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;

import android.view.View;
import com.nineoldandroids.util.IntProperty;
import com.nineoldandroids.view.animation.AnimatorProxy;

final class p extends IntProperty
{

    p(String s)
    {
        super(s);
    }

    public final Integer get(View view)
    {
        return Integer.valueOf(AnimatorProxy.wrap(view).getScrollY());
    }

    public final volatile Object get(Object obj)
    {
        return get((View)obj);
    }

    public final void setValue(View view, int i)
    {
        AnimatorProxy.wrap(view).setScrollY(i);
    }

    public final volatile void setValue(Object obj, int i)
    {
        setValue((View)obj, i);
    }
}
