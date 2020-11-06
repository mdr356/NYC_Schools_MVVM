package com.trinity.a20201031_marcregistre_nycschools.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.trinity.a20201031_marcregistre_nycschools.R;
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi;
import com.trinity.a20201031_marcregistre_nycschools.model.NycHighSchool;
import com.trinity.a20201031_marcregistre_nycschools.model.SatScores;
import com.trinity.a20201031_marcregistre_nycschools.viewmodel.SatScoresViewModel;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

public class SatScoresFragment extends Fragment {

    @Inject
    public SatScoresViewModel satScoresViewModel;

    @Inject
    public RetrofitApi request;

    private NycHighSchool nycHighSchool;

    private TextView error_view;
    private TextView sat_math_avg_score_text;
    private TextView num_of_sat_test_takers_text;
    private ConstraintLayout sat_score_view;
    private ProgressBar loadingIndicator;

    // Instead of using DaggerFragment, using this instead:
    // AndroidSupportInjection.inject(this) is a contract that allows all the @Inject field to get injected.
    @Override
    public void onAttach(final Context context) {
        injectMembers();
        super.onAttach(context);
    }
    protected void injectMembers() {
        AndroidSupportInjection.inject(this);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        assert getArguments() != null;
        NycHighSchool nycHighSchool = getArguments().getParcelable("SCHOOL_DATA_BUNDLE");
        this.nycHighSchool = nycHighSchool;
        return inflater.inflate(R.layout.fragment_second, parent, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        initView(view);
        setObservers();
    }

    private void setObservers() {
        final Observer<Boolean> showLoadingView = it -> {
            Timber.d("Showing loading spinner");
            loadingIndicator.setVisibility(View.VISIBLE);
        };
        satScoresViewModel.showLoading.observe((LifecycleOwner) getContext(), showLoadingView);
        final Observer<Boolean> hideLoadingView = it -> {
            error_view.setVisibility(View.GONE);
            loadingIndicator.setVisibility(View.GONE);
        };
        satScoresViewModel.hideLoading.observe((LifecycleOwner) getContext(), hideLoadingView);

        final Observer<Boolean> isLoadingObserver = it -> {
            satScoresViewModel.showLoadingView(it);
        };
        satScoresViewModel.isLoading.postValue(true);
        satScoresViewModel.isLoading.observe(getViewLifecycleOwner(), isLoadingObserver);

        final Observer<SatScores> getSatScoresViewModel = it -> {
            //populate view.
            satScoresViewModel.showSatScoreOrNot(it);
        };
        satScoresViewModel.getSatScoresData(getContext(), request, nycHighSchool.getSchool_name()).observe(getViewLifecycleOwner(), getSatScoresViewModel);

        final Observer<SatScores> showSatView = it -> {
            sat_math_avg_score_text.setText(it.getSat_math_avg_score());
            num_of_sat_test_takers_text.setText(it.getNum_of_sat_test_takers());
            sat_score_view.setVisibility(View.VISIBLE);
        };
        satScoresViewModel.showSatView.observe((LifecycleOwner) getContext(), showSatView);

        final Observer<Boolean> initializeSatView = it -> {
            sat_score_view.setVisibility(View.VISIBLE);
            sat_math_avg_score_text.setText("N/A");
            num_of_sat_test_takers_text.setText("N/A");
        };
        satScoresViewModel.initializeSatView.observe((LifecycleOwner) getContext(), initializeSatView);

    }

    private void initView(View view) {
        loadingIndicator = view.findViewById(R.id.loadingIndicator);
        error_view = view.findViewById(R.id.error_view);
        final TextView school_name = view.findViewById(R.id.school_name);
        final TextView overview_paragraph = view.findViewById(R.id.overview_paragraph);
        final TextView language_offered = view.findViewById(R.id.language_offered);
        final TextView location_text = view.findViewById(R.id.location_text);
        sat_math_avg_score_text = view.findViewById(R.id.sat_math_avg_score_text);
        num_of_sat_test_takers_text = view.findViewById(R.id.num_of_sat_test_takers_text);
        sat_score_view = view.findViewById(R.id.sat_score_view);

        // populate the schools_list_item views that is included in layout.
        school_name.setText(nycHighSchool.getSchool_name());
        overview_paragraph.setText(nycHighSchool.getOverview_paragraph());
        language_offered.setText(nycHighSchool.getLanguage_classes());
        location_text.setText(nycHighSchool.getLocation());
    }
}
