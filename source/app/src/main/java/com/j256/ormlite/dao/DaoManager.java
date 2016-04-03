// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.DatabaseTableConfig;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package com.j256.ormlite.dao:
//            Dao, BaseDaoImpl

public class DaoManager
{

    private static Map classMap = null;
    private static Map configMap = null;
    private static Logger logger = LoggerFactory.getLogger(com/j256/ormlite/dao/DaoManager);
    private static Map tableConfigMap = null;

    public DaoManager()
    {
    }

    public static void addCachedDatabaseConfigs(Collection collection)
    {
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorenter ;
        HashMap hashmap;
        if (configMap != null)
        {
            break MISSING_BLOCK_LABEL_80;
        }
        hashmap = new HashMap();
_L1:
        DatabaseTableConfig databasetableconfig;
        for (Iterator iterator = collection.iterator(); iterator.hasNext(); logger.info("Loaded configuration for {}", databasetableconfig.getDataClass()))
        {
            databasetableconfig = (DatabaseTableConfig)iterator.next();
            hashmap.put(databasetableconfig.getDataClass(), databasetableconfig);
        }

        break MISSING_BLOCK_LABEL_94;
        Exception exception;
        exception;
        throw exception;
        hashmap = new HashMap(configMap);
          goto _L1
        configMap = hashmap;
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
    }

    private static void addDaoToClassMap(ClassConnectionSource classconnectionsource, Dao dao)
    {
        if (classMap == null)
        {
            classMap = new HashMap();
        }
        classMap.put(classconnectionsource, dao);
    }

    private static void addDaoToTableMap(TableConfigConnectionSource tableconfigconnectionsource, Dao dao)
    {
        if (tableConfigMap == null)
        {
            tableConfigMap = new HashMap();
        }
        tableConfigMap.put(tableconfigconnectionsource, dao);
    }

