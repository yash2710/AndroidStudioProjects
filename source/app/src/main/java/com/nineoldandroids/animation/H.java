// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;

import android.os.Handler;
import android.os.Message;
import android.view.animation.AnimationUtils;
import java.util.ArrayList;

// Referenced classes of package com.nineoldandroids.animation:
//            ValueAnimator

final class H extends Handler
{

    private H()
    {
    }

    H(byte byte0)
    {
        this();
    }

    public final void handleMessage(Message message)
    {
        ArrayList arraylist;
        ArrayList arraylist1;
        arraylist = (ArrayList)ValueAnimator.b().get();
        arraylist1 = (ArrayList)ValueAnimator.c().get();
        message.what;
        JVM INSTR tableswitch 0 1: default 48
    //                   0 49
    //                   1 160;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        ArrayList arraylist4 = (ArrayList)ValueAnimator.d().get();
        boolean flag;
        long l;
        ArrayList arraylist2;
        ArrayList arraylist3;
        int i;
        int j;
        int k;
        int i1;
        int j1;
        int k1;
        int l1;
        ValueAnimator valueanimator;
        int i2;
        ValueAnimator valueanimator1;
        ValueAnimator valueanimator2;
        if (arraylist.size() > 0 || arraylist1.size() > 0)
        {
            flag = false;
        } else
        {
            flag = true;
        }
        while (arraylist4.size() > 0) 
        {
            ArrayList arraylist5 = (ArrayList)arraylist4.clone();
            arraylist4.clear();
            int j2 = arraylist5.size();
            int k2 = 0;
            while (k2 < j2) 
            {
                ValueAnimator valueanimator3 = (ValueAnimator)arraylist5.get(k2);
                if (ValueAnimator.a(valueanimator3) == 0L)
                {
                    ValueAnimator.b(valueanimator3);
                } else
                {
                    arraylist1.add(valueanimator3);
                }
                k2++;
            }
        }
        break; /* Loop/switch isn't completed */
_L3:
        flag = true;
        l = AnimationUtils.currentAnimationTimeMillis();
        arraylist2 = (ArrayList)ValueAnimator.e().get();
        arraylist3 = (ArrayList)ValueAnimator.f().get();
        i = arraylist1.size();
        for (j = 0; j < i; j++)
        {
            valueanimator2 = (ValueAnimator)arraylist1.get(j);
            if (ValueAnimator.a(valueanimator2, l))
            {
                arraylist2.add(valueanimator2);
            }
        }

        k = arraylist2.size();
        if (k > 0)
        {
            for (i2 = 0; i2 < k; i2++)
            {
                valueanimator1 = (ValueAnimator)arraylist2.get(i2);
                ValueAnimator.b(valueanimator1);
                ValueAnimator.a(valueanimator1, true);
                arraylist1.remove(valueanimator1);
            }

            arraylist2.clear();
        }
        i1 = arraylist.size();
        j1 = 0;
        for (k1 = i1; j1 < k1;)
        {
            valueanimator = (ValueAnimator)arraylist.get(j1);
            if (valueanimator.a(l))
            {
                arraylist3.add(valueanimator);
            }
            if (arraylist.size() == k1)
            {
                j1++;
            } else
            {
                k1--;
                arraylist3.remove(valueanimator);
            }
        }

        if (arraylist3.size() > 0)
        {
            for (l1 = 0; l1 < arraylist3.size(); l1++)
            {
                ValueAnimator.c((ValueAnimator)arraylist3.get(l1));
            }

            arraylist3.clear();
        }
        if (flag && (!arraylist.isEmpty() || !arraylist1.isEmpty()))
        {
            sendEmptyMessageDelayed(1, Math.max(0L, ValueAnimator.g() - (AnimationUtils.currentAnimationTimeMillis() - l)));
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }
}
