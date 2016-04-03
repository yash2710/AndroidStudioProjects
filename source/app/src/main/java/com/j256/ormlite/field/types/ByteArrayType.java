// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;
import java.util.Arrays;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDataType

public class ByteArrayType extends BaseDataType
{

    private static final ByteArrayType singleTon = new ByteArrayType();

    private ByteArrayType()
    {
        super(SqlType.BYTE_ARRAY, new Class[0]);
    }

    protected ByteArrayType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static ByteArrayType getSingleton()
    {
        return singleTon;
    }

    public boolean dataIsEqual(Object obj, Object obj1)
    {
        boolean flag;
        if (obj == null)
        {
            flag = false;
            if (obj1 == null)
            {
                flag = true;
            }
        } else
        {
            flag = false;
            if (obj1 != null)
            {
                return Arrays.equals((byte[])obj, (byte[])obj1);
            }
        }
        return flag;
    }

    public Class getPrimaryClass()
    {
        return [B;
    }

    public boolean isAppropriateId()
    {
        return false;
    }

    public boolean isArgumentHolderRequired()
    {
        return true;
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        throw new SQLException("byte[] type cannot have default values");
    }

    public Object resultStringToJava(FieldType fieldtype, String s, int i)
    {
        throw new SQLException("byte[] type cannot be converted from string to Java");
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return (byte[])databaseresults.getBytes(i);
    }

}
