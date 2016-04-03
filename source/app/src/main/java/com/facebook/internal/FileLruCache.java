// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.internal;

import android.content.Context;
import com.facebook.LoggingBehavior;
import com.facebook.Settings;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.facebook.internal:
//            Utility, Logger

public final class FileLruCache
{

    private static final String HEADER_CACHEKEY_KEY = "key";
    private static final String HEADER_CACHE_CONTENT_TAG_KEY = "tag";
    static final String TAG = com/facebook/internal/FileLruCache.getSimpleName();
    private static final AtomicLong bufferIndex = new AtomicLong();
    private final File directory;
    private boolean isTrimPending;
    private AtomicLong lastClearCacheTime;
    private final Limits limits;
    private final Object lock = new Object();
    private final String tag;

    public FileLruCache(Context context, String s, Limits limits1)
    {
        lastClearCacheTime = new AtomicLong(0L);
        tag = s;
        limits = limits1;
        directory = new File(context.getCacheDir(), s);
        if (directory.mkdirs() || directory.isDirectory())
        {
            BufferFile.deleteAll(directory);
        }
    }

    private void postTrim()
    {
        synchronized (lock)
        {
            if (!isTrimPending)
            {
                isTrimPending = true;
                Settings.getExecutor().execute(new _cls3());
            }
        }
    }

    private void renameToTargetAndTrim(String s, File file)
    {
        if (!file.renameTo(new File(directory, Utility.md5hash(s))))
        {
            file.delete();
        }
        postTrim();
    }

