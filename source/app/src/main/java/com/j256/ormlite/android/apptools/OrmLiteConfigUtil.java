// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.android.apptools;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.db.SqliteAndroidDatabaseType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DatabaseFieldConfig;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.DatabaseTableConfigLoader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrmLiteConfigUtil
{

    protected static final String RAW_DIR_NAME = "raw";
    protected static final String RESOURCE_DIR_NAME = "res";
    private static final DatabaseType databaseType = new SqliteAndroidDatabaseType();
    protected static int maxFindSourceLevel = 20;

    public OrmLiteConfigUtil()
    {
    }

    private static boolean classHasAnnotations(Class class1)
    {
_L5:
        if (class1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (class1.getAnnotation(com/j256/ormlite/table/DatabaseTable) == null) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        Field afield[];
        int i;
        int j;
        try
        {
            afield = class1.getDeclaredFields();
        }
        catch (Throwable throwable)
        {
            System.err.println((new StringBuilder("Could not load get delcared fields from: ")).append(class1).toString());
            System.err.println((new StringBuilder("     ")).append(throwable).toString());
            return false;
        }
        i = afield.length;
        j = 0;
label0:
        do
        {
label1:
            {
                if (j >= i)
                {
                    break label1;
                }
                Field field = afield[j];
                if (field.getAnnotation(com/j256/ormlite/field/DatabaseField) != null || field.getAnnotation(com/j256/ormlite/field/ForeignCollectionField) != null)
                {
                    break label0;
                }
                j++;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        Class class2;
        try
        {
            class2 = class1.getSuperclass();
        }
        catch (Throwable throwable1)
        {
            System.err.println((new StringBuilder("Could not get super class for: ")).append(class1).toString());
            System.err.println((new StringBuilder("     ")).append(throwable1).toString());
            return false;
        }
        class1 = class2;
        if (true) goto _L5; else goto _L4
_L4:
        return false;
    }

    private static void findAnnotatedClasses(List list, File file, int i)
    {
        File afile[];
        int j;
        int k;
        afile = file.listFiles();
        j = afile.length;
        k = 0;
_L2:
        File file1;
        if (k >= j)
        {
            break MISSING_BLOCK_LABEL_334;
        }
        file1 = afile[k];
        if (!file1.isDirectory())
        {
            break; /* Loop/switch isn't completed */
        }
        if (i < maxFindSourceLevel)
        {
            findAnnotatedClasses(list, file1, i + 1);
        }
_L4:
        k++;
        if (true) goto _L2; else goto _L1
_L1:
        if (!file1.getName().endsWith(".java")) goto _L4; else goto _L3
_L3:
        String s;
label0:
        {
            s = getPackageOfClass(file1);
            if (s != null)
            {
                break label0;
            }
            System.err.println((new StringBuilder("Could not find package name for: ")).append(file1).toString());
        }
          goto _L4
        String s3;
        String s1 = file1.getName();
        String s2 = s1.substring(0, -5 + s1.length());
        s3 = (new StringBuilder()).append(s).append(".").append(s2).toString();
        Class class1 = Class.forName(s3);
        if (classHasAnnotations(class1))
        {
            list.add(class1);
        }
        Class aclass[];
        int l;
        aclass = class1.getDeclaredClasses();
        l = aclass.length;
        int i1 = 0;
_L6:
        if (i1 >= l) goto _L4; else goto _L5
_L5:
        Class class2 = aclass[i1];
        if (classHasAnnotations(class2))
        {
            list.add(class2);
        }
        i1++;
          goto _L6
        Throwable throwable;
        throwable;
        System.err.println((new StringBuilder("Could not load class file for: ")).append(file1).toString());
        System.err.println((new StringBuilder("     ")).append(throwable).toString());
          goto _L4
        Throwable throwable1;
        throwable1;
        System.err.println((new StringBuilder("Could not load inner classes for: ")).append(class1).toString());
        System.err.println((new StringBuilder("     ")).append(throwable1).toString());
          goto _L4
    }

    protected static File findRawDir(File file)
    {
        for (int i = 0; file != null && i < 20; i++)
        {
            File file1 = findResRawDir(file);
            if (file1 != null)
            {
                return file1;
            }
            file = file.getParentFile();
        }

        return null;
    }

    private static File findResRawDir(File file)
    {
        File afile[] = file.listFiles();
        int i = afile.length;
        for (int j = 0; j < i; j++)
        {
            File file1 = afile[j];
            if (!file1.getName().equals("res") || !file1.isDirectory())
            {
                continue;
            }
            File afile1[] = file1.listFiles(new _cls1());
            if (afile1.length == 1)
            {
                return afile1[0];
            }
        }

        return null;
    }

    private static String getPackageOfClass(File file)
    {
        BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
_L2:
        String s = bufferedreader.readLine();
        if (s == null)
        {
            bufferedreader.close();
            return null;
        }
        if (!s.contains("package")) goto _L2; else goto _L1
_L1:
        String as[] = s.split("[ \t;]");
        if (as.length <= 1 || !as[0].equals("package")) goto _L2; else goto _L3
_L3:
        String s1 = as[1];
        bufferedreader.close();
        return s1;
        Exception exception;
        exception;
        bufferedreader.close();
        throw exception;
    }

    public static void main(String args[])
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Main can take a single file-name argument.");
        } else
        {
            writeConfigFile(args[0]);
            return;
        }
    }

    public static void writeConfigFile(File file)
    {
        writeConfigFile(file, new File("."));
    }

    public static void writeConfigFile(File file, File file1)
    {
        ArrayList arraylist = new ArrayList();
        findAnnotatedClasses(arraylist, file1, 0);
        writeConfigFile(file, (Class[])arraylist.toArray(new Class[arraylist.size()]));
    }

    public static void writeConfigFile(File file, Class aclass[])
    {
        System.out.println((new StringBuilder("Writing configurations to ")).append(file.getAbsolutePath()).toString());
        writeConfigFile(((OutputStream) (new FileOutputStream(file))), aclass);
    }

    public static void writeConfigFile(OutputStream outputstream, File file)
    {
        ArrayList arraylist = new ArrayList();
        findAnnotatedClasses(arraylist, file, 0);
        writeConfigFile(outputstream, (Class[])arraylist.toArray(new Class[arraylist.size()]));
    }

    public static void writeConfigFile(OutputStream outputstream, Class aclass[])
    {
        BufferedWriter bufferedwriter = new BufferedWriter(new OutputStreamWriter(outputstream), 4096);
        int i;
        writeHeader(bufferedwriter);
        i = aclass.length;
        int j = 0;
_L2:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        writeConfigForTable(bufferedwriter, aclass[j]);
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        System.out.println("Done.");
        bufferedwriter.close();
        return;
        Exception exception;
        exception;
        bufferedwriter.close();
        throw exception;
    }

    public static void writeConfigFile(String s)
    {
        ArrayList arraylist = new ArrayList();
        findAnnotatedClasses(arraylist, new File("."), 0);
        writeConfigFile(s, (Class[])arraylist.toArray(new Class[arraylist.size()]));
    }

    public static void writeConfigFile(String s, Class aclass[])
    {
        File file = findRawDir(new File("."));
        if (file == null)
        {
            System.err.println("Could not find raw directory which is typically in the res directory");
            return;
        } else
        {
            writeConfigFile(new File(file, s), aclass);
            return;
        }
    }

    private static void writeConfigForTable(BufferedWriter bufferedwriter, Class class1)
    {
        String s;
        ArrayList arraylist;
        Class class2;
        s = DatabaseTableConfig.extractTableName(class1);
        arraylist = new ArrayList();
        class2 = class1;
_L2:
        if (class2 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        Field afield[];
        int i;
        int j;
        Field field;
        DatabaseFieldConfig databasefieldconfig;
        Class class3;
        try
        {
            afield = class2.getDeclaredFields();
            i = afield.length;
        }
        catch (Error error)
        {
            System.err.println((new StringBuilder("Skipping ")).append(class1).append(" because we got an error finding its definition: ").append(error.getMessage()).toString());
            return;
        }
        for (j = 0; j >= i; j++)
        {
            break MISSING_BLOCK_LABEL_78;
        }

        field = afield[j];
        databasefieldconfig = DatabaseFieldConfig.fromField(databaseType, s, field);
        if (databasefieldconfig == null)
        {
            break MISSING_BLOCK_LABEL_221;
        }
        arraylist.add(databasefieldconfig);
        break MISSING_BLOCK_LABEL_221;
        class3 = class2.getSuperclass();
        class2 = class3;
        if (true) goto _L2; else goto _L1
_L1:
        if (arraylist.isEmpty())
        {
            System.out.println((new StringBuilder("Skipping ")).append(class1).append(" because no annotated fields found").toString());
            return;
        } else
        {
            DatabaseTableConfigLoader.write(bufferedwriter, new DatabaseTableConfig(class1, s, arraylist));
            bufferedwriter.append("#################################");
            bufferedwriter.newLine();
            System.out.println((new StringBuilder("Wrote config for ")).append(class1).toString());
            return;
        }
    }

    private static void writeHeader(BufferedWriter bufferedwriter)
    {
        bufferedwriter.append('#');
        bufferedwriter.newLine();
        bufferedwriter.append("# generated on ").append((new SimpleDateFormat("yyyy/MM/dd hh:mm:ss")).format(new Date()));
        bufferedwriter.newLine();
        bufferedwriter.append('#');
        bufferedwriter.newLine();
    }


    private class _cls1
        implements FileFilter
    {

        public final boolean accept(File file)
        {
            return file.getName().equals("raw") && file.isDirectory();
        }

        _cls1()
        {
        }
    }

}
