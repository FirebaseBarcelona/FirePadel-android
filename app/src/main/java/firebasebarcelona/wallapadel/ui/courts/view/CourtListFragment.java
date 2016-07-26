package firebasebarcelona.wallapadel.ui.courts.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import firebasebarcelona.wallapadel.R;
import firebasebarcelona.wallapadel.ui.courts.presentation.CourtListView;
import firebasebarcelona.wallapadel.ui.di.component.DaggerViewComponent;
import firebasebarcelona.wallapadel.ui.di.module.ViewModule;
import firebasebarcelona.wallapadel.ui.models.CourtViewModel;

public class CourtListFragment extends Fragment implements CourtListView {
     public static final String TAG = CourtListFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_court_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaggerViewComponent.builder().viewModule(new ViewModule(this)).build().inject(this);
    }

    @Override
    public void showCourts(List<CourtViewModel> courts) {

    }

    @Override
    public void updateCourt(CourtViewModel court) {

    }

    @Override
    public void showLoginProcess() {

    }
}
