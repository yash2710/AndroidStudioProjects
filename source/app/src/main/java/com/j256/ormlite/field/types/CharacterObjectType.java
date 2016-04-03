// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDataType

public class CharacterObjectType extends BaseDataType
{

    private static final CharacterObjectType singleTon = new CharacterObjectType();

    private CharacterObjectType()
    {
        super(SqlType.CHAR, new Class[] {
            java/lang/Character
        });
    }

    protected CharacterObjectType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static CharacterObjectType getSingleton()
    {
        return singleTon;
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        if (s.length() != 1)
        {
            throw new SQLException((new StringBuilder("Problems with field ")).append(fieldtype).append(", default string to long for Character: '").append(s).append("'").toString());
        } else
        {
            return Character.valueOf(s.charAt(0));
        }
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return Character.valueOf(databaseresults.getChar(i));
    }

}
