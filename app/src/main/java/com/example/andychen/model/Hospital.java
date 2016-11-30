package com.example.andychen.model;

import java.util.List;

/**
 * Created by chenxujun on 2016/8/22.
 */
public class Hospital {

    /**
     * AppointmentCount : 498540
     * CityId : 2157
     * DepartmentDistributionUrl : http://patientapi.hk515.com/RichTextPage/Hospital/DepartmentLayout?id=601
     * DoctorCount : 1642
     * HospitalAddress : 广东省深圳市福田莲花路1120号
     * HospitalDescriptionUrl : http://patientapi.hk515.com/RichTextPage/Hospital/Introduction?id=601
     * HospitalId : 601
     * HospitalLevel : 三级甲等
     * HospitalName : 北京大学深圳医院
     * HospitalServices : [{"CanServiceCount":"686","EnumValue":1,"HospitalServiceId":"1","HospitalServiceName":"咨询医生"},{"CanServiceCount":"99","EnumValue":2,"HospitalServiceId":"2","HospitalServiceName":"预约挂号"}]
     * Icon : http://storage2.hk515.net/group1/M02/07/30/ooYBAFX9MASARpu4AAH_uanyZmU372.jpg
     * IsCollect : false
     * IsSingle : false
     * IsSmartHospital : false
     * Thumbnail : http://storage2.hk515.net/group1/M02/07/30/ooYBAFX9MASARpu4AAH_uanyZmU372.jpg
     * TicketPoolName : ningyuan
     * TrafficGuideUrl : http://patientapi.hk515.com/RichTextPage/Hospital/BusWay?id=601
     * XPoint : 22.555870056152344
     * YPoint : 114.04920196533203
     */

    private int AppointmentCount;
    private int CityId;
    private String DepartmentDistributionUrl;
    private int DoctorCount;
    private String HospitalAddress;
    private String HospitalDescriptionUrl;
    private String HospitalId;
    private String HospitalLevel;
    private String HospitalName;
    private String Icon;
    private boolean IsCollect;
    private boolean IsSingle;
    private boolean IsSmartHospital;
    private String Thumbnail;
    private String TicketPoolName;
    private String TrafficGuideUrl;
    private double XPoint;
    private double YPoint;
    /**
     * CanServiceCount : 686
     * EnumValue : 1
     * HospitalServiceId : 1
     * HospitalServiceName : 咨询医生
     */

    private List<HospitalServicesBean> HospitalServices;

    public int getAppointmentCount() {
        return AppointmentCount;
    }

    public void setAppointmentCount(int AppointmentCount) {
        this.AppointmentCount = AppointmentCount;
    }

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int CityId) {
        this.CityId = CityId;
    }

    public String getDepartmentDistributionUrl() {
        return DepartmentDistributionUrl;
    }

    public void setDepartmentDistributionUrl(String DepartmentDistributionUrl) {
        this.DepartmentDistributionUrl = DepartmentDistributionUrl;
    }

    public int getDoctorCount() {
        return DoctorCount;
    }

    public void setDoctorCount(int DoctorCount) {
        this.DoctorCount = DoctorCount;
    }

    public String getHospitalAddress() {
        return HospitalAddress;
    }

    public void setHospitalAddress(String HospitalAddress) {
        this.HospitalAddress = HospitalAddress;
    }

    public String getHospitalDescriptionUrl() {
        return HospitalDescriptionUrl;
    }

    public void setHospitalDescriptionUrl(String HospitalDescriptionUrl) {
        this.HospitalDescriptionUrl = HospitalDescriptionUrl;
    }

    public String getHospitalId() {
        return HospitalId;
    }

    public void setHospitalId(String HospitalId) {
        this.HospitalId = HospitalId;
    }

    public String getHospitalLevel() {
        return HospitalLevel;
    }

    public void setHospitalLevel(String HospitalLevel) {
        this.HospitalLevel = HospitalLevel;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String HospitalName) {
        this.HospitalName = HospitalName;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String Icon) {
        this.Icon = Icon;
    }

    public boolean isIsCollect() {
        return IsCollect;
    }

    public void setIsCollect(boolean IsCollect) {
        this.IsCollect = IsCollect;
    }

    public boolean isIsSingle() {
        return IsSingle;
    }

    public void setIsSingle(boolean IsSingle) {
        this.IsSingle = IsSingle;
    }

    public boolean isIsSmartHospital() {
        return IsSmartHospital;
    }

    public void setIsSmartHospital(boolean IsSmartHospital) {
        this.IsSmartHospital = IsSmartHospital;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String Thumbnail) {
        this.Thumbnail = Thumbnail;
    }

    public String getTicketPoolName() {
        return TicketPoolName;
    }

    public void setTicketPoolName(String TicketPoolName) {
        this.TicketPoolName = TicketPoolName;
    }

    public String getTrafficGuideUrl() {
        return TrafficGuideUrl;
    }

    public void setTrafficGuideUrl(String TrafficGuideUrl) {
        this.TrafficGuideUrl = TrafficGuideUrl;
    }

    public double getXPoint() {
        return XPoint;
    }

    public void setXPoint(double XPoint) {
        this.XPoint = XPoint;
    }

    public double getYPoint() {
        return YPoint;
    }

    public void setYPoint(double YPoint) {
        this.YPoint = YPoint;
    }

    public List<HospitalServicesBean> getHospitalServices() {
        return HospitalServices;
    }

    public void setHospitalServices(List<HospitalServicesBean> HospitalServices) {
        this.HospitalServices = HospitalServices;
    }

    public static class HospitalServicesBean {
        private String CanServiceCount;
        private int EnumValue;
        private String HospitalServiceId;
        private String HospitalServiceName;

        public String getCanServiceCount() {
            return CanServiceCount;
        }

        public void setCanServiceCount(String CanServiceCount) {
            this.CanServiceCount = CanServiceCount;
        }

        public int getEnumValue() {
            return EnumValue;
        }

        public void setEnumValue(int EnumValue) {
            this.EnumValue = EnumValue;
        }

        public String getHospitalServiceId() {
            return HospitalServiceId;
        }

        public void setHospitalServiceId(String HospitalServiceId) {
            this.HospitalServiceId = HospitalServiceId;
        }

        public String getHospitalServiceName() {
            return HospitalServiceName;
        }

        public void setHospitalServiceName(String HospitalServiceName) {
            this.HospitalServiceName = HospitalServiceName;
        }
    }
}
