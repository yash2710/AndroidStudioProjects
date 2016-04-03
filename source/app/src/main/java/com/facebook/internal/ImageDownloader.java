// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.facebook.FacebookException;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.facebook.internal:
//            WorkQueue, ImageRequest, ImageResponseCache, UrlRedirectCache, 
//            Utility

public class ImageDownloader
{

    private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
    private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
    private static WorkQueue cacheReadQueue = new WorkQueue(2);
    private static WorkQueue downloadQueue = new WorkQueue(8);
    private static Handler handler;
    private static final Map pendingRequests = new HashMap();

    public ImageDownloader()
    {
    }

    public static boolean cancelRequest(ImageRequest imagerequest)
    {
        RequestKey requestkey = new RequestKey(imagerequest.getImageUri(), imagerequest.getCallerTag());
        Map map = pendingRequests;
        map;
        JVM INSTR monitorenter ;
        DownloaderContext downloadercontext = (DownloaderContext)pendingRequests.get(requestkey);
        if (downloadercontext == null) goto _L2; else goto _L1
_L1:
        if (!downloadercontext.workItem.cancel()) goto _L4; else goto _L3
_L3:
        pendingRequests.remove(requestkey);
        boolean flag = true;
_L6:
        map;
        JVM INSTR monitorexit ;
        return flag;
_L4:
        downloadercontext.isCancelled = true;
        flag = true;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
_L2:
        flag = false;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static void clearCache(Context context)
    {
        ImageResponseCache.clearCache(context);
        UrlRedirectCache.clearCache(context);
    }

    private static void download(RequestKey requestkey, Context context)
    {
        Object obj;
        boolean flag;
        obj = null;
        flag = true;
        HttpURLConnection httpurlconnection = (HttpURLConnection)HttpInstrumentation.openConnection((new URL(requestkey.uri.toString())).openConnection());
        httpurlconnection.setInstanceFollowRedirects(false);
        httpurlconnection.getResponseCode();
        JVM INSTR lookupswitch 3: default 76
    //                   200: 283
    //                   301: 179
    //                   302: 179;
           goto _L1 _L2 _L3 _L3
_L1:
        java.io.InputStream inputstream2 = httpurlconnection.getErrorStream();
        java.io.InputStream inputstream = inputstream2;
        InputStreamReader inputstreamreader;
        char ac[];
        StringBuilder stringbuilder;
        inputstreamreader = new InputStreamReader(inputstream);
        ac = new char[128];
        stringbuilder = new StringBuilder();
_L6:
        int i = inputstreamreader.read(ac, 0, 128);
        if (i <= 0) goto _L5; else goto _L4
_L4:
        stringbuilder.append(ac, 0, i);
          goto _L6
        IOException ioexception1;
        ioexception1;
        Object obj1;
        Object obj2;
        obj2 = httpurlconnection;
        obj1 = ioexception1;
_L16:
        Utility.closeQuietly(inputstream);
        Utility.disconnectQuietly(((java.net.URLConnection) (obj2)));
_L11:
        if (flag)
        {
            issueResponse(requestkey, ((Exception) (obj1)), ((Bitmap) (obj)), false);
        }
        return;
_L3:
        String s = httpurlconnection.getHeaderField("location");
        if (Utility.isNullOrEmpty(s)) goto _L8; else goto _L7
_L7:
        URI uri;
        DownloaderContext downloadercontext;
        uri = new URI(s);
        UrlRedirectCache.cacheUriRedirect(context, requestkey.uri, uri);
        downloadercontext = removePendingRequest(requestkey);
        if (downloadercontext == null) goto _L10; else goto _L9
_L9:
        if (!downloadercontext.isCancelled)
        {
            enqueueCacheRead(downloadercontext.request, new RequestKey(uri, requestkey.tag), false);
        }
_L10:
        FacebookException facebookexception;
        flag = false;
        facebookexception = null;
        inputstream = null;
_L12:
        Utility.closeQuietly(inputstream);
        Utility.disconnectQuietly(httpurlconnection);
        obj1 = facebookexception;
          goto _L11
_L2:
        java.io.InputStream inputstream1 = ImageResponseCache.interceptAndCacheImageStream(context, httpurlconnection);
        inputstream = inputstream1;
        obj = BitmapFactoryInstrumentation.decodeStream(inputstream);
        facebookexception = null;
          goto _L12
_L5:
        Utility.closeQuietly(inputstreamreader);
        facebookexception = new FacebookException(stringbuilder.toString());
        obj = null;
          goto _L12
        URISyntaxException urisyntaxexception1;
        urisyntaxexception1;
        Object obj3;
        obj3 = httpurlconnection;
        obj1 = urisyntaxexception1;
_L15:
        Utility.closeQuietly(inputstream);
        Utility.disconnectQuietly(((java.net.URLConnection) (obj3)));
        obj = null;
          goto _L11
        Exception exception;
        exception;
        Object obj4 = null;
_L14:
        Utility.closeQuietly(((java.io.Closeable) (obj)));
        Utility.disconnectQuietly(((java.net.URLConnection) (obj4)));
        throw exception;
        Exception exception1;
        exception1;
        obj4 = httpurlconnection;
        exception = exception1;
        obj = null;
        continue; /* Loop/switch isn't completed */
        Exception exception2;
        exception2;
        obj = inputstream;
        obj4 = httpurlconnection;
        exception = exception2;
        if (true) goto _L14; else goto _L13
_L13:
        obj1;
        obj3 = null;
        inputstream = null;
          goto _L15
        URISyntaxException urisyntaxexception;
        urisyntaxexception;
        obj3 = httpurlconnection;
        obj1 = urisyntaxexception;
        inputstream = null;
          goto _L15
        URISyntaxException urisyntaxexception2;
        urisyntaxexception2;
        obj3 = httpurlconnection;
        obj1 = urisyntaxexception2;
        flag = false;
        inputstream = null;
          goto _L15
        obj1;
        obj2 = null;
        inputstream = null;
          goto _L16
        IOException ioexception;
        ioexception;
        obj2 = httpurlconnection;
        obj1 = ioexception;
        inputstream = null;
          goto _L16
        IOException ioexception2;
        ioexception2;
        obj2 = httpurlconnection;
        obj1 = ioexception2;
        flag = false;
        inputstream = null;
          goto _L16
_L8:
        flag = false;
        obj = null;
        facebookexception = null;
        inputstream = null;
          goto _L12
    }

    public static void downloadAsync(ImageRequest imagerequest)
    {
        RequestKey requestkey;
        if (imagerequest == null)
        {
            return;
        }
        requestkey = new RequestKey(imagerequest.getImageUri(), imagerequest.getCallerTag());
        Map map = pendingRequests;
        map;
        JVM INSTR monitorenter ;
        DownloaderContext downloadercontext = (DownloaderContext)pendingRequests.get(requestkey);
        if (downloadercontext == null)
        {
            break MISSING_BLOCK_LABEL_76;
        }
        downloadercontext.request = imagerequest;
        downloadercontext.isCancelled = false;
        downloadercontext.workItem.moveToFront();
_L1:
        map;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
        enqueueCacheRead(imagerequest, requestkey, imagerequest.isCachedRedirectAllowed());
          goto _L1
    }

    private static void enqueueCacheRead(ImageRequest imagerequest, RequestKey requestkey, boolean flag)
    {
        enqueueRequest(imagerequest, requestkey, cacheReadQueue, new CacheReadWorkItem(imagerequest.getContext(), requestkey, flag));
    }

    private static void enqueueDownload(ImageRequest imagerequest, RequestKey requestkey)
    {
        enqueueRequest(imagerequest, requestkey, downloadQueue, new DownloadImageWorkItem(imagerequest.getContext(), requestkey));
    }

    private static void enqueueRequest(ImageRequest imagerequest, RequestKey requestkey, WorkQueue workqueue, Runnable runnable)
    {
        synchronized (pendingRequests)
        {
            DownloaderContext downloadercontext = new DownloaderContext(null);
            downloadercontext.request = imagerequest;
            pendingRequests.put(requestkey, downloadercontext);
            downloadercontext.workItem = workqueue.addActiveWorkItem(runnable);
        }
    }

    private static Handler getHandler()
    {
        com/facebook/internal/ImageDownloader;
        JVM INSTR monitorenter ;
        Handler handler1;
        if (handler == null)
        {
            handler = new Handler(Looper.getMainLooper());
        }
        handler1 = handler;
        com/facebook/internal/ImageDownloader;
        JVM INSTR monitorexit ;
        return handler1;
        Exception exception;
        exception;
        throw exception;
    }

    private static void issueResponse(RequestKey requestkey, final Exception error, final Bitmap bitmap, final boolean isCachedRedirect)
    {
        DownloaderContext downloadercontext = removePendingRequest(requestkey);
        if (downloadercontext != null && !downloadercontext.isCancelled)
        {
            final ImageRequest request = downloadercontext.request;
            final ImageRequest.Callback callback = request.getCallback();
            if (callback != null)
            {
                getHandler().post(new _cls1());
            }
        }
    }

    public static void prioritizeRequest(ImageRequest imagerequest)
    {
        RequestKey requestkey = new RequestKey(imagerequest.getImageUri(), imagerequest.getCallerTag());
        Map map = pendingRequests;
        map;
        JVM INSTR monitorenter ;
        DownloaderContext downloadercontext = (DownloaderContext)pendingRequests.get(requestkey);
        if (downloadercontext == null)
        {
            break MISSING_BLOCK_LABEL_51;
        }
        downloadercontext.workItem.moveToFront();
        map;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static void readFromCache(RequestKey requestkey, Context context, boolean flag)
    {
        if (!flag) goto _L2; else goto _L1
_L1:
        URI uri = UrlRedirectCache.getRedirectedUri(context, requestkey.uri);
        if (uri == null) goto _L2; else goto _L3
_L3:
        java.io.InputStream inputstream;
        boolean flag1;
        java.io.InputStream inputstream1 = ImageResponseCache.getCachedImageStream(uri, context);
        boolean flag2 = false;
        if (inputstream1 != null)
        {
            flag2 = true;
        }
        boolean flag3 = flag2;
        inputstream = inputstream1;
        flag1 = flag3;
_L9:
        if (!flag1)
        {
            inputstream = ImageResponseCache.getCachedImageStream(requestkey.uri, context);
        }
        if (inputstream == null) goto _L5; else goto _L4
_L4:
        Bitmap bitmap = BitmapFactoryInstrumentation.decodeStream(inputstream);
        Utility.closeQuietly(inputstream);
        issueResponse(requestkey, null, bitmap, flag1);
_L7:
        return;
_L5:
        DownloaderContext downloadercontext = removePendingRequest(requestkey);
        if (downloadercontext == null || downloadercontext.isCancelled) goto _L7; else goto _L6
_L6:
        enqueueDownload(downloadercontext.request, requestkey);
        return;
_L2:
        inputstream = null;
        flag1 = false;
        if (true) goto _L9; else goto _L8
_L8:
    }

    private static DownloaderContext removePendingRequest(RequestKey requestkey)
    {
        DownloaderContext downloadercontext;
        synchronized (pendingRequests)
        {
            downloadercontext = (DownloaderContext)pendingRequests.remove(requestkey);
        }
        return downloadercontext;
    }




    private class RequestKey
    {

        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        Object tag;
        URI uri;

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if (obj != null)
            {
                boolean flag1 = obj instanceof RequestKey;
                flag = false;
                if (flag1)
                {
                    RequestKey requestkey = (RequestKey)obj;
                    URI uri1 = requestkey.uri;
                    URI uri2 = uri;
                    flag = false;
                    if (uri1 == uri2)
                    {
                        Object obj1 = requestkey.tag;
                        Object obj2 = tag;
                        flag = false;
                        if (obj1 == obj2)
                        {
                            flag = true;
                        }
                    }
                }
            }
            return flag;
        }

        public int hashCode()
        {
            return 37 * (1073 + uri.hashCode()) + tag.hashCode();
        }

        RequestKey(URI uri1, Object obj)
        {
            uri = uri1;
            tag = obj;
        }
    }


    private class DownloaderContext
    {

        boolean isCancelled;
        ImageRequest request;
        WorkQueue.WorkItem workItem;

        private DownloaderContext()
        {
        }

        DownloaderContext(_cls1 _pcls1)
        {
            this();
        }
    }


    private class CacheReadWorkItem
        implements Runnable
    {

        private boolean allowCachedRedirects;
        private Context context;
        private RequestKey key;

        public void run()
        {
            ImageDownloader.readFromCache(key, context, allowCachedRedirects);
        }

        CacheReadWorkItem(Context context1, RequestKey requestkey, boolean flag)
        {
            context = context1;
            key = requestkey;
            allowCachedRedirects = flag;
        }
    }


    private class DownloadImageWorkItem
        implements Runnable
    {

        private Context context;
        private RequestKey key;

        public void run()
        {
            ImageDownloader.download(key, context);
        }

        DownloadImageWorkItem(Context context1, RequestKey requestkey)
        {
            context = context1;
            key = requestkey;
        }
    }


    private class _cls1
        implements Runnable
    {

        final Bitmap val$bitmap;
        final ImageRequest.Callback val$callback;
        final Exception val$error;
        final boolean val$isCachedRedirect;
        final ImageRequest val$request;

        public final void run()
        {
            ImageResponse imageresponse = new ImageResponse(request, error, isCachedRedirect, bitmap);
            callback.onCompleted(imageresponse);
        }

        _cls1()
        {
            request = imagerequest;
            error = exception;
            isCachedRedirect = flag;
            bitmap = bitmap1;
            callback = callback1;
            super();
        }
    }

}
