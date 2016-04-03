// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field;

import com.j256.ormlite.misc.SqlExceptionUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

// Referenced classes of package com.j256.ormlite.field:
//            DatabaseFieldConfig, DataType, DataPersister

public class DatabaseFieldConfigLoader
{

    private static final String CONFIG_FILE_END_MARKER = "# --field-end--";
    private static final String CONFIG_FILE_START_MARKER = "# --field-start--";
    private static final DataPersister DEFAULT_DATA_PERSISTER;
    private static final int DEFAULT_MAX_EAGER_FOREIGN_COLLECTION_LEVEL = 1;
    private static final String FIELD_NAME_ALLOW_GENERATED_ID_INSERT = "allowGeneratedIdInsert";
    private static final String FIELD_NAME_CAN_BE_NULL = "canBeNull";
    private static final String FIELD_NAME_COLUMN_DEFINITION = "columnDefinition";
    private static final String FIELD_NAME_COLUMN_NAME = "columnName";
    private static final String FIELD_NAME_DATA_PERSISTER = "dataPersister";
    private static final String FIELD_NAME_DEFAULT_VALUE = "defaultValue";
    private static final String FIELD_NAME_FIELD_NAME = "fieldName";
    private static final String FIELD_NAME_FOREIGN = "foreign";
    private static final String FIELD_NAME_FOREIGN_AUTO_CREATE = "foreignAutoCreate";
    private static final String FIELD_NAME_FOREIGN_AUTO_REFRESH = "foreignAutoRefresh";
    private static final String FIELD_NAME_FOREIGN_COLLECTION = "foreignCollection";
    private static final String FIELD_NAME_FOREIGN_COLLECTION_COLUMN_NAME = "foreignCollectionColumnName";
    private static final String FIELD_NAME_FOREIGN_COLLECTION_EAGER = "foreignCollectionEager";
    private static final String FIELD_NAME_FOREIGN_COLLECTION_FOREIGN_FIELD_NAME = "foreignCollectionForeignFieldName";
    private static final String FIELD_NAME_FOREIGN_COLLECTION_FOREIGN_FIELD_NAME_OLD = "foreignCollectionForeignColumnName";
    private static final String FIELD_NAME_FOREIGN_COLLECTION_ORDER_ASCENDING = "foreignCollectionOrderAscending";
    private static final String FIELD_NAME_FOREIGN_COLLECTION_ORDER_COLUMN_NAME = "foreignCollectionOrderColumnName";
    private static final String FIELD_NAME_FOREIGN_COLLECTION_ORDER_COLUMN_NAME_OLD = "foreignCollectionOrderColumn";
    private static final String FIELD_NAME_FOREIGN_COLUMN_NAME = "foreignColumnName";
    private static final String FIELD_NAME_FORMAT = "format";
    private static final String FIELD_NAME_GENERATED_ID = "generatedId";
    private static final String FIELD_NAME_GENERATED_ID_SEQUENCE = "generatedIdSequence";
    private static final String FIELD_NAME_ID = "id";
    private static final String FIELD_NAME_INDEX = "index";
    private static final String FIELD_NAME_INDEX_NAME = "indexName";
    private static final String FIELD_NAME_MAX_EAGER_FOREIGN_COLLECTION_LEVEL = "foreignCollectionMaxEagerLevel";
    private static final String FIELD_NAME_MAX_EAGER_FOREIGN_COLLECTION_LEVEL_OLD = "maxEagerForeignCollectionLevel";
    private static final String FIELD_NAME_MAX_FOREIGN_AUTO_REFRESH_LEVEL = "maxForeignAutoRefreshLevel";
    private static final String FIELD_NAME_PERSISTER_CLASS = "persisterClass";
    private static final String FIELD_NAME_READ_ONLY = "readOnly";
    private static final String FIELD_NAME_THROW_IF_NULL = "throwIfNull";
    private static final String FIELD_NAME_UNIQUE = "unique";
    private static final String FIELD_NAME_UNIQUE_COMBO = "uniqueCombo";
    private static final String FIELD_NAME_UNIQUE_INDEX = "uniqueIndex";
    private static final String FIELD_NAME_UNIQUE_INDEX_NAME = "uniqueIndexName";
    private static final String FIELD_NAME_UNKNOWN_ENUM_VALUE = "unknownEnumValue";
    private static final String FIELD_NAME_USE_GET_SET = "useGetSet";
    private static final String FIELD_NAME_VERSION = "version";
    private static final String FIELD_NAME_WIDTH = "width";

    public DatabaseFieldConfigLoader()
    {
    }

