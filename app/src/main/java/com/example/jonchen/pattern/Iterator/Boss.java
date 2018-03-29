package com.example.jonchen.pattern.Iterator;

import com.example.jonchen.utils.ToastUtils;

/**
 * @author jon
 * @since 3/29/18
 */

public class Boss extends Leader {
    @Override
    public int maxCodeLines() {
        return 4000;
    }

    @Override
    public void handle(int codeLines) {
        ToastUtils.show("Boss write " + codeLines + " lines code");
    }
}
