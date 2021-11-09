package interview.veripark.com.ui.fragment.stock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import interview.veripark.com.R;
import interview.veripark.com.data.network.ApiHeader;
import interview.veripark.com.data.network.model.HandShakeResponse;
import interview.veripark.com.data.network.model.StockResponse;
import interview.veripark.com.di.Aes;
import interview.veripark.com.ui.activity.detail.DetailActivity;
import interview.veripark.com.ui.base.BaseViewHolder;
import interview.veripark.com.utils.AESUtils;

/**
 * Created by mertKaradeniz on 8.11.2021
 * <p>
 * This is an interview project.
 */

public class StockAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_TITLE = 1;
    public static final int VIEW_TYPE_NORMAL = 2;


    Context context;
    private List<StockResponse.Stock> stockList;

    @Inject
    ApiHeader.ProtectedApiHeader protectedApiHeader;

    private Callback mCallback;

    public StockAdapter(Context context, List<StockResponse.Stock> stockList) {
        this.stockList = stockList;
        this.context = context;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    private void addTitleItemBlank() {
        this.stockList.add(stockList.get(0));
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_stock_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (stockList != null && stockList.size() > 1) {
            addTitleItemBlank();
            return VIEW_TYPE_NORMAL;
        } else if (stockList != null && stockList.size() == 0) {
            return VIEW_TYPE_TITLE;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (stockList != null && stockList.size() > 0) {
            return stockList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<StockResponse.Stock> productList) {
        this.stockList.clear();
        this.stockList.addAll(productList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onRetryClick();

        void onItemClick(StockResponse.Stock product);
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.columnOne)
        TextView columnOne;

        @BindView(R.id.columnTwo)
        TextView columnTwo;

        @BindView(R.id.columnThree)
        TextView columnThree;

        @BindView(R.id.columnFour)
        TextView columnFour;

        @BindView(R.id.columnFive)
        TextView columnFive;

        @BindView(R.id.columnSix)
        TextView columnSix;

        @BindView(R.id.columnSeven)
        TextView columnSeven;

        @BindView(R.id.columnNine)
        ImageView columnNine;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
        public void onBind(int position) {
            super.onBind(position);

            resetColor(itemView);

            if (stockList.size() > 0) {
                if (position == 0) {
                    columnOne.setText("Sembol");
                    columnTwo.setText("Fiyat");
                    columnThree.setText("Fark");
                    columnFour.setText("Hacim");
                    columnFive.setText("Alış");
                    columnSix.setText("Satış");
                    columnSeven.setText("Değişim");
                    columnNine.setVisibility(View.GONE);
                    columnSeven.setVisibility(View.VISIBLE);
                    itemView.setBackgroundColor(itemView.getResources().getColor(R.color.tableRowsTitle));
                } else {
                    StockResponse.Stock item = stockList.get(position);

                    String symbol = "0.0";
                    try {
                        symbol = AESUtils.decrypt(protectedApiHeader.getAesKey(), protectedApiHeader.getAesIV(), item.getSymbol().getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    columnOne.setText(symbol);
                    columnTwo.setText(String.valueOf(item.getDifference()));
                    columnThree.setText(String.valueOf(item.getDifference()));
                    columnFour.setText(String.valueOf("0.0"));
                    columnFive.setText(String.valueOf(item.getBid()));
                    columnSix.setText(String.valueOf(item.getOffer()));

                    columnSeven.setVisibility(View.GONE);
                    columnNine.setVisibility(View.VISIBLE);

                    columnNine.setBackground(item.getIsDown() ? itemView.getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24) :
                            itemView.getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24));

                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = DetailActivity.getStartIntent(context);
                            context.startActivity(intent);
                        }
                    });

                    if (position % 2 == 0) {
                        changeColor(itemView);
                    }
                }
            }
        }

        void resetColor(View itemView) {
            itemView.setBackgroundColor(itemView.getResources().getColor(R.color.white));
        }

        void changeColor(View itemView) {
            itemView.setBackgroundColor(itemView.getResources().getColor(R.color.tableRows));
        }
    }

    public class EmptyViewHolder extends BaseViewHolder {


        //  @BindView(R.id.emptyTitle)
        TextView emptyTitle;

        // @BindView(R.id.emptySubtitle)
        TextView emptySubtitle;

        // @BindView(R.id.btnRetry)
        Button btnRetry;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            emptyTitle.setVisibility(View.VISIBLE);
            emptySubtitle.setVisibility(View.VISIBLE);

            btnRetry.setOnClickListener(view -> mCallback.onRetryClick());
        }
    }
}