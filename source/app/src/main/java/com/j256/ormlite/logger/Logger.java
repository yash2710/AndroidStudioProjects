// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.logger;

import java.util.Arrays;

// Referenced classes of package com.j256.ormlite.logger:
//            Log

public class Logger
{

    private static final String ARG_STRING = "{}";
    private static final int ARG_STRING_LENGTH = 2;
    private static final Object UNKNOWN_ARG = new Object();
    private final Log log;

    public Logger(Log log1)
    {
        log = log1;
    }

    private void appendArg(StringBuilder stringbuilder, Object obj)
    {
label0:
        {
            if (obj != UNKNOWN_ARG)
            {
                if (obj != null)
                {
                    break label0;
                }
                stringbuilder.append("null");
            }
            return;
        }
        if (obj.getClass().isArray())
        {
            stringbuilder.append(Arrays.toString((Object[])obj));
            return;
        } else
        {
            stringbuilder.append(obj);
            return;
        }
    }

    private String buildFullMessage(String s, Object obj, Object obj1, Object obj2, Object aobj[])
    {
        int i = 0;
        StringBuilder stringbuilder = null;
        int j = 0;
        do
        {
            int k = s.indexOf("{}", i);
            if (k != -1)
            {
                if (stringbuilder == null)
                {
                    stringbuilder = new StringBuilder(128);
                }
                stringbuilder.append(s, i, k);
                i = k + ARG_STRING_LENGTH;
                if (aobj == null)
                {
                    if (j == 0)
                    {
                        appendArg(stringbuilder, obj);
                    } else
                    if (j == 1)
                    {
                        appendArg(stringbuilder, obj1);
                    } else
                    if (j == 2)
                    {
                        appendArg(stringbuilder, obj2);
                    }
                } else
                if (j < aobj.length)
                {
                    appendArg(stringbuilder, aobj[j]);
                }
                j++;
            } else
            if (stringbuilder == null)
            {
                return s;
            } else
            {
                stringbuilder.append(s, i, s.length());
                return stringbuilder.toString();
            }
        } while (true);
    }

    private void innerLog(Log.Level level, Throwable throwable, String s, Object obj, Object obj1, Object obj2, Object aobj[])
    {
        String s1;
label0:
        {
            if (log.isLevelEnabled(level))
            {
                s1 = buildFullMessage(s, obj, obj1, obj2, aobj);
                if (throwable != null)
                {
                    break label0;
                }
                log.log(level, s1);
            }
            return;
        }
        log.log(level, s1, throwable);
    }

