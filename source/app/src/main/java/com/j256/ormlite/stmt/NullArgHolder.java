// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;

// Referenced classes of package com.j256.ormlite.stmt:
//            ArgumentHolder

public class NullArgHolder
    implements ArgumentHolder
{

    public NullArgHolder()
    {
    }

    public String getColumnName()
    {
        return "null-holder";
    }

    public FieldType getFieldType()
    {
        return null;
    }

    public Object getSqlArgValue()
    {
        return null;
    }

    public SqlType getSqlType()
    {
        return SqlType.STRING;
    }

    public void setMetaInfo(FieldType fieldtype)
    {
    }

    public void setMetaInfo(String s)
    {
    }

    public void setMetaInfo(String s, FieldType fieldtype)
    {
    }

    public void setValue(Object obj)
    {
        throw new UnsupportedOperationException((new StringBuilder("Cannot set null on ")).append(getClass()).toString());
    }
}
