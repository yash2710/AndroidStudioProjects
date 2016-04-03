// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.table;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.j256.ormlite.table:
//            TableInfo, DatabaseTableConfig

public class TableUtils
{

    private static Logger logger = LoggerFactory.getLogger(com/j256/ormlite/table/TableUtils);
    private static final FieldType noFieldTypes[] = new FieldType[0];

    private TableUtils()
    {
    }

    private static void addCreateIndexStatements(DatabaseType databasetype, TableInfo tableinfo, List list, boolean flag, boolean flag1)
    {
        HashMap hashmap = new HashMap();
        FieldType afieldtype[] = tableinfo.getFieldTypes();
        int i = afieldtype.length;
        int j = 0;
        while (j < i) 
        {
            FieldType fieldtype = afieldtype[j];
            String s1;
            Object obj;
            if (flag1)
            {
                s1 = fieldtype.getUniqueIndexName();
            } else
            {
                s1 = fieldtype.getIndexName();
            }
            if (s1 == null)
            {
                continue;
            }
            obj = (List)hashmap.get(s1);
            if (obj == null)
            {
                obj = new ArrayList();
                hashmap.put(s1, obj);
            }
            ((List) (obj)).add(fieldtype.getColumnName());
            j++;
        }
        StringBuilder stringbuilder = new StringBuilder(128);
        for (Iterator iterator = hashmap.entrySet().iterator(); iterator.hasNext(); stringbuilder.setLength(0))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            logger.info("creating index '{}' for table '{}", entry.getKey(), tableinfo.getTableName());
            stringbuilder.append("CREATE ");
            if (flag1)
            {
                stringbuilder.append("UNIQUE ");
            }
            stringbuilder.append("INDEX ");
            if (flag && databasetype.isCreateIndexIfNotExistsSupported())
            {
                stringbuilder.append("IF NOT EXISTS ");
            }
            databasetype.appendEscapedEntityName(stringbuilder, (String)entry.getKey());
            stringbuilder.append(" ON ");
            databasetype.appendEscapedEntityName(stringbuilder, tableinfo.getTableName());
            stringbuilder.append(" ( ");
            Iterator iterator1 = ((List)entry.getValue()).iterator();
            boolean flag2 = true;
            while (iterator1.hasNext()) 
            {
                String s = (String)iterator1.next();
                if (flag2)
                {
                    flag2 = false;
                } else
                {
                    stringbuilder.append(", ");
                }
                databasetype.appendEscapedEntityName(stringbuilder, s);
            }
            stringbuilder.append(" )");
            list.add(stringbuilder.toString());
        }

    }

    private static List addCreateTableStatements(ConnectionSource connectionsource, TableInfo tableinfo, boolean flag)
    {
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        addCreateTableStatements(connectionsource.getDatabaseType(), tableinfo, ((List) (arraylist)), ((List) (arraylist1)), flag);
        return arraylist;
    }

    private static void addCreateTableStatements(DatabaseType databasetype, TableInfo tableinfo, List list, List list1, boolean flag)
    {
        StringBuilder stringbuilder = new StringBuilder(256);
        stringbuilder.append("CREATE TABLE ");
        if (flag && databasetype.isCreateIfNotExistsSupported())
        {
            stringbuilder.append("IF NOT EXISTS ");
        }
        databasetype.appendEscapedEntityName(stringbuilder, tableinfo.getTableName());
        stringbuilder.append(" (");
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        ArrayList arraylist2 = new ArrayList();
        FieldType afieldtype[] = tableinfo.getFieldTypes();
        int i = afieldtype.length;
        int j = 0;
        boolean flag1 = true;
        while (j < i) 
        {
            FieldType fieldtype = afieldtype[j];
            Iterator iterator;
            String s;
            boolean flag2;
            if (!fieldtype.isForeignCollection())
            {
                String s1;
                if (flag1)
                {
                    flag2 = false;
                } else
                {
                    stringbuilder.append(", ");
                    flag2 = flag1;
                }
                s1 = fieldtype.getColumnDefinition();
                if (s1 == null)
                {
                    databasetype.appendColumnArg(tableinfo.getTableName(), stringbuilder, fieldtype, arraylist, arraylist1, arraylist2, list1);
                } else
                {
                    databasetype.appendEscapedEntityName(stringbuilder, fieldtype.getColumnName());
                    stringbuilder.append(' ').append(s1).append(' ');
                }
            } else
            {
                flag2 = flag1;
            }
            j++;
            flag1 = flag2;
        }
        databasetype.addPrimaryKeySql(tableinfo.getFieldTypes(), arraylist, arraylist1, arraylist2, list1);
        databasetype.addUniqueComboSql(tableinfo.getFieldTypes(), arraylist, arraylist1, arraylist2, list1);
        for (iterator = arraylist.iterator(); iterator.hasNext(); stringbuilder.append(", ").append(s))
        {
            s = (String)iterator.next();
        }

        stringbuilder.append(") ");
        databasetype.appendCreateTableSuffix(stringbuilder);
        list.addAll(arraylist1);
        list.add(stringbuilder.toString());
        list.addAll(arraylist2);
        addCreateIndexStatements(databasetype, tableinfo, list, flag, false);
        addCreateIndexStatements(databasetype, tableinfo, list, flag, true);
    }

    private static void addDropIndexStatements(DatabaseType databasetype, TableInfo tableinfo, List list)
    {
        HashSet hashset = new HashSet();
        FieldType afieldtype[] = tableinfo.getFieldTypes();
        int i = afieldtype.length;
        for (int j = 0; j < i; j++)
        {
            FieldType fieldtype = afieldtype[j];
            String s1 = fieldtype.getIndexName();
            if (s1 != null)
            {
                hashset.add(s1);
            }
            String s2 = fieldtype.getUniqueIndexName();
            if (s2 != null)
            {
                hashset.add(s2);
            }
        }

        StringBuilder stringbuilder = new StringBuilder(48);
        for (Iterator iterator = hashset.iterator(); iterator.hasNext(); stringbuilder.setLength(0))
        {
            String s = (String)iterator.next();
            logger.info("dropping index '{}' for table '{}", s, tableinfo.getTableName());
            stringbuilder.append("DROP INDEX ");
            databasetype.appendEscapedEntityName(stringbuilder, s);
            list.add(stringbuilder.toString());
        }

    }

    private static void addDropTableStatements(DatabaseType databasetype, TableInfo tableinfo, List list)
    {
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        FieldType afieldtype[] = tableinfo.getFieldTypes();
        int i = afieldtype.length;
        for (int j = 0; j < i; j++)
        {
            databasetype.dropColumnArg(afieldtype[j], arraylist, arraylist1);
        }

        StringBuilder stringbuilder = new StringBuilder(64);
        stringbuilder.append("DROP TABLE ");
        databasetype.appendEscapedEntityName(stringbuilder, tableinfo.getTableName());
        stringbuilder.append(' ');
        list.addAll(arraylist);
        list.add(stringbuilder.toString());
        list.addAll(arraylist1);
    }

    public static int clearTable(ConnectionSource connectionsource, DatabaseTableConfig databasetableconfig)
    {
        return clearTable(connectionsource, databasetableconfig.getTableName());
    }

    public static int clearTable(ConnectionSource connectionsource, Class class1)
    {
        String s = DatabaseTableConfig.extractTableName(class1);
        if (connectionsource.getDatabaseType().isEntityNamesMustBeUpCase())
        {
            s = s.toUpperCase();
        }
        return clearTable(connectionsource, s);
    }

    private static int clearTable(ConnectionSource connectionsource, String s)
    {
        CompiledStatement compiledstatement;
        DatabaseConnection databaseconnection;
        DatabaseType databasetype = connectionsource.getDatabaseType();
        StringBuilder stringbuilder = new StringBuilder(48);
        String s1;
        int i;
        if (databasetype.isTruncateSupported())
        {
            stringbuilder.append("TRUNCATE TABLE ");
        } else
        {
            stringbuilder.append("DELETE FROM ");
        }
        databasetype.appendEscapedEntityName(stringbuilder, s);
        s1 = stringbuilder.toString();
        logger.info("clearing table '{}' with '{}", s, s1);
        compiledstatement = null;
        databaseconnection = connectionsource.getReadWriteConnection();
        compiledstatement = databaseconnection.compileStatement(s1, com.j256.ormlite.stmt.StatementBuilder.StatementType.EXECUTE, noFieldTypes, -1);
        i = compiledstatement.runExecute();
        if (compiledstatement != null)
        {
            compiledstatement.close();
        }
        connectionsource.releaseConnection(databaseconnection);
        return i;
        Exception exception;
        exception;
        if (compiledstatement != null)
        {
            compiledstatement.close();
        }
        connectionsource.releaseConnection(databaseconnection);
        throw exception;
    }

    public static int createTable(ConnectionSource connectionsource, DatabaseTableConfig databasetableconfig)
    {
        return createTable(connectionsource, databasetableconfig, false);
    }

    private static int createTable(ConnectionSource connectionsource, DatabaseTableConfig databasetableconfig, boolean flag)
    {
        com.j256.ormlite.dao.Dao dao = DaoManager.createDao(connectionsource, databasetableconfig);
        if (dao instanceof BaseDaoImpl)
        {
            return doCreateTable(connectionsource, ((BaseDaoImpl)dao).getTableInfo(), flag);
        } else
        {
            databasetableconfig.extractFieldTypes(connectionsource);
            return doCreateTable(connectionsource, new TableInfo(connectionsource.getDatabaseType(), null, databasetableconfig), flag);
        }
    }

    public static int createTable(ConnectionSource connectionsource, Class class1)
    {
        return createTable(connectionsource, class1, false);
    }

    private static int createTable(ConnectionSource connectionsource, Class class1, boolean flag)
    {
        com.j256.ormlite.dao.Dao dao = DaoManager.createDao(connectionsource, class1);
        if (dao instanceof BaseDaoImpl)
        {
            return doCreateTable(connectionsource, ((BaseDaoImpl)dao).getTableInfo(), flag);
        } else
        {
            return doCreateTable(connectionsource, new TableInfo(connectionsource, null, class1), flag);
        }
    }

    public static int createTableIfNotExists(ConnectionSource connectionsource, DatabaseTableConfig databasetableconfig)
    {
        return createTable(connectionsource, databasetableconfig, true);
    }

    public static int createTableIfNotExists(ConnectionSource connectionsource, Class class1)
    {
        return createTable(connectionsource, class1, true);
    }

    private static int doCreateTable(ConnectionSource connectionsource, TableInfo tableinfo, boolean flag)
    {
        DatabaseType databasetype;
        ArrayList arraylist;
        ArrayList arraylist1;
        DatabaseConnection databaseconnection;
        databasetype = connectionsource.getDatabaseType();
        logger.info("creating table '{}'", tableinfo.getTableName());
        arraylist = new ArrayList();
        arraylist1 = new ArrayList();
        addCreateTableStatements(databasetype, tableinfo, arraylist, arraylist1, flag);
        databaseconnection = connectionsource.getReadWriteConnection();
        int i;
        int j;
        i = doStatements(databaseconnection, "create", arraylist, false, databasetype.isCreateTableReturnsNegative(), databasetype.isCreateTableReturnsZero());
        j = doCreateTestQueries(databaseconnection, databasetype, arraylist1);
        int k = i + j;
        connectionsource.releaseConnection(databaseconnection);
        return k;
        Exception exception;
        exception;
        connectionsource.releaseConnection(databaseconnection);
        throw exception;
    }

    private static int doCreateTestQueries(DatabaseConnection databaseconnection, DatabaseType databasetype, List list)
    {
        Iterator iterator;
        int i;
        iterator = list.iterator();
        i = 0;
_L3:
        String s;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        s = (String)iterator.next();
        CompiledStatement compiledstatement2 = databaseconnection.compileStatement(s, com.j256.ormlite.stmt.StatementBuilder.StatementType.SELECT, noFieldTypes, -1);
        CompiledStatement compiledstatement = compiledstatement2;
        DatabaseResults databaseresults;
        boolean flag;
        databaseresults = compiledstatement.runQuery(null);
        flag = databaseresults.first();
        int j = 0;
_L1:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_93;
        }
        j++;
        flag = databaseresults.next();
          goto _L1
        logger.info("executing create table after-query got {} results: {}", Integer.valueOf(j), s);
        if (compiledstatement != null)
        {
            compiledstatement.close();
        }
        i++;
        if (true) goto _L3; else goto _L2
        SQLException sqlexception;
        sqlexception;
        CompiledStatement compiledstatement1 = null;
