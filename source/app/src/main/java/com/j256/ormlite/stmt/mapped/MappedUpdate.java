// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

// Referenced classes of package com.j256.ormlite.stmt.mapped:
//            BaseMappedStatement

public class MappedUpdate extends BaseMappedStatement
{

    private final FieldType versionFieldType;
    private final int versionFieldTypeIndex;

    private MappedUpdate(TableInfo tableinfo, String s, FieldType afieldtype[], FieldType fieldtype, int i)
    {
        super(tableinfo, s, afieldtype);
        versionFieldType = fieldtype;
        versionFieldTypeIndex = i;
    }

    public static MappedUpdate build(DatabaseType databasetype, TableInfo tableinfo)
    {
        FieldType fieldtype = tableinfo.getIdField();
        if (fieldtype == null)
        {
            throw new SQLException((new StringBuilder("Cannot update ")).append(tableinfo.getDataClass()).append(" because it doesn't have an id field").toString());
        }
        StringBuilder stringbuilder = new StringBuilder(64);
        appendTableName(databasetype, stringbuilder, "UPDATE ", tableinfo.getTableName());
        FieldType fieldtype1 = null;
        int i = -1;
        FieldType afieldtype[] = tableinfo.getFieldTypes();
        int j = afieldtype.length;
        int k = 0;
        int l = 0;
        for (; k < j; k++)
        {
            FieldType fieldtype3 = afieldtype[k];
            if (!isFieldUpdatable(fieldtype3, fieldtype))
            {
                continue;
            }
            if (fieldtype3.isVersion())
            {
                i = l;
                fieldtype1 = fieldtype3;
            }
            l++;
        }

        int i1 = l + 1;
        if (fieldtype1 != null)
        {
            i1++;
        }
        FieldType afieldtype1[] = new FieldType[i1];
        int j1 = 0;
        FieldType afieldtype2[] = tableinfo.getFieldTypes();
        int k1 = afieldtype2.length;
        boolean flag = true;
        int l1 = 0;
        while (l1 < k1) 
        {
            FieldType fieldtype2 = afieldtype2[l1];
            int i2;
            boolean flag1;
            int j2;
            if (isFieldUpdatable(fieldtype2, fieldtype))
            {
                int k2;
                if (flag)
                {
                    stringbuilder.append("SET ");
                    flag = false;
                } else
                {
                    stringbuilder.append(", ");
                }
                appendFieldColumnName(databasetype, stringbuilder, fieldtype2, null);
                k2 = j1 + 1;
                afieldtype1[j1] = fieldtype2;
                stringbuilder.append("= ?");
                flag1 = flag;
                j2 = k2;
            } else
            {
                flag1 = flag;
                j2 = j1;
            }
            l1++;
            j1 = j2;
            flag = flag1;
        }
        stringbuilder.append(' ');
        appendWhereFieldEq(databasetype, fieldtype, stringbuilder, null);
        i2 = j1 + 1;
        afieldtype1[j1] = fieldtype;
        if (fieldtype1 != null)
        {
            stringbuilder.append(" AND ");
            appendFieldColumnName(databasetype, stringbuilder, fieldtype1, null);
            stringbuilder.append("= ?");
            afieldtype1[i2] = fieldtype1;
        }
        return new MappedUpdate(tableinfo, stringbuilder.toString(), afieldtype1, fieldtype1, i);
    }

    private static boolean isFieldUpdatable(FieldType fieldtype, FieldType fieldtype1)
    {
        return fieldtype != fieldtype1 && !fieldtype.isForeignCollection() && !fieldtype.isReadOnly();
    }

    public int update(DatabaseConnection databaseconnection, Object obj, ObjectCache objectcache)
    {
        int i = 0;
        if (argFieldTypes.length <= 1)
        {
            return 0;
        }
        Object aobj[] = getFieldObjects(obj);
        if (versionFieldType == null) goto _L2; else goto _L1
_L1:
        Object obj5;
        Object obj4 = versionFieldType.extractJavaFieldValue(obj);
        obj5 = versionFieldType.moveToNextValue(obj4);
        aobj[versionFieldTypeIndex] = versionFieldType.convertJavaFieldToSqlArgValue(obj5);
        Object obj1 = obj5;
_L8:
        int j;
        Object obj3;
        FieldType afieldtype[];
        int k;
        try
        {
            j = databaseconnection.update(statement, aobj, argFieldTypes);
        }
        catch (SQLException sqlexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Unable to run update stmt on object ")).append(obj).append(": ").append(statement).toString(), sqlexception);
        }
        if (j <= 0) goto _L4; else goto _L3
_L3:
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_109;
        }
        versionFieldType.assignField(obj, obj1, false, null);
        if (objectcache == null) goto _L4; else goto _L5
_L5:
        Object obj2 = idField.extractJavaFieldValue(obj);
        obj3 = objectcache.get(clazz, obj2);
        if (obj3 == null || obj3 == obj) goto _L4; else goto _L6
_L6:
        afieldtype = tableInfo.getFieldTypes();
        k = afieldtype.length;
_L9:
        if (i >= k) goto _L4; else goto _L7
_L7:
        FieldType fieldtype = afieldtype[i];
        if (fieldtype != idField)
        {
            fieldtype.assignField(obj3, fieldtype.extractJavaFieldValue(obj), false, objectcache);
        }
        break MISSING_BLOCK_LABEL_287;
_L4:
        logger.debug("update data with statement '{}' and {} args, changed {} rows", statement, Integer.valueOf(aobj.length), Integer.valueOf(j));
        if (aobj.length > 0)
        {
            logger.trace("update arguments: {}", ((Object) (aobj)));
        }
        return j;
_L2:
        obj1 = null;
          goto _L8
        i++;
          goto _L9
    }
}
