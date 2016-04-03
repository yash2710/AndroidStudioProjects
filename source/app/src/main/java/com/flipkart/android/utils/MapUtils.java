// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

// Referenced classes of package com.flipkart.android.utils:
//            StringUtils

public class MapUtils
{

    public MapUtils()
    {
    }

    public static int fetchInt(Map map, String s)
    {
        return Integer.valueOf(Integer.parseInt((String)map.get(s))).intValue();
    }

    public static String getString(Map map, String s)
    {
        return null;
    }

    public static ArrayList getStringsArray(Map map, String s)
    {
        return null;
    }

    public static ArrayList getStringsList(Map map, String s)
    {
        ArrayList arraylist = new ArrayList();
        if (map != null && !StringUtils.isNullOrEmpty(s))
        {
            Object obj = map.get(s);
            if (obj == null)
            {
                return arraylist;
            }
            FkLogger.debug("OfferIdArray", (new StringBuilder("tag ")).append(s).append(" json text is ").append(obj).toString());
            JSONArray jsonarray;
            try
            {
                if (obj instanceof ArrayList)
                {
                    return (ArrayList)obj;
                }
                JSONArray _tmp = JVM INSTR new #78  <Class JSONArray>;
                jsonarray = JSONArrayInstrumentation.init(obj.toString());
            }
            catch (JSONException jsonexception)
            {
                FkLogger.printStackTrace(jsonexception);
                return arraylist;
            }
            if (jsonarray.length() > 0)
            {
                for (int i = 0; i < jsonarray.length(); i++)
                {
                    arraylist.add(jsonarray.optString(i));
                }

            }
        }
        return arraylist;
    }
}
