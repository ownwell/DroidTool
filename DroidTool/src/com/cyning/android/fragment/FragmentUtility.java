package com.cyning.android.fragment;

import android.support.v4.app.FragmentTransaction;

public class FragmentUtility {

	public static void hideOthersFragment(FragmentTransaction transaction,
			InnerFragment... fragments) {
		for (InnerFragment fragment : fragments) {
			if (fragment != null) {
				transaction.hide(fragment);
			}
		}
	}
}
