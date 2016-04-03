// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.types.VoidType;
import com.j256.ormlite.misc.JavaxPersistence;
import com.j256.ormlite.table.DatabaseTableConfig;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Referenced classes of package com.j256.ormlite.field:
//            DataType, DatabaseField, ForeignCollectionField, DataPersister

public class DatabaseFieldConfig
{

    public static final boolean DEFAULT_CAN_BE_NULL = true;
    public static final DataType DEFAULT_DATA_TYPE;
    public static final boolean DEFAULT_FOREIGN_COLLECTION_ORDER_ASCENDING = true;
    private static final int DEFAULT_MAX_EAGER_FOREIGN_COLLECTION_LEVEL = 1;
    public static final Class DEFAULT_PERSISTER_CLASS = com/j256/ormlite/field/types/VoidType;
    private boolean allowGeneratedIdInsert;
    private boolean canBeNull;
    private String columnDefinition;
    private String columnName;
    private DataPersister dataPersister;
    private DataType dataType;
    private String defaultValue;
    private String fieldName;
    private boolean foreign;
    private boolean foreignAutoCreate;
    private boolean foreignAutoRefresh;
    private boolean foreignCollection;
    private String foreignCollectionColumnName;
    private boolean foreignCollectionEager;
    private String foreignCollectionForeignFieldName;
    private int foreignCollectionMaxEagerLevel;
    private boolean foreignCollectionOrderAscending;
    private String foreignCollectionOrderColumnName;
    private String foreignColumnName;
    private DatabaseTableConfig foreignTableConfig;
    private String format;
    private boolean generatedId;
    private String generatedIdSequence;
    private boolean id;
    private boolean index;
    private String indexName;
    private int maxForeignAutoRefreshLevel;
    private boolean persisted;
    private Class persisterClass;
    private boolean readOnly;
    private boolean throwIfNull;
    private boolean unique;
    private boolean uniqueCombo;
    private boolean uniqueIndex;
    private String uniqueIndexName;
    private Enum unknownEnumValue;
    private boolean useGetSet;
    private boolean version;
    private int width;

    public DatabaseFieldConfig()
    {
        dataType = DEFAULT_DATA_TYPE;
        canBeNull = true;
        persisted = true;
        maxForeignAutoRefreshLevel = -1;
        persisterClass = DEFAULT_PERSISTER_CLASS;
        foreignCollectionMaxEagerLevel = 1;
        foreignCollectionOrderAscending = true;
    }

    public DatabaseFieldConfig(String s)
    {
        dataType = DEFAULT_DATA_TYPE;
        canBeNull = true;
        persisted = true;
        maxForeignAutoRefreshLevel = -1;
        persisterClass = DEFAULT_PERSISTER_CLASS;
        foreignCollectionMaxEagerLevel = 1;
        foreignCollectionOrderAscending = true;
        fieldName = s;
    }

    public DatabaseFieldConfig(String s, String s1, DataType datatype, String s2, int i, boolean flag, boolean flag1, 
            boolean flag2, String s3, boolean flag3, DatabaseTableConfig databasetableconfig, boolean flag4, Enum enum, boolean flag5, 
            String s4, boolean flag6, String s5, String s6, boolean flag7, int j, int k)
    {
        dataType = DEFAULT_DATA_TYPE;
        canBeNull = true;
        persisted = true;
        maxForeignAutoRefreshLevel = -1;
        persisterClass = DEFAULT_PERSISTER_CLASS;
        foreignCollectionMaxEagerLevel = 1;
        foreignCollectionOrderAscending = true;
        fieldName = s;
        columnName = s1;
        dataType = DataType.UNKNOWN;
        defaultValue = s2;
        width = i;
        canBeNull = flag;
        id = flag1;
        generatedId = flag2;
        generatedIdSequence = s3;
        foreign = flag3;
        foreignTableConfig = databasetableconfig;
        useGetSet = flag4;
        unknownEnumValue = enum;
        throwIfNull = flag5;
        format = s4;
        unique = flag6;
        indexName = s5;
        uniqueIndexName = s6;
        foreignAutoRefresh = flag7;
        maxForeignAutoRefreshLevel = j;
        foreignCollectionMaxEagerLevel = k;
    }

