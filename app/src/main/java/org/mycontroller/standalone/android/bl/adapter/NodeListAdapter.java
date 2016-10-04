package org.mycontroller.standalone.android.bl.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.mycontroller.standalone.android.R;
import org.mycontroller.standalone.android.bo.Node;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tibi on 28/09/16.
 */
public class NodeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Node> nodeList;
    private Context mContext;

    public NodeListAdapter(Context context, List<Node> nodeList) {
        this.nodeList = nodeList;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        if (nodeList == null) {
            return 0;
        }

        if (nodeList.size() == 0) {
            //Return 1 here to show nothing
            return 0;
        }

        return nodeList.size();
    }

    public void clear() {
        nodeList.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder storeViewHolder, int i) {

        if (storeViewHolder instanceof NodeViewHolder) {

            NodeViewHolder vh = (NodeViewHolder) storeViewHolder;

            Node node = nodeList.get(i);

            if (node.state.equals("Up")) {
                vh.mEnabled.setImageResource(R.drawable.circle_enabled);
            } else {
                vh.mEnabled.setImageResource(R.drawable.circle_disabled);
            }
            vh.mGwName.setText(node.gateway.name);
            vh.mNodeName.setText(node.name);

            if (i%2==0) {
                vh.mEnabled.setBackgroundColor(ContextCompat.getColor(mContext, R.color.cell_dark));
                vh.mGwName.setBackgroundColor(ContextCompat.getColor(mContext, R.color.cell_dark));
                vh.mNodeName.setBackgroundColor(ContextCompat.getColor(mContext, R.color.cell_dark));
            }

            if (i==0)
                vh.mHeadLayout.setVisibility(View.VISIBLE);

            if (i==getItemCount()-1)
                vh.mBottomLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_node, viewGroup, false);

        return new NodeViewHolder(itemView);
    }

    public static class NodeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_enabled)
        ImageView mEnabled;
        @BindView(R.id.tv_node_gw)
        TextView mGwName;
        @BindView(R.id.tv_node_name)
        TextView mNodeName;
        @BindView(R.id.ll_bottom)
        LinearLayout mBottomLayout;
        @BindView(R.id.ll_head)
        LinearLayout mHeadLayout;

        public Node mNode;

        public NodeViewHolder(View v) {
            super(v);

            ButterKnife.bind(this, v);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
