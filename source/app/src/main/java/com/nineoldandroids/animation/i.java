// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;


// Referenced classes of package com.nineoldandroids.animation:
//            Keyframe

final class i extends Keyframe
{

    private int d;

    i(float f)
    {
        a = f;
        b = Integer.TYPE;
    }

    i(float f, int j)
    {
        a = f;
        d = j;
        b = Integer.TYPE;
        c = true;
    }

    public final volatile Keyframe clone()
    {
        return clone();
    }

    public final i clone()
    {
        i j = new i(getFraction(), d);
        j.setInterpolator(getInterpolator());
        return j;
    }

    public final volatile Object clone()
    {
        return clone();
    }

    public final int getIntValue()
    {
        return d;
    }

    public final Object getValue()
    {
        return Integer.valueOf(d);
    }

    public final void setValue(Object obj)
    {
        if (obj != null && obj.getClass() == java/lang/Integer)
        {
            d = ((Integer)obj).intValue();
            c = true;
        }
    }
}
