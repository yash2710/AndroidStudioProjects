// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;


public final class  extends Enum
{

    public static final f a;
    public static final f b;
    public static final f c;
    public static final f d;
    public static final f e;
    private static final f f[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(crittercism/android/f$a, s);
    }

    public static [] values()
    {
        return ([])f.clone();
    }

    static 
    {
        a = new <init>("NO_INTERNET", 0);
        b = new <init>("CONN_TIMEOUT", 1);
        c = new <init>("UNKNOWN_HOST", 2);
        d = new <init>("WTF", 3);
        e = new <init>("FILE_NOT_FOUND", 4);
        lone alone[] = new <init>[5];
        alone[0] = a;
        alone[1] = b;
        alone[2] = c;
        alone[3] = d;
        alone[4] = e;
        f = alone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
