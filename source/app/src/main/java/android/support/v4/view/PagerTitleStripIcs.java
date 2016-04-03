// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.widget.TextView;

class PagerTitleStripIcs
{

    PagerTitleStripIcs()
    {
    }

    public static void setSingleLineAllCaps(TextView textview)
    {
        textview.setTransformationMethod(new SingleLineAllCapsTransform(textview.getContext()));
    }

    private class SingleLineAllCapsTransform extends SingleLineTransformationMethod
    {

        private static final String TAG = "SingleLineAllCapsTransform";
        private Locale mLocale;

        public CharSequence getTransformation(CharSequence charsequence, View view)
        {
            CharSequence charsequence1 = super.getTransformation(charsequence, view);
            if (charsequence1 != null)
            {
                return charsequence1.toString().toUpperCase(mLocale);
            } else
            {
                return null;
            }
        }

        public SingleLineAllCapsTransform(Context context)
        {
            mLocale = context.getResources().getConfiguration().locale;
        }
    }

}
