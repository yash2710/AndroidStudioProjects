// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.AlertDialog;

public class FkDialogHelper
{

    public FkDialogHelper()
    {
    }

    public static void cancelDialog(AlertDialog alertdialog)
    {
        try
        {
            alertdialog.cancel();
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public static void showDialog(AlertDialog alertdialog)
    {
        try
        {
            alertdialog.show();
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }
}
