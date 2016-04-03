// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;

import android.util.Log;
import com.nineoldandroids.util.FloatProperty;
import com.nineoldandroids.util.Property;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package com.nineoldandroids.animation:
//            PropertyValuesHolder, f

final class A extends PropertyValuesHolder
{

    private FloatProperty e;
    private f f;
    private float g;

    public A(Property property, f f1)
    {
        super(property, (byte)0);
        b = Float.TYPE;
        c = f1;
        f = (f)c;
        if (property instanceof FloatProperty)
        {
            e = (FloatProperty)mProperty;
        }
    }

    public transient A(Property property, float af[])
    {
        super(property, (byte)0);
        setFloatValues(af);
        if (property instanceof FloatProperty)
        {
            e = (FloatProperty)mProperty;
        }
    }

    public A(String s, f f1)
    {
        super(s, (byte)0);
        b = Float.TYPE;
        c = f1;
        f = (f)c;
    }

    public transient A(String s, float af[])
    {
        super(s, (byte)0);
        setFloatValues(af);
    }

    final void a(float f1)
    {
        g = f.getFloatValue(f1);
    }

    final void a(Class class1)
    {
        if (mProperty != null)
        {
            return;
        } else
        {
            super.a(class1);
            return;
        }
    }

    final Object b()
    {
        return Float.valueOf(g);
    }

    public final A clone()
    {
        A a1 = (A)super.clone();
        a1.f = (f)a1.c;
        return a1;
    }

    public final volatile PropertyValuesHolder clone()
    {
        return clone();
    }

    public final volatile Object clone()
    {
        return clone();
    }

    final void d(Object obj)
    {
        if (e != null)
        {
            e.setValue(obj, g);
        } else
        {
            if (mProperty != null)
            {
                mProperty.set(obj, Float.valueOf(g));
                return;
            }
            if (a != null)
            {
                try
                {
                    d[0] = Float.valueOf(g);
                    a.invoke(obj, d);
                    return;
                }
                catch (InvocationTargetException invocationtargetexception)
                {
                    Log.e("PropertyValuesHolder", invocationtargetexception.toString());
                    return;
                }
                catch (IllegalAccessException illegalaccessexception)
                {
                    Log.e("PropertyValuesHolder", illegalaccessexception.toString());
                }
                return;
            }
        }
    }

    public final transient void setFloatValues(float af[])
    {
        super.setFloatValues(af);
        f = (f)c;
    }
}
