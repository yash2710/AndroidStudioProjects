// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;


public class Data
{

    private long a;
    private Object b;
    private long c;
    private DataCacheState d;

    public Data()
    {
        setEventId(System.currentTimeMillis() + System.nanoTime());
        setCacheState(DataCacheState.CSTATE_NOT_CACHED);
    }

    public Data(Object obj)
    {
        setEventId(System.currentTimeMillis() + System.nanoTime());
        setData(obj);
        setCacheState(DataCacheState.CSTATE_NOT_CACHED);
    }

    public DataCacheState getCacheState()
    {
        return d;
    }

    public Object getData()
    {
        return b;
    }

    public long getEventId()
    {
        return a;
    }

    public long getExpiry()
    {
        return c;
    }

    public void setCacheState(DataCacheState datacachestate)
    {
        d = datacachestate;
    }

    public void setData(Object obj)
    {
        b = obj;
    }

    public void setEventId(long l)
    {
        a = l;
    }

    public void setExpiry(long l)
    {
        c = l;
    }

    private class DataCacheState extends Enum
    {

        public static final DataCacheState CSTATE_CACHED;
        public static final DataCacheState CSTATE_DONOT_CACHE;
        public static final DataCacheState CSTATE_NOT_CACHED;
        private static final DataCacheState a[];

        public static DataCacheState valueOf(String s)
        {
            return (DataCacheState)Enum.valueOf(com/flipkart/fk_android_batchnetworking/Data$DataCacheState, s);
        }

        public static DataCacheState[] values()
        {
            return (DataCacheState[])a.clone();
        }

        static 
        {
            CSTATE_NOT_CACHED = new DataCacheState("CSTATE_NOT_CACHED", 0);
            CSTATE_CACHED = new DataCacheState("CSTATE_CACHED", 1);
            CSTATE_DONOT_CACHE = new DataCacheState("CSTATE_DONOT_CACHE", 2);
            DataCacheState adatacachestate[] = new DataCacheState[3];
            adatacachestate[0] = CSTATE_NOT_CACHED;
            adatacachestate[1] = CSTATE_CACHED;
            adatacachestate[2] = CSTATE_DONOT_CACHE;
            a = adatacachestate;
        }

        private DataCacheState(String s, int i)
        {
            super(s, i);
        }
    }

}
