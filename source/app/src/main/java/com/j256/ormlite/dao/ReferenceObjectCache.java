// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.j256.ormlite.dao:
//            ObjectCache

public class ReferenceObjectCache
    implements ObjectCache
{

    private final ConcurrentHashMap classMaps = new ConcurrentHashMap();
    private final boolean useWeak;

    public ReferenceObjectCache(boolean flag)
    {
        useWeak = flag;
    }

    private void cleanMap(Map map)
    {
        Iterator iterator = map.entrySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            if (((Reference)((java.util.Map.Entry)iterator.next()).getValue()).get() == null)
            {
                iterator.remove();
            }
        } while (true);
    }

    private Map getMapForClass(Class class1)
    {
        Map map = (Map)classMaps.get(class1);
        if (map == null)
        {
            map = null;
        }
        return map;
    }

    public static ReferenceObjectCache makeSoftCache()
    {
        return new ReferenceObjectCache(false);
    }

    public static ReferenceObjectCache makeWeakCache()
    {
        return new ReferenceObjectCache(true);
    }

    public void cleanNullReferences(Class class1)
    {
        Map map = getMapForClass(class1);
        if (map != null)
        {
            cleanMap(map);
        }
    }

    public void cleanNullReferencesAll()
    {
        for (Iterator iterator = classMaps.values().iterator(); iterator.hasNext(); cleanMap((Map)iterator.next())) { }
    }

    public void clear(Class class1)
    {
        Map map = getMapForClass(class1);
        if (map != null)
        {
            map.clear();
        }
    }

    public void clearAll()
    {
        for (Iterator iterator = classMaps.values().iterator(); iterator.hasNext(); ((Map)iterator.next()).clear()) { }
    }

    public Object get(Class class1, Object obj)
    {
        Map map = getMapForClass(class1);
        Object obj1;
        if (map == null)
        {
            obj1 = null;
        } else
        {
            Reference reference = (Reference)map.get(obj);
            if (reference == null)
            {
                return null;
            }
            obj1 = reference.get();
            if (obj1 == null)
            {
                map.remove(obj);
                return null;
            }
        }
        return obj1;
    }

    public void put(Class class1, Object obj, Object obj1)
    {
        Map map;
label0:
        {
            map = getMapForClass(class1);
            if (map != null)
            {
                if (!useWeak)
                {
                    break label0;
                }
                map.put(obj, new WeakReference(obj1));
            }
            return;
        }
        map.put(obj, new SoftReference(obj1));
    }

    public void registerClass(Class class1)
    {
        this;
        JVM INSTR monitorenter ;
        if ((Map)classMaps.get(class1) == null)
        {
            ConcurrentHashMap concurrenthashmap = new ConcurrentHashMap();
            classMaps.put(class1, concurrenthashmap);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void remove(Class class1, Object obj)
    {
        Map map = getMapForClass(class1);
        if (map != null)
        {
            map.remove(obj);
        }
    }

    public int size(Class class1)
    {
        Map map = getMapForClass(class1);
        if (map == null)
        {
            return 0;
        } else
        {
            return map.size();
        }
    }

    public int sizeAll()
    {
        Iterator iterator = classMaps.values().iterator();
        int i;
        for (i = 0; iterator.hasNext(); i += ((Map)iterator.next()).size()) { }
        return i;
    }

    public Object updateId(Class class1, Object obj, Object obj1)
    {
        Map map = getMapForClass(class1);
        if (map == null)
        {
            return null;
        }
        Reference reference = (Reference)map.remove(obj);
        if (reference == null)
        {
            return null;
        } else
        {
            map.put(obj1, reference);
            return reference.get();
        }
    }
}
