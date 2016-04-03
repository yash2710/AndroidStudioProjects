// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.table.TableInfo;

public class RawRowMapperImpl
    implements RawRowMapper
{

    private final TableInfo tableInfo;

    public RawRowMapperImpl(TableInfo tableinfo)
    {
        tableInfo = tableinfo;
    }

    public Object mapRow(String as[], String as1[])
    {
        Object obj = tableInfo.createObject();
        for (int i = 0; i < as.length; i++)
        {
            if (i < as1.length)
            {
                FieldType fieldtype = tableInfo.getFieldTypeByColumnName(as[i]);
                fieldtype.assignField(obj, fieldtype.convertStringToJavaField(as1[i], i), false, null);
            }
        }

        return obj;
    }
}
