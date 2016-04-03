// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package com.crashlytics.android.internal:
//            R, v, q, S

public final class ab
{

    public static final Comparator a = new R();
    private static Boolean b = null;
    private static final char c[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'a', 'b', 'c', 'd', 'e', 'f'
    };
    private static long d = -1L;
    private static Boolean e = null;

    public static int a(Context context, String s, String s1)
    {
        Resources resources = context.getResources();
        int j = context.getApplicationContext().getApplicationInfo().icon;
        String s2;
        if (j > 0)
        {
            s2 = context.getResources().getResourcePackageName(j);
        } else
        {
            s2 = context.getPackageName();
        }
        return resources.getIdentifier(s, s1, s2);
    }

    public static int a(boolean flag)
    {
        float f1 = b(v.a().getContext());
        if (!flag)
        {
            return 1;
        }
        if (flag && (double)f1 >= 99D)
        {
            return 3;
        }
        return !flag || (double)f1 >= 99D ? 0 : 2;
    }

    public static long a(Context context)
    {
        android.app.ActivityManager.MemoryInfo memoryinfo = new android.app.ActivityManager.MemoryInfo();
        ((ActivityManager)context.getSystemService("activity")).getMemoryInfo(memoryinfo);
        return memoryinfo.availMem;
    }

    private static long a(String s, String s1, int j)
    {
        return Long.parseLong(s.split(s1)[0].trim()) * (long)j;
    }

    public static android.app.ActivityManager.RunningAppProcessInfo a(String s, Context context)
    {
label0:
        {
            List list = ((ActivityManager)context.getSystemService("activity")).getRunningAppProcesses();
            if (list == null)
            {
                break label0;
            }
            Iterator iterator = list.iterator();
            android.app.ActivityManager.RunningAppProcessInfo runningappprocessinfo;
            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }
                runningappprocessinfo = (android.app.ActivityManager.RunningAppProcessInfo)iterator.next();
            } while (!runningappprocessinfo.processName.equals(s));
            return runningappprocessinfo;
        }
        return null;
    }

    public static SharedPreferences a()
    {
        return v.a().getContext().getSharedPreferences("com.crashlytics.prefs", 0);
    }

    public static String a(int j)
    {
        if (j < 0)
        {
            throw new IllegalArgumentException("value must be zero or greater");
        } else
        {
            Locale locale = Locale.US;
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(j);
            return String.format(locale, "%1$10s", aobj).replace(' ', '0');
        }
    }

    public static String a(Context context, String s)
    {
        int j = a(context, s, "string");
        if (j > 0)
        {
            return context.getString(j);
        } else
        {
            return "";
        }
    }

    private static String a(File file, String s)
    {
        String s1;
        boolean flag = file.exists();
        s1 = null;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_88;
        }
        BufferedReader bufferedreader = new BufferedReader(new FileReader(file), 1024);
