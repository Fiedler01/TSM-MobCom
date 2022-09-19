package ch.ulrichfiedler.exp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LaunchCallFragment extends Fragment {
    private final int MY_PERMISSIONS_REQUEST_ACTION_CALL = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.calltab, container, false);
        Button callTabButton = view.findViewById(R.id.calltabbutton);
        callTabButton.setOnClickListener((View v) -> {
                if (getActivity() != null) {
                    if (ActivityCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        Uri phoneNumber = Uri.parse("tel:+41797643480"); // U. Fiedler's cell phone
                        Intent callIntent = new Intent(Intent.ACTION_CALL, phoneNumber);
                        startActivity(callIntent);
                    } else {
                        ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_ACTION_CALL);
                    }
                }
            }
        );

        return view;
    }
}
