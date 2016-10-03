package com.ht.home.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ht.home.R;
import com.ht.home.bl.NodeManager;
import com.ht.home.bl.adapter.NodeListAdapter;
import com.ht.home.bo.Node;
import com.ht.home.util.GenericCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NodeListFragment extends Fragment implements GenericCallback {

    @BindView(R.id.rv_node)
    RecyclerView mRecyclerView;

    public NodeListFragment() {
        // Required empty public constructor
    }

    public static NodeListFragment newInstance() {
        NodeListFragment fragment = new NodeListFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NodeManager.getInstance().getAllNodes(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_node_list, container, false);

        ButterKnife.bind(this, view);

        RecyclerView.LayoutManager storeLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(storeLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onSuccess(Object response) {
        List<Node> nodeList = (List<Node>) response;

        NodeListAdapter adapter = new NodeListAdapter(getContext(), nodeList);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onError(String error) {

    }
}
