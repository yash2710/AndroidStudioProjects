// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.android.volley:
//            DefaultRetryPolicy, RequestQueue, d, RetryPolicy, 
//            VolleyError, NetworkResponse, Response

public abstract class Request
    implements Comparable
{

    private final int a;
    private final String b;
    private final int c;
    private final Response.ErrorListener d;
    private Integer e;
    private RequestQueue f;
    private boolean g;
    private boolean h;
    private boolean i;
    private RetryPolicy j;
    private Cache.Entry k;
    private Object l;
    protected final VolleyLog.MarkerLog mEventLog;

    public Request(int i1, String s, Response.ErrorListener errorlistener)
    {
        mEventLog = new VolleyLog.MarkerLog();
        g = true;
        h = false;
        i = false;
        k = null;
        a = i1;
        b = s;
        d = errorlistener;
        setRetryPolicy(new DefaultRetryPolicy());
        boolean flag = TextUtils.isEmpty(s);
        int j1 = 0;
        if (!flag)
        {
            Uri uri = Uri.parse(s);
            j1 = 0;
            if (uri != null)
            {
                String s1 = uri.getHost();
                j1 = 0;
                if (s1 != null)
                {
                    j1 = s1.hashCode();
                }
            }
        }
        c = j1;
    }

    public Request(String s, Response.ErrorListener errorlistener)
    {
        this(-1, s, errorlistener);
    }

    private static byte[] a(Map map, String s)
    {
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder();
        try
        {
            for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); stringbuilder.append('&'))
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                stringbuilder.append(URLEncoder.encode((String)entry.getKey(), s));
                stringbuilder.append('=');
                stringbuilder.append(URLEncoder.encode((String)entry.getValue(), s));
            }

        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new RuntimeException((new StringBuilder("Encoding not supported: ")).append(s).toString(), unsupportedencodingexception);
        }
        byte abyte0[] = stringbuilder.toString().getBytes(s);
        return abyte0;
    }

    final void a(String s)
    {
        if (f != null)
        {
            f.a(this);
        }
        long l1 = Thread.currentThread().getId();
        if (Looper.myLooper() != Looper.getMainLooper())
        {
            (new Handler(Looper.getMainLooper())).post(new d(this, s, l1));
            return;
        } else
        {
            mEventLog.add(s, l1);
            mEventLog.finish(toString());
            return;
        }
    }

    public void addMarker(String s)
    {
        mEventLog.add(s, Thread.currentThread().getId());
    }

    public void cancel()
    {
        h = true;
    }

    public int compareTo(Request request)
    {
        Priority priority = getPriority();
        Priority priority1 = request.getPriority();
        if (priority == priority1)
        {
            return e.intValue() - request.e.intValue();
        } else
        {
            return priority1.ordinal() - priority.ordinal();
        }
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((Request)obj);
    }

    public void deliverError(VolleyError volleyerror)
    {
        if (d != null)
        {
            d.onErrorResponse(volleyerror);
        }
    }

    protected abstract void deliverResponse(Object obj);

    public byte[] getBody()
    {
        Map map = getParams();
        if (map != null && map.size() > 0)
        {
            return a(map, getParamsEncoding());
        } else
        {
            return null;
        }
    }

    public String getBodyContentType()
    {
        return (new StringBuilder("application/x-www-form-urlencoded; charset=")).append(getParamsEncoding()).toString();
    }

    public Cache.Entry getCacheEntry()
    {
        return k;
    }

    public String getCacheKey()
    {
        return getUrl();
    }

    public Map getHeaders()
    {
        return Collections.emptyMap();
    }

    public int getMethod()
    {
        return a;
    }

    protected Map getParams()
    {
        return null;
    }

    protected String getParamsEncoding()
    {
        return "UTF-8";
    }

    public byte[] getPostBody()
    {
        Map map = getPostParams();
        if (map != null && map.size() > 0)
        {
            return a(map, getPostParamsEncoding());
        } else
        {
            return null;
        }
    }

    public String getPostBodyContentType()
    {
        return getBodyContentType();
    }

    protected Map getPostParams()
    {
        return getParams();
    }

    protected String getPostParamsEncoding()
    {
        return getParamsEncoding();
    }

    public Priority getPriority()
    {
        return Priority.NORMAL;
    }

    public RetryPolicy getRetryPolicy()
    {
        return j;
    }

    public final int getSequence()
    {
        if (e == null)
        {
            throw new IllegalStateException("getSequence called before setSequence");
        } else
        {
            return e.intValue();
        }
    }

    public Object getTag()
    {
        return l;
    }

    public final int getTimeoutMs()
    {
        return j.getCurrentTimeout();
    }

    public int getTrafficStatsTag()
    {
        return c;
    }

    public String getUrl()
    {
        return b;
    }

    public boolean hasHadResponseDelivered()
    {
        return i;
    }

    public boolean isCanceled()
    {
        return h;
    }

    public void markDelivered()
    {
        i = true;
    }

    protected VolleyError parseNetworkError(VolleyError volleyerror)
    {
        return volleyerror;
    }

    protected abstract Response parseNetworkResponse(NetworkResponse networkresponse);

    public void setCacheEntry(Cache.Entry entry)
    {
        k = entry;
    }

    public void setRequestQueue(RequestQueue requestqueue)
    {
        f = requestqueue;
    }

    public void setRetryPolicy(RetryPolicy retrypolicy)
    {
        j = retrypolicy;
    }

    public final void setSequence(int i1)
    {
        e = Integer.valueOf(i1);
    }

    public final void setShouldCache(boolean flag)
    {
        g = flag;
    }

    public void setTag(Object obj)
    {
        l = obj;
    }

    public final boolean shouldCache()
    {
        return g;
    }

    public String toString()
    {
        String s = (new StringBuilder("0x")).append(Integer.toHexString(getTrafficStatsTag())).toString();
        StringBuilder stringbuilder = new StringBuilder();
        String s1;
        if (h)
        {
            s1 = "[X] ";
        } else
        {
            s1 = "[ ] ";
        }
        return stringbuilder.append(s1).append(getUrl()).append(" ").append(s).append(" ").append(getPriority()).append(" ").append(e).toString();
    }

    private class Priority extends Enum
    {

        public static final Priority HIGH;
        public static final Priority IMMEDIATE;
        public static final Priority LOW;
        public static final Priority NORMAL;
        private static final Priority a[];

        public static Priority valueOf(String s)
        {
            return (Priority)Enum.valueOf(com/android/volley/Request$Priority, s);
        }

        public static Priority[] values()
        {
            return (Priority[])a.clone();
        }

        static 
        {
            LOW = new Priority("LOW", 0);
            NORMAL = new Priority("NORMAL", 1);
            HIGH = new Priority("HIGH", 2);
            IMMEDIATE = new Priority("IMMEDIATE", 3);
            Priority apriority[] = new Priority[4];
            apriority[0] = LOW;
            apriority[1] = NORMAL;
            apriority[2] = HIGH;
            apriority[3] = IMMEDIATE;
            a = apriority;
        }

        private Priority(String s, int i1)
        {
            super(s, i1);
        }
    }

}
