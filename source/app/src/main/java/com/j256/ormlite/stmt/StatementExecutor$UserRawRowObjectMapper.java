// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.RawRowObjectMapper;
import com.j256.ormlite.field.DataPersister;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.support.DatabaseResults;

// Referenced classes of package com.j256.ormlite.stmt:
//            GenericRowMapper

class columnTypes
    implements GenericRowMapper
{

    private String columnNames[];
    private final DataType columnTypes[];
    private final RawRowObjectMapper mapper;

    private String[] getColumnNames(DatabaseResults databaseresults)
    {
        if (columnNames != null)
        {
            return columnNames;
        } else
        {
            columnNames = databaseresults.getColumnNames();
            return columnNames;
        }
    }

    public Object mapRow(DatabaseResults databaseresults)
    {
        int i = databaseresults.getColumnCount();
        Object aobj[] = new Object[i];
        int j = 0;
        while (j < i) 
        {
            if (j >= columnTypes.length)
            {
                aobj[j] = null;
            } else
            {
                aobj[j] = columnTypes[j].getDataPersister().resultToJava(null, databaseresults, j);
            }
            j++;
        }
        return mapper.mapRow(getColumnNames(databaseresults), columnTypes, aobj);
    }

    public (RawRowObjectMapper rawrowobjectmapper, DataType adatatype[])
    {
        mapper = rawrowobjectmapper;
        columnTypes = adatatype;
    }
}
