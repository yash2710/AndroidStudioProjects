// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.util;

import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package android.support.v4.util:
//            MapCollections

final class this._cls0
    implements Collection
{

    final MapCollections this$0;

    public final boolean add(Object obj)
    {
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(Collection collection)
    {
        throw new UnsupportedOperationException();
    }

    public final void clear()
    {
        colClear();
    }

    public final boolean contains(Object obj)
    {
        return colIndexOfValue(obj) >= 0;
    }

    public final boolean containsAll(Collection collection)
    {
        for (Iterator iterator1 = collection.iterator(); iterator1.hasNext();)
        {
            if (!contains(iterator1.next()))
            {
                return false;
            }
        }

        return true;
    }

    public final boolean isEmpty()
    {
        return colGetSize() == 0;
    }

    public final Iterator iterator()
    {
        return new it>(MapCollections.this, 1);
    }

    public final boolean remove(Object obj)
    {
        int i = colIndexOfValue(obj);
        if (i >= 0)
        {
            colRemoveAt(i);
            return true;
        } else
        {
            return false;
        }
    }

    public final boolean removeAll(Collection collection)
    {
        int i = 0;
        int j = colGetSize();
        boolean flag = false;
        for (; i < j; i++)
        {
            if (collection.contains(colGetEntry(i, 1)))
            {
                colRemoveAt(i);
                i--;
                j--;
                flag = true;
            }
        }

        return flag;
    }

    public final boolean retainAll(Collection collection)
    {
        int i = 0;
        int j = colGetSize();
        boolean flag = false;
        for (; i < j; i++)
        {
            if (!collection.contains(colGetEntry(i, 1)))
            {
                colRemoveAt(i);
                i--;
                j--;
                flag = true;
            }
        }

        return flag;
    }

    public final int size()
    {
        return colGetSize();
    }

    public final Object[] toArray()
    {
        return toArrayHelper(1);
    }

    public final Object[] toArray(Object aobj[])
    {
        return toArrayHelper(aobj, 1);
    }

    ()
    {
        this$0 = MapCollections.this;
        super();
    }
}
