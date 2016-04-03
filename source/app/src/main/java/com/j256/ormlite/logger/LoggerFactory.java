// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.logger;


// Referenced classes of package com.j256.ormlite.logger:
//            LocalLog, Log, Logger

public class LoggerFactory
{

    public static final String LOG_TYPE_SYSTEM_PROPERTY = "com.j256.ormlite.logger.type";
    private static LogType logType;

    private LoggerFactory()
    {
    }

    private static LogType findLogType()
    {
        String s = System.getProperty("com.j256.ormlite.logger.type");
        if (s == null) goto _L2; else goto _L1
_L1:
        LogType logtype1 = LogType.valueOf(s);
        LogType logtype = logtype1;
_L4:
        return logtype;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        (new LocalLog(com/j256/ormlite/logger/LoggerFactory.getName())).log(Log.Level.WARNING, (new StringBuilder("Could not find valid log-type from system property 'com.j256.ormlite.logger.type', value '")).append(s).append("'").toString());
_L2:
        LogType alogtype[] = LogType.values();
        int i = alogtype.length;
        int j = 0;
label0:
        do
        {
label1:
            {
                if (j >= i)
                {
                    break label1;
                }
                logtype = alogtype[j];
                if (logtype.isAvailable())
                {
                    break label0;
                }
                j++;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        return LogType.LOCAL;
    }

    public static Logger getLogger(Class class1)
    {
        return getLogger(class1.getName());
    }

    public static Logger getLogger(String s)
    {
        if (logType == null)
        {
            logType = findLogType();
        }
        return new Logger(logType.createLog(s));
    }

    public static String getSimpleClassName(String s)
    {
        String as[] = s.split("\\.");
        if (as.length <= 1)
        {
            return s;
        } else
        {
            return as[-1 + as.length];
        }
    }

    private class LogType extends Enum
    {

        private static final LogType $VALUES[];
        public static final LogType ANDROID;
        public static final LogType COMMONS_LOGGING;
        public static final LogType LOCAL;
        public static final LogType LOG4J;
        public static final LogType LOG4J2;
        public static final LogType SLF4J;
        private final String detectClassName;
        private final String logClassName;

        public static LogType valueOf(String s)
        {
            return (LogType)Enum.valueOf(com/j256/ormlite/logger/LoggerFactory$LogType, s);
        }

        public static LogType[] values()
        {
            return (LogType[])$VALUES.clone();
        }

        public Log createLog(String s)
        {
            Log log;
            try
            {
                log = createLogFromClassName(s);
            }
            catch (Exception exception)
            {
                LocalLog locallog = new LocalLog(s);
                locallog.log(Log.Level.WARNING, (new StringBuilder("Unable to call constructor with single String argument for class ")).append(logClassName).append(", so had to use local log: ").append(exception.getMessage()).toString());
                return locallog;
            }
            return log;
        }

        Log createLogFromClassName(String s)
        {
            return (Log)Class.forName(logClassName).getConstructor(new Class[] {
                java/lang/String
            }).newInstance(new Object[] {
                s
            });
        }

        public boolean isAvailable()
        {
            if (!isAvailableTestClass())
            {
                return false;
            }
            try
            {
                createLogFromClassName(getClass().getName()).isLevelEnabled(Log.Level.INFO);
            }
            catch (Exception exception)
            {
                return false;
            }
            return true;
        }

        boolean isAvailableTestClass()
        {
            try
            {
                Class.forName(detectClassName);
            }
            catch (Exception exception)
            {
                return false;
            }
            return true;
        }

        static 
        {
            ANDROID = new LogType("ANDROID", 0, "android.util.Log", "com.j256.ormlite.android.AndroidLog");
            SLF4J = new LogType("SLF4J", 1, "org.slf4j.LoggerFactory", "com.j256.ormlite.logger.Slf4jLoggingLog");
            COMMONS_LOGGING = new LogType("COMMONS_LOGGING", 2, "org.apache.commons.logging.LogFactory", "com.j256.ormlite.logger.CommonsLoggingLog");
            LOG4J2 = new LogType("LOG4J2", 3, "org.apache.logging.log4j.LogManager", "com.j256.ormlite.logger.Log4j2Log");
            LOG4J = new LogType("LOG4J", 4, "org.apache.log4j.Logger", "com.j256.ormlite.logger.Log4jLog");
            class _cls1 extends LogType
            {

                public final Log createLog(String s)
                {
                    return new LocalLog(s);
                }

                public final boolean isAvailable()
                {
                    return true;
                }

                _cls1(String s, int i, String s1, String s2)
                {
                    super(s, i, s1, s2, null);
                }
            }

            LOCAL = new _cls1("LOCAL", 5, com/j256/ormlite/logger/LocalLog.getName(), com/j256/ormlite/logger/LocalLog.getName());
            LogType alogtype[] = new LogType[6];
            alogtype[0] = ANDROID;
            alogtype[1] = SLF4J;
            alogtype[2] = COMMONS_LOGGING;
            alogtype[3] = LOG4J2;
            alogtype[4] = LOG4J;
            alogtype[5] = LOCAL;
            $VALUES = alogtype;
        }

        private LogType(String s, int i, String s1, String s2)
        {
            super(s, i);
            detectClassName = s1;
            logClassName = s2;
        }

        LogType(String s, int i, String s1, String s2, _cls1 _pcls1)
        {
            this(s, i, s1, s2);
        }
    }

}
