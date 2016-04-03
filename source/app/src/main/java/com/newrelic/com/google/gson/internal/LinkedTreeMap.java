// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson.internal;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Set;

// Referenced classes of package com.newrelic.com.google.gson.internal:
//            q, w, r, t

public final class LinkedTreeMap extends AbstractMap
    implements Serializable
{

    private static final Comparator d = new q();
    private static boolean i;
    int a;
    int b;
    final w c;
    private Comparator e;
    private w f;
    private r g;
    private t h;

    public LinkedTreeMap()
    {
        this(d);
    }

    public LinkedTreeMap(Comparator comparator)
    {
        a = 0;
        b = 0;
        c = new w();
        if (comparator == null)
        {
            comparator = d;
        }
        e = comparator;
    }

    private w a(Object obj, boolean flag)
    {
        Comparator comparator;
        w w1;
        w w3;
        int k;
        comparator = e;
        w1 = f;
        if (w1 == null)
        {
            break MISSING_BLOCK_LABEL_293;
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
            k = comparable.compareTo(w1.f);
        } else
        {
            k = comparator.compare(obj, w1.f);
        }
        if (k != 0) goto _L2; else goto _L1
_L1:
        w3 = w1;
_L5:
        return w3;
_L2:
label0:
        {
            w w6;
            if (k < 0)
            {
                w6 = w1.b;
            } else
            {
                w6 = w1.c;
            }
            if (w6 == null)
            {
                break label0;
            }
            w1 = w6;
        }
          goto _L3
        w w2;
        int j;
        int l = k;
        w2 = w1;
        j = l;
_L6:
        w3 = null;
        if (!flag) goto _L5; else goto _L4
_L4:
        w w4 = c;
        w w5;
        if (w2 == null)
        {
            if (comparator == d && !(obj instanceof Comparable))
            {
                throw new ClassCastException((new StringBuilder()).append(obj.getClass().getName()).append(" is not Comparable").toString());
            }
            w5 = new w(w2, obj, w4, w4.e);
            f = w5;
        } else
        {
            w5 = new w(w2, obj, w4, w4.e);
            if (j < 0)
            {
                w2.b = w5;
            } else
            {
                w2.c = w5;
            }
            b(w2, true);
        }
        a = 1 + a;
        b = 1 + b;
        return w5;
        w2 = w1;
        j = 0;
          goto _L6
    }

    private void a(w w1)
    {
        w w2 = w1.b;
        w w3 = w1.c;
        w w4 = w3.b;
        w w5 = w3.c;
        w1.c = w4;
        if (w4 != null)
        {
            w4.a = w1;
        }
        a(w1, w3);
        w3.b = w1;
        w1.a = w3;
        int j;
        int k;
        int l;
        int i1;
        if (w2 != null)
        {
            j = w2.h;
        } else
        {
            j = 0;
        }
        if (w4 != null)
        {
            k = w4.h;
        } else
        {
            k = 0;
        }
        w1.h = 1 + Math.max(j, k);
        l = w1.h;
        i1 = 0;
        if (w5 != null)
        {
            i1 = w5.h;
        }
        w3.h = 1 + Math.max(l, i1);
    }

    private void a(w w1, w w2)
    {
        w w3 = w1.a;
        w1.a = null;
        if (w2 != null)
        {
            w2.a = w3;
        }
        if (w3 != null)
        {
            if (w3.b == w1)
            {
                w3.b = w2;
                return;
            }
            if (!i && w3.c != w1)
            {
                throw new AssertionError();
            } else
            {
                w3.c = w2;
                return;
            }
        } else
        {
            f = w2;
            return;
        }
    }

    private w b(Object obj)
    {
        w w1 = null;
        if (obj != null)
        {
            w w2;
            try
            {
                w2 = a(obj, false);
            }
            catch (ClassCastException classcastexception)
            {
                return null;
            }
            w1 = w2;
        }
        return w1;
    }

    private void b(w w1)
    {
        w w2 = w1.b;
        w w3 = w1.c;
        w w4 = w2.b;
        w w5 = w2.c;
        w1.b = w5;
        if (w5 != null)
        {
            w5.a = w1;
        }
        a(w1, w2);
        w2.c = w1;
        w1.a = w2;
        int j;
        int k;
        int l;
        int i1;
        if (w3 != null)
        {
            j = w3.h;
        } else
        {
            j = 0;
        }
        if (w5 != null)
        {
            k = w5.h;
        } else
        {
            k = 0;
        }
        w1.h = 1 + Math.max(j, k);
        l = w1.h;
        i1 = 0;
        if (w4 != null)
        {
            i1 = w4.h;
        }
        w2.h = 1 + Math.max(l, i1);
    }

    private void b(w w1, boolean flag)
    {
_L6:
        if (w1 == null) goto _L2; else goto _L1
_L1:
        w w2;
        w w3;
        int j;
        int k;
        int l;
        w2 = w1.b;
        w3 = w1.c;
        w w6;
        w w7;
        if (w2 != null)
        {
            j = w2.h;
        } else
        {
            j = 0;
        }
        if (w3 != null)
        {
            k = w3.h;
        } else
        {
            k = 0;
        }
        l = j - k;
        if (l != -2) goto _L4; else goto _L3
_L3:
        w6 = w3.b;
        w7 = w3.c;
        int l1;
        int i2;
        int j2;
        if (w7 != null)
        {
            l1 = w7.h;
        } else
        {
            l1 = 0;
        }
        if (w6 != null)
        {
            i2 = w6.h;
        } else
        {
            i2 = 0;
        }
        j2 = i2 - l1;
        if (j2 == -1 || j2 == 0 && !flag)
        {
            a(w1);
        } else
        {
            if (!i && j2 != 1)
            {
                throw new AssertionError();
            }
            b(w3);
            a(w1);
        }
        if (flag) goto _L2; else goto _L5
_L5:
        w1 = w1.a;
          goto _L6
_L4:
        if (l != 2) goto _L8; else goto _L7
_L7:
        w w4 = w2.b;
        w w5 = w2.c;
        int i1;
        int j1;
        int k1;
        if (w5 != null)
        {
            i1 = w5.h;
        } else
        {
            i1 = 0;
        }
        if (w4 != null)
        {
            j1 = w4.h;
        } else
        {
            j1 = 0;
        }
        k1 = j1 - i1;
        if (k1 == 1 || k1 == 0 && !flag)
        {
            b(w1);
        } else
        {
            if (!i && k1 != -1)
            {
                throw new AssertionError();
            }
            a(w2);
            b(w1);
        }
        if (!flag) goto _L5; else goto _L2
_L2:
        return;
_L8:
        if (l == 0)
        {
            w1.h = j + 1;
            if (flag)
            {
                return;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (!i && l != -1 && l != 1)
        {
            throw new AssertionError();
        }
        w1.h = 1 + Math.max(j, k);
        if (!flag) goto _L2; else goto _L5
    }

    final w a(Object obj)
    {
        w w1 = b(obj);
        if (w1 != null)
        {
            a(w1, true);
        }
        return w1;
    }

    final w a(java.util.Map.Entry entry)
    {
        boolean flag;
        flag = true;
        w w1 = b(entry.getKey());
        if (w1 == null)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        Object obj = w1.g;
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
            return w1;
        } else
        {
            return null;
        }
        flag = false;
          goto _L1
    }

    final void a(w w1, boolean flag)
    {
        if (flag)
        {
            w1.e.d = w1.d;
            w1.d.e = w1.e;
        }
        w w2 = w1.b;
        w w3 = w1.c;
        w w4 = w1.a;
        if (w2 != null && w3 != null)
        {
            w w5;
            w w6;
            int j;
            w w7;
            int k;
            if (w2.h > w3.h)
            {
                w5 = w2.last();
            } else
            {
                w5 = w3.first();
            }
            a(w5, false);
            w6 = w1.b;
            if (w6 != null)
            {
                j = w6.h;
                w5.b = w6;
                w6.a = w5;
                w1.b = null;
            } else
            {
                j = 0;
            }
            w7 = w1.c;
            k = 0;
            if (w7 != null)
            {
                k = w7.h;
                w5.c = w7;
                w7.a = w5;
                w1.c = null;
            }
            w5.h = 1 + Math.max(j, k);
            a(w1, w5);
            return;
        }
        if (w2 != null)
        {
            a(w1, w2);
            w1.b = null;
        } else
        if (w3 != null)
        {
            a(w1, w3);
            w1.c = null;
        } else
        {
            a(w1, ((w) (null)));
        }
        b(w4, false);
        a = -1 + a;
        b = 1 + b;
    }

    public final void clear()
    {
        f = null;
        a = 0;
        b = 1 + b;
        w w1 = c;
        w1.e = w1;
        w1.d = w1;
    }

    public final boolean containsKey(Object obj)
    {
        return b(obj) != null;
    }

    public final Set entrySet()
    {
        r r1 = g;
        if (r1 != null)
        {
            return r1;
        } else
        {
            r r2 = new r(this);
            g = r2;
            return r2;
        }
    }

    public final Object get(Object obj)
    {
        w w1 = b(obj);
        if (w1 != null)
        {
            return w1.g;
        } else
        {
            return null;
        }
    }

    public final Set keySet()
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

    public final Object put(Object obj, Object obj1)
    {
        if (obj == null)
        {
            throw new NullPointerException("key == null");
        } else
        {
            w w1 = a(obj, true);
            Object obj2 = w1.g;
            w1.g = obj1;
            return obj2;
        }
    }

    public final Object remove(Object obj)
    {
        w w1 = a(obj);
        if (w1 != null)
        {
            return w1.g;
        } else
        {
            return null;
        }
    }

    public final int size()
    {
        return a;
    }

    static 
    {
        boolean flag;
        if (!com/newrelic/com/google/gson/internal/LinkedTreeMap.desiredAssertionStatus())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        i = flag;
    }
}
