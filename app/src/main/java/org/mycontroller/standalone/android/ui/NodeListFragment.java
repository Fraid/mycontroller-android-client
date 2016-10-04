package org.mycontroller.standalone.android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.mycontroller.standalone.android.bl.NodeManager;
import org.mycontroller.standalone.android.bl.adapter.NodeListAdapter;
import org.mycontroller.standalone.android.bo.Node;
import org.mycontroller.standalone.android.util.GenericCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NodeListFragment extends Fragment implements GenericCallback {

    @BindView(org.mycontroller.standalone.android.R.id.rv_node)
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
        View view =  inflater.inflate(org.mycontroller.standalone.android.R.layout.fragment_node_list, container, false);

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
