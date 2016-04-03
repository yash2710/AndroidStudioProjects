// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.book;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Locale;

// Referenced classes of package com.google.zxing.client.android.book:
//            f

public final class SearchBookContentsListItem extends LinearLayout
{

    private TextView a;
    private TextView b;

    SearchBookContentsListItem(Context context)
    {
        super(context);
    }

    public SearchBookContentsListItem(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected final void onFinishInflate()
    {
        super.onFinishInflate();
        a = (TextView)findViewById(com.google.zxing.client.android.R.id.page_number_view);
        b = (TextView)findViewById(com.google.zxing.client.android.R.id.snippet_view);
    }

    public final void set(f f1)
    {
        a.setText(f1.getPageNumber());
        String s = f1.getSnippet();
        if (s.length() > 0)
        {
            if (f1.getValidSnippet())
            {
                String s1 = f.getQuery().toLowerCase(Locale.getDefault());
                String s2 = s.toLowerCase(Locale.getDefault());
                SpannableString spannablestring = new SpannableString(s);
                StyleSpan stylespan = new StyleSpan(1);
                int i = s1.length();
                int j = 0;
                do
                {
                    int k = s2.indexOf(s1, j);
                    if (k >= 0)
                    {
                        spannablestring.setSpan(stylespan, k, k + i, 0);
                        j = k + i;
                    } else
                    {
                        b.setText(spannablestring);
                        return;
                    }
                } while (true);
            } else
            {
                b.setText(s);
                return;
            }
        } else
        {
            b.setText("");
            return;
        }
    }
}
