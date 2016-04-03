// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;

// Referenced classes of package crittercism.android:
//            p

public final class d
{

    public static final a a;

    public d()
    {
    }

    public static String a()
    {
        switch (p.a[a.ordinal()])
        {
        default:
            return "https://api.crittercism.com";

        case 1: // '\001'
            return "http://10.0.2.2:6013";

        case 2: // '\002'
            return "http://192.168.2.1:6013";

        case 3: // '\003'
            return "http://192.168.1.110:6013";

        case 4: // '\004'
            return "http://192.168.1.80:6013";

        case 5: // '\005'
            return "http://10.0.1.7:6013";

        case 6: // '\006'
            return "http://192.168.1.185:6013";

        case 7: // '\007'
            return "http://192.168.1.100:6013";

        case 8: // '\b'
            return "https://www.appcred.com";
        }
    }

    public static String a(Context context, String s)
    {
        SharedPreferences sharedpreferences;
        String s1;
        String s2;
        try
        {
            sharedpreferences = context.getSharedPreferences("com.crittercism.prefs", 0);
        }
        catch (Exception exception)
        {
            return null;
        }
        s1 = null;
        if (sharedpreferences == null)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        s2 = sharedpreferences.getString(s, null);
        s1 = s2;
        return s1;
    }

    public static void a(Context context, String s, String s1)
    {
        SharedPreferences sharedpreferences;
        android.content.SharedPreferences.Editor editor;
        try
        {
            sharedpreferences = context.getSharedPreferences("com.crittercism.prefs", 0);
        }
        catch (Exception exception)
        {
            exception.toString();
            return;
        }
        if (sharedpreferences == null)
        {
            break MISSING_BLOCK_LABEL_55;
        }
        editor = sharedpreferences.edit();
        if (editor == null)
        {
            break MISSING_BLOCK_LABEL_55;
        }
        editor.remove(s);
        editor.putString(s, s1);
        editor.commit();
    }

    public static String b()
    {
        return "3.0.8";
    }

    static 
    {
        a = a.i;
    }

    private class a extends Enum
    {

        public static final a a;
        public static final a b;
        public static final a c;
        public static final a d;
        public static final a e;
        public static final a f;
        public static final a g;
        public static final a h;
        public static final a i;
        private static final a j[];

        public static a valueOf(String s)
        {
            return (a)Enum.valueOf(crittercism/android/d$a, s);
        }

        public static a[] values()
        {
            return (a[])j.clone();
        }

        static 
        {
            a = new a("LOCAL_EMULATOR", 0);
            b = new a("LOCAL_DEVICE", 1);
            c = new a("OFFICE", 2);
            d = new a("OFFICE_YOUSEF", 3);
            e = new a("APT_YOUSEF", 4);
            f = new a("OTHER", 5);
            g = new a("KEVIN_ROB", 6);
            h = new a("STAGING", 7);
            i = new a("PRODUCTION", 8);
            a aa[] = new a[9];
            aa[0] = a;
            aa[1] = b;
            aa[2] = c;
            aa[3] = d;
            aa[4] = e;
            aa[5] = f;
            aa[6] = g;
            aa[7] = h;
            aa[8] = i;
            j = aa;
        }

        private a(String s, int k)
        {
            super(s, k);
        }
    }

}
