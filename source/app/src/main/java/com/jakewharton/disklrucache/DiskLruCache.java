// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.jakewharton.disklrucache;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.jakewharton.disklrucache:
//            b, a, d, Util, 
//            e

public final class DiskLruCache
    implements Closeable
{

    private static Pattern a = Pattern.compile("[a-z0-9_-]{1,64}");
    private static final OutputStream p = new b();
    private final File b;
    private final File c;
    private final File d;
    private final File e;
    private final int f;
    private long g;
    private final int h;
    private long i;
    private Writer j;
    private final LinkedHashMap k = new LinkedHashMap(0, 0.75F, true);
    private int l;
    private long m;
    private ThreadPoolExecutor n;
    private final Callable o = new a(this);

    private DiskLruCache(File file, int i1, int j1, long l1)
    {
        i = 0L;
        m = 0L;
        n = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        b = file;
        f = i1;
        c = new File(file, "journal");
        d = new File(file, "journal.tmp");
        e = new File(file, "journal.bkp");
        h = j1;
        g = l1;
    }

    static int a(DiskLruCache disklrucache, int i1)
    {
        disklrucache.l = 0;
        return 0;
    }

    static Editor a(DiskLruCache disklrucache, String s, long l1)
    {
        return disklrucache.a(s, l1);
    }

    private Editor a(String s, long l1)
    {
        this;
        JVM INSTR monitorenter ;
        d d1;
        f();
        a(s);
        d1 = (d)k.get(s);
        if (l1 == -1L) goto _L2; else goto _L1
_L1:
        if (d1 == null) goto _L4; else goto _L3
_L3:
        long l2 = com.jakewharton.disklrucache.d.e(d1);
        if (l2 == l1) goto _L2; else goto _L4
_L4:
        Editor editor1 = null;
_L7:
        this;
        JVM INSTR monitorexit ;
        return editor1;
_L2:
        if (d1 != null) goto _L6; else goto _L5
_L5:
        d d3;
        d3 = new d(this, s, (byte)0);
        k.put(s, d3);
        d d2 = d3;
_L9:
        editor1 = new Editor(d2, (byte)0);
        com.jakewharton.disklrucache.d.a(d2, editor1);
        j.write((new StringBuilder("DIRTY ")).append(s).append('\n').toString());
        j.flush();
          goto _L7
        Exception exception;
        exception;
        throw exception;
_L6:
        Editor editor = com.jakewharton.disklrucache.d.a(d1);
label0:
        {
            if (editor == null)
            {
                break label0;
            }
            editor1 = null;
        }
        if (true) goto _L7; else goto _L8
_L8:
        d2 = d1;
          goto _L9
    }

    static OutputStream a()
    {
        return p;
    }

    static Writer a(DiskLruCache disklrucache)
    {
        return disklrucache.j;
    }

    static String a(InputStream inputstream)
    {
        return Util.a(new InputStreamReader(inputstream, Util.b));
    }

    private void a(Editor editor, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        d d1;
        d1 = Editor.a(editor);
        if (com.jakewharton.disklrucache.d.a(d1) != editor)
        {
            throw new IllegalStateException();
        }
        break MISSING_BLOCK_LABEL_30;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        int i1 = 0;
        if (!flag) goto _L2; else goto _L1
_L1:
        boolean flag1 = com.jakewharton.disklrucache.d.d(d1);
        i1 = 0;
        if (flag1) goto _L2; else goto _L3
_L3:
        int j1 = 0;
_L7:
        int k1 = h;
        i1 = 0;
        if (j1 >= k1) goto _L2; else goto _L4
_L4:
        if (!Editor.b(editor)[j1])
        {
            editor.abort();
            throw new IllegalStateException((new StringBuilder("Newly created entry didn't create value for index ")).append(j1).toString());
        }
        if (d1.getDirtyFile(j1).exists()) goto _L6; else goto _L5
_L5:
        editor.abort();
_L8:
        this;
        JVM INSTR monitorexit ;
        return;
_L6:
        j1++;
          goto _L7
_L2:
        long l1;
        File file;
        File file1;
        long l2;
        long l3;
        for (; i1 >= h; i1++)
        {
            break MISSING_BLOCK_LABEL_235;
        }

        file = d1.getDirtyFile(i1);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_227;
        }
        if (file.exists())
        {
            file1 = d1.getCleanFile(i1);
            file.renameTo(file1);
            l2 = com.jakewharton.disklrucache.d.b(d1)[i1];
            l3 = file1.length();
            com.jakewharton.disklrucache.d.b(d1)[i1] = l3;
            i = l3 + (i - l2);
        }
        break MISSING_BLOCK_LABEL_425;
        a(file);
        break MISSING_BLOCK_LABEL_425;
        l = 1 + l;
        com.jakewharton.disklrucache.d.a(d1, null);
        if (!(flag | com.jakewharton.disklrucache.d.d(d1)))
        {
            break MISSING_BLOCK_LABEL_376;
        }
        com.jakewharton.disklrucache.d.a(d1, true);
        j.write((new StringBuilder("CLEAN ")).append(com.jakewharton.disklrucache.d.c(d1)).append(d1.getLengths()).append('\n').toString());
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_335;
        }
        l1 = m;
        m = 1L + l1;
        com.jakewharton.disklrucache.d.a(d1, l1);
