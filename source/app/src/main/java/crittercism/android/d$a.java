// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;


public final class  extends Enum
{

    public static final j a;
    public static final j b;
    public static final j c;
    public static final j d;
    public static final j e;
    public static final j f;
    public static final j g;
    public static final j h;
    public static final j i;
    private static final j j[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(crittercism/android/d$a, s);
    }

    public static [] values()
    {
        return ([])j.clone();
    }

    static 
    {
        a = new <init>("LOCAL_EMULATOR", 0);
        b = new <init>("LOCAL_DEVICE", 1);
        c = new <init>("OFFICE", 2);
        d = new <init>("OFFICE_YOUSEF", 3);
        e = new <init>("APT_YOUSEF", 4);
        f = new <init>("OTHER", 5);
        g = new <init>("KEVIN_ROB", 6);
        h = new <init>("STAGING", 7);
        i = new <init>("PRODUCTION", 8);
        lone alone[] = new <init>[9];
        alone[0] = a;
        alone[1] = b;
        alone[2] = c;
        alone[3] = d;
        alone[4] = e;
        alone[5] = f;
        alone[6] = g;
        alone[7] = h;
        alone[8] = i;
        j = alone;
    }

    private (String s, int k)
    {
        super(s, k);
    }
}
