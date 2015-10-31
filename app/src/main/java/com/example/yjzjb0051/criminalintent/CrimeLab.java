package com.example.yjzjb0051.criminalintent;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by YJZJB0051 on 2015-10-22.
 */

//单例模式
public class CrimeLab {

    private static final String TAG = "CrimeLab";
    private static final String FILENAME = "crimes.json";

    private ArrayList<Crime> mCrimes;

    private CriminalIntentJSONSerializer mSerializer;

    private static CrimeLab sCrimeLab;
    private Context mAppContext;
    private Calendar mCalendar;

    private CrimeLab(Context appContext){
        mAppContext = appContext;
//        mCrimes = new ArrayList<Crime>();
//        for (int i = 0; i < 100; i++){
//            Crime c = new Crime();
//            c.setTittle("Crime #" + i);
//            c.setSolved(i % 2 == 0);
//            mCalendar = Calendar.getInstance();
//            mCalendar.setTime(new Date());
//            mCalendar.set(Calendar.DATE, i);
//            c.setDate(mCalendar.getTime());
//            mCrimes.add(c);
//        }
        mSerializer = new CriminalIntentJSONSerializer(mAppContext, FILENAME);
        try {
            mCrimes = mSerializer.loadCrimes();
        } catch (Exception e) {
            mCrimes = new ArrayList<Crime>();
            Log.e(TAG, "Error loading crimes: ", e);
        }
    }

    public static CrimeLab get(Context c) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return  sCrimeLab;
    }

    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime c : mCrimes) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public void addCrime(Crime c) {
        mCrimes.add(c);
    }

    public boolean saveCrimes() {
        try {
            mSerializer = new CriminalIntentJSONSerializer(mAppContext, FILENAME);
            mSerializer.saveCrimes(mCrimes);
            Log.d(TAG, "criems saved to file");
//            Toast toast = Toast.makeText(mAppContext, "Susses", Toast.LENGTH_SHORT);
//            toast.show();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving crimes: ", e);
//            Toast toast = Toast.makeText(mAppContext, "False", Toast.LENGTH_SHORT);
//            toast.show();
            return false;
        }
    }

    public void deleteCrime(Crime c) {
        mCrimes.remove(c);
    }
}
