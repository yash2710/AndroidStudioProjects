// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Notification;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            RemoteInputCompatApi20

class NotificationCompatApi20
{

    NotificationCompatApi20()
    {
    }

    public static NotificationCompatBase.Action getAction(Notification notification, int i, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
    {
        return getActionCompatFromAction(notification.actions[i], factory, factory1);
    }

    private static NotificationCompatBase.Action getActionCompatFromAction(android.app.Notification.Action action, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
    {
        RemoteInputCompatBase.RemoteInput aremoteinput[] = RemoteInputCompatApi20.toCompat(action.getRemoteInputs(), factory1);
        return factory.build(action.icon, action.title, action.actionIntent, action.getExtras(), aremoteinput);
    }

    private static android.app.Notification.Action getActionFromActionCompat(NotificationCompatBase.Action action)
    {
        android.app.Notification.Action.Builder builder = (new android.app.Notification.Action.Builder(action.getIcon(), action.getTitle(), action.getActionIntent())).addExtras(action.getExtras());
        RemoteInputCompatBase.RemoteInput aremoteinput[] = action.getRemoteInputs();
        if (aremoteinput != null)
        {
            android.app.RemoteInput aremoteinput1[] = RemoteInputCompatApi20.fromCompat(aremoteinput);
            int i = aremoteinput1.length;
            for (int j = 0; j < i; j++)
            {
                builder.addRemoteInput(aremoteinput1[j]);
            }

        }
        return builder.build();
    }

    public static NotificationCompatBase.Action[] getActionsFromParcelableArrayList(ArrayList arraylist, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
    {
        if (arraylist == null)
        {
            return null;
        }
        NotificationCompatBase.Action aaction[] = factory.newArray(arraylist.size());
        for (int i = 0; i < aaction.length; i++)
        {
            aaction[i] = getActionCompatFromAction((android.app.Notification.Action)arraylist.get(i), factory, factory1);
        }

        return aaction;
    }

    public static String getGroup(Notification notification)
    {
        return notification.getGroup();
    }

    public static boolean getLocalOnly(Notification notification)
    {
        return (0x100 & notification.flags) != 0;
    }

    public static ArrayList getParcelableArrayListForActions(NotificationCompatBase.Action aaction[])
    {
        ArrayList arraylist;
        if (aaction == null)
        {
            arraylist = null;
        } else
        {
            arraylist = new ArrayList(aaction.length);
            int i = aaction.length;
            int j = 0;
            while (j < i) 
            {
                arraylist.add(getActionFromActionCompat(aaction[j]));
                j++;
            }
        }
        return arraylist;
    }

    public static String getSortKey(Notification notification)
    {
        return notification.getSortKey();
    }

    public static boolean isGroupSummary(Notification notification)
    {
        return (0x200 & notification.flags) != 0;
    }
}