_L9:
        j.flush();
        if (i > g || e())
        {
            n.submit(o);
        }
          goto _L8
        k.remove(com.jakewharton.disklrucache.d.c(d1));
        j.write((new StringBuilder("REMOVE ")).append(com.jakewharton.disklrucache.d.c(d1)).append('\n').toString());
          goto _L9
    }

    static void a(DiskLruCache disklrucache, Editor editor, boolean flag)
    {
        disklrucache.a(editor, flag);
    }

    private static void a(File file)
    {
        if (file.exists() && !file.delete())
        {
            throw new IOException();
        } else
        {
            return;
        }
    }

    private static void a(File file, File file1, boolean flag)
    {
        if (flag)
        {
            a(file1);
        }
        if (!file.renameTo(file1))
        {
            throw new IOException();
        } else
        {
            return;
        }
    }

    private static void a(String s)
    {
        if (!a.matcher(s).matches())
        {
            throw new IllegalArgumentException((new StringBuilder("keys must match regex [a-z0-9_-]{1,64}: \"")).append(s).append("\"").toString());
        } else
        {
            return;
        }
    }

    private void b()
    {
        e e1 = new e(new FileInputStream(c), Util.a);
        Exception exception;
        String s = e1.readLine();
        String s1 = e1.readLine();
        String s2 = e1.readLine();
        String s3 = e1.readLine();
        String s4 = e1.readLine();
        if (!"libcore.io.DiskLruCache".equals(s) || !"1".equals(s1) || !Integer.toString(f).equals(s2) || !Integer.toString(h).equals(s3) || !"".equals(s4))
        {
            throw new IOException((new StringBuilder("unexpected journal header: [")).append(s).append(", ").append(s1).append(", ").append(s3).append(", ").append(s4).append("]").toString());
        }
        int i1 = 0;
_L3:
        String s5;
        int j1;
        s5 = e1.readLine();
        j1 = s5.indexOf(' ');
        if (j1 != -1)
        {
            break MISSING_BLOCK_LABEL_255;
        }
        try
        {
            throw new IOException((new StringBuilder("unexpected journal line: ")).append(s5).toString());
        }
        catch (EOFException eofexception) { }
        finally
        {
            Util.a(e1);
            throw exception;
        }
        l = i1 - k.size();
        Util.a(e1);
        return;
        int k1 = j1 + 1;
        int l1 = s5.indexOf(' ', k1);
        if (l1 != -1)
        {
            break MISSING_BLOCK_LABEL_318;
        }
        String s6 = s5.substring(k1);
        if (j1 != 6)
        {
            break MISSING_BLOCK_LABEL_529;
        }
        if (s5.startsWith("REMOVE"))
        {
            k.remove(s6);
            break MISSING_BLOCK_LABEL_536;
        }
        break MISSING_BLOCK_LABEL_529;
        String s7 = s5.substring(k1, l1);
_L1:
        d d1 = (d)k.get(s7);
        if (d1 != null)
        {
            break MISSING_BLOCK_LABEL_373;
        }
        d1 = new d(this, s7, (byte)0);
        k.put(s7, d1);
        if (l1 == -1 || j1 != 5)
        {
            break MISSING_BLOCK_LABEL_437;
        }
        if (s5.startsWith("CLEAN"))
        {
            String as[] = s5.substring(l1 + 1).split(" ");
            com.jakewharton.disklrucache.d.a(d1, true);
            com.jakewharton.disklrucache.d.a(d1, null);
            com.jakewharton.disklrucache.d.a(d1, as);
            break MISSING_BLOCK_LABEL_536;
        }
        if (l1 != -1 || j1 != 5)
        {
            break MISSING_BLOCK_LABEL_480;
        }
        if (s5.startsWith("DIRTY"))
        {
            com.jakewharton.disklrucache.d.a(d1, new Editor(d1, (byte)0));
            break MISSING_BLOCK_LABEL_536;
        }
        if (l1 != -1 || j1 != 4)
        {
            break MISSING_BLOCK_LABEL_503;
        }
        if (s5.startsWith("READ"))
        {
            break MISSING_BLOCK_LABEL_536;
        }
        throw new IOException((new StringBuilder("unexpected journal line: ")).append(s5).toString());
        s7 = s6;
          goto _L1
        i1++;
        if (true) goto _L3; else goto _L2
_L2:
    }

    static void b(DiskLruCache disklrucache)
    {
        disklrucache.g();
    }

    private void c()
    {
        a(d);
        for (Iterator iterator = k.values().iterator(); iterator.hasNext();)
        {
            d d1 = (d)iterator.next();
            if (com.jakewharton.disklrucache.d.a(d1) == null)
            {
                int j1 = 0;
                while (j1 < h) 
                {
                    i = i + com.jakewharton.disklrucache.d.b(d1)[j1];
                    j1++;
                }
            } else
            {
                com.jakewharton.disklrucache.d.a(d1, null);
                for (int i1 = 0; i1 < h; i1++)
                {
                    a(d1.getCleanFile(i1));
                    a(d1.getDirtyFile(i1));
                }

                iterator.remove();
            }
        }

    }

    static boolean c(DiskLruCache disklrucache)
    {
        return disklrucache.e();
    }

    private void d()
    {
        this;
        JVM INSTR monitorenter ;
        BufferedWriter bufferedwriter;
        if (j != null)
        {
            j.close();
        }
        bufferedwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(d), Util.a));
        Iterator iterator;
        bufferedwriter.write("libcore.io.DiskLruCache");
        bufferedwriter.write("\n");
        bufferedwriter.write("1");
        bufferedwriter.write("\n");
        bufferedwriter.write(Integer.toString(f));
        bufferedwriter.write("\n");
        bufferedwriter.write(Integer.toString(h));
        bufferedwriter.write("\n");
        bufferedwriter.write("\n");
        iterator = k.values().iterator();
