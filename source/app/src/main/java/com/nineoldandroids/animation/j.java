// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;


// Referenced classes of package com.nineoldandroids.animation:
//            Keyframe

final class j extends Keyframe
{

    private Object d;

    j(float f, Object obj)
    {
        a = f;
        d = obj;
        boolean flag;
        Object obj1;
        if (obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        c = flag;
        if (c)
        {
            obj1 = obj.getClass();
        } else
        {
            obj1 = java/lang/Object;
        }
        b = ((Class) (obj1));
    }

    public final volatile Keyframe clone()
    {
        return clone();
    }

    public final j clone()
    {
        j j1 = new j(getFraction(), d);
        j1.setInterpolator(getInterpolator());
        return j1;
    }

    public final volatile Object clone()
    {
        return clone();
    }

    public final Object getValue()
    {
        return d;
    }

    public final void setValue(Object obj)
    {
        d = obj;
        boolean flag;
        if (obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        c = flag;
    }
}
