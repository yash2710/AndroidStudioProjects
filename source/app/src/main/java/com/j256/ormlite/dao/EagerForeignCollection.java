// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;

import com.j256.ormlite.field.FieldType;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.j256.ormlite.dao:
//            BaseForeignCollection, CloseableWrappedIterable, ForeignCollection, Dao, 
//            CloseableIterator

public class EagerForeignCollection extends BaseForeignCollection
    implements CloseableWrappedIterable, ForeignCollection, Serializable
{

    private static final long serialVersionUID = 0xdcfb4fa278aa2727L;
    private List results;

    public EagerForeignCollection(Dao dao, Object obj, Object obj1, FieldType fieldtype, String s, boolean flag)
    {
        super(dao, obj, obj1, fieldtype, s, flag);
        if (obj1 == null)
        {
            results = new ArrayList();
            return;
        } else
        {
            results = dao.query(getPreparedQuery());
            return;
        }
    }

    public boolean add(Object obj)
    {
        if (results.add(obj))
        {
            return super.add(obj);
        } else
        {
            return false;
        }
    }

    public boolean addAll(Collection collection)
    {
        if (results.addAll(collection))
        {
            return super.addAll(collection);
        } else
        {
            return false;
        }
    }

    public void close()
    {
    }

    public void closeLastIterator()
    {
    }

    public CloseableIterator closeableIterator()
    {
        return iteratorThrow(-1);
    }

    public CloseableIterator closeableIterator(int i)
    {
        return iteratorThrow(-1);
    }

    public boolean contains(Object obj)
    {
        return results.contains(obj);
    }

    public boolean containsAll(Collection collection)
    {
        return results.containsAll(collection);
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof EagerForeignCollection))
        {
            return false;
        } else
        {
            EagerForeignCollection eagerforeigncollection = (EagerForeignCollection)obj;
            return results.equals(eagerforeigncollection.results);
        }
    }

    public CloseableWrappedIterable getWrappedIterable()
    {
        return this;
    }

    public CloseableWrappedIterable getWrappedIterable(int i)
    {
        return this;
    }

    public int hashCode()
    {
        return results.hashCode();
    }

    public boolean isEager()
    {
        return true;
    }

    public boolean isEmpty()
    {
        return results.isEmpty();
    }

    public CloseableIterator iterator()
    {
        return iteratorThrow(-1);
    }

    public CloseableIterator iterator(int i)
    {
        return iteratorThrow(i);
    }

    public volatile Iterator iterator()
    {
        return iterator();
    }

    public CloseableIterator iteratorThrow()
    {
        return iteratorThrow(-1);
    }

    public CloseableIterator iteratorThrow(int i)
    {
        return new _cls1();
    }

    public int refreshAll()
    {
        int i = 0;
        for (Iterator iterator1 = results.iterator(); iterator1.hasNext();)
        {
            Object obj = iterator1.next();
            i += dao.refresh(obj);
        }

        return i;
    }

    public int refreshCollection()
    {
        results = dao.query(getPreparedQuery());
        return results.size();
    }

    public boolean remove(Object obj)
    {
        boolean flag = true;
        if (!results.remove(obj) || dao == null)
        {
            flag = false;
        } else
        {
            int i;
            try
            {
                i = dao.delete(obj);
            }
            catch (SQLException sqlexception)
            {
                throw new IllegalStateException("Could not delete data element from dao", sqlexception);
            }
            if (i != flag)
            {
                return false;
            }
        }
        return flag;
    }

    public boolean removeAll(Collection collection)
    {
        boolean flag = false;
        Iterator iterator1 = collection.iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            if (remove(iterator1.next()))
            {
                flag = true;
            }
        } while (true);
        return flag;
    }

    public boolean retainAll(Collection collection)
    {
        return super.retainAll(collection);
    }

    public int size()
    {
        return results.size();
    }

    public Object[] toArray()
    {
        return results.toArray();
    }

    public Object[] toArray(Object aobj[])
    {
        return results.toArray(aobj);
    }

    public int updateAll()
    {
        int i = 0;
        for (Iterator iterator1 = results.iterator(); iterator1.hasNext();)
        {
            Object obj = iterator1.next();
            i += dao.update(obj);
        }

        return i;
    }


    private class _cls1
        implements CloseableIterator
    {

        private int offset;
        final EagerForeignCollection this$0;

        public void close()
        {
        }

        public void closeQuietly()
        {
        }

        public Object current()
        {
            if (offset < 0)
            {
                offset = 0;
            }
            if (offset >= results.size())
            {
                return null;
            } else
            {
                return results.get(offset);
            }
        }

        public Object first()
        {
            offset = 0;
            if (offset >= results.size())
            {
                return null;
            } else
            {
                return results.get(0);
            }
        }

        public DatabaseResults getRawResults()
        {
            return null;
        }

        public boolean hasNext()
        {
            return 1 + offset < results.size();
        }

        public Object moveRelative(int i)
        {
            offset = i + offset;
            if (offset < 0 || offset >= results.size())
            {
                return null;
            } else
            {
                return results.get(offset);
            }
        }

        public void moveToNext()
        {
            offset = 1 + offset;
        }

        public Object next()
        {
            offset = 1 + offset;
            return results.get(offset);
        }

        public Object nextThrow()
        {
            offset = 1 + offset;
            if (offset >= results.size())
            {
                return null;
            } else
            {
                return results.get(offset);
            }
        }

        public Object previous()
        {
            offset = -1 + offset;
            if (offset < 0 || offset >= results.size())
            {
                return null;
            } else
            {
                return results.get(offset);
            }
        }

        public void remove()
        {
            Object obj;
            if (offset < 0)
            {
                throw new IllegalStateException("next() must be called before remove()");
            }
            if (offset >= results.size())
            {
                throw new IllegalStateException((new StringBuilder("current results position (")).append(offset).append(") is out of bounds").toString());
            }
            obj = results.remove(offset);
            offset = -1 + offset;
            if (dao == null)
            {
                break MISSING_BLOCK_LABEL_119;
            }
            dao.delete(obj);
            return;
            SQLException sqlexception;
            sqlexception;
            throw new RuntimeException(sqlexception);
        }

        _cls1()
        {
            this$0 = EagerForeignCollection.this;
            super();
            offset = -1;
        }
    }

}
