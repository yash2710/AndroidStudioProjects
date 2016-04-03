// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDataType

public class StringType extends BaseDataType
{

    public static int DEFAULT_WIDTH = 255;
    private static final StringType singleTon = new StringType();

    private StringType()
    {
        super(SqlType.STRING, new Class[] {
            java/lang/String
        });
    }

    protected StringType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static StringType getSingleton()
    {
        return singleTon;
    }

    public int getDefaultWidth()
    {
        return DEFAULT_WIDTH;
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        return s;
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return databaseresults.getString(i);
    }

}
