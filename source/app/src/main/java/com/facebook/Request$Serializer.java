// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.facebook.internal.Logger;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

// Referenced classes of package com.facebook:
//            Request

class logger
    implements ializer
{

    private boolean firstWrite;
    private final Logger logger;
    private final BufferedOutputStream outputStream;

    public transient void write(String s, Object aobj[])
    {
        if (firstWrite)
        {
            outputStream.write("--".getBytes());
            outputStream.write("3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f".getBytes());
            outputStream.write("\r\n".getBytes());
            firstWrite = false;
        }
        outputStream.write(String.format(s, aobj).getBytes());
    }

    public void writeBitmap(String s, Bitmap bitmap)
    {
        writeContentDisposition(s, s, "image/png");
        bitmap.compress(android.graphics.at.PNG, 100, outputStream);
        writeLine("", new Object[0]);
        writeRecordBoundary();
        logger.appendKeyValue((new StringBuilder("    ")).append(s).toString(), "<Image>");
    }

    public void writeBytes(String s, byte abyte0[])
    {
        writeContentDisposition(s, s, "content/unknown");
        outputStream.write(abyte0);
        writeLine("", new Object[0]);
        writeRecordBoundary();
        Logger logger1 = logger;
        String s1 = (new StringBuilder("    ")).append(s).toString();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(abyte0.length);
        logger1.appendKeyValue(s1, String.format("<Data: %d>", aobj));
    }

    public void writeContentDisposition(String s, String s1, String s2)
    {
        write("Content-Disposition: form-data; name=\"%s\"", new Object[] {
            s
        });
        if (s1 != null)
        {
            write("; filename=\"%s\"", new Object[] {
                s1
            });
        }
        writeLine("", new Object[0]);
        if (s2 != null)
        {
            writeLine("%s: %s", new Object[] {
                "Content-Type", s2
            });
        }
        writeLine("", new Object[0]);
    }

    public void writeFile(String s, ParcelFileDescriptor parcelfiledescriptor, String s1)
    {
        android.os.r.AutoCloseInputStream autocloseinputstream;
        autocloseinputstream = null;
        if (s1 == null)
        {
            s1 = "content/unknown";
        }
        writeContentDisposition(s, s, s1);
        android.os.r.AutoCloseInputStream autocloseinputstream1 = new android.os.r.AutoCloseInputStream(parcelfiledescriptor);
        BufferedInputStream bufferedinputstream = new BufferedInputStream(autocloseinputstream1);
        byte abyte0[] = new byte[8192];
        int i = 0;
_L2:
        int j = bufferedinputstream.read(abyte0);
        if (j == -1)
        {
            break; /* Loop/switch isn't completed */
        }
        outputStream.write(abyte0, 0, j);
        i += j;
        if (true) goto _L2; else goto _L1
_L1:
        bufferedinputstream.close();
        autocloseinputstream1.close();
        writeLine("", new Object[0]);
        writeRecordBoundary();
        Logger logger1 = logger;
        String s2 = (new StringBuilder("    ")).append(s).toString();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        logger1.appendKeyValue(s2, String.format("<Data: %d>", aobj));
        return;
        Exception exception;
        exception;
        bufferedinputstream = null;
_L4:
        if (bufferedinputstream != null)
        {
            bufferedinputstream.close();
        }
        if (autocloseinputstream != null)
        {
            autocloseinputstream.close();
        }
        throw exception;
        exception;
        autocloseinputstream = autocloseinputstream1;
        bufferedinputstream = null;
        continue; /* Loop/switch isn't completed */
        exception;
        autocloseinputstream = autocloseinputstream1;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void writeFile(String s, escriptorWithMimeType escriptorwithmimetype)
    {
        writeFile(s, escriptorwithmimetype.getFileDescriptor(), escriptorwithmimetype.getMimeType());
    }

    public transient void writeLine(String s, Object aobj[])
    {
        write(s, aobj);
        write("\r\n", new Object[0]);
    }

    public void writeObject(String s, Object obj)
    {
        if (Request.access$100(obj))
        {
            writeString(s, Request.access$200(obj));
            return;
        }
        if (obj instanceof Bitmap)
        {
            writeBitmap(s, (Bitmap)obj);
            return;
        }
        if (obj instanceof byte[])
        {
            writeBytes(s, (byte[])obj);
            return;
        }
        if (obj instanceof ParcelFileDescriptor)
        {
            writeFile(s, (ParcelFileDescriptor)obj, null);
            return;
        }
        if (obj instanceof escriptorWithMimeType)
        {
            writeFile(s, (escriptorWithMimeType)obj);
            return;
        } else
        {
            throw new IllegalArgumentException("value is not a supported type: String, Bitmap, byte[]");
        }
    }

    public void writeRecordBoundary()
    {
        writeLine("--%s", new Object[] {
            "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f"
        });
    }

    public void writeString(String s, String s1)
    {
        writeContentDisposition(s, null, null);
        writeLine("%s", new Object[] {
            s1
        });
        writeRecordBoundary();
        if (logger != null)
        {
            logger.appendKeyValue((new StringBuilder("    ")).append(s).toString(), s1);
        }
    }

    public escriptorWithMimeType(BufferedOutputStream bufferedoutputstream, Logger logger1)
    {
        firstWrite = true;
        outputStream = bufferedoutputstream;
        logger = logger1;
    }
}
