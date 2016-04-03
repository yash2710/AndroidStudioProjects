// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.print;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.print.PrintManager;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

class PrintHelperKitkat
{

    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    private static final String LOG_TAG = "PrintHelperKitkat";
    private static final int MAX_PRINT_SIZE = 3500;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    int mColorMode;
    final Context mContext;
    android.graphics.BitmapFactory.Options mDecodeOptions;
    private final Object mLock = new Object();
    int mOrientation;
    int mScaleMode;

    PrintHelperKitkat(Context context)
    {
        mDecodeOptions = null;
        mScaleMode = 2;
        mColorMode = 2;
        mOrientation = 1;
        mContext = context;
    }

    private Matrix getMatrix(int i, int j, RectF rectf, int k)
    {
        Matrix matrix = new Matrix();
        float f = rectf.width() / (float)i;
        float f1;
        if (k == 2)
        {
            f1 = Math.max(f, rectf.height() / (float)j);
        } else
        {
            f1 = Math.min(f, rectf.height() / (float)j);
        }
        matrix.postScale(f1, f1);
        matrix.postTranslate((rectf.width() - f1 * (float)i) / 2.0F, (rectf.height() - f1 * (float)j) / 2.0F);
        return matrix;
    }

    private Bitmap loadBitmap(Uri uri, android.graphics.BitmapFactory.Options options)
    {
        InputStream inputstream;
        inputstream = null;
        if (uri == null || mContext == null)
        {
            throw new IllegalArgumentException("bad argument to loadBitmap");
        }
        Bitmap bitmap;
        inputstream = mContext.getContentResolver().openInputStream(uri);
        bitmap = BitmapFactory.decodeStream(inputstream, null, options);
        if (inputstream != null)
        {
            try
            {
                inputstream.close();
            }
            catch (IOException ioexception1)
            {
                Log.w("PrintHelperKitkat", "close fail ", ioexception1);
                return bitmap;
            }
        }
        return bitmap;
        Exception exception;
        exception;
        if (inputstream != null)
        {
            try
            {
                inputstream.close();
            }
            catch (IOException ioexception)
            {
                Log.w("PrintHelperKitkat", "close fail ", ioexception);
            }
        }
        throw exception;
    }

    private Bitmap loadConstrainedBitmap(Uri uri, int i)
    {
        boolean flag;
        int j;
        int k;
        flag = true;
        if (i <= 0 || uri == null || mContext == null)
        {
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        }
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = flag;
        loadBitmap(uri, options);
        j = options.outWidth;
        k = options.outHeight;
        if (j > 0 && k > 0) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        for (int l = Math.max(j, k); l > i;)
        {
            l >>>= 1;
            flag <<= true;
        }

        flag;
        JVM INSTR ifle 74;
           goto _L3 _L1
_L3:
        if (Math.min(j, k) / flag <= 0) goto _L1; else goto _L4
_L4:
        android.graphics.BitmapFactory.Options options1;
        Bitmap bitmap;
        synchronized (mLock)
        {
            mDecodeOptions = new android.graphics.BitmapFactory.Options();
            mDecodeOptions.inMutable = true;
            mDecodeOptions.inSampleSize = ((flag) ? 1 : 0);
            options1 = mDecodeOptions;
        }
        bitmap = loadBitmap(uri, options1);
        synchronized (mLock)
        {
            mDecodeOptions = null;
        }
        return bitmap;
        Exception exception1;
        exception1;
        synchronized (mLock)
        {
            mDecodeOptions = null;
        }
        throw exception1;
    }

    public int getColorMode()
    {
        return mColorMode;
    }

    public int getOrientation()
    {
        return mOrientation;
    }

    public int getScaleMode()
    {
        return mScaleMode;
    }

    public void printBitmap(final String jobName, final Bitmap bitmap)
    {
        if (bitmap == null)
        {
            return;
        }
        final int fittingMode = mScaleMode;
        PrintManager printmanager = (PrintManager)mContext.getSystemService("print");
        android.print.PrintAttributes.MediaSize mediasize = android.print.PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
        if (bitmap.getWidth() > bitmap.getHeight())
        {
            mediasize = android.print.PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
        }
        PrintAttributes printattributes = (new android.print.PrintAttributes.Builder()).setMediaSize(mediasize).setColorMode(mColorMode).build();
        printmanager.print(jobName, new _cls1(), printattributes);
    }

