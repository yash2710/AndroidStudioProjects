// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.widget.RatingBar;
import android.widget.TextView;
import java.io.InputStream;
import java.io.OutputStream;

class e
{

    TextView a;
    TextView b;
    TextView c;
    TextView d;
    TextView e;
    TextView f;
    RatingBar g;
    TextView h;

    e()
    {
    }

    public static int copy(InputStream inputstream, OutputStream outputstream)
    {
        byte abyte0[] = new byte[4096];
        int i = 0;
        do
        {
            int j = inputstream.read(abyte0);
            if (-1 != j)
            {
                outputstream.write(abyte0, 0, j);
                i += j;
                if (Thread.interrupted())
                {
                    throw new InterruptedException();
                }
            } else
            {
                return i;
            }
        } while (true);
    }
}
