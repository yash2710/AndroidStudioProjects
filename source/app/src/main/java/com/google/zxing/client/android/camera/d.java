// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.camera;

import java.util.Comparator;

// Referenced classes of package com.google.zxing.client.android.camera:
//            b

final class d
    implements Comparator
{

    d(b b)
    {
    }

    public final int compare(android.hardware.Camera.Size size, android.hardware.Camera.Size size1)
    {
        int i = size.height * size.width;
        int j = size1.height * size1.width;
        if (j < i)
        {
            return -1;
        }
        return j <= i ? 0 : 1;
    }

    public final volatile int compare(Object obj, Object obj1)
    {
        return compare((android.hardware.Camera.Size)obj, (android.hardware.Camera.Size)obj1);
    }
}
