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
import android.widget.Toast;

import java.util.Arrays;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Object} and makes a call to the
 * specified {@link PrincipleFragment.OnPrincipleFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PrincipleViewAdapter extends RecyclerView.Adapter<PrincipleViewAdapter.ViewHolder> {
    private static final int MAX_PRINCIPLES = 6;
    private final String[] mTitles;
    private final PrincipleFragment.OnPrincipleFragmentInteractionListener mListener;
    private String[] mValues;
    private int mSelectedItemCount = 0;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
        holder.mView.setOnClickListener(holder);
        holder.mSpinner.setOnItemSelectedListener(holder);
    }

    @Override
    public int getItemCount() {
        return mTitles.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener, AdapterView.OnItemSelectedListener {
        private final View mView;
        private final TextView mTitle;
        private final Spinner mSpinner;

        public ViewHolder(View view, String[] values) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.title);
            mSpinner = (Spinner) view.findViewById(R.id.spinner);
            mSpinner.setAdapter(new ArrayAdapter<>(view.getContext(),
                    R.layout.spinner_item,values));
            mView.setOnClickListener(this);
            mSpinner.setOnItemSelectedListener(this);
        }

        public void bind(int position) {
            if (position >= 0) {
                int count = mListener.onRequestPrincipleSelectedCount();
                mTitle.setText(mTitles[position]);
                int value = mListener.onRequestPrincipleValue(position);
                mSpinner.setSelection(value);
                mView.setBackgroundResource(value > 0 ? R.color.colorSelected : R.color.colorUnselected);
                mSpinner.setEnabled((count < MAX_PRINCIPLES || value > 0 ) && position != 0 && position != 7);

            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            int count = mListener.onRequestPrincipleSelectedCount();
            int value = mListener.onRequestPrincipleValue(position);
            if ((count >= MAX_PRINCIPLES && value == 0) || position <= 0 || position == 7) {
                if (position == 0 || position == 7)
                    Toast.makeText(v.getContext(),
                            "Questo principio è imprescindibile per il territorio",
                            Toast.LENGTH_SHORT).show();
                else if (count >= MAX_PRINCIPLES)
                    Toast.makeText(v.getContext(),
                            "Puoi selezionare al massimo 6 principi",
                            Toast.LENGTH_SHORT).show();
                return;
            }
            mListener.onSetPrincipleValue(position, value > 0 ? 0 : 1);
            bind(position);
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int value, long id) {
            int position = getAdapterPosition();
            if (position <= 0 || position == 7) return;
            mListener.onSetPrincipleValue(position, value);
            bind(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            int position = getAdapterPosition();
            if (position <= 0 || position == 7) return;
            mListener.onSetPrincipleValue(position, 0);
            bind(position);
        }
    }

}
