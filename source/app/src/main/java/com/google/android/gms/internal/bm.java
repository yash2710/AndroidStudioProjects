// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.internal:
//            eu, bl, br

public final class bm
{

    public final List nr;
    public final long ns;
    public final List nt;
    public final List nu;
    public final List nv;
    public final String nw;
    public final long nx;
    public int ny;
    public int nz;

    public bm(String s)
    {
        JSONObject jsonobject = new JSONObject(s);
        JSONArray jsonarray;
        ArrayList arraylist;
        int i;
        if (eu.p(2))
        {
            StringBuilder stringbuilder = new StringBuilder("Mediation Response JSON: ");
            String s1;
            int j;
            bl bl1;
            if (!(jsonobject instanceof JSONObject))
            {
                s1 = jsonobject.toString(2);
            } else
            {
                s1 = JSONObjectInstrumentation.toString((JSONObject)jsonobject, 2);
            }
            eu.C(stringbuilder.append(s1).toString());
        }
        jsonarray = jsonobject.getJSONArray("ad_networks");
        arraylist = new ArrayList(jsonarray.length());
        i = -1;
        for (j = 0; j < jsonarray.length(); j++)
        {
            bl1 = new bl(jsonarray.getJSONObject(j));
            arraylist.add(bl1);
            if (i < 0 && a(bl1))
            {
                i = j;
            }
        }

        ny = i;
        nz = jsonarray.length();
        nr = Collections.unmodifiableList(arraylist);
        nw = jsonobject.getString("qdata");
        JSONObject jsonobject1 = jsonobject.optJSONObject("settings");
        if (jsonobject1 != null)
        {
            ns = jsonobject1.optLong("ad_network_timeout_millis", -1L);
            nt = br.a(jsonobject1, "click_urls");
            nu = br.a(jsonobject1, "imp_urls");
            nv = br.a(jsonobject1, "nofill_urls");
            long l = jsonobject1.optLong("refresh", -1L);
            long l1;
            if (l > 0L)
            {
                l1 = l * 1000L;
            } else
            {
                l1 = -1L;
            }
            nx = l1;
            return;
        } else
        {
            ns = -1L;
            nt = null;
            nu = null;
            nv = null;
            nx = -1L;
            return;
        }
    }

    private boolean a(bl bl1)
    {
        for (Iterator iterator = bl1.nm.iterator(); iterator.hasNext();)
        {
            if (((String)iterator.next()).equals("com.google.ads.mediation.admob.AdMobAdapter"))
            {
                return true;
            }
        }

        return false;
    }
}
