package it.fdc.dynamicbrand;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Object} and makes a call to the
 * specified {@link PrincipleFragment.OnPrincipleFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PrincipleViewAdapter extends RecyclerView.Adapter<PrincipleViewAdapter.ViewHolder> {

    private final String[] mTitles;
    private final PrincipleFragment.OnPrincipleFragmentInteractionListener mListener;
    private String[] mValues;

    public PrincipleViewAdapter(String[] values, PrincipleFragment.OnPrincipleFragmentInteractionListener listener) {
        mListener = listener;
        mTitles = values;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_principle, parent, false);
        mValues = view.getResources().getStringArray(R.array.principles_values);
        return new ViewHolder(view, mValues);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTitle.setText(mTitles[position]);
        if (null != mListener) {
            holder.setValue(mListener.onRequestPrincipleValue(position));
        }
        holder.mView.setBackgroundColor(holder.getValue() > 0 ?
                ContextCompat.getColor(holder.mView.getContext(), R.color.colorPrimary) :
                Color.WHITE);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onSetPrincipleValue(position, 1);
                }
            }
        });

        holder.mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                if (null != mListener)
                    mListener.onSetPrincipleValue(position,
                            Integer.valueOf(parent.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (null != mListener)
                    mListener.onSetPrincipleValue(position, 0);
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
        public final Spinner mSpinner;

        public ViewHolder(View view, String[] values) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.id);
            mSpinner = (Spinner) view.findViewById(R.id.spinner);
            mSpinner.setAdapter(new ArrayAdapter<>(view.getContext(),
                    android.R.layout.simple_spinner_item,values));
        }

        public int getValue() {
            return Integer.valueOf(mSpinner.getSelectedItem().toString());
        }

        public void setValue(int value) {
            mSpinner.setSelection(value);
        }
    }

}
