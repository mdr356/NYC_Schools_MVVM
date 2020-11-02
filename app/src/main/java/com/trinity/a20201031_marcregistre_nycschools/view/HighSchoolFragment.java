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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.trinity.a20201031_marcregistre_nycschools.R;
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi;
import com.trinity.a20201031_marcregistre_nycschools.model.NycHighSchool;
import com.trinity.a20201031_marcregistre_nycschools.view.adapter.HighSchoolAdapter;
import com.trinity.a20201031_marcregistre_nycschools.viewmodel.HighSchoolViewModel;
import dagger.android.support.DaggerFragment;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

public class HighSchoolFragment extends DaggerFragment implements HighSchoolAdapter.ItemClickListener {

    @Inject
    HighSchoolViewModel highSchoolViewModel;

    @Inject
    RetrofitApi request;

    private HighSchoolAdapter adapter;

    private TextView error_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        Timber.d("onCreateView is called in HighSchool Fragment");
        return inflater.inflate(R.layout.fragment_first, parent, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        Timber.d("onViewCreated called in high school fragment");
        error_view = view.findViewById(R.id.error_view);
        ProgressBar loadingIndicator = view.findViewById(R.id.loadingIndicator);
        initializeRecyclerView(view);

        final Observer<Boolean> isLoadingObserver = it -> {
            if(it) {
                Timber.d("Showing loading spinner");
                loadingIndicator.setVisibility(View.VISIBLE);
            } else {
                error_view.setVisibility(View.GONE);
                loadingIndicator.setVisibility(View.GONE);
            }
        };
        highSchoolViewModel.isLoading.postValue(true);
        highSchoolViewModel.isLoading.observe(getViewLifecycleOwner(), isLoadingObserver);

        final Observer<List<NycHighSchool>> getNycHighSchoolDataObserver = it -> {
            adapter.addData(it);
        };
        highSchoolViewModel.getNycHighSchoolData(getContext(), request).observe(getViewLifecycleOwner(), getNycHighSchoolDataObserver);

        final Observer<Boolean> errorDialog = it -> showErrorDialog();
        highSchoolViewModel.showErrorDialog.observe(getViewLifecycleOwner(), errorDialog);
    }

    private void initializeRecyclerView(View view) {
        Timber.d("initialize Recyclerview in high school fragment.");
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<NycHighSchool> data = new ArrayList<NycHighSchool>();
        adapter = new HighSchoolAdapter(getContext(), data);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    private void showErrorDialog() {
        Timber.d("showErrorDialog is called in high school fragment");
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

    @Override
    public void onItemClick(View view, int position) {
        Timber.d("user selected this option of school"+adapter.getItem(position).getSchool_name());
        Bundle result = new Bundle();
        result.putParcelable("SCHOOL_DATA_BUNDLE", adapter.getItem(position));
        Navigation.findNavController(view).navigate(R.id.action_FirstFragment_to_SecondFragment, result);
    }
}
