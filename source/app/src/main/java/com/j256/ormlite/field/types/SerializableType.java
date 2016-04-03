// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDataType

public class SerializableType extends BaseDataType
{

    private static final SerializableType singleTon = new SerializableType();

    private SerializableType()
    {
        super(SqlType.SERIALIZABLE, new Class[0]);
    }

    protected SerializableType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static SerializableType getSingleton()
    {
        return singleTon;
    }

    public Class getPrimaryClass()
    {
        return java/io/Serializable;
    }

    public boolean isAppropriateId()
    {
        return false;
    }

    public boolean isArgumentHolderRequired()
    {
        return true;
    }

    public boolean isComparable()
    {
        return false;
    }

    public boolean isStreamType()
    {
        return true;
    }

    public boolean isValidForField(Field field)
    {
        return java/io/Serializable.isAssignableFrom(field.getType());
    }

    public Object javaToSqlArg(FieldType fieldtype, Object obj)
    {
        ObjectOutputStream objectoutputstream = null;
        ByteArrayOutputStream bytearrayoutputstream;
        ObjectOutputStream objectoutputstream1;
        bytearrayoutputstream = new ByteArrayOutputStream();
        objectoutputstream1 = new ObjectOutputStream(bytearrayoutputstream);
        objectoutputstream1.writeObject(obj);
        objectoutputstream1.close();
        byte abyte0[] = bytearrayoutputstream.toByteArray();
        return abyte0;
        Exception exception;
        exception;
_L4:
        throw SqlExceptionUtil.create((new StringBuilder("Could not write serialized object to byte array: ")).append(obj).toString(), exception);
        Exception exception1;
        exception1;
_L2:
        if (objectoutputstream != null)
        {
            try
            {
                objectoutputstream.close();
            }
            catch (IOException ioexception) { }
        }
        throw exception1;
        exception1;
        objectoutputstream = objectoutputstream1;
        if (true) goto _L2; else goto _L1
_L1:
        exception;
        objectoutputstream = objectoutputstream1;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        throw new SQLException("Default values for serializable types are not supported");
    }

    public Object resultStringToJava(FieldType fieldtype, String s, int i)
    {
        throw new SQLException("Serializable type cannot be converted from string to Java");
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return databaseresults.getBytes(i);
    }

    public Object sqlArgToJava(FieldType fieldtype, Object obj, int i)
    {
        byte abyte0[] = (byte[])obj;
        ObjectInputStream objectinputstream = new ObjectInputStream(new ByteArrayInputStream(abyte0));
        Object obj1 = objectinputstream.readObject();
        Exception exception;
        Exception exception1;
        try
        {
            objectinputstream.close();
        }
        catch (IOException ioexception1)
        {
            return obj1;
        }
        return obj1;
        exception;
        objectinputstream = null;
_L4:
        throw SqlExceptionUtil.create((new StringBuilder("Could not read serialized object from byte array: ")).append(Arrays.toString(abyte0)).append("(len ").append(abyte0.length).append(")").toString(), exception);
        exception1;
_L2:
        if (objectinputstream != null)
        {
            try
            {
                objectinputstream.close();
            }
            catch (IOException ioexception) { }
        }
        throw exception1;
        exception1;
        objectinputstream = null;
        if (true) goto _L2; else goto _L1
_L1:
        exception;
        if (true) goto _L4; else goto _L3
_L3:
    }

}
