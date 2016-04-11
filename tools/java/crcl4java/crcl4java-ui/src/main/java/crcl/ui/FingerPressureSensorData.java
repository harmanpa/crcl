/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crcl.ui;

import com.google.gson.Gson;

/**
 *
 * @author shackle
 */
public class FingerPressureSensorData {

    public static class DataItem {

        private int FSR_finger_A_distal = Integer.MIN_VALUE;
        private int FSR_finger_B_distal = Integer.MIN_VALUE;

        public int getFSR_finger_A_distal() {
            return FSR_finger_A_distal;
        }

        public void setFSR_finger_A_distal(int FSR_finger_A_distal) {
            this.FSR_finger_A_distal = FSR_finger_A_distal;
        }

        public int getFSR_finger_B_distal() {
            return FSR_finger_B_distal;
        }

        public void setFSR_finger_B_distal(int FSR_finger_B_distal) {
            this.FSR_finger_B_distal = FSR_finger_B_distal;
        }

    }

    private DataItem[] sensor_values;

    /**
     * Get the value of sensor_values
     *
     * @return the value of sensor_values
     */
    public DataItem[] getSensor_values() {
        return sensor_values;
    }

    /**
     * Set the value of sensor_values
     *
     * @param sensor_values new value of sensor_values
     */
    public void setSensor_values(DataItem[] sensor_values) {
        this.sensor_values = sensor_values;
    }

    public int getFSR_finger_A_distal() {
        if (null != sensor_values) {
            for (int i = 0; i < sensor_values.length; i++) {
                DataItem item = sensor_values[i];
                if (null != item) {
                    if (item.getFSR_finger_A_distal() != Integer.MIN_VALUE) {
                        return item.getFSR_finger_A_distal();
                    }
                }
            }
        }
        return 0;
    }

    public int getFSR_finger_B_distal() {
        if (null != sensor_values) {
            for (int i = 0; i < sensor_values.length; i++) {
                DataItem item = sensor_values[i];
                if (null != item) {
                    if (item.getFSR_finger_B_distal() != Integer.MIN_VALUE) {
                        return item.getFSR_finger_B_distal();
                    }
                }
            }
        }
        return 0;
    }
    
    private static Gson gson = new Gson();

    public static FingerPressureSensorData fromJSON(String json) {
        return gson.fromJson(json, FingerPressureSensorData.class);
    }

}
