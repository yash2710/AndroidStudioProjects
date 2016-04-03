// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;


// Referenced classes of package com.google.android.gms.internal:
//            bh, bc

public final class bb
{

    public static final bc mT = new _cls1();
    public static final bc mU = new _cls2();
    public static final bc mV = new _cls3();
    public static final bc mW = new _cls4();
    public static final bc mX = new _cls5();
    public static final bc mY = new _cls6();
    public static final bc mZ = new _cls7();
    public static final bc na = new _cls8();
    public static final bc nb = new bh();


    private class _cls1
        implements bc
    {

        public final void b(ex ex, Map map)
        {
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements bc
    {

        public final void b(ex ex1, Map map)
        {
            String s = (String)map.get("urls");
            if (TextUtils.isEmpty(s))
            {
                eu.D("URLs missing in canOpenURLs GMSG.");
                return;
            }
            String as[] = s.split(",");
            HashMap hashmap = new HashMap();
            PackageManager packagemanager = ex1.getContext().getPackageManager();
            int i = as.length;
            int j = 0;
            while (j < i) 
            {
                String s1 = as[j];
                String as1[] = s1.split(";", 2);
                String s2 = as1[0].trim();
                String s3;
                boolean flag;
                if (as1.length > 1)
                {
                    s3 = as1[1].trim();
                } else
                {
                    s3 = "android.intent.action.VIEW";
                }
                if (packagemanager.resolveActivity(new Intent(s3, Uri.parse(s2)), 0x10000) != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                hashmap.put(s1, Boolean.valueOf(flag));
                j++;
            }
            ex1.a("openableURLs", hashmap);
        }

        _cls2()
        {
        }
    }


    private class _cls3
        implements bc
    {

        public final void b(ex ex1, Map map)
        {
            String s;
            Uri uri;
            s = (String)map.get("u");
            if (s == null)
            {
                eu.D("URL missing from click GMSG.");
                return;
            }
            uri = Uri.parse(s);
            k k1 = ex1.cc();
            if (k1 == null) goto _L2; else goto _L1
_L1:
            if (!k1.b(uri)) goto _L2; else goto _L3
_L3:
            Uri uri2 = k1.a(uri, ex1.getContext());
            Uri uri1 = uri2;
_L5:
            String s1 = uri1.toString();
            (new es(ex1.getContext(), ex1.cd().sw, s1)).start();
            return;
            l l1;
            l1;
            eu.D((new StringBuilder("Unable to append parameter to URL: ")).append(s).toString());
_L2:
            uri1 = uri;
            if (true) goto _L5; else goto _L4
_L4:
        }

        _cls3()
        {
        }
    }


    private class _cls4
        implements bc
    {

        public final void b(ex ex1, Map map)
        {
            cf cf1 = ex1.ca();
            if (cf1 == null)
            {
                eu.D("A GMSG tried to close something that wasn't an overlay.");
                return;
            } else
            {
                cf1.close();
                return;
            }
        }

        _cls4()
        {
        }
    }


    private class _cls5
        implements bc
    {

        public final void b(ex ex1, Map map)
        {
            cf cf1 = ex1.ca();
            if (cf1 == null)
            {
                eu.D("A GMSG tried to use a custom close button on something that wasn't an overlay.");
                return;
            } else
            {
                cf1.j("1".equals(map.get("custom_close")));
                return;
            }
        }

        _cls5()
        {
        }
    }


    private class _cls6
        implements bc
    {

        public final void b(ex ex1, Map map)
        {
            String s = (String)map.get("u");
            if (s == null)
            {
                eu.D("URL missing from httpTrack GMSG.");
                return;
            } else
            {
                (new es(ex1.getContext(), ex1.cd().sw, s)).start();
                return;
            }
        }

        _cls6()
        {
        }
    }


    private class _cls7
        implements bc
    {

        public final void b(ex ex, Map map)
        {
            eu.B((new StringBuilder("Received log message: ")).append((String)map.get("string")).toString());
        }

        _cls7()
        {
        }
    }


    private class _cls8
        implements bc
    {

        public final void b(ex ex1, Map map)
        {
            String s = (String)map.get("tx");
            String s1 = (String)map.get("ty");
            String s2 = (String)map.get("td");
            int i;
            int j;
            int l;
            k k1;
            try
            {
                i = Integer.parseInt(s);
                j = Integer.parseInt(s1);
                l = Integer.parseInt(s2);
                k1 = ex1.cc();
            }
            catch (NumberFormatException numberformatexception)
            {
                eu.D("Could not parse touch parameters from gmsg.");
                return;
            }
            if (k1 == null)
            {
                break MISSING_BLOCK_LABEL_85;
            }
            k1.z().a(i, j, l);
        }

        _cls8()
        {
        }
    }

}
