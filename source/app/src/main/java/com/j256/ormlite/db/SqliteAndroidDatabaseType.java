// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.db;

import com.j256.ormlite.android.DatabaseTableConfigUtil;
import com.j256.ormlite.field.DataPersister;
import com.j256.ormlite.field.FieldConverter;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.DateStringType;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

// Referenced classes of package com.j256.ormlite.db:
//            BaseSqliteDatabaseType

public class SqliteAndroidDatabaseType extends BaseSqliteDatabaseType
{

    public SqliteAndroidDatabaseType()
    {
    }

    protected void appendBooleanType(StringBuilder stringbuilder, FieldType fieldtype, int i)
    {
        appendShortType(stringbuilder, fieldtype, i);
    }

    protected void appendDateType(StringBuilder stringbuilder, FieldType fieldtype, int i)
    {
        appendStringType(stringbuilder, fieldtype, i);
    }

    public DatabaseTableConfig extractDatabaseTableConfig(ConnectionSource connectionsource, Class class1)
    {
        return DatabaseTableConfigUtil.fromClass(connectionsource, class1);
    }

    public String getDatabaseName()
    {
        return "Android SQLite";
    }

    protected String getDriverClassName()
    {
        return null;
    }

    public FieldConverter getFieldConverter(DataPersister datapersister)
    {
        switch (_cls1..SwitchMap.com.j256.ormlite.field.SqlType[datapersister.getSqlType().ordinal()])
        {
        default:
            return super.getFieldConverter(datapersister);

        case 1: // '\001'
            return DateStringType.getSingleton();
        }
    }

    public boolean isBatchUseTransaction()
    {
        return true;
    }

    public boolean isDatabaseUrlThisType(String s, String s1)
    {
        return true;
    }

    public boolean isNestedSavePointsSupported()
    {
        return false;
    }

    public void loadDriver()
    {
    }

    private class _cls1
    {

        static final int $SwitchMap$com$j256$ormlite$field$SqlType[];

        static 
        {
            $SwitchMap$com$j256$ormlite$field$SqlType = new int[SqlType.values().length];
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.DATE.ordinal()] = 1;
            }
            catch (NoSuchFieldError nosuchfielderror) { }
        }
    }

}
