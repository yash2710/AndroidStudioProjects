// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable.internal;

import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.hb;
import com.google.android.gms.internal.hi;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.wearable.PutDataRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

// Referenced classes of package com.google.android.gms.wearable.internal:
//            b, av, ad, ac, 
//            ao

public class au extends hb
{

    private final ExecutorService agU = Executors.newCachedThreadPool();
    private final HashMap ame = new HashMap();
    private final HashMap amf = new HashMap();
    private final HashMap amg = new HashMap();

    public au(Context context, Looper looper, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        super(context, looper, connectioncallbacks, onconnectionfailedlistener, new String[0]);
    }

    private FutureTask a(ParcelFileDescriptor parcelfiledescriptor, byte abyte0[])
    {
        return new FutureTask(new _cls11(parcelfiledescriptor, abyte0));
    }

    static HashMap b(au au1)
    {
        return au1.ame;
    }

    static HashMap c(au au1)
    {
        return au1.amf;
    }

    static HashMap d(au au1)
    {
        return au1.amg;
    }

    protected void a(int i, IBinder ibinder, Bundle bundle)
    {
        if (Log.isLoggable("WearableClient", 2))
        {
            Log.d("WearableClient", (new StringBuilder("onPostInitHandler: statusCode ")).append(i).toString());
        }
        if (i != 0) goto _L2; else goto _L1
_L1:
        _cls1 _lcls1;
        RemoteException remoteexception;
        ad ad1;
        _lcls1 = new _cls1();
        if (Log.isLoggable("WearableClient", 2))
        {
            Log.d("WearableClient", (new StringBuilder("onPostInitHandler: service ")).append(ibinder).toString());
        }
        ad1 = ad.a.by(ibinder);
        java.util.Map.Entry entry2;
        for (Iterator iterator = ame.entrySet().iterator(); iterator.hasNext(); ad1.a(_lcls1, new b((av)entry2.getValue())))
        {
            entry2 = (java.util.Map.Entry)iterator.next();
            if (Log.isLoggable("WearableClient", 2))
            {
                Log.d("WearableClient", (new StringBuilder("onPostInitHandler: adding Data listener ")).append(entry2.getValue()).toString());
            }
        }

          goto _L3
_L2:
        Log.d("WearableClient", "WearableClientImpl.onPostInitHandler: done");
        super.a(i, ibinder, bundle);
        return;
_L3:
        java.util.Map.Entry entry1;
        for (Iterator iterator1 = amf.entrySet().iterator(); iterator1.hasNext(); ad1.a(_lcls1, new b((av)entry1.getValue())))
        {
            entry1 = (java.util.Map.Entry)iterator1.next();
            if (Log.isLoggable("WearableClient", 2))
            {
                Log.d("WearableClient", (new StringBuilder("onPostInitHandler: adding Message listener ")).append(entry1.getValue()).toString());
            }
        }

        try
        {
            Iterator iterator2 = amg.entrySet().iterator();
            while (iterator2.hasNext()) 
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator2.next();
                if (Log.isLoggable("WearableClient", 2))
                {
                    Log.d("WearableClient", (new StringBuilder("onPostInitHandler: adding Node listener ")).append(entry.getValue()).toString());
                }
                ad1.a(_lcls1, new b((av)entry.getValue()));
            }
        }
        // Misplaced declaration of an exception variable
        catch (RemoteException remoteexception)
        {
            Log.d("WearableClient", "WearableClientImpl.onPostInitHandler: error while adding listener", remoteexception);
        }
        if (true) goto _L2; else goto _L4
_L4:
    }

    public void a(com.google.android.gms.common.api.a.d d1, Uri uri)
    {
        ((ad)ft()).a(new _cls12(d1), uri);
    }

    public void a(com.google.android.gms.common.api.a.d d1, Asset asset)
    {
        ((ad)ft()).a(new _cls4(d1), asset);
    }

    public void a(com.google.android.gms.common.api.a.d d1, com.google.android.gms.wearable.DataApi.DataListener datalistener)
    {
        ac ac1;
        synchronized (ame)
        {
            ac1 = (ac)ame.remove(datalistener);
        }
        if (ac1 == null)
        {
            d1.a(new Status(4002));
            return;
        } else
        {
            a(d1, ac1);
            return;
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, com.google.android.gms.wearable.DataApi.DataListener datalistener, IntentFilter aintentfilter[])
    {
        av av1;
label0:
        {
            av1 = av.a(datalistener, aintentfilter);
            synchronized (ame)
            {
                if (ame.get(datalistener) == null)
                {
                    break label0;
                }
                d1.a(new Status(4001));
            }
            return;
        }
        ame.put(datalistener, av1);
        hashmap;
        JVM INSTR monitorexit ;
        ((ad)ft()).a(new _cls7(datalistener, d1), new b(av1));
        return;
        exception;
        throw exception;
    }

    public void a(com.google.android.gms.common.api.a.d d1, DataItemAsset dataitemasset)
    {
        a(d1, Asset.createFromRef(dataitemasset.getId()));
    }

    public void a(com.google.android.gms.common.api.a.d d1, com.google.android.gms.wearable.MessageApi.MessageListener messagelistener)
    {
        HashMap hashmap = amf;
        hashmap;
        JVM INSTR monitorenter ;
        ac ac1 = (ac)amf.remove(messagelistener);
        if (ac1 != null)
        {
            break MISSING_BLOCK_LABEL_44;
        }
        d1.a(new Status(4002));
_L2:
        hashmap;
        JVM INSTR monitorexit ;
        return;
        a(d1, ac1);
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public void a(com.google.android.gms.common.api.a.d d1, com.google.android.gms.wearable.MessageApi.MessageListener messagelistener, IntentFilter aintentfilter[])
    {
        av av1;
label0:
        {
            av1 = av.a(messagelistener, aintentfilter);
            synchronized (amf)
            {
                if (amf.get(messagelistener) == null)
                {
                    break label0;
                }
                d1.a(new Status(4001));
            }
            return;
        }
        amf.put(messagelistener, av1);
        hashmap;
        JVM INSTR monitorexit ;
        ((ad)ft()).a(new _cls8(messagelistener, d1), new b(av1));
        return;
        exception;
        throw exception;
    }

    public void a(com.google.android.gms.common.api.a.d d1, com.google.android.gms.wearable.NodeApi.NodeListener nodelistener)
    {
        av av1;
label0:
        {
            av1 = av.a(nodelistener);
            synchronized (amg)
            {
                if (amg.get(nodelistener) == null)
                {
                    break label0;
                }
                d1.a(new Status(4001));
            }
            return;
        }
        amg.put(nodelistener, av1);
        hashmap;
        JVM INSTR monitorexit ;
        ((ad)ft()).a(new _cls9(nodelistener, d1), new b(av1));
        return;
        exception;
        throw exception;
    }

    public void a(com.google.android.gms.common.api.a.d d1, PutDataRequest putdatarequest)
    {
        for (Iterator iterator = putdatarequest.getAssets().entrySet().iterator(); iterator.hasNext();)
        {
            Asset asset1 = (Asset)((java.util.Map.Entry)iterator.next()).getValue();
            if (asset1.getData() == null && asset1.getDigest() == null && asset1.getFd() == null && asset1.getUri() == null)
            {
                throw new IllegalArgumentException((new StringBuilder("Put for ")).append(putdatarequest.getUri()).append(" contains invalid asset: ").append(asset1).toString());
            }
        }

        PutDataRequest putdatarequest1 = PutDataRequest.k(putdatarequest.getUri());
        putdatarequest1.setData(putdatarequest.getData());
        ArrayList arraylist = new ArrayList();
        for (Iterator iterator1 = putdatarequest.getAssets().entrySet().iterator(); iterator1.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
            Asset asset = (Asset)entry.getValue();
            if (asset.getData() == null)
            {
                putdatarequest1.putAsset((String)entry.getKey(), (Asset)entry.getValue());
            } else
            {
                ParcelFileDescriptor aparcelfiledescriptor[];
                FutureTask futuretask;
                try
                {
                    aparcelfiledescriptor = ParcelFileDescriptor.createPipe();
                }
                catch (IOException ioexception)
                {
                    throw new IllegalStateException((new StringBuilder("Unable to create ParcelFileDescriptor for asset in request: ")).append(putdatarequest).toString(), ioexception);
                }
                if (Log.isLoggable("WearableClient", 3))
                {
                    Log.d("WearableClient", (new StringBuilder("processAssets: replacing data with FD in asset: ")).append(asset).append(" read:").append(aparcelfiledescriptor[0]).append(" write:").append(aparcelfiledescriptor[1]).toString());
                }
                putdatarequest1.putAsset((String)entry.getKey(), Asset.createFromFd(aparcelfiledescriptor[0]));
                futuretask = a(aparcelfiledescriptor[1], asset.getData());
                arraylist.add(futuretask);
                agU.submit(futuretask);
            }
        }

        try
        {
            ((ad)ft()).a(new a(d1, arraylist), putdatarequest1);
            return;
        }
        catch (NullPointerException nullpointerexception)
        {
            throw new IllegalStateException((new StringBuilder("Unable to putDataItem: ")).append(putdatarequest).toString(), nullpointerexception);
        }
    }

    public void a(com.google.android.gms.common.api.a.d d1, ac ac1)
    {
        ((ad)ft()).a(new _cls10(d1), new ao(ac1));
    }

    public void a(com.google.android.gms.common.api.a.d d1, String s, String s1, byte abyte0[])
    {
        ((ad)ft()).a(new _cls3(d1), s, s1, abyte0);
    }

    protected void a(hi hi1, com.google.android.gms.internal.hb.e e)
    {
        hi1.e(e, 0x4da6e8, getContext().getPackageName());
    }

    public void b(com.google.android.gms.common.api.a.d d1, Uri uri)
    {
        ((ad)ft()).b(new _cls14(d1), uri);
    }

    public void b(com.google.android.gms.common.api.a.d d1, com.google.android.gms.wearable.NodeApi.NodeListener nodelistener)
    {
        HashMap hashmap = amg;
        hashmap;
        JVM INSTR monitorenter ;
        ac ac1 = (ac)amg.remove(nodelistener);
        if (ac1 != null)
        {
            break MISSING_BLOCK_LABEL_44;
        }
        d1.a(new Status(4002));
_L2:
        hashmap;
        JVM INSTR monitorexit ;
        return;
        a(d1, ac1);
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    protected String bu()
    {
        return "com.google.android.gms.wearable.BIND";
    }

    protected String bv()
    {
        return "com.google.android.gms.wearable.internal.IWearableService";
    }

    protected ad bz(IBinder ibinder)
    {
        return ad.a.by(ibinder);
    }

    public void c(com.google.android.gms.common.api.a.d d1, Uri uri)
    {
        ((ad)ft()).c(new _cls2(d1), uri);
    }

    public void disconnect()
    {
        super.disconnect();
        ame.clear();
        amf.clear();
        amg.clear();
    }

    public void o(com.google.android.gms.common.api.a.d d1)
    {
        ((ad)ft()).d(new _cls13(d1));
    }

    public void p(com.google.android.gms.common.api.a.d d1)
    {
        ((ad)ft()).e(new _cls5(d1));
    }

    public void q(com.google.android.gms.common.api.a.d d1)
    {
        ((ad)ft()).f(new _cls6(d1));
    }

    protected IInterface x(IBinder ibinder)
    {
        return bz(ibinder);
    }

    private class _cls11
        implements Callable
    {

        final au amh;
        final ParcelFileDescriptor amj;
        final byte yL[];

        public Object call()
        {
            return nt();
        }

        public Boolean nt()
        {
            android.os.ParcelFileDescriptor.AutoCloseOutputStream autocloseoutputstream;
            if (Log.isLoggable("WearableClient", 3))
            {
                Log.d("WearableClient", (new StringBuilder("processAssets: writing data to FD : ")).append(amj).toString());
            }
            autocloseoutputstream = new android.os.ParcelFileDescriptor.AutoCloseOutputStream(amj);
            Boolean boolean1;
            autocloseoutputstream.write(yL);
            autocloseoutputstream.flush();
            if (Log.isLoggable("WearableClient", 3))
            {
                Log.d("WearableClient", (new StringBuilder("processAssets: wrote data: ")).append(amj).toString());
            }
            boolean1 = Boolean.valueOf(true);
            Exception exception;
            IOException ioexception;
            IOException ioexception1;
            IOException ioexception2;
            try
            {
                if (Log.isLoggable("WearableClient", 3))
                {
                    Log.d("WearableClient", (new StringBuilder("processAssets: closing: ")).append(amj).toString());
                }
                autocloseoutputstream.close();
            }
            catch (IOException ioexception3)
            {
                return boolean1;
            }
            return boolean1;
            ioexception1;
            Log.w("WearableClient", (new StringBuilder("processAssets: writing data failed: ")).append(amj).toString());
            try
            {
                if (Log.isLoggable("WearableClient", 3))
                {
                    Log.d("WearableClient", (new StringBuilder("processAssets: closing: ")).append(amj).toString());
                }
                autocloseoutputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception2) { }
            return Boolean.valueOf(false);
            exception;
            try
            {
                if (Log.isLoggable("WearableClient", 3))
                {
                    Log.d("WearableClient", (new StringBuilder("processAssets: closing: ")).append(amj).toString());
                }
                autocloseoutputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception) { }
            throw exception;
        }

        _cls11(ParcelFileDescriptor parcelfiledescriptor, byte abyte0[])
        {
            amh = au.this;
            amj = parcelfiledescriptor;
            yL = abyte0;
            super();
        }
    }


    private class _cls1 extends com.google.android.gms.wearable.internal.a
    {

        final au amh;

        public void a(Status status)
        {
        }

        _cls1()
        {
            amh = au.this;
            super();
        }
    }


    private class _cls12 extends com.google.android.gms.wearable.internal.a
    {

        final au amh;
        final com.google.android.gms.common.api.a.d ami;

        public void a(v v1)
        {
            ami.a(new f.a(new Status(v1.statusCode), v1.alO));
        }

        _cls12(com.google.android.gms.common.api.a.d d1)
        {
            amh = au.this;
            ami = d1;
            super();
        }
    }


    private class _cls4 extends com.google.android.gms.wearable.internal.a
    {

        final au amh;
        final com.google.android.gms.common.api.a.d ami;

        public void a(x x1)
        {
            ami.a(new f.c(new Status(x1.statusCode), x1.alP));
        }

        _cls4(com.google.android.gms.common.api.a.d d1)
        {
            amh = au.this;
            ami = d1;
            super();
        }
    }


    private class _cls7 extends com.google.android.gms.wearable.internal.a
    {

        final com.google.android.gms.wearable.DataApi.DataListener alF;
        final au amh;
        final com.google.android.gms.common.api.a.d ami;

        public void a(Status status)
        {
            if (!status.isSuccess())
            {
                synchronized (au.b(amh))
                {
                    au.b(amh).remove(alF);
                }
            }
            ami.a(status);
        }

        _cls7(com.google.android.gms.wearable.DataApi.DataListener datalistener, com.google.android.gms.common.api.a.d d1)
        {
            amh = au.this;
            alF = datalistener;
            ami = d1;
            super();
        }
    }


    private class _cls8 extends com.google.android.gms.wearable.internal.a
    {

        final com.google.android.gms.wearable.MessageApi.MessageListener alU;
        final au amh;
        final com.google.android.gms.common.api.a.d ami;

        public void a(Status status)
        {
            if (!status.isSuccess())
            {
                synchronized (au.c(amh))
                {
                    au.c(amh).remove(alU);
                }
            }
            ami.a(status);
        }

        _cls8(com.google.android.gms.wearable.MessageApi.MessageListener messagelistener, com.google.android.gms.common.api.a.d d1)
        {
            amh = au.this;
            alU = messagelistener;
            ami = d1;
            super();
        }
    }


    private class _cls9 extends com.google.android.gms.wearable.internal.a
    {

        final com.google.android.gms.wearable.NodeApi.NodeListener alY;
        final au amh;
        final com.google.android.gms.common.api.a.d ami;

        public void a(Status status)
        {
            if (!status.isSuccess())
            {
                synchronized (au.d(amh))
                {
                    au.d(amh).remove(alY);
                }
            }
            ami.a(status);
        }

        _cls9(com.google.android.gms.wearable.NodeApi.NodeListener nodelistener, com.google.android.gms.common.api.a.d d1)
        {
            amh = au.this;
            alY = nodelistener;
            ami = d1;
            super();
        }
    }


    private class a extends com.google.android.gms.wearable.internal.a
    {

        private final List amk;
        private final com.google.android.gms.common.api.a.d yR;

        public void a(am am1)
        {
            yR.a(new f.a(new Status(am1.statusCode), am1.alO));
            if (am1.statusCode != 0)
            {
                for (Iterator iterator = amk.iterator(); iterator.hasNext(); ((FutureTask)iterator.next()).cancel(true)) { }
            }
        }

        a(com.google.android.gms.common.api.a.d d1, List list)
        {
            yR = d1;
            amk = list;
        }
    }


    private class _cls10 extends com.google.android.gms.wearable.internal.a
    {

        final au amh;
        final com.google.android.gms.common.api.a.d ami;

        public void a(Status status)
        {
            ami.a(status);
        }

        _cls10(com.google.android.gms.common.api.a.d d1)
        {
            amh = au.this;
            ami = d1;
            super();
        }
    }


    private class _cls3 extends com.google.android.gms.wearable.internal.a
    {

        final au amh;
        final com.google.android.gms.common.api.a.d ami;

        public void a(aq aq1)
        {
            ami.a(new ae.a(new Status(aq1.statusCode), aq1.amc));
        }

        _cls3(com.google.android.gms.common.api.a.d d1)
        {
            amh = au.this;
            ami = d1;
            super();
        }
    }


    private class _cls14 extends com.google.android.gms.wearable.internal.a
    {

        final au amh;
        final com.google.android.gms.common.api.a.d ami;

        public void Z(DataHolder dataholder)
        {
            ami.a(new DataItemBuffer(dataholder));
        }

        _cls14(com.google.android.gms.common.api.a.d d1)
        {
            amh = au.this;
            ami = d1;
            super();
        }
    }


    private class _cls2 extends com.google.android.gms.wearable.internal.a
    {

        final au amh;
        final com.google.android.gms.common.api.a.d ami;

        public void a(p p1)
        {
            ami.a(new f.b(new Status(p1.statusCode), p1.alL));
        }

        _cls2(com.google.android.gms.common.api.a.d d1)
        {
            amh = au.this;
            ami = d1;
            super();
        }
    }


    private class _cls13 extends com.google.android.gms.wearable.internal.a
    {

        final au amh;
        final com.google.android.gms.common.api.a.d ami;

        public void Z(DataHolder dataholder)
        {
            ami.a(new DataItemBuffer(dataholder));
        }

        _cls13(com.google.android.gms.common.api.a.d d1)
        {
            amh = au.this;
            ami = d1;
            super();
        }
    }


    private class _cls5 extends com.google.android.gms.wearable.internal.a
    {

        final au amh;
        final com.google.android.gms.common.api.a.d ami;

        public void a(z z1)
        {
            ami.a(new ah.b(new Status(z1.statusCode), z1.alQ));
        }

        _cls5(com.google.android.gms.common.api.a.d d1)
        {
            amh = au.this;
            ami = d1;
            super();
        }
    }


    private class _cls6 extends com.google.android.gms.wearable.internal.a
    {

        final au amh;
        final com.google.android.gms.common.api.a.d ami;

        public void a(t t1)
        {
            ArrayList arraylist = new ArrayList();
            arraylist.addAll(t1.alN);
            ami.a(new ah.a(new Status(t1.statusCode), arraylist));
        }

        _cls6(com.google.android.gms.common.api.a.d d1)
        {
            amh = au.this;
            ami = d1;
            super();
        }
    }

}
