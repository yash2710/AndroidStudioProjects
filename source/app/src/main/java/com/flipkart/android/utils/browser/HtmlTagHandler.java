// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.browser;

import android.graphics.Color;
import android.text.Editable;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import com.flipkart.android.utils.StringUtils;
import java.lang.reflect.Field;
import org.xml.sax.XMLReader;

public class HtmlTagHandler
    implements android.text.Html.TagHandler
{

    private static int a = 0;

    public HtmlTagHandler()
    {
    }

    private static String a(String s, XMLReader xmlreader)
    {
        String as[];
        int i;
        int j;
        String s1;
        try
        {
            Field field = xmlreader.getClass().getDeclaredField("theNewElement");
            field.setAccessible(true);
            Object obj = field.get(xmlreader);
            Field field1 = obj.getClass().getDeclaredField("theAtts");
            field1.setAccessible(true);
            Object obj1 = field1.get(obj);
            Field field2 = obj1.getClass().getDeclaredField("data");
            field2.setAccessible(true);
            as = (String[])field2.get(obj1);
            Field field3 = obj1.getClass().getDeclaredField("length");
            field3.setAccessible(true);
            i = ((Integer)field3.get(obj1)).intValue();
        }
        catch (Exception exception)
        {
            break; /* Loop/switch isn't completed */
        }
        j = 0;
_L2:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        if (!s.equals(as[1 + j * 5]))
        {
            break MISSING_BLOCK_LABEL_152;
        }
        s1 = as[4 + j * 5];
        return s1;
        j++;
        continue; /* Loop/switch isn't completed */
        if (true) goto _L2; else goto _L1
_L1:
        return "";
    }

    public void handleTag(boolean flag, String s, Editable editable, XMLReader xmlreader)
    {
        if (s.contains("background") && flag)
        {
            a("", xmlreader);
            String s1 = a("color", xmlreader);
            if (!StringUtils.isNullOrEmpty(s1))
            {
                a = Color.parseColor(s1);
            }
            BackgroundColorSpan backgroundcolorspan1 = new BackgroundColorSpan(a);
            int k = editable.length();
            editable.setSpan(backgroundcolorspan1, k, k, 17);
        } else
        if (s.contains("background") && !flag)
        {
            BackgroundColorSpan backgroundcolorspan = new BackgroundColorSpan(a);
            int i = editable.length();
            Object aobj[] = editable.getSpans(0, editable.length(), android/text/style/BackgroundColorSpan);
            Object obj;
            int j;
            if (aobj.length == 0)
            {
                obj = null;
            } else
            {
                obj = aobj[-1 + aobj.length];
            }
            j = editable.getSpanStart(obj);
            editable.removeSpan(obj);
            if (j != i && i > j)
            {
                editable.setSpan(backgroundcolorspan, j, i, 33);
                return;
            }
        }
    }

}
