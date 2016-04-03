// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.view;

import android.view.View;
import com.nineoldandroids.view.animation.AnimatorProxy;

public final class ViewHelper
{

    private ViewHelper()
    {
    }

    public static float getAlpha(View view)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            return AnimatorProxy.wrap(view).getAlpha();
        } else
        {
            return view.getAlpha();
        }
    }

    public static float getPivotX(View view)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            return AnimatorProxy.wrap(view).getPivotX();
        } else
        {
            return view.getPivotX();
        }
    }

    public static float getPivotY(View view)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            return AnimatorProxy.wrap(view).getPivotY();
        } else
        {
            return view.getPivotY();
        }
    }

    public static float getRotation(View view)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            return AnimatorProxy.wrap(view).getRotation();
        } else
        {
            return view.getRotation();
        }
    }

    public static float getRotationX(View view)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            return AnimatorProxy.wrap(view).getRotationX();
        } else
        {
            return view.getRotationX();
        }
    }

    public static float getRotationY(View view)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            return AnimatorProxy.wrap(view).getRotationY();
        } else
        {
            return view.getRotationY();
        }
    }

    public static float getScaleX(View view)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            return AnimatorProxy.wrap(view).getScaleX();
        } else
        {
            return view.getScaleX();
        }
    }

    public static float getScaleY(View view)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            return AnimatorProxy.wrap(view).getScaleY();
        } else
        {
            return view.getScaleY();
        }
    }

    public static float getScrollX(View view)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            return (float)AnimatorProxy.wrap(view).getScrollX();
        } else
        {
            return (float)view.getScrollX();
        }
    }

    public static float getScrollY(View view)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            return (float)AnimatorProxy.wrap(view).getScrollY();
        } else
        {
            return (float)view.getScrollY();
        }
    }

    public static float getTranslationX(View view)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            return AnimatorProxy.wrap(view).getTranslationX();
        } else
        {
            return view.getTranslationX();
        }
    }

    public static float getTranslationY(View view)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            return AnimatorProxy.wrap(view).getTranslationY();
        } else
        {
            return view.getTranslationY();
        }
    }

    public static float getX(View view)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            return AnimatorProxy.wrap(view).getX();
        } else
        {
            return view.getX();
        }
    }

    public static float getY(View view)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            return AnimatorProxy.wrap(view).getY();
        } else
        {
            return view.getY();
        }
    }

    public static void setAlpha(View view, float f)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            AnimatorProxy.wrap(view).setAlpha(f);
            return;
        } else
        {
            view.setAlpha(f);
            return;
        }
    }

    public static void setPivotX(View view, float f)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            AnimatorProxy.wrap(view).setPivotX(f);
            return;
        } else
        {
            view.setPivotX(f);
            return;
        }
    }

    public static void setPivotY(View view, float f)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            AnimatorProxy.wrap(view).setPivotY(f);
            return;
        } else
        {
            view.setPivotY(f);
            return;
        }
    }

    public static void setRotation(View view, float f)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            AnimatorProxy.wrap(view).setRotation(f);
            return;
        } else
        {
            view.setRotation(f);
            return;
        }
    }

    public static void setRotationX(View view, float f)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            AnimatorProxy.wrap(view).setRotationX(f);
            return;
        } else
        {
            view.setRotationX(f);
            return;
        }
    }

    public static void setRotationY(View view, float f)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            AnimatorProxy.wrap(view).setRotationY(f);
            return;
        } else
        {
            view.setRotationY(f);
            return;
        }
    }

    public static void setScaleX(View view, float f)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            AnimatorProxy.wrap(view).setScaleX(f);
            return;
        } else
        {
            view.setScaleX(f);
            return;
        }
    }

    public static void setScaleY(View view, float f)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            AnimatorProxy.wrap(view).setScaleY(f);
            return;
        } else
        {
            view.setScaleY(f);
            return;
        }
    }

    public static void setScrollX(View view, int i)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            AnimatorProxy.wrap(view).setScrollX(i);
            return;
        } else
        {
            view.setScrollX(i);
            return;
        }
    }

    public static void setScrollY(View view, int i)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            AnimatorProxy.wrap(view).setScrollY(i);
            return;
        } else
        {
            view.setScrollY(i);
            return;
        }
    }

    public static void setTranslationX(View view, float f)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            AnimatorProxy.wrap(view).setTranslationX(f);
            return;
        } else
        {
            view.setTranslationX(f);
            return;
        }
    }

    public static void setTranslationY(View view, float f)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            AnimatorProxy.wrap(view).setTranslationY(f);
            return;
        } else
        {
            view.setTranslationY(f);
            return;
        }
    }

    public static void setX(View view, float f)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            AnimatorProxy.wrap(view).setX(f);
            return;
        } else
        {
            view.setX(f);
            return;
        }
    }

    public static void setY(View view, float f)
    {
        if (AnimatorProxy.NEEDS_PROXY)
        {
            AnimatorProxy.wrap(view).setY(f);
            return;
        } else
        {
            view.setY(f);
            return;
        }
    }
}
