package interview.veripark.com.ui.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import interview.veripark.com.MainApplication;
import interview.veripark.com.di.component.ActivityComponent;
import interview.veripark.com.di.component.DaggerActivityComponent;
import interview.veripark.com.di.module.ActivityModule;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private int mCurrentPosition;
    private ActivityComponent mActivityComponent;


    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(int position) {
        mCurrentPosition = position;
        clear();
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}
