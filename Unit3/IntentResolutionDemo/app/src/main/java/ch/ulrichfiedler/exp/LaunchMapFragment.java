package ch.ulrichfiedler.exp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LaunchMapFragment extends Fragment {
    Button mapTabButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maptab, container, false);
        mapTabButton = view.findViewById(R.id.maptabbutton);

        // Map point based on address
        // Uri location = Uri.parse("geo:0,0?q=8400, Loewenstrasse 2");
        // Or map point based on latitude/longitude
        Uri location = Uri.parse("geo: 47.5093236,8.7186126?z=14"); // z param
        // is zoom
        // level
        final Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

        if (Util.canBeResolved(getActivity(), mapIntent)) {
            mapTabButton.setEnabled(true);
            mapTabButton.setOnClickListener(arg0 -> startActivity(mapIntent));
        } else {
            mapTabButton.setEnabled(false);
        }
        // Inflate the layout for this fragment
        return view;
    }

}
