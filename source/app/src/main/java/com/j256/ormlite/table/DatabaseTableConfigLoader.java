// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.table;

import com.j256.ormlite.field.DatabaseFieldConfig;
import com.j256.ormlite.field.DatabaseFieldConfigLoader;
import com.j256.ormlite.misc.SqlExceptionUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.j256.ormlite.table:
//            DatabaseTableConfig

public class DatabaseTableConfigLoader
{

    private static final String CONFIG_FILE_END_MARKER = "# --table-end--";
    private static final String CONFIG_FILE_FIELDS_END = "# --table-fields-end--";
    private static final String CONFIG_FILE_FIELDS_START = "# --table-fields-start--";
    private static final String CONFIG_FILE_START_MARKER = "# --table-start--";
    private static final String FIELD_NAME_DATA_CLASS = "dataClass";
    private static final String FIELD_NAME_TABLE_NAME = "tableName";

    public DatabaseTableConfigLoader()
    {
    }

    public static DatabaseTableConfig fromReader(BufferedReader bufferedreader)
    {
        DatabaseTableConfig databasetableconfig = new DatabaseTableConfig();
        boolean flag = false;
        do
        {
            String s;
            try
            {
                s = bufferedreader.readLine();
            }
            catch (IOException ioexception)
            {
                throw SqlExceptionUtil.create("Could not read DatabaseTableConfig from stream", ioexception);
            }
            if (s == null || s.equals("# --table-end--"))
            {
                break;
            }
            if (s.equals("# --table-fields-start--"))
            {
                readFields(bufferedreader, databasetableconfig);
            } else
            if (s.length() != 0 && !s.startsWith("#") && !s.equals("# --table-start--"))
            {
                String as[] = s.split("=", -2);
                if (as.length != 2)
                {
                    throw new SQLException((new StringBuilder("DatabaseTableConfig reading from stream cannot parse line: ")).append(s).toString());
                }
                readTableField(databasetableconfig, as[0], as[1]);
                flag = true;
            }
        } while (true);
        if (flag)
        {
            return databasetableconfig;
        } else
        {
            return null;
        }
    }

    public static List loadDatabaseConfigFromReader(BufferedReader bufferedreader)
    {
        ArrayList arraylist = new ArrayList();
        do
        {
            DatabaseTableConfig databasetableconfig = fromReader(bufferedreader);
            if (databasetableconfig != null)
            {
                arraylist.add(databasetableconfig);
            } else
            {
                return arraylist;
            }
        } while (true);
    }

    private static void readFields(BufferedReader bufferedreader, DatabaseTableConfig databasetableconfig)
    {
        ArrayList arraylist;
        arraylist = new ArrayList();
        do
        {
label0:
            {
                String s;
                DatabaseFieldConfig databasefieldconfig;
                try
                {
                    s = bufferedreader.readLine();
                }
                catch (IOException ioexception)
                {
                    throw SqlExceptionUtil.create("Could not read next field from config file", ioexception);
                }
                if (s == null || s.equals("# --table-fields-end--"))
                {
                    break label0;
                }
                databasefieldconfig = DatabaseFieldConfigLoader.fromReader(bufferedreader);
                if (databasefieldconfig == null)
                {
                    break label0;
                }
                arraylist.add(databasefieldconfig);
            }
        } while (true);
        databasetableconfig.setFieldConfigs(arraylist);
        return;
    }

    private static void readTableField(DatabaseTableConfig databasetableconfig, String s, String s1)
    {
        if (!s.equals("dataClass"))
        {
            break MISSING_BLOCK_LABEL_43;
        }
        databasetableconfig.setDataClass(Class.forName(s1));
_L1:
        return;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        throw new IllegalArgumentException((new StringBuilder("Unknown class specified for dataClass: ")).append(s1).toString());
        if (s.equals("tableName"))
        {
            databasetableconfig.setTableName(s1);
            return;
        }
          goto _L1
    }

    public static void write(BufferedWriter bufferedwriter, DatabaseTableConfig databasetableconfig)
    {
        try
        {
            writeConfig(bufferedwriter, databasetableconfig);
            return;
        }
        catch (IOException ioexception)
        {
            throw SqlExceptionUtil.create("Could not write config to writer", ioexception);
        }
    }

    private static void writeConfig(BufferedWriter bufferedwriter, DatabaseTableConfig databasetableconfig)
    {
        bufferedwriter.append("# --table-start--");
        bufferedwriter.newLine();
        if (databasetableconfig.getDataClass() != null)
        {
            bufferedwriter.append("dataClass").append('=').append(databasetableconfig.getDataClass().getName());
            bufferedwriter.newLine();
        }
        if (databasetableconfig.getTableName() != null)
        {
            bufferedwriter.append("tableName").append('=').append(databasetableconfig.getTableName());
            bufferedwriter.newLine();
        }
        bufferedwriter.append("# --table-fields-start--");
        bufferedwriter.newLine();
        if (databasetableconfig.getFieldConfigs() != null)
        {
            for (Iterator iterator = databasetableconfig.getFieldConfigs().iterator(); iterator.hasNext(); DatabaseFieldConfigLoader.write(bufferedwriter, (DatabaseFieldConfig)iterator.next(), databasetableconfig.getTableName())) { }
        }
        bufferedwriter.append("# --table-fields-end--");
        bufferedwriter.newLine();
        bufferedwriter.append("# --table-end--");
        bufferedwriter.newLine();
    }
}
