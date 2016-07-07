package it.fdc.dynamicbrand;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Object} and makes a call to the
 * specified {@link AreaFragment.OnAreaFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AreaViewAdapter extends RecyclerView.Adapter<AreaViewAdapter.ViewHolder> {

    private final String[] mTitles;
    private final AreaFragment.OnAreaFragmentInteractionListener mListener;

    public AreaViewAdapter(String[] values, AreaFragment.OnAreaFragmentInteractionListener listener) {
        mTitles = values;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_area, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTitle.setText(mTitles[position]);
        if (null != mListener)
            holder.select(mListener.onRequestAreaValue(position));

        holder.mView.setBackgroundColor(holder.isSelected() ?
                ContextCompat.getColor(holder.mView.getContext(), R.color.colorPrimary) :
                Color.WHITE);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {

                    mListener.onSetAreasValue(position, !holder.isSelected());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public boolean selected = false;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.title);
        }

        public void select(boolean select) {
            selected = select;
        }

        public boolean isSelected() {
            return selected;
        }
    }
}
