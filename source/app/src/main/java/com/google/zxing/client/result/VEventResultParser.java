// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.List;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, VCardResultParser, CalendarParsedResult, ParsedResult

public final class VEventResultParser extends ResultParser
{

    public VEventResultParser()
    {
    }

    private static String a(CharSequence charsequence, String s, boolean flag)
    {
        List list = VCardResultParser.b(charsequence, s, true, false);
        if (list == null || list.isEmpty())
        {
            return null;
        } else
        {
            return (String)list.get(0);
        }
    }

    private static String a(String s)
    {
        if (s != null && (s.startsWith("mailto:") || s.startsWith("MAILTO:")))
        {
            s = s.substring(7);
        }
        return s;
    }

    public final CalendarParsedResult parse(Result result)
    {
        String s = getMassagedText(result);
        if (s.indexOf("BEGIN:VEVENT") < 0)
        {
            return null;
        }
        String s1 = a("SUMMARY", s, true);
        String s2 = a("DTSTART", s, true);
        if (s2 == null)
        {
            return null;
        }
        String s3 = a("DTEND", s, true);
        String s4 = a("DURATION", s, true);
        String s5 = a("LOCATION", s, true);
        String s6 = a(a("ORGANIZER", s, true));
        List list = VCardResultParser.a("ATTENDEE", s, true, false);
        String as[];
        if (list == null || list.isEmpty())
        {
            as = null;
        } else
        {
            int k = list.size();
            as = new String[k];
            int l = 0;
            while (l < k) 
            {
                as[l] = (String)((List)list.get(l)).get(0);
                l++;
            }
        }
        if (as != null)
        {
            for (int j = 0; j < as.length; j++)
            {
                as[j] = a(as[j]);
            }

        }
        String s7 = a("DESCRIPTION", s, true);
        String s8 = a("GEO", s, true);
        double d;
        double d2;
        CalendarParsedResult calendarparsedresult;
        if (s8 == null)
        {
            d = (0.0D / 0.0D);
            d2 = (0.0D / 0.0D);
        } else
        {
            int i = s8.indexOf(';');
            double d1;
            try
            {
                d = Double.parseDouble(s8.substring(0, i));
                d1 = Double.parseDouble(s8.substring(i + 1));
            }
            catch (NumberFormatException numberformatexception)
            {
                return null;
            }
            d2 = d1;
        }
        try
        {
            calendarparsedresult = new CalendarParsedResult(s1, s2, s3, s4, s5, s6, as, s7, d, d2);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return null;
        }
        return calendarparsedresult;
    }

    public final volatile ParsedResult parse(Result result)
    {
        return parse(result);
    }
}
