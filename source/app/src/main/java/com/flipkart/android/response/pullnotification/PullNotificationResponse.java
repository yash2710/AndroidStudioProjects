// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.pullnotification;

import com.flipkart.android.pullnotification.data.NotificationWrapper;
import com.flipkart.android.response.baseresponse.BaseResponse;
import java.util.ArrayList;

public class PullNotificationResponse extends BaseResponse
{

    ArrayList messages;

    public PullNotificationResponse()
    {
    }

    public ArrayList getMessages()
    {
        return messages;
    }

    public void setMessages(ArrayList arraylist)
    {
        messages = arraylist;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[");
        if (messages != null)
        {
            for (int i = 0; i < messages.size(); i++)
            {
                stringbuilder.append("{");
                stringbuilder.append(((NotificationWrapper)messages.get(i)).toString());
                stringbuilder.append("},");
            }

            int j = messages.size();
            if (stringbuilder.length() > 2)
            {
                stringbuilder.delete(j - 2, j);
            }
        }
        stringbuilder.append("]");
        return stringbuilder.toString();
    }
}
