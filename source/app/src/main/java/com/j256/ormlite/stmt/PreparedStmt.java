// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.DatabaseConnection;

// Referenced classes of package com.j256.ormlite.stmt:
//            GenericRowMapper

public interface PreparedStmt
    extends GenericRowMapper
{

    public abstract CompiledStatement compile(DatabaseConnection databaseconnection, StatementBuilder.StatementType statementtype);

    public abstract CompiledStatement compile(DatabaseConnection databaseconnection, StatementBuilder.StatementType statementtype, int i);

    public abstract String getStatement();

    public abstract StatementBuilder.StatementType getType();

    public abstract void setArgumentHolderValue(int i, Object obj);
}
