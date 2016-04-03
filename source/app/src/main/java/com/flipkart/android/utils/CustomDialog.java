// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.Activity;

// Referenced classes of package com.flipkart.android.utils:
//            ToastMessageUtils, CustomAlertDialog, StringUtils

public class CustomDialog
{

    private static CustomAlertDialog a = null;

    public CustomDialog()
    {
    }

    private static boolean a(Activity activity)
    {
        if (activity == null)
        {
            break MISSING_BLOCK_LABEL_43;
        }
        if (activity.isFinishing())
        {
            break MISSING_BLOCK_LABEL_43;
        }
        ToastMessageUtils.dismissToast(activity);
        if (a != null && a.isShowing())
        {
            return false;
        }
        try
        {
            a = new CustomAlertDialog(activity);
        }
        catch (IllegalArgumentException illegalargumentexception) { }
        catch (Exception exception) { }
        return true;
    }

    public static void dismissDialog()
    {
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorenter ;
        CustomAlertDialog customalertdialog = a;
        if (customalertdialog == null)
        {
            break MISSING_BLOCK_LABEL_30;
        }
        Exception exception;
        try
        {
            if (a.isShowing())
            {
                a.dismiss();
            }
            a = null;
        }
        catch (IllegalArgumentException illegalargumentexception) { }
        catch (Exception exception1) { }
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorexit ;
        return;
        exception;
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static String getErrorMessage(int i)
    {
        switch (i)
        {
        default:
            return "Please try after sometime.";

        case 400: 
            return "Please try after sometime.";

        case 401: 
            return "Please try after sometime.";

        case 4015000: 
            return "Unauthorized access. Please try again.";

        case 504: 
            return "Request timed out. Please check your network connection and try again";

        case 500: 
            return "We have problem reaching our server! Please try again.";

        case 900: 
            return "Please check your network connection and try again.";

        case -1: 
            return "Not able to connect to Flipkart. Please check your network connection and try again.";

        case 123: // '{'
            return "No Matching Product Available";

        case 234: 
            return "No More Data";
        }
    }

    public static String getShortErrorMessage(int i)
    {
        switch (i)
        {
        default:
            return "Please try after sometime";

        case 400: 
            return "Please try after sometime";

        case 401: 
            return "Please try after sometime";

        case 4015000: 
            return "Unauthorized access";

        case 504: 
            return "Request time out";

        case 500: 
            return "Problem in reaching our server";

        case 900: 
            return "Please check your network connection";

        case -1: 
            return "Not able to connect to Flipkart";

        case 123: // '{'
            return "No Matching Product Available";

        case 234: 
            return "No More Data";
        }
    }

    public static void showAlertMessage(String s, String s1, boolean flag, Activity activity)
    {
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorenter ;
        if (a(activity) && a != null)
        {
            a.showSingleButtonDialog(s, s1, activity, "Ok");
        }
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static void showAppUpgrade(Activity activity, String s, String s1, String s2)
    {
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorenter ;
        if (a(activity) && a != null)
        {
            a.showDoubleButtonDialog(s, s1, activity, s2, "LATER", "UPGRADE NOW");
        }
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static void showErrorMessage(int i, int j, String s, Activity activity)
    {
        if (i == 1000)
        {
            return;
        }
        if (i != 456)
        {
            s = getErrorMessage(i);
        }
        if (StringUtils.isNullOrEmpty(s))
        {
            s = "Please try after sometime.";
        }
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorenter ;
        if (a(activity) && a != null)
        {
            a.showSingleButtonDialog("Error", s, activity, "Ok");
        }
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static void showErrorMessage(String s, Activity activity)
    {
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorenter ;
        if (a(activity) && a != null)
        {
            a.showSingleButtonDialog("Error", s, activity, "Ok");
        }
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static void showRateTheApp(Activity activity, String s, String s1, String s2)
    {
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorenter ;
        a(activity);
        if (a != null)
        {
            a.showTripleButtonDialog(s, s1, activity, s2, "RATE IT NOW", "REMIND ME LATER", "NO, THANKS");
        }
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        com/flipkart/android/utils/CustomDialog;
        JVM INSTR monitorexit ;
        throw exception;
    }

}
