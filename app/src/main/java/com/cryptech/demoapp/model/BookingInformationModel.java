package com.cryptech.demoapp.model;

public class BookingInformationModel {

  private Long slot;
  private String customerName;
  private String custormerPhone;
  private String time;
  private String workerId;
  private String workerName;
  private String salonId;
  private String salonName  ;
  private String salonAddress;

    public BookingInformationModel() {
    }

    public BookingInformationModel(Long slot, String customerName, String custormerPhone, String time, String workerId, String workerName, String salonId, String salonName, String salonAddress) {
        this.slot = slot;
        this.customerName = customerName;
        this.custormerPhone = custormerPhone;
        this.time = time;
        this.workerId = workerId;
        this.workerName = workerName;
        this.salonId = salonId;
        this.salonName = salonName;
        this.salonAddress = salonAddress;
    }

    public Long getSlot() {
        return slot;
    }

    public void setSlot(Long slot) {
        this.slot = slot;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustormerPhone() {
        return custormerPhone;
    }

    public void setCustormerPhone(String custormerPhone) {
        this.custormerPhone = custormerPhone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getSalonId() {
        return salonId;
    }

    public void setSalonId(String salonId) {
        this.salonId = salonId;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    public String getSalonAddress() {
        return salonAddress;
    }

    public void setSalonAddress(String salonAddress) {
        this.salonAddress = salonAddress;
    }
}
