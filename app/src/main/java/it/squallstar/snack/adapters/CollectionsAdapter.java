package it.squallstar.snack.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import it.squallstar.snack.CollectionFragment;
import it.squallstar.snack.helpers.Globals;

/**
 * Created by nicholas on 08/10/2015.
 */
public class CollectionsAdapter extends FragmentPagerAdapter {

    public CollectionsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return Globals.collections.size();
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        args.putInt(CollectionFragment.POSITION_KEY, position);

        CollectionFragment fr = new CollectionFragment();
        fr.setArguments(args);

        return fr;
    }
}