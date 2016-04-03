// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.text;


// Referenced classes of package android.support.v4.text:
//            TextDirectionHeuristicsCompat

class nAlgorithm
    implements nAlgorithm
{

    public static final nAlgorithm INSTANCE = new <init>();

    public int checkRtl(CharSequence charsequence, int i, int j)
    {
        int k = i + j;
        int l;
        for (l = 2; i < k && l == 2; i++)
        {
            l = TextDirectionHeuristicsCompat.access$100(Character.getDirectionality(charsequence.charAt(i)));
        }

        return l;
    }


    private nAlgorithm()
    {
    }
}
