// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.android;

import android.database.Cursor;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.db.SqliteAndroidDatabaseType;
import com.j256.ormlite.support.DatabaseResults;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AndroidDatabaseResults
    implements DatabaseResults
{

    private static final int MIN_NUM_COLUMN_NAMES_MAP = 8;
    private static final DatabaseType databaseType = new SqliteAndroidDatabaseType();
    private final Map columnNameMap;
    private final String columnNames[];
    private final Cursor cursor;
    private final ObjectCache objectCache;

    public AndroidDatabaseResults(Cursor cursor1, ObjectCache objectcache)
    {
        cursor = cursor1;
        columnNames = cursor1.getColumnNames();
        if (columnNames.length >= 8)
        {
            columnNameMap = new HashMap();
            for (int i = 0; i < columnNames.length; i++)
            {
                columnNameMap.put(columnNames[i], Integer.valueOf(i));
            }

        } else
        {
            columnNameMap = null;
        }
        objectCache = objectcache;
    }

    public AndroidDatabaseResults(Cursor cursor1, boolean flag, ObjectCache objectcache)
    {
        this(cursor1, objectcache);
    }

    private int lookupColumn(String s)
    {
        if (columnNameMap == null)
        {
            for (int i = 0; i < columnNames.length; i++)
            {
                if (columnNames[i].equals(s))
                {
                    return i;
                }
            }

            return -1;
        }
        Integer integer = (Integer)columnNameMap.get(s);
        if (integer == null)
        {
            return -1;
        } else
        {
            return integer.intValue();
        }
    }

    public void close()
    {
        cursor.close();
    }

    public void closeQuietly()
    {
        close();
    }

    public int findColumn(String s)
    {
        int i = lookupColumn(s);
        if (i < 0)
        {
            StringBuilder stringbuilder = new StringBuilder(4 + s.length());
            databaseType.appendEscapedEntityName(stringbuilder, s);
            i = lookupColumn(stringbuilder.toString());
            if (i < 0)
            {
                String as[] = cursor.getColumnNames();
                throw new SQLException((new StringBuilder("Unknown field '")).append(s).append("' from the Android sqlite cursor, not in:").append(Arrays.toString(as)).toString());
            }
        }
        return i;
    }

    public boolean first()
    {
        return cursor.moveToFirst();
    }

    public BigDecimal getBigDecimal(int i)
    {
        throw new SQLException("Android does not support BigDecimal type.  Use BIG_DECIMAL or BIG_DECIMAL_STRING types");
    }

    public InputStream getBlobStream(int i)
    {
        return new ByteArrayInputStream(cursor.getBlob(i));
    }

    public boolean getBoolean(int i)
    {
        return !cursor.isNull(i) && cursor.getShort(i) != 0;
    }

    public byte getByte(int i)
    {
        return (byte)getShort(i);
    }

    public byte[] getBytes(int i)
    {
        return cursor.getBlob(i);
    }

    public char getChar(int i)
    {
        String s = cursor.getString(i);
        if (s == null || s.length() == 0)
        {
            return '\0';
        }
        if (s.length() == 1)
        {
            return s.charAt(0);
        } else
        {
            throw new SQLException((new StringBuilder("More than 1 character stored in database column: ")).append(i).toString());
        }
    }

    public int getColumnCount()
    {
        return cursor.getColumnCount();
    }

    public String[] getColumnNames()
    {
        int i = getColumnCount();
        String as[] = new String[i];
        for (int j = 0; j < i; j++)
        {
            as[j] = cursor.getColumnName(j);
        }

        return as;
    }

    public int getCount()
    {
        return cursor.getCount();
    }

    public double getDouble(int i)
    {
        return cursor.getDouble(i);
    }

    public float getFloat(int i)
    {
        return cursor.getFloat(i);
    }

    public int getInt(int i)
    {
        return cursor.getInt(i);
    }

    public long getLong(int i)
    {
        return cursor.getLong(i);
    }

    public ObjectCache getObjectCache()
    {
        return objectCache;
    }

    public int getPosition()
    {
        return cursor.getPosition();
    }

    public Cursor getRawCursor()
    {
        return cursor;
    }

    public short getShort(int i)
    {
        return cursor.getShort(i);
    }

    public String getString(int i)
    {
        return cursor.getString(i);
    }

    public Timestamp getTimestamp(int i)
    {
        throw new SQLException("Android does not support timestamp.  Use JAVA_DATE_LONG or JAVA_DATE_STRING types");
    }

    public boolean last()
    {
        return cursor.moveToLast();
    }

    public boolean moveAbsolute(int i)
    {
        return cursor.moveToPosition(i);
    }

    public boolean moveRelative(int i)
    {
        return cursor.move(i);
    }

    public boolean next()
    {
        return cursor.moveToNext();
    }

    public boolean previous()
    {
        return cursor.moveToPrevious();
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append("@").append(Integer.toHexString(super.hashCode())).toString();
    }

    public boolean wasNull(int i)
    {
        return cursor.isNull(i);
    }

}
