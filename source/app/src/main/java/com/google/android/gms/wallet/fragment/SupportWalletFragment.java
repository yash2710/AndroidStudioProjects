// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.h;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.google.android.gms.wallet.fragment:
//            WalletFragmentOptions, WalletFragmentInitParams

public final class SupportWalletFragment extends Fragment
    implements TraceFieldInterface
{

    private final Fragment FV = this;
    private b akg;
    private final h akh = com.google.android.gms.dynamic.h.a(this);
    private final c aki = new c(null);
    private a akj;
    private WalletFragmentOptions akk;
    private WalletFragmentInitParams akl;
    private MaskedWalletRequest akm;
    private MaskedWallet akn;
    private Boolean ako;
    private boolean mCreated;

    public SupportWalletFragment()
    {
        mCreated = false;
        akj = new a();
    }

    static Fragment a(SupportWalletFragment supportwalletfragment)
    {
        return supportwalletfragment.FV;
    }

    static MaskedWallet a(SupportWalletFragment supportwalletfragment, MaskedWallet maskedwallet)
    {
        supportwalletfragment.akn = maskedwallet;
        return maskedwallet;
    }

    static MaskedWalletRequest a(SupportWalletFragment supportwalletfragment, MaskedWalletRequest maskedwalletrequest)
    {
        supportwalletfragment.akm = maskedwalletrequest;
        return maskedwalletrequest;
    }

    static b a(SupportWalletFragment supportwalletfragment, b b1)
    {
        supportwalletfragment.akg = b1;
        return b1;
    }

    static WalletFragmentInitParams a(SupportWalletFragment supportwalletfragment, WalletFragmentInitParams walletfragmentinitparams)
    {
        supportwalletfragment.akl = walletfragmentinitparams;
        return walletfragmentinitparams;
    }

    static WalletFragmentOptions a(SupportWalletFragment supportwalletfragment, WalletFragmentOptions walletfragmentoptions)
    {
        supportwalletfragment.akk = walletfragmentoptions;
        return walletfragmentoptions;
    }

    static Boolean a(SupportWalletFragment supportwalletfragment, Boolean boolean1)
    {
        supportwalletfragment.ako = boolean1;
        return boolean1;
    }

    static b b(SupportWalletFragment supportwalletfragment)
    {
        return supportwalletfragment.akg;
    }

    static boolean c(SupportWalletFragment supportwalletfragment)
    {
        return supportwalletfragment.mCreated;
    }

    static h d(SupportWalletFragment supportwalletfragment)
    {
        return supportwalletfragment.akh;
    }

    static WalletFragmentOptions e(SupportWalletFragment supportwalletfragment)
    {
        return supportwalletfragment.akk;
    }

    static a f(SupportWalletFragment supportwalletfragment)
    {
        return supportwalletfragment.akj;
    }

    static WalletFragmentInitParams g(SupportWalletFragment supportwalletfragment)
    {
        return supportwalletfragment.akl;
    }

    static MaskedWalletRequest h(SupportWalletFragment supportwalletfragment)
    {
        return supportwalletfragment.akm;
    }

    static MaskedWallet i(SupportWalletFragment supportwalletfragment)
    {
        return supportwalletfragment.akn;
    }

    static Boolean j(SupportWalletFragment supportwalletfragment)
    {
        return supportwalletfragment.ako;
    }

    public static SupportWalletFragment newInstance(WalletFragmentOptions walletfragmentoptions)
    {
        SupportWalletFragment supportwalletfragment = new SupportWalletFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extraWalletFragmentOptions", walletfragmentoptions);
        supportwalletfragment.FV.setArguments(bundle);
        return supportwalletfragment;
    }

    public final int getState()
    {
        if (akg != null)
        {
            return com.google.android.gms.wallet.fragment.b.a(akg);
        } else
        {
            return 0;
        }
    }

    public final void initialize(WalletFragmentInitParams walletfragmentinitparams)
    {
        if (akg != null)
        {
            com.google.android.gms.wallet.fragment.b.a(akg, walletfragmentinitparams);
            akl = null;
        } else
        if (akl == null)
        {
            akl = walletfragmentinitparams;
            if (akm != null)
            {
                Log.w("SupportWalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }
            if (akn != null)
            {
                Log.w("SupportWalletFragment", "updateMaskedWallet() was called before initialize()");
                return;
            }
        } else
        {
            Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
            return;
        }
    }

    public final void onActivityResult(int k, int l, Intent intent)
    {
        super.onActivityResult(k, l, intent);
        if (akg != null)
        {
            com.google.android.gms.wallet.fragment.b.a(akg, k, l, intent);
        }
    }

    public final void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("SupportWalletFragment");
        TraceMachine.enterMethod(_nr_trace, "SupportWalletFragment#onCreate", null);
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
                    Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
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
        if (FV.getArguments() != null)
        {
            WalletFragmentOptions walletfragmentoptions = (WalletFragmentOptions)FV.getArguments().getParcelable("extraWalletFragmentOptions");
            if (walletfragmentoptions != null)
            {
                walletfragmentoptions.Q(FV.getActivity());
                akk = walletfragmentoptions;
            }
        }
        mCreated = true;
        aki.onCreate(bundle);
        TraceMachine.exitMethod();
        return;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "SupportWalletFragment#onCreate", null);
          goto _L1
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        TraceMachine.enterMethod(_nr_trace, "SupportWalletFragment#onCreateView", null);
_L1:
        View view = aki.onCreateView(layoutinflater, viewgroup, bundle);
        TraceMachine.exitMethod();
        return view;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "SupportWalletFragment#onCreateView", null);
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
        aki.onInflate(activity, bundle1, bundle);
    }

    public final void onPause()
    {
        super.onPause();
        aki.onPause();
    }

    public final void onResume()
    {
        super.onResume();
        aki.onResume();
        FragmentManager fragmentmanager = FV.getActivity().getSupportFragmentManager();
        Fragment fragment = fragmentmanager.findFragmentByTag("GooglePlayServicesErrorDialog");
        if (fragment != null)
        {
            fragmentmanager.beginTransaction().remove(fragment).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(FV.getActivity()), FV.getActivity(), -1);
        }
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.setClassLoader(com/google/android/gms/wallet/fragment/WalletFragmentOptions.getClassLoader());
        aki.onSaveInstanceState(bundle);
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
        aki.onStart();
    }

    public final void onStop()
    {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        aki.onStop();
    }

    public final void setEnabled(boolean flag)
    {
        if (akg != null)
        {
            com.google.android.gms.wallet.fragment.b.a(akg, flag);
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
        akj.a(onstatechangedlistener);
    }

    public final void updateMaskedWallet(MaskedWallet maskedwallet)
    {
        if (akg != null)
        {
            com.google.android.gms.wallet.fragment.b.a(akg, maskedwallet);
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
        if (akg != null)
        {
            com.google.android.gms.wallet.fragment.b.a(akg, maskedwalletrequest);
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

        final SupportWalletFragment aks;

        protected void a(FrameLayout framelayout)
        {
            int k = -1;
            int l = -2;
            Button button = new Button(com.google.android.gms.wallet.fragment.SupportWalletFragment.a(aks).getActivity());
            button.setText(com.google.android.gms.R.string.wallet_buy_button_place_holder);
            if (com.google.android.gms.wallet.fragment.SupportWalletFragment.e(aks) != null)
            {
                WalletFragmentStyle walletfragmentstyle = com.google.android.gms.wallet.fragment.SupportWalletFragment.e(aks).getFragmentStyle();
                if (walletfragmentstyle != null)
                {
                    android.util.DisplayMetrics displaymetrics = com.google.android.gms.wallet.fragment.SupportWalletFragment.a(aks).getResources().getDisplayMetrics();
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
            FragmentActivity fragmentactivity = com.google.android.gms.wallet.fragment.SupportWalletFragment.a(aks).getActivity();
            if (SupportWalletFragment.b(aks) == null && SupportWalletFragment.c(aks) && fragmentactivity != null)
            {
                try
                {
                    lk lk = ls.a(fragmentactivity, SupportWalletFragment.d(aks), com.google.android.gms.wallet.fragment.SupportWalletFragment.e(aks), com.google.android.gms.wallet.fragment.SupportWalletFragment.f(aks));
                    com.google.android.gms.wallet.fragment.SupportWalletFragment.a(aks, new b(lk, null));
                    com.google.android.gms.wallet.fragment.SupportWalletFragment.a(aks, null);
                }
                catch (GooglePlayServicesNotAvailableException googleplayservicesnotavailableexception)
                {
                    return;
                }
                f1.a(SupportWalletFragment.b(aks));
                if (SupportWalletFragment.g(aks) != null)
                {
                    com.google.android.gms.wallet.fragment.b.a(SupportWalletFragment.b(aks), SupportWalletFragment.g(aks));
                    com.google.android.gms.wallet.fragment.SupportWalletFragment.a(aks, null);
                }
                if (com.google.android.gms.wallet.fragment.SupportWalletFragment.h(aks) != null)
                {
                    com.google.android.gms.wallet.fragment.b.a(SupportWalletFragment.b(aks), com.google.android.gms.wallet.fragment.SupportWalletFragment.h(aks));
                    com.google.android.gms.wallet.fragment.SupportWalletFragment.a(aks, null);
                }
                if (SupportWalletFragment.i(aks) != null)
                {
                    com.google.android.gms.wallet.fragment.b.a(SupportWalletFragment.b(aks), SupportWalletFragment.i(aks));
                    com.google.android.gms.wallet.fragment.SupportWalletFragment.a(aks, null);
                }
                if (SupportWalletFragment.j(aks) != null)
                {
                    com.google.android.gms.wallet.fragment.b.a(SupportWalletFragment.b(aks), SupportWalletFragment.j(aks).booleanValue());
                    com.google.android.gms.wallet.fragment.SupportWalletFragment.a(aks, null);
                }
            }
        }

        public void onClick(View view)
        {
            FragmentActivity fragmentactivity = com.google.android.gms.wallet.fragment.SupportWalletFragment.a(aks).getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(fragmentactivity), fragmentactivity, -1);
        }

        private c()
        {
            aks = SupportWalletFragment.this;
            super();
        }

        c(_cls1 _pcls1)
        {
            this();
        }
    }


    private class a extends com.google.android.gms.internal.ll.a
    {

        private OnStateChangedListener akp;
        private final SupportWalletFragment akq;

        public void a(int k, int l, Bundle bundle)
        {
            if (akp != null)
            {
                akp.onStateChanged(akq, k, l, bundle);
            }
        }

        public void a(OnStateChangedListener onstatechangedlistener)
        {
            akp = onstatechangedlistener;
        }

        a()
        {
            akq = SupportWalletFragment.this;
        }

        private class OnStateChangedListener
        {

            public abstract void onStateChanged(SupportWalletFragment supportwalletfragment, int k, int l, Bundle bundle);
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
