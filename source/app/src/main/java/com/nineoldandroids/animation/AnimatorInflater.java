// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.animation.AnimationUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package com.nineoldandroids.animation:
//            ObjectAnimator, AnimatorSet, Animator, ValueAnimator, 
//            ArgbEvaluator

public class AnimatorInflater
{

    private static final int a[] = {
        0x10102e2
    };
    private static final int b[] = {
        0x10102e1
    };
    private static final int c[] = {
        0x1010141, 0x1010198, 0x10101be, 0x10101bf, 0x10101c0, 0x10102de, 0x10102df, 0x10102e0
    };

    public AnimatorInflater()
    {
    }

    private static Animator a(Context context, XmlPullParser xmlpullparser, AttributeSet attributeset, AnimatorSet animatorset, int i)
    {
        int j;
        int k;
        ArrayList arraylist;
        Object obj;
        j = 0;
        k = xmlpullparser.getDepth();
        arraylist = null;
        obj = null;
_L3:
        int l = xmlpullparser.next();
        if (l == 3 && xmlpullparser.getDepth() <= k || l == 1) goto _L2; else goto _L1
_L1:
        if (l == 2)
        {
            String s = xmlpullparser.getName();
            ArrayList arraylist1;
            if (s.equals("objectAnimator"))
            {
                obj = new ObjectAnimator();
                a(context, attributeset, ((ValueAnimator) (obj)));
                TypedArray typedarray = context.obtainStyledAttributes(attributeset, b);
                ((ObjectAnimator) (obj)).setPropertyName(typedarray.getString(0));
                typedarray.recycle();
            } else
            if (s.equals("animator"))
            {
                obj = a(context, attributeset, null);
            } else
            if (s.equals("set"))
            {
                obj = new AnimatorSet();
                TypedArray typedarray1 = context.obtainStyledAttributes(attributeset, a);
                TypedValue typedvalue = new TypedValue();
                typedarray1.getValue(0, typedvalue);
                int j1;
                if (typedvalue.type == 16)
                {
                    j1 = typedvalue.data;
                } else
                {
                    j1 = 0;
                }
                a(context, xmlpullparser, attributeset, (AnimatorSet)obj, j1);
                typedarray1.recycle();
            } else
            {
                throw new RuntimeException((new StringBuilder("Unknown animator name: ")).append(xmlpullparser.getName()).toString());
            }
            if (animatorset != null)
            {
                Animator aanimator[];
                Iterator iterator;
                Animator animator;
                int i1;
                if (arraylist == null)
                {
                    arraylist1 = new ArrayList();
                } else
                {
                    arraylist1 = arraylist;
                }
                arraylist1.add(obj);
            } else
            {
                arraylist1 = arraylist;
            }
            arraylist = arraylist1;
        }
        if (true) goto _L3; else goto _L2
_L2:
label0:
        {
            if (animatorset != null && arraylist != null)
            {
                aanimator = new Animator[arraylist.size()];
                for (iterator = arraylist.iterator(); iterator.hasNext();)
                {
                    animator = (Animator)iterator.next();
                    i1 = j + 1;
                    aanimator[j] = animator;
                    j = i1;
                }

                if (i != 0)
                {
                    break label0;
                }
                animatorset.playTogether(aanimator);
            }
            return ((Animator) (obj));
        }
        animatorset.playSequentially(aanimator);
        return ((Animator) (obj));
    }

