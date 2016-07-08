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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
        holder.mView.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return mTitles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView mTitle;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.title);
            mView.setOnClickListener(this);
        }

        public void bind(int position) {
            if (position >= 0) {
                mTitle.setText(mTitles[position]);
                boolean value = mListener.onRequestAreaValue(position);
                mView.setBackgroundResource(value ? R.color.colorSelected : R.color.colorUnselected);
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position < 0) return;
            boolean value = mListener.onRequestAreaValue(position);
            mListener.onSetAreasValue(position, !value);
            bind(position);
        }
    }
}
