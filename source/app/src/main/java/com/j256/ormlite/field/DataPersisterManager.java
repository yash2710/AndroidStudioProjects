// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field;

import com.j256.ormlite.field.types.EnumStringType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.j256.ormlite.field:
//            DataType, DataPersister

public class DataPersisterManager
{

    private static final DataPersister DEFAULT_ENUM_PERSISTER = EnumStringType.getSingleton();
    private static final Map builtInMap;
    private static List registeredPersisters = null;

    private DataPersisterManager()
    {
    }

    public static void clear()
    {
        registeredPersisters = null;
    }

    public static DataPersister lookupForField(Field field)
    {
        Iterator iterator;
        if (registeredPersisters == null)
        {
            break MISSING_BLOCK_LABEL_88;
        }
        iterator = registeredPersisters.iterator();
_L4:
        DataPersister datapersister;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_88;
        }
        datapersister = (DataPersister)iterator.next();
        if (!datapersister.isValidForField(field)) goto _L2; else goto _L1
_L1:
        return datapersister;
_L2:
        Class aclass[];
        int i;
        int j;
        aclass = datapersister.getAssociatedClasses();
        i = aclass.length;
        j = 0;
_L6:
        if (j >= i) goto _L4; else goto _L3
_L3:
        Class class1 = aclass[j];
        if (field.getType() == class1) goto _L1; else goto _L5
_L5:
        j++;
          goto _L6
        datapersister = (DataPersister)builtInMap.get(field.getType().getName());
        if (datapersister == null)
        {
            if (field.getType().isEnum())
            {
                return DEFAULT_ENUM_PERSISTER;
            } else
            {
                return null;
            }
        }
          goto _L1
    }

    public static transient void registerDataPersisters(DataPersister adatapersister[])
    {
        ArrayList arraylist = new ArrayList();
        if (registeredPersisters != null)
        {
            arraylist.addAll(registeredPersisters);
        }
        int i = adatapersister.length;
        for (int j = 0; j < i; j++)
        {
            arraylist.add(adatapersister[j]);
        }

        registeredPersisters = arraylist;
    }

    static 
    {
        builtInMap = new HashMap();
        DataType adatatype[] = DataType.values();
        int i = adatatype.length;
        for (int j = 0; j < i; j++)
        {
            DataPersister datapersister = adatatype[j].getDataPersister();
            if (datapersister == null)
            {
                continue;
            }
            Class aclass[] = datapersister.getAssociatedClasses();
            int k = aclass.length;
            for (int l = 0; l < k; l++)
            {
                Class class1 = aclass[l];
                builtInMap.put(class1.getName(), datapersister);
            }

            if (datapersister.getAssociatedClassNames() == null)
            {
                continue;
            }
            String as[] = datapersister.getAssociatedClassNames();
            int i1 = as.length;
            for (int j1 = 0; j1 < i1; j1++)
            {
                String s = as[j1];
                builtInMap.put(s, datapersister);
            }

        }

    }
}