    private static ValueAnimator a(Context context, AttributeSet attributeset, ValueAnimator valueanimator)
    {
        TypedArray typedarray;
        boolean flag1;
        int j;
        boolean flag2;
        int k;
        typedarray = context.obtainStyledAttributes(attributeset, c);
        long l = typedarray.getInt(1, 0);
        long l1 = typedarray.getInt(2, 0);
        int i = typedarray.getInt(7, 0);
        if (valueanimator == null)
        {
            valueanimator = new ValueAnimator();
        }
        boolean flag;
        TypedValue typedvalue;
        TypedValue typedvalue1;
        int j1;
        if (i == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        typedvalue = typedarray.peekValue(5);
        if (typedvalue != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            j = typedvalue.type;
        } else
        {
            j = 0;
        }
        typedvalue1 = typedarray.peekValue(6);
        if (typedvalue1 != null)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (flag2)
        {
            k = typedvalue1.type;
        } else
        {
            k = 0;
        }
        if (flag1 && j >= 28 && j <= 31 || flag2 && k >= 28 && k <= 31)
        {
            flag = false;
            valueanimator.setEvaluator(new ArgbEvaluator());
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        if (flag1)
        {
            float f1;
            if (j == 5)
            {
                f1 = typedarray.getDimension(5, 0.0F);
            } else
            {
                f1 = typedarray.getFloat(5, 0.0F);
            }
            if (flag2)
            {
                float f2;
                if (k == 5)
                {
                    f2 = typedarray.getDimension(6, 0.0F);
                } else
                {
                    f2 = typedarray.getFloat(6, 0.0F);
                }
                valueanimator.setFloatValues(new float[] {
                    f1, f2
                });
            } else
            {
                valueanimator.setFloatValues(new float[] {
                    f1
                });
            }
        } else
        {
            float f;
            if (k == 5)
            {
                f = typedarray.getDimension(6, 0.0F);
            } else
            {
                f = typedarray.getFloat(6, 0.0F);
            }
            valueanimator.setFloatValues(new float[] {
                f
            });
        }
_L4:
        valueanimator.setDuration(l);
        valueanimator.setStartDelay(l1);
        if (typedarray.hasValue(3))
        {
            valueanimator.setRepeatCount(typedarray.getInt(3, 0));
        }
        if (typedarray.hasValue(4))
        {
            valueanimator.setRepeatMode(typedarray.getInt(4, 1));
        }
        j1 = typedarray.getResourceId(0, 0);
        if (j1 > 0)
        {
            valueanimator.setInterpolator(AnimationUtils.loadInterpolator(context, j1));
        }
        typedarray.recycle();
        return valueanimator;
_L2:
        int i1;
        if (flag1)
        {
            int k1;
            if (j == 5)
            {
                k1 = (int)typedarray.getDimension(5, 0.0F);
            } else
            if (j >= 28 && j <= 31)
            {
                k1 = typedarray.getColor(5, 0);
            } else
            {
                k1 = typedarray.getInt(5, 0);
            }
            if (flag2)
            {
                int i2;
                if (k == 5)
                {
                    i2 = (int)typedarray.getDimension(6, 0.0F);
                } else
                if (k >= 28 && k <= 31)
                {
                    i2 = typedarray.getColor(6, 0);
                } else
                {
                    i2 = typedarray.getInt(6, 0);
                }
                valueanimator.setIntValues(new int[] {
                    k1, i2
                });
            } else
            {
                valueanimator.setIntValues(new int[] {
                    k1
                });
            }
            continue; /* Loop/switch isn't completed */
        }
        if (!flag2)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (k != 5)
        {
            break; /* Loop/switch isn't completed */
        }
        i1 = (int)typedarray.getDimension(6, 0.0F);
_L5:
        valueanimator.setIntValues(new int[] {
            i1
        });
        if (true) goto _L4; else goto _L3
_L3:
        if (k >= 28 && k <= 31)
        {
            i1 = typedarray.getColor(6, 0);
        } else
        {
            i1 = typedarray.getInt(6, 0);
        }
          goto _L5
        if (true) goto _L4; else goto _L6
_L6:
    }

    public static Animator loadAnimator(Context context, int i)
    {
        XmlResourceParser xmlresourceparser = null;
        Animator animator;
        xmlresourceparser = context.getResources().getAnimation(i);
        animator = a(context, xmlresourceparser, Xml.asAttributeSet(xmlresourceparser), null, 0);
        if (xmlresourceparser != null)
        {
            xmlresourceparser.close();
        }
        return animator;
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
        android.content.res.Resources.NotFoundException notfoundexception1 = new android.content.res.Resources.NotFoundException((new StringBuilder("Can't load animation resource ID #0x")).append(Integer.toHexString(i)).toString());
        notfoundexception1.initCause(xmlpullparserexception);
        throw notfoundexception1;
        Exception exception;
        exception;
        if (xmlresourceparser != null)
        {
            xmlresourceparser.close();
        }
        throw exception;
        IOException ioexception;
        ioexception;
        android.content.res.Resources.NotFoundException notfoundexception = new android.content.res.Resources.NotFoundException((new StringBuilder("Can't load animation resource ID #0x")).append(Integer.toHexString(i)).toString());
        notfoundexception.initCause(ioexception);
        throw notfoundexception;
    }

}
