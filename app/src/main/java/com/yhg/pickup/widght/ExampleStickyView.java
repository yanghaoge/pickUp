package com.yhg.pickup.widght;

import android.view.View;

/**
 * Created by cpf on 2018/1/16.
 */

public class ExampleStickyView implements StickyView {

    @Override
    public boolean isStickyView(View view) {
        try {
            return (Boolean) view.getTag();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getStickViewType() {
        return 11;
    }
}
