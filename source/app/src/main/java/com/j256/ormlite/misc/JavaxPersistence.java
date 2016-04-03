// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.misc;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.DataPersisterManager;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseFieldConfig;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

// Referenced classes of package com.j256.ormlite.misc:
//            SqlExceptionUtil

public class JavaxPersistence
{

    public JavaxPersistence()
    {
    }

    public static DatabaseFieldConfig createFieldConfig(DatabaseType databasetype, Field field)
    {
        Annotation annotation = null;
        Annotation annotation1 = null;
        Annotation annotation2 = null;
        Annotation annotation3 = null;
        Annotation annotation4 = null;
        Annotation annotation5 = null;
        Annotation annotation6 = null;
        Annotation annotation7 = null;
        Annotation annotation8 = null;
        Annotation aannotation[] = field.getAnnotations();
        int i = aannotation.length;
        int j = 0;
        while (j < i) 
        {
            Annotation annotation9 = aannotation[j];
            Class class1 = annotation9.annotationType();
            if (class1.getName().equals("javax.persistence.Column"))
            {
                annotation = annotation9;
            }
            if (class1.getName().equals("javax.persistence.Basic"))
            {
                annotation1 = annotation9;
            }
            if (class1.getName().equals("javax.persistence.Id"))
            {
                annotation2 = annotation9;
            }
            if (class1.getName().equals("javax.persistence.GeneratedValue"))
            {
                annotation3 = annotation9;
            }
            if (class1.getName().equals("javax.persistence.OneToOne"))
            {
                annotation4 = annotation9;
            }
            if (class1.getName().equals("javax.persistence.ManyToOne"))
            {
                annotation5 = annotation9;
            }
            if (class1.getName().equals("javax.persistence.JoinColumn"))
            {
                annotation6 = annotation9;
            }
            if (class1.getName().equals("javax.persistence.Enumerated"))
            {
                annotation7 = annotation9;
            }
            DatabaseFieldConfig databasefieldconfig;
            String s;
            boolean flag;
            Exception exception;
            Object obj;
            Exception exception1;
            String s1;
            Object obj1;
            Exception exception2;
            String s2;
            Boolean boolean1;
            Boolean boolean2;
            Exception exception3;
            Boolean boolean3;
            Exception exception4;
            String s3;
            String s4;
            Boolean boolean4;
            Boolean boolean5;
            if (!class1.getName().equals("javax.persistence.Version"))
            {
                annotation9 = annotation8;
            }
            j++;
            annotation8 = annotation9;
        }
        if (annotation == null && annotation1 == null && annotation2 == null && annotation4 == null && annotation5 == null && annotation7 == null && annotation8 == null)
        {
            return null;
        }
        databasefieldconfig = new DatabaseFieldConfig();
        s = field.getName();
        if (databasetype.isEntityNamesMustBeUpCase())
        {
            s = s.toUpperCase();
        }
        databasefieldconfig.setFieldName(s);
        if (annotation == null)
        {
            break MISSING_BLOCK_LABEL_502;
        }
        try
        {
            s3 = (String)annotation.getClass().getMethod("name", new Class[0]).invoke(annotation, new Object[0]);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception4)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problem accessing fields from the @Column annotation for field ")).append(field).toString(), exception4);
        }
        if (s3 == null)
        {
            break MISSING_BLOCK_LABEL_342;
        }
        if (s3.length() > 0)
        {
            databasefieldconfig.setColumnName(s3);
        }
        s4 = (String)annotation.getClass().getMethod("columnDefinition", new Class[0]).invoke(annotation, new Object[0]);
        if (s4 == null)
        {
            break MISSING_BLOCK_LABEL_388;
        }
        if (s4.length() > 0)
        {
            databasefieldconfig.setColumnDefinition(s4);
        }
        databasefieldconfig.setWidth(((Integer)annotation.getClass().getMethod("length", new Class[0]).invoke(annotation, new Object[0])).intValue());
        boolean4 = (Boolean)annotation.getClass().getMethod("nullable", new Class[0]).invoke(annotation, new Object[0]);
        if (boolean4 == null)
        {
            break MISSING_BLOCK_LABEL_461;
        }
        databasefieldconfig.setCanBeNull(boolean4.booleanValue());
        boolean5 = (Boolean)annotation.getClass().getMethod("unique", new Class[0]).invoke(annotation, new Object[0]);
        if (boolean5 == null)
        {
            break MISSING_BLOCK_LABEL_502;
        }
        databasefieldconfig.setUnique(boolean5.booleanValue());
        if (annotation1 == null) goto _L2; else goto _L1
_L1:
        try
        {
            boolean3 = (Boolean)annotation1.getClass().getMethod("optional", new Class[0]).invoke(annotation1, new Object[0]);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception3)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problem accessing fields from the @Basic annotation for field ")).append(field).toString(), exception3);
        }
        if (boolean3 != null) goto _L4; else goto _L3