    public static DatabaseFieldConfig fromReader(BufferedReader bufferedreader)
    {
        DatabaseFieldConfig databasefieldconfig = new DatabaseFieldConfig();
        boolean flag = false;
        do
        {
            String s;
            try
            {
                s = bufferedreader.readLine();
            }
            catch (IOException ioexception)
            {
                throw SqlExceptionUtil.create("Could not read DatabaseFieldConfig from stream", ioexception);
            }
            if (s == null || s.equals("# --field-end--"))
            {
                break;
            }
            if (s.length() != 0 && !s.startsWith("#") && !s.equals("# --field-start--"))
            {
                String as[] = s.split("=", -2);
                if (as.length != 2)
                {
                    throw new SQLException((new StringBuilder("DatabaseFieldConfig reading from stream cannot parse line: ")).append(s).toString());
                }
                readField(databasefieldconfig, as[0], as[1]);
                flag = true;
            }
        } while (true);
        if (flag)
        {
            return databasefieldconfig;
        } else
        {
            return null;
        }
    }

    private static void readField(DatabaseFieldConfig databasefieldconfig, String s, String s1)
    {
        boolean flag = false;
        if (!s.equals("fieldName")) goto _L2; else goto _L1
_L1:
        databasefieldconfig.setFieldName(s1);
_L4:
        return;
_L2:
        if (s.equals("columnName"))
        {
            databasefieldconfig.setColumnName(s1);
            return;
        }
        if (s.equals("dataPersister"))
        {
            databasefieldconfig.setDataPersister(DataType.valueOf(s1).getDataPersister());
            return;
        }
        if (s.equals("defaultValue"))
        {
            databasefieldconfig.setDefaultValue(s1);
            return;
        }
        if (s.equals("width"))
        {
            databasefieldconfig.setWidth(Integer.parseInt(s1));
            return;
        }
        if (s.equals("canBeNull"))
        {
            databasefieldconfig.setCanBeNull(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("id"))
        {
            databasefieldconfig.setId(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("generatedId"))
        {
            databasefieldconfig.setGeneratedId(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("generatedIdSequence"))
        {
            databasefieldconfig.setGeneratedIdSequence(s1);
            return;
        }
        if (s.equals("foreign"))
        {
            databasefieldconfig.setForeign(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("useGetSet"))
        {
            databasefieldconfig.setUseGetSet(Boolean.parseBoolean(s1));
            return;
        }
        if (!s.equals("unknownEnumValue"))
        {
            break; /* Loop/switch isn't completed */
        }
        String as[] = s1.split("#", -2);
        if (as.length != 2)
        {
            throw new IllegalArgumentException((new StringBuilder("Invalid value for unknownEnumValue which should be in class#name format: ")).append(s1).toString());
        }
        Class class1;
        Object aobj[];
        try
        {
            class1 = Class.forName(as[0]);
        }
        catch (ClassNotFoundException classnotfoundexception1)
        {
            throw new IllegalArgumentException((new StringBuilder("Unknown class specified for unknownEnumValue: ")).append(s1).toString());
        }
        aobj = class1.getEnumConstants();
        if (aobj == null)
        {
            throw new IllegalArgumentException((new StringBuilder("Invalid class is not an Enum for unknownEnumValue: ")).append(s1).toString());
        }
        Enum aenum[] = (Enum[])aobj;
        int i = aenum.length;
        for (int j = 0; j < i; j++)
        {
            Enum enum = aenum[j];
            if (enum.name().equals(as[1]))
            {
                databasefieldconfig.setUnknownEnumValue(enum);
                flag = true;
            }
        }

        if (!flag)
        {
            throw new IllegalArgumentException((new StringBuilder("Invalid enum value name for unknownEnumvalue: ")).append(s1).toString());
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (s.equals("throwIfNull"))
        {
            databasefieldconfig.setThrowIfNull(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("format"))
        {
            databasefieldconfig.setFormat(s1);
            return;
        }
        if (s.equals("unique"))
        {
            databasefieldconfig.setUnique(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("uniqueCombo"))
        {
            databasefieldconfig.setUniqueCombo(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("index"))
        {
            databasefieldconfig.setIndex(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("indexName"))
        {
            databasefieldconfig.setIndex(true);
            databasefieldconfig.setIndexName(s1);
            return;
        }
        if (s.equals("uniqueIndex"))
        {
            databasefieldconfig.setUniqueIndex(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("uniqueIndexName"))
        {
            databasefieldconfig.setUniqueIndex(true);
            databasefieldconfig.setUniqueIndexName(s1);
            return;
        }
        if (s.equals("foreignAutoRefresh"))
        {
            databasefieldconfig.setForeignAutoRefresh(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("maxForeignAutoRefreshLevel"))
        {
            databasefieldconfig.setMaxForeignAutoRefreshLevel(Integer.parseInt(s1));
            return;
        }
        if (s.equals("persisterClass"))
        {
            try
            {
                databasefieldconfig.setPersisterClass(Class.forName(s1));
                return;
            }
            catch (ClassNotFoundException classnotfoundexception)
            {
                throw new IllegalArgumentException((new StringBuilder("Could not find persisterClass: ")).append(s1).toString());
            }
        }
        if (s.equals("allowGeneratedIdInsert"))
        {
            databasefieldconfig.setAllowGeneratedIdInsert(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("columnDefinition"))
        {
            databasefieldconfig.setColumnDefinition(s1);
            return;
        }
        if (s.equals("foreignAutoCreate"))
        {
            databasefieldconfig.setForeignAutoCreate(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("version"))
        {
            databasefieldconfig.setVersion(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("foreignColumnName"))
        {
            databasefieldconfig.setForeignColumnName(s1);
            return;
        }
        if (s.equals("readOnly"))
        {
            databasefieldconfig.setReadOnly(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("foreignCollection"))
        {
            databasefieldconfig.setForeignCollection(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("foreignCollectionEager"))
        {
            databasefieldconfig.setForeignCollectionEager(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("maxEagerForeignCollectionLevel"))
        {
            databasefieldconfig.setForeignCollectionMaxEagerLevel(Integer.parseInt(s1));
            return;
        }
        if (s.equals("foreignCollectionMaxEagerLevel"))
        {
            databasefieldconfig.setForeignCollectionMaxEagerLevel(Integer.parseInt(s1));
            return;
        }
        if (s.equals("foreignCollectionColumnName"))
        {
            databasefieldconfig.setForeignCollectionColumnName(s1);
            return;
        }
        if (s.equals("foreignCollectionOrderColumn"))
        {
            databasefieldconfig.setForeignCollectionOrderColumnName(s1);
            return;
        }
        if (s.equals("foreignCollectionOrderColumnName"))
        {
            databasefieldconfig.setForeignCollectionOrderColumnName(s1);
            return;
        }
        if (s.equals("foreignCollectionOrderAscending"))
        {
            databasefieldconfig.setForeignCollectionOrderAscending(Boolean.parseBoolean(s1));
            return;
        }
        if (s.equals("foreignCollectionForeignColumnName"))
        {
            databasefieldconfig.setForeignCollectionForeignFieldName(s1);
            return;
        }
        if (s.equals("foreignCollectionForeignFieldName"))
        {
            databasefieldconfig.setForeignCollectionForeignFieldName(s1);
            return;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public static void write(BufferedWriter bufferedwriter, DatabaseFieldConfig databasefieldconfig, String s)
    {
        try
        {
            writeConfig(bufferedwriter, databasefieldconfig, s);
            return;
        }
        catch (IOException ioexception)
        {
            throw SqlExceptionUtil.create("Could not write config to writer", ioexception);
        }
    }

    public static void writeConfig(BufferedWriter bufferedwriter, DatabaseFieldConfig databasefieldconfig, String s)
    {
        bufferedwriter.append("# --field-start--");
        bufferedwriter.newLine();
        if (databasefieldconfig.getFieldName() != null)
        {
            bufferedwriter.append("fieldName").append('=').append(databasefieldconfig.getFieldName());
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.getColumnName() != null)
        {
            bufferedwriter.append("columnName").append('=').append(databasefieldconfig.getColumnName());
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.getDataPersister() != DEFAULT_DATA_PERSISTER)
        {
            DataType adatatype[] = DataType.values();
            int i = adatatype.length;
            int j = 0;
label0:
            do
            {
label1:
                {
                    boolean flag = false;
                    if (j < i)
                    {
                        DataType datatype = adatatype[j];
                        if (datatype.getDataPersister() != databasefieldconfig.getDataPersister())
                        {
                            break label1;
                        }
                        bufferedwriter.append("dataPersister").append('=').append(datatype.name());
                        bufferedwriter.newLine();
                        flag = true;
                    }
                    if (!flag)
                    {
                        throw new IllegalArgumentException((new StringBuilder("Unknown data persister field: ")).append(databasefieldconfig.getDataPersister()).toString());
                    }
                    break label0;
                }
                j++;
            } while (true);
        }
        if (databasefieldconfig.getDefaultValue() != null)
        {
            bufferedwriter.append("defaultValue").append('=').append(databasefieldconfig.getDefaultValue());
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.getWidth() != 0)
        {
            bufferedwriter.append("width").append('=').append(Integer.toString(databasefieldconfig.getWidth()));
            bufferedwriter.newLine();
        }
        if (!databasefieldconfig.isCanBeNull())
        {
            bufferedwriter.append("canBeNull").append('=').append(Boolean.toString(databasefieldconfig.isCanBeNull()));
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.isId())
        {
            bufferedwriter.append("id").append('=').append("true");
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.isGeneratedId())
        {
            bufferedwriter.append("generatedId").append('=').append("true");
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.getGeneratedIdSequence() != null)
        {
            bufferedwriter.append("generatedIdSequence").append('=').append(databasefieldconfig.getGeneratedIdSequence());
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.isForeign())
        {
            bufferedwriter.append("foreign").append('=').append("true");
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.isUseGetSet())
        {
            bufferedwriter.append("useGetSet").append('=').append("true");
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.getUnknownEnumValue() != null)
        {
            bufferedwriter.append("unknownEnumValue").append('=').append(databasefieldconfig.getUnknownEnumValue().getClass().getName()).append("#").append(databasefieldconfig.getUnknownEnumValue().name());
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.isThrowIfNull())
        {
            bufferedwriter.append("throwIfNull").append('=').append("true");
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.getFormat() != null)
        {
            bufferedwriter.append("format").append('=').append(databasefieldconfig.getFormat());
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.isUnique())
        {
            bufferedwriter.append("unique").append('=').append("true");
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.isUniqueCombo())
        {
            bufferedwriter.append("uniqueCombo").append('=').append("true");
            bufferedwriter.newLine();
        }
        String s1 = databasefieldconfig.getIndexName(s);
        if (s1 != null)
        {
            bufferedwriter.append("indexName").append('=').append(s1);
            bufferedwriter.newLine();
        }
        String s2 = databasefieldconfig.getUniqueIndexName(s);
        if (s2 != null)
        {
            bufferedwriter.append("uniqueIndexName").append('=').append(s2);
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.isForeignAutoRefresh())
        {
            bufferedwriter.append("foreignAutoRefresh").append('=').append("true");
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.getMaxForeignAutoRefreshLevel() != -1)
        {
            bufferedwriter.append("maxForeignAutoRefreshLevel").append('=').append(Integer.toString(databasefieldconfig.getMaxForeignAutoRefreshLevel()));
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.getPersisterClass() != DatabaseFieldConfig.DEFAULT_PERSISTER_CLASS)
        {
            bufferedwriter.append("persisterClass").append('=').append(databasefieldconfig.getPersisterClass().getName());
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.isAllowGeneratedIdInsert())
        {
            bufferedwriter.append("allowGeneratedIdInsert").append('=').append("true");
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.getColumnDefinition() != null)
        {
            bufferedwriter.append("columnDefinition").append('=').append(databasefieldconfig.getColumnDefinition());
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.isForeignAutoCreate())
        {
            bufferedwriter.append("foreignAutoCreate").append('=').append("true");
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.isVersion())
        {
            bufferedwriter.append("version").append('=').append("true");
            bufferedwriter.newLine();
        }
        String s3 = databasefieldconfig.getForeignColumnName();
        if (s3 != null)
        {
            bufferedwriter.append("foreignColumnName").append('=').append(s3);
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.isReadOnly())
        {
            bufferedwriter.append("readOnly").append('=').append("true");
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.isForeignCollection())
        {
            bufferedwriter.append("foreignCollection").append('=').append("true");
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.isForeignCollectionEager())
        {
            bufferedwriter.append("foreignCollectionEager").append('=').append("true");
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.getForeignCollectionMaxEagerLevel() != 1)
        {
            bufferedwriter.append("foreignCollectionMaxEagerLevel").append('=').append(Integer.toString(databasefieldconfig.getForeignCollectionMaxEagerLevel()));
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.getForeignCollectionColumnName() != null)
        {
            bufferedwriter.append("foreignCollectionColumnName").append('=').append(databasefieldconfig.getForeignCollectionColumnName());
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.getForeignCollectionOrderColumnName() != null)
        {
            bufferedwriter.append("foreignCollectionOrderColumnName").append('=').append(databasefieldconfig.getForeignCollectionOrderColumnName());
            bufferedwriter.newLine();
        }
        if (!databasefieldconfig.isForeignCollectionOrderAscending())
        {
            bufferedwriter.append("foreignCollectionOrderAscending").append('=').append(Boolean.toString(databasefieldconfig.isForeignCollectionOrderAscending()));
            bufferedwriter.newLine();
        }
        if (databasefieldconfig.getForeignCollectionForeignFieldName() != null)
        {
            bufferedwriter.append("foreignCollectionForeignFieldName").append('=').append(databasefieldconfig.getForeignCollectionForeignFieldName());
            bufferedwriter.newLine();
        }
        bufferedwriter.append("# --field-end--");
        bufferedwriter.newLine();
    }

    static 
    {
        DEFAULT_DATA_PERSISTER = DatabaseFieldConfig.DEFAULT_DATA_TYPE.getDataPersister();
    }
}
