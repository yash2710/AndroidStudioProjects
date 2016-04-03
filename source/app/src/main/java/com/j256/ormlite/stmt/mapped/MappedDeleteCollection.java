// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.j256.ormlite.stmt.mapped:
//            BaseMappedStatement

public class MappedDeleteCollection extends BaseMappedStatement
{

    private MappedDeleteCollection(TableInfo tableinfo, String s, FieldType afieldtype[])
    {
        super(tableinfo, s, afieldtype);
    }

    private static void appendWhereIds(DatabaseType databasetype, FieldType fieldtype, StringBuilder stringbuilder, int i, FieldType afieldtype[])
    {
        stringbuilder.append("WHERE ");
        databasetype.appendEscapedEntityName(stringbuilder, fieldtype.getColumnName());
        stringbuilder.append(" IN (");
        boolean flag = true;
        int j = 0;
        while (j < i) 
        {
            if (flag)
            {
                flag = false;
            } else
            {
                stringbuilder.append(',');
            }
            stringbuilder.append('?');
            if (afieldtype != null)
            {
                afieldtype[j] = fieldtype;
            }
            j++;
        }
        stringbuilder.append(") ");
    }

    private static MappedDeleteCollection build(DatabaseType databasetype, TableInfo tableinfo, int i)
    {
        FieldType fieldtype = tableinfo.getIdField();
        if (fieldtype == null)
        {
            throw new SQLException((new StringBuilder("Cannot delete ")).append(tableinfo.getDataClass()).append(" because it doesn't have an id field defined").toString());
        } else
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            appendTableName(databasetype, stringbuilder, "DELETE FROM ", tableinfo.getTableName());
            FieldType afieldtype[] = new FieldType[i];
            appendWhereIds(databasetype, fieldtype, stringbuilder, i, afieldtype);
            return new MappedDeleteCollection(tableinfo, stringbuilder.toString(), afieldtype);
        }
    }

    public static int deleteIds(DatabaseType databasetype, TableInfo tableinfo, DatabaseConnection databaseconnection, Collection collection, ObjectCache objectcache)
    {
        MappedDeleteCollection mappeddeletecollection = build(databasetype, tableinfo, collection.size());
        Object aobj[] = new Object[collection.size()];
        FieldType fieldtype = tableinfo.getIdField();
        int i = 0;
        for (Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            aobj[i] = fieldtype.convertJavaFieldToSqlArgValue(iterator.next());
            i++;
        }

        return updateRows(databaseconnection, tableinfo.getDataClass(), mappeddeletecollection, aobj, objectcache);
    }

    public static int deleteObjects(DatabaseType databasetype, TableInfo tableinfo, DatabaseConnection databaseconnection, Collection collection, ObjectCache objectcache)
    {
        MappedDeleteCollection mappeddeletecollection = build(databasetype, tableinfo, collection.size());
        Object aobj[] = new Object[collection.size()];
        FieldType fieldtype = tableinfo.getIdField();
        int i = 0;
        for (Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            aobj[i] = fieldtype.extractJavaFieldToSqlArgValue(iterator.next());
            i++;
        }

        return updateRows(databaseconnection, tableinfo.getDataClass(), mappeddeletecollection, aobj, objectcache);
    }

    private static int updateRows(DatabaseConnection databaseconnection, Class class1, MappedDeleteCollection mappeddeletecollection, Object aobj[], ObjectCache objectcache)
    {
        int i;
        int j;
        int k;
        try
        {
            i = databaseconnection.delete(mappeddeletecollection.statement, aobj, mappeddeletecollection.argFieldTypes);
        }
        catch (SQLException sqlexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Unable to run delete collection stmt: ")).append(mappeddeletecollection.statement).toString(), sqlexception);
        }
        if (i <= 0 || objectcache == null) goto _L2; else goto _L1
_L1:
        j = aobj.length;
        k = 0;
_L3:
        if (k >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        objectcache.remove(class1, aobj[k]);
        k++;
        if (true) goto _L3; else goto _L2
_L2:
        logger.debug("delete-collection with statement '{}' and {} args, changed {} rows", mappeddeletecollection.statement, Integer.valueOf(aobj.length), Integer.valueOf(i));
        if (aobj.length > 0)
        {
            logger.trace("delete-collection arguments: {}", ((Object) (aobj)));
        }
        return i;
    }
}
