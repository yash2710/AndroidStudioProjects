// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;

import android.view.View;
import com.nineoldandroids.util.Property;
import com.nineoldandroids.view.animation.AnimatorProxy;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.nineoldandroids.animation:
//            ValueAnimator, l, PropertyValuesHolder, TypeEvaluator, 
//            Animator

public final class ObjectAnimator extends ValueAnimator
{

    private static final Map h;
    private Object i;
    private String j;
    private Property k;

    public ObjectAnimator()
    {
    }

    private ObjectAnimator(Object obj, Property property)
    {
        i = obj;
        setProperty(property);
    }

    private ObjectAnimator(Object obj, String s)
    {
        i = obj;
        setPropertyName(s);
    }

    public static transient ObjectAnimator ofFloat(Object obj, Property property, float af[])
    {
        ObjectAnimator objectanimator = new ObjectAnimator(obj, property);
        objectanimator.setFloatValues(af);
        return objectanimator;
    }

    public static transient ObjectAnimator ofFloat(Object obj, String s, float af[])
    {
        ObjectAnimator objectanimator = new ObjectAnimator(obj, s);
        objectanimator.setFloatValues(af);
        return objectanimator;
    }

    public static transient ObjectAnimator ofInt(Object obj, Property property, int ai[])
    {
        ObjectAnimator objectanimator = new ObjectAnimator(obj, property);
        objectanimator.setIntValues(ai);
        return objectanimator;
    }

    public static transient ObjectAnimator ofInt(Object obj, String s, int ai[])
    {
        ObjectAnimator objectanimator = new ObjectAnimator(obj, s);
        objectanimator.setIntValues(ai);
        return objectanimator;
    }

    public static transient ObjectAnimator ofObject(Object obj, Property property, TypeEvaluator typeevaluator, Object aobj[])
    {
        ObjectAnimator objectanimator = new ObjectAnimator(obj, property);
        objectanimator.setObjectValues(aobj);
        objectanimator.setEvaluator(typeevaluator);
        return objectanimator;
    }

    public static transient ObjectAnimator ofObject(Object obj, String s, TypeEvaluator typeevaluator, Object aobj[])
    {
        ObjectAnimator objectanimator = new ObjectAnimator(obj, s);
        objectanimator.setObjectValues(aobj);
        objectanimator.setEvaluator(typeevaluator);
        return objectanimator;
    }

    public static transient ObjectAnimator ofPropertyValuesHolder(Object obj, PropertyValuesHolder apropertyvaluesholder[])
    {
        ObjectAnimator objectanimator = new ObjectAnimator();
        objectanimator.i = obj;
        objectanimator.setValues(apropertyvaluesholder);
        return objectanimator;
    }

    final void a()
    {
        if (!e)
        {
            if (k == null && AnimatorProxy.NEEDS_PROXY && (i instanceof View) && h.containsKey(j))
            {
                setProperty((Property)h.get(j));
            }
            int i1 = f.length;
            for (int j1 = 0; j1 < i1; j1++)
            {
                f[j1].a(i);
            }

            super.a();
        }
    }

    final void a(float f)
    {
        super.a(f);
        int i1 = this.f.length;
        for (int j1 = 0; j1 < i1; j1++)
        {
            this.f[j1].d(i);
        }

    }

    public final volatile Animator clone()
    {
        return clone();
    }

    public final ObjectAnimator clone()
    {
        return (ObjectAnimator)super.clone();
    }

    public final volatile ValueAnimator clone()
    {
        return clone();
    }

    public final volatile Object clone()
    {
        return clone();
    }

    public final String getPropertyName()
    {
        return j;
    }

    public final Object getTarget()
    {
        return i;
    }

    public final volatile Animator setDuration(long l1)
    {
        return setDuration(l1);
    }

    public final ObjectAnimator setDuration(long l1)
    {
        super.setDuration(l1);
        return this;
    }

    public final volatile ValueAnimator setDuration(long l1)
    {
        return setDuration(l1);
    }

