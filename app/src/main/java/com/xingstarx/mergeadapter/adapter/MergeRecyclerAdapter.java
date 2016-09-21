package com.xingstarx.mergeadapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongxingxing on 16/9/6.
 */

public class MergeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected PieceStateRoster pieces = new PieceStateRoster();

    public MergeRecyclerAdapter() {
        super();
    }

    public List<RecyclerView.Adapter> getPieces() {
        return pieces.getPieces();
    }

    public interface OnViewTypeCheckListener {
        boolean checkViewType(int viewType);
    }

    /**
     * Adds a new adapter to the roster of things to appear in
     * the aggregate list.
     *
     * @param adapter Source for row views for this section
     */
    public void addAdapter(RecyclerView.Adapter adapter) {
        pieces.add(adapter);
        adapter.registerAdapterDataObserver(mDataObserver);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        for (int i =0 ; i < getPieces().size(); i++) {
            RecyclerView.Adapter piece = getPieces().get(i);
            if (!(piece instanceof OnViewTypeCheckListener)) {
                throw new IllegalStateException("the sub RecyclerView.Adapter piece need to implements OnViewTypeCheckListener");
            }
            OnViewTypeCheckListener otc = (OnViewTypeCheckListener) piece;
            if (otc.checkViewType(viewType)) {
                return piece.onCreateViewHolder(parent, viewType);
            }
        }
        throw new IllegalStateException("the sub RecyclerView.Adapter piece need to implements OnViewTypeCheckListener, and checkViewType must contains true condition");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        for (int i =0 ; i < getPieces().size(); i++) {
            RecyclerView.Adapter piece = getPieces().get(i);
            int size = piece.getItemCount();
            if (position < size) {
                piece.onBindViewHolder(holder, position);
                break;
            }
            position -= size;
        }
    }

    @Override
    public int getItemCount() {
        int total = 0;
        for (int i =0 ; i < getPieces().size(); i++) {
            RecyclerView.Adapter piece = getPieces().get(i);
            total += piece.getItemCount();
        }
        return total;
    }

    @Override
    public int getItemViewType(int position) {
        for (int i = 0; i < pieces.getRawPieces().size(); i++) {
            PieceState piece = pieces.getRawPieces().get(i);
            if (piece.isActive) {
                int size = piece.adapter.getItemCount();
                if (position < size) {
                    return piece.adapter.getItemViewType(position);
                }
                position -= size;
            }
        }
        return 0;
    }

    private static class PieceState {
        RecyclerView.Adapter<RecyclerView.ViewHolder> adapter;
        boolean isActive = true;

        PieceState(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter, boolean isActive) {
            this.adapter = adapter;
            this.isActive = isActive;
        }
    }

    private static class PieceStateRoster {
        protected ArrayList<PieceState> pieces = new ArrayList<>();
        protected ArrayList<RecyclerView.Adapter> active = null;

        void add(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
            pieces.add(new PieceState(adapter, true));
        }

        void setActive(ListAdapter adapter, boolean isActive) {
            for (int i = 0; i < pieces.size(); i++) {
                PieceState state = pieces.get(i);
                if (state.adapter == adapter) {
                    state.isActive = isActive;
                    active = null;
                    break;
                }
            }
        }

        List<PieceState> getRawPieces() {
            return (pieces);
        }

        List<RecyclerView.Adapter> getPieces() {
            if (active == null) {
                active = new ArrayList<>();
                for (int i = 0; i < pieces.size(); i++) {
                    PieceState state = pieces.get(i);
                    if (state.isActive) {
                        active.add(state.adapter);
                    }
                }
            }

            return active;
        }
    }

    private RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
        }


        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
        }


        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
        }


        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
        }
    };
}
