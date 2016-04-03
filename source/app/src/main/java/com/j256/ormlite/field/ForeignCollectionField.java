// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field;

import java.lang.annotation.Annotation;

public interface ForeignCollectionField
    extends Annotation
{

    public static final int MAX_EAGER_LEVEL = 1;

    public abstract String columnName();

    public abstract boolean eager();

    public abstract String foreignColumnName();

    public abstract String foreignFieldName();

    public abstract int maxEagerForeignCollectionLevel();

    public abstract int maxEagerLevel();

    public abstract boolean orderAscending();

    public abstract String orderColumnName();
}
