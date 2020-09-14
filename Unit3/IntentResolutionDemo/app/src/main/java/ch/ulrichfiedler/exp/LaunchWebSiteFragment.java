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

public class LaunchWebSiteFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webtab, container, false);
        Button webTabButton = view.findViewById(R.id.webtabbutton);
        webTabButton.setOnClickListener(arg0 -> {
            Uri url = Uri.parse("https://www.bfh.ch/");
            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, url);
            startActivity(websiteIntent);
        });
        // Inflate the layout for this fragment
        return view;
    }

}
