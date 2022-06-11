package com.example.bce.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BusinessLeadDetailModalClass {
    @SerializedName("success")
    private String success;
    @SerializedName("Receive_list")
    private List<BusinessReceiveList> receiveList = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<BusinessReceiveList> getReceiveList() {
        return receiveList;
    }

    public void setReceiveList(List<BusinessReceiveList> receiveList) {
        this.receiveList = receiveList;
    }

    public static class BusinessLeadDetail {
        @SerializedName("name")
        private String name;
        @SerializedName("mobile")
        private String mobile;
        @SerializedName("remark")
        private String remark;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

    }

    public static class BusinessLeadReceiveFrom {
        @SerializedName("Image")
        private String image;
        @SerializedName("name")
        private String name;
        @SerializedName("company")
        private String company;
        @SerializedName("club_name")
        private String clubName;
        @SerializedName("category")
        private String category;
        @SerializedName("address")
        private String address;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getClubName() {
            return clubName;
        }

        public void setClubName(String clubName) {
            this.clubName = clubName;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static class BusinessReceiveList {
        @SerializedName("date_info")
        private DateInfo dateInfo;
        @SerializedName("business_lead_details")
        private BusinessLeadDetail businessLeadDetails;
        @SerializedName("Receive_from")
        private ReceiveFrom receiveFrom;
        @SerializedName("lead_status")
        private LeadStatus leadStatus;

        public DateInfo getDateInfo() {
            return dateInfo;
        }

        public void setDateInfo(DateInfo dateInfo) {
            this.dateInfo = dateInfo;
        }

        public BusinessLeadDetail getBusinessLeadDetails() {
            return businessLeadDetails;
        }

        public void setBusinessLeadDetails(BusinessLeadDetail businessLeadDetails) {
            this.businessLeadDetails = businessLeadDetails;
        }

        public ReceiveFrom getReceiveFrom() {
            return receiveFrom;
        }

        public void setReceiveFrom(ReceiveFrom receiveFrom) {
            this.receiveFrom = receiveFrom;
        }

        public LeadStatus getLeadStatus() {
            return leadStatus;
        }

        public void setLeadStatus(LeadStatus leadStatus) {
            this.leadStatus = leadStatus;
        }

    }

    public static class DateInfo {
        @SerializedName("date")
        private String date;
        @SerializedName("title")
        private String title;
        @SerializedName("rating")
        private String rating;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }
    }

    public static class LeadStatus {

        @SerializedName("status")
        private String status;
        @SerializedName("amount")
        private String amount;
        @SerializedName("invoice")
        private String invoice;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getInvoice() {
            return invoice;
        }

        public void setInvoice(String invoice) {
            this.invoice = invoice;
        }
    }
}
