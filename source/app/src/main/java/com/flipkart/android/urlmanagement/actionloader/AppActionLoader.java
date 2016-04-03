// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.urlmanagement.actionloader;

import android.app.Activity;
import com.flipkart.android.urlmanagement.AppParams;
import com.flipkart.android.utils.StringUtils;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public abstract class AppActionLoader
{

    protected Activity activity;
    protected AppParams params;

    public AppActionLoader(AppParams appparams, Activity activity1)
    {
        params = appparams;
        activity = activity1;
    }

    protected Map getQueryParams()
    {
        HashMap hashmap;
        String s;
        hashmap = new HashMap();
        s = params.getParams();
        if (!StringUtils.isNullOrEmpty(s)) goto _L2; else goto _L1
_L1:
        String as[];
        return hashmap;
_L2:
        if ((as = StringUtils.trim(s.trim(), "/").split("&")).length <= 0) goto _L1; else goto _L3
_L3:
        int i;
        int j;
        i = as.length;
        j = 0;
_L5:
        if (j >= i) goto _L1; else goto _L4
_L4:
        String as1[];
        String s1;
        as1 = as[j].split("=");
        if (as1.length != 2)
        {
            break MISSING_BLOCK_LABEL_103;
        }
        s1 = as1[0];
        String s2 = URLDecoder.decode(as1[1], "UTF-8");
        hashmap.put(s1, s2);
_L6:
        j++;
          goto _L5
          goto _L1
        Exception exception;
        exception;
          goto _L6
    }

    public abstract void load();
}
