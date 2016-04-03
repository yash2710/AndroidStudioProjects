// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Referenced classes of package com.newrelic.com.google.gson.internal:
//            z, A, B, C

public abstract class UnsafeAllocator
{

    public UnsafeAllocator()
    {
    }

    public static UnsafeAllocator create()
    {
        z z1;
        try
        {
            Class class1 = Class.forName("sun.misc.Unsafe");
            Field field = class1.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Object obj = field.get(null);
            z1 = new z(class1.getMethod("allocateInstance", new Class[] {
                java/lang/Class
            }), obj);
        }
        catch (Exception exception)
        {
            A a;
            try
            {
                Method method2 = java/io/ObjectInputStream.getDeclaredMethod("newInstance", new Class[] {
                    java/lang/Class, java/lang/Class
                });
                method2.setAccessible(true);
                a = new A(method2);
            }
            catch (Exception exception1)
            {
                B b;
                try
                {
                    Method method = java/io/ObjectStreamClass.getDeclaredMethod("getConstructorId", new Class[] {
                        java/lang/Class
                    });
                    method.setAccessible(true);
                    int i = ((Integer)method.invoke(null, new Object[] {
                        java/lang/Object
                    })).intValue();
                    Class aclass[] = new Class[2];
                    aclass[0] = java/lang/Class;
                    aclass[1] = Integer.TYPE;
                    Method method1 = java/io/ObjectStreamClass.getDeclaredMethod("newInstance", aclass);
                    method1.setAccessible(true);
                    b = new B(method1, i);
                }
                catch (Exception exception2)
                {
                    return new C();
                }
                return b;
            }
            return a;
        }
        return z1;
    }

    public abstract Object newInstance(Class class1);
}
