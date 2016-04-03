// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.table.TableInfo;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt:
//            StatementBuilder, PreparedDelete

public class DeleteBuilder extends StatementBuilder
{

    public DeleteBuilder(DatabaseType databasetype, TableInfo tableinfo, Dao dao)
    {
        super(databasetype, tableinfo, dao, StatementBuilder.StatementType.DELETE);
    }

    protected void appendStatementEnd(StringBuilder stringbuilder, List list)
    {
    }

    protected void appendStatementStart(StringBuilder stringbuilder, List list)
    {
        stringbuilder.append("DELETE FROM ");
        databaseType.appendEscapedEntityName(stringbuilder, tableInfo.getTableName());
        stringbuilder.append(' ');
    }

    public void clear()
    {
        reset();
    }

    public int delete()
    {
        return dao.delete(prepare());
    }

    public PreparedDelete prepare()
    {
        return super.prepareStatement(null);
    }

    public void reset()
    {
        super.reset();
    }
}
