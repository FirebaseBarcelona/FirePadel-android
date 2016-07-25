package firebasebarcelona.wallapadel.ui.courts.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import firebasebarcelona.wallapadel.R;
import firebasebarcelona.wallapadel.ui.courts.presentations.CourtListView;
import firebasebarcelona.wallapadel.ui.di.component.DaggerViewComponent;
import firebasebarcelona.wallapadel.ui.di.module.ViewModule;

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
}
