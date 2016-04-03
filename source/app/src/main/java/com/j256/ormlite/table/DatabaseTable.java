// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.table;

import java.lang.annotation.Annotation;

public interface DatabaseTable
    extends Annotation
{

    public abstract Class daoClass();

    public abstract String tableName();
}
