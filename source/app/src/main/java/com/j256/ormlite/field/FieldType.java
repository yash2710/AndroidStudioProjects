// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.BaseForeignCollection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.EagerForeignCollection;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.dao.LazyForeignCollection;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.types.VoidType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.stmt.mapped.MappedQueryForId;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableInfo;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

// Referenced classes of package com.j256.ormlite.field:
//            DatabaseFieldConfig, DataPersisterManager, DataPersister, ForeignCollectionField, 
//            DataType, FieldConverter, SqlType

public class FieldType
{

    private static boolean DEFAULT_VALUE_BOOLEAN = false;
    private static byte DEFAULT_VALUE_BYTE = 0;
    private static char DEFAULT_VALUE_CHAR = 0;
    private static double DEFAULT_VALUE_DOUBLE = 0D;
    private static float DEFAULT_VALUE_FLOAT = 0F;
    private static int DEFAULT_VALUE_INT = 0;
    private static long DEFAULT_VALUE_LONG = 0L;
    private static short DEFAULT_VALUE_SHORT = 0;
    public static final String FOREIGN_ID_FIELD_SUFFIX = "_id";
    private static final ThreadLocal threadLevelCounters = new _cls1();
    private final String columnName;
    private final ConnectionSource connectionSource;
    private DataPersister dataPersister;
    private Object dataTypeConfigObj;
    private Object defaultValue;
    private final Field field;
    private final DatabaseFieldConfig fieldConfig;
    private FieldConverter fieldConverter;
    private final Method fieldGetMethod;
    private final Method fieldSetMethod;
    private BaseDaoImpl foreignDao;
    private FieldType foreignFieldType;
    private FieldType foreignIdField;
    private TableInfo foreignTableInfo;
    private final String generatedIdSequence;
    private final boolean isGeneratedId;
    private final boolean isId;
    private MappedQueryForId mappedQueryForId;
    private final Class parentClass;
    private final String tableName;

