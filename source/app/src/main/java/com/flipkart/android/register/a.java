// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.register;

import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.utils.CustomDialog;

final class a
    implements Runnable
{

    a()
    {
    }

    public final void run()
    {
        CustomDialog.showAlertMessage("Time Sync Error", "Your time is not synced with our sever , please update your time and retry", true, FlipkartApplication.getCurrentActivity());
    }
}