    public void printBitmap(final String jobName, final Uri imageFile)
    {
        _cls2 _lcls2;
        PrintManager printmanager;
        android.print.PrintAttributes.Builder builder;
        _lcls2 = new _cls2();
        printmanager = (PrintManager)mContext.getSystemService("print");
        builder = new android.print.PrintAttributes.Builder();
        builder.setColorMode(mColorMode);
        if (mOrientation != 1) goto _L2; else goto _L1
_L1:
        builder.setMediaSize(android.print.PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
_L4:
        printmanager.print(jobName, _lcls2, builder.build());
        return;
_L2:
        if (mOrientation == 2)
        {
            builder.setMediaSize(android.print.PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void setColorMode(int i)
    {
        mColorMode = i;
    }

    public void setOrientation(int i)
    {
        mOrientation = i;
    }

    public void setScaleMode(int i)
    {
        mScaleMode = i;
    }




    private class _cls1 extends PrintDocumentAdapter
    {

        private PrintAttributes mAttributes;
        final PrintHelperKitkat this$0;
        final Bitmap val$bitmap;
        final int val$fittingMode;
        final String val$jobName;

        public void onLayout(PrintAttributes printattributes, PrintAttributes printattributes1, CancellationSignal cancellationsignal, android.print.PrintDocumentAdapter.LayoutResultCallback layoutresultcallback, Bundle bundle)
        {
            boolean flag = true;
            mAttributes = printattributes1;
            android.print.PrintDocumentInfo printdocumentinfo = (new android.print.PrintDocumentInfo.Builder(jobName)).setContentType(flag).setPageCount(flag).build();
            if (printattributes1.equals(printattributes))
            {
                flag = false;
            }
            layoutresultcallback.onLayoutFinished(printdocumentinfo, flag);
        }

        public void onWrite(PageRange apagerange[], ParcelFileDescriptor parcelfiledescriptor, CancellationSignal cancellationsignal, android.print.PrintDocumentAdapter.WriteResultCallback writeresultcallback)
        {
            PrintedPdfDocument printedpdfdocument = new PrintedPdfDocument(mContext, mAttributes);
            android.graphics.pdf.PdfDocument.Page page = printedpdfdocument.startPage(1);
            RectF rectf = new RectF(page.getInfo().getContentRect());
            Matrix matrix = getMatrix(bitmap.getWidth(), bitmap.getHeight(), rectf, fittingMode);
            page.getCanvas().drawBitmap(bitmap, matrix, null);
            printedpdfdocument.finishPage(page);
            printedpdfdocument.writeTo(new FileOutputStream(parcelfiledescriptor.getFileDescriptor()));
            PageRange apagerange1[] = new PageRange[1];
            apagerange1[0] = PageRange.ALL_PAGES;
            writeresultcallback.onWriteFinished(apagerange1);
_L2:
            printedpdfdocument.close();
            if (parcelfiledescriptor == null)
            {
                break MISSING_BLOCK_LABEL_145;
            }
            parcelfiledescriptor.close();
            return;
            IOException ioexception1;
            ioexception1;
            Log.e("PrintHelperKitkat", "Error writing printed content", ioexception1);
            writeresultcallback.onWriteFailed(null);
            if (true) goto _L2; else goto _L1
_L1:
            Exception exception;
            exception;
            printedpdfdocument.close();
            IOException ioexception2;
            if (parcelfiledescriptor != null)
            {
                try
                {
                    parcelfiledescriptor.close();
                }
                catch (IOException ioexception) { }
            }
            throw exception;
            ioexception2;
        }

        _cls1()
        {
            this$0 = PrintHelperKitkat.this;
            jobName = s;
            bitmap = bitmap1;
            fittingMode = i;
            super();
        }
    }


    private class _cls2 extends PrintDocumentAdapter
    {

        AsyncTask loadBitmap;
        private PrintAttributes mAttributes;
        Bitmap mBitmap;
        final PrintHelperKitkat this$0;
        final int val$fittingMode;
        final Uri val$imageFile;
        final String val$jobName;

        private void cancelLoad()
        {
            synchronized (mLock)
            {
                if (mDecodeOptions != null)
                {
                    mDecodeOptions.requestCancelDecode();
                    mDecodeOptions = null;
                }
            }
        }

        public void onFinish()
        {
            super.onFinish();
            cancelLoad();
            loadBitmap.cancel(true);
        }

        public void onLayout(final PrintAttributes oldPrintAttributes, final PrintAttributes newPrintAttributes, final CancellationSignal cancellationSignal, final android.print.PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle)
        {
            boolean flag = true;
            if (cancellationSignal.isCanceled())
            {
                layoutResultCallback.onLayoutCancelled();
                mAttributes = newPrintAttributes;
                return;
            }
            if (mBitmap != null)
            {
                android.print.PrintDocumentInfo printdocumentinfo = (new android.print.PrintDocumentInfo.Builder(jobName)).setContentType(flag).setPageCount(flag).build();
                if (newPrintAttributes.equals(oldPrintAttributes))
                {
                    flag = false;
                }
                layoutResultCallback.onLayoutFinished(printdocumentinfo, flag);
                return;
            } else
            {
                class _cls1 extends AsyncTask
                {

                    final _cls2 this$1;
                    final CancellationSignal val$cancellationSignal;
                    final android.print.PrintDocumentAdapter.LayoutResultCallback val$layoutResultCallback;
                    final PrintAttributes val$newPrintAttributes;
                    final PrintAttributes val$oldPrintAttributes;

                    protected transient Bitmap doInBackground(Uri auri[])
                    {
                        Bitmap bitmap;
                        try
                        {
                            bitmap = loadConstrainedBitmap(imageFile, 3500);
                        }
                        catch (FileNotFoundException filenotfoundexception)
                        {
                            return null;
                        }
                        return bitmap;
                    }

                    protected volatile Object doInBackground(Object aobj[])
                    {
                        return doInBackground((Uri[])aobj);
                    }

                    protected void onCancelled(Bitmap bitmap)
                    {
                        layoutResultCallback.onLayoutCancelled();
                    }

                    protected volatile void onCancelled(Object obj)
                    {
                        onCancelled((Bitmap)obj);
                    }

                    protected void onPostExecute(Bitmap bitmap)
                    {
                        boolean flag1 = true;
                        super.onPostExecute(bitmap);
                        mBitmap = bitmap;
                        if (bitmap != null)
                        {
                            android.print.PrintDocumentInfo printdocumentinfo1 = (new android.print.PrintDocumentInfo.Builder(jobName)).setContentType(flag1).setPageCount(flag1).build();
                            if (newPrintAttributes.equals(oldPrintAttributes))
                            {
                                flag1 = false;
                            }
                            layoutResultCallback.onLayoutFinished(printdocumentinfo1, flag1);
                            return;
                        } else
                        {
                            layoutResultCallback.onLayoutFailed(null);
                            return;
                        }
                    }

                    protected volatile void onPostExecute(Object obj)
                    {
                        onPostExecute((Bitmap)obj);
                    }

                    protected void onPreExecute()
                    {
                        class _cls1
                            implements android.os.CancellationSignal.OnCancelListener
                        {

                            final _cls1 this$2;

                            public void onCancel()
                            {
                                cancelLoad();
                                cancel(false);
                            }

                                _cls1()
                                {
                                    this$2 = _cls1.this;
                                    super();
                                }
                        }

                        cancellationSignal.setOnCancelListener(new _cls1());
                    }

                _cls1()
                {
                    this$1 = _cls2.this;
                    cancellationSignal = cancellationsignal;
                    newPrintAttributes = printattributes;
                    oldPrintAttributes = printattributes1;
                    layoutResultCallback = layoutresultcallback;
                    super();
                }
                }

                loadBitmap = new _cls1();
                loadBitmap.execute(new Uri[0]);
                mAttributes = newPrintAttributes;
                return;
            }
        }

        public void onWrite(PageRange apagerange[], ParcelFileDescriptor parcelfiledescriptor, CancellationSignal cancellationsignal, android.print.PrintDocumentAdapter.WriteResultCallback writeresultcallback)
        {
            PrintedPdfDocument printedpdfdocument = new PrintedPdfDocument(mContext, mAttributes);
            android.graphics.pdf.PdfDocument.Page page = printedpdfdocument.startPage(1);
            RectF rectf = new RectF(page.getInfo().getContentRect());
            Matrix matrix = getMatrix(mBitmap.getWidth(), mBitmap.getHeight(), rectf, fittingMode);
            page.getCanvas().drawBitmap(mBitmap, matrix, null);
            printedpdfdocument.finishPage(page);
            printedpdfdocument.writeTo(new FileOutputStream(parcelfiledescriptor.getFileDescriptor()));
            PageRange apagerange1[] = new PageRange[1];
            apagerange1[0] = PageRange.ALL_PAGES;
            writeresultcallback.onWriteFinished(apagerange1);
_L2:
            printedpdfdocument.close();
            if (parcelfiledescriptor == null)
            {
                break MISSING_BLOCK_LABEL_145;
            }
            parcelfiledescriptor.close();
            return;
            IOException ioexception1;
            ioexception1;
            Log.e("PrintHelperKitkat", "Error writing printed content", ioexception1);
            writeresultcallback.onWriteFailed(null);
            if (true) goto _L2; else goto _L1
_L1:
            Exception exception;
            exception;
            printedpdfdocument.close();
            IOException ioexception2;
            if (parcelfiledescriptor != null)
            {
                try
                {
                    parcelfiledescriptor.close();
                }
                catch (IOException ioexception) { }
            }
            throw exception;
            ioexception2;
        }


        _cls2()
        {
            this$0 = PrintHelperKitkat.this;
            jobName = s;
            imageFile = uri;
            fittingMode = i;
            super();
            mBitmap = null;
        }
    }

}
