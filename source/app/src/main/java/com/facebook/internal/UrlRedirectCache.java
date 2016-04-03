// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.internal;

import android.content.Context;
import com.facebook.LoggingBehavior;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

// Referenced classes of package com.facebook.internal:
//            FileLruCache, Utility, Logger

class UrlRedirectCache
{

    private static final String REDIRECT_CONTENT_TAG = (new StringBuilder()).append(TAG).append("_Redirect").toString();
    static final String TAG = com/facebook/internal/UrlRedirectCache.getSimpleName();
    private static volatile FileLruCache urlRedirectCache;

    UrlRedirectCache()
    {
    }

    static void cacheUriRedirect(Context context, URI uri, URI uri1)
    {
        OutputStream outputstream;
        Exception exception;
        Object obj;
        if (uri == null || uri1 == null)
        {
            return;
        }
        outputstream = null;
        OutputStream outputstream1;
        try
        {
            outputstream1 = getCache(context).openPutStream(uri.toString(), REDIRECT_CONTENT_TAG);
        }
        catch (IOException ioexception)
        {
            Utility.closeQuietly(outputstream);
            return;
        }
        finally
        {
            obj = null;
        }
        outputstream = outputstream1;
        outputstream.write(uri1.toString().getBytes());
        Utility.closeQuietly(outputstream);
        return;
        Exception exception1 = exception;
_L2:
        Utility.closeQuietly(((java.io.Closeable) (obj)));
        throw exception1;
        Exception exception2;
        exception2;
        obj = outputstream;
        exception1 = exception2;
        if (true) goto _L2; else goto _L1
_L1:
    }

    static void clearCache(Context context)
    {
        try
        {
            getCache(context).clearCache();
            return;
        }
        catch (IOException ioexception)
        {
            Logger.log(LoggingBehavior.CACHE, 5, TAG, (new StringBuilder("clearCache failed ")).append(ioexception.getMessage()).toString());
        }
    }

    static FileLruCache getCache(Context context)
    {
        com/facebook/internal/UrlRedirectCache;
        JVM INSTR monitorenter ;
        FileLruCache filelrucache;
        if (urlRedirectCache == null)
        {
            urlRedirectCache = new FileLruCache(context.getApplicationContext(), TAG, new FileLruCache.Limits());
        }
        filelrucache = urlRedirectCache;
        com/facebook/internal/UrlRedirectCache;
        JVM INSTR monitorexit ;
        return filelrucache;
        Exception exception;
        exception;
        throw exception;
    }

    static URI getRedirectedUri(Context context, URI uri)
    {
        boolean flag;
        String s;
        flag = false;
        if (uri == null)
        {
            return null;
        }
        s = uri.toString();
        FileLruCache filelrucache = getCache(context);
        String s1;
        InputStreamReader inputstreamreader1;
        s1 = s;
        inputstreamreader1 = null;
_L6:
        java.io.InputStream inputstream = filelrucache.get(s1, REDIRECT_CONTENT_TAG);
        if (inputstream == null) goto _L2; else goto _L1
_L1:
        InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
        char ac[];
        StringBuilder stringbuilder;
        ac = new char[128];
        stringbuilder = new StringBuilder();
_L5:
        int i = inputstreamreader.read(ac, 0, 128);
        if (i <= 0) goto _L4; else goto _L3
_L3:
        stringbuilder.append(ac, 0, i);
          goto _L5
        URISyntaxException urisyntaxexception2;
        urisyntaxexception2;
_L11:
        Utility.closeQuietly(inputstreamreader);
        return null;
_L4:
        String s2;
        Utility.closeQuietly(inputstreamreader);
        s2 = stringbuilder.toString();
        s1 = s2;
        inputstreamreader1 = inputstreamreader;
        flag = true;
          goto _L6
_L2:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_158;
        }
        URI uri1 = new URI(s1);
        Utility.closeQuietly(inputstreamreader1);
        return uri1;
        Utility.closeQuietly(inputstreamreader1);
        return null;
        IOException ioexception;
        ioexception;
        inputstreamreader = null;
_L10:
        Utility.closeQuietly(inputstreamreader);
        return null;
        Exception exception;
        exception;
        Exception exception1;
        inputstreamreader = null;
        exception1 = exception;
_L8:
        Utility.closeQuietly(inputstreamreader);
        throw exception1;
        exception1;
        continue; /* Loop/switch isn't completed */
        exception1;
        inputstreamreader = inputstreamreader1;
        if (true) goto _L8; else goto _L7
_L7:
        IOException ioexception2;
        ioexception2;
        continue; /* Loop/switch isn't completed */
        IOException ioexception1;
        ioexception1;
        inputstreamreader = inputstreamreader1;
        if (true) goto _L10; else goto _L9
_L9:
        URISyntaxException urisyntaxexception;
        urisyntaxexception;
        inputstreamreader = null;
          goto _L11
        URISyntaxException urisyntaxexception1;
        urisyntaxexception1;
        inputstreamreader = inputstreamreader1;
          goto _L11
    }

}