    private void trim()
    {
        PriorityQueue priorityqueue;
        Logger.log(LoggingBehavior.CACHE, TAG, "trim started");
        priorityqueue = new PriorityQueue();
        long l;
        long l1;
        l = 0L;
        l1 = 0L;
        File afile[] = directory.listFiles(BufferFile.excludeBufferFiles());
        if (afile == null) goto _L2; else goto _L1
_L1:
        int i = afile.length;
        int j = 0;
_L3:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        long l2;
        File file = afile[j];
        ModifiedFile modifiedfile = new ModifiedFile(file);
        priorityqueue.add(modifiedfile);
        Logger.log(LoggingBehavior.CACHE, TAG, (new StringBuilder("  trim considering time=")).append(Long.valueOf(modifiedfile.getModified())).append(" name=").append(modifiedfile.getFile().getName()).toString());
        l2 = l + file.length();
        long l3 = l1 + 1L;
        j++;
        l1 = l3;
        l = l2;
        if (true) goto _L3; else goto _L2
_L5:
        long l5;
        long l6;
        File file1;
        if (l5 <= (long)limits.getByteCount() && l6 <= (long)limits.getFileCount())
        {
            break MISSING_BLOCK_LABEL_261;
        }
        file1 = ((ModifiedFile)priorityqueue.remove()).getFile();
        Logger.log(LoggingBehavior.CACHE, TAG, (new StringBuilder("  trim removing ")).append(file1.getName()).toString());
        l5 -= file1.length();
        long l7 = l6 - 1L;
        file1.delete();
        l6 = l7;
        continue; /* Loop/switch isn't completed */
        synchronized (lock)
        {
            isTrimPending = false;
            lock.notifyAll();
        }
        return;
        Exception exception;
        exception;
        synchronized (lock)
        {
            isTrimPending = false;
            lock.notifyAll();
        }
        throw exception;
_L2:
        long l4 = l1;
        l5 = l;
        l6 = l4;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final void clearCache()
    {
        final File filesToDelete[] = directory.listFiles(BufferFile.excludeBufferFiles());
        lastClearCacheTime.set(System.currentTimeMillis());
        if (filesToDelete != null)
        {
            Settings.getExecutor().execute(new _cls2());
        }
    }

    public final InputStream get(String s)
    {
        return get(s, null);
    }

    public final InputStream get(String s, String s1)
    {
        File file = new File(directory, Utility.md5hash(s));
        FileInputStream fileinputstream;
        BufferedInputStream bufferedinputstream;
        Exception exception;
        JSONObject jsonobject;
        String s2;
        boolean flag;
        String s3;
        boolean flag1;
        long l;
        try
        {
            fileinputstream = new FileInputStream(file);
        }
        catch (IOException ioexception)
        {
            return null;
        }
        bufferedinputstream = new BufferedInputStream(fileinputstream, 8192);
        jsonobject = StreamHeader.readHeader(bufferedinputstream);
        if (jsonobject == null)
        {
            bufferedinputstream.close();
            return null;
        }
        s2 = jsonobject.optString("key");
        if (s2 == null)
        {
            break MISSING_BLOCK_LABEL_86;
        }
        flag = s2.equals(s);
        if (flag)
        {
            break MISSING_BLOCK_LABEL_93;
        }
        bufferedinputstream.close();
        return null;
        s3 = jsonobject.optString("tag", null);
        if (s1 == null && s3 != null)
        {
            break MISSING_BLOCK_LABEL_129;
        }
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_136;
        }
        flag1 = s1.equals(s3);
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_136;
        }
        bufferedinputstream.close();
        return null;
        l = (new Date()).getTime();
        Logger.log(LoggingBehavior.CACHE, TAG, (new StringBuilder("Setting lastModified to ")).append(Long.valueOf(l)).append(" for ").append(file.getName()).toString());
        file.setLastModified(l);
        return bufferedinputstream;
        exception;
        bufferedinputstream.close();
        throw exception;
    }

    public final InputStream interceptAndPut(String s, InputStream inputstream)
    {
        return new CopyingInputStream(inputstream, openPutStream(s));
    }

    final OutputStream openPutStream(String s)
    {
        return openPutStream(s, null);
    }

    public final OutputStream openPutStream(String s, String s1)
    {
        BufferedOutputStream bufferedoutputstream;
        File file = BufferFile.newFile(directory);
        file.delete();
        if (!file.createNewFile())
        {
            throw new IOException((new StringBuilder("Could not create file at ")).append(file.getAbsolutePath()).toString());
        }
        FileOutputStream fileoutputstream;
        JSONObject jsonobject;
        try
        {
            fileoutputstream = new FileOutputStream(file);
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            Logger.log(LoggingBehavior.CACHE, 5, TAG, (new StringBuilder("Error creating buffer output stream: ")).append(filenotfoundexception).toString());
            throw new IOException(filenotfoundexception.getMessage());
        }
        bufferedoutputstream = new BufferedOutputStream(new CloseCallbackOutputStream(fileoutputstream, new _cls1()), 8192);
        jsonobject = new JSONObject();
        jsonobject.put("key", s);
        if (!Utility.isNullOrEmpty(s1))
        {
            jsonobject.put("tag", s1);
        }
        StreamHeader.writeHeader(bufferedoutputstream, jsonobject);
        return bufferedoutputstream;
        JSONException jsonexception;
        jsonexception;
        Logger.log(LoggingBehavior.CACHE, 5, TAG, (new StringBuilder("Error creating JSON header for cache file: ")).append(jsonexception).toString());
        throw new IOException(jsonexception.getMessage());
        Exception exception;
        exception;
        bufferedoutputstream.close();
        throw exception;
    }

    final long sizeInBytesForTest()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
_L3:
        boolean flag = isTrimPending;
        if (!flag) goto _L2; else goto _L1
_L1:
        Exception exception;
        long l;
        try
        {
            lock.wait();
        }
        catch (InterruptedException interruptedexception) { }
        finally
        {
            obj;
        }
        if (true) goto _L3; else goto _L2