_L7:
        throw SqlExceptionUtil.create((new StringBuilder("executing create table after-query failed: ")).append(s).toString(), sqlexception);
        Exception exception;
        exception;
        compiledstatement = compiledstatement1;
_L5:
        if (compiledstatement != null)
        {
            compiledstatement.close();
        }
        throw exception;
_L2:
        return i;
        exception;
        compiledstatement = null;
        continue; /* Loop/switch isn't completed */
        exception;
        if (true) goto _L5; else goto _L4
_L4:
        sqlexception;
        compiledstatement1 = compiledstatement;
        if (true) goto _L7; else goto _L6
_L6:
    }

    private static int doDropTable(DatabaseType databasetype, ConnectionSource connectionsource, TableInfo tableinfo, boolean flag)
    {
        ArrayList arraylist;
        DatabaseConnection databaseconnection;
        logger.info("dropping table '{}'", tableinfo.getTableName());
        arraylist = new ArrayList();
        addDropIndexStatements(databasetype, tableinfo, arraylist);
        addDropTableStatements(databasetype, tableinfo, arraylist);
        databaseconnection = connectionsource.getReadWriteConnection();
        int i = doStatements(databaseconnection, "drop", arraylist, flag, databasetype.isCreateTableReturnsNegative(), false);
        connectionsource.releaseConnection(databaseconnection);
        return i;
        Exception exception;
        exception;
        connectionsource.releaseConnection(databaseconnection);
        throw exception;
    }

    private static int doStatements(DatabaseConnection databaseconnection, String s, Collection collection, boolean flag, boolean flag1, boolean flag2)
    {
        Iterator iterator;
        int i;
        iterator = collection.iterator();
        i = 0;
_L4:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        String s1;
        CompiledStatement compiledstatement;
        s1 = (String)iterator.next();
        compiledstatement = null;
        int k;
        compiledstatement = databaseconnection.compileStatement(s1, com.j256.ormlite.stmt.StatementBuilder.StatementType.EXECUTE, noFieldTypes, -1);
        k = compiledstatement.runExecute();
        int j = k;
        logger.info("executed {} table statement changed {} rows: {}", s, Integer.valueOf(j), s1);
        if (compiledstatement != null)
        {
            compiledstatement.close();
        }
        break MISSING_BLOCK_LABEL_95;
        SQLException sqlexception;
        sqlexception;
        j = 0;
_L5:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_186;
        }
        logger.info("ignoring {} error '{}' for statement: {}", s, sqlexception, s1);
        if (compiledstatement != null)
        {
            compiledstatement.close();
        }
          goto _L3
        throw SqlExceptionUtil.create((new StringBuilder("SQL statement failed: ")).append(s1).toString(), sqlexception);
        exception;
        if (compiledstatement != null)
        {
            compiledstatement.close();
        }
        throw exception;
