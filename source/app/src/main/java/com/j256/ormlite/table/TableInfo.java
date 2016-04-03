// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.table;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.ConnectionSource;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.j256.ormlite.table:
//            DatabaseTableConfig, ObjectFactory

public class TableInfo
{

    private static final FieldType NO_FOREIGN_COLLECTIONS[] = new FieldType[0];
    private final BaseDaoImpl baseDaoImpl;
    private final Constructor constructor;
    private final Class dataClass;
    private Map fieldNameMap;
    private final FieldType fieldTypes[];
    private final boolean foreignAutoCreate;
    private final FieldType foreignCollections[];
    private final FieldType idField;
    private final String tableName;

    public TableInfo(DatabaseType databasetype, BaseDaoImpl basedaoimpl, DatabaseTableConfig databasetableconfig)
    {
        baseDaoImpl = basedaoimpl;
        dataClass = databasetableconfig.getDataClass();
        tableName = databasetableconfig.getTableName();
        fieldTypes = databasetableconfig.getFieldTypes(databasetype);
        FieldType fieldtype = null;
        FieldType afieldtype[] = fieldTypes;
        int i = afieldtype.length;
        int j = 0;
        int k = 0;
        boolean flag = false;
        while (j < i) 
        {
            FieldType fieldtype2 = afieldtype[j];
            if (fieldtype2.isId() || fieldtype2.isGeneratedId() || fieldtype2.isGeneratedIdSequence())
            {
                if (fieldtype != null)
                {
                    throw new SQLException((new StringBuilder("More than 1 idField configured for class ")).append(dataClass).append(" (").append(fieldtype).append(",").append(fieldtype2).append(")").toString());
                }
                fieldtype = fieldtype2;
            }
            if (fieldtype2.isForeignAutoCreate())
            {
                flag = true;
            }
            FieldType afieldtype1[];
            int l;
            int i1;
            int j1;
            FieldType fieldtype1;
            int k1;
            if (fieldtype2.isForeignCollection())
            {
                k1 = k + 1;
            } else
            {
                k1 = k;
            }
            j++;
            k = k1;
        }
        idField = fieldtype;
        constructor = databasetableconfig.getConstructor();
        foreignAutoCreate = flag;
        if (k == 0)
        {
            foreignCollections = NO_FOREIGN_COLLECTIONS;
        } else
        {
            foreignCollections = new FieldType[k];
            afieldtype1 = fieldTypes;
            l = afieldtype1.length;
            i1 = 0;
            j1 = 0;
            while (i1 < l) 
            {
                fieldtype1 = afieldtype1[i1];
                if (fieldtype1.isForeignCollection())
                {
                    foreignCollections[j1] = fieldtype1;
                    j1++;
                }
                i1++;
            }
        }
    }

    public TableInfo(ConnectionSource connectionsource, BaseDaoImpl basedaoimpl, Class class1)
    {
        this(connectionsource.getDatabaseType(), basedaoimpl, DatabaseTableConfig.fromClass(connectionsource, class1));
    }

    private static void wireNewInstance(BaseDaoImpl basedaoimpl, Object obj)
    {
        if (obj instanceof BaseDaoEnabled)
        {
            ((BaseDaoEnabled)obj).setDao(basedaoimpl);
        }
    }

    public Object createObject()
    {
        BaseDaoImpl basedaoimpl;
        ObjectFactory objectfactory;
        Object obj;
        Object obj1;
        try
        {
            basedaoimpl = baseDaoImpl;
        }
        catch (Exception exception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Could not create object for ")).append(constructor.getDeclaringClass()).toString(), exception);
        }
        objectfactory = null;
        if (basedaoimpl == null)
        {
            break MISSING_BLOCK_LABEL_19;
        }
        objectfactory = baseDaoImpl.getObjectFactory();
        if (objectfactory != null) goto _L2; else goto _L1
_L1:
        obj = constructor.newInstance(new Object[0]);
_L3:
        wireNewInstance(baseDaoImpl, obj);
        return obj;
_L2:
        obj1 = objectfactory.createObject(constructor, baseDaoImpl.getDataClass());
        obj = obj1;
          goto _L3
    }

    public Constructor getConstructor()
    {
        return constructor;
    }

    public Class getDataClass()
    {
        return dataClass;
    }

    public FieldType getFieldTypeByColumnName(String s)
    {
        if (fieldNameMap == null)
        {
            HashMap hashmap = new HashMap();
            FieldType afieldtype[] = fieldTypes;
            int i = afieldtype.length;
            for (int j = 0; j < i; j++)
            {
                FieldType fieldtype2 = afieldtype[j];
                hashmap.put(fieldtype2.getColumnName().toLowerCase(), fieldtype2);
            }

            fieldNameMap = hashmap;
        }
        FieldType fieldtype = (FieldType)fieldNameMap.get(s.toLowerCase());
        if (fieldtype != null)
        {
            return fieldtype;
        }
        FieldType afieldtype1[] = fieldTypes;
        int k = afieldtype1.length;
        for (int l = 0; l < k; l++)
        {
            FieldType fieldtype1 = afieldtype1[l];
            if (fieldtype1.getFieldName().equals(s))
            {
                throw new IllegalArgumentException((new StringBuilder("You should use columnName '")).append(fieldtype1.getColumnName()).append("' for table ").append(tableName).append(" instead of fieldName '").append(fieldtype1.getFieldName()).append("'").toString());
            }
        }

        throw new IllegalArgumentException((new StringBuilder("Unknown column name '")).append(s).append("' in table ").append(tableName).toString());
    }

    public FieldType[] getFieldTypes()
    {
        return fieldTypes;
    }

    public FieldType[] getForeignCollections()
    {
        return foreignCollections;
    }

    public FieldType getIdField()
    {
        return idField;
    }

    public String getTableName()
    {
        return tableName;
    }

    public boolean hasColumnName(String s)
    {
        FieldType afieldtype[] = fieldTypes;
        int i = afieldtype.length;
        int j = 0;
        do
        {
label0:
            {
                boolean flag = false;
                if (j < i)
                {
                    if (!afieldtype[j].getColumnName().equals(s))
                    {
                        break label0;
                    }
                    flag = true;
                }
                return flag;
            }
            j++;
        } while (true);
    }

    public boolean isForeignAutoCreate()
    {
        return foreignAutoCreate;
    }

    public boolean isUpdatable()
    {
        return idField != null && fieldTypes.length > 1;
    }

    public String objectToString(Object obj)
    {
        StringBuilder stringbuilder = new StringBuilder(64);
        stringbuilder.append(obj.getClass().getSimpleName());
        FieldType afieldtype[] = fieldTypes;
        int i = afieldtype.length;
        int j = 0;
        while (j < i) 
        {
            FieldType fieldtype = afieldtype[j];
            stringbuilder.append(' ').append(fieldtype.getColumnName()).append("=");
            try
            {
                stringbuilder.append(fieldtype.extractJavaFieldValue(obj));
            }
            catch (Exception exception)
            {
                throw new IllegalStateException((new StringBuilder("Could not generate toString of field ")).append(fieldtype).toString(), exception);
            }
            j++;
        }
        return stringbuilder.toString();
    }

}
