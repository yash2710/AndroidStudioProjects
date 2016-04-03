// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDataType

public class ByteObjectType extends BaseDataType
{

    private static final ByteObjectType singleTon = new ByteObjectType();

    private ByteObjectType()
    {
        super(SqlType.BYTE, new Class[] {
            java/lang/Byte
        });
    }

    protected ByteObjectType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static ByteObjectType getSingleton()
    {
        return singleTon;
    }

    public boolean isEscapedValue()
    {
        return false;
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        return Byte.valueOf(Byte.parseByte(s));
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return Byte.valueOf(databaseresults.getByte(i));
    }

}
