// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.util;

import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AtomicFile
{

    private final File mBackupName;
    private final File mBaseName;

    public AtomicFile(File file)
    {
        mBaseName = file;
        mBackupName = new File((new StringBuilder()).append(file.getPath()).append(".bak").toString());
    }

    static boolean sync(FileOutputStream fileoutputstream)
    {
        if (fileoutputstream != null)
        {
            try
            {
                fileoutputstream.getFD().sync();
            }
            catch (IOException ioexception)
            {
                return false;
            }
        }
        return true;
    }

    public void delete()
    {
        mBaseName.delete();
        mBackupName.delete();
    }

    public void failWrite(FileOutputStream fileoutputstream)
    {
        if (fileoutputstream == null)
        {
            break MISSING_BLOCK_LABEL_33;
        }
        sync(fileoutputstream);
        fileoutputstream.close();
        mBaseName.delete();
        mBackupName.renameTo(mBaseName);
        return;
        IOException ioexception;
        ioexception;
        Log.w("AtomicFile", "failWrite: Got exception:", ioexception);
        return;
    }

    public void finishWrite(FileOutputStream fileoutputstream)
    {
        if (fileoutputstream == null)
        {
            break MISSING_BLOCK_LABEL_21;
        }
        sync(fileoutputstream);
        fileoutputstream.close();
        mBackupName.delete();
        return;
        IOException ioexception;
        ioexception;
        Log.w("AtomicFile", "finishWrite: Got exception:", ioexception);
        return;
    }

    public File getBaseFile()
    {
        return mBaseName;
    }

    public FileInputStream openRead()
    {
        if (mBackupName.exists())
        {
            mBaseName.delete();
            mBackupName.renameTo(mBaseName);
        }
        return new FileInputStream(mBaseName);
    }

    public byte[] readFully()
    {
        int i;
        FileInputStream fileinputstream;
        i = 0;
        fileinputstream = openRead();
        byte abyte0[] = new byte[fileinputstream.available()];
_L1:
        int j = fileinputstream.read(abyte0, i, abyte0.length - i);
        int k;
        if (j <= 0)
        {
            fileinputstream.close();
            return abyte0;
        }
        k = j + i;
        byte abyte1[];
        int l = fileinputstream.available();
        if (l <= abyte0.length - k)
        {
            break MISSING_BLOCK_LABEL_101;
        }
        abyte1 = new byte[l + k];
        System.arraycopy(abyte0, 0, abyte1, 0, k);
_L2:
        abyte0 = abyte1;
        i = k;
          goto _L1
        Exception exception;
        exception;
        fileinputstream.close();
        throw exception;
        abyte1 = abyte0;
          goto _L2
    }

    public FileOutputStream startWrite()
    {
        FileOutputStream fileoutputstream;
        if (mBaseName.exists())
        {
            if (!mBackupName.exists())
            {
                if (!mBaseName.renameTo(mBackupName))
                {
                    Log.w("AtomicFile", (new StringBuilder("Couldn't rename file ")).append(mBaseName).append(" to backup file ").append(mBackupName).toString());
                }
            } else
            {
                mBaseName.delete();
            }
        }
        try
        {
            fileoutputstream = new FileOutputStream(mBaseName);
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            if (!mBaseName.getParentFile().mkdir())
            {
                throw new IOException((new StringBuilder("Couldn't create directory ")).append(mBaseName).toString());
            }
            FileOutputStream fileoutputstream1;
            try
            {
                fileoutputstream1 = new FileOutputStream(mBaseName);
            }
            catch (FileNotFoundException filenotfoundexception1)
            {
                throw new IOException((new StringBuilder("Couldn't create ")).append(mBaseName).toString());
            }
            return fileoutputstream1;
        }
        return fileoutputstream;
    }
}
