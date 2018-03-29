package com.example.jonchen.pattern.Iterator;

import com.example.jonchen.utils.ToastUtils;

/**
 * @author jon
 * @since 3/29/18
 */

public class Director extends Leader {
    @Override
    public int maxCodeLines() {
        return 2000;
    }

    @Override
    public void handle(int codeLines) {
        ToastUtils.show("Director write "+codeLines+" lines code");
    }
}
