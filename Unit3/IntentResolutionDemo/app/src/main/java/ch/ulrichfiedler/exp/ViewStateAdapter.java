package ch.ulrichfiedler.exp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewStateAdapter extends FragmentStateAdapter {

    public ViewStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LaunchCallFragment();
            case 1:
                return new LaunchMapFragment();
            case 2:
                return new LaunchWebSiteFragment();
            default:
                throw new IllegalArgumentException("Unknown position received: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
