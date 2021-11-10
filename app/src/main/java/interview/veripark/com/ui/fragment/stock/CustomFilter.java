package interview.veripark.com.ui.fragment.stock;

import android.widget.Filter;
import java.util.ArrayList;
import java.util.List;
import interview.veripark.com.data.network.model.StockResponse;

/**
 * Created by mertKaradeniz on 10.11.2021
 * <p>
 * This is an interview project.
 */

public class CustomFilter extends Filter {

    StockAdapter adapter;
    List<StockResponse.Stock> filterList;

    public CustomFilter(List<StockResponse.Stock> filterList, StockAdapter adapter) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if (constraint != null && constraint.length() > 0) {
            constraint = (constraint.toString().toUpperCase());
            List<StockResponse.Stock> filteredPlayers = new ArrayList<>();

            for (int i = 0; i < filterList.size(); i++) {
                if (adapter.mCallback.getAesDecrypt(filterList.get(i).getSymbol()).toUpperCase().contains(constraint)) {
                    filteredPlayers.add(filterList.get(i));
                }
            }

            results.count = filteredPlayers.size();
            results.values = filteredPlayers;
        } else {
            results.count = filterList.size();
            results.values = filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.stockList = (List<StockResponse.Stock>) results.values;
        adapter.notifyDataSetChanged();
    }
}