    public static Method findGetMethod(Field field, boolean flag)
    {
        String s = methodFromField(field, "get");
        Method method1 = field.getDeclaringClass().getMethod(s, new Class[0]);
        Method method;
        Exception exception;
        if (method1.getReturnType() != field.getType())
        {
            method = null;
            if (flag)
            {
                throw new IllegalArgumentException((new StringBuilder("Return type of get method ")).append(s).append(" does not return ").append(field.getType()).toString());
            }
        } else
        {
            method = method1;
        }
        break MISSING_BLOCK_LABEL_112;
        exception;
        method = null;
        if (flag)
        {
            throw new IllegalArgumentException((new StringBuilder("Could not find appropriate get method for ")).append(field).toString());
        }
        return method;
    }

    private String findIndexName(String s)
    {
        if (columnName == null)
        {
            return (new StringBuilder()).append(s).append("_").append(fieldName).append("_idx").toString();
        } else
        {
            return (new StringBuilder()).append(s).append("_").append(columnName).append("_idx").toString();
        }
    }

    public static Enum findMatchingEnumVal(Field field, String s)
    {
        if (s == null || s.length() == 0)
        {
            return null;
        }
        Enum aenum[] = (Enum[])field.getType().getEnumConstants();
        int i = aenum.length;
        for (int j = 0; j < i; j++)
        {
            Enum enum = aenum[j];
            if (enum.name().equals(s))
            {
                return enum;
            }
        }

        throw new IllegalArgumentException((new StringBuilder("Unknwown enum unknown name ")).append(s).append(" for field ").append(field).toString());
    }

    public static Method findSetMethod(Field field, boolean flag)
    {
        String s = methodFromField(field, "set");
        Method method1;
        Class class1 = field.getDeclaringClass();
        Class aclass[] = new Class[1];
        aclass[0] = field.getType();
        method1 = class1.getMethod(s, aclass);
        Method method;
        Exception exception;
        if (method1.getReturnType() != Void.TYPE)
        {
            method = null;
            if (flag)
            {
                throw new IllegalArgumentException((new StringBuilder("Return type of set method ")).append(s).append(" returns ").append(method1.getReturnType()).append(" instead of void").toString());
            }
        } else
        {
            method = method1;
        }
        break MISSING_BLOCK_LABEL_133;
        exception;
        method = null;
        if (flag)
        {
            throw new IllegalArgumentException((new StringBuilder("Could not find appropriate set method for ")).append(field).toString());
        }
        return method;
    }

