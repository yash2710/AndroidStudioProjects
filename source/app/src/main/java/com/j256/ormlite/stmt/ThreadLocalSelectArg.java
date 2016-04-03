// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.field.SqlType;

// Referenced classes of package com.j256.ormlite.stmt:
//            BaseArgumentHolder, ArgumentHolder

public class ThreadLocalSelectArg extends BaseArgumentHolder
    implements ArgumentHolder
{

    private ThreadLocal threadValue;

    public ThreadLocalSelectArg()
    {
        threadValue = new ThreadLocal();
    }

    public ThreadLocalSelectArg(SqlType sqltype, Object obj)
    {
        super(sqltype);
        threadValue = new ThreadLocal();
        setValue(obj);
    }

    public ThreadLocalSelectArg(Object obj)
    {
        threadValue = new ThreadLocal();
        setValue(obj);
    }

    public ThreadLocalSelectArg(String s, Object obj)
    {
        super(s);
        threadValue = new ThreadLocal();
        setValue(obj);
    }

    protected Object getValue()
    {
        ValueWrapper valuewrapper = (ValueWrapper)threadValue.get();
        if (valuewrapper == null)
        {
            return null;
        } else
        {
            return valuewrapper.value;
        }
    }

    protected boolean isValueSet()
    {
        return threadValue.get() != null;
    }

    public void setValue(Object obj)
    {
        threadValue.set(new ValueWrapper(obj));
    }

    private class ValueWrapper
    {

        Object value;

        public ValueWrapper(Object obj)
        {
            value = obj;
        }
    }

}
