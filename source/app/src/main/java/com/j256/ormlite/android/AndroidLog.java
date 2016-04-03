// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.android;

import com.j256.ormlite.logger.Log;
import com.j256.ormlite.logger.LoggerFactory;

public class AndroidLog
    implements Log
{

    private static final String ALL_LOGS_NAME = "ORMLite";
    private static final int MAX_TAG_LENGTH = 23;
    private static final int REFRESH_LEVEL_CACHE_EVERY = 200;
    private String className;
    private final boolean levelCache[];
    private volatile int levelCacheC;

    public AndroidLog(String s)
    {
        levelCacheC = 0;
        className = LoggerFactory.getSimpleClassName(s);
        int i = className.length();
        if (i > 23)
        {
            className = className.substring(i - 23, i);
        }
        com.j256.ormlite.logger.Log.Level alevel[] = com.j256.ormlite.logger.Log.Level.values();
        int j = alevel.length;
        int k = 0;
        int l = 0;
        while (k < j) 
        {
            int i1 = levelToAndroidLevel(alevel[k]);
            if (i1 <= l)
            {
                i1 = l;
            }
            k++;
            l = i1;
        }
        levelCache = new boolean[l + 1];
        refreshLevelCache();
    }

    private boolean isLevelEnabledInternal(int i)
    {
        return android.util.Log.isLoggable(className, i) || android.util.Log.isLoggable("ORMLite", i);
    }

    private int levelToAndroidLevel(com.j256.ormlite.logger.Log.Level level)
    {
        switch (_cls1..SwitchMap.com.j256.ormlite.logger.Log.Level[level.ordinal()])
        {
        case 3: // '\003'
        default:
            return 4;

        case 1: // '\001'
            return 2;

        case 2: // '\002'
            return 3;

        case 4: // '\004'
            return 5;

        case 5: // '\005'
            return 6;

        case 6: // '\006'
            return 6;
        }
    }

    private void refreshLevelCache()
    {
        com.j256.ormlite.logger.Log.Level alevel[] = com.j256.ormlite.logger.Log.Level.values();
        int i = alevel.length;
        for (int j = 0; j < i; j++)
        {
            int k = levelToAndroidLevel(alevel[j]);
            if (k < levelCache.length)
            {
                levelCache[k] = isLevelEnabledInternal(k);
            }
        }

    }

    public boolean isLevelEnabled(com.j256.ormlite.logger.Log.Level level)
    {
        int i = 1 + levelCacheC;
        levelCacheC = i;
        if (i >= 200)
        {
            refreshLevelCache();
            levelCacheC = 0;
        }
        int j = levelToAndroidLevel(level);
        if (j < levelCache.length)
        {
            return levelCache[j];
        } else
        {
            return isLevelEnabledInternal(j);
        }
    }

    public void log(com.j256.ormlite.logger.Log.Level level, String s)
    {
        switch (_cls1..SwitchMap.com.j256.ormlite.logger.Log.Level[level.ordinal()])
        {
        default:
            android.util.Log.i(className, s);
            return;

        case 1: // '\001'
            android.util.Log.v(className, s);
            return;

        case 2: // '\002'
            android.util.Log.d(className, s);
            return;

        case 3: // '\003'
            android.util.Log.i(className, s);
            return;

        case 4: // '\004'
            android.util.Log.w(className, s);
            return;

        case 5: // '\005'
            android.util.Log.e(className, s);
            return;

        case 6: // '\006'
            android.util.Log.e(className, s);
            break;
        }
    }

    public void log(com.j256.ormlite.logger.Log.Level level, String s, Throwable throwable)
    {
        switch (_cls1..SwitchMap.com.j256.ormlite.logger.Log.Level[level.ordinal()])
        {
        default:
            android.util.Log.i(className, s, throwable);
            return;

        case 1: // '\001'
            android.util.Log.v(className, s, throwable);
            return;

        case 2: // '\002'
            android.util.Log.d(className, s, throwable);
            return;

        case 3: // '\003'
            android.util.Log.i(className, s, throwable);
            return;

        case 4: // '\004'
            android.util.Log.w(className, s, throwable);
            return;

        case 5: // '\005'
            android.util.Log.e(className, s, throwable);
            return;

        case 6: // '\006'
            android.util.Log.e(className, s, throwable);
            break;
        }
    }

    private class _cls1
    {

        static final int $SwitchMap$com$j256$ormlite$logger$Log$Level[];

        static 
        {
            $SwitchMap$com$j256$ormlite$logger$Log$Level = new int[com.j256.ormlite.logger.Log.Level.values().length];
            try
            {
                $SwitchMap$com$j256$ormlite$logger$Log$Level[com.j256.ormlite.logger.Log.Level.TRACE.ordinal()] = 1;
            }
            catch (NoSuchFieldError nosuchfielderror) { }
            try
            {
                $SwitchMap$com$j256$ormlite$logger$Log$Level[com.j256.ormlite.logger.Log.Level.DEBUG.ordinal()] = 2;
            }
            catch (NoSuchFieldError nosuchfielderror1) { }
            try
            {
                $SwitchMap$com$j256$ormlite$logger$Log$Level[com.j256.ormlite.logger.Log.Level.INFO.ordinal()] = 3;
            }
            catch (NoSuchFieldError nosuchfielderror2) { }
            try
            {
                $SwitchMap$com$j256$ormlite$logger$Log$Level[com.j256.ormlite.logger.Log.Level.WARNING.ordinal()] = 4;
            }
            catch (NoSuchFieldError nosuchfielderror3) { }
            try
            {
                $SwitchMap$com$j256$ormlite$logger$Log$Level[com.j256.ormlite.logger.Log.Level.ERROR.ordinal()] = 5;
            }
            catch (NoSuchFieldError nosuchfielderror4) { }
            try
            {
                $SwitchMap$com$j256$ormlite$logger$Log$Level[com.j256.ormlite.logger.Log.Level.FATAL.ordinal()] = 6;
            }
            catch (NoSuchFieldError nosuchfielderror5)
            {
                return;
            }
        }
    }

}
