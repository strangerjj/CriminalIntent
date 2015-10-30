package com.example.yjzjb0051.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by YJZJB0051 on 2015-10-22.
 */
public class CrimeListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return  new CrimeListFragment();
    }
}
