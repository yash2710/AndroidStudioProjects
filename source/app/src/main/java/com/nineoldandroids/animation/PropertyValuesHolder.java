// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;

import android.util.Log;
import com.nineoldandroids.util.Property;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Referenced classes of package com.nineoldandroids.animation:
//            IntEvaluator, FloatEvaluator, Keyframe, A, 
//            B, k, g, f, 
//            TypeEvaluator

public class PropertyValuesHolder
    implements Cloneable
{

    private static final TypeEvaluator g = new IntEvaluator();
    private static final TypeEvaluator h = new FloatEvaluator();
    private static Class i[];
    private static Class j[];
    private static Class k[];
    private static final HashMap l = new HashMap();
    private static final HashMap m = new HashMap();
    Method a;
    Class b;
    k c;
    final Object d[];
    private String e;
    private Method f;
    protected Property mProperty;
    private ReentrantReadWriteLock n;
    private TypeEvaluator o;
    private Object p;

    private PropertyValuesHolder(Property property)
    {
        a = null;
        f = null;
        c = null;
        n = new ReentrantReadWriteLock();
        d = new Object[1];
        mProperty = property;
        if (property != null)
        {
            e = property.getName();
        }
    }

    PropertyValuesHolder(Property property, byte byte0)
    {
        this(property);
    }

    private PropertyValuesHolder(String s)
    {
        a = null;
        f = null;
        c = null;
        n = new ReentrantReadWriteLock();
        d = new Object[1];
        e = s;
    }

    PropertyValuesHolder(String s, byte byte0)
    {
        this(s);
    }

    private Method a(Class class1, String s, Class class2)
    {
        Method method;
        method = null;
        String s1 = e;
        Method method3;
        if (s1 != null && s1.length() != 0)
        {
            char c1 = Character.toUpperCase(s1.charAt(0));
            String s2 = s1.substring(1);
            s = (new StringBuilder()).append(s).append(c1).append(s2).toString();
        }
        if (class2 != null) goto _L2; else goto _L1
_L1:
        method3 = class1.getMethod(s, null);
        return method3;
        NoSuchMethodException nosuchmethodexception2;
        nosuchmethodexception2;
        Method method2 = class1.getDeclaredMethod(s, null);
        Method method1 = method2;
        method1.setAccessible(true);
        return method1;
        NoSuchMethodException nosuchmethodexception4;
        nosuchmethodexception4;
_L6:
        Log.e("PropertyValuesHolder", (new StringBuilder("Couldn't find no-arg method for property ")).append(e).append(": ").append(nosuchmethodexception2).toString());
        return method1;
_L2:
        Class aclass[];
        int j1;
        Class class3;
        aclass = new Class[1];
        Class aclass1[];
        int i1;
        if (b.equals(java/lang/Float))
        {
            aclass1 = i;
        } else
        if (b.equals(java/lang/Integer))
        {
            aclass1 = j;
        } else
        if (b.equals(java/lang/Double))
        {
            aclass1 = k;
        } else
        {
            aclass1 = new Class[1];
            aclass1[0] = b;
        }
        i1 = aclass1.length;
        j1 = 0;
_L4:
        if (j1 >= i1)
        {
            break; /* Loop/switch isn't completed */
        }
        class3 = aclass1[j1];
        aclass[0] = class3;
        method = class1.getMethod(s, aclass);
        b = class3;
        return method;
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        method = class1.getDeclaredMethod(s, aclass);
        method.setAccessible(true);
        b = class3;
        return method;
        NoSuchMethodException nosuchmethodexception1;
        nosuchmethodexception1;
        j1++;
        if (true) goto _L4; else goto _L3
_L3:
        Log.e("PropertyValuesHolder", (new StringBuilder("Couldn't find setter/getter for property ")).append(e).append(" with value type ").append(b).toString());
        return method;
        NoSuchMethodException nosuchmethodexception3;
        nosuchmethodexception3;
        method1 = null;
        if (true) goto _L6; else goto _L5
_L5:
    }

    private Method a(Class class1, HashMap hashmap, String s, Class class2)
    {
        HashMap hashmap1;
        n.writeLock().lock();
        hashmap1 = (HashMap)hashmap.get(class1);
        Method method;
        method = null;
        if (hashmap1 == null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        method = (Method)hashmap1.get(e);
        if (method != null)
        {
            break MISSING_BLOCK_LABEL_91;
        }
        method = a(class1, s, class2);
        if (hashmap1 != null)
        {
            break MISSING_BLOCK_LABEL_79;
        }
        hashmap1 = new HashMap();
        hashmap.put(class1, hashmap1);
        hashmap1.put(e, method);
        Method method1 = method;
        n.writeLock().unlock();
        return method1;
        Exception exception;
        exception;
        n.writeLock().unlock();
        throw exception;
    }

    private void a(Object obj, Keyframe keyframe)
    {
        if (mProperty != null)
        {
            keyframe.setValue(mProperty.get(obj));
        }
        try
        {
            if (f == null)
            {
                b(obj.getClass());
            }
            keyframe.setValue(f.invoke(obj, new Object[0]));
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
    }

    private void b(Class class1)
    {
        f = a(class1, m, "get", null);
    }

    public static transient PropertyValuesHolder ofFloat(Property property, float af[])
    {
        return new A(property, af);
    }

    public static transient PropertyValuesHolder ofFloat(String s, float af[])
    {
        return new A(s, af);
    }

    public static transient PropertyValuesHolder ofInt(Property property, int ai[])
    {
        return new B(property, ai);
    }

    public static transient PropertyValuesHolder ofInt(String s, int ai[])
    {
        return new B(s, ai);
    }

    public static transient PropertyValuesHolder ofKeyframe(Property property, Keyframe akeyframe[])
    {
        k k1 = com.nineoldandroids.animation.k.ofKeyframe(akeyframe);
        if (k1 instanceof g)
        {
            return new B(property, (g)k1);
        }
        if (k1 instanceof f)
        {
            return new A(property, (f)k1);
        } else
        {
            PropertyValuesHolder propertyvaluesholder = new PropertyValuesHolder(property);
            propertyvaluesholder.c = k1;
            propertyvaluesholder.b = akeyframe[0].getType();
            return propertyvaluesholder;
        }
    }

    public static transient PropertyValuesHolder ofKeyframe(String s, Keyframe akeyframe[])
    {
        k k1 = com.nineoldandroids.animation.k.ofKeyframe(akeyframe);
        if (k1 instanceof g)
        {
            return new B(s, (g)k1);
        }
        if (k1 instanceof f)
        {
            return new A(s, (f)k1);
        } else
        {
            PropertyValuesHolder propertyvaluesholder = new PropertyValuesHolder(s);
            propertyvaluesholder.c = k1;
            propertyvaluesholder.b = akeyframe[0].getType();
            return propertyvaluesholder;
        }
    }

    public static transient PropertyValuesHolder ofObject(Property property, TypeEvaluator typeevaluator, Object aobj[])
    {
        PropertyValuesHolder propertyvaluesholder = new PropertyValuesHolder(property);
        propertyvaluesholder.setObjectValues(aobj);
        propertyvaluesholder.setEvaluator(typeevaluator);
        return propertyvaluesholder;
    }

    public static transient PropertyValuesHolder ofObject(String s, TypeEvaluator typeevaluator, Object aobj[])
    {
        PropertyValuesHolder propertyvaluesholder = new PropertyValuesHolder(s);
        propertyvaluesholder.setObjectValues(aobj);
        propertyvaluesholder.setEvaluator(typeevaluator);
        return propertyvaluesholder;
    }

    final void a()
    {
        if (o == null)
        {
            TypeEvaluator typeevaluator;
            if (b == java/lang/Integer)
            {
                typeevaluator = g;
            } else
            if (b == java/lang/Float)
            {
                typeevaluator = h;
            } else
            {
                typeevaluator = null;
            }
            o = typeevaluator;
        }
        if (o != null)
        {
            c.setEvaluator(o);
        }
    }

    void a(float f1)
    {
        p = c.getValue(f1);
    }

    void a(Class class1)
    {
        a = a(class1, l, "set", b);
    }

    final void a(Object obj)
    {
label0:
        {
            if (mProperty != null)
            {
                try
                {
                    mProperty.get(obj);
                    Iterator iterator1 = c.c.iterator();
                    do
                    {
                        if (!iterator1.hasNext())
                        {
                            break;
                        }
                        Keyframe keyframe1 = (Keyframe)iterator1.next();
                        if (!keyframe1.hasValue())
                        {
                            keyframe1.setValue(mProperty.get(obj));
                        }
                    } while (true);
                    break label0;
                }
                catch (ClassCastException classcastexception)
                {
                    Log.e("PropertyValuesHolder", (new StringBuilder("No such property (")).append(mProperty.getName()).append(") on target object ").append(obj).append(". Trying reflection instead").toString());
                    mProperty = null;
                }
            }
            Class class1 = obj.getClass();
            if (a == null)
            {
                a(class1);
            }
            Iterator iterator = c.c.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                Keyframe keyframe = (Keyframe)iterator.next();
                if (!keyframe.hasValue())
                {
                    if (f == null)
                    {
                        b(class1);
                    }
                    try
                    {
                        keyframe.setValue(f.invoke(obj, new Object[0]));
                    }
                    catch (InvocationTargetException invocationtargetexception)
                    {
                        Log.e("PropertyValuesHolder", invocationtargetexception.toString());
                    }
                    catch (IllegalAccessException illegalaccessexception)
                    {
                        Log.e("PropertyValuesHolder", illegalaccessexception.toString());
                    }
                }
            } while (true);
        }
    }

    Object b()
    {
        return p;
    }

    final void b(Object obj)
    {
        a(obj, (Keyframe)c.c.get(0));
    }

    final void c(Object obj)
    {
        a(obj, (Keyframe)c.c.get(-1 + c.c.size()));
    }

    public PropertyValuesHolder clone()
    {
        PropertyValuesHolder propertyvaluesholder;
        try
        {
            propertyvaluesholder = (PropertyValuesHolder)super.clone();
            propertyvaluesholder.e = e;
            propertyvaluesholder.mProperty = mProperty;
            propertyvaluesholder.c = c.clone();
            propertyvaluesholder.o = o;
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            return null;
        }
        return propertyvaluesholder;
    }

    public volatile Object clone()
    {
        return clone();
    }

    void d(Object obj)
    {
        if (mProperty != null)
        {
            mProperty.set(obj, b());
        }
        if (a == null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        d[0] = b();
        a.invoke(obj, d);
        return;
        InvocationTargetException invocationtargetexception;
        invocationtargetexception;
        Log.e("PropertyValuesHolder", invocationtargetexception.toString());
        return;
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        Log.e("PropertyValuesHolder", illegalaccessexception.toString());
        return;
    }

    public String getPropertyName()
    {
        return e;
    }

    public void setEvaluator(TypeEvaluator typeevaluator)
    {
        o = typeevaluator;
        c.setEvaluator(typeevaluator);
    }

    public transient void setFloatValues(float af[])
    {
        b = Float.TYPE;
        c = com.nineoldandroids.animation.k.ofFloat(af);
    }

    public transient void setIntValues(int ai[])
    {
        b = Integer.TYPE;
        c = com.nineoldandroids.animation.k.ofInt(ai);
    }

    public transient void setKeyframes(Keyframe akeyframe[])
    {
        int i1 = 0;
        int j1 = akeyframe.length;
        Keyframe akeyframe1[] = new Keyframe[Math.max(j1, 2)];
        b = akeyframe[0].getType();
        for (; i1 < j1; i1++)
        {
            akeyframe1[i1] = akeyframe[i1];
        }

        c = new k(akeyframe1);
    }

    public transient void setObjectValues(Object aobj[])
    {
        b = aobj[0].getClass();
        c = com.nineoldandroids.animation.k.ofObject(aobj);
    }

    public void setProperty(Property property)
    {
        mProperty = property;
    }

    public void setPropertyName(String s)
    {
        e = s;
    }

    public String toString()
    {
        return (new StringBuilder()).append(e).append(": ").append(c.toString()).toString();
    }

    static 
    {
        Class aclass[] = new Class[6];
        aclass[0] = Float.TYPE;
        aclass[1] = java/lang/Float;
        aclass[2] = Double.TYPE;
        aclass[3] = Integer.TYPE;
        aclass[4] = java/lang/Double;
        aclass[5] = java/lang/Integer;
        i = aclass;
        Class aclass1[] = new Class[6];
        aclass1[0] = Integer.TYPE;
        aclass1[1] = java/lang/Integer;
        aclass1[2] = Float.TYPE;
        aclass1[3] = Double.TYPE;
        aclass1[4] = java/lang/Float;
        aclass1[5] = java/lang/Double;
        j = aclass1;
        Class aclass2[] = new Class[6];
        aclass2[0] = Double.TYPE;
        aclass2[1] = java/lang/Double;
        aclass2[2] = Float.TYPE;
        aclass2[3] = Integer.TYPE;
        aclass2[4] = java/lang/Float;
        aclass2[5] = java/lang/Integer;
        k = aclass2;
    }
}
