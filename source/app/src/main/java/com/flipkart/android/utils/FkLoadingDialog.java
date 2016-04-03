// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class FkLoadingDialog
{

    private ProgressDialog a;
    private Context b;

    public FkLoadingDialog(Context context)
    {
        a = null;
        b = context;
    }

    public boolean cancelDlg()
    {
        ProgressDialog progressdialog;
        boolean flag;
        try
        {
            progressdialog = a;
        }
        catch (Exception exception)
        {
            return false;
        }
        flag = false;
        if (progressdialog == null)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        a.cancel();
        a = null;
        flag = true;
        return flag;
    }

    public boolean dismissDlg()
    {
        ProgressDialog progressdialog;
        boolean flag;
        try
        {
            progressdialog = a;
        }
        catch (Exception exception)
        {
            return false;
        }
        flag = false;
        if (progressdialog == null)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        a.dismiss();
        a = null;
        flag = true;
        return flag;
    }

    public boolean isShowing()
    {
        ProgressDialog progressdialog = a;
        boolean flag = false;
        if (progressdialog != null)
        {
            flag = true;
        }
        return flag;
    }

    public boolean showDlg(String s, String s1, android.content.DialogInterface.OnCancelListener oncancellistener, boolean flag)
    {
        if (a != null || b == null)
        {
            break MISSING_BLOCK_LABEL_61;
        }
        a = ProgressDialog.show(b, s, s1, true);
        a.setCancelable(true);
        a.setCanceledOnTouchOutside(flag);
        if (oncancellistener == null)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        a.setOnCancelListener(oncancellistener);
        return true;
        Exception exception;
        exception;
        return false;
    }
}
