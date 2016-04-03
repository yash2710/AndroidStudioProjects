// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.flipkart.logging.FkLogger;
import com.google.zxing.client.result.CalendarParsedResult;
import com.google.zxing.client.result.ParsedResult;
import java.text.DateFormat;
import java.util.Date;

// Referenced classes of package com.google.zxing.client.android.result:
//            ResultHandler

public final class CalendarResultHandler extends ResultHandler
{

    private static final String a = com/google/zxing/client/android/result/CalendarResultHandler.getSimpleName();
    private static final int b[];

    public CalendarResultHandler(Activity activity, ParsedResult parsedresult)
    {
        super(activity, parsedresult);
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

    public final int getButtonCount()
    {
        int[] _tmp = b;
        return 1;
    }

    public final int getButtonText(int i)
    {
        return b[i];
    }

    public final CharSequence getDisplayContents()
    {
        CalendarParsedResult calendarparsedresult = (CalendarParsedResult)getResult();
        StringBuilder stringbuilder = new StringBuilder(100);
        ParsedResult.maybeAppend(calendarparsedresult.getSummary(), stringbuilder);
        Date date = calendarparsedresult.getStart();
        ParsedResult.maybeAppend(a(calendarparsedresult.isStartAllDay(), date), stringbuilder);
        Date date1 = calendarparsedresult.getEnd();
        if (date1 != null)
        {
            Date date2;
            if (calendarparsedresult.isEndAllDay() && !date.equals(date1))
            {
                date2 = new Date(date1.getTime() - 0x5265c00L);
            } else
            {
                date2 = date1;
            }
            ParsedResult.maybeAppend(a(calendarparsedresult.isEndAllDay(), date2), stringbuilder);
        }
        ParsedResult.maybeAppend(calendarparsedresult.getLocation(), stringbuilder);
        ParsedResult.maybeAppend(calendarparsedresult.getOrganizer(), stringbuilder);
        ParsedResult.maybeAppend(calendarparsedresult.getAttendees(), stringbuilder);
        ParsedResult.maybeAppend(calendarparsedresult.getDescription(), stringbuilder);
        return stringbuilder.toString();
    }

    public final int getDisplayTitle()
    {
        return com.google.zxing.client.android.R.string.result_calendar;
    }

    public final void handleButtonPress(int i)
    {
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_197;
        }
        CalendarParsedResult calendarparsedresult = (CalendarParsedResult)getResult();
        String s = calendarparsedresult.getDescription();
        String s1 = calendarparsedresult.getOrganizer();
        String s2;
        Date date;
        boolean flag;
        Date date1;
        String s3;
        String as[];
        Intent intent;
        long l;
        ActivityNotFoundException activitynotfoundexception;
        if (s1 != null)
        {
            if (s != null)
            {
                s1 = (new StringBuilder()).append(s).append('\n').append(s1).toString();
            }
        } else
        {
            s1 = s;
        }
        s2 = calendarparsedresult.getSummary();
        date = calendarparsedresult.getStart();
        flag = calendarparsedresult.isStartAllDay();
        date1 = calendarparsedresult.getEnd();
        s3 = calendarparsedresult.getLocation();
        as = calendarparsedresult.getAttendees();
        intent = new Intent("android.intent.action.INSERT");
        intent.setType("vnd.android.cursor.item/event");
        l = date.getTime();
        intent.putExtra("beginTime", l);
        if (flag)
        {
            intent.putExtra("allDay", true);
        }
        if (date1 == null)
        {
            if (flag)
            {
                l += 0x5265c00L;
            }
        } else
        {
            l = date1.getTime();
        }
        intent.putExtra("endTime", l);
        intent.putExtra("title", s2);
        intent.putExtra("eventLocation", s3);
        intent.putExtra("description", s1);
        if (as != null)
        {
            intent.putExtra("android.intent.extra.EMAIL", as);
        }
        a(intent);
        return;
        activitynotfoundexception;
        FkLogger.warn(a, "No calendar app available that responds to android.intent.action.INSERT");
        intent.setAction("android.intent.action.EDIT");
        b(intent);
        return;
    }

    static 
    {
        int ai[] = new int[1];
        ai[0] = com.google.zxing.client.android.R.string.button_add_calendar;
        b = ai;
    }
}
