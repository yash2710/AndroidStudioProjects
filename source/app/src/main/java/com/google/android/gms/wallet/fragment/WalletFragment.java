// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.google.android.gms.wallet.fragment:
//            WalletFragmentOptions, WalletFragmentInitParams

public final class WalletFragment extends Fragment
    implements TraceFieldInterface
{

    private final Fragment Mj = this;
    private WalletFragmentOptions akk;
    private WalletFragmentInitParams akl;
    private MaskedWalletRequest akm;
    private MaskedWallet akn;
    private Boolean ako;
    private b akt;
    private final com.google.android.gms.dynamic.b aku = com.google.android.gms.dynamic.b.a(this);
    private final c akv = new c(null);
    private a akw;
    private boolean mCreated;

    public WalletFragment()
    {
        mCreated = false;
        akw = new a();
    }

    static Fragment a(WalletFragment walletfragment)
    {
        return walletfragment.Mj;
    }

    static MaskedWallet a(WalletFragment walletfragment, MaskedWallet maskedwallet)
    {
        walletfragment.akn = maskedwallet;
        return maskedwallet;
    }

    static MaskedWalletRequest a(WalletFragment walletfragment, MaskedWalletRequest maskedwalletrequest)
    {
        walletfragment.akm = maskedwalletrequest;
        return maskedwalletrequest;
    }

    static b a(WalletFragment walletfragment, b b1)
    {
        walletfragment.akt = b1;
        return b1;
    }

    static WalletFragmentInitParams a(WalletFragment walletfragment, WalletFragmentInitParams walletfragmentinitparams)
    {
        walletfragment.akl = walletfragmentinitparams;
        return walletfragmentinitparams;
    }

    static WalletFragmentOptions a(WalletFragment walletfragment, WalletFragmentOptions walletfragmentoptions)
    {
        walletfragment.akk = walletfragmentoptions;
        return walletfragmentoptions;
    }

    static Boolean a(WalletFragment walletfragment, Boolean boolean1)
    {
        walletfragment.ako = boolean1;
        return boolean1;
    }

    static b b(WalletFragment walletfragment)
    {
        return walletfragment.akt;
    }

    static boolean c(WalletFragment walletfragment)
    {
        return walletfragment.mCreated;
    }

    static b d(WalletFragment walletfragment)
    {
        return walletfragment.aku;
    }

    static WalletFragmentOptions e(WalletFragment walletfragment)
    {
        return walletfragment.akk;
    }

    static a f(WalletFragment walletfragment)
    {
        return walletfragment.akw;
    }

    static WalletFragmentInitParams g(WalletFragment walletfragment)
    {
        return walletfragment.akl;
    }

    static MaskedWalletRequest h(WalletFragment walletfragment)
    {
        return walletfragment.akm;
    }

    static MaskedWallet i(WalletFragment walletfragment)
    {
        return walletfragment.akn;
    }

    static Boolean j(WalletFragment walletfragment)
    {
        return walletfragment.ako;
    }

    public static WalletFragment newInstance(WalletFragmentOptions walletfragmentoptions)
    {
        WalletFragment walletfragment = new WalletFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extraWalletFragmentOptions", walletfragmentoptions);
        walletfragment.Mj.setArguments(bundle);
        return walletfragment;
    }

    public final int getState()
    {
        if (akt != null)
        {
            return com.google.android.gms.wallet.fragment.b.a(akt);
        } else
        {
            return 0;
        }
    }

    public final void initialize(WalletFragmentInitParams walletfragmentinitparams)
    {
        if (akt != null)
        {
            com.google.android.gms.wallet.fragment.b.a(akt, walletfragmentinitparams);
            akl = null;
        } else
        if (akl == null)
        {
            akl = walletfragmentinitparams;
            if (akm != null)
            {
                Log.w("WalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }
            if (akn != null)
            {
                Log.w("WalletFragment", "updateMaskedWallet() was called before initialize()");
                return;
            }
        } else
        {
            Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
            return;
        }
    }

    public final void onActivityResult(int k, int l, Intent intent)
    {
        super.onActivityResult(k, l, intent);
        if (akt != null)
        {
            com.google.android.gms.wallet.fragment.b.a(akt, k, l, intent);
        }
    }

    public final void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("WalletFragment");
        TraceMachine.enterMethod(_nr_trace, "WalletFragment#onCreate", null);
_L1:
        super.onCreate(bundle);
        NoSuchFieldError nosuchfielderror;
        if (bundle != null)
        {
            bundle.setClassLoader(com/google/android/gms/wallet/fragment/WalletFragmentOptions.getClassLoader());
            WalletFragmentInitParams walletfragmentinitparams = (WalletFragmentInitParams)bundle.getParcelable("walletFragmentInitParams");
            if (walletfragmentinitparams != null)
            {
                if (akl != null)
                {
                    Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
                }
                akl = walletfragmentinitparams;
            }
            if (akm == null)
            {
                akm = (MaskedWalletRequest)bundle.getParcelable("maskedWalletRequest");
            }
            if (akn == null)
            {
                akn = (MaskedWallet)bundle.getParcelable("maskedWallet");
            }
            if (bundle.containsKey("walletFragmentOptions"))
            {
                akk = (WalletFragmentOptions)bundle.getParcelable("walletFragmentOptions");
            }
            if (bundle.containsKey("enabled"))
            {
                ako = Boolean.valueOf(bundle.getBoolean("enabled"));
            }
        } else
        if (Mj.getArguments() != null)
        {
            WalletFragmentOptions walletfragmentoptions = (WalletFragmentOptions)Mj.getArguments().getParcelable("extraWalletFragmentOptions");
            if (walletfragmentoptions != null)
            {
                walletfragmentoptions.Q(Mj.getActivity());
                akk = walletfragmentoptions;
            }
        }
        mCreated = true;
        akv.onCreate(bundle);
        TraceMachine.exitMethod();
        return;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "WalletFragment#onCreate", null);
          goto _L1
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        TraceMachine.enterMethod(_nr_trace, "WalletFragment#onCreateView", null);
_L1:
        View view = akv.onCreateView(layoutinflater, viewgroup, bundle);
        TraceMachine.exitMethod();
        return view;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "WalletFragment#onCreateView", null);
          goto _L1
    }

    public final void onDestroy()
    {
        super.onDestroy();
        mCreated = false;
    }

    public final void onInflate(Activity activity, AttributeSet attributeset, Bundle bundle)
    {
        super.onInflate(activity, attributeset, bundle);
        if (akk == null)
        {
            akk = com.google.android.gms.wallet.fragment.WalletFragmentOptions.a(activity, attributeset);
        }
        Bundle bundle1 = new Bundle();
        bundle1.putParcelable("attrKeyWalletFragmentOptions", akk);
        akv.onInflate(activity, bundle1, bundle);
    }

    public final void onPause()
    {
        super.onPause();
        akv.onPause();
    }

    public final void onResume()
    {
        super.onResume();
        akv.onResume();
        FragmentManager fragmentmanager = Mj.getActivity().getFragmentManager();
        Fragment fragment = fragmentmanager.findFragmentByTag("GooglePlayServicesErrorDialog");
        if (fragment != null)
        {
            fragmentmanager.beginTransaction().remove(fragment).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(Mj.getActivity()), Mj.getActivity(), -1);
        }
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.setClassLoader(com/google/android/gms/wallet/fragment/WalletFragmentOptions.getClassLoader());
        akv.onSaveInstanceState(bundle);
        if (akl != null)
        {
            bundle.putParcelable("walletFragmentInitParams", akl);
            akl = null;
        }
        if (akm != null)
        {
            bundle.putParcelable("maskedWalletRequest", akm);
            akm = null;
        }
        if (akn != null)
        {
            bundle.putParcelable("maskedWallet", akn);
            akn = null;
        }
        if (akk != null)
        {
            bundle.putParcelable("walletFragmentOptions", akk);
            akk = null;
        }
        if (ako != null)
        {
            bundle.putBoolean("enabled", ako.booleanValue());
            ako = null;
        }
    }

    public final void onStart()
    {
        ApplicationStateMonitor.getInstance().activityStarted();
        super.onStart();
        akv.onStart();
    }

    public final void onStop()
    {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        akv.onStop();
    }

    public final void setEnabled(boolean flag)
    {
        if (akt != null)
        {
            com.google.android.gms.wallet.fragment.b.a(akt, flag);
            ako = null;
            return;
        } else
        {
            ako = Boolean.valueOf(flag);
            return;
        }
    }

    public final void setOnStateChangedListener(OnStateChangedListener onstatechangedlistener)
    {
        akw.a(onstatechangedlistener);
    }

    public final void updateMaskedWallet(MaskedWallet maskedwallet)
    {
        if (akt != null)
        {
            com.google.android.gms.wallet.fragment.b.a(akt, maskedwallet);
            akn = null;
            return;
        } else
        {
            akn = maskedwallet;
            return;
        }
    }

    public final void updateMaskedWalletRequest(MaskedWalletRequest maskedwalletrequest)
    {
        if (akt != null)
        {
            com.google.android.gms.wallet.fragment.b.a(akt, maskedwalletrequest);
            akm = null;
            return;
        } else
        {
            akm = maskedwalletrequest;
            return;
        }
    }

    private class c extends com.google.android.gms.dynamic.a
        implements android.view.View.OnClickListener
    {

        final WalletFragment akz;

        protected void a(FrameLayout framelayout)
        {
            int k = -1;
            int l = -2;
            Button button = new Button(com.google.android.gms.wallet.fragment.WalletFragment.a(akz).getActivity());
            button.setText(com.google.android.gms.R.string.wallet_buy_button_place_holder);
            if (com.google.android.gms.wallet.fragment.WalletFragment.e(akz) != null)
            {
                WalletFragmentStyle walletfragmentstyle = com.google.android.gms.wallet.fragment.WalletFragment.e(akz).getFragmentStyle();
                if (walletfragmentstyle != null)
                {
                    android.util.DisplayMetrics displaymetrics = com.google.android.gms.wallet.fragment.WalletFragment.a(akz).getResources().getDisplayMetrics();
                    k = walletfragmentstyle.a("buyButtonWidth", displaymetrics, k);
                    l = walletfragmentstyle.a("buyButtonHeight", displaymetrics, l);
                }
            }
            button.setLayoutParams(new android.view.ViewGroup.LayoutParams(k, l));
            button.setOnClickListener(this);
            framelayout.addView(button);
        }

        protected void a(f f1)
        {
            Activity activity = com.google.android.gms.wallet.fragment.WalletFragment.a(akz).getActivity();
            if (com.google.android.gms.wallet.fragment.WalletFragment.b(akz) == null && WalletFragment.c(akz) && activity != null)
            {
                try
                {
                    lk lk = ls.a(activity, WalletFragment.d(akz), com.google.android.gms.wallet.fragment.WalletFragment.e(akz), com.google.android.gms.wallet.fragment.WalletFragment.f(akz));
                    com.google.android.gms.wallet.fragment.WalletFragment.a(akz, new b(lk, null));
                    com.google.android.gms.wallet.fragment.WalletFragment.a(akz, null);
                }
                catch (GooglePlayServicesNotAvailableException googleplayservicesnotavailableexception)
                {
                    return;
                }
                f1.a(com.google.android.gms.wallet.fragment.WalletFragment.b(akz));
                if (WalletFragment.g(akz) != null)
                {
                    com.google.android.gms.wallet.fragment.b.a(com.google.android.gms.wallet.fragment.WalletFragment.b(akz), WalletFragment.g(akz));
                    com.google.android.gms.wallet.fragment.WalletFragment.a(akz, null);
                }
                if (WalletFragment.h(akz) != null)
                {
                    com.google.android.gms.wallet.fragment.b.a(com.google.android.gms.wallet.fragment.WalletFragment.b(akz), WalletFragment.h(akz));
                    com.google.android.gms.wallet.fragment.WalletFragment.a(akz, null);
                }
                if (WalletFragment.i(akz) != null)
                {
                    com.google.android.gms.wallet.fragment.b.a(com.google.android.gms.wallet.fragment.WalletFragment.b(akz), WalletFragment.i(akz));
                    com.google.android.gms.wallet.fragment.WalletFragment.a(akz, null);
                }
                if (WalletFragment.j(akz) != null)
                {
                    com.google.android.gms.wallet.fragment.b.a(com.google.android.gms.wallet.fragment.WalletFragment.b(akz), WalletFragment.j(akz).booleanValue());
                    com.google.android.gms.wallet.fragment.WalletFragment.a(akz, null);
                }
            }
        }

        public void onClick(View view)
        {
            Activity activity = com.google.android.gms.wallet.fragment.WalletFragment.a(akz).getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity), activity, -1);
        }

        private c()
        {
            akz = WalletFragment.this;
            super();
        }

        c(_cls1 _pcls1)
        {
            this();
        }
    }


    private class a extends com.google.android.gms.internal.ll.a
    {

        private OnStateChangedListener akx;
        private final WalletFragment aky;

        public void a(int k, int l, Bundle bundle)
        {
            if (akx != null)
            {
                akx.onStateChanged(aky, k, l, bundle);
            }
        }

        public void a(OnStateChangedListener onstatechangedlistener)
        {
            akx = onstatechangedlistener;
        }

        a()
        {
            aky = WalletFragment.this;
        }

        private class OnStateChangedListener
        {

            public abstract void onStateChanged(WalletFragment walletfragment, int k, int l, Bundle bundle);
        }

    }


    private class b
        implements LifecycleDelegate
    {

        private final lk akr;

        static int a(b b1)
        {
            return b1.getState();
        }

        static void a(b b1, int k, int l, Intent intent)
        {
            b1.onActivityResult(k, l, intent);
        }

        static void a(b b1, MaskedWallet maskedwallet)
        {
            b1.updateMaskedWallet(maskedwallet);
        }

        static void a(b b1, MaskedWalletRequest maskedwalletrequest)
        {
            b1.updateMaskedWalletRequest(maskedwalletrequest);
        }

        static void a(b b1, WalletFragmentInitParams walletfragmentinitparams)
        {
            b1.initialize(walletfragmentinitparams);
        }

        static void a(b b1, boolean flag)
        {
            b1.setEnabled(flag);
        }

        private int getState()
        {
            int k;
            try
            {
                k = akr.getState();
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeException(remoteexception);
            }
            return k;
        }

        private void initialize(WalletFragmentInitParams walletfragmentinitparams)
        {
            try
            {
                akr.initialize(walletfragmentinitparams);
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeException(remoteexception);
            }
        }

        private void onActivityResult(int k, int l, Intent intent)
        {
            try
            {
                akr.onActivityResult(k, l, intent);
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeException(remoteexception);
            }
        }

        private void setEnabled(boolean flag)
        {
            try
            {
                akr.setEnabled(flag);
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeException(remoteexception);
            }
        }

        private void updateMaskedWallet(MaskedWallet maskedwallet)
        {
            try
            {
                akr.updateMaskedWallet(maskedwallet);
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeException(remoteexception);
            }
        }

        private void updateMaskedWalletRequest(MaskedWalletRequest maskedwalletrequest)
        {
            try
            {
                akr.updateMaskedWalletRequest(maskedwalletrequest);
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeException(remoteexception);
            }
        }

        public void onCreate(Bundle bundle)
        {
            try
            {
                akr.onCreate(bundle);
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeException(remoteexception);
            }
        }

        public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
        {
            View view;
            try
            {
                view = (View)com.google.android.gms.dynamic.e.e(akr.onCreateView(com.google.android.gms.dynamic.e.h(layoutinflater), com.google.android.gms.dynamic.e.h(viewgroup), bundle));
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeException(remoteexception);
            }
            return view;
        }

        public void onDestroy()
        {
        }

        public void onDestroyView()
        {
        }

        public void onInflate(Activity activity, Bundle bundle, Bundle bundle1)
        {
            WalletFragmentOptions walletfragmentoptions = (WalletFragmentOptions)bundle.getParcelable("extraWalletFragmentOptions");
            try
            {
                akr.a(com.google.android.gms.dynamic.e.h(activity), walletfragmentoptions, bundle1);
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeException(remoteexception);
            }
        }

        public void onLowMemory()
        {
        }

        public void onPause()
        {
            try
            {
                akr.onPause();
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeException(remoteexception);
            }
        }

        public void onResume()
        {
            try
            {
                akr.onResume();
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeException(remoteexception);
            }
        }

        public void onSaveInstanceState(Bundle bundle)
        {
            try
            {
                akr.onSaveInstanceState(bundle);
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeException(remoteexception);
            }
        }

        public void onStart()
        {
            try
            {
                akr.onStart();
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeException(remoteexception);
            }
        }

        public void onStop()
        {
            try
            {
                akr.onStop();
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeException(remoteexception);
            }
        }

        private b(lk lk1)
        {
            akr = lk1;
        }

        b(lk lk1, _cls1 _pcls1)
        {
            this(lk1);
        }
    }

}
