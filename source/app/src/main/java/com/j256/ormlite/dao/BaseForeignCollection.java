// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.stmt.mapped.MappedPreparedStmt;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.j256.ormlite.dao:
//            ForeignCollection, Dao, CloseableIterator

public abstract class BaseForeignCollection
    implements ForeignCollection, Serializable
{

    private static final long serialVersionUID = 0xb8681d892ada296bL;
    protected final transient Dao dao;
    private final transient FieldType foreignFieldType;
    private final transient boolean orderAscending;
    private final transient String orderColumn;
    private final transient Object parent;
    private final transient Object parentId;
    private transient PreparedQuery preparedQuery;

    protected BaseForeignCollection(Dao dao1, Object obj, Object obj1, FieldType fieldtype, String s, boolean flag)
    {
        dao = dao1;
        foreignFieldType = fieldtype;
        parentId = obj1;
        orderColumn = s;
        orderAscending = flag;
        parent = obj;
    }

    private boolean addElement(Object obj)
    {
        if (dao == null)
        {
            return false;
        }
        if (parent != null && foreignFieldType.getFieldValueIfNotDefault(obj) == null)
        {
            foreignFieldType.assignField(obj, parent, true, null);
        }
        dao.create(obj);
        return true;
    }

    public boolean add(Object obj)
    {
        boolean flag;
        try
        {
            flag = addElement(obj);
        }
        catch (SQLException sqlexception)
        {
            throw new IllegalStateException("Could not create data element in dao", sqlexception);
        }
        return flag;
    }

    public boolean addAll(Collection collection)
    {
        boolean flag = false;
        Iterator iterator = collection.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj = iterator.next();
            boolean flag1;
            try
            {
                flag1 = addElement(obj);
            }
            catch (SQLException sqlexception)
            {
                throw new IllegalStateException("Could not create data elements in dao", sqlexception);
            }
            if (flag1)
            {
                flag = true;
            }
        } while (true);
        return flag;
    }

    public void clear()
    {
        CloseableIterator closeableiterator;
        if (dao == null)
        {
            return;
        }
        closeableiterator = closeableIterator();
        while (closeableiterator.hasNext()) 
        {
            closeableiterator.next();
            closeableiterator.remove();
        }
        break MISSING_BLOCK_LABEL_47;
        Exception exception;
        exception;
        SQLException sqlexception1;
        try
        {
            closeableiterator.close();
        }
        catch (SQLException sqlexception) { }
        throw exception;
        try
        {
            closeableiterator.close();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (SQLException sqlexception1)
        {
            return;
        }
    }

    protected PreparedQuery getPreparedQuery()
    {
        if (dao == null)
        {
            return null;
        }
        if (preparedQuery == null)
        {
            SelectArg selectarg = new SelectArg();
            selectarg.setValue(parentId);
            QueryBuilder querybuilder = dao.queryBuilder();
            if (orderColumn != null)
            {
                querybuilder.orderBy(orderColumn, orderAscending);
            }
            preparedQuery = querybuilder.where().eq(foreignFieldType.getColumnName(), selectarg).prepare();
            if (preparedQuery instanceof MappedPreparedStmt)
            {
                ((MappedPreparedStmt)preparedQuery).setParentInformation(parent, parentId);
            }
        }
        return preparedQuery;
    }

    public int refresh(Object obj)
    {
        if (dao == null)
        {
            return 0;
        } else
        {
            return dao.refresh(obj);
        }
    }

    public abstract boolean remove(Object obj);

    public abstract boolean removeAll(Collection collection);

    public boolean retainAll(Collection collection)
    {
        boolean flag;
        CloseableIterator closeableiterator;
        flag = false;
        if (dao == null)
        {
            return false;
        }
        closeableiterator = closeableIterator();
_L2:
        if (!closeableiterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        if (collection.contains(closeableiterator.next()))
        {
            continue; /* Loop/switch isn't completed */
        }
        closeableiterator.remove();
        flag = true;
        if (true) goto _L2; else goto _L1
_L1:
        try
        {
            closeableiterator.close();
        }
        catch (SQLException sqlexception1)
        {
            return flag;
        }
        return flag;
        Exception exception;
        exception;
        try
        {
            closeableiterator.close();
        }
        catch (SQLException sqlexception) { }
        throw exception;
    }

    public int update(Object obj)
    {
        if (dao == null)
        {
            return 0;
        } else
        {
            return dao.update(obj);
        }
    }
}
