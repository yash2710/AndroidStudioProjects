// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;

public interface ArgumentHolder
{

    public abstract String getColumnName();

    public abstract FieldType getFieldType();

    public abstract Object getSqlArgValue();

    public abstract SqlType getSqlType();

    public abstract void setMetaInfo(FieldType fieldtype);

    public abstract void setMetaInfo(String s);

    public abstract void setMetaInfo(String s, FieldType fieldtype);

    public abstract void setValue(Object obj);
}
