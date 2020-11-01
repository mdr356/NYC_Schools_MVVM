package com.trinity.a20201031_marcregistre_nycschools.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;

import com.trinity.a20201031_marcregistre_nycschools.R;
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi;
import com.trinity.a20201031_marcregistre_nycschools.model.SatScores;
import com.trinity.a20201031_marcregistre_nycschools.viewmodel.SatScoresViewModel;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class SatScoresFragment extends DaggerFragment {

    /*
     view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
     */
    @Inject
    SatScoresViewModel satScoresViewModel;

    @Inject
    RetrofitApi request;

    private TextView error_view;
    private TextView school_name;
    private TextView overview_paragraph;
    private TextView language_offered;
    private TextView location_text;
    private TextView sat_math_avg_score_text;
    private TextView num_of_sat_test_takers_text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_second, parent, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        initView(view);
        ProgressBar loadingIndicator = view.findViewById(R.id.loadingIndicator);

        final Observer<Boolean> isLoadingObserver = it -> {
            if(it) {
                loadingIndicator.setVisibility(View.VISIBLE);
            } else {
                error_view.setVisibility(View.GONE);
                loadingIndicator.setVisibility(View.GONE);
            }
        };
        satScoresViewModel.isLoading.postValue(true);
        satScoresViewModel.isLoading.observe(getViewLifecycleOwner(), isLoadingObserver);

        final Observer<SatScores> getsatScoresViewModel = it -> {
            //populate view.
            school_name.setText();
            overview_paragraph.setText();
            language_offered.setText();
            location_text.setText();
            sat_math_avg_score_text.setText(it.getSat_math_avg_score());
            num_of_sat_test_takers_text.setText(it.getNum_of_sat_test_takers());
        };
        satScoresViewModel.getSatScoresData(getContext(), request).observe(getViewLifecycleOwner(), getsatScoresViewModel);

        final Observer<Boolean> errorDialog = it -> showErrorDialog();
        satScoresViewModel.showErrorDialog.observe(getViewLifecycleOwner(), errorDialog);
    }

    private void initView(View view) {
        error_view = view.findViewById(R.id.error_view);
        school_name = view.findViewById(R.id.school_name);
        overview_paragraph = view.findViewById(R.id.overview_paragraph);
        language_offered = view.findViewById(R.id.language_offered);
        location_text = view.findViewById(R.id.location_text);
        sat_math_avg_score_text = view.findViewById(R.id.sat_math_avg_score_text);
        num_of_sat_test_takers_text = view.findViewById(R.id.num_of_sat_test_takers_text);
    }

    private void showErrorDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle(getActivity().getString(R.string.error))
                .setMessage(getActivity().getString(R.string.error_msg))

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        error_view.setVisibility(View.VISIBLE);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
