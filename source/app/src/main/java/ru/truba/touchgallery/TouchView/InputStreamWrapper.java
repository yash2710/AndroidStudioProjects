// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.TouchView;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class InputStreamWrapper extends BufferedInputStream
{

    protected long mBytesLoaded;
    protected long mContentLen;
    protected InputStreamProgressListener mProgressListener;

    public InputStreamWrapper(InputStream inputstream, int i, long l)
    {
        super(inputstream, i);
        mContentLen = l;
        mBytesLoaded = 0L;
    }

    public int read(byte abyte0[], int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        int k;
        mBytesLoaded = mBytesLoaded + (long)j;
        if (mProgressListener != null)
        {
            mProgressListener.onProgress((float)mBytesLoaded / (float)mContentLen, mBytesLoaded, mContentLen);
        }
        k = super.read(abyte0, i, j);
        this;
        JVM INSTR monitorexit ;
        return k;
        Exception exception;
        exception;
        throw exception;
    }

    public void setProgressListener(InputStreamProgressListener inputstreamprogresslistener)
    {
        mProgressListener = inputstreamprogresslistener;
    }

    private class InputStreamProgressListener
    {

        public abstract void onProgress(float f, long l, long l1);
    }

}
