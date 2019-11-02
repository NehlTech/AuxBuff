package com.cryptech.demoapp.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cryptech.demoapp.fragments.ProductDescriptionFragment;
import com.cryptech.demoapp.fragments.ProductSpecificationFragment;

public class ProductDetailsAdapter extends FragmentPagerAdapter {

    private int totalTabs;

    public ProductDetailsAdapter(FragmentManager fm, int totalTabs) {
        super(fm);
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ProductDescriptionFragment productDescriptionFragment1 = new ProductDescriptionFragment();
                return productDescriptionFragment1;
            case 1:
                ProductSpecificationFragment productSpecificationFragment = new ProductSpecificationFragment();
                return productSpecificationFragment;
            case 2:
                ProductDescriptionFragment productDescriptionFragment2 = new ProductDescriptionFragment();
                return productDescriptionFragment2;
                default:

                    return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
