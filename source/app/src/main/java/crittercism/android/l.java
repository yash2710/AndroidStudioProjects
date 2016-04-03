// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;

import android.content.Context;
import com.crittercism.app.Crittercism;
import java.io.File;
import java.util.Iterator;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package crittercism.android:
//            j, b

public final class l extends j
{

    public l()
    {
        a = a.c.g;
        b = new Vector();
    }

    public static l c()
    {
        l l1 = new l();
        String s;
        String s1;
        File file;
        String as[];
        Vector vector;
        int i;
        try
        {
            Context context = Crittercism.a().n();
            s = (new StringBuilder()).append(context.getFilesDir().getAbsolutePath()).append("/").append(Crittercism.a().v()).toString();
            s1 = context.getPackageName();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return l1;
        }
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_216;
        }
        if (s1.equals(""))
        {
            break MISSING_BLOCK_LABEL_216;
        }
        file = new File(s);
        if (!file.exists())
        {
            break MISSING_BLOCK_LABEL_216;
        }
        as = file.list(new a(".dmp"));
        if (as == null)
        {
            break MISSING_BLOCK_LABEL_216;
        }
        if (as.length <= 0)
        {
            break MISSING_BLOCK_LABEL_216;
        }
        vector = new Vector();
        i = 0;
_L2:
        if (i >= as.length)
        {
            break; /* Loop/switch isn't completed */
        }
        (new StringBuilder("filename = ")).append(file.getAbsolutePath()).append("/").append(as[i]);
        vector.add((new StringBuilder()).append(file.getAbsolutePath()).append("/").append(as[i]).toString());
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        l1.a(vector);
        return l1;
    }

    public final JSONObject a()
    {
        JSONObject jsonobject;
        jsonobject = new JSONObject();
        new JSONObject();
        new JSONArray();
        JSONArray jsonarray = new JSONArray(b);
        JSONArray jsonarray1 = jsonarray;
_L1:
        JSONObject jsonobject1;
        Exception exception2;
        try
        {
            jsonobject1 = Crittercism.a().o().c();
            jsonobject1.put("app_state", Crittercism.a().o().d());
            jsonobject1.put("num_ndk_crashes", b.size());
            jsonobject1.put("filenames", jsonarray1);
            jsonobject1.put("filename_prefix", "ndk_crash_");
        }
        catch (Exception exception)
        {
            jsonobject1 = new JSONObject();
        }
        try
        {
            jsonobject.put("requestUrl", a);
            jsonobject.put("requestData", jsonobject1);
        }
        catch (Exception exception1)
        {
            return new JSONObject();
        }
        return jsonobject;
        exception2;
        jsonarray1 = new JSONArray();
          goto _L1
    }

    public final void b()
    {
        try
        {
            if (b != null)
            {
                String s;
                for (Iterator iterator = b.iterator(); iterator.hasNext(); (new File(s)).delete())
                {
                    s = (String)iterator.next();
                    (new StringBuilder("deleting filename: ")).append(s);
                }

            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private class a
        implements FilenameFilter
    {

        private String a;

        public final boolean accept(File file, String s)
        {
            return s.endsWith(a);
        }

        public a(String s)
        {
            a = new String();
            if (s != null)
            {
                a = s;
            }
        }
    }

}
