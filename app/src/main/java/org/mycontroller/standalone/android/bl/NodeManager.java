package org.mycontroller.standalone.android.bl;

import org.mycontroller.standalone.android.bl.api.ApiManager;
import org.mycontroller.standalone.android.bl.api.RetrofitCallback;
import org.mycontroller.standalone.android.bl.api.RetrofitResponse;
import org.mycontroller.standalone.android.bo.Node;
import org.mycontroller.standalone.android.bo.response.NodeResponse;
import org.mycontroller.standalone.android.util.GenericCallback;
import org.mycontroller.standalone.android.util.LOG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tibi on 28/09/16.
 */
public class NodeManager {

    private static NodeManager instance;

    public static NodeManager getInstance() {
        if (instance == null)
            instance = new NodeManager();

        return instance;
    }

    public void getAllNodes(final GenericCallback<List<Node>> callback) {

        ApiManager.getInstance().API.getAllNodes().enqueue(new RetrofitCallback<>(new RetrofitResponse<NodeResponse>() {
            @Override
            public void onSuccess(NodeResponse response) {
                List<Node> nodeList = new ArrayList<Node>();
                for (Node node:response.data) {
                    nodeList.add(node);
                }
                callback.onSuccess(nodeList);
            }

            @Override
            public void onFailure(String error) {
                LOG.debug(error);
                callback.onError(error);
            }
        }));

    }
}
