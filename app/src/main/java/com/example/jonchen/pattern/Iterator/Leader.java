package com.example.jonchen.pattern.Iterator;

import com.example.jonchen.utils.ToastUtils;

/**
 * @author jon
 * @since 3/29/18
 */

public abstract class Leader {
    public Leader nextHandler;

    public void handleRequest(int codeLines) {
        if (codeLines < maxCodeLines()) {
            handle(codeLines);
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(codeLines);
            } else {
                ToastUtils.show("no one can write so much code!");
            }
        }
    }

    public abstract int maxCodeLines();

    public abstract void handle(int codeLines);

}
