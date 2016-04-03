// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.book;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import com.google.zxing.client.android.LocaleManager;
import java.util.List;

// Referenced classes of package com.google.zxing.client.android.book:
//            f, SearchBookContentsActivity

final class a
    implements android.widget.AdapterView.OnItemClickListener
{

    private final SearchBookContentsActivity a;
    private final List b;

    a(SearchBookContentsActivity searchbookcontentsactivity, List list)
    {
        a = searchbookcontentsactivity;
        b = list;
    }

    public final void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        int j;
        if (i > 0)
        {
            if ((j = i - 1) < b.size())
            {
                String s = ((f)b.get(j)).getPageId();
                String s1 = f.getQuery();
                if (LocaleManager.isBookSearchUrl(a.a()) && s.length() > 0)
                {
                    String s2 = a.a();
                    String s3 = s2.substring(1 + s2.indexOf('='));
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("http://books.google.")).append(LocaleManager.getBookSearchCountryTLD(a)).append("/books?id=").append(s3).append("&pg=").append(s).append("&vq=").append(s1).toString()));
                    intent.addFlags(0x80000);
                    a.startActivity(intent);
                    return;
                }
            }
        }
    }
}
