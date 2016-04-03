// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Referenced classes of package com.google.gson.internal:
//            I, J, K, L

public abstract class UnsafeAllocator
{

    public UnsafeAllocator()
    {
    }

    public static UnsafeAllocator create()
    {
        I l;
        try
        {
            Class class1 = Class.forName("sun.misc.Unsafe");
            Field field = class1.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Object obj = field.get(null);
            l = new I(class1.getMethod("allocateInstance", new Class[] {
                java/lang/Class
            }), obj);
        }
        catch (Exception exception)
        {
            J j;
            try
            {
                Method method1 = java/io/ObjectStreamClass.getDeclaredMethod("getConstructorId", new Class[] {
                    java/lang/Class
                });
                method1.setAccessible(true);
                int i = ((Integer)method1.invoke(null, new Object[] {
                    java/lang/Object
                })).intValue();
                Class aclass[] = new Class[2];
                aclass[0] = java/lang/Class;
                aclass[1] = Integer.TYPE;
                Method method2 = java/io/ObjectStreamClass.getDeclaredMethod("newInstance", aclass);
                method2.setAccessible(true);
                j = new J(method2, i);
            }
            catch (Exception exception1)
            {
                K k;
                try
                {
                    Method method = java/io/ObjectInputStream.getDeclaredMethod("newInstance", new Class[] {
                        java/lang/Class, java/lang/Class
                    });
                    method.setAccessible(true);
                    k = new K(method);
                }
                catch (Exception exception2)
                {
                    return new L();
                }
                return k;
            }
            return j;
        }
        return l;
    }

    public abstract Object newInstance(Class class1);
}
