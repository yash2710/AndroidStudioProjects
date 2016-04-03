// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.table;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DatabaseFieldConfig;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.misc.JavaxPersistence;
import com.j256.ormlite.support.ConnectionSource;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.j256.ormlite.table:
//            DatabaseTable

public class DatabaseTableConfig
{

    private Constructor constructor;
    private Class dataClass;
    private List fieldConfigs;
    private FieldType fieldTypes[];
    private String tableName;

    public DatabaseTableConfig()
    {
    }

    public DatabaseTableConfig(Class class1, String s, List list)
    {
        dataClass = class1;
        tableName = s;
        fieldConfigs = list;
    }

    private DatabaseTableConfig(Class class1, String s, FieldType afieldtype[])
    {
        dataClass = class1;
        tableName = s;
        fieldTypes = afieldtype;
    }

    public DatabaseTableConfig(Class class1, List list)
    {
        this(class1, extractTableName(class1), list);
    }

    private FieldType[] convertFieldConfigs(ConnectionSource connectionsource, String s, List list)
    {
        ArrayList arraylist;
        Iterator iterator;
        arraylist = new ArrayList();
        iterator = list.iterator();
_L5:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        DatabaseFieldConfig databasefieldconfig;
        Class class1;
        databasefieldconfig = (DatabaseFieldConfig)iterator.next();
        class1 = dataClass;
_L4:
        FieldType fieldtype;
        fieldtype = null;
        if (class1 == null)
        {
            break MISSING_BLOCK_LABEL_89;
        }
        java.lang.reflect.Field field = class1.getDeclaredField(databasefieldconfig.getFieldName());
        if (field == null)
        {
            break MISSING_BLOCK_LABEL_136;
        }
        fieldtype = new FieldType(connectionsource, s, field, databasefieldconfig, dataClass);
        if (fieldtype == null)
        {
            throw new SQLException((new StringBuilder("Could not find declared field with name '")).append(databasefieldconfig.getFieldName()).append("' for ").append(dataClass).toString());
        }
        continue; /* Loop/switch isn't completed */
        NoSuchFieldException nosuchfieldexception;
        nosuchfieldexception;
        class1 = class1.getSuperclass();
        if (true) goto _L4; else goto _L3
_L3:
        arraylist.add(fieldtype);
          goto _L5
_L2:
        if (arraylist.isEmpty())
        {
            throw new SQLException((new StringBuilder("No fields were configured for class ")).append(dataClass).toString());
        } else
        {
            return (FieldType[])arraylist.toArray(new FieldType[arraylist.size()]);
        }
    }

    private static FieldType[] extractFieldTypes(ConnectionSource connectionsource, Class class1, String s)
    {
        ArrayList arraylist = new ArrayList();
        for (Class class2 = class1; class2 != null; class2 = class2.getSuperclass())
        {
            java.lang.reflect.Field afield[] = class2.getDeclaredFields();
            int i = afield.length;
            for (int j = 0; j < i; j++)
            {
                FieldType fieldtype = FieldType.createFieldType(connectionsource, s, afield[j], class1);
                if (fieldtype != null)
                {
                    arraylist.add(fieldtype);
                }
            }

        }

        if (arraylist.isEmpty())
        {
            throw new IllegalArgumentException((new StringBuilder("No fields have a ")).append(com/j256/ormlite/field/DatabaseField.getSimpleName()).append(" annotation in ").append(class1).toString());
        } else
        {
            return (FieldType[])arraylist.toArray(new FieldType[arraylist.size()]);
        }
    }

    public static String extractTableName(Class class1)
    {
        DatabaseTable databasetable = (DatabaseTable)class1.getAnnotation(com/j256/ormlite/table/DatabaseTable);
        String s;
        if (databasetable != null && databasetable.tableName() != null && databasetable.tableName().length() > 0)
        {
            s = databasetable.tableName();
        } else
        {
            s = JavaxPersistence.getEntityName(class1);
            if (s == null)
            {
                return class1.getSimpleName().toLowerCase();
            }
        }
        return s;
    }

    public static Constructor findNoArgConstructor(Class class1)
    {
        Constructor aconstructor[];
        int i;
        try
        {
            aconstructor = (Constructor[])class1.getDeclaredConstructors();
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException((new StringBuilder("Can't lookup declared constructors for ")).append(class1).toString(), exception);
        }
        i = aconstructor.length;
        for (int j = 0; j < i; j++)
        {
            Constructor constructor1 = aconstructor[j];
            if (constructor1.getParameterTypes().length == 0)
            {
                if (!constructor1.isAccessible())
                {
                    try
                    {
                        constructor1.setAccessible(true);
                    }
                    catch (SecurityException securityexception)
                    {
                        throw new IllegalArgumentException((new StringBuilder("Could not open access to constructor for ")).append(class1).toString());
                    }
                }
                return constructor1;
            }
        }

        if (class1.getEnclosingClass() == null)
        {
            throw new IllegalArgumentException((new StringBuilder("Can't find a no-arg constructor for ")).append(class1).toString());
        } else
        {
            throw new IllegalArgumentException((new StringBuilder("Can't find a no-arg constructor for ")).append(class1).append(".  Missing static on inner class?").toString());
        }
    }

    public static DatabaseTableConfig fromClass(ConnectionSource connectionsource, Class class1)
    {
        String s = extractTableName(class1);
        if (connectionsource.getDatabaseType().isEntityNamesMustBeUpCase())
        {
            s = s.toUpperCase();
        }
        return new DatabaseTableConfig(class1, s, extractFieldTypes(connectionsource, class1, s));
    }

    public void extractFieldTypes(ConnectionSource connectionsource)
    {
label0:
        {
            if (fieldTypes == null)
            {
                if (fieldConfigs != null)
                {
                    break label0;
                }
                fieldTypes = extractFieldTypes(connectionsource, dataClass, tableName);
            }
            return;
        }
        fieldTypes = convertFieldConfigs(connectionsource, tableName, fieldConfigs);
    }

    public Constructor getConstructor()
    {
        if (constructor == null)
        {
            constructor = findNoArgConstructor(dataClass);
        }
        return constructor;
    }

    public Class getDataClass()
    {
        return dataClass;
    }

    public List getFieldConfigs()
    {
        return fieldConfigs;
    }

    public FieldType[] getFieldTypes(DatabaseType databasetype)
    {
        if (fieldTypes == null)
        {
            throw new SQLException("Field types have not been extracted in table config");
        } else
        {
            return fieldTypes;
        }
    }

    public String getTableName()
    {
        return tableName;
    }

    public void initialize()
    {
        if (dataClass == null)
        {
            throw new IllegalStateException((new StringBuilder("dataClass was never set on ")).append(getClass().getSimpleName()).toString());
        }
        if (tableName == null)
        {
            tableName = extractTableName(dataClass);
        }
    }

    public void setConstructor(Constructor constructor1)
    {
        constructor = constructor1;
    }

    public void setDataClass(Class class1)
    {
        dataClass = class1;
    }

    public void setFieldConfigs(List list)
    {
        fieldConfigs = list;
    }

    public void setTableName(String s)
    {
        tableName = s;
    }
}
