// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONObject;

// Referenced classes of package com.crashlytics.android.internal:
//            h, aM, aR, aQ, 
//            aP, aK, aX, ah

public class aY
{

    aY()
    {
    }

    public aX a(ah ah1, JSONObject jsonobject)
    {
        int i = jsonobject.optInt("settings_version", 0);
        int j = jsonobject.optInt("cache_duration", 3600);
        JSONObject jsonobject1 = jsonobject.getJSONObject("app");
        String s = jsonobject1.getString("identifier");
        String s1 = jsonobject1.getString("status");
        String s2 = jsonobject1.getString("url");
        String s3 = jsonobject1.getString("reports_url");
        boolean flag = jsonobject1.optBoolean("update_required", false);
        boolean flag1 = jsonobject1.has("icon");
        h h1 = null;
        if (flag1)
        {
            boolean flag2 = jsonobject1.getJSONObject("icon").has("hash");
            h1 = null;
            if (flag2)
            {
                JSONObject jsonobject6 = jsonobject1.getJSONObject("icon");
                h1 = new h(jsonobject6.getString("hash"), jsonobject6.getInt("width"), jsonobject6.getInt("height"));
            }
        }
        aM am = new aM(s, s1, s2, s3, flag, h1);
        JSONObject jsonobject2 = jsonobject.getJSONObject("session");
        aR ar = new aR(jsonobject2.optInt("log_buffer_size", 64000), jsonobject2.optInt("max_chained_exception_depth", 8), jsonobject2.optInt("max_custom_exception_events", 64), jsonobject2.optInt("max_custom_key_value_pairs", 64), jsonobject2.optInt("identifier_mask", 255), jsonobject2.optBoolean("send_session_without_crash", false));
        JSONObject jsonobject3 = jsonobject.getJSONObject("prompt");
        aQ aq = new aQ(jsonobject3.optString("title", "Send Crash Report?"), jsonobject3.optString("message", "Looks like we crashed! Please help us fix the problem by sending a crash report."), jsonobject3.optString("send_button_title", "Send"), jsonobject3.optBoolean("show_cancel_button", true), jsonobject3.optString("cancel_button_title", "Don't Send"), jsonobject3.optBoolean("show_always_send_button", true), jsonobject3.optString("always_send_button_title", "Always Send"));
        JSONObject jsonobject4 = jsonobject.getJSONObject("features");
        aP ap = new aP(jsonobject4.optBoolean("prompt_enabled", false), jsonobject4.optBoolean("collect_logged_exceptions", true), jsonobject4.optBoolean("collect_reports", true), jsonobject4.optBoolean("collect_analytics", false));
        JSONObject jsonobject5 = jsonobject.getJSONObject("analytics");
        aK ak = new aK(jsonobject5.optString("url", "https://e.crashlytics.com/spi/v2/events"), jsonobject5.optInt("flush_interval_secs", 600), jsonobject5.optInt("max_byte_size_per_file", 8000), jsonobject5.optInt("max_file_count_per_send", 1), jsonobject5.optInt("max_pending_send_file_count", 100));
        long l = j;
        long l1;
        if (jsonobject.has("expires_at"))
        {
            l1 = jsonobject.getLong("expires_at");
        } else
        {
            l1 = ah1.a() + l * 1000L;
        }
        return new aX(l1, am, ar, aq, ap, ak, i, j);
    }

    public JSONObject a(JSONObject jsonobject)
    {
        JSONObject _tmp = JVM INSTR new #14  <Class JSONObject>;
        String s;
        JSONObject jsonobject1;
        if (!(jsonobject instanceof JSONObject))
        {
            s = jsonobject.toString();
        } else
        {
            s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        }
        jsonobject1 = JSONObjectInstrumentation.init(s);
        jsonobject1.getJSONObject("features").remove("collect_analytics");
        jsonobject1.remove("analytics");
        return jsonobject1;
    }
}
