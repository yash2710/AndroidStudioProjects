// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson;


// Referenced classes of package com.google.gson:
//            FieldNamingStrategy, b, c, d, 
//            e, f

public abstract class FieldNamingPolicy extends Enum
    implements FieldNamingStrategy
{

    public static final FieldNamingPolicy IDENTITY;
    public static final FieldNamingPolicy LOWER_CASE_WITH_DASHES;
    public static final FieldNamingPolicy LOWER_CASE_WITH_UNDERSCORES;
    public static final FieldNamingPolicy UPPER_CAMEL_CASE;
    public static final FieldNamingPolicy UPPER_CAMEL_CASE_WITH_SPACES;
    private static final FieldNamingPolicy a[];

    private FieldNamingPolicy(String s, int i)
    {
        super(s, i);
    }

    FieldNamingPolicy(String s, int i, byte byte0)
    {
        this(s, i);
    }

    static String a(String s)
    {
        int i = 0;
        StringBuilder stringbuilder = new StringBuilder();
        char c1;
        for (c1 = s.charAt(0); i < -1 + s.length() && !Character.isLetter(c1); c1 = s.charAt(i))
        {
            stringbuilder.append(c1);
            i++;
        }

        if (i == s.length())
        {
            s = stringbuilder.toString();
        } else
        if (!Character.isUpperCase(c1))
        {
            char c2 = Character.toUpperCase(c1);
            int j = i + 1;
            String s1;
            if (j < s.length())
            {
                s1 = (new StringBuilder()).append(c2).append(s.substring(j)).toString();
            } else
            {
                s1 = String.valueOf(c2);
            }
            return stringbuilder.append(s1).toString();
        }
        return s;
    }

    static String a(String s, String s1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
        {
            char c1 = s.charAt(i);
            if (Character.isUpperCase(c1) && stringbuilder.length() != 0)
            {
                stringbuilder.append(s1);
            }
            stringbuilder.append(c1);
        }

        return stringbuilder.toString();
    }

    public static FieldNamingPolicy valueOf(String s)
    {
        return (FieldNamingPolicy)Enum.valueOf(com/google/gson/FieldNamingPolicy, s);
    }

    public static FieldNamingPolicy[] values()
    {
        return (FieldNamingPolicy[])a.clone();
    }

    static 
    {
        IDENTITY = new b("IDENTITY", 0);
        UPPER_CAMEL_CASE = new c("UPPER_CAMEL_CASE", 1);
        UPPER_CAMEL_CASE_WITH_SPACES = new d("UPPER_CAMEL_CASE_WITH_SPACES", 2);
        LOWER_CASE_WITH_UNDERSCORES = new e("LOWER_CASE_WITH_UNDERSCORES", 3);
        LOWER_CASE_WITH_DASHES = new f("LOWER_CASE_WITH_DASHES", 4);
        FieldNamingPolicy afieldnamingpolicy[] = new FieldNamingPolicy[5];
        afieldnamingpolicy[0] = IDENTITY;
        afieldnamingpolicy[1] = UPPER_CAMEL_CASE;
        afieldnamingpolicy[2] = UPPER_CAMEL_CASE_WITH_SPACES;
        afieldnamingpolicy[3] = LOWER_CASE_WITH_UNDERSCORES;
        afieldnamingpolicy[4] = LOWER_CASE_WITH_DASHES;
        a = afieldnamingpolicy;
    }
}
