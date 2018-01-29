package com.example.jonchen.pattern.builder;

/**
 * @author 17041931
 * @since 2018/1/29
 */

public class ComputerConfig {
    private String brightness;
    private String contrast;
    private String background;
    private String resolution;
    private String time;
    private String color;
    private String colourTemperature;

    public static class Builder {
        private String brightness;
        private String contrast;
        private String background;
        private String resolution;
        private String time;
        private String color;
        private String colourTemperature;

        public Builder setBrightness(String brightness) {
            this.brightness = brightness;
            return this;
        }

        public Builder setContrast(String contrast) {
            this.contrast = contrast;
            return this;
        }

        public Builder setBackground(String background) {
            this.background = background;
            return this;
        }

        public Builder setResolution(String resolution) {
            this.resolution = resolution;
            return this;
        }

        public Builder setTime(String time) {
            this.time = time;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setColourTemperature(String colourTemperature) {
            this.colourTemperature = colourTemperature;
            return this;
        }

        void applyConfig(ComputerConfig config) {
            config.brightness = this.brightness;
            config.contrast = this.contrast;
            config.background = this.background;
            config.resolution = this.resolution;
            config.time = this.time;
            config.color = this.color;
            config.colourTemperature = this.colourTemperature;
        }

        public ComputerConfig create() {
            ComputerConfig config = new ComputerConfig();
            applyConfig(config);
            return config;
        }
    }

}
