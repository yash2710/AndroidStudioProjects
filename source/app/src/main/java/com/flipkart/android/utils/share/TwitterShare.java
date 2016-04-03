// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.share;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;
import com.flipkart.android.utils.CustomDialog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TwitterShare
{

    public TwitterShare()
    {
    }

    public static void share(String s, String s1, String s2, Activity activity, int i)
    {
        ArrayList arraylist;
label0:
        {
            try
            {
                arraylist = new ArrayList();
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                List list = activity.getPackageManager().queryIntentActivities(intent, 0);
                if (!list.isEmpty())
                {
                    Iterator iterator = list.iterator();
                    do
                    {
                        if (!iterator.hasNext())
                        {
                            break;
                        }
                        ResolveInfo resolveinfo = (ResolveInfo)iterator.next();
                        Intent intent2 = new Intent("android.intent.action.SEND");
                        intent2.setType("text/plain");
                        if (resolveinfo.activityInfo.packageName.toLowerCase().contains(s) || resolveinfo.activityInfo.name.toLowerCase().contains(s))
                        {
                            intent2.putExtra("android.intent.extra.TEXT", (new StringBuilder()).append(s1).append(" \n ").append(s2).toString());
                            intent2.setPackage(resolveinfo.activityInfo.packageName);
                            arraylist.add(intent2);
                        }
                    } while (true);
                    break label0;
                }
            }
            catch (Exception exception)
            {
                CustomDialog.showAlertMessage("Error", "Looks like your twitter app is not updated! Please update and try again.", false, activity);
            }
            return;
        }
        Intent intent1 = Intent.createChooser((Intent)arraylist.remove(0), "Select app to share");
        intent1.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])arraylist.toArray(new Parcelable[0]));
        activity.startActivityForResult(intent1, i);
        return;
    }
}
