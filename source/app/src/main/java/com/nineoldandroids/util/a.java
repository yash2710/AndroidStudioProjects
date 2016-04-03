// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package com.nineoldandroids.util:
//            Property, NoSuchPropertyException

final class a extends Property
{

    private Method a;
    private Method b;
    private Field c;

    public a(Class class1, Class class2, String s)
    {
        Class class4;
        String s5;
        super(class2, s);
        char c1 = Character.toUpperCase(s.charAt(0));
        String s1 = s.substring(1);
        String s2 = (new StringBuilder()).append(c1).append(s1).toString();
        String s3 = (new StringBuilder("get")).append(s2).toString();
        try
        {
            b = class1.getMethod(s3, null);
        }
        catch (NoSuchMethodException nosuchmethodexception)
        {
            try
            {
                b = class1.getDeclaredMethod(s3, null);
                b.setAccessible(true);
            }
            catch (NoSuchMethodException nosuchmethodexception1)
            {
                String s4 = (new StringBuilder("is")).append(s2).toString();
                try
                {
                    b = class1.getMethod(s4, null);
                }
                catch (NoSuchMethodException nosuchmethodexception2)
                {
                    try
                    {
                        b = class1.getDeclaredMethod(s4, null);
                        b.setAccessible(true);
                    }
                    catch (NoSuchMethodException nosuchmethodexception3)
                    {
                        try
                        {
                            c = class1.getField(s);
                            Class class3 = c.getType();
                            if (!a(class2, class3))
                            {
                                throw new NoSuchPropertyException((new StringBuilder("Underlying type (")).append(class3).append(") does not match Property type (").append(class2).append(")").toString());
                            }
                        }
                        catch (NoSuchFieldException nosuchfieldexception)
                        {
                            throw new NoSuchPropertyException((new StringBuilder("No accessor method or field found for property with name ")).append(s).toString());
                        }
                        break MISSING_BLOCK_LABEL_355;
                    }
                }
            }
        }
        class4 = b.getReturnType();
        if (!a(class2, class4))
        {
            throw new NoSuchPropertyException((new StringBuilder("Underlying type (")).append(class4).append(") does not match Property type (").append(class2).append(")").toString());
        }
        s5 = (new StringBuilder("set")).append(s2).toString();
        a = class1.getDeclaredMethod(s5, new Class[] {
            class4
        });
        a.setAccessible(true);
        return;
        NoSuchMethodException nosuchmethodexception4;
        nosuchmethodexception4;
    }

    private static boolean a(Class class1, Class class2)
    {
label0:
        {
            boolean flag1;
label1:
            {
                if (class2 == class1)
                {
                    break label0;
                }
                boolean flag = class2.isPrimitive();
                flag1 = false;
                if (!flag)
                {
                    break label1;
                }
                if ((class2 != Float.TYPE || class1 != java/lang/Float) && (class2 != Integer.TYPE || class1 != java/lang/Integer) && (class2 != Boolean.TYPE || class1 != java/lang/Boolean) && (class2 != Long.TYPE || class1 != java/lang/Long) && (class2 != Double.TYPE || class1 != java/lang/Double) && (class2 != Short.TYPE || class1 != java/lang/Short) && (class2 != Byte.TYPE || class1 != java/lang/Byte))
                {
                    Class class3 = Character.TYPE;
                    flag1 = false;
                    if (class2 != class3)
                    {
                        break label1;
                    }
                    flag1 = false;
                    if (class1 != java/lang/Character)
                    {
                        break label1;
                    }
                }
                flag1 = true;
            }
            return flag1;
        }
        return true;
    }

    public final Object get(Object obj)
    {
        if (b != null)
        {
            Object obj2;
            try
            {
                obj2 = b.invoke(obj, null);
            }
            catch (IllegalAccessException illegalaccessexception1)
            {
                throw new AssertionError();
            }
            catch (InvocationTargetException invocationtargetexception)
            {
                throw new RuntimeException(invocationtargetexception.getCause());
            }
            return obj2;
        }
        if (c != null)
        {
            Object obj1;
            try
            {
                obj1 = c.get(obj);
            }
            catch (IllegalAccessException illegalaccessexception)
            {
                throw new AssertionError();
            }
            return obj1;
        } else
        {
            throw new AssertionError();
        }
    }

    public final boolean isReadOnly()
    {
        return a == null && c == null;
    }

    public final void set(Object obj, Object obj1)
    {
        if (a != null)
        {
            try
            {
                a.invoke(obj, new Object[] {
                    obj1
                });
                return;
            }
            catch (IllegalAccessException illegalaccessexception1)
            {
                throw new AssertionError();
            }
            catch (InvocationTargetException invocationtargetexception)
            {
                throw new RuntimeException(invocationtargetexception.getCause());
            }
        }
        if (c != null)
        {
            try
            {
                c.set(obj, obj1);
                return;
            }
            catch (IllegalAccessException illegalaccessexception)
            {
                throw new AssertionError();
            }
        } else
        {
            throw new UnsupportedOperationException((new StringBuilder("Property ")).append(getName()).append(" is read-only").toString());
        }
    }
}
