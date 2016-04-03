// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.inAppNotification;

import com.flipkart.android.response.customwidgetitemvalue.Action;
import java.util.Map;

public class InAppNotification
{

    Action action;
    String id;
    Map images;
    boolean read;
    boolean sharable;
    String text;
    Long timestamp;
    String title;
    String type;

    public InAppNotification()
    {
    }

    public Action getAction()
    {
        return action;
    }

    public String getId()
    {
        return id;
    }

    public Map getImages()
    {
        return images;
    }

    public String getText()
    {
        return text;
    }

    public Long getTimestamp()
    {
        return timestamp;
    }

    public String getTitle()
    {
        return title;
    }

    public String getType()
    {
        return type;
    }

    public boolean isRead()
    {
        return read;
    }

    public boolean isSharable()
    {
        return sharable;
    }

    public void setAction(Action action1)
    {
        action = action1;
    }

    public void setId(String s)
    {
        id = s;
    }

    public void setImages(Map map)
    {
        images = map;
    }

    public void setRead(boolean flag)
    {
        read = flag;
    }

    public void setSharable(boolean flag)
    {
        sharable = flag;
    }

    public void setText(String s)
    {
        text = s;
    }

    public void setTimestamp(Long long1)
    {
        timestamp = long1;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void setType(String s)
    {
        type = s;
    }
}