    public static void clearCache()
    {
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorenter ;
        if (configMap != null)
        {
            configMap.clear();
            configMap = null;
        }
        clearDaoCache();
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static void clearDaoCache()
    {
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorenter ;
        if (classMap != null)
        {
            classMap.clear();
            classMap = null;
        }
        if (tableConfigMap != null)
        {
            tableConfigMap.clear();
            tableConfigMap = null;
        }
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static Dao createDao(ConnectionSource connectionsource, DatabaseTableConfig databasetableconfig)
    {
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorenter ;
        if (connectionsource != null)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        throw new IllegalArgumentException("connectionSource argument cannot be null");
        Exception exception;
        exception;
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
        throw exception;
        Dao dao = doCreateDao(connectionsource, databasetableconfig);
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
        return dao;
    }

    public static Dao createDao(ConnectionSource connectionsource, Class class1)
    {
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorenter ;
        if (connectionsource != null)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        throw new IllegalArgumentException("connectionSource argument cannot be null");
        Exception exception;
        exception;
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
        throw exception;
        Dao dao = lookupDao(new ClassConnectionSource(connectionsource, class1));
        Dao dao1 = dao;
        if (dao1 == null) goto _L2; else goto _L1
_L1:
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
        return dao1;
_L2:
        dao1 = (Dao)createDaoFromConfig(connectionsource, class1);
        if (dao1 != null) goto _L1; else goto _L3
_L3:
        DatabaseTable databasetable = (DatabaseTable)class1.getAnnotation(com/j256/ormlite/table/DatabaseTable);
        if (databasetable == null) goto _L5; else goto _L4
_L4:
        if (databasetable.daoClass() != java/lang/Void && databasetable.daoClass() != com/j256/ormlite/dao/BaseDaoImpl) goto _L6; else goto _L5
_L5:
        DatabaseTableConfig databasetableconfig = connectionsource.getDatabaseType().extractDatabaseTableConfig(connectionsource, class1);
        if (databasetableconfig != null) goto _L8; else goto _L7
_L7:
        dao1 = BaseDaoImpl.createDao(connectionsource, class1);
_L9:
        logger.debug("created dao for class {} with reflection", class1);
_L10:
        registerDao(connectionsource, dao1);
          goto _L1
_L8:
        dao1 = BaseDaoImpl.createDao(connectionsource, databasetableconfig);
          goto _L9
_L6:
        Class class2;
        Object aobj[];
        Constructor constructor;
        class2 = databasetable.daoClass();
        aobj = (new Object[] {
            connectionsource, class1
        });
        constructor = findConstructor(class2, aobj);
        if (constructor != null)
        {
            break MISSING_BLOCK_LABEL_246;
        }
        aobj = (new Object[] {
            connectionsource
        });
        constructor = findConstructor(class2, aobj);
        if (constructor != null)
        {
            break MISSING_BLOCK_LABEL_246;
        }
        throw new SQLException((new StringBuilder("Could not find public constructor with ConnectionSource and optional Class parameters ")).append(class2).append(".  Missing static on class?").toString());
        dao1 = (Dao)constructor.newInstance(aobj);
        logger.debug("created dao for class {} from constructor", class1);
          goto _L10
        Exception exception1;
        exception1;
        throw SqlExceptionUtil.create((new StringBuilder("Could not call the constructor in class ")).append(class2).toString(), exception1);
          goto _L1
    }

    private static Object createDaoFromConfig(ConnectionSource connectionsource, Class class1)
    {
        if (configMap == null)
        {
            return null;
        }
        DatabaseTableConfig databasetableconfig = (DatabaseTableConfig)configMap.get(class1);
        if (databasetableconfig == null)
        {
            return null;
        } else
        {
            return doCreateDao(connectionsource, databasetableconfig);
        }
    }

    private static Dao doCreateDao(ConnectionSource connectionsource, DatabaseTableConfig databasetableconfig)
    {
        TableConfigConnectionSource tableconfigconnectionsource = new TableConfigConnectionSource(connectionsource, databasetableconfig);
        Dao dao = lookupDao(tableconfigconnectionsource);
        if (dao == null)
        {
            Class class1 = databasetableconfig.getDataClass();
            ClassConnectionSource classconnectionsource = new ClassConnectionSource(connectionsource, class1);
            Dao dao1 = lookupDao(classconnectionsource);
            if (dao1 != null)
            {
                addDaoToTableMap(tableconfigconnectionsource, dao1);
                return dao1;
            }
            DatabaseTable databasetable = (DatabaseTable)databasetableconfig.getDataClass().getAnnotation(com/j256/ormlite/table/DatabaseTable);
            if (databasetable == null || databasetable.daoClass() == java/lang/Void || databasetable.daoClass() == com/j256/ormlite/dao/BaseDaoImpl)
            {
                dao = BaseDaoImpl.createDao(connectionsource, databasetableconfig);
            } else
            {
                Class class2 = databasetable.daoClass();
                Object aobj[] = {
                    connectionsource, databasetableconfig
                };
                Constructor constructor = findConstructor(class2, aobj);
                if (constructor == null)
                {
                    throw new SQLException((new StringBuilder("Could not find public constructor with ConnectionSource, DatabaseTableConfig parameters in class ")).append(class2).toString());
                }
                try
                {
                    dao = (Dao)constructor.newInstance(aobj);
                }
                catch (Exception exception)
                {
                    throw SqlExceptionUtil.create((new StringBuilder("Could not call the constructor in class ")).append(class2).toString(), exception);
                }
            }
            addDaoToTableMap(tableconfigconnectionsource, dao);
            logger.debug("created dao for class {} from table config", class1);
            if (lookupDao(classconnectionsource) == null)
            {
                addDaoToClassMap(classconnectionsource, dao);
                return dao;
            }
        }
        return dao;
    }

    private static Constructor findConstructor(Class class1, Object aobj[])
    {
        Constructor aconstructor[];
        int i;
        int j;
        aconstructor = class1.getConstructors();
        i = aconstructor.length;
        j = 0;
_L6:
        if (j >= i) goto _L2; else goto _L1
_L1:
        Constructor constructor;
        Class aclass[];
        int k;
        constructor = aconstructor[j];
        aclass = constructor.getParameterTypes();
        if (aclass.length != aobj.length)
        {
            continue; /* Loop/switch isn't completed */
        }
        k = 0;
_L5:
        if (k >= aclass.length)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        if (aclass[k].isAssignableFrom(aobj[k].getClass())) goto _L4; else goto _L3
_L3:
        boolean flag = false;
_L7:
        if (flag)
        {
            return constructor;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        k++;
          goto _L5
        j++;
          goto _L6
_L2:
        return null;
        flag = true;
          goto _L7
    }

    private static Dao lookupDao(ClassConnectionSource classconnectionsource)
    {
        if (classMap == null)
        {
            classMap = new HashMap();
        }
        Dao dao = (Dao)classMap.get(classconnectionsource);
        if (dao == null)
        {
            dao = null;
        }
        return dao;
    }

    private static Dao lookupDao(TableConfigConnectionSource tableconfigconnectionsource)
    {
        if (tableConfigMap == null)
        {
            tableConfigMap = new HashMap();
        }
        Dao dao = (Dao)tableConfigMap.get(tableconfigconnectionsource);
        if (dao == null)
        {
            dao = null;
        }
        return dao;
    }

    public static Dao lookupDao(ConnectionSource connectionsource, DatabaseTableConfig databasetableconfig)
    {
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorenter ;
        if (connectionsource != null)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        throw new IllegalArgumentException("connectionSource argument cannot be null");
        Exception exception;
        exception;
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
        throw exception;
        Dao dao = lookupDao(new TableConfigConnectionSource(connectionsource, databasetableconfig));
        Dao dao1;
        dao1 = dao;
        if (dao1 == null)
        {
            dao1 = null;
        }
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
        return dao1;
    }

    public static Dao lookupDao(ConnectionSource connectionsource, Class class1)
    {
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorenter ;
        if (connectionsource != null)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        throw new IllegalArgumentException("connectionSource argument cannot be null");
        Exception exception;
        exception;
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
        throw exception;
        Dao dao = lookupDao(new ClassConnectionSource(connectionsource, class1));
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
        return dao;
    }

    public static void registerDao(ConnectionSource connectionsource, Dao dao)
    {
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorenter ;
        if (connectionsource != null)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        throw new IllegalArgumentException("connectionSource argument cannot be null");
        Exception exception;
        exception;
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
        throw exception;
        addDaoToClassMap(new ClassConnectionSource(connectionsource, dao.getDataClass()), dao);
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
    }

    public static void registerDaoWithTableConfig(ConnectionSource connectionsource, Dao dao)
    {
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorenter ;
        if (connectionsource != null)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        throw new IllegalArgumentException("connectionSource argument cannot be null");
        Exception exception;
        exception;
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
        throw exception;
        DatabaseTableConfig databasetableconfig;
        if (!(dao instanceof BaseDaoImpl))
        {
            break MISSING_BLOCK_LABEL_59;
        }
        databasetableconfig = ((BaseDaoImpl)dao).getTableConfig();
        if (databasetableconfig == null)
        {
            break MISSING_BLOCK_LABEL_59;
        }
        addDaoToTableMap(new TableConfigConnectionSource(connectionsource, databasetableconfig), dao);
_L1:
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
        return;
        addDaoToClassMap(new ClassConnectionSource(connectionsource, dao.getDataClass()), dao);
          goto _L1
    }

    private static void removeDaoToClassMap(ClassConnectionSource classconnectionsource, Dao dao)
    {
        if (classMap != null)
        {
            classMap.remove(classconnectionsource);
        }
    }

    public static void unregisterDao(ConnectionSource connectionsource, Dao dao)
    {
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorenter ;
        if (connectionsource != null)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        throw new IllegalArgumentException("connectionSource argument cannot be null");
        Exception exception;
        exception;
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
        throw exception;
        removeDaoToClassMap(new ClassConnectionSource(connectionsource, dao.getDataClass()), dao);
        com/j256/ormlite/dao/DaoManager;
        JVM INSTR monitorexit ;
    }


    private class ClassConnectionSource
    {

        Class clazz;
        ConnectionSource connectionSource;

        public boolean equals(Object obj)
        {
            ClassConnectionSource classconnectionsource;
            if (obj != null && getClass() == obj.getClass())
            {
                if (clazz.equals((classconnectionsource = (ClassConnectionSource)obj).clazz) && connectionSource.equals(classconnectionsource.connectionSource))
                {
                    return true;
                }
            }
            return false;
        }

        public int hashCode()
        {
            return 31 * (31 + clazz.hashCode()) + connectionSource.hashCode();
        }

        public ClassConnectionSource(ConnectionSource connectionsource, Class class1)
        {
            connectionSource = connectionsource;
            clazz = class1;
        }
    }


    private class TableConfigConnectionSource
    {

        ConnectionSource connectionSource;
        DatabaseTableConfig tableConfig;

        public boolean equals(Object obj)
        {
            TableConfigConnectionSource tableconfigconnectionsource;
            if (obj != null && getClass() == obj.getClass())
            {
                if (tableConfig.equals((tableconfigconnectionsource = (TableConfigConnectionSource)obj).tableConfig) && connectionSource.equals(tableconfigconnectionsource.connectionSource))
                {
                    return true;
                }
            }
            return false;
        }

        public int hashCode()
        {
            return 31 * (31 + tableConfig.hashCode()) + connectionSource.hashCode();
        }

        public TableConfigConnectionSource(ConnectionSource connectionsource, DatabaseTableConfig databasetableconfig)
        {
            connectionSource = connectionsource;
            tableConfig = databasetableconfig;
        }
    }

}
