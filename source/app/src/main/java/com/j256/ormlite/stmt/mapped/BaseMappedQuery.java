// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.TableInfo;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.j256.ormlite.stmt.mapped:
//            BaseMappedStatement

public abstract class BaseMappedQuery extends BaseMappedStatement
    implements GenericRowMapper
{

    private Map columnPositions;
    private Object parent;
    private Object parentId;
    protected final FieldType resultsFieldTypes[];

    protected BaseMappedQuery(TableInfo tableinfo, String s, FieldType afieldtype[], FieldType afieldtype1[])
    {
        super(tableinfo, s, afieldtype);
        columnPositions = null;
        parent = null;
        parentId = null;
        resultsFieldTypes = afieldtype1;
    }

    public Object mapRow(DatabaseResults databaseresults)
    {
        Object obj;
        ObjectCache objectcache;
        if (columnPositions == null)
        {
            obj = new HashMap();
        } else
        {
            obj = columnPositions;
        }
        objectcache = databaseresults.getObjectCache();
        if (objectcache != null)
        {
            Object obj4 = idField.resultToJava(databaseresults, ((Map) (obj)));
            Object obj5 = objectcache.get(clazz, obj4);
            if (obj5 != null)
            {
                return obj5;
            }
        }
        Object obj1 = tableInfo.createObject();
        Object obj2 = null;
        FieldType afieldtype[] = resultsFieldTypes;
        int i = afieldtype.length;
        int j = 0;
        boolean flag = false;
        while (j < i) 
        {
            FieldType fieldtype1 = afieldtype[j];
            if (fieldtype1.isForeignCollection())
            {
                flag = true;
            } else
            {
                Object obj3 = fieldtype1.resultToJava(databaseresults, ((Map) (obj)));
                if (obj3 != null && parent != null && fieldtype1.getField().getType() == parent.getClass() && obj3.equals(parentId))
                {
                    fieldtype1.assignField(obj1, parent, true, objectcache);
                } else
                {
                    fieldtype1.assignField(obj1, obj3, false, objectcache);
                }
                if (fieldtype1 == idField)
                {
                    obj2 = obj3;
                }
            }
            j++;
        }
        if (flag)
        {
            FieldType afieldtype1[] = resultsFieldTypes;
            int k = afieldtype1.length;
            for (int l = 0; l < k; l++)
            {
                FieldType fieldtype = afieldtype1[l];
                if (!fieldtype.isForeignCollection())
                {
                    continue;
                }
                com.j256.ormlite.dao.BaseForeignCollection baseforeigncollection = fieldtype.buildForeignCollection(obj1, obj2);
                if (baseforeigncollection != null)
                {
                    fieldtype.assignField(obj1, baseforeigncollection, false, objectcache);
                }
            }

        }
        if (objectcache != null && obj2 != null)
        {
            objectcache.put(clazz, obj2, obj1);
        }
        if (columnPositions == null)
        {
            columnPositions = ((Map) (obj));
        }
        return obj1;
    }

    public void setParentInformation(Object obj, Object obj1)
    {
        parent = obj;
        parentId = obj1;
    }
}