_L2:
        obj;
        JVM INSTR monitorexit ;
        File afile[] = directory.listFiles();
        l = 0L;
        if (afile != null)
        {
            int i = afile.length;
            for (int j = 0; j < i;)
            {
                long l1 = l + afile[j].length();
                j++;
                l = l1;
            }

        }
        if (true)
        {
            break MISSING_BLOCK_LABEL_93;
        }
        JVM INSTR monitorexit ;
        throw exception;
        return l;
    }

    public final String toString()
    {
        return (new StringBuilder("{FileLruCache: tag:")).append(tag).append(" file:").append(directory.getName()).append("}").toString();
    }






    private class BufferFile
    {

        private static final String FILE_NAME_PREFIX = "buffer";
        private static final FilenameFilter filterExcludeBufferFiles = new _cls1();
        private static final FilenameFilter filterExcludeNonBufferFiles = new _cls2();

        static void deleteAll(File file)
        {
            File afile[] = file.listFiles(excludeNonBufferFiles());
            if (afile != null)
            {
                int i = afile.length;
                for (int j = 0; j < i; j++)
                {
                    afile[j].delete();
                }

            }
        }

        static FilenameFilter excludeBufferFiles()
        {
            return filterExcludeBufferFiles;
        }

        static FilenameFilter excludeNonBufferFiles()
        {
            return filterExcludeNonBufferFiles;
        }

        static File newFile(File file)
        {
            return new File(file, (new StringBuilder("buffer")).append(Long.valueOf(FileLruCache.bufferIndex.incrementAndGet()).toString()).toString());
        }


        private BufferFile()
        {
        }

        class _cls1
            implements FilenameFilter
        {

            public final boolean accept(File file, String s)
            {
                return !s.startsWith("buffer");
            }

                _cls1()
                {
                }
        }


        class _cls2
            implements FilenameFilter
        {

            public final boolean accept(File file, String s)
            {
                return s.startsWith("buffer");
            }

                _cls2()
                {
                }
        }

    }


    private class _cls3
        implements Runnable
    {

        final FileLruCache this$0;

        public void run()
        {
            trim();
        }

        _cls3()
        {
            this$0 = FileLruCache.this;
            super();
        }
    }


    private class ModifiedFile
        implements Comparable
    {

        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        private final File file;
        private final long modified;

        public final int compareTo(ModifiedFile modifiedfile)
        {
            if (getModified() < modifiedfile.getModified())
            {
                return -1;
            }
            if (getModified() > modifiedfile.getModified())
            {
                return 1;
            } else
            {
                return getFile().compareTo(modifiedfile.getFile());
            }
        }

        public final volatile int compareTo(Object obj)
        {
            return compareTo((ModifiedFile)obj);
        }

        public final boolean equals(Object obj)
        {
            return (obj instanceof ModifiedFile) && compareTo((ModifiedFile)obj) == 0;
        }

        final File getFile()
        {
            return file;
        }

        final long getModified()
        {
            return modified;
        }

        public final int hashCode()
        {
            return 37 * (1073 + file.hashCode()) + (int)(modified % 0x7fffffffL);
        }

        ModifiedFile(File file1)
        {
            file = file1;
            modified = file1.lastModified();
        }
    }


    private class Limits
    {

        private int byteCount;
        private int fileCount;

        final int getByteCount()
        {
            return byteCount;
        }

        final int getFileCount()
        {
            return fileCount;
        }

        final void setByteCount(int i)
        {
            if (i < 0)
            {
                throw new InvalidParameterException("Cache byte-count limit must be >= 0");
            } else
            {
                byteCount = i;
                return;
            }
        }

        final void setFileCount(int i)
        {
            if (i < 0)
            {
                throw new InvalidParameterException("Cache file count limit must be >= 0");
            } else
            {
                fileCount = i;
                return;
            }
        }

        public Limits()
        {
            fileCount = 1024;
            byteCount = 0x100000;
        }
    }


    private class _cls2
        implements Runnable
    {

        final FileLruCache this$0;
        final File val$filesToDelete[];

        public void run()
        {
            File afile[] = filesToDelete;
            int i = afile.length;
            for (int j = 0; j < i; j++)
            {
                afile[j].delete();
            }

        }

        _cls2()
        {
            this$0 = FileLruCache.this;
            filesToDelete = afile;
            super();
        }
    }


    private class StreamHeader
    {

        private static final int HEADER_VERSION;

        static JSONObject readHeader(InputStream inputstream)
        {
            JSONTokener jsontokener;
            int i = 0;
            if (inputstream.read() != 0)
            {
                return null;
            }
            int j = 0;
            int k = 0;
            for (; j < 3; j++)
            {
                int i1 = inputstream.read();
                if (i1 == -1)
                {
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read returned -1 while reading header size");
                    return null;
                }
                k = (k << 8) + (i1 & 0xff);
            }

            byte abyte0[] = new byte[k];
            int l;
            for (; i < k; i += l)
            {
                l = inputstream.read(abyte0, i, k - i);
                if (l <= 0)
                {
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, (new StringBuilder("readHeader: stream.read stopped at ")).append(Integer.valueOf(i)).append(" when expected ").append(k).toString());
                    return null;
                }
            }

            jsontokener = new JSONTokener(new String(abyte0));
            Object obj;
            obj = jsontokener.nextValue();
            if (obj instanceof JSONObject)
            {
                break MISSING_BLOCK_LABEL_204;
            }
            Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, (new StringBuilder("readHeader: expected JSONObject, got ")).append(obj.getClass().getCanonicalName()).toString());
            return null;
            JSONObject jsonobject;
            try
            {
                jsonobject = (JSONObject)obj;
            }
            catch (JSONException jsonexception)
            {
                throw new IOException(jsonexception.getMessage());
            }
            return jsonobject;
        }

        static void writeHeader(OutputStream outputstream, JSONObject jsonobject)
        {
            String s;
            byte abyte0[];
            if (!(jsonobject instanceof JSONObject))
            {
                s = jsonobject.toString();
            } else
            {
                s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
            }
            abyte0 = s.getBytes();
            outputstream.write(0);
            outputstream.write(0xff & abyte0.length >> 16);
            outputstream.write(0xff & abyte0.length >> 8);
            outputstream.write(0xff & abyte0.length);
            outputstream.write(abyte0);
        }

        private StreamHeader()
        {
        }
    }


    private class CopyingInputStream extends InputStream
    {

        final InputStream input;
        final OutputStream output;

        public final int available()
        {
            return input.available();
        }

        public final void close()
        {
            input.close();
            output.close();
            return;
            Exception exception;
            exception;
            output.close();
            throw exception;
        }

        public final void mark(int i)
        {
            throw new UnsupportedOperationException();
        }

        public final boolean markSupported()
        {
            return false;
        }

        public final int read()
        {
            int i = input.read();
            if (i >= 0)
            {
                output.write(i);
            }
            return i;
        }

        public final int read(byte abyte0[])
        {
            int i = input.read(abyte0);
            if (i > 0)
            {
                output.write(abyte0, 0, i);
            }
            return i;
        }

        public final int read(byte abyte0[], int i, int j)
        {
            int k = input.read(abyte0, i, j);
            if (k > 0)
            {
                output.write(abyte0, i, k);
            }
            return k;
        }

        public final void reset()
        {
            this;
            JVM INSTR monitorenter ;
            throw new UnsupportedOperationException();
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public final long skip(long l)
        {
            byte abyte0[] = new byte[1024];
            long l1 = 0L;
            do
            {
                int i;
label0:
                {
                    if (l1 < l)
                    {
                        i = read(abyte0, 0, (int)Math.min(l - l1, 1024L));
                        if (i >= 0)
                        {
                            break label0;
                        }
                    }
                    return l1;
                }
                l1 += i;
            } while (true);
        }

        CopyingInputStream(InputStream inputstream, OutputStream outputstream)
        {
            input = inputstream;
            output = outputstream;
        }
    }


    private class CloseCallbackOutputStream extends OutputStream
    {

        final StreamCloseCallback callback;
        final OutputStream innerStream;

        public void close()
        {
            innerStream.close();
            callback.onClose();
            return;
            Exception exception;
            exception;
            callback.onClose();
            throw exception;
        }

        public void flush()
        {
            innerStream.flush();
        }

        public void write(int i)
        {
            innerStream.write(i);
        }

        public void write(byte abyte0[])
        {
            innerStream.write(abyte0);
        }

        public void write(byte abyte0[], int i, int j)
        {
            innerStream.write(abyte0, i, j);
        }

        CloseCallbackOutputStream(OutputStream outputstream, StreamCloseCallback streamclosecallback)
        {
            innerStream = outputstream;
            callback = streamclosecallback;
        }

        private class StreamCloseCallback
        {

            public abstract void onClose();
        }

    }


    private class _cls1
        implements StreamCloseCallback
    {

        final FileLruCache this$0;
        final File val$buffer;
        final long val$bufferFileCreateTime;
        final String val$key;

        public void onClose()
        {
            if (bufferFileCreateTime < lastClearCacheTime.get())
            {
                buffer.delete();
                return;
            } else
            {
                renameToTargetAndTrim(key, buffer);
                return;
            }
        }

        _cls1()
        {
            this$0 = FileLruCache.this;
            bufferFileCreateTime = l;
            buffer = file;
            key = s;
            super();
        }
    }

}
