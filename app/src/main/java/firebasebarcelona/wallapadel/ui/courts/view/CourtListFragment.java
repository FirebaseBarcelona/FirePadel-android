package firebasebarcelona.wallapadel.ui.courts.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import firebasebarcelona.wallapadel.R;
import firebasebarcelona.wallapadel.ui.courts.presentation.CourtListPresenter;
import firebasebarcelona.wallapadel.ui.courts.presentation.CourtListView;
import firebasebarcelona.wallapadel.ui.di.component.DaggerViewComponent;
import firebasebarcelona.wallapadel.ui.di.module.ViewModule;
import firebasebarcelona.wallapadel.ui.models.CourtViewModel;

public class CourtListFragment extends Fragment implements CourtListView {
    public static final String TAG = CourtListFragment.class.getSimpleName();
    @Inject
    CourtListPresenter presenter;
    @BindView(R.id.courts_list)
    RecyclerView courts;
    private CourtAdapter courtAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_court_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        DaggerViewComponent.builder().viewModule(new ViewModule(this)).build().inject(this);
        initRecyclerView();
        presenter.requestCourts();
    }

    private void initRecyclerView() {
        courtAdapter = new CourtAdapter(new ArrayList<CourtViewModel>());
        courts.setHasFixedSize(true);
        courts.setLayoutManager(new LinearLayoutManager(getContext()));
        courts.setAdapter(courtAdapter);
    }

    @Override
    public void showCourts(List<CourtViewModel> courts) {
        courtAdapter.setCourts(courts);
    }

    @Override
    public void updateCourt(CourtViewModel court) {

    }

    @Override
    public void showLoginProcess() {

    }
}