_L1:
        d d1;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_244;
            }
            d1 = (d)iterator.next();
            if (com.jakewharton.disklrucache.d.a(d1) == null)
            {
                break MISSING_BLOCK_LABEL_204;
            }
            bufferedwriter.write((new StringBuilder("DIRTY ")).append(com.jakewharton.disklrucache.d.c(d1)).append('\n').toString());
        } while (true);
        Exception exception1;
        exception1;
        bufferedwriter.close();
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        bufferedwriter.write((new StringBuilder("CLEAN ")).append(com.jakewharton.disklrucache.d.c(d1)).append(d1.getLengths()).append('\n').toString());
          goto _L1
        bufferedwriter.close();
        if (c.exists())
        {
            a(c, e, true);
        }
        a(d, c, false);
        e.delete();
        j = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(c, true), Util.a));
        this;
        JVM INSTR monitorexit ;
    }

    static void d(DiskLruCache disklrucache)
    {
        disklrucache.d();
    }

    static int e(DiskLruCache disklrucache)
    {
        return disklrucache.h;
    }

    private boolean e()
    {
        return l >= 2000 && l >= k.size();
    }

    static File f(DiskLruCache disklrucache)
    {
        return disklrucache.b;
    }

    private void f()
    {
        if (j == null)
        {
            throw new IllegalStateException("cache is closed");
        } else
        {
            return;
        }
    }

    private void g()
    {
        while (i > g) 
        {
            remove((String)((java.util.Map.Entry)k.entrySet().iterator().next()).getKey());
        }
    }

    public static DiskLruCache open(File file, int i1, int j1, long l1)
    {
        if (l1 <= 0L)
        {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (j1 <= 0)
        {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        if (file == null)
        {
            return null;
        }
        File file1 = new File(file, "journal.bkp");
        if (file1.exists())
        {
            File file2 = new File(file, "journal");
            DiskLruCache disklrucache;
            DiskLruCache disklrucache1;
            IOException ioexception;
            if (file2.exists())
            {
                file1.delete();
            } else
            {
                a(file1, file2, false);
            }
        }
        disklrucache = new DiskLruCache(file, i1, j1, l1);
        if (!disklrucache.c.exists())
        {
            break MISSING_BLOCK_LABEL_163;
        }
        disklrucache.b();
        disklrucache.c();
        disklrucache.j = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(disklrucache.c, true), Util.a));
        return disklrucache;
        ioexception;
        disklrucache.delete();
        file.mkdirs();
        disklrucache1 = new DiskLruCache(file, i1, j1, l1);
        disklrucache1.d();
        return disklrucache1;
    }

    public final void close()
    {
        this;
        JVM INSTR monitorenter ;
        Writer writer = j;
        if (writer != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Iterator iterator = (new ArrayList(k.values())).iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            d d1 = (d)iterator.next();
            if (com.jakewharton.disklrucache.d.a(d1) != null)
            {
                com.jakewharton.disklrucache.d.a(d1).abort();
            }
        } while (true);
        break MISSING_BLOCK_LABEL_76;
        Exception exception;
        exception;
        throw exception;
        g();
        j.close();
        j = null;
          goto _L1
    }

    public final void delete()
    {
        close();
        Util.a(b);
    }

    public final Editor edit(String s)
    {
        return a(s, -1L);
    }

    public final void flush()
    {
        this;
        JVM INSTR monitorenter ;
        f();
        g();
        j.flush();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final Snapshot get(String s)
    {
        this;
        JVM INSTR monitorenter ;
        d d1;
        f();
        a(s);
        d1 = (d)k.get(s);
        if (d1 != null) goto _L2; else goto _L1
_L1:
        Snapshot snapshot = null;
_L8:
        this;
        JVM INSTR monitorexit ;
        return snapshot;
_L2:
        if (!com.jakewharton.disklrucache.d.d(d1))
        {
            snapshot = null;
            continue; /* Loop/switch isn't completed */
        }
        InputStream ainputstream[] = new InputStream[h];
        int i1 = 0;
_L4:
        if (i1 >= h)
        {
            break; /* Loop/switch isn't completed */
        }
        ainputstream[i1] = new FileInputStream(d1.getCleanFile(i1));
        i1++;
        if (true) goto _L4; else goto _L3
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        int j1 = 0;
_L6:
        if (j1 >= h || ainputstream[j1] == null)
        {
            break; /* Loop/switch isn't completed */
        }
        Util.a(ainputstream[j1]);
        j1++;
        if (true) goto _L6; else goto _L5
_L3:
        l = 1 + l;
        j.append((new StringBuilder("READ ")).append(s).append('\n').toString());
        if (e())
        {
            n.submit(o);
        }
        snapshot = new Snapshot(s, com.jakewharton.disklrucache.d.e(d1), ainputstream, com.jakewharton.disklrucache.d.b(d1), (byte)0);
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
_L5:
        snapshot = null;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final File getDirectory()
    {
        return b;
    }

    public final long getMaxSize()
    {
        this;
        JVM INSTR monitorenter ;
        long l1 = g;
        this;
        JVM INSTR monitorexit ;
        return l1;
        Exception exception;
        exception;
        throw exception;
    }

    public final boolean isClosed()
    {
        this;
        JVM INSTR monitorenter ;
        Writer writer = j;
        boolean flag;
        if (writer == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public final boolean remove(String s)
    {
        this;
        JVM INSTR monitorenter ;
        d d1;
        f();
        a(s);
        d1 = (d)k.get(s);
        if (d1 == null) goto _L2; else goto _L1
_L1:
        Editor editor = com.jakewharton.disklrucache.d.a(d1);
        int i1 = 0;
        if (editor == null) goto _L3; else goto _L2
_L2:
        boolean flag = false;
_L7:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L5:
        i = i - com.jakewharton.disklrucache.d.b(d1)[i1];
        com.jakewharton.disklrucache.d.b(d1)[i1] = 0L;
        i1++;
_L3:
        File file;
        if (i1 >= h)
        {
            break MISSING_BLOCK_LABEL_139;
        }
        file = d1.getCleanFile(i1);
        if (!file.exists() || file.delete()) goto _L5; else goto _L4
_L4:
        throw new IOException((new StringBuilder("failed to delete ")).append(file).toString());
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        l = 1 + l;
        j.append((new StringBuilder("REMOVE ")).append(s).append('\n').toString());
        k.remove(s);
        if (e())
        {
            n.submit(o);
        }
        flag = true;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final void setMaxSize(long l1)
    {
        this;
        JVM INSTR monitorenter ;
        g = l1;
        n.submit(o);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final long size()
    {
        this;
        JVM INSTR monitorenter ;
        long l1 = i;
        this;
        JVM INSTR monitorexit ;
        return l1;
        Exception exception;
        exception;
        throw exception;
    }


    private class Editor
    {

        private final d a;
        private final boolean b[];
        private boolean c;
        private boolean d;
        private DiskLruCache e;

        static d a(Editor editor)
        {
            return editor.a;
        }

        static boolean a(Editor editor, boolean flag)
        {
            editor.c = true;
            return true;
        }

        static boolean[] b(Editor editor)
        {
            return editor.b;
        }

        public final void abort()
        {
            DiskLruCache.a(e, this, false);
        }

        public final void abortUnlessCommitted()
        {
            if (d)
            {
                break MISSING_BLOCK_LABEL_11;
            }
            abort();
            return;
            IOException ioexception;
            ioexception;
        }

        public final void commit()
        {
            if (c)
            {
                DiskLruCache.a(e, this, false);
                e.remove(com.jakewharton.disklrucache.d.c(a));
            } else
            {
                DiskLruCache.a(e, this, true);
            }
            d = true;
        }

        public final String getString(int i1)
        {
            InputStream inputstream = newInputStream(i1);
            if (inputstream != null)
            {
                return DiskLruCache.a(inputstream);
            } else
            {
                return null;
            }
        }

        public final InputStream newInputStream(int i1)
        {
            synchronized (e)
            {
                if (com.jakewharton.disklrucache.d.a(a) != this)
                {
                    throw new IllegalStateException();
                }
                break MISSING_BLOCK_LABEL_31;
            }
            if (com.jakewharton.disklrucache.d.d(a))
            {
                break MISSING_BLOCK_LABEL_45;
            }
            disklrucache;
            JVM INSTR monitorexit ;
            return null;
            FileInputStream fileinputstream;
            try
            {
                fileinputstream = new FileInputStream(a.getCleanFile(i1));
            }
            catch (FileNotFoundException filenotfoundexception)
            {
                return null;
            }
            disklrucache;
            JVM INSTR monitorexit ;
            return fileinputstream;
        }

        public final OutputStream newOutputStream(int i1)
        {
            synchronized (e)
            {
                if (com.jakewharton.disklrucache.d.a(a) != this)
                {
                    throw new IllegalStateException();
                }
                break MISSING_BLOCK_LABEL_31;
            }
            File file;
            if (!com.jakewharton.disklrucache.d.d(a))
            {
                b[i1] = true;
            }
            file = a.getDirtyFile(i1);
            FileOutputStream fileoutputstream = new FileOutputStream(file);
            FileOutputStream fileoutputstream1 = fileoutputstream;
_L1:
            c c1 = new c(this, fileoutputstream1, (byte)0);
            disklrucache;
            JVM INSTR monitorexit ;
            return c1;
            FileNotFoundException filenotfoundexception;
            filenotfoundexception;
            DiskLruCache.f(e).mkdirs();
            FileOutputStream fileoutputstream2 = new FileOutputStream(file);
            fileoutputstream1 = fileoutputstream2;
              goto _L1
            FileNotFoundException filenotfoundexception1;
            filenotfoundexception1;
            OutputStream outputstream = DiskLruCache.a();
            disklrucache;
            JVM INSTR monitorexit ;
            return outputstream;
        }

        public final void set(int i1, String s)
        {
            OutputStreamWriter outputstreamwriter = new OutputStreamWriter(newOutputStream(i1), Util.b);
            outputstreamwriter.write(s);
            Util.a(outputstreamwriter);
            return;
            Exception exception;
            exception;
            outputstreamwriter = null;
_L2:
            Util.a(outputstreamwriter);
            throw exception;
            exception;
            if (true) goto _L2; else goto _L1
_L1:
        }

        private Editor(d d1)
        {
            e = DiskLruCache.this;
            super();
            a = d1;
            boolean aflag[];
            if (com.jakewharton.disklrucache.d.d(d1))
            {
                aflag = null;
            } else
            {
                aflag = new boolean[DiskLruCache.e(DiskLruCache.this)];
            }
            b = aflag;
        }

        Editor(d d1, byte byte0)
        {
            this(d1);
        }
    }


    private class Snapshot
        implements Closeable
    {

        private final String a;
        private final long b;
        private final InputStream c[];
        private final long d[];
        private DiskLruCache e;

        public final void close()
        {
            InputStream ainputstream[] = c;
            int i1 = ainputstream.length;
            for (int j1 = 0; j1 < i1; j1++)
            {
                Util.a(ainputstream[j1]);
            }

        }

        public final Editor edit()
        {
            return DiskLruCache.a(e, a, b);
        }

        public final InputStream getInputStream(int i1)
        {
            return c[i1];
        }

        public final long getLength(int i1)
        {
            return d[i1];
        }

        public final String getString(int i1)
        {
            return DiskLruCache.a(getInputStream(i1));
        }

        private Snapshot(String s, long l1, InputStream ainputstream[], long al[])
        {
            e = DiskLruCache.this;
            super();
            a = s;
            b = l1;
            c = ainputstream;
            d = al;
        }

        Snapshot(String s, long l1, InputStream ainputstream[], long al[], byte byte0)
        {
            this(s, l1, ainputstream, al);
        }
    }

}
