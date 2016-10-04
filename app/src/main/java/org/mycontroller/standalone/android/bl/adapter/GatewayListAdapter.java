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

import org.mycontroller.standalone.android.bo.Gateway;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tibi on 28/09/16.
 */
public class GatewayListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Gateway> gatewayList;
    private Context mContext;

    public GatewayListAdapter(Context context, List<Gateway> storeList) {
        this.gatewayList = storeList;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        if (gatewayList == null) {
            return 0;
        }

        if (gatewayList.size() == 0) {
            //Return 1 here to show nothing
            return 0;
        }

        return gatewayList.size();
    }

    public void clear() {
        gatewayList.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder storeViewHolder, int i) {

        if (storeViewHolder instanceof GatewayViewHolder) {

            GatewayViewHolder vh = (GatewayViewHolder) storeViewHolder;

            Gateway gateway = gatewayList.get(i);

            if (gateway.enabled) {
                vh.mEnabled.setImageResource(org.mycontroller.standalone.android.R.drawable.circle_enabled);
            } else {
                vh.mEnabled.setImageResource(org.mycontroller.standalone.android.R.drawable.circle_disabled);
            }
            vh.mGwName.setText(gateway.name);
            vh.mGwType.setText(gateway.type);

            if (i%2==0) {
                vh.mEnabled.setBackgroundColor(ContextCompat.getColor(mContext, org.mycontroller.standalone.android.R.color.cell_dark));
                vh.mGwName.setBackgroundColor(ContextCompat.getColor(mContext, org.mycontroller.standalone.android.R.color.cell_dark));
                vh.mGwType.setBackgroundColor(ContextCompat.getColor(mContext, org.mycontroller.standalone.android.R.color.cell_dark));
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
                inflate(org.mycontroller.standalone.android.R.layout.item_gateway, viewGroup, false);

        return new GatewayViewHolder(itemView);
    }

    public static class GatewayViewHolder extends RecyclerView.ViewHolder {

        @BindView(org.mycontroller.standalone.android.R.id.iv_enabled)
        ImageView mEnabled;
        @BindView(org.mycontroller.standalone.android.R.id.tv_gw_name)
        TextView mGwName;
        @BindView(org.mycontroller.standalone.android.R.id.tv_gw_type)
        TextView mGwType;
        @BindView(org.mycontroller.standalone.android.R.id.ll_bottom)
        LinearLayout mBottomLayout;
        @BindView(org.mycontroller.standalone.android.R.id.ll_head)
        LinearLayout mHeadLayout;

        public Gateway mGateway;

        public GatewayViewHolder(View v) {
            super(v);

            ButterKnife.bind(this, v);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
