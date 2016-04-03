// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.db;

import com.j256.ormlite.field.DataPersister;
import com.j256.ormlite.field.FieldConverter;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import java.sql.Driver;
import java.util.List;

public interface DatabaseType
{

    public abstract void addPrimaryKeySql(FieldType afieldtype[], List list, List list1, List list2, List list3);

    public abstract void addUniqueComboSql(FieldType afieldtype[], List list, List list1, List list2, List list3);

    public abstract void appendColumnArg(String s, StringBuilder stringbuilder, FieldType fieldtype, List list, List list1, List list2, List list3);

    public abstract void appendCreateTableSuffix(StringBuilder stringbuilder);

    public abstract void appendEscapedEntityName(StringBuilder stringbuilder, String s);

    public abstract void appendEscapedWord(StringBuilder stringbuilder, String s);

    public abstract void appendInsertNoColumns(StringBuilder stringbuilder);

    public abstract void appendLimitValue(StringBuilder stringbuilder, long l, Long long1);

    public abstract void appendOffsetValue(StringBuilder stringbuilder, long l);

    public abstract void appendSelectNextValFromSequence(StringBuilder stringbuilder, String s);

    public abstract void dropColumnArg(FieldType fieldtype, List list, List list1);

    public abstract DatabaseTableConfig extractDatabaseTableConfig(ConnectionSource connectionsource, Class class1);

    public abstract String generateIdSequenceName(String s, FieldType fieldtype);

    public abstract String getCommentLinePrefix();

    public abstract String getDatabaseName();

    public abstract FieldConverter getFieldConverter(DataPersister datapersister);

    public abstract String getPingStatement();

    public abstract boolean isAllowGeneratedIdInsertSupported();

    public abstract boolean isBatchUseTransaction();

    public abstract boolean isCreateIfNotExistsSupported();

    public abstract boolean isCreateIndexIfNotExistsSupported();

    public abstract boolean isCreateTableReturnsNegative();

    public abstract boolean isCreateTableReturnsZero();

    public abstract boolean isDatabaseUrlThisType(String s, String s1);

    public abstract boolean isEntityNamesMustBeUpCase();

    public abstract boolean isIdSequenceNeeded();

    public abstract boolean isLimitAfterSelect();

    public abstract boolean isLimitSqlSupported();

    public abstract boolean isNestedSavePointsSupported();

    public abstract boolean isOffsetLimitArgument();

    public abstract boolean isOffsetSqlSupported();

    public abstract boolean isSelectSequenceBeforeInsert();

    public abstract boolean isTruncateSupported();

    public abstract boolean isVarcharFieldWidthSupported();

    public abstract void loadDriver();

    public abstract void setDriver(Driver driver);
}
