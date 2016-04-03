// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson.internal;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

// Referenced classes of package com.google.gson.internal:
//            q, y, s, r, 
//            t, v

public final class LinkedHashTreeMap extends AbstractMap
    implements Serializable
{

    private static final Comparator d = new q();
    private static boolean j;
    final y a;
    int b;
    int c;
    private Comparator e;
    private y f[];
    private int g;
    private t h;
    private v i;

    public LinkedHashTreeMap()
    {
        this(d);
    }

    public LinkedHashTreeMap(Comparator comparator)
    {
        b = 0;
        c = 0;
        if (comparator == null)
        {
            comparator = d;
        }
        e = comparator;
        a = new y();
        f = new y[16];
        g = f.length / 2 + f.length / 4;
    }

    private y a(Object obj, boolean flag)
    {
        Comparator comparator;
        y ay[];
        int i1;
        int j1;
        y y1;
        y y2;
        int i2;
        comparator = e;
        ay = f;
        int k = obj.hashCode();
        int l = k ^ (k >>> 20 ^ k >>> 12);
        i1 = l ^ l >>> 7 ^ l >>> 4;
        j1 = i1 & -1 + ay.length;
        y1 = ay[j1];
        if (y1 == null)
        {
            break MISSING_BLOCK_LABEL_387;
        }
        Comparable comparable;
        if (comparator == d)
        {
            comparable = (Comparable)obj;
        } else
        {
            comparable = null;
        }
_L3:
        if (comparable != null)
        {
            i2 = comparable.compareTo(y1.f);
        } else
        {
            i2 = comparator.compare(obj, y1.f);
        }
        if (i2 != 0) goto _L2; else goto _L1
_L1:
        y2 = y1;
_L5:
        return y2;
_L2:
label0:
        {
            y y5;
            if (i2 < 0)
            {
                y5 = y1.b;
            } else
            {
                y5 = y1.c;
            }
            if (y5 == null)
            {
                break label0;
            }
            y1 = y5;
        }
          goto _L3
        int k1 = i2;
_L6:
        y2 = null;
        if (!flag) goto _L5; else goto _L4
_L4:
        y y3 = a;
        y y4;
        int l1;
        if (y1 == null)
        {
            if (comparator == d && !(obj instanceof Comparable))
            {
                throw new ClassCastException((new StringBuilder()).append(obj.getClass().getName()).append(" is not Comparable").toString());
            }
            y4 = new y(y1, obj, i1, y3, y3.e);
            ay[j1] = y4;
        } else
        {
            y4 = new y(y1, obj, i1, y3, y3.e);
            if (k1 < 0)
            {
                y1.b = y4;
            } else
            {
                y1.c = y4;
            }
            b(y1, true);
        }
        l1 = b;
        b = l1 + 1;
        if (l1 > g)
        {
            f = a(f);
            g = f.length / 2 + f.length / 4;
        }
        c = 1 + c;
        return y4;
        k1 = 0;
          goto _L6
    }

    private void a(y y1)
    {
        y y2 = y1.b;
        y y3 = y1.c;
        y y4 = y3.b;
        y y5 = y3.c;
        y1.c = y4;
        if (y4 != null)
        {
            y4.a = y1;
        }
        a(y1, y3);
        y3.b = y1;
        y1.a = y3;
        int k;
        int l;
        int i1;
        int j1;
        if (y2 != null)
        {
            k = y2.i;
        } else
        {
            k = 0;
        }
        if (y4 != null)
        {
            l = y4.i;
        } else
        {
            l = 0;
        }
        y1.i = 1 + Math.max(k, l);
        i1 = y1.i;
        j1 = 0;
        if (y5 != null)
        {
            j1 = y5.i;
        }
        y3.i = 1 + Math.max(i1, j1);
    }

    private void a(y y1, y y2)
    {
        y y3 = y1.a;
        y1.a = null;
        if (y2 != null)
        {
            y2.a = y3;
        }
        if (y3 != null)
        {
            if (y3.b == y1)
            {
                y3.b = y2;
                return;
            }
            if (!j && y3.c != y1)
            {
                throw new AssertionError();
            } else
            {
                y3.c = y2;
                return;
            }
        } else
        {
            int k = y1.g & -1 + f.length;
            f[k] = y2;
            return;
        }
    }

    private static y[] a(y ay[])
    {
        int k = ay.length;
        y ay1[] = new y[k << 1];
        s s1 = new s();
        r r1 = new r();
        r r2 = new r();
        int l = 0;
        while (l < k) 
        {
            y y1 = ay[l];
            if (y1 != null)
            {
                s1.a(y1);
                int i1 = 0;
                int j1 = 0;
                do
                {
                    y y2 = s1.next();
                    if (y2 == null)
                    {
                        break;
                    }
                    if ((k & y2.g) == 0)
                    {
                        j1++;
                    } else
                    {
                        i1++;
                    }
                } while (true);
                r1.a(j1);
                r2.a(i1);
                s1.a(y1);
                do
                {
                    y y3 = s1.next();
                    if (y3 == null)
                    {
                        break;
                    }
                    if ((k & y3.g) == 0)
                    {
                        r1.a(y3);
                    } else
                    {
                        r2.a(y3);
                    }
                } while (true);
                y y4;
                int k1;
                y y5;
                if (j1 > 0)
                {
                    y4 = r1.a();
                } else
                {
                    y4 = null;
                }
                ay1[l] = y4;
                k1 = l + k;
                if (i1 > 0)
                {
                    y5 = r2.a();
                } else
                {
                    y5 = null;
                }
                ay1[k1] = y5;
            }
            l++;
        }
        return ay1;
    }

    private y b(Object obj)
    {
        y y1 = null;
        if (obj != null)
        {
            y y2;
            try
            {
                y2 = a(obj, false);
            }
            catch (ClassCastException classcastexception)
            {
                return null;
            }
            y1 = y2;
        }
        return y1;
    }

    private void b(y y1)
    {
        y y2 = y1.b;
        y y3 = y1.c;
        y y4 = y2.b;
        y y5 = y2.c;
        y1.b = y5;
        if (y5 != null)
        {
            y5.a = y1;
        }
        a(y1, y2);
        y2.c = y1;
        y1.a = y2;
        int k;
        int l;
        int i1;
        int j1;
        if (y3 != null)
        {
            k = y3.i;
        } else
        {
            k = 0;
        }
        if (y5 != null)
        {
            l = y5.i;
        } else
        {
            l = 0;
        }
        y1.i = 1 + Math.max(k, l);
        i1 = y1.i;
        j1 = 0;
        if (y4 != null)
        {
            j1 = y4.i;
        }
        y2.i = 1 + Math.max(i1, j1);
    }

    private void b(y y1, boolean flag)
    {
_L6:
        if (y1 == null) goto _L2; else goto _L1
_L1:
        y y2;
        y y3;
        int k;
        int l;
        int i1;
        y2 = y1.b;
        y3 = y1.c;
        y y6;
        y y7;
        if (y2 != null)
        {
            k = y2.i;
        } else
        {
            k = 0;
        }
        if (y3 != null)
        {
            l = y3.i;
        } else
        {
            l = 0;
        }
        i1 = k - l;
        if (i1 != -2) goto _L4; else goto _L3
_L3:
        y6 = y3.b;
        y7 = y3.c;
        int i2;
        int j2;
        int k2;
        if (y7 != null)
        {
            i2 = y7.i;
        } else
        {
            i2 = 0;
        }
        if (y6 != null)
        {
            j2 = y6.i;
        } else
        {
            j2 = 0;
        }
        k2 = j2 - i2;
        if (k2 == -1 || k2 == 0 && !flag)
        {
            a(y1);
        } else
        {
            if (!j && k2 != 1)
            {
                throw new AssertionError();
            }
            b(y3);
            a(y1);
        }
        if (flag) goto _L2; else goto _L5
_L5:
        y1 = y1.a;
          goto _L6
_L4:
        if (i1 != 2) goto _L8; else goto _L7
_L7:
        y y4 = y2.b;
        y y5 = y2.c;
        int j1;
        int k1;
        int l1;
        if (y5 != null)
        {
            j1 = y5.i;
        } else
        {
            j1 = 0;
        }
        if (y4 != null)
        {
            k1 = y4.i;
        } else
        {
            k1 = 0;
        }
        l1 = k1 - j1;
        if (l1 == 1 || l1 == 0 && !flag)
        {
            b(y1);
        } else
        {
            if (!j && l1 != -1)
            {
                throw new AssertionError();
            }
            a(y2);
            b(y1);
        }
        if (!flag) goto _L5; else goto _L2
_L2:
        return;
_L8:
        if (i1 == 0)
        {
            y1.i = k + 1;
            if (flag)
            {
                return;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (!j && i1 != -1 && i1 != 1)
        {
            throw new AssertionError();
        }
        y1.i = 1 + Math.max(k, l);
        if (!flag) goto _L2; else goto _L5
    }

    final y a(Object obj)
    {
        y y1 = b(obj);
        if (y1 != null)
        {
            a(y1, true);
        }
        return y1;
    }

    final y a(java.util.Map.Entry entry)
    {
        boolean flag;
        flag = true;
        y y1 = b(entry.getKey());
        if (y1 == null)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        Object obj = y1.h;
        Object obj1 = entry.getValue();
        boolean flag1;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_73;
        }
_L1:
        if (flag)
        {
            return y1;
        } else
        {
            return null;
        }
        flag = false;
          goto _L1
    }

    final void a(y y1, boolean flag)
    {
        if (flag)
        {
            y1.e.d = y1.d;
            y1.d.e = y1.e;
            y1.e = null;
            y1.d = null;
        }
        y y2 = y1.b;
        y y3 = y1.c;
        y y4 = y1.a;
        if (y2 != null && y3 != null)
        {
            y y5;
            y y6;
            int k;
            y y7;
            int l;
            if (y2.i > y3.i)
            {
                y5 = y2.last();
            } else
            {
                y5 = y3.first();
            }
            a(y5, false);
            y6 = y1.b;
            if (y6 != null)
            {
                k = y6.i;
                y5.b = y6;
                y6.a = y5;
                y1.b = null;
            } else
            {
                k = 0;
            }
            y7 = y1.c;
            l = 0;
            if (y7 != null)
            {
                l = y7.i;
                y5.c = y7;
                y7.a = y5;
                y1.c = null;
            }
            y5.i = 1 + Math.max(k, l);
            a(y1, y5);
            return;
        }
        if (y2 != null)
        {
            a(y1, y2);
            y1.b = null;
        } else
        if (y3 != null)
        {
            a(y1, y3);
            y1.c = null;
        } else
        {
            a(y1, ((y) (null)));
        }
        b(y4, false);
        b = -1 + b;
        c = 1 + c;
    }

    public final void clear()
    {
        Arrays.fill(f, null);
        b = 0;
        c = 1 + c;
        y y1 = a;
        y y3;
        for (y y2 = y1.d; y2 != y1; y2 = y3)
        {
            y3 = y2.d;
            y2.e = null;
            y2.d = null;
        }

        y1.e = y1;
        y1.d = y1;
    }

    public final boolean containsKey(Object obj)
    {
        return b(obj) != null;
    }

    public final Set entrySet()
    {
        t t1 = h;
        if (t1 != null)
        {
            return t1;
        } else
        {
            t t2 = new t(this);
            h = t2;
            return t2;
        }
    }

    public final Object get(Object obj)
    {
        y y1 = b(obj);
        if (y1 != null)
        {
            return y1.h;
        } else
        {
            return null;
        }
    }

    public final Set keySet()
    {
        v v1 = i;
        if (v1 != null)
        {
            return v1;
        } else
        {
            v v2 = new v(this);
            i = v2;
            return v2;
        }
    }

    public final Object put(Object obj, Object obj1)
    {
        if (obj == null)
        {
            throw new NullPointerException("key == null");
        } else
        {
            y y1 = a(obj, true);
            Object obj2 = y1.h;
            y1.h = obj1;
            return obj2;
        }
    }

    public final Object remove(Object obj)
    {
        y y1 = a(obj);
        if (y1 != null)
        {
            return y1.h;
        } else
        {
            return null;
        }
    }

    public final int size()
    {
        return b;
    }

    static 
    {
        boolean flag;
        if (!com/google/gson/internal/LinkedHashTreeMap.desiredAssertionStatus())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        j = flag;
    }
}
