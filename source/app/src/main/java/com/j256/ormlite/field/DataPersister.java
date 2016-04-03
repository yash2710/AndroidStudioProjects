// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field;

import java.lang.reflect.Field;

// Referenced classes of package com.j256.ormlite.field:
//            FieldConverter, FieldType

public interface DataPersister
    extends FieldConverter
{

    public abstract Object convertIdNumber(Number number);

    public abstract boolean dataIsEqual(Object obj, Object obj1);

    public abstract Object generateId();

    public abstract String[] getAssociatedClassNames();

    public abstract Class[] getAssociatedClasses();

    public abstract int getDefaultWidth();

    public abstract Class getPrimaryClass();

    public abstract boolean isAppropriateId();

    public abstract boolean isArgumentHolderRequired();

    public abstract boolean isComparable();

    public abstract boolean isEscapedDefaultValue();

    public abstract boolean isEscapedValue();

    public abstract boolean isPrimitive();

    public abstract boolean isSelfGeneratedId();

    public abstract boolean isValidForField(Field field);

    public abstract boolean isValidForVersion();

    public abstract boolean isValidGeneratedType();

    public abstract Object makeConfigObject(FieldType fieldtype);

    public abstract Object moveToNextValue(Object obj);
}