_L3:
        Exception exception;
        if (j < 0)
        {
            if (!flag1)
            {
                throw new SQLException((new StringBuilder("SQL statement ")).append(s1).append(" updated ").append(j).append(" rows, we were expecting >= 0").toString());
            }
            continue; /* Loop/switch isn't completed */
        }
        if (j > 0 && flag2)
        {
            throw new SQLException((new StringBuilder("SQL statement updated ")).append(j).append(" rows, we were expecting == 0: ").append(s1).toString());
        }
        i++;
          goto _L4
_L2:
        return i;
        sqlexception;
          goto _L5
    }

    public static int dropTable(ConnectionSource connectionsource, DatabaseTableConfig databasetableconfig, boolean flag)
    {
        DatabaseType databasetype = connectionsource.getDatabaseType();
        com.j256.ormlite.dao.Dao dao = DaoManager.createDao(connectionsource, databasetableconfig);
        if (dao instanceof BaseDaoImpl)
        {
            return doDropTable(databasetype, connectionsource, ((BaseDaoImpl)dao).getTableInfo(), flag);
        } else
        {
            databasetableconfig.extractFieldTypes(connectionsource);
            return doDropTable(databasetype, connectionsource, new TableInfo(databasetype, null, databasetableconfig), flag);
        }
    }

    public static int dropTable(ConnectionSource connectionsource, Class class1, boolean flag)
    {
        DatabaseType databasetype = connectionsource.getDatabaseType();
        com.j256.ormlite.dao.Dao dao = DaoManager.createDao(connectionsource, class1);
        if (dao instanceof BaseDaoImpl)
        {
            return doDropTable(databasetype, connectionsource, ((BaseDaoImpl)dao).getTableInfo(), flag);
        } else
        {
            return doDropTable(databasetype, connectionsource, new TableInfo(connectionsource, null, class1), flag);
        }
    }

    public static List getCreateTableStatements(ConnectionSource connectionsource, DatabaseTableConfig databasetableconfig)
    {
        com.j256.ormlite.dao.Dao dao = DaoManager.createDao(connectionsource, databasetableconfig);
        if (dao instanceof BaseDaoImpl)
        {
            return addCreateTableStatements(connectionsource, ((BaseDaoImpl)dao).getTableInfo(), false);
        } else
        {
            databasetableconfig.extractFieldTypes(connectionsource);
            return addCreateTableStatements(connectionsource, new TableInfo(connectionsource.getDatabaseType(), null, databasetableconfig), false);
        }
    }

    public static List getCreateTableStatements(ConnectionSource connectionsource, Class class1)
    {
        com.j256.ormlite.dao.Dao dao = DaoManager.createDao(connectionsource, class1);
        if (dao instanceof BaseDaoImpl)
        {
            return addCreateTableStatements(connectionsource, ((BaseDaoImpl)dao).getTableInfo(), false);
        } else
        {
            return addCreateTableStatements(connectionsource, new TableInfo(connectionsource, null, class1), false);
        }
    }

}
