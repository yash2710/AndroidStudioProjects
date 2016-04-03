// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;

// Referenced classes of package com.google.zxing.client.android:
//            ViewfinderView

final class s
    implements ResultPointCallback
{

    private final ViewfinderView a;

    s(ViewfinderView viewfinderview)
    {
        a = viewfinderview;
    }

    public final void foundPossibleResultPoint(ResultPoint resultpoint)
    {
        a.addPossibleResultPoint(resultpoint);
    }
}