    public static DatabaseFieldConfig fromDatabaseField(DatabaseType databasetype, String s, Field field, DatabaseField databasefield)
    {
        DatabaseFieldConfig databasefieldconfig = new DatabaseFieldConfig();
        databasefieldconfig.fieldName = field.getName();
        if (databasetype.isEntityNamesMustBeUpCase())
        {
            databasefieldconfig.fieldName = databasefieldconfig.fieldName.toUpperCase();
        }
        databasefieldconfig.columnName = valueIfNotBlank(databasefield.columnName());
        databasefieldconfig.dataType = databasefield.dataType();
        String s1 = databasefield.defaultValue();
        if (!s1.equals("__ormlite__ no default value string was specified"))
        {
            databasefieldconfig.defaultValue = s1;
        }
        databasefieldconfig.width = databasefield.width();
        databasefieldconfig.canBeNull = databasefield.canBeNull();
        databasefieldconfig.id = databasefield.id();
        databasefieldconfig.generatedId = databasefield.generatedId();
        databasefieldconfig.generatedIdSequence = valueIfNotBlank(databasefield.generatedIdSequence());
        databasefieldconfig.foreign = databasefield.foreign();
        databasefieldconfig.useGetSet = databasefield.useGetSet();
        databasefieldconfig.unknownEnumValue = findMatchingEnumVal(field, databasefield.unknownEnumName());
        databasefieldconfig.throwIfNull = databasefield.throwIfNull();
        databasefieldconfig.format = valueIfNotBlank(databasefield.format());
        databasefieldconfig.unique = databasefield.unique();
        databasefieldconfig.uniqueCombo = databasefield.uniqueCombo();
        databasefieldconfig.index = databasefield.index();
        databasefieldconfig.indexName = valueIfNotBlank(databasefield.indexName());
        databasefieldconfig.uniqueIndex = databasefield.uniqueIndex();
        databasefieldconfig.uniqueIndexName = valueIfNotBlank(databasefield.uniqueIndexName());
        databasefieldconfig.foreignAutoRefresh = databasefield.foreignAutoRefresh();
        databasefieldconfig.maxForeignAutoRefreshLevel = databasefield.maxForeignAutoRefreshLevel();
        databasefieldconfig.persisterClass = databasefield.persisterClass();
        databasefieldconfig.allowGeneratedIdInsert = databasefield.allowGeneratedIdInsert();
        databasefieldconfig.columnDefinition = valueIfNotBlank(databasefield.columnDefinition());
        databasefieldconfig.foreignAutoCreate = databasefield.foreignAutoCreate();
        databasefieldconfig.version = databasefield.version();
        databasefieldconfig.foreignColumnName = valueIfNotBlank(databasefield.foreignColumnName());
        databasefieldconfig.readOnly = databasefield.readOnly();
        return databasefieldconfig;
    }

    public static DatabaseFieldConfig fromField(DatabaseType databasetype, String s, Field field)
    {
        DatabaseField databasefield = (DatabaseField)field.getAnnotation(com/j256/ormlite/field/DatabaseField);
        if (databasefield != null)
        {
            if (databasefield.persisted())
            {
                return fromDatabaseField(databasetype, s, field, databasefield);
            } else
            {
                return null;
            }
        }
        ForeignCollectionField foreigncollectionfield = (ForeignCollectionField)field.getAnnotation(com/j256/ormlite/field/ForeignCollectionField);
        if (foreigncollectionfield != null)
        {
            return fromForeignCollection(databasetype, field, foreigncollectionfield);
        } else
        {
            return JavaxPersistence.createFieldConfig(databasetype, field);
        }
    }

    private static DatabaseFieldConfig fromForeignCollection(DatabaseType databasetype, Field field, ForeignCollectionField foreigncollectionfield)
    {
        DatabaseFieldConfig databasefieldconfig = new DatabaseFieldConfig();
        databasefieldconfig.fieldName = field.getName();
        if (foreigncollectionfield.columnName().length() > 0)
        {
            databasefieldconfig.columnName = foreigncollectionfield.columnName();
        }
        databasefieldconfig.foreignCollection = true;
        databasefieldconfig.foreignCollectionEager = foreigncollectionfield.eager();
        int i = foreigncollectionfield.maxEagerForeignCollectionLevel();
        String s;
        if (i != 1)
        {
            databasefieldconfig.foreignCollectionMaxEagerLevel = i;
        } else
        {
            databasefieldconfig.foreignCollectionMaxEagerLevel = foreigncollectionfield.maxEagerLevel();
        }
        databasefieldconfig.foreignCollectionOrderColumnName = valueIfNotBlank(foreigncollectionfield.orderColumnName());
        databasefieldconfig.foreignCollectionOrderAscending = foreigncollectionfield.orderAscending();
        databasefieldconfig.foreignCollectionColumnName = valueIfNotBlank(foreigncollectionfield.columnName());
        s = valueIfNotBlank(foreigncollectionfield.foreignFieldName());
        if (s == null)
        {
            databasefieldconfig.foreignCollectionForeignFieldName = valueIfNotBlank(valueIfNotBlank(foreigncollectionfield.foreignColumnName()));
            return databasefieldconfig;
        } else
        {
            databasefieldconfig.foreignCollectionForeignFieldName = s;
            return databasefieldconfig;
        }
    }

