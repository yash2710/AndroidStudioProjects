// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt.query:
//            Clause

interface Comparison
    extends Clause
{

    public abstract void appendOperation(StringBuilder stringbuilder);

    public abstract void appendValue(DatabaseType databasetype, StringBuilder stringbuilder, List list);

    public abstract String getColumnName();
}