    public void debug(String s)
    {
        innerLog(Log.Level.DEBUG, null, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void debug(String s, Object obj)
    {
        innerLog(Log.Level.DEBUG, null, s, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void debug(String s, Object obj, Object obj1)
    {
        innerLog(Log.Level.DEBUG, null, s, obj, obj1, UNKNOWN_ARG, null);
    }

    public void debug(String s, Object obj, Object obj1, Object obj2)
    {
        innerLog(Log.Level.DEBUG, null, s, obj, obj1, obj2, null);
    }

    public void debug(String s, Object aobj[])
    {
        innerLog(Log.Level.DEBUG, null, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, aobj);
    }

    public void debug(Throwable throwable, String s)
    {
        innerLog(Log.Level.DEBUG, throwable, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void debug(Throwable throwable, String s, Object obj)
    {
        innerLog(Log.Level.DEBUG, throwable, s, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void debug(Throwable throwable, String s, Object obj, Object obj1)
    {
        innerLog(Log.Level.DEBUG, throwable, s, obj, obj1, UNKNOWN_ARG, null);
    }

    public void debug(Throwable throwable, String s, Object obj, Object obj1, Object obj2)
    {
        innerLog(Log.Level.DEBUG, throwable, s, obj, obj1, obj2, null);
    }

    public void debug(Throwable throwable, String s, Object aobj[])
    {
        innerLog(Log.Level.DEBUG, throwable, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, aobj);
    }

    public void error(String s)
    {
        innerLog(Log.Level.ERROR, null, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void error(String s, Object obj)
    {
        innerLog(Log.Level.ERROR, null, s, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void error(String s, Object obj, Object obj1)
    {
        innerLog(Log.Level.ERROR, null, s, obj, obj1, UNKNOWN_ARG, null);
    }

    public void error(String s, Object obj, Object obj1, Object obj2)
    {
        innerLog(Log.Level.ERROR, null, s, obj, obj1, obj2, null);
    }

    public void error(String s, Object aobj[])
    {
        innerLog(Log.Level.ERROR, null, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, aobj);
    }

    public void error(Throwable throwable, String s)
    {
        innerLog(Log.Level.ERROR, throwable, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void error(Throwable throwable, String s, Object obj)
    {
        innerLog(Log.Level.ERROR, throwable, s, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void error(Throwable throwable, String s, Object obj, Object obj1)
    {
        innerLog(Log.Level.ERROR, throwable, s, obj, obj1, UNKNOWN_ARG, null);
    }

    public void error(Throwable throwable, String s, Object obj, Object obj1, Object obj2)
    {
        innerLog(Log.Level.ERROR, throwable, s, obj, obj1, obj2, null);
    }

    public void error(Throwable throwable, String s, Object aobj[])
    {
        innerLog(Log.Level.ERROR, throwable, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, aobj);
    }

    public void fatal(String s)
    {
        innerLog(Log.Level.FATAL, null, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void fatal(String s, Object obj)
    {
        innerLog(Log.Level.FATAL, null, s, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void fatal(String s, Object obj, Object obj1)
    {
        innerLog(Log.Level.FATAL, null, s, obj, obj1, UNKNOWN_ARG, null);
    }

    public void fatal(String s, Object obj, Object obj1, Object obj2)
    {
        innerLog(Log.Level.FATAL, null, s, obj, obj1, obj2, null);
    }

    public void fatal(String s, Object aobj[])
    {
        innerLog(Log.Level.FATAL, null, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, aobj);
    }

    public void fatal(Throwable throwable, String s)
    {
        innerLog(Log.Level.FATAL, throwable, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void fatal(Throwable throwable, String s, Object obj)
    {
        innerLog(Log.Level.FATAL, throwable, s, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void fatal(Throwable throwable, String s, Object obj, Object obj1)
    {
        innerLog(Log.Level.FATAL, throwable, s, obj, obj1, UNKNOWN_ARG, null);
    }

    public void fatal(Throwable throwable, String s, Object obj, Object obj1, Object obj2)
    {
        innerLog(Log.Level.FATAL, throwable, s, obj, obj1, obj2, null);
    }

    public void fatal(Throwable throwable, String s, Object aobj[])
    {
        innerLog(Log.Level.FATAL, throwable, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, aobj);
    }

    public void info(String s)
    {
        innerLog(Log.Level.INFO, null, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void info(String s, Object obj)
    {
        innerLog(Log.Level.INFO, null, s, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void info(String s, Object obj, Object obj1)
    {
        innerLog(Log.Level.INFO, null, s, obj, obj1, UNKNOWN_ARG, null);
    }

    public void info(String s, Object obj, Object obj1, Object obj2)
    {
        innerLog(Log.Level.INFO, null, s, obj, obj1, obj2, null);
    }

    public void info(String s, Object aobj[])
    {
        innerLog(Log.Level.INFO, null, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, aobj);
    }

    public void info(Throwable throwable, String s)
    {
        innerLog(Log.Level.INFO, throwable, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void info(Throwable throwable, String s, Object obj)
    {
        innerLog(Log.Level.INFO, throwable, s, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void info(Throwable throwable, String s, Object obj, Object obj1)
    {
        innerLog(Log.Level.INFO, throwable, s, obj, obj1, UNKNOWN_ARG, null);
    }

    public void info(Throwable throwable, String s, Object obj, Object obj1, Object obj2)
    {
        innerLog(Log.Level.INFO, throwable, s, obj, obj1, obj2, null);
    }

    public void info(Throwable throwable, String s, Object aobj[])
    {
        innerLog(Log.Level.INFO, throwable, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, aobj);
    }

    public boolean isLevelEnabled(Log.Level level)
    {
        return log.isLevelEnabled(level);
    }

    public void log(Log.Level level, String s)
    {
        innerLog(level, null, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void log(Log.Level level, String s, Object obj)
    {
        innerLog(level, null, s, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void log(Log.Level level, String s, Object obj, Object obj1)
    {
        innerLog(level, null, s, obj, obj1, UNKNOWN_ARG, null);
    }

    public void log(Log.Level level, String s, Object obj, Object obj1, Object obj2)
    {
        innerLog(level, null, s, obj, obj1, obj2, null);
    }

    public void log(Log.Level level, String s, Object aobj[])
    {
        innerLog(level, null, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, aobj);
    }

    public void log(Log.Level level, Throwable throwable, String s)
    {
        innerLog(level, throwable, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void log(Log.Level level, Throwable throwable, String s, Object obj)
    {
        innerLog(level, throwable, s, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void log(Log.Level level, Throwable throwable, String s, Object obj, Object obj1)
    {
        innerLog(level, throwable, s, obj, obj1, UNKNOWN_ARG, null);
    }

    public void log(Log.Level level, Throwable throwable, String s, Object obj, Object obj1, Object obj2)
    {
        innerLog(level, throwable, s, obj, obj1, obj2, null);
    }

    public void log(Log.Level level, Throwable throwable, String s, Object aobj[])
    {
        innerLog(level, throwable, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, aobj);
    }

    public void trace(String s)
    {
        innerLog(Log.Level.TRACE, null, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void trace(String s, Object obj)
    {
        innerLog(Log.Level.TRACE, null, s, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void trace(String s, Object obj, Object obj1)
    {
        innerLog(Log.Level.TRACE, null, s, obj, obj1, UNKNOWN_ARG, null);
    }

    public void trace(String s, Object obj, Object obj1, Object obj2)
    {
        innerLog(Log.Level.TRACE, null, s, obj, obj1, obj2, null);
    }

    public void trace(String s, Object aobj[])
    {
        innerLog(Log.Level.TRACE, null, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, aobj);
    }

    public void trace(Throwable throwable, String s)
    {
        innerLog(Log.Level.TRACE, throwable, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void trace(Throwable throwable, String s, Object obj)
    {
        innerLog(Log.Level.TRACE, throwable, s, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void trace(Throwable throwable, String s, Object obj, Object obj1)
    {
        innerLog(Log.Level.TRACE, throwable, s, obj, obj1, UNKNOWN_ARG, null);
    }

    public void trace(Throwable throwable, String s, Object obj, Object obj1, Object obj2)
    {
        innerLog(Log.Level.TRACE, throwable, s, obj, obj1, obj2, null);
    }

    public void trace(Throwable throwable, String s, Object aobj[])
    {
        innerLog(Log.Level.TRACE, throwable, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, aobj);
    }

    public void warn(String s)
    {
        innerLog(Log.Level.WARNING, null, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void warn(String s, Object obj)
    {
        innerLog(Log.Level.WARNING, null, s, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void warn(String s, Object obj, Object obj1)
    {
        innerLog(Log.Level.WARNING, null, s, obj, obj1, UNKNOWN_ARG, null);
    }

    public void warn(String s, Object obj, Object obj1, Object obj2)
    {
        innerLog(Log.Level.WARNING, null, s, obj, obj1, obj2, null);
    }

    public void warn(String s, Object aobj[])
    {
        innerLog(Log.Level.WARNING, null, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, aobj);
    }

    public void warn(Throwable throwable, String s)
    {
        innerLog(Log.Level.WARNING, throwable, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void warn(Throwable throwable, String s, Object obj)
    {
        innerLog(Log.Level.WARNING, throwable, s, obj, UNKNOWN_ARG, UNKNOWN_ARG, null);
    }

    public void warn(Throwable throwable, String s, Object obj, Object obj1)
    {
        innerLog(Log.Level.WARNING, throwable, s, obj, obj1, UNKNOWN_ARG, null);
    }

    public void warn(Throwable throwable, String s, Object obj, Object obj1, Object obj2)
    {
        innerLog(Log.Level.WARNING, throwable, s, obj, obj1, obj2, null);
    }

    public void warn(Throwable throwable, String s, Object aobj[])
    {
        innerLog(Log.Level.WARNING, throwable, s, UNKNOWN_ARG, UNKNOWN_ARG, UNKNOWN_ARG, aobj);
    }

}
