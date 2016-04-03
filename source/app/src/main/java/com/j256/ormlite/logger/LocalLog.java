// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.j256.ormlite.logger:
//            Log, LoggerFactory

public class LocalLog
    implements Log
{

    private static final Log.Level DEFAULT_LEVEL;
    public static final String LOCAL_LOG_FILE_PROPERTY = "com.j256.ormlite.logger.file";
    public static final String LOCAL_LOG_LEVEL_PROPERTY = "com.j256.ormlite.logger.level";
    public static final String LOCAL_LOG_PROPERTIES_FILE = "/ormliteLocalLog.properties";
    private static final List classLevels = readLevelResourceFile(com/j256/ormlite/logger/LocalLog.getResourceAsStream("/ormliteLocalLog.properties"));
    private static final ThreadLocal dateFormatThreadLocal = new _cls1();
    private static PrintStream printStream;
    private final String className;
    private final Log.Level level;

    public LocalLog(String s)
    {
        className = LoggerFactory.getSimpleClassName(s);
        Log.Level level1 = null;
        Log.Level level2;
        if (classLevels != null)
        {
            Iterator iterator = classLevels.iterator();
            while (iterator.hasNext()) 
            {
                PatternLevel patternlevel = (PatternLevel)iterator.next();
                String s1;
                IllegalArgumentException illegalargumentexception;
                Log.Level level3;
                Log.Level level4;
                if (patternlevel.pattern.matcher(s).matches() && (level1 == null || patternlevel.level.ordinal() < level1.ordinal()))
                {
                    level4 = patternlevel.level;
                } else
                {
                    level4 = level1;
                }
                level1 = level4;
            }
            level2 = level1;
        } else
        {
            level2 = null;
        }
        if (level2 == null)
        {
            s1 = System.getProperty("com.j256.ormlite.logger.level");
            if (s1 == null)
            {
                level2 = DEFAULT_LEVEL;
            } else
            {
                try
                {
                    level3 = Log.Level.valueOf(s1.toUpperCase());
                }
                // Misplaced declaration of an exception variable
                catch (IllegalArgumentException illegalargumentexception)
                {
                    throw new IllegalArgumentException((new StringBuilder("Level '")).append(s1).append("' was not found").toString(), illegalargumentexception);
                }
                level2 = level3;
            }
        }
        level = level2;
    }

    private static List configureClassLevels(InputStream inputstream)
    {
        BufferedReader bufferedreader;
        ArrayList arraylist;
        bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
        arraylist = new ArrayList();
_L2:
        String as[];
        Pattern pattern;
        String s = bufferedreader.readLine();
        if (s == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (s.length() == 0 || s.charAt(0) == '#')
        {
            continue; /* Loop/switch isn't completed */
        }
        as = s.split("=");
        if (as.length != 2)
        {
            System.err.println((new StringBuilder("Line is not in the format of 'pattern = level': ")).append(s).toString());
            continue; /* Loop/switch isn't completed */
        }
        pattern = Pattern.compile(as[0].trim());
        Log.Level level1 = Log.Level.valueOf(as[1].trim());
        arraylist.add(new PatternLevel(pattern, level1));
        continue; /* Loop/switch isn't completed */
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        System.err.println((new StringBuilder("Level '")).append(as[1]).append("' was not found").toString());
        if (true) goto _L2; else goto _L1
_L1:
        return arraylist;
    }

    public static void openLogFile(String s)
    {
        if (s == null)
        {
            printStream = System.out;
            return;
        }
        try
        {
            printStream = new PrintStream(new File(s));
            return;
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            throw new IllegalArgumentException((new StringBuilder("Log file ")).append(s).append(" was not found").toString(), filenotfoundexception);
        }
    }

    private void printMessage(Log.Level level1, String s, Throwable throwable)
    {
        if (isLevelEnabled(level1))
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            stringbuilder.append(((DateFormat)dateFormatThreadLocal.get()).format(new Date()));
            stringbuilder.append(" [").append(level1.name()).append("] ");
            stringbuilder.append(className).append(' ');
            stringbuilder.append(s);
            printStream.println(stringbuilder.toString());
            if (throwable != null)
            {
                throwable.printStackTrace(printStream);
                return;
            }
        }
    }

    static List readLevelResourceFile(InputStream inputstream)
    {
        List list;
        list = null;
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_19;
        }
        List list1 = configureClassLevels(inputstream);
        list = list1;
        Exception exception;
        IOException ioexception1;
        IOException ioexception2;
        try
        {
            inputstream.close();
        }
        catch (IOException ioexception3)
        {
            return list;
        }
        return list;
        ioexception1;
        System.err.println((new StringBuilder("IO exception reading the log properties file '/ormliteLocalLog.properties': ")).append(ioexception1).toString());
        try
        {
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception2)
        {
            return null;
        }
        return null;
        exception;
        try
        {
            inputstream.close();
        }
        catch (IOException ioexception) { }
        throw exception;
    }

    void flush()
    {
        printStream.flush();
    }

    public boolean isLevelEnabled(Log.Level level1)
    {
        return level.isEnabled(level1);
    }

    public void log(Log.Level level1, String s)
    {
        printMessage(level1, s, null);
    }

    public void log(Log.Level level1, String s, Throwable throwable)
    {
        printMessage(level1, s, throwable);
    }

    static 
    {
        DEFAULT_LEVEL = Log.Level.DEBUG;
        openLogFile(System.getProperty("com.j256.ormlite.logger.file"));
    }

    private class PatternLevel
    {

        Log.Level level;
        Pattern pattern;

        public PatternLevel(Pattern pattern1, Log.Level level1)
        {
            pattern = pattern1;
            level = level1;
        }
    }


    private class _cls1 extends ThreadLocal
    {

        protected final volatile Object initialValue()
        {
            return initialValue();
        }

        protected final DateFormat initialValue()
        {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        }

        _cls1()
        {
        }
    }

}
