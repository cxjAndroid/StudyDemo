package com.example.jonchen.pattern.Iterator;

import com.example.jonchen.utils.ToastUtils;

/**
 * @author jon
 * @since 3/29/18
 */

public class GroupLeader extends Leader {
    @Override
    public int maxCodeLines() {
        return 1000;
    }

    @Override
    public void handle(int codeLines) {
        ToastUtils.show("GroupLeader write "+codeLines+"lines code");
    }
}