    private static String methodFromField(Field field, String s)
    {
        return (new StringBuilder()).append(s).append(field.getName().substring(0, 1).toUpperCase()).append(field.getName().substring(1)).toString();
    }

    private static String valueIfNotBlank(String s)
    {
        if (s == null || s.length() == 0)
        {
            s = null;
        }
        return s;
    }

    public String getColumnDefinition()
    {
        return columnDefinition;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public DataPersister getDataPersister()
    {
        if (dataPersister == null)
        {
            return dataType.getDataPersister();
        } else
        {
            return dataPersister;
        }
    }

    public DataType getDataType()
    {
        return dataType;
    }

    public String getDefaultValue()
    {
        return defaultValue;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public String getForeignCollectionColumnName()
    {
        return foreignCollectionColumnName;
    }

    public String getForeignCollectionForeignFieldName()
    {
        return foreignCollectionForeignFieldName;
    }

    public int getForeignCollectionMaxEagerLevel()
    {
        return foreignCollectionMaxEagerLevel;
    }

    public String getForeignCollectionOrderColumnName()
    {
        return foreignCollectionOrderColumnName;
    }

    public String getForeignColumnName()
    {
        return foreignColumnName;
    }

    public DatabaseTableConfig getForeignTableConfig()
    {
        return foreignTableConfig;
    }

    public String getFormat()
    {
        return format;
    }

    public String getGeneratedIdSequence()
    {
        return generatedIdSequence;
    }

    public String getIndexName(String s)
    {
        if (index && indexName == null)
        {
            indexName = findIndexName(s);
        }
        return indexName;
    }

    public int getMaxForeignAutoRefreshLevel()
    {
        return maxForeignAutoRefreshLevel;
    }

    public Class getPersisterClass()
    {
        return persisterClass;
    }

    public String getUniqueIndexName(String s)
    {
        if (uniqueIndex && uniqueIndexName == null)
        {
            uniqueIndexName = findIndexName(s);
        }
        return uniqueIndexName;
    }

    public Enum getUnknownEnumValue()
    {
        return unknownEnumValue;
    }

    public int getWidth()
    {
        return width;
    }

    public boolean isAllowGeneratedIdInsert()
    {
        return allowGeneratedIdInsert;
    }

    public boolean isCanBeNull()
    {
        return canBeNull;
    }

    public boolean isForeign()
    {
        return foreign;
    }

    public boolean isForeignAutoCreate()
    {
        return foreignAutoCreate;
    }

    public boolean isForeignAutoRefresh()
    {
        return foreignAutoRefresh;
    }

    public boolean isForeignCollection()
    {
        return foreignCollection;
    }

    public boolean isForeignCollectionEager()
    {
        return foreignCollectionEager;
    }

    public boolean isForeignCollectionOrderAscending()
    {
        return foreignCollectionOrderAscending;
    }

    public boolean isGeneratedId()
    {
        return generatedId;
    }

    public boolean isId()
    {
        return id;
    }

    public boolean isIndex()
    {
        return index;
    }

    public boolean isPersisted()
    {
        return persisted;
    }

    public boolean isReadOnly()
    {
        return readOnly;
    }

    public boolean isThrowIfNull()
    {
        return throwIfNull;
    }

    public boolean isUnique()
    {
        return unique;
    }

    public boolean isUniqueCombo()
    {
        return uniqueCombo;
    }

    public boolean isUniqueIndex()
    {
        return uniqueIndex;
    }

    public boolean isUseGetSet()
    {
        return useGetSet;
    }

    public boolean isVersion()
    {
        return version;
    }

    public void postProcess()
    {
        if (foreignColumnName != null)
        {
            foreignAutoRefresh = true;
        }
        if (foreignAutoRefresh && maxForeignAutoRefreshLevel == -1)
        {
            maxForeignAutoRefreshLevel = 2;
        }
    }

    public void setAllowGeneratedIdInsert(boolean flag)
    {
        allowGeneratedIdInsert = flag;
    }

    public void setCanBeNull(boolean flag)
    {
        canBeNull = flag;
    }

    public void setColumnDefinition(String s)
    {
        columnDefinition = s;
    }

    public void setColumnName(String s)
    {
        columnName = s;
    }

    public void setDataPersister(DataPersister datapersister)
    {
        dataPersister = datapersister;
    }

    public void setDataType(DataType datatype)
    {
        dataType = datatype;
    }

    public void setDefaultValue(String s)
    {
        defaultValue = s;
    }

    public void setFieldName(String s)
    {
        fieldName = s;
    }

    public void setForeign(boolean flag)
    {
        foreign = flag;
    }

    public void setForeignAutoCreate(boolean flag)
    {
        foreignAutoCreate = flag;
    }

    public void setForeignAutoRefresh(boolean flag)
    {
        foreignAutoRefresh = flag;
    }

    public void setForeignCollection(boolean flag)
    {
        foreignCollection = flag;
    }

    public void setForeignCollectionColumnName(String s)
    {
        foreignCollectionColumnName = s;
    }

    public void setForeignCollectionEager(boolean flag)
    {
        foreignCollectionEager = flag;
    }

    public void setForeignCollectionForeignColumnName(String s)
    {
        foreignCollectionForeignFieldName = s;
    }

    public void setForeignCollectionForeignFieldName(String s)
    {
        foreignCollectionForeignFieldName = s;
    }

    public void setForeignCollectionMaxEagerForeignCollectionLevel(int i)
    {
        foreignCollectionMaxEagerLevel = i;
    }

    public void setForeignCollectionMaxEagerLevel(int i)
    {
        foreignCollectionMaxEagerLevel = i;
    }

    public void setForeignCollectionOrderAscending(boolean flag)
    {
        foreignCollectionOrderAscending = flag;
    }

    public void setForeignCollectionOrderColumn(String s)
    {
        foreignCollectionOrderColumnName = s;
    }

    public void setForeignCollectionOrderColumnName(String s)
    {
        foreignCollectionOrderColumnName = s;
    }

    public void setForeignColumnName(String s)
    {
        foreignColumnName = s;
    }

    public void setForeignTableConfig(DatabaseTableConfig databasetableconfig)
    {
        foreignTableConfig = databasetableconfig;
    }

    public void setFormat(String s)
    {
        format = s;
    }

    public void setGeneratedId(boolean flag)
    {
        generatedId = flag;
    }

    public void setGeneratedIdSequence(String s)
    {
        generatedIdSequence = s;
    }

    public void setId(boolean flag)
    {
        id = flag;
    }

    public void setIndex(boolean flag)
    {
        index = flag;
    }

    public void setIndexName(String s)
    {
        indexName = s;
    }

    public void setMaxEagerForeignCollectionLevel(int i)
    {
        foreignCollectionMaxEagerLevel = i;
    }

    public void setMaxForeignAutoRefreshLevel(int i)
    {
        maxForeignAutoRefreshLevel = i;
    }

    public void setPersisted(boolean flag)
    {
        persisted = flag;
    }

    public void setPersisterClass(Class class1)
    {
        persisterClass = class1;
    }

    public void setReadOnly(boolean flag)
    {
        readOnly = flag;
    }

    public void setThrowIfNull(boolean flag)
    {
        throwIfNull = flag;
    }

    public void setUnique(boolean flag)
    {
        unique = flag;
    }

    public void setUniqueCombo(boolean flag)
    {
        uniqueCombo = flag;
    }

    public void setUniqueIndex(boolean flag)
    {
        uniqueIndex = flag;
    }

    public void setUniqueIndexName(String s)
    {
        uniqueIndexName = s;
    }

    public void setUnknownEnumValue(Enum enum)
    {
        unknownEnumValue = enum;
    }

    public void setUseGetSet(boolean flag)
    {
        useGetSet = flag;
    }

    public void setVersion(boolean flag)
    {
        version = flag;
    }

    public void setWidth(int i)
    {
        width = i;
    }

    static 
    {
        DEFAULT_DATA_TYPE = DataType.UNKNOWN;
    }
}
