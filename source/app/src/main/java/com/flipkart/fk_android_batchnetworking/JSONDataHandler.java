// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;

import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package com.flipkart.fk_android_batchnetworking:
//            GroupDataHandler, Data, GroupSyncPolicy

public class JSONDataHandler extends GroupDataHandler
{

    private static final String a = String.format("application/json; charset=%s", new Object[] {
        "utf-8"
    });

    public JSONDataHandler(String s, String s1)
    {
        super(s, s1);
    }

    public JSONDataHandler(String s, String s1, GroupSyncPolicy groupsyncpolicy, int i)
    {
        super(s, s1, groupsyncpolicy, i);
    }

    public Object deSerializeIndividualData(byte abyte0[])
    {
        String s = new String(abyte0, "utf-8");
        char c = s.charAt(0);
        Object obj = s.substring(1);
        if (c == 'O')
        {
            JSONObject _tmp = JVM INSTR new #44  <Class JSONObject>;
            obj = JSONObjectInstrumentation.init(((String) (obj)));
        } else
        if (c == 'A')
        {
            JSONArray _tmp1 = JVM INSTR new #52  <Class JSONArray>;
            return JSONArrayInstrumentation.init(((String) (obj)));
        }
        return obj;
    }

    public String getContentType()
    {
        return a;
    }

    public byte[] getPackedDataForNetworkPush(ArrayList arraylist)
    {
        JSONArray jsonarray;
        try
        {
            Enumeration enumeration = Collections.enumeration(arraylist);
            jsonarray = new JSONArray();
            for (; enumeration.hasMoreElements(); jsonarray.put(((Data)enumeration.nextElement()).getData())) { }
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            FkLogger.printStackTrace(unsupportedencodingexception);
            return null;
        }
        if (jsonarray instanceof JSONArray) goto _L2; else goto _L1
_L1:
        String s1 = jsonarray.toString();
_L3:
        return s1.getBytes("UTF-8");
_L2:
        String s = JSONArrayInstrumentation.toString((JSONArray)jsonarray);
        s1 = s;
          goto _L3
    }

    public byte[] serializeIndividualData(Object obj)
    {
        if (obj == null)
        {
            throw new Exception("Data can't be null");
        }
        char c = ' ';
        String s = "";
        if (obj instanceof String)
        {
            String s1 = (String)obj;
            c = 'S';
            s = s1;
        } else
        if (obj instanceof JSONObject)
        {
            JSONObject jsonobject = (JSONObject)obj;
            if (!(jsonobject instanceof JSONObject))
            {
                s = jsonobject.toString();
            } else
            {
                s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
            }
            c = 'O';
        } else
        if (obj instanceof JSONArray)
        {
            JSONArray jsonarray = (JSONArray)obj;
            if (!(jsonarray instanceof JSONArray))
            {
                s = jsonarray.toString();
            } else
            {
                s = JSONArrayInstrumentation.toString((JSONArray)jsonarray);
            }
            c = 'A';
        }
        if (s == null)
        {
            throw new Exception("JSONDataHandler couldn'd serialize the data");
        } else
        {
            return (new StringBuilder()).append(c).append(s).toString().getBytes("utf-8");
        }
    }

}