_L2:
        String s2 = bufferedreader.readLine();
        s1 = null;
        if (s2 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        Exception exception;
        Exception exception1;
        Exception exception2;
        try
        {
            String as[] = Pattern.compile("\\s*:\\s*").split(s2, 2);
            if (as.length <= 1 || !as[0].equals(s))
            {
                continue; /* Loop/switch isn't completed */
            }
            s1 = as[1];
            break; /* Loop/switch isn't completed */
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception) { }
        finally { }
        break MISSING_BLOCK_LABEL_95;
        if (true) goto _L2; else goto _L1
_L1:
        a(((Closeable) (bufferedreader)), "Failed to close system file reader.");
        return s1;
        exception;
        bufferedreader = null;
        v.a().b().a("Crashlytics", (new StringBuilder("Error parsing ")).append(file).toString(), exception);
        a(((Closeable) (bufferedreader)), "Failed to close system file reader.");
        return null;
        exception2;
        bufferedreader = null;
        exception1 = exception2;
        a(((Closeable) (bufferedreader)), "Failed to close system file reader.");
        throw exception1;
    }

    public static String a(InputStream inputstream)
    {
        Scanner scanner = (new Scanner(inputstream)).useDelimiter("\\A");
        if (scanner.hasNext())
        {
            return scanner.next();
        } else
        {
            return "";
        }
    }

    public static String a(String s)
    {
        return a(s.getBytes(), "SHA-1");
    }

    public static String a(byte abyte0[])
    {
        char ac[] = new char[abyte0.length << 1];
        for (int j = 0; j < abyte0.length; j++)
        {
            int k = 0xff & abyte0[j];
            ac[j << 1] = c[k >>> 4];
            ac[1 + (j << 1)] = c[k & 0xf];
        }

        return new String(ac);
    }

    private static String a(byte abyte0[], String s)
    {
        MessageDigest messagedigest;
        try
        {
            messagedigest = MessageDigest.getInstance(s);
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            v.a().b().a("Crashlytics", (new StringBuilder("Could not create hashing algorithm: ")).append(s).append(", returning empty string.").toString(), nosuchalgorithmexception);
            return "";
        }
        messagedigest.update(abyte0);
        return a(messagedigest.digest());
    }

    public static transient String a(String as[])
    {
        if (as == null || as.length == 0)
        {
            return null;
        }
        ArrayList arraylist = new ArrayList();
        int j = as.length;
        for (int k = 0; k < j; k++)
        {
            String s1 = as[k];
            if (s1 != null)
            {
                arraylist.add(s1.replace("-", "").toLowerCase(Locale.US));
            }
        }

        Collections.sort(arraylist);
        StringBuilder stringbuilder = new StringBuilder();
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); stringbuilder.append((String)iterator.next())) { }
        String s = stringbuilder.toString();
        if (s.length() > 0)
        {
            return a(s);
        } else
        {
            return null;
        }
    }

    public static void a(int j, String s)
    {
        if (e(v.a().getContext()))
        {
            v.a().b().a(4, "Crashlytics", s);
        }
    }

    public static void a(Closeable closeable, String s)
    {
        if (closeable == null)
        {
            break MISSING_BLOCK_LABEL_10;
        }
        closeable.close();
        return;
        IOException ioexception;
        ioexception;
        v.a().b().a("Crashlytics", s, ioexception);
        return;
    }

    public static void a(Flushable flushable, String s)
    {
        if (flushable == null)
        {
            break MISSING_BLOCK_LABEL_10;
        }
        flushable.flush();
        return;
        IOException ioexception;
        ioexception;
        v.a().b().a("Crashlytics", s, ioexception);
        return;
    }

    public static void a(InputStream inputstream, OutputStream outputstream, byte abyte0[])
    {
        do
        {
            int j = inputstream.read(abyte0);
            if (j != -1)
            {
                outputstream.write(abyte0, 0, j);
            } else
            {
                return;
            }
        } while (true);
    }

    public static boolean a(Context context, String s, boolean flag)
    {
        if (context != null)
        {
            Resources resources = context.getResources();
            if (resources != null)
            {
                int j = a(context, s, "bool");
                if (j > 0)
                {
                    flag = resources.getBoolean(j);
                } else
                {
                    int k = a(context, s, "string");
                    if (k > 0)
                    {
                        return Boolean.parseBoolean(context.getString(k));
                    }
                }
            }
        }
        return flag;
    }

    public static float b(Context context)
    {
        Intent intent = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int j = intent.getIntExtra("level", -1);
        int k = intent.getIntExtra("scale", -1);
        return (float)j / (float)k;
    }

    public static int b()
    {
        return S.a().ordinal();
    }

    public static long b(String s)
    {
        StatFs statfs = new StatFs(s);
        long l = statfs.getBlockSize();
        return l * (long)statfs.getBlockCount() - l * (long)statfs.getAvailableBlocks();
    }

    public static String b(int j)
    {
        switch (j)
        {
        default:
            return "?";

        case 7: // '\007'
            return "A";

        case 3: // '\003'
            return "D";

        case 6: // '\006'
            return "E";

        case 4: // '\004'
            return "I";

        case 2: // '\002'
            return "V";

        case 5: // '\005'
            return "W";
        }
    }

    private static String b(InputStream inputstream)
    {
        MessageDigest messagedigest;
        byte abyte0[];
        messagedigest = MessageDigest.getInstance("SHA-1");
        abyte0 = new byte[1024];
_L1:
        int j = inputstream.read(abyte0);
label0:
        {
            if (j == -1)
            {
                break label0;
            }
            try
            {
                messagedigest.update(abyte0, 0, j);
            }
            catch (Exception exception)
            {
                v.a().b().a("Crashlytics", "Could not calculate hash for app icon.", exception);
                return "";
            }
        }
          goto _L1
        String s = a(messagedigest.digest());
        return s;
    }

    public static Cipher b(int j, String s)
    {
        if (s.length() < 32)
        {
            throw new InvalidKeyException("Key must be at least 32 bytes.");
        }
        SecretKeySpec secretkeyspec = new SecretKeySpec(s.getBytes(), 0, 32, "AES/ECB/PKCS7Padding");
        Cipher cipher;
        try
        {
            cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
        }
        catch (GeneralSecurityException generalsecurityexception)
        {
            v.a().b().a("Crashlytics", "Could not create Cipher for AES/ECB/PKCS7Padding - should never happen.", generalsecurityexception);
            throw new RuntimeException(generalsecurityexception);
        }
        cipher.init(1, secretkeyspec);
        return cipher;
    }

    public static long c()
    {
        com/crashlytics/android/internal/ab;
        JVM INSTR monitorenter ;
        if (d != -1L) goto _L2; else goto _L1
_L1:
        String s = a(new File("/proc/meminfo"), "MemTotal");
        if (TextUtils.isEmpty(s)) goto _L4; else goto _L3
_L3:
        String s1 = s.toUpperCase(Locale.US);
        if (!s1.endsWith("KB")) goto _L6; else goto _L5
_L5:
        long l2 = a(s1, "KB", 1024);
        long l1 = l2;
_L8:
        d = l1;
_L2:
        long l = d;
        com/crashlytics/android/internal/ab;
        JVM INSTR monitorexit ;
        return l;
_L6:
        if (s1.endsWith("MB"))
        {
            l1 = a(s1, "MB", 0x100000);
            continue; /* Loop/switch isn't completed */
        }
        if (s1.endsWith("GB"))
        {
            l1 = a(s1, "GB", 0x40000000);
            continue; /* Loop/switch isn't completed */
        }
        v.a().b().a("Crashlytics", (new StringBuilder("Unexpected meminfo format while computing RAM: ")).append(s1).toString());
        l1 = 0L;
        continue; /* Loop/switch isn't completed */
        NumberFormatException numberformatexception;
        numberformatexception;
        v.a().b().a("Crashlytics", (new StringBuilder("Unexpected meminfo format while computing RAM: ")).append(s1).toString(), numberformatexception);
_L4:
        l1 = 0L;
        if (true) goto _L8; else goto _L7
_L7:
        Exception exception;
        exception;
        throw exception;
    }

    public static void c(String s)
    {
        if (e(v.a().getContext()))
        {
            v.a().b().a("Crashlytics", s);
        }
    }

    public static boolean c(Context context)
    {
        if (d())
        {
            return false;
        }
        return ((SensorManager)context.getSystemService("sensor")).getDefaultSensor(8) != null;
    }

    public static void d(String s)
    {
        if (e(v.a().getContext()))
        {
            v.a().b().d("Crashlytics", s);
        }
    }

    public static boolean d()
    {
        String s = android.provider.Settings.Secure.getString(v.a().getContext().getContentResolver(), "android_id");
        return "sdk".equals(Build.PRODUCT) || "google_sdk".equals(Build.PRODUCT) || s == null;
    }

    public static boolean d(Context context)
    {
        if (e == null)
        {
            boolean flag = a(context, "com.crashlytics.SilenceCrashlyticsLogCat", false);
            boolean flag1 = false;
            if (!flag)
            {
                flag1 = true;
            }
            e = Boolean.valueOf(flag1);
        }
        return e.booleanValue();
    }

    public static boolean e()
    {
        boolean flag;
        String s;
        flag = d();
        s = Build.TAGS;
        break MISSING_BLOCK_LABEL_8;
        if ((flag || s == null || !s.contains("test-keys")) && !(new File("/system/app/Superuser.apk")).exists())
        {
            File file = new File("/system/xbin/su");
            if (flag || !file.exists())
            {
                return false;
            }
        }
        return true;
    }

    public static boolean e(Context context)
    {
        if (b == null)
        {
            b = Boolean.valueOf(a(context, "com.crashlytics.Trace", false));
        }
        return b.booleanValue();
    }

    public static boolean e(String s)
    {
        return s == null || s.length() == 0;
    }

    public static int f()
    {
label0:
        {
            int j;
            boolean flag;
            if (d())
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (e())
            {
                j |= 2;
            }
            if (!Debug.isDebuggerConnected())
            {
                boolean flag1 = Debug.waitingForDebugger();
                flag = false;
                if (!flag1)
                {
                    break label0;
                }
            }
            flag = true;
        }
        if (flag)
        {
            j |= 4;
        }
        return j;
    }

    public static boolean f(Context context)
    {
        return (2 & context.getApplicationInfo().flags) != 0;
    }

    public static String g(Context context)
    {
        InputStream inputstream1 = context.getResources().openRawResource(h(context));
        InputStream inputstream = inputstream1;
        String s;
        boolean flag;
        s = b(inputstream);
        flag = e(s);
        String s1 = null;
        if (!flag)
        {
            s1 = s;
        }
        a(inputstream, "Failed to close icon input stream.");
        return s1;
        Exception exception2;
        exception2;
        inputstream = null;
_L4:
        v.a().b().a("Crashlytics", "Could not calculate hash for app icon.", exception2);
        a(inputstream, "Failed to close icon input stream.");
        return null;
        Exception exception;
        exception;
        Exception exception1;
        inputstream = null;
        exception1 = exception;
_L2:
        a(inputstream, "Failed to close icon input stream.");
        throw exception1;
        exception1;
        if (true) goto _L2; else goto _L1
_L1:
        exception2;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static int h(Context context)
    {
        return context.getApplicationContext().getApplicationInfo().icon;
    }

    public static String i(Context context)
    {
        int j = a(context, "com.crashlytics.android.build_id", "string");
        String s = null;
        if (j != 0)
        {
            s = context.getResources().getString(j);
            v.a().b().a("Crashlytics", (new StringBuilder("Build ID is: ")).append(s).toString());
        }
        return s;
    }

}
