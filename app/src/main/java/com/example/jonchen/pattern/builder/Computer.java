package com.example.jonchen.pattern.builder;

/**
 * @author 17041931
 * @since 2018/1/29
 */

public class Computer {

    private ComputerConfig config;

    public ComputerConfig getConfig() {
        return config;
    }

    public void init(ComputerConfig config) {
        this.config = config;
    }

}
