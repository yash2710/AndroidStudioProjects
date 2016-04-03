// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.result;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class CalendarParsedResult extends ParsedResult
{

    private static final Pattern a = Pattern.compile("P(?:(\\d+)W)?(?:(\\d+)D)?(?:T(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+)S)?)?");
    private static final long b[] = {
        0x240c8400L, 0x5265c00L, 0x36ee80L, 60000L, 1000L
    };
    private static final Pattern c = Pattern.compile("[0-9]{8}(T[0-9]{6}Z?)?");
    private static final DateFormat d;
    private static final DateFormat e;
    private final String f;
    private final Date g;
    private final boolean h;
    private final Date i;
    private final boolean j;
    private final String k;
    private final String l;
    private final String m[];
    private final String n;
    private final double o;
    private final double p;

    public CalendarParsedResult(String s, String s1, String s2, String s3, String s4, String s5, String as[], 
            String s6, double d1, double d2)
    {
        super(ParsedResultType.CALENDAR);
        f = s;
        boolean flag;
        boolean flag1;
        try
        {
            g = a(s1);
        }
        catch (ParseException parseexception)
        {
            throw new IllegalArgumentException(parseexception.toString());
        }
        if (s2 == null)
        {
            long l1 = a(s3);
            Date date;
            if (l1 < 0L)
            {
                date = null;
            } else
            {
                date = new Date(l1 + g.getTime());
            }
            i = date;
        } else
        {
            try
            {
                i = a(s2);
            }
            catch (ParseException parseexception1)
            {
                throw new IllegalArgumentException(parseexception1.toString());
            }
        }
        if (s1.length() == 8)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        h = flag;
        if (s2 != null && s2.length() == 8)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        j = flag1;
        k = s4;
        l = s5;
        m = as;
        n = s6;
        o = d1;
        p = d2;
    }

    private static long a(CharSequence charsequence)
    {
        long l1 = -1L;
        if (charsequence != null) goto _L2; else goto _L1
_L1:
        Matcher matcher;
        return l1;
_L2:
        if (!(matcher = a.matcher(charsequence)).matches())
        {
            continue;
        }
        l1 = 0L;
        int i1 = 0;
        do
        {
            b;
            if (i1 >= 5)
            {
                continue;
            }
            String s = matcher.group(i1 + 1);
            if (s != null)
            {
                l1 += b[i1] * (long)Integer.parseInt(s);
            }
            i1++;
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
    }

    private static String a(boolean flag, Date date)
    {
        if (date == null)
        {
            return null;
        }
        DateFormat dateformat;
        if (flag)
        {
            dateformat = DateFormat.getDateInstance(2);
        } else
        {
            dateformat = DateFormat.getDateTimeInstance(2, 2);
        }
        return dateformat.format(date);
    }

    private static Date a(String s)
    {
        if (!c.matcher(s).matches())
        {
            throw new ParseException(s, 0);
        }
        if (s.length() == 8)
        {
            return d.parse(s);
        }
        if (s.length() == 16 && s.charAt(15) == 'Z')
        {
            Date date = e.parse(s.substring(0, 15));
            GregorianCalendar gregoriancalendar = new GregorianCalendar();
            long l1 = date.getTime() + (long)gregoriancalendar.get(15);
            gregoriancalendar.setTime(new Date(l1));
            return new Date(l1 + (long)gregoriancalendar.get(16));
        } else
        {
            return e.parse(s);
        }
    }

    public final String[] getAttendees()
    {
        return m;
    }

    public final String getDescription()
    {
        return n;
    }

    public final String getDisplayResult()
    {
        StringBuilder stringbuilder = new StringBuilder(100);
        maybeAppend(f, stringbuilder);
        maybeAppend(a(h, g), stringbuilder);
        maybeAppend(a(j, i), stringbuilder);
        maybeAppend(k, stringbuilder);
        maybeAppend(l, stringbuilder);
        maybeAppend(m, stringbuilder);
        maybeAppend(n, stringbuilder);
        return stringbuilder.toString();
    }

    public final Date getEnd()
    {
        return i;
    }

    public final double getLatitude()
    {
        return o;
    }

    public final String getLocation()
    {
        return k;
    }

    public final double getLongitude()
    {
        return p;
    }

    public final String getOrganizer()
    {
        return l;
    }

    public final Date getStart()
    {
        return g;
    }

    public final String getSummary()
    {
        return f;
    }

    public final boolean isEndAllDay()
    {
        return j;
    }

    public final boolean isStartAllDay()
    {
        return h;
    }

    static 
    {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        d = simpledateformat;
        simpledateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
        e = new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.ENGLISH);
    }
}
