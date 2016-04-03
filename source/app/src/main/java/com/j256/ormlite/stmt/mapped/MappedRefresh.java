// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

// Referenced classes of package com.j256.ormlite.stmt.mapped:
//            MappedQueryForId

public class MappedRefresh extends MappedQueryForId
{

    private MappedRefresh(TableInfo tableinfo, String s, FieldType afieldtype[], FieldType afieldtype1[])
    {
        super(tableinfo, s, afieldtype, afieldtype1, "refresh");
    }

    public static MappedRefresh build(DatabaseType databasetype, TableInfo tableinfo)
    {
        FieldType fieldtype = tableinfo.getIdField();
        if (fieldtype == null)
        {
            throw new SQLException((new StringBuilder("Cannot refresh ")).append(tableinfo.getDataClass()).append(" because it doesn't have an id field").toString());
        } else
        {
            String s = buildStatement(databasetype, tableinfo, fieldtype);
            FieldType afieldtype[] = new FieldType[1];
            afieldtype[0] = tableinfo.getIdField();
            return new MappedRefresh(tableinfo, s, afieldtype, tableinfo.getFieldTypes());
        }
    }

    public int executeRefresh(DatabaseConnection databaseconnection, Object obj, ObjectCache objectcache)
    {
        Object obj1 = super.execute(databaseconnection, idField.extractJavaFieldValue(obj), null);
        if (obj1 == null)
        {
            return 0;
        }
        FieldType afieldtype[] = resultsFieldTypes;
        int i = afieldtype.length;
        for (int j = 0; j < i; j++)
        {
            FieldType fieldtype = afieldtype[j];
            if (fieldtype != idField)
            {
                fieldtype.assignField(obj, fieldtype.extractJavaFieldValue(obj1), false, objectcache);
            }
        }

        return 1;
    }
}
