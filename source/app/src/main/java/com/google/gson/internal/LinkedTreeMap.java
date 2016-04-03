// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson.internal;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Set;

// Referenced classes of package com.google.gson.internal:
//            z, F, A, C

public final class LinkedTreeMap extends AbstractMap
    implements Serializable
{

    private static final Comparator d = new z();
    private static boolean i;
    int a;
    int b;
    final F c;
    private Comparator e;
    private F f;
    private A g;
    private C h;

    public LinkedTreeMap()
    {
        this(d);
    }

    public LinkedTreeMap(Comparator comparator)
    {
        a = 0;
        b = 0;
        c = new F();
        if (comparator == null)
        {
            comparator = d;
        }
        e = comparator;
    }

    private F a(Object obj, boolean flag)
    {
        Comparator comparator;
        F f1;
        F f3;
        int k;
        comparator = e;
        f1 = f;
        if (f1 == null)
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
            k = comparable.compareTo(f1.f);
        } else
        {
            k = comparator.compare(obj, f1.f);
        }
        if (k != 0) goto _L2; else goto _L1
_L1:
        f3 = f1;
_L5:
        return f3;
_L2:
label0:
        {
            F f6;
            if (k < 0)
            {
                f6 = f1.b;
            } else
            {
                f6 = f1.c;
            }
            if (f6 == null)
            {
                break label0;
            }
            f1 = f6;
        }
          goto _L3
        F f2;
        int j;
        int l = k;
        f2 = f1;
        j = l;
_L6:
        f3 = null;
        if (!flag) goto _L5; else goto _L4
_L4:
        F f4 = c;
        F f5;
        if (f2 == null)
        {
            if (comparator == d && !(obj instanceof Comparable))
            {
                throw new ClassCastException((new StringBuilder()).append(obj.getClass().getName()).append(" is not Comparable").toString());
            }
            f5 = new F(f2, obj, f4, f4.e);
            f = f5;
        } else
        {
            f5 = new F(f2, obj, f4, f4.e);
            if (j < 0)
            {
                f2.b = f5;
            } else
            {
                f2.c = f5;
            }
            b(f2, true);
        }
        a = 1 + a;
        b = 1 + b;
        return f5;
        f2 = f1;
        j = 0;
          goto _L6
    }

    private void a(F f1)
    {
        F f2 = f1.b;
        F f3 = f1.c;
        F f4 = f3.b;
        F f5 = f3.c;
        f1.c = f4;
        if (f4 != null)
        {
            f4.a = f1;
        }
        a(f1, f3);
        f3.b = f1;
        f1.a = f3;
        int j;
        int k;
        int l;
        int i1;
        if (f2 != null)
        {
            j = f2.h;
        } else
        {
            j = 0;
        }
        if (f4 != null)
        {
            k = f4.h;
        } else
        {
            k = 0;
        }
        f1.h = 1 + Math.max(j, k);
        l = f1.h;
        i1 = 0;
        if (f5 != null)
        {
            i1 = f5.h;
        }
        f3.h = 1 + Math.max(l, i1);
    }

    private void a(F f1, F f2)
    {
        F f3 = f1.a;
        f1.a = null;
        if (f2 != null)
        {
            f2.a = f3;
        }
        if (f3 != null)
        {
            if (f3.b == f1)
            {
                f3.b = f2;
                return;
            }
            if (!i && f3.c != f1)
            {
                throw new AssertionError();
            } else
            {
                f3.c = f2;
                return;
            }
        } else
        {
            f = f2;
            return;
        }
    }

    private F b(Object obj)
    {
        F f1 = null;
        if (obj != null)
        {
            F f2;
            try
            {
                f2 = a(obj, false);
            }
            catch (ClassCastException classcastexception)
            {
                return null;
            }
            f1 = f2;
        }
        return f1;
    }

    private void b(F f1)
    {
        F f2 = f1.b;
        F f3 = f1.c;
        F f4 = f2.b;
        F f5 = f2.c;
        f1.b = f5;
        if (f5 != null)
        {
            f5.a = f1;
        }
        a(f1, f2);
        f2.c = f1;
        f1.a = f2;
        int j;
        int k;
        int l;
        int i1;
        if (f3 != null)
        {
            j = f3.h;
        } else
        {
            j = 0;
        }
        if (f5 != null)
        {
            k = f5.h;
        } else
        {
            k = 0;
        }
        f1.h = 1 + Math.max(j, k);
        l = f1.h;
        i1 = 0;
        if (f4 != null)
        {
            i1 = f4.h;
        }
        f2.h = 1 + Math.max(l, i1);
    }

    private void b(F f1, boolean flag)
    {
_L6:
        if (f1 == null) goto _L2; else goto _L1
_L1:
        F f2;
        F f3;
        int j;
        int k;
        int l;
        f2 = f1.b;
        f3 = f1.c;
        F f6;
        F f7;
        if (f2 != null)
        {
            j = f2.h;
        } else
        {
            j = 0;
        }
        if (f3 != null)
        {
            k = f3.h;
        } else
        {
            k = 0;
        }
        l = j - k;
        if (l != -2) goto _L4; else goto _L3
_L3:
        f6 = f3.b;
        f7 = f3.c;
        int l1;
        int i2;
        int j2;
        if (f7 != null)
        {
            l1 = f7.h;
        } else
        {
            l1 = 0;
        }
        if (f6 != null)
        {
            i2 = f6.h;
        } else
        {
            i2 = 0;
        }
        j2 = i2 - l1;
        if (j2 == -1 || j2 == 0 && !flag)
        {
            a(f1);
        } else
        {
            if (!i && j2 != 1)
            {
                throw new AssertionError();
            }
            b(f3);
            a(f1);
        }
        if (flag) goto _L2; else goto _L5
_L5:
        f1 = f1.a;
          goto _L6
_L4:
        if (l != 2) goto _L8; else goto _L7
_L7:
        F f4 = f2.b;
        F f5 = f2.c;
        int i1;
        int j1;
        int k1;
        if (f5 != null)
        {
            i1 = f5.h;
        } else
        {
            i1 = 0;
        }
        if (f4 != null)
        {
            j1 = f4.h;
        } else
        {
            j1 = 0;
        }
        k1 = j1 - i1;
        if (k1 == 1 || k1 == 0 && !flag)
        {
            b(f1);
        } else
        {
            if (!i && k1 != -1)
            {
                throw new AssertionError();
            }
            a(f2);
            b(f1);
        }
        if (!flag) goto _L5; else goto _L2
_L2:
        return;
_L8:
        if (l == 0)
        {
            f1.h = j + 1;
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
        f1.h = 1 + Math.max(j, k);
        if (!flag) goto _L2; else goto _L5
    }

    final F a(Object obj)
    {
        F f1 = b(obj);
        if (f1 != null)
        {
            a(f1, true);
        }
        return f1;
    }

    final F a(java.util.Map.Entry entry)
    {
        boolean flag;
        flag = true;
        F f1 = b(entry.getKey());
        if (f1 == null)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        Object obj = f1.g;
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
            return f1;
        } else
        {
            return null;
        }
        flag = false;
          goto _L1
    }

    final void a(F f1, boolean flag)
    {
        if (flag)
        {
            f1.e.d = f1.d;
            f1.d.e = f1.e;
        }
        F f2 = f1.b;
        F f3 = f1.c;
        F f4 = f1.a;
        if (f2 != null && f3 != null)
        {
            F f5;
            F f6;
            int j;
            F f7;
            int k;
            if (f2.h > f3.h)
            {
                f5 = f2.last();
            } else
            {
                f5 = f3.first();
            }
            a(f5, false);
            f6 = f1.b;
            if (f6 != null)
            {
                j = f6.h;
                f5.b = f6;
                f6.a = f5;
                f1.b = null;
            } else
            {
                j = 0;
            }
            f7 = f1.c;
            k = 0;
            if (f7 != null)
            {
                k = f7.h;
                f5.c = f7;
                f7.a = f5;
                f1.c = null;
            }
            f5.h = 1 + Math.max(j, k);
            a(f1, f5);
            return;
        }
        if (f2 != null)
        {
            a(f1, f2);
            f1.b = null;
        } else
        if (f3 != null)
        {
            a(f1, f3);
            f1.c = null;
        } else
        {
            a(f1, ((F) (null)));
        }
        b(f4, false);
        a = -1 + a;
        b = 1 + b;
    }

    public final void clear()
    {
        f = null;
        a = 0;
        b = 1 + b;
        F f1 = c;
        f1.e = f1;
        f1.d = f1;
    }

    public final boolean containsKey(Object obj)
    {
        return b(obj) != null;
    }

    public final Set entrySet()
    {
        A a1 = g;
        if (a1 != null)
        {
            return a1;
        } else
        {
            A a2 = new A(this);
            g = a2;
            return a2;
        }
    }

    public final Object get(Object obj)
    {
        F f1 = b(obj);
        if (f1 != null)
        {
            return f1.g;
        } else
        {
            return null;
        }
    }

    public final Set keySet()
    {
        C c1 = h;
        if (c1 != null)
        {
            return c1;
        } else
        {
            C c2 = new C(this);
            h = c2;
            return c2;
        }
    }

    public final Object put(Object obj, Object obj1)
    {
        if (obj == null)
        {
            throw new NullPointerException("key == null");
        } else
        {
            F f1 = a(obj, true);
            Object obj2 = f1.g;
            f1.g = obj1;
            return obj2;
        }
    }

    public final Object remove(Object obj)
    {
        F f1 = a(obj);
        if (f1 != null)
        {
            return f1.g;
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
        if (!com/google/gson/internal/LinkedTreeMap.desiredAssertionStatus())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        i = flag;
    }
}
