package com.example.jonchen.pattern.Iterator;

import com.example.jonchen.utils.ToastUtils;

/**
 * @author jon
 * @since 3/29/18
 */

public class Manager extends Leader {
    @Override
    public int maxCodeLines() {
        return 3000;
    }

    @Override
    public void handle(int codeLines) {
        ToastUtils.show("Manager write "+codeLines+" lines code");
    }
}
