// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package com.crashlytics.android.internal:
//            X, Y, au, W

public class aq
    implements Closeable
{

    private static final Logger a = Logger.getLogger(com/crashlytics/android/internal/aq.getName());
    private final RandomAccessFile b;
    private int c;
    private int d;
    private X e;
    private X f;
    private final byte g[];

    public aq(File file)
    {
        File file1;
        RandomAccessFile randomaccessfile;
        g = new byte[16];
        if (file.exists())
        {
            break MISSING_BLOCK_LABEL_135;
        }
        file1 = new File((new StringBuilder()).append(file.getPath()).append(".tmp").toString());
        randomaccessfile = a(file1);
        randomaccessfile.setLength(4096L);
        randomaccessfile.seek(0L);
        byte abyte0[] = new byte[16];
        a(abyte0, new int[] {
            4096, 0, 0, 0
        });
        randomaccessfile.write(abyte0);
        randomaccessfile.close();
        if (!file1.renameTo(file))
        {
            throw new IOException("Rename failed!");
        }
        break MISSING_BLOCK_LABEL_135;
        Exception exception;
        exception;
        randomaccessfile.close();
        throw exception;
        b = a(file);
        b.seek(0L);
        b.readFully(g);
        c = a(g, 0);
        if ((long)c > b.length())
        {
            throw new IOException((new StringBuilder("File is truncated. Expected length: ")).append(c).append(", Actual length: ").append(b.length()).toString());
        } else
        {
            d = a(g, 4);
            int i = a(g, 8);
            int j = a(g, 12);
            e = a(i);
            f = a(j);
            return;
        }
    }

    static int a(aq aq1, int i)
    {
        return aq1.b(i);
    }

    private static int a(byte abyte0[], int i)
    {
        return ((0xff & abyte0[i]) << 24) + ((0xff & abyte0[i + 1]) << 16) + ((0xff & abyte0[i + 2]) << 8) + (0xff & abyte0[i + 3]);
    }

    private X a(int i)
    {
        if (i == 0)
        {
            return X.a;
        } else
        {
            b.seek(i);
            return new X(i, b.readInt());
        }
    }

    static RandomAccessFile a(aq aq1)
    {
        return aq1.b;
    }

    private static RandomAccessFile a(File file)
    {
        return new RandomAccessFile(file, "rwd");
    }

    static Object a(Object obj, String s)
    {
        return b(obj, s);
    }

    private void a(int i, int j, int k, int l)
    {
        a(g, new int[] {
            i, j, k, l
        });
        b.seek(0L);
        b.write(g);
    }

    private void a(int i, byte abyte0[], int j, int k)
    {
        int l = b(i);
        if (l + k <= c)
        {
            b.seek(l);
            b.write(abyte0, 0, k);
            return;
        } else
        {
            int i1 = c - l;
            b.seek(l);
            b.write(abyte0, 0, i1);
            b.seek(16L);
            b.write(abyte0, i1 + 0, k - i1);
            return;
        }
    }

    static void a(aq aq1, int i, byte abyte0[], int j, int k)
    {
        aq1.b(i, abyte0, j, k);
    }

    private static void a(byte abyte0[], int i, int j)
    {
        abyte0[i] = (byte)(j >> 24);
        abyte0[i + 1] = (byte)(j >> 16);
        abyte0[i + 2] = (byte)(j >> 8);
        abyte0[i + 3] = (byte)j;
    }

    private static transient void a(byte abyte0[], int ai[])
    {
        int i = 0;
        int j = 0;
        for (; i < 4; i++)
        {
            a(abyte0, j, ai[i]);
            j += 4;
        }

    }

    private int b(int i)
    {
        if (i < c)
        {
            return i;
        } else
        {
            return (i + 16) - c;
        }
    }

    private static Object b(Object obj, String s)
    {
        if (obj == null)
        {
            throw new NullPointerException(s);
        } else
        {
            return obj;
        }
    }

    private void b(int i, byte abyte0[], int j, int k)
    {
        int l = b(i);
        if (l + k <= c)
        {
            b.seek(l);
            b.readFully(abyte0, j, k);
            return;
        } else
        {
            int i1 = c - l;
            b.seek(l);
            b.readFully(abyte0, j, i1);
            b.seek(16L);
            b.readFully(abyte0, j + i1, k - i1);
            return;
        }
    }

    private void b(byte abyte0[], int i)
    {
        this;
        JVM INSTR monitorenter ;
        b(abyte0, "buffer");
        if ((i | 0) < 0)
        {
            break MISSING_BLOCK_LABEL_21;
        }
        if (i <= abyte0.length)
        {
            break MISSING_BLOCK_LABEL_34;
        }
        throw new IndexOutOfBoundsException();
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        boolean flag;
        c(i);
        flag = b();
        if (!flag) goto _L2; else goto _L1
_L1:
        int j = 16;
_L3:
        X x;
        x = new X(j, i);
        a(g, 0, i);
        a(x.b, g, 0, 4);
        a(4 + x.b, abyte0, 0, i);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_195;
        }
        int k = x.b;
_L4:
        a(c, 1 + d, k, x.b);
        f = x;
        d = 1 + d;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_166;
        }
        e = f;
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        j = b(4 + f.b + f.c);
          goto _L3
        k = e.b;
          goto _L4
    }

    private void c(int i)
    {
        int j = i + 4;
        int k = c - a();
        if (k >= j)
        {
            return;
        }
        int l = c;
        do
        {
            k += l;
            int i1 = l << 1;
            if (k >= j)
            {
                d(i1);
                int j1 = b(4 + f.b + f.c);
                if (j1 < e.b)
                {
                    FileChannel filechannel = b.getChannel();
                    filechannel.position(c);
                    int l1 = j1 - 4;
                    if (filechannel.transferTo(16L, l1, filechannel) != (long)l1)
                    {
                        throw new AssertionError("Copied insufficient number of bytes!");
                    }
                }
                if (f.b < e.b)
                {
                    int k1 = -16 + (c + f.b);
                    a(i1, d, e.b, k1);
                    f = new X(k1, f.c);
                } else
                {
                    a(i1, d, e.b, f.b);
                }
                c = i1;
                return;
            }
            l = i1;
        } while (true);
    }

    private void d()
    {
        this;
        JVM INSTR monitorenter ;
        a(4096, 0, 0, 0);
        d = 0;
        e = X.a;
        f = X.a;
        if (c > 4096)
        {
            d(4096);
        }
        c = 4096;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void d(int i)
    {
        b.setLength(i);
        b.getChannel().force(true);
    }

    public final int a()
    {
        if (d == 0)
        {
            return 16;
        }
        if (f.b >= e.b)
        {
            return 16 + (4 + (f.b - e.b) + f.c);
        } else
        {
            return (4 + f.b + f.c + c) - e.b;
        }
    }

    public final void a(au au1)
    {
        int i = 0;
        this;
        JVM INSTR monitorenter ;
        int j = e.b;
_L1:
        int k;
        if (i >= d)
        {
            break MISSING_BLOCK_LABEL_80;
        }
        X x = a(j);
        au1.a(new Y(this, x, (byte)0), x.c);
        k = b(4 + x.b + x.c);
        j = k;
        i++;
          goto _L1
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void a(byte abyte0[])
    {
        b(abyte0, abyte0.length);
    }

    public final boolean a(int i, int j)
    {
        return i + (4 + a()) <= j;
    }

    public final boolean b()
    {
        this;
        JVM INSTR monitorenter ;
        int i = d;
        boolean flag;
        if (i == 0)
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

    public final void c()
    {
        this;
        JVM INSTR monitorenter ;
        if (b())
        {
            throw new NoSuchElementException();
        }
        break MISSING_BLOCK_LABEL_22;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if (d != 1)
        {
            break MISSING_BLOCK_LABEL_37;
        }
        d();
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        int i = b(4 + e.b + e.c);
        b(i, g, 0, 4);
        int j = a(g, 0);
        a(c, -1 + d, i, f.b);
        d = -1 + d;
        e = new X(i, j);
          goto _L1
    }

    public void close()
    {
        this;
        JVM INSTR monitorenter ;
        b.close();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getClass().getSimpleName()).append('[');
        stringbuilder.append("fileLength=").append(c);
        stringbuilder.append(", size=").append(d);
        stringbuilder.append(", first=").append(e);
        stringbuilder.append(", last=").append(f);
        stringbuilder.append(", element lengths=[");
        try
        {
            a(new W(stringbuilder));
        }
        catch (IOException ioexception)
        {
            a.log(Level.WARNING, "read error", ioexception);
        }
        stringbuilder.append("]]");
        return stringbuilder.toString();
    }

}
