// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;

import org.json.JSONException;
import org.json.JSONObject;

public final class e
{

    public String a;
    public String b;

    public e()
    {
        a = "";
        b = "";
    }

    private e(String s, int i, String s1)
    {
        a = "";
        b = "";
        b = s1;
    }

    public static e a(JSONObject jsonobject)
    {
        String s;
        if (!jsonobject.has("username"))
        {
            break MISSING_BLOCK_LABEL_104;
        }
        s = jsonobject.getString("username");
_L4:
        if (!jsonobject.has("votes_left")) goto _L2; else goto _L1
_L1:
        int i = jsonobject.getInt("votes_left");
_L3:
        e e1 = new e(jsonobject.getString("id"), i, s);
        JSONException jsonexception;
        try
        {
            if (jsonobject.has("notify"))
            {
                JSONObject jsonobject1 = jsonobject.getJSONObject("notify");
                if (jsonobject1.has("text"))
                {
                    e1.a = jsonobject1.getString("text");
                }
            }
        }
        catch (JSONException jsonexception1)
        {
            return e1;
        }
        return e1;
        jsonexception;
        return null;
_L2:
        i = 0;
          goto _L3
        s = "anonymous";
          goto _L4
    }
}
