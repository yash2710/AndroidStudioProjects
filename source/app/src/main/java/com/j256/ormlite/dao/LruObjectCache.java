// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.j256.ormlite.dao:
//            ObjectCache

public class LruObjectCache
    implements ObjectCache
{

    private final int capacity;
    private final ConcurrentHashMap classMaps = new ConcurrentHashMap();

    public LruObjectCache(int i)
    {
        capacity = i;
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
        if (map == null)
        {
            return null;
        } else
        {
            return map.get(obj);
        }
    }

    public void put(Class class1, Object obj, Object obj1)
    {
        Map map = getMapForClass(class1);
        if (map != null)
        {
            map.put(obj, obj1);
        }
    }

    public void registerClass(Class class1)
    {
        this;
        JVM INSTR monitorenter ;
        if ((Map)classMaps.get(class1) == null)
        {
            Map map = Collections.synchronizedMap(new LimitedLinkedHashMap(capacity));
            classMaps.put(class1, map);
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
        Object obj2;
        if (map != null)
        {
            if ((obj2 = map.remove(obj)) != null)
            {
                map.put(obj1, obj2);
                return obj2;
            }
        }
        return null;
    }

    private class LimitedLinkedHashMap extends LinkedHashMap
    {

        private static final long serialVersionUID = 0xc0a06ee7c72ce80cL;
        private final int capacity;

        protected boolean removeEldestEntry(java.util.Map.Entry entry)
        {
            return size() > capacity;
        }

        public LimitedLinkedHashMap(int i)
        {
            super(i, 0.75F, true);
            capacity = i;
        }
    }

}
