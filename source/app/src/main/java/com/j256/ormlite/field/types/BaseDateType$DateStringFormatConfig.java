// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class dateFormatStr
{

    final String dateFormatStr;
    private final ThreadLocal threadLocal = new _cls1();

    public DateFormat getDateFormat()
    {
        return (DateFormat)threadLocal.get();
    }

    public String toString()
    {
        return dateFormatStr;
    }

    public _cls1.this._cls0(String s)
    {
        class _cls1 extends ThreadLocal
        {

            final BaseDateType.DateStringFormatConfig this$0;

            protected volatile Object initialValue()
            {
                return initialValue();
            }

            protected DateFormat initialValue()
            {
                return new SimpleDateFormat(dateFormatStr);
            }

            _cls1()
            {
                this$0 = BaseDateType.DateStringFormatConfig.this;
                super();
            }
        }

        dateFormatStr = s;
    }
}