    public final transient void setFloatValues(float af[])
    {
        if (f == null || f.length == 0)
        {
            if (k != null)
            {
                PropertyValuesHolder apropertyvaluesholder1[] = new PropertyValuesHolder[1];
                apropertyvaluesholder1[0] = PropertyValuesHolder.ofFloat(k, af);
                setValues(apropertyvaluesholder1);
                return;
            } else
            {
                PropertyValuesHolder apropertyvaluesholder[] = new PropertyValuesHolder[1];
                apropertyvaluesholder[0] = PropertyValuesHolder.ofFloat(j, af);
                setValues(apropertyvaluesholder);
                return;
            }
        } else
        {
            super.setFloatValues(af);
            return;
        }
    }

    public final transient void setIntValues(int ai[])
    {
        if (f == null || f.length == 0)
        {
            if (k != null)
            {
                PropertyValuesHolder apropertyvaluesholder1[] = new PropertyValuesHolder[1];
                apropertyvaluesholder1[0] = PropertyValuesHolder.ofInt(k, ai);
                setValues(apropertyvaluesholder1);
                return;
            } else
            {
                PropertyValuesHolder apropertyvaluesholder[] = new PropertyValuesHolder[1];
                apropertyvaluesholder[0] = PropertyValuesHolder.ofInt(j, ai);
                setValues(apropertyvaluesholder);
                return;
            }
        } else
        {
            super.setIntValues(ai);
            return;
        }
    }

    public final transient void setObjectValues(Object aobj[])
    {
        if (f == null || f.length == 0)
        {
            if (k != null)
            {
                PropertyValuesHolder apropertyvaluesholder1[] = new PropertyValuesHolder[1];
                apropertyvaluesholder1[0] = PropertyValuesHolder.ofObject(k, null, aobj);
                setValues(apropertyvaluesholder1);
                return;
            } else
            {
                PropertyValuesHolder apropertyvaluesholder[] = new PropertyValuesHolder[1];
                apropertyvaluesholder[0] = PropertyValuesHolder.ofObject(j, null, aobj);
                setValues(apropertyvaluesholder);
                return;
            }
        } else
        {
            super.setObjectValues(aobj);
            return;
        }
    }

    public final void setProperty(Property property)
    {
        if (f != null)
        {
            PropertyValuesHolder propertyvaluesholder = f[0];
            String s = propertyvaluesholder.getPropertyName();
            propertyvaluesholder.setProperty(property);
            g.remove(s);
            g.put(j, propertyvaluesholder);
        }
        if (k != null)
        {
            j = property.getName();
        }
        k = property;
        e = false;
    }

    public final void setPropertyName(String s)
    {
        if (f != null)
        {
            PropertyValuesHolder propertyvaluesholder = f[0];
            String s1 = propertyvaluesholder.getPropertyName();
            propertyvaluesholder.setPropertyName(s);
            g.remove(s1);
            g.put(s, propertyvaluesholder);
        }
        j = s;
        e = false;
    }

    public final void setTarget(Object obj)
    {
label0:
        {
            if (i != obj)
            {
                Object obj1 = i;
                i = obj;
                if (obj1 == null || obj == null || obj1.getClass() != obj.getClass())
                {
                    break label0;
                }
            }
            return;
        }
        e = false;
    }

    public final void setupEndValues()
    {
        a();
        int i1 = f.length;
        for (int j1 = 0; j1 < i1; j1++)
        {
            f[j1].c(i);
        }

    }

    public final void setupStartValues()
    {
        a();
        int i1 = f.length;
        for (int j1 = 0; j1 < i1; j1++)
        {
            f[j1].b(i);
        }

    }

    public final void start()
    {
        super.start();
    }

    public final String toString()
    {
        String s = (new StringBuilder("ObjectAnimator@")).append(Integer.toHexString(hashCode())).append(", target ").append(i).toString();
        if (f != null)
        {
            for (int i1 = 0; i1 < f.length; i1++)
            {
                s = (new StringBuilder()).append(s).append("\n    ").append(f[i1].toString()).toString();
            }

        }
        return s;
    }

    static 
    {
        HashMap hashmap = new HashMap();
        h = hashmap;
        hashmap.put("alpha", l.a);
        h.put("pivotX", l.b);
        h.put("pivotY", l.c);
        h.put("translationX", l.d);
        h.put("translationY", l.e);
        h.put("rotation", l.f);
        h.put("rotationX", l.g);
        h.put("rotationY", l.h);
        h.put("scaleX", l.i);
        h.put("scaleY", l.j);
        h.put("scrollX", l.k);
        h.put("scrollY", l.l);
        h.put("x", l.m);
        h.put("y", l.n);
    }
}
