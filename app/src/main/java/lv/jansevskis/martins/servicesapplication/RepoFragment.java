package lv.jansevskis.martins.servicesapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RepoFragment extends Fragment {

        private static final String SARG_REPOS = "repos";
        private List<Repository> mRepos = new ArrayList<>();

        public RepoFragment() {
        }

        public static RepoFragment newInstance(ArrayList<Repository> repos) {
            RepoFragment fragment = new RepoFragment();
            Bundle args = new Bundle();
            args.putParcelableArrayList(SARG_REPOS, repos);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mRepos = getArguments().getParcelableArrayList(SARG_REPOS);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_item_list, container, false);

            if (view instanceof RecyclerView) {
                Context context = view.getContext();
                RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.setLayoutManager(new LinearLayoutManager(context));

                recyclerView.setAdapter(new RepoRecyclerViewAdapter(mRepos));
            }
            return view;
        }
}
