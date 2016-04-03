// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

class this._cls0 extends ThreadLocal
{

    final teFormatStr this$0;

    protected volatile Object initialValue()
    {
        return initialValue();
    }

    protected DateFormat initialValue()
    {
        return new SimpleDateFormat(teFormatStr);
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
