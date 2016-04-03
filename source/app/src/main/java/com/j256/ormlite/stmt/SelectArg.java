// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.field.SqlType;

// Referenced classes of package com.j256.ormlite.stmt:
//            BaseArgumentHolder, ArgumentHolder

public class SelectArg extends BaseArgumentHolder
    implements ArgumentHolder
{

    private boolean hasBeenSet;
    private Object value;

    public SelectArg()
    {
        hasBeenSet = false;
        value = null;
    }

    public SelectArg(SqlType sqltype)
    {
        super(sqltype);
        hasBeenSet = false;
        value = null;
    }

    public SelectArg(SqlType sqltype, Object obj)
    {
        super(sqltype);
        hasBeenSet = false;
        value = null;
        setValue(obj);
    }

    public SelectArg(Object obj)
    {
        hasBeenSet = false;
        value = null;
        setValue(obj);
    }

    public SelectArg(String s, Object obj)
    {
        super(s);
        hasBeenSet = false;
        value = null;
        setValue(obj);
    }

    protected Object getValue()
    {
        return value;
    }

    protected boolean isValueSet()
    {
        return hasBeenSet;
    }

    public void setValue(Object obj)
    {
        hasBeenSet = true;
        value = obj;
    }
}
