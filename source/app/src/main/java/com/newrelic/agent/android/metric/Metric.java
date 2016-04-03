// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.metric;

import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;

public class Metric extends HarvestableObject
{

    private String a;
    private String b;
    private Double c;
    private Double d;
    private Double e;
    private Double f;
    private Double g;
    private long h;

    public Metric(Metric metric)
    {
        a = metric.getName();
        b = metric.getScope();
        c = Double.valueOf(metric.getMin());
        d = Double.valueOf(metric.getMax());
        e = Double.valueOf(metric.getTotal());
        f = Double.valueOf(metric.getSumOfSquares());
        g = Double.valueOf(metric.getExclusive());
        h = metric.getCount();
    }

    public Metric(String s)
    {
        this(s, null);
    }

    public Metric(String s, String s1)
    {
        a = s;
        b = s1;
        h = 0L;
    }

    public void addExclusive(double d1)
    {
        if (g == null)
        {
            g = Double.valueOf(d1);
            return;
        } else
        {
            g = Double.valueOf(d1 + g.doubleValue());
            return;
        }
    }

    public void aggregate(Metric metric)
    {
        if (metric != null)
        {
            increment(metric.getCount());
            if (!metric.isCountOnly())
            {
                double d1;
                double d2;
                double d3;
                if (e == null)
                {
                    d1 = metric.getTotal();
                } else
                {
                    d1 = e.doubleValue() + metric.getTotal();
                }
                e = Double.valueOf(d1);
                if (f == null)
                {
                    d2 = metric.getSumOfSquares();
                } else
                {
                    d2 = f.doubleValue() + metric.getSumOfSquares();
                }
                f = Double.valueOf(d2);
                if (g == null)
                {
                    d3 = metric.getExclusive();
                } else
                {
                    d3 = g.doubleValue() + metric.getExclusive();
                }
                g = Double.valueOf(d3);
                setMin(Double.valueOf(metric.getMin()));
                setMax(Double.valueOf(metric.getMax()));
                return;
            }
        }
    }

    public JsonElement asJson()
    {
        if (isCountOnly())
        {
            return new JsonPrimitive(Long.valueOf(h));
        } else
        {
            return asJsonObject();
        }
    }

    public JsonObject asJsonObject()
    {
        JsonObject jsonobject = new JsonObject();
        jsonobject.add("count", new JsonPrimitive(Long.valueOf(h)));
        if (e != null)
        {
            jsonobject.add("total", new JsonPrimitive(e));
        }
        if (c != null)
        {
            jsonobject.add("min", new JsonPrimitive(c));
        }
        if (d != null)
        {
            jsonobject.add("max", new JsonPrimitive(d));
        }
        if (f != null)
        {
            jsonobject.add("sum_of_squares", new JsonPrimitive(f));
        }
        if (g != null)
        {
            jsonobject.add("exclusive", new JsonPrimitive(g));
        }
        return jsonobject;
    }

    public void clear()
    {
        c = null;
        d = null;
        e = null;
        f = null;
        g = null;
        h = 0L;
    }

    public long getCount()
    {
        return h;
    }

    public double getExclusive()
    {
        if (g == null)
        {
            return 0.0D;
        } else
        {
            return g.doubleValue();
        }
    }

    public double getMax()
    {
        if (d == null)
        {
            return 0.0D;
        } else
        {
            return d.doubleValue();
        }
    }

    public double getMin()
    {
        if (c == null)
        {
            return 0.0D;
        } else
        {
            return c.doubleValue();
        }
    }

    public String getName()
    {
        return a;
    }

    public String getScope()
    {
        return b;
    }

    public String getStringScope()
    {
        if (b == null)
        {
            return "";
        } else
        {
            return b;
        }
    }

    public double getSumOfSquares()
    {
        if (f == null)
        {
            return 0.0D;
        } else
        {
            return f.doubleValue();
        }
    }

    public double getTotal()
    {
        if (e == null)
        {
            return 0.0D;
        } else
        {
            return e.doubleValue();
        }
    }

    public void increment()
    {
        increment(1L);
    }

    public void increment(long l)
    {
        h = l + h;
    }

    public boolean isCountOnly()
    {
        return e == null;
    }

    public boolean isScoped()
    {
        return b != null;
    }

    public boolean isUnscoped()
    {
        return b == null;
    }

    public void sample(double d1)
    {
        h = 1L + h;
        if (e == null)
        {
            e = Double.valueOf(d1);
            f = Double.valueOf(d1 * d1);
        } else
        {
            e = Double.valueOf(d1 + e.doubleValue());
            f = Double.valueOf(f.doubleValue() + d1 * d1);
        }
        setMin(Double.valueOf(d1));
        setMax(Double.valueOf(d1));
    }

    public void setCount(long l)
    {
        h = l;
    }

    public void setExclusive(Double double1)
    {
        g = double1;
    }

    public void setMax(Double double1)
    {
        if (double1 != null)
        {
            if (d == null)
            {
                d = double1;
                return;
            }
            if (double1.doubleValue() > d.doubleValue())
            {
                d = double1;
                return;
            }
        }
    }

    public void setMaxFieldValue(Double double1)
    {
        d = double1;
    }

    public void setMin(Double double1)
    {
        if (double1 != null)
        {
            if (c == null)
            {
                c = double1;
                return;
            }
            if (double1.doubleValue() < c.doubleValue())
            {
                c = double1;
                return;
            }
        }
    }

    public void setMinFieldValue(Double double1)
    {
        c = double1;
    }

    public void setName(String s)
    {
        a = s;
    }

    public void setScope(String s)
    {
        b = s;
    }

    public void setSumOfSquares(Double double1)
    {
        f = double1;
    }

    public void setTotal(Double double1)
    {
        e = double1;
    }

    public String toString()
    {
        return (new StringBuilder("Metric{count=")).append(h).append(", total=").append(e).append(", max=").append(d).append(", min=").append(c).append(", scope='").append(b).append('\'').append(", name='").append(a).append('\'').append(", exclusive='").append(g).append('\'').append(", sumofsquares='").append(f).append('\'').append('}').toString();
    }
}
