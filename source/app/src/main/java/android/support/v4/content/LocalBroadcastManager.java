// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

public class LocalBroadcastManager
{

    private static final boolean DEBUG = false;
    static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final String TAG = "LocalBroadcastManager";
    private static LocalBroadcastManager mInstance;
    private static final Object mLock = new Object();
    private final HashMap mActions = new HashMap();
    private final Context mAppContext;
    private final Handler mHandler;
    private final ArrayList mPendingBroadcasts = new ArrayList();
    private final HashMap mReceivers = new HashMap();

    private LocalBroadcastManager(Context context)
    {
        mAppContext = context;
        mHandler = new _cls1(context.getMainLooper());
    }

    private void executePendingBroadcasts()
    {
_L4:
        HashMap hashmap = mReceivers;
        hashmap;
        JVM INSTR monitorenter ;
        int i = mPendingBroadcasts.size();
        if (i > 0)
        {
            break MISSING_BLOCK_LABEL_22;
        }
        hashmap;
        JVM INSTR monitorexit ;
        return;
        BroadcastRecord abroadcastrecord[];
        abroadcastrecord = new BroadcastRecord[i];
        mPendingBroadcasts.toArray(abroadcastrecord);
        mPendingBroadcasts.clear();
        hashmap;
        JVM INSTR monitorexit ;
        int j = 0;
_L2:
        if (j < abroadcastrecord.length)
        {
            BroadcastRecord broadcastrecord = abroadcastrecord[j];
            for (int k = 0; k < broadcastrecord.receivers.size(); k++)
            {
                ((ReceiverRecord)broadcastrecord.receivers.get(k)).receiver.onReceive(mAppContext, broadcastrecord.intent);
            }

            break MISSING_BLOCK_LABEL_120;
        }
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static LocalBroadcastManager getInstance(Context context)
    {
        LocalBroadcastManager localbroadcastmanager;
        synchronized (mLock)
        {
            if (mInstance == null)
            {
                mInstance = new LocalBroadcastManager(context.getApplicationContext());
            }
            localbroadcastmanager = mInstance;
        }
        return localbroadcastmanager;
    }

    public void registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter)
    {
        HashMap hashmap = mReceivers;
        hashmap;
        JVM INSTR monitorenter ;
        ReceiverRecord receiverrecord;
        ArrayList arraylist;
        receiverrecord = new ReceiverRecord(intentfilter, broadcastreceiver);
        arraylist = (ArrayList)mReceivers.get(broadcastreceiver);
        if (arraylist != null)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        arraylist = new ArrayList(1);
        mReceivers.put(broadcastreceiver, arraylist);
        arraylist.add(intentfilter);
        int i = 0;
_L2:
        String s;
        ArrayList arraylist1;
        if (i >= intentfilter.countActions())
        {
            break; /* Loop/switch isn't completed */
        }
        s = intentfilter.getAction(i);
        arraylist1 = (ArrayList)mActions.get(s);
        if (arraylist1 != null)
        {
            break MISSING_BLOCK_LABEL_125;
        }
        arraylist1 = new ArrayList(1);
        mActions.put(s, arraylist1);
        arraylist1.add(receiverrecord);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        hashmap;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean sendBroadcast(Intent intent)
    {
        HashMap hashmap = mReceivers;
        hashmap;
        JVM INSTR monitorenter ;
        String s;
        String s1;
        android.net.Uri uri;
        String s2;
        java.util.Set set;
        s = intent.getAction();
        s1 = intent.resolveTypeIfNeeded(mAppContext.getContentResolver());
        uri = intent.getData();
        s2 = intent.getScheme();
        set = intent.getCategories();
        ArrayList arraylist1;
        int j;
        ArrayList arraylist2;
        String s3;
        Exception exception;
        boolean flag;
        ArrayList arraylist;
        int i;
        ReceiverRecord receiverrecord;
        int k;
        if ((8 & intent.getFlags()) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_104;
        }
        Log.v("LocalBroadcastManager", (new StringBuilder("Resolving type ")).append(s1).append(" scheme ").append(s2).append(" of intent ").append(intent).toString());
        arraylist = (ArrayList)mActions.get(intent.getAction());
        if (arraylist == null) goto _L2; else goto _L1
_L1:
        if (!flag) goto _L4; else goto _L3
_L3:
        Log.v("LocalBroadcastManager", (new StringBuilder("Action list: ")).append(arraylist).toString());
          goto _L4
_L24:
        if (i >= arraylist.size()) goto _L6; else goto _L5
_L5:
        receiverrecord = (ReceiverRecord)arraylist.get(i);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_209;
        }
        Log.v("LocalBroadcastManager", (new StringBuilder("Matching against filter ")).append(receiverrecord.filter).toString());
        if (!receiverrecord.broadcasting) goto _L8; else goto _L7
_L7:
        if (!flag) goto _L10; else goto _L9
_L9:
        Log.v("LocalBroadcastManager", "  Filter's target already added");
        arraylist2 = arraylist1;
          goto _L11
_L8:
        k = receiverrecord.filter.match(s, s1, s2, uri, set, "LocalBroadcastManager");
        if (k < 0) goto _L13; else goto _L12
_L12:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_295;
        }
        Log.v("LocalBroadcastManager", (new StringBuilder("  Filter matched!  match=0x")).append(Integer.toHexString(k)).toString());
        if (arraylist1 != null) goto _L15; else goto _L14
_L14:
        arraylist2 = new ArrayList();
_L23:
        arraylist2.add(receiverrecord);
        receiverrecord.broadcasting = true;
          goto _L11
        exception;
        throw exception;
_L13:
        if (!flag) goto _L10; else goto _L16
_L16:
        k;
        JVM INSTR tableswitch -4 -1: default 368
    //                   -4 520
    //                   -3 513
    //                   -2 527
    //                   -1 534;
           goto _L17 _L18 _L19 _L20 _L21
_L17:
        s3 = "unknown reason";
_L25:
        Log.v("LocalBroadcastManager", (new StringBuilder("  Filter did not match: ")).append(s3).toString());
          goto _L10
_L22:
        if (j >= arraylist1.size())
        {
            break MISSING_BLOCK_LABEL_428;
        }
        ((ReceiverRecord)arraylist1.get(j)).broadcasting = false;
        j++;
          goto _L22
        mPendingBroadcasts.add(new BroadcastRecord(intent, arraylist1));
        if (!mHandler.hasMessages(1))
        {
            mHandler.sendEmptyMessage(1);
        }
        hashmap;
        JVM INSTR monitorexit ;
        return true;
_L2:
        hashmap;
        JVM INSTR monitorexit ;
        return false;
_L15:
        arraylist2 = arraylist1;
          goto _L23
_L4:
        arraylist1 = null;
        i = 0;
          goto _L24
_L11:
        i++;
        arraylist1 = arraylist2;
          goto _L24
_L10:
        arraylist2 = arraylist1;
          goto _L11
_L19:
        s3 = "action";
          goto _L25
_L18:
        s3 = "category";
          goto _L25
_L20:
        s3 = "data";
          goto _L25
_L21:
        s3 = "type";
          goto _L25
_L6:
        if (arraylist1 == null) goto _L2; else goto _L26
_L26:
        j = 0;
          goto _L22
    }

    public void sendBroadcastSync(Intent intent)
    {
        if (sendBroadcast(intent))
        {
            executePendingBroadcasts();
        }
    }

    public void unregisterReceiver(BroadcastReceiver broadcastreceiver)
    {
        HashMap hashmap = mReceivers;
        hashmap;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)mReceivers.remove(broadcastreceiver);
        if (arraylist != null) goto _L2; else goto _L1
