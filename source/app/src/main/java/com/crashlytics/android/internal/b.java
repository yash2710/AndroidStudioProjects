// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

// Referenced classes of package com.crashlytics.android.internal:
//            at, al, am, m, 
//            an, ar, as, f

public class b
{

    public static final String a = "default";
    private final ConcurrentMap b;
    private final ConcurrentMap c;
    private final String d;
    private final m e;
    private final at f;
    private final ThreadLocal g;
    private final ThreadLocal h;
    private final Map i;

    public b()
    {
        this("default");
    }

    public b(m m1)
    {
        this(m1, "default");
    }

    public b(m m1, String s)
    {
        this(m1, s, at.a);
    }

    private b(m m1, String s, at at1)
    {
        b = new ConcurrentHashMap();
        c = new ConcurrentHashMap();
        g = new al();
        h = new am();
        i = new HashMap();
        e = m1;
        d = s;
        f = at1;
    }

    public b(String s)
    {
        this(m.b, s);
    }

    private Set a(Class class1)
    {
        return (Set)b.get(class1);
    }

    private void a()
    {
        if (((Boolean)h.get()).booleanValue())
        {
            return;
        }
        h.set(Boolean.valueOf(true));
_L2:
        an an1 = (an)((ConcurrentLinkedQueue)g.get()).poll();
        if (an1 == null)
        {
            break MISSING_BLOCK_LABEL_87;
        }
        if (!an1.b.a()) goto _L2; else goto _L1
_L1:
        a(an1.a, an1.b);
          goto _L2
        Exception exception;
        exception;
        h.set(Boolean.valueOf(false));
        throw exception;
        h.set(Boolean.valueOf(false));
        return;
    }

    private static void a(ar ar1, as as1)
    {
        Object obj1 = as1.c();
        Object obj = obj1;
_L1:
        InvocationTargetException invocationtargetexception;
        if (obj == null)
        {
            return;
        } else
        {
            a(obj, ar1);
            return;
        }
        invocationtargetexception;
        a((new StringBuilder("Producer ")).append(as1).append(" threw an exception.").toString(), invocationtargetexception);
        obj = null;
          goto _L1
    }

    private static void a(Object obj, ar ar1)
    {
        try
        {
            ar1.a(obj);
            return;
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            a((new StringBuilder("Could not dispatch event: ")).append(obj.getClass()).append(" to handler ").append(ar1).toString(), invocationtargetexception);
        }
    }

    private static void a(String s, InvocationTargetException invocationtargetexception)
    {
        Throwable throwable = invocationtargetexception.getCause();
        if (throwable != null)
        {
            throw new RuntimeException(s, throwable);
        } else
        {
            throw new RuntimeException(s);
        }
    }

    public void a(Object obj)
    {
        Iterator iterator2;
        e.a(this);
        Map map = f.a(obj);
        Iterator iterator = map.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Class class3 = (Class)iterator.next();
            as as2 = (as)map.get(class3);
            as as3 = (as)c.putIfAbsent(class3, as2);
            if (as3 != null)
            {
                throw new IllegalArgumentException((new StringBuilder("Producer method for type ")).append(class3).append(" found on type ").append(as2.a.getClass()).append(", but already registered by type ").append(as3.a.getClass()).append(".").toString());
            }
            Set set = (Set)b.get(class3);
            if (set != null && !set.isEmpty())
            {
                Iterator iterator4 = set.iterator();
                while (iterator4.hasNext()) 
                {
                    a((ar)iterator4.next(), as2);
                }
            }
        } while (true);
        Map map1 = f.b(obj);
        Class class2;
        Object obj1;
        for (Iterator iterator1 = map1.keySet().iterator(); iterator1.hasNext(); ((Set) (obj1)).addAll((Set)map1.get(class2)))
        {
            class2 = (Class)iterator1.next();
            obj1 = (Set)b.get(class2);
            if (obj1 != null)
            {
                continue;
            }
            CopyOnWriteArraySet copyonwritearrayset = new CopyOnWriteArraySet();
            obj1 = (Set)b.putIfAbsent(class2, copyonwritearrayset);
            if (obj1 == null)
            {
                obj1 = copyonwritearrayset;
            }
        }