    public FieldType(ConnectionSource connectionsource, String s, Field field1, DatabaseFieldConfig databasefieldconfig, Class class1)
    {
        DatabaseType databasetype;
        Class class2;
        connectionSource = connectionsource;
        tableName = s;
        databasetype = connectionsource.getDatabaseType();
        field = field1;
        parentClass = class1;
        databasefieldconfig.postProcess();
        class2 = field1.getType();
        if (databasefieldconfig.getDataPersister() != null) goto _L2; else goto _L1
_L1:
        DataPersister datapersister1;
        String s1;
        String s2;
        Class class4 = databasefieldconfig.getPersisterClass();
        if (class4 == null || class4 == com/j256/ormlite/field/types/VoidType)
        {
            datapersister1 = DataPersisterManager.lookupForField(field1);
        } else
        {
            Method method;
            Object obj;
            try
            {
                method = class4.getDeclaredMethod("getSingleton", new Class[0]);
            }
            catch (Exception exception)
            {
                throw SqlExceptionUtil.create((new StringBuilder("Could not find getSingleton static method on class ")).append(class4).toString(), exception);
            }
            try
            {
                obj = method.invoke(null, new Object[0]);
            }
            catch (InvocationTargetException invocationtargetexception)
            {
                throw SqlExceptionUtil.create((new StringBuilder("Could not run getSingleton method on class ")).append(class4).toString(), invocationtargetexception.getTargetException());
            }
            catch (Exception exception1)
            {
                throw SqlExceptionUtil.create((new StringBuilder("Could not run getSingleton method on class ")).append(class4).toString(), exception1);
            }
            if (obj == null)
            {
                throw new SQLException((new StringBuilder("Static getSingleton method should not return null on class ")).append(class4).toString());
            }
            DataPersister datapersister2;
            try
            {
                datapersister2 = (DataPersister)obj;
            }
            catch (Exception exception2)
            {
                throw SqlExceptionUtil.create((new StringBuilder("Could not cast result of static getSingleton method to DataPersister from class ")).append(class4).toString(), exception2);
            }
            datapersister1 = datapersister2;
        }
_L13:
        s1 = databasefieldconfig.getForeignColumnName();
        s2 = field1.getName();
        if (!databasefieldconfig.isForeign() && !databasefieldconfig.isForeignAutoRefresh() && s1 == null) goto _L4; else goto _L3
_L2:
        DataPersister datapersister;
        datapersister = databasefieldconfig.getDataPersister();
        if (!datapersister.isValidForField(field1))
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("Field class ").append(class2.getName());
            stringbuilder.append(" for field ").append(this);
            stringbuilder.append(" is not valid for type ").append(datapersister);
            Class class3 = datapersister.getPrimaryClass();
            if (class3 != null)
            {
                stringbuilder.append((new StringBuilder(", maybe should be ")).append(class3).toString());
            }
            throw new IllegalArgumentException(stringbuilder.toString());
        }
          goto _L5
_L3:
        String s3;
        if (datapersister1 != null && datapersister1.isPrimitive())
        {
            throw new IllegalArgumentException((new StringBuilder("Field ")).append(this).append(" is a primitive class ").append(class2).append(" but marked as foreign").toString());
        }
        if (s1 == null)
        {
            s3 = (new StringBuilder()).append(s2).append("_id").toString();
        } else
        {
            s3 = (new StringBuilder()).append(s2).append("_").append(s1).toString();
        }
        if (com/j256/ormlite/dao/ForeignCollection.isAssignableFrom(class2))
        {
            throw new SQLException((new StringBuilder("Field '")).append(field1.getName()).append("' in class ").append(class2).append("' should use the @").append(com/j256/ormlite/field/ForeignCollectionField.getSimpleName()).append(" annotation not foreign=true").toString());
        }
          goto _L6
_L4:
        if (!databasefieldconfig.isForeignCollection()) goto _L8; else goto _L7
_L7:
        if (class2 != java/util/Collection && !com/j256/ormlite/dao/ForeignCollection.isAssignableFrom(class2))
        {
            throw new SQLException((new StringBuilder("Field class for '")).append(field1.getName()).append("' must be of class ").append(com/j256/ormlite/dao/ForeignCollection.getSimpleName()).append(" or Collection.").toString());
        }
        java.lang.reflect.Type type = field1.getGenericType();
        if (!(type instanceof ParameterizedType))
        {
            throw new SQLException((new StringBuilder("Field class for '")).append(field1.getName()).append("' must be a parameterized Collection.").toString());
        }
        if (((ParameterizedType)type).getActualTypeArguments().length == 0)
        {
            throw new SQLException((new StringBuilder("Field class for '")).append(field1.getName()).append("' must be a parameterized Collection with at least 1 type.").toString());
        }
        s3 = s2;
_L6:
        if (databasefieldconfig.getColumnName() == null)
        {
            columnName = s3;
        } else
        {
            columnName = databasefieldconfig.getColumnName();
        }
        fieldConfig = databasefieldconfig;
        if (databasefieldconfig.isId())
        {
            break MISSING_BLOCK_LABEL_773;
        }
        if (databasefieldconfig.isGeneratedId())
        {
            if (databasefieldconfig.getGeneratedIdSequence() != null)
            {
                throw new IllegalArgumentException((new StringBuilder("Must specify one of id, generatedId, and generatedIdSequence with ")).append(field1.getName()).toString());
            }
            isId = true;
            isGeneratedId = true;
            if (databasetype.isIdSequenceNeeded())
            {
                generatedIdSequence = databasetype.generateIdSequenceName(s, this);
            } else
            {
                generatedIdSequence = null;
            }
        } else
        if (databasefieldconfig.getGeneratedIdSequence() != null)
        {
            isId = true;
            isGeneratedId = true;
            String s4 = databasefieldconfig.getGeneratedIdSequence();
            if (databasetype.isEntityNamesMustBeUpCase())
            {
                s4 = s4.toUpperCase();
            }
            generatedIdSequence = s4;
        } else
        {
            isId = false;
            isGeneratedId = false;
            generatedIdSequence = null;
        }
          goto _L9
_L8:
        SecurityException securityexception;
        if (datapersister1 == null && !databasefieldconfig.isForeignCollection())
        {
            if ([B.isAssignableFrom(class2))
            {
                throw new SQLException((new StringBuilder("ORMLite does not know how to store ")).append(class2).append(" for field '").append(field1.getName()).append("'. byte[] fields must specify dataType=DataType.BYTE_ARRAY or SERIALIZABLE").toString());
            }
            if (java/io/Serializable.isAssignableFrom(class2))
            {
                throw new SQLException((new StringBuilder("ORMLite does not know how to store ")).append(class2).append(" for field '").append(field1.getName()).append("'.  Use another class, custom persister, or to serialize it use dataType=DataType.SERIALIZABLE").toString());
            } else
            {
                throw new IllegalArgumentException((new StringBuilder("ORMLite does not know how to store ")).append(class2).append(" for field ").append(field1.getName()).append(". Use another class or a custom persister.").toString());
            }
        }
        s3 = s2;
        continue; /* Loop/switch isn't completed */
        if (databasefieldconfig.isGeneratedId() || databasefieldconfig.getGeneratedIdSequence() != null)
        {
            throw new IllegalArgumentException((new StringBuilder("Must specify one of id, generatedId, and generatedIdSequence with ")).append(field1.getName()).toString());
        }
        isId = true;
        isGeneratedId = false;
        generatedIdSequence = null;
_L11:
        if (isId && (databasefieldconfig.isForeign() || databasefieldconfig.isForeignAutoRefresh()))
        {
            throw new IllegalArgumentException((new StringBuilder("Id field ")).append(field1.getName()).append(" cannot also be a foreign object").toString());
        }
        break; /* Loop/switch isn't completed */
_L9:
        if (true) goto _L11; else goto _L10
_L10:
        if (databasefieldconfig.isUseGetSet())
        {
            fieldGetMethod = DatabaseFieldConfig.findGetMethod(field1, true);
            fieldSetMethod = DatabaseFieldConfig.findSetMethod(field1, true);
        } else
        {
            if (!field1.isAccessible())
            {
                try
                {
                    field.setAccessible(true);
                }
                // Misplaced declaration of an exception variable
                catch (SecurityException securityexception)
                {
                    throw new IllegalArgumentException((new StringBuilder("Could not open access to field ")).append(field1.getName()).append(".  You may have to set useGetSet=true to fix.").toString());
                }
            }
            fieldGetMethod = null;
            fieldSetMethod = null;
        }
        if (databasefieldconfig.isAllowGeneratedIdInsert() && !databasefieldconfig.isGeneratedId())
        {
            throw new IllegalArgumentException((new StringBuilder("Field ")).append(field1.getName()).append(" must be a generated-id if allowGeneratedIdInsert = true").toString());
        }
        if (databasefieldconfig.isForeignAutoRefresh() && !databasefieldconfig.isForeign())
        {
            throw new IllegalArgumentException((new StringBuilder("Field ")).append(field1.getName()).append(" must have foreign = true if foreignAutoRefresh = true").toString());
        }
        if (databasefieldconfig.isForeignAutoCreate() && !databasefieldconfig.isForeign())
        {
            throw new IllegalArgumentException((new StringBuilder("Field ")).append(field1.getName()).append(" must have foreign = true if foreignAutoCreate = true").toString());
        }
        if (databasefieldconfig.getForeignColumnName() != null && !databasefieldconfig.isForeign())
        {
            throw new IllegalArgumentException((new StringBuilder("Field ")).append(field1.getName()).append(" must have foreign = true if foreignColumnName is set").toString());
        }
        if (databasefieldconfig.isVersion() && (datapersister1 == null || !datapersister1.isValidForVersion()))
        {
            throw new IllegalArgumentException((new StringBuilder("Field ")).append(field1.getName()).append(" is not a valid type to be a version field").toString());
        }
        if (databasefieldconfig.getMaxForeignAutoRefreshLevel() > 0 && !databasefieldconfig.isForeignAutoRefresh())
        {
            throw new IllegalArgumentException((new StringBuilder("Field ")).append(field1.getName()).append(" has maxForeignAutoRefreshLevel set but not foreignAutoRefresh is false").toString());
        } else
        {
            assignDataType(databasetype, datapersister1);
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
        datapersister1 = datapersister;
        if (true) goto _L13; else goto _L12
_L12:
    }

    private void assignDataType(DatabaseType databasetype, DataPersister datapersister)
    {
        String s;
label0:
        {
            dataPersister = datapersister;
            if (datapersister == null)
            {
                if (!fieldConfig.isForeign() && !fieldConfig.isForeignCollection())
                {
                    throw new SQLException((new StringBuilder("Data persister for field ")).append(this).append(" is null but the field is not a foreign or foreignCollection").toString());
                }
            } else
            {
                fieldConverter = databasetype.getFieldConverter(datapersister);
                if (isGeneratedId && !datapersister.isValidGeneratedType())
                {
                    StringBuilder stringbuilder = new StringBuilder();
                    stringbuilder.append("Generated-id field '").append(field.getName());
                    stringbuilder.append("' in ").append(field.getDeclaringClass().getSimpleName());
                    stringbuilder.append(" can't be type ").append(dataPersister.getSqlType());
                    stringbuilder.append(".  Must be one of: ");
                    DataType adatatype[] = DataType.values();
                    int i = adatatype.length;
                    for (int j = 0; j < i; j++)
                    {
                        DataType datatype = adatatype[j];
                        DataPersister datapersister1 = datatype.getDataPersister();
                        if (datapersister1 != null && datapersister1.isValidGeneratedType())
                        {
                            stringbuilder.append(datatype).append(' ');
                        }
                    }

                    throw new IllegalArgumentException(stringbuilder.toString());
                }
                if (fieldConfig.isThrowIfNull() && !datapersister.isPrimitive())
                {
                    throw new SQLException((new StringBuilder("Field ")).append(field.getName()).append(" must be a primitive if set with throwIfNull").toString());
                }
                if (isId && !datapersister.isAppropriateId())
                {
                    throw new SQLException((new StringBuilder("Field '")).append(field.getName()).append("' is of data type ").append(datapersister).append(" which cannot be the ID field").toString());
                }
                dataTypeConfigObj = datapersister.makeConfigObject(this);
                s = fieldConfig.getDefaultValue();
                if (s != null)
                {
                    break label0;
                }
                defaultValue = null;
            }
            return;
        }
        if (isGeneratedId)
        {
            throw new SQLException((new StringBuilder("Field '")).append(field.getName()).append("' cannot be a generatedId and have a default value '").append(s).append("'").toString());
        } else
        {
            defaultValue = fieldConverter.parseDefaultString(this, s);
            return;
        }
    }

    public static FieldType createFieldType(ConnectionSource connectionsource, String s, Field field1, Class class1)
    {
        DatabaseFieldConfig databasefieldconfig = DatabaseFieldConfig.fromField(connectionsource.getDatabaseType(), s, field1);
        if (databasefieldconfig == null)
        {
            return null;
        } else
        {
            return new FieldType(connectionsource, s, field1, databasefieldconfig, class1);
        }
    }

    private FieldType findForeignFieldType(Class class1, Class class2, BaseDaoImpl basedaoimpl)
    {
        String s = fieldConfig.getForeignCollectionForeignFieldName();
        FieldType afieldtype[] = basedaoimpl.getTableInfo().getFieldTypes();
        int i = afieldtype.length;
        int j = 0;
        while (j < i) 
        {
            FieldType fieldtype = afieldtype[j];
            StringBuilder stringbuilder;
            if (fieldtype.getType() == class2 && (s == null || fieldtype.getField().getName().equals(s)))
            {
                if (!fieldtype.fieldConfig.isForeign() && !fieldtype.fieldConfig.isForeignAutoRefresh())
                {
                    throw new SQLException((new StringBuilder("Foreign collection object ")).append(class1).append(" for field '").append(field.getName()).append("' contains a field of class ").append(class2).append(" but it's not foreign").toString());
                } else
                {
                    return fieldtype;
                }
            }
            j++;
        }
        stringbuilder = new StringBuilder();
        stringbuilder.append("Foreign collection class ").append(class1.getName());
        stringbuilder.append(" for field '").append(field.getName()).append("' column-name does not contain a foreign field");
        if (s != null)
        {
            stringbuilder.append(" named '").append(s).append('\'');
        }
        stringbuilder.append(" of class ").append(class2.getName());
        throw new SQLException(stringbuilder.toString());
    }

    private boolean isFieldValueDefault(Object obj)
    {
        if (obj == null)
        {
            return true;
        } else
        {
            return obj.equals(getJavaDefaultValueDefault());
        }
    }

    public void assignField(Object obj, Object obj1, boolean flag, ObjectCache objectcache)
    {
        if (foreignIdField == null || obj1 == null) goto _L2; else goto _L1
_L1:
        Object obj2 = extractJavaFieldValue(obj);
        if (obj2 != null && obj2.equals(obj1))
        {
            return;
        }
        ObjectCache objectcache1 = foreignDao.getObjectCache();
        IllegalArgumentException illegalargumentexception;
        Object obj3;
        if (objectcache1 == null)
        {
            obj3 = null;
        } else
        {
            obj3 = objectcache1.get(getType(), obj1);
        }
        if (obj3 == null) goto _L4; else goto _L3
_L3:
        obj1 = obj3;
_L2:
        Object obj4;
        Object obj5;
        LevelCounters levelcounters;
        Exception exception1;
        com.j256.ormlite.support.DatabaseConnection databaseconnection;
        Exception exception2;
        if (fieldSetMethod == null)
        {
            try
            {
                field.set(obj, obj1);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (IllegalArgumentException illegalargumentexception)
            {
                throw SqlExceptionUtil.create((new StringBuilder("Could not assign object '")).append(obj1).append("' to field ").append(this).toString(), illegalargumentexception);
            }
            catch (IllegalAccessException illegalaccessexception)
            {
                throw SqlExceptionUtil.create((new StringBuilder("Could not assign object '")).append(obj1).append("' to field ").append(this).toString(), illegalaccessexception);
            }
        }
        break MISSING_BLOCK_LABEL_400;
_L4:
        if (flag) goto _L2; else goto _L5
_L5:
        levelcounters = (LevelCounters)threadLevelCounters.get();
        if (levelcounters.autoRefreshLevel == 0)
        {
            levelcounters.autoRefreshLevelMax = fieldConfig.getMaxForeignAutoRefreshLevel();
        }
        if (levelcounters.autoRefreshLevel < levelcounters.autoRefreshLevelMax) goto _L7; else goto _L6
_L6:
        obj5 = foreignTableInfo.createObject();
        foreignIdField.assignField(obj5, obj1, false, objectcache);
_L8:
        obj1 = obj5;
          goto _L2
_L7:
        if (mappedQueryForId == null)
        {
            mappedQueryForId = MappedQueryForId.build(connectionSource.getDatabaseType(), foreignDao.getTableInfo(), foreignIdField);
        }
        levelcounters.autoRefreshLevel = 1 + levelcounters.autoRefreshLevel;
        databaseconnection = connectionSource.getReadOnlyConnection();
        obj4 = mappedQueryForId.execute(databaseconnection, obj1, objectcache);
        connectionSource.releaseConnection(databaseconnection);
        levelcounters.autoRefreshLevel = -1 + levelcounters.autoRefreshLevel;
        Exception exception;
        if (levelcounters.autoRefreshLevel <= 0)
        {
            threadLevelCounters.remove();
            obj5 = obj4;
        } else
        {
            obj5 = obj4;
        }
          goto _L8
        exception2;
        connectionSource.releaseConnection(databaseconnection);
        throw exception2;
        exception1;
        levelcounters.autoRefreshLevel = -1 + levelcounters.autoRefreshLevel;
        if (levelcounters.autoRefreshLevel <= 0)
        {
            threadLevelCounters.remove();
        }
        throw exception1;
        try
        {
            fieldSetMethod.invoke(obj, new Object[] {
                obj1
            });
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Could not call ")).append(fieldSetMethod).append(" on object with '").append(obj1).append("' for ").append(this).toString(), exception);
        }
    }

    public Object assignIdValue(Object obj, Number number, ObjectCache objectcache)
    {
        Object obj1 = dataPersister.convertIdNumber(number);
        if (obj1 == null)
        {
            throw new SQLException((new StringBuilder("Invalid class ")).append(dataPersister).append(" for sequence-id ").append(this).toString());
        } else
        {
            assignField(obj, obj1, false, objectcache);
            return obj1;
        }
    }

    public BaseForeignCollection buildForeignCollection(Object obj, Object obj1)
    {
        BaseDaoImpl basedaoimpl;
        LevelCounters levelcounters;
        if (foreignFieldType == null)
        {
            return null;
        }
        basedaoimpl = foreignDao;
        if (!fieldConfig.isForeignCollectionEager())
        {
            return new LazyForeignCollection(basedaoimpl, obj, obj1, foreignFieldType, fieldConfig.getForeignCollectionOrderColumnName(), fieldConfig.isForeignCollectionOrderAscending());
        }
        levelcounters = (LevelCounters)threadLevelCounters.get();
        if (levelcounters.foreignCollectionLevel == 0)
        {
            levelcounters.foreignCollectionLevelMax = fieldConfig.getForeignCollectionMaxEagerLevel();
        }
        if (levelcounters.foreignCollectionLevel >= levelcounters.foreignCollectionLevelMax)
        {
            return new LazyForeignCollection(basedaoimpl, obj, obj1, foreignFieldType, fieldConfig.getForeignCollectionOrderColumnName(), fieldConfig.isForeignCollectionOrderAscending());
        }
        levelcounters.foreignCollectionLevel = 1 + levelcounters.foreignCollectionLevel;
        EagerForeignCollection eagerforeigncollection = new EagerForeignCollection(basedaoimpl, obj, obj1, foreignFieldType, fieldConfig.getForeignCollectionOrderColumnName(), fieldConfig.isForeignCollectionOrderAscending());
        levelcounters.foreignCollectionLevel = -1 + levelcounters.foreignCollectionLevel;
        return eagerforeigncollection;
        Exception exception;
        exception;
        levelcounters.foreignCollectionLevel = -1 + levelcounters.foreignCollectionLevel;
        throw exception;
    }

    public void configDaoInformation(ConnectionSource connectionsource, Class class1)
    {
        FieldType fieldtype = null;
        Class class2 = field.getType();
        DatabaseType databasetype = connectionsource.getDatabaseType();
        String s = fieldConfig.getForeignColumnName();
        TableInfo tableinfo;
        BaseDaoImpl basedaoimpl2;
        FieldType fieldtype2;
        MappedQueryForId mappedqueryforid1;
        if (fieldConfig.isForeignAutoRefresh() || s != null)
        {
            DatabaseTableConfig databasetableconfig = fieldConfig.getForeignTableConfig();
            BaseDaoImpl basedaoimpl1;
            FieldType fieldtype1;
            if (databasetableconfig == null)
            {
                BaseDaoImpl basedaoimpl3 = (BaseDaoImpl)DaoManager.createDao(connectionsource, class2);
                tableinfo = basedaoimpl3.getTableInfo();
                basedaoimpl1 = basedaoimpl3;
            } else
            {
                databasetableconfig.extractFieldTypes(connectionsource);
                BaseDaoImpl basedaoimpl = (BaseDaoImpl)DaoManager.createDao(connectionsource, databasetableconfig);
                tableinfo = basedaoimpl.getTableInfo();
                basedaoimpl1 = basedaoimpl;
            }
            if (s == null)
            {
                fieldtype1 = tableinfo.getIdField();
                if (fieldtype1 == null)
                {
                    throw new IllegalArgumentException((new StringBuilder("Foreign field ")).append(class2).append(" does not have id field").toString());
                }
            } else
            {
                fieldtype1 = tableinfo.getFieldTypeByColumnName(s);
                if (fieldtype1 == null)
                {
                    throw new IllegalArgumentException((new StringBuilder("Foreign field ")).append(class2).append(" does not have field named '").append(s).append("'").toString());
                }
            }
            MappedQueryForId mappedqueryforid = MappedQueryForId.build(databasetype, tableinfo, fieldtype1);
            basedaoimpl2 = basedaoimpl1;
            fieldtype2 = fieldtype1;
            mappedqueryforid1 = mappedqueryforid;
        } else
        if (fieldConfig.isForeign())
        {
            if (dataPersister != null && dataPersister.isPrimitive())
            {
                throw new IllegalArgumentException((new StringBuilder("Field ")).append(this).append(" is a primitive class ").append(class2).append(" but marked as foreign").toString());
            }
            DatabaseTableConfig databasetableconfig2 = fieldConfig.getForeignTableConfig();
            BaseDaoImpl basedaoimpl4;
            TableInfo tableinfo1;
            FieldType fieldtype3;
            if (databasetableconfig2 != null)
            {
                databasetableconfig2.extractFieldTypes(connectionsource);
                basedaoimpl4 = (BaseDaoImpl)DaoManager.createDao(connectionsource, databasetableconfig2);
            } else
            {
                basedaoimpl4 = (BaseDaoImpl)DaoManager.createDao(connectionsource, class2);
            }
            tableinfo1 = basedaoimpl4.getTableInfo();
            fieldtype3 = tableinfo1.getIdField();
            if (fieldtype3 == null)
            {
                throw new IllegalArgumentException((new StringBuilder("Foreign field ")).append(class2).append(" does not have id field").toString());
            }
            if (isForeignAutoCreate() && !fieldtype3.isGeneratedId())
            {
                throw new IllegalArgumentException((new StringBuilder("Field ")).append(field.getName()).append(", if foreignAutoCreate = true then class ").append(class2.getSimpleName()).append(" must have id field with generatedId = true").toString());
            }
            tableinfo = tableinfo1;
            fieldtype2 = fieldtype3;
            basedaoimpl2 = basedaoimpl4;
            mappedqueryforid1 = null;
            fieldtype = null;
        } else
        if (fieldConfig.isForeignCollection())
        {
            if (class2 != java/util/Collection && !com/j256/ormlite/dao/ForeignCollection.isAssignableFrom(class2))
            {
                throw new SQLException((new StringBuilder("Field class for '")).append(field.getName()).append("' must be of class ").append(com/j256/ormlite/dao/ForeignCollection.getSimpleName()).append(" or Collection.").toString());
            }
            java.lang.reflect.Type type = field.getGenericType();
            if (!(type instanceof ParameterizedType))
            {
                throw new SQLException((new StringBuilder("Field class for '")).append(field.getName()).append("' must be a parameterized Collection.").toString());
            }
            java.lang.reflect.Type atype[] = ((ParameterizedType)type).getActualTypeArguments();
            if (atype.length == 0)
            {
                throw new SQLException((new StringBuilder("Field class for '")).append(field.getName()).append("' must be a parameterized Collection with at least 1 type.").toString());
            }
            if (!(atype[0] instanceof Class))
            {
                throw new SQLException((new StringBuilder("Field class for '")).append(field.getName()).append("' must be a parameterized Collection whose generic argument is an entity class not: ").append(atype[0]).toString());
            }
            Class class3 = (Class)atype[0];
            DatabaseTableConfig databasetableconfig1 = fieldConfig.getForeignTableConfig();
            if (databasetableconfig1 == null)
            {
                basedaoimpl2 = (BaseDaoImpl)DaoManager.createDao(connectionsource, class3);
            } else
            {
                basedaoimpl2 = (BaseDaoImpl)DaoManager.createDao(connectionsource, databasetableconfig1);
            }
            fieldtype = findForeignFieldType(class3, class1, basedaoimpl2);
            mappedqueryforid1 = null;
            fieldtype2 = null;
            tableinfo = null;
        } else
        {
            mappedqueryforid1 = null;
            basedaoimpl2 = null;
            fieldtype = null;
            fieldtype2 = null;
            tableinfo = null;
        }
        mappedQueryForId = mappedqueryforid1;
        foreignTableInfo = tableinfo;
        foreignFieldType = fieldtype;
        foreignDao = basedaoimpl2;
        foreignIdField = fieldtype2;
        if (foreignIdField != null)
        {
            assignDataType(databasetype, foreignIdField.getDataPersister());
        }
    }

    public Object convertJavaFieldToSqlArgValue(Object obj)
    {
        if (obj == null)
        {
            return null;
        } else
        {
            return fieldConverter.javaToSqlArg(this, obj);
        }
    }

    public Object convertStringToJavaField(String s, int i)
    {
        if (s == null)
        {
            return null;
        } else
        {
            return fieldConverter.resultStringToJava(this, s, i);
        }
    }

    public int createWithForeignDao(Object obj)
    {
        return foreignDao.create(obj);
    }

    public boolean equals(Object obj)
    {
        FieldType fieldtype;
        if (obj != null && obj.getClass() == getClass())
        {
            if (field.equals((fieldtype = (FieldType)obj).field) && (parentClass != null ? parentClass.equals(fieldtype.parentClass) : fieldtype.parentClass == null))
            {
                return true;
            }
        }
        return false;
    }

    public Object extractJavaFieldToSqlArgValue(Object obj)
    {
        return convertJavaFieldToSqlArgValue(extractJavaFieldValue(obj));
    }

    public Object extractJavaFieldValue(Object obj)
    {
        Object obj1 = extractRawJavaFieldValue(obj);
        if (foreignIdField != null && obj1 != null)
        {
            obj1 = foreignIdField.extractRawJavaFieldValue(obj1);
        }
        return obj1;
    }

    public Object extractRawJavaFieldValue(Object obj)
    {
        if (fieldGetMethod == null)
        {
            Object obj2;
            try
            {
                obj2 = field.get(obj);
            }
            catch (Exception exception1)
            {
                throw SqlExceptionUtil.create((new StringBuilder("Could not get field value for ")).append(this).toString(), exception1);
            }
            return obj2;
        }
        Object obj1;
        try
        {
            obj1 = fieldGetMethod.invoke(obj, new Object[0]);
        }
        catch (Exception exception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Could not call ")).append(fieldGetMethod).append(" for ").append(this).toString(), exception);
        }
        return obj1;
    }

    public Object generateId()
    {
        return dataPersister.generateId();
    }

    public String getColumnDefinition()
    {
        return fieldConfig.getColumnDefinition();
    }

    public String getColumnName()
    {
        return columnName;
    }

    public DataPersister getDataPersister()
    {
        return dataPersister;
    }

    public Object getDataTypeConfigObj()
    {
        return dataTypeConfigObj;
    }

    public Object getDefaultValue()
    {
        return defaultValue;
    }

    public Field getField()
    {
        return field;
    }

    public String getFieldName()
    {
        return field.getName();
    }

    public Object getFieldValueIfNotDefault(Object obj)
    {
        Object obj1 = extractJavaFieldValue(obj);
        if (isFieldValueDefault(obj1))
        {
            obj1 = null;
        }
        return obj1;
    }

    public FieldType getForeignIdField()
    {
        return foreignIdField;
    }

    public String getFormat()
    {
        return fieldConfig.getFormat();
    }

    public String getGeneratedIdSequence()
    {
        return generatedIdSequence;
    }

    public String getIndexName()
    {
        return fieldConfig.getIndexName(tableName);
    }

    public Object getJavaDefaultValueDefault()
    {
        if (field.getType() == Boolean.TYPE)
        {
            return Boolean.valueOf(DEFAULT_VALUE_BOOLEAN);
        }
        if (field.getType() == Byte.TYPE || field.getType() == java/lang/Byte)
        {
            return Byte.valueOf(DEFAULT_VALUE_BYTE);
        }
        if (field.getType() == Character.TYPE || field.getType() == java/lang/Character)
        {
            return Character.valueOf(DEFAULT_VALUE_CHAR);
        }
        if (field.getType() == Short.TYPE || field.getType() == java/lang/Short)
        {
            return Short.valueOf(DEFAULT_VALUE_SHORT);
        }
        if (field.getType() == Integer.TYPE || field.getType() == java/lang/Integer)
        {
            return Integer.valueOf(DEFAULT_VALUE_INT);
        }
        if (field.getType() == Long.TYPE || field.getType() == java/lang/Long)
        {
            return Long.valueOf(DEFAULT_VALUE_LONG);
        }
        if (field.getType() == Float.TYPE || field.getType() == java/lang/Float)
        {
            return Float.valueOf(DEFAULT_VALUE_FLOAT);
        }
        if (field.getType() == Double.TYPE || field.getType() == java/lang/Double)
        {
            return Double.valueOf(DEFAULT_VALUE_DOUBLE);
        } else
        {
            return null;
        }
    }

    public SqlType getSqlType()
    {
        return fieldConverter.getSqlType();
    }

    public String getTableName()
    {
        return tableName;
    }

    public Class getType()
    {
        return field.getType();
    }

    public String getUniqueIndexName()
    {
        return fieldConfig.getUniqueIndexName(tableName);
    }

    public Enum getUnknownEnumVal()
    {
        return fieldConfig.getUnknownEnumValue();
    }

    public int getWidth()
    {
        return fieldConfig.getWidth();
    }

    public int hashCode()
    {
        return field.hashCode();
    }

    public boolean isAllowGeneratedIdInsert()
    {
        return fieldConfig.isAllowGeneratedIdInsert();
    }

    public boolean isArgumentHolderRequired()
    {
        return dataPersister.isArgumentHolderRequired();
    }

    public boolean isCanBeNull()
    {
        return fieldConfig.isCanBeNull();
    }

    public boolean isComparable()
    {
        if (fieldConfig.isForeignCollection())
        {
            return false;
        }
        if (dataPersister == null)
        {
            throw new SQLException((new StringBuilder("Internal error.  Data-persister is not configured for field.  Please post _full_ exception with associated data objects to mailing list: ")).append(this).toString());
        } else
        {
            return dataPersister.isComparable();
        }
    }

    public boolean isEscapedDefaultValue()
    {
        return dataPersister.isEscapedDefaultValue();
    }

    public boolean isEscapedValue()
    {
        return dataPersister.isEscapedValue();
    }

    public boolean isForeign()
    {
        return fieldConfig.isForeign();
    }

    public boolean isForeignAutoCreate()
    {
        return fieldConfig.isForeignAutoCreate();
    }

    public boolean isForeignCollection()
    {
        return fieldConfig.isForeignCollection();
    }

    public boolean isGeneratedId()
    {
        return isGeneratedId;
    }

    public boolean isGeneratedIdSequence()
    {
        return generatedIdSequence != null;
    }

    public boolean isId()
    {
        return isId;
    }

    public boolean isObjectsFieldValueDefault(Object obj)
    {
        return isFieldValueDefault(extractJavaFieldValue(obj));
    }

    public boolean isReadOnly()
    {
        return fieldConfig.isReadOnly();
    }

    public boolean isSelfGeneratedId()
    {
        return dataPersister.isSelfGeneratedId();
    }

    public boolean isUnique()
    {
        return fieldConfig.isUnique();
    }

    public boolean isUniqueCombo()
    {
        return fieldConfig.isUniqueCombo();
    }

    public boolean isVersion()
    {
        return fieldConfig.isVersion();
    }

    public Object moveToNextValue(Object obj)
    {
        if (dataPersister == null)
        {
            return null;
        } else
        {
            return dataPersister.moveToNextValue(obj);
        }
    }

    public Object resultToJava(DatabaseResults databaseresults, Map map)
    {
        Integer integer = (Integer)map.get(columnName);
        if (integer == null)
        {
            integer = Integer.valueOf(databaseresults.findColumn(columnName));
            map.put(columnName, integer);
        }
        Object obj = fieldConverter.resultToJava(this, databaseresults, integer.intValue());
        if (fieldConfig.isForeign())
        {
            if (databaseresults.wasNull(integer.intValue()))
            {
                return null;
            }
        } else
        if (dataPersister.isPrimitive())
        {
            if (fieldConfig.isThrowIfNull() && databaseresults.wasNull(integer.intValue()))
            {
                throw new SQLException((new StringBuilder("Results value for primitive field '")).append(field.getName()).append("' was an invalid null value").toString());
            }
        } else
        if (!fieldConverter.isStreamType() && databaseresults.wasNull(integer.intValue()))
        {
            return null;
        }
        return obj;
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append(":name=").append(field.getName()).append(",class=").append(field.getDeclaringClass().getSimpleName()).toString();
    }


    private class LevelCounters
    {

        int autoRefreshLevel;
        int autoRefreshLevelMax;
        int foreignCollectionLevel;
        int foreignCollectionLevelMax;

        private LevelCounters()
        {
        }

        LevelCounters(_cls1 _pcls1)
        {
            this();
        }
    }


    private class _cls1 extends ThreadLocal
    {

        protected final LevelCounters initialValue()
        {
            return new LevelCounters(null);
        }

        protected final volatile Object initialValue()
        {
            return initialValue();
        }

        _cls1()
        {
        }
    }

}