_L1:
        hashmap;
        JVM INSTR monitorexit ;
        return;
_L16:
        int i;
        if (i >= arraylist.size()) goto _L4; else goto _L3
_L3:
        IntentFilter intentfilter = (IntentFilter)arraylist.get(i);
        int j = 0;
_L14:
        String s;
        ArrayList arraylist1;
        if (j >= intentfilter.countActions())
        {
            break; /* Loop/switch isn't completed */
        }
        s = intentfilter.getAction(j);
        arraylist1 = (ArrayList)mActions.get(s);
        if (arraylist1 == null) goto _L6; else goto _L5
_L5:
        int k = 0;
_L12:
        if (k >= arraylist1.size()) goto _L8; else goto _L7
_L7:
        if (((ReceiverRecord)arraylist1.get(k)).receiver != broadcastreceiver) goto _L10; else goto _L9
_L9:
        arraylist1.remove(k);
        int l = k - 1;
          goto _L11
_L8:
        if (arraylist1.size() <= 0)
        {
            mActions.remove(s);
        }
        break; /* Loop/switch isn't completed */
_L4:
        hashmap;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
_L10:
        l = k;
          goto _L11
_L2:
        i = 0;
        continue; /* Loop/switch isn't completed */
_L11:
        k = l + 1;
        if (true) goto _L12; else goto _L6
_L6:
        j++;
        if (true) goto _L14; else goto _L13
_L13:
        i++;
        if (true) goto _L16; else goto _L15
_L15:
    }



    private class _cls1 extends Handler
    {

        final LocalBroadcastManager this$0;

        public void handleMessage(Message message)
        {
            switch (message.what)
            {
            default:
                super.handleMessage(message);
                return;

            case 1: // '\001'
                executePendingBroadcasts();
                break;
            }
        }

        _cls1(Looper looper)
        {
            this$0 = LocalBroadcastManager.this;
            super(looper);
        }
    }


    private class BroadcastRecord
    {

        final Intent intent;
        final ArrayList receivers;

        BroadcastRecord(Intent intent1, ArrayList arraylist)
        {
            intent = intent1;
            receivers = arraylist;
        }
    }


    private class ReceiverRecord
    {

        boolean broadcasting;
        final IntentFilter filter;
        final BroadcastReceiver receiver;

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            stringbuilder.append("Receiver{");
            stringbuilder.append(receiver);
            stringbuilder.append(" filter=");
            stringbuilder.append(filter);
            stringbuilder.append("}");
            return stringbuilder.toString();
        }

        ReceiverRecord(IntentFilter intentfilter, BroadcastReceiver broadcastreceiver)
        {
            filter = intentfilter;
            receiver = broadcastreceiver;
        }
    }

}
