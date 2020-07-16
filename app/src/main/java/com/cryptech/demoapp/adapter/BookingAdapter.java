package com.cryptech.demoapp.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cryptech.demoapp.fragments.BookingStep1Fragment;
import com.cryptech.demoapp.fragments.BookingStep2Fragment;
import com.cryptech.demoapp.fragments.BookingStep3Fragment;
import com.cryptech.demoapp.fragments.BookingStep4Fragment;
import com.cryptech.demoapp.fragments.ProductDescriptionFragment;
import com.cryptech.demoapp.fragments.ProductSpecificationFragment;
import com.cryptech.demoapp.model.ProductSpecificationModel;

import java.util.List;

public class BookingAdapter extends FragmentPagerAdapter {


    public BookingAdapter(FragmentManager fm) {
        super(fm);

    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return BookingStep1Fragment.getInstance();
            case 1:
                return BookingStep2Fragment.getInstance();
            case 2:
                return BookingStep3Fragment.getInstance();
            case 3:
                return BookingStep4Fragment.getInstance();
            default:

                return null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }
}
