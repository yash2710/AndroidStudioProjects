// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;

import android.view.View;
import com.nineoldandroids.util.FloatProperty;
import com.nineoldandroids.view.animation.AnimatorProxy;

final class s extends FloatProperty
{

    s(String s1)
    {
        super(s1);
    }

    public final Float get(View view)
    {
        return Float.valueOf(AnimatorProxy.wrap(view).getPivotX());
    }

    public final volatile Object get(Object obj)
    {
        return get((View)obj);
    }

    public final void setValue(View view, float f)
    {
        AnimatorProxy.wrap(view).setPivotX(f);
    }

    public final volatile void setValue(Object obj, float f)
    {
        setValue((View)obj, f);
    }
}