        iterator2 = map1.entrySet().iterator();
_L2:
        as as1;
        Iterator iterator3;
        java.util.Map.Entry entry;
        do
        {
            if (!iterator2.hasNext())
            {
                break MISSING_BLOCK_LABEL_489;
            }
            entry = (java.util.Map.Entry)iterator2.next();
            Class class1 = (Class)entry.getKey();
            as1 = (as)c.get(class1);
        } while (as1 == null || !as1.a());
        iterator3 = ((Set)entry.getValue()).iterator();
_L4:
        if (!iterator3.hasNext()) goto _L2; else goto _L1
_L1:
        ar ar1 = (ar)iterator3.next();
        if (!as1.a()) goto _L2; else goto _L3
_L3:
        if (ar1.a())
        {
            a(ar1, as1);
        }
          goto _L4
    }

    public void b(Object obj)
    {
        e.a(this);
        Class class1;
        for (Iterator iterator = f.a(obj).entrySet().iterator(); iterator.hasNext(); ((as)c.remove(class1)).b())
        {
            java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator.next();
            class1 = (Class)entry1.getKey();
            as as1 = (as)c.get(class1);
            as as2 = (as)entry1.getValue();
            if (as2 == null || !as2.equals(as1))
            {
                throw new IllegalArgumentException((new StringBuilder("Missing event producer for an annotated method. Is ")).append(obj.getClass()).append(" registered?").toString());
            }
        }

        Set set;
        Collection collection;
label0:
        for (Iterator iterator1 = f.b(obj).entrySet().iterator(); iterator1.hasNext(); set.removeAll(collection))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
            set = a((Class)entry.getKey());
            collection = (Collection)entry.getValue();
            if (set == null || !set.containsAll(collection))
            {
                throw new IllegalArgumentException((new StringBuilder("Missing event handler for an annotated method. Is ")).append(obj.getClass()).append(" registered?").toString());
            }
            Iterator iterator2 = set.iterator();
            do
            {
                if (!iterator2.hasNext())
                {
                    continue label0;
                }
                ar ar1 = (ar)iterator2.next();
                if (collection.contains(ar1))
                {
                    ar1.b();
                }
            } while (true);
        }

    }

    public void c(Object obj)
    {
        Iterator iterator;
        boolean flag;
        e.a(this);
        Class class1 = obj.getClass();
        Object obj1 = (Set)i.get(class1);
        if (obj1 == null)
        {
            LinkedList linkedlist = new LinkedList();
            HashSet hashset = new HashSet();
            linkedlist.add(class1);
            do
            {
                if (linkedlist.isEmpty())
                {
                    break;
                }
                Class class2 = (Class)linkedlist.remove(0);
                hashset.add(class2);
                Class class3 = class2.getSuperclass();
                if (class3 != null)
                {
                    linkedlist.add(class3);
                }
            } while (true);
            i.put(class1, hashset);
            obj1 = hashset;
        }
        iterator = ((Set) (obj1)).iterator();
        flag = false;
_L2:
        boolean flag1;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        Set set = a((Class)iterator.next());
        if (set != null && !set.isEmpty())
        {
            ar ar1;
            for (Iterator iterator1 = set.iterator(); iterator1.hasNext(); ((ConcurrentLinkedQueue)g.get()).offer(new an(obj, ar1)))
            {
                ar1 = (ar)iterator1.next();
            }

            break MISSING_BLOCK_LABEL_285;
        }
        flag1 = flag;
_L3:
        flag = flag1;
        if (true) goto _L2; else goto _L1
_L1:
        if (!flag && !(obj instanceof f))
        {
            c(new f(this, obj));
        }
        a();
        return;
        flag1 = true;
          goto _L3
    }

    public String toString()
    {
        return (new StringBuilder("[Bus \"")).append(d).append("\"]").toString();
    }
}