_L3:
        databasefieldconfig.setCanBeNull(true);
_L2:
        if (annotation2 != null)
        {
            if (annotation3 == null)
            {
                databasefieldconfig.setId(true);
            } else
            {
                databasefieldconfig.setGeneratedId(true);
            }
        }
        if (annotation4 == null && annotation5 == null) goto _L6; else goto _L5
_L5:
        if (!java/util/Collection.isAssignableFrom(field.getType()) && !com/j256/ormlite/dao/ForeignCollection.isAssignableFrom(field.getType())) goto _L8; else goto _L7
_L7:
        databasefieldconfig.setForeignCollection(true);
        if (annotation6 == null) goto _L6; else goto _L9
_L9:
        try
        {
            s1 = (String)annotation6.getClass().getMethod("name", new Class[0]).invoke(annotation6, new Object[0]);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception1)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problem accessing fields from the @JoinColumn annotation for field ")).append(field).toString(), exception1);
        }
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_652;
        }
        if (s1.length() > 0)
        {
            databasefieldconfig.setForeignCollectionColumnName(s1);
        }
        obj1 = annotation6.getClass().getMethod("fetch", new Class[0]).invoke(annotation6, new Object[0]);
        if (obj1 == null) goto _L6; else goto _L10
_L10:
        if (obj1.toString().equals("EAGER"))
        {
            databasefieldconfig.setForeignCollectionEager(true);
        }
_L6:
        if (annotation7 == null)
        {
            break MISSING_BLOCK_LABEL_757;
        }
        try
        {
            obj = annotation7.getClass().getMethod("value", new Class[0]).invoke(annotation7, new Object[0]);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problem accessing fields from the @Enumerated annotation for field ")).append(field).toString(), exception);
        }
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_1080;
        }
        if (!obj.toString().equals("STRING"))
        {
            break MISSING_BLOCK_LABEL_1080;
        }
        databasefieldconfig.setDataType(DataType.ENUM_STRING);
_L13:
        if (annotation8 != null)
        {
            databasefieldconfig.setVersion(true);
        }
        if (databasefieldconfig.getDataPersister() == null)
        {
            databasefieldconfig.setDataPersister(DataPersisterManager.lookupForField(field));
        }
        if (DatabaseFieldConfig.findGetMethod(field, false) != null && DatabaseFieldConfig.findSetMethod(field, false) != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        databasefieldconfig.setUseGetSet(flag);
        return databasefieldconfig;
_L4:
        databasefieldconfig.setCanBeNull(boolean3.booleanValue());
          goto _L2
_L8:
        databasefieldconfig.setForeign(true);
        if (annotation6 == null) goto _L6; else goto _L11
_L11:
        try
        {
            s2 = (String)annotation6.getClass().getMethod("name", new Class[0]).invoke(annotation6, new Object[0]);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception2)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problem accessing fields from the @JoinColumn annotation for field ")).append(field).toString(), exception2);
        }
        if (s2 == null)
        {
            break MISSING_BLOCK_LABEL_967;
        }
        if (s2.length() > 0)
        {
            databasefieldconfig.setColumnName(s2);
        }
        boolean1 = (Boolean)annotation6.getClass().getMethod("nullable", new Class[0]).invoke(annotation6, new Object[0]);
        if (boolean1 == null)
        {
            break MISSING_BLOCK_LABEL_1010;
        }
        databasefieldconfig.setCanBeNull(boolean1.booleanValue());
        boolean2 = (Boolean)annotation6.getClass().getMethod("unique", new Class[0]).invoke(annotation6, new Object[0]);
        if (boolean2 == null) goto _L6; else goto _L12
_L12:
        databasefieldconfig.setUnique(boolean2.booleanValue());
          goto _L6
        databasefieldconfig.setDataType(DataType.ENUM_INTEGER);
          goto _L13
    }

    public static String getEntityName(Class class1)
    {
        Annotation aannotation[] = class1.getAnnotations();
        int i = aannotation.length;
        int j = 0;
        Annotation annotation = null;
        while (j < i) 
        {
            Annotation annotation1 = aannotation[j];
            Exception exception;
            String s;
            int k;
            if (!annotation1.annotationType().getName().equals("javax.persistence.Entity"))
            {
                annotation1 = annotation;
            }
            j++;
            annotation = annotation1;
        }
        if (annotation != null) goto _L2; else goto _L1
_L1:
        s = null;
_L4:
        return s;
_L2:
        try
        {
            s = (String)annotation.getClass().getMethod("name", new Class[0]).invoke(annotation, new Object[0]);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            throw new IllegalStateException((new StringBuilder("Could not get entity name from class ")).append(class1).toString(), exception);
        }
        if (s == null)
        {
            break; /* Loop/switch isn't completed */
        }
        k = s.length();
        if (k > 0) goto _L4; else goto _L3
_L3:
        return null;
    }
}
