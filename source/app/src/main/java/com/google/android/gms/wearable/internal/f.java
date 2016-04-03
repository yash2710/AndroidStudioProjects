// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.wearable.PutDataRequest;

public final class f
    implements DataApi
{

    public f()
    {
    }

    private PendingResult a(GoogleApiClient googleapiclient, com.google.android.gms.wearable.DataApi.DataListener datalistener, IntentFilter aintentfilter[])
    {
        return googleapiclient.a(new _cls8(datalistener, aintentfilter));
    }

    private void a(Asset asset)
    {
        if (asset == null)
        {
            throw new IllegalArgumentException("asset is null");
        }
        if (asset.getDigest() == null)
        {
            throw new IllegalArgumentException("invalid asset");
        }
        if (asset.getData() != null)
        {
            throw new IllegalArgumentException("invalid asset");
        } else
        {
            return;
        }
    }

    public final PendingResult addListener(GoogleApiClient googleapiclient, com.google.android.gms.wearable.DataApi.DataListener datalistener)
    {
        return a(googleapiclient, datalistener, null);
    }

    public final PendingResult deleteDataItems(GoogleApiClient googleapiclient, Uri uri)
    {
        return googleapiclient.a(new _cls5(uri));
    }

    public final PendingResult getDataItem(GoogleApiClient googleapiclient, Uri uri)
    {
        return googleapiclient.a(new _cls2(uri));
    }

    public final PendingResult getDataItems(GoogleApiClient googleapiclient)
    {
        return googleapiclient.a(new _cls3());
    }

    public final PendingResult getDataItems(GoogleApiClient googleapiclient, Uri uri)
    {
        return googleapiclient.a(new _cls4(uri));
    }

    public final PendingResult getFdForAsset(GoogleApiClient googleapiclient, Asset asset)
    {
        a(asset);
        return googleapiclient.a(new _cls6(asset));
    }

    public final PendingResult getFdForAsset(GoogleApiClient googleapiclient, DataItemAsset dataitemasset)
    {
        return googleapiclient.a(new _cls7(dataitemasset));
    }

    public final PendingResult putDataItem(GoogleApiClient googleapiclient, PutDataRequest putdatarequest)
    {
        return googleapiclient.a(new _cls1(putdatarequest));
    }

    public final PendingResult removeListener(GoogleApiClient googleapiclient, com.google.android.gms.wearable.DataApi.DataListener datalistener)
    {
        return googleapiclient.a(new _cls9(datalistener));
    }

    private class _cls8 extends d
    {

        final f alC;
        final com.google.android.gms.wearable.DataApi.DataListener alF;
        final IntentFilter alG[];

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((au)a1);
        }

        protected void a(au au1)
        {
            au1.a(this, alF, alG);
        }

        public Result c(Status status)
        {
            return d(status);
        }

        public Status d(Status status)
        {
            return new Status(13);
        }

        _cls8(com.google.android.gms.wearable.DataApi.DataListener datalistener, IntentFilter aintentfilter[])
        {
            alC = f.this;
            alF = datalistener;
            alG = aintentfilter;
            super();
        }
    }


    private class _cls5 extends d
    {

        final Uri abk;
        final f alC;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((au)a1);
        }

        protected void a(au au1)
        {
            au1.c(this, abk);
        }

        protected com.google.android.gms.wearable.DataApi.DeleteDataItemsResult as(Status status)
        {
            return new b(status, 0);
        }

        protected Result c(Status status)
        {
            return as(status);
        }

        _cls5(Uri uri)
        {
            alC = f.this;
            abk = uri;
            super();
        }

        private class b
            implements com.google.android.gms.wearable.DataApi.DeleteDataItemsResult
        {

            private final int alI;
            private final Status yz;

            public int getNumDeleted()
            {
                return alI;
            }

            public Status getStatus()
            {
                return yz;
            }

            public b(Status status, int i)
            {
                yz = status;
                alI = i;
            }
        }

    }


    private class _cls2 extends d
    {

        final Uri abk;
        final f alC;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((au)a1);
        }

        protected void a(au au1)
        {
            au1.a(this, abk);
        }

        protected com.google.android.gms.wearable.DataApi.DataItemResult aq(Status status)
        {
            return new a(status, null);
        }

        protected Result c(Status status)
        {
            return aq(status);
        }

        _cls2(Uri uri)
        {
            alC = f.this;
            abk = uri;
            super();
        }

        private class a
            implements com.google.android.gms.wearable.DataApi.DataItemResult
        {

            private final DataItem alH;
            private final Status yz;

            public DataItem getDataItem()
            {
                return alH;
            }

            public Status getStatus()
            {
                return yz;
            }

            public a(Status status, DataItem dataitem)
            {
                yz = status;
                alH = dataitem;
            }
        }

    }


    private class _cls3 extends d
    {

        final f alC;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((au)a1);
        }

        protected void a(au au1)
        {
            au1.o(this);
        }

        protected DataItemBuffer ar(Status status)
        {
            return new DataItemBuffer(DataHolder.af(status.getStatusCode()));
        }

        protected Result c(Status status)
        {
            return ar(status);
        }

        _cls3()
        {
            alC = f.this;
            super();
        }
    }


    private class _cls4 extends d
    {

        final Uri abk;
        final f alC;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((au)a1);
        }

        protected void a(au au1)
        {
            au1.b(this, abk);
        }

        protected DataItemBuffer ar(Status status)
        {
            return new DataItemBuffer(DataHolder.af(status.getStatusCode()));
        }

        protected Result c(Status status)
        {
            return ar(status);
        }

        _cls4(Uri uri)
        {
            alC = f.this;
            abk = uri;
            super();
        }
    }


    private class _cls6 extends d
    {

        final f alC;
        final Asset alD;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((au)a1);
        }

        protected void a(au au1)
        {
            au1.a(this, alD);
        }

        protected com.google.android.gms.wearable.DataApi.GetFdForAssetResult at(Status status)
        {
            return new c(status, null);
        }

        protected Result c(Status status)
        {
            return at(status);
        }

        _cls6(Asset asset)
        {
            alC = f.this;
            alD = asset;
            super();
        }

        private class c
            implements com.google.android.gms.wearable.DataApi.GetFdForAssetResult
        {

            private final ParcelFileDescriptor alJ;
            private final Status yz;

            public ParcelFileDescriptor getFd()
            {
                return alJ;
            }

            public InputStream getInputStream()
            {
                return new android.os.ParcelFileDescriptor.AutoCloseInputStream(alJ);
            }

            public Status getStatus()
            {
                return yz;
            }

            public void release()
            {
                try
                {
                    alJ.close();
                    return;
                }
                catch (IOException ioexception)
                {
                    return;
                }
            }

            public c(Status status, ParcelFileDescriptor parcelfiledescriptor)
            {
                yz = status;
                alJ = parcelfiledescriptor;
            }
        }

    }


    private class _cls7 extends d
    {

        final f alC;
        final DataItemAsset alE;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((au)a1);
        }

        protected void a(au au1)
        {
            au1.a(this, alE);
        }

        protected com.google.android.gms.wearable.DataApi.GetFdForAssetResult at(Status status)
        {
            return new c(status, null);
        }

        protected Result c(Status status)
        {
            return at(status);
        }

        _cls7(DataItemAsset dataitemasset)
        {
            alC = f.this;
            alE = dataitemasset;
            super();
        }
    }


    private class _cls1 extends d
    {

        final PutDataRequest alB;
        final f alC;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((au)a1);
        }

        protected void a(au au1)
        {
            au1.a(this, alB);
        }

        public com.google.android.gms.wearable.DataApi.DataItemResult aq(Status status)
        {
            return new a(status, null);
        }

        public Result c(Status status)
        {
            return aq(status);
        }

        _cls1(PutDataRequest putdatarequest)
        {
            alC = f.this;
            alB = putdatarequest;
            super();
        }
    }


    private class _cls9 extends d
    {

        final f alC;
        final com.google.android.gms.wearable.DataApi.DataListener alF;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((au)a1);
        }

        protected void a(au au1)
        {
            au1.a(this, alF);
        }

        public Result c(Status status)
        {
            return d(status);
        }

        public Status d(Status status)
        {
            return new Status(13);
        }

        _cls9(com.google.android.gms.wearable.DataApi.DataListener datalistener)
        {
            alC = f.this;
            alF = datalistener;
            super();
        }
    }

}
