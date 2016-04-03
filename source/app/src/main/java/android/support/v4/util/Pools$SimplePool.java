// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.util;


public class mPool
    implements mPool
{

    private final Object mPool[];
    private int mPoolSize;

    private boolean isInPool(Object obj)
    {
        int i = 0;
        do
        {
label0:
            {
                int j = mPoolSize;
                boolean flag = false;
                if (i < j)
                {
                    if (mPool[i] != obj)
                    {
                        break label0;
                    }
                    flag = true;
                }
                return flag;
            }
            i++;
        } while (true);
    }

    public Object acquire()
    {
        if (mPoolSize > 0)
        {
            int i = -1 + mPoolSize;
            Object obj = mPool[i];
            mPool[i] = null;
            mPoolSize = -1 + mPoolSize;
            return obj;
        } else
        {
            return null;
        }
    }

    public boolean release(Object obj)
    {
        if (isInPool(obj))
        {
            throw new IllegalStateException("Already in the pool!");
        }
        if (mPoolSize < mPool.length)
        {
            mPool[mPoolSize] = obj;
            mPoolSize = 1 + mPoolSize;
            return true;
        } else
        {
            return false;
        }
    }

    public (int i)
    {
        if (i <= 0)
        {
            throw new IllegalArgumentException("The max pool size must be > 0");
        } else
        {
            mPool = new Object[i];
            return;
        }
    }
}
