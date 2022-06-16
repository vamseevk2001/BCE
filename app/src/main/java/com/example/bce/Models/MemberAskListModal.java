package com.example.bce.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MemberAskListModal {
    @SerializedName("success")
    private String success;
    @SerializedName("member_ask_list")
    private List<MemberAsk> memberAskList = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<MemberAsk> getMemberAskList() {
        return memberAskList;
    }

    public void setMemberAskList(List<MemberAsk> memberAskList) {
        this.memberAskList = memberAskList;
    }

    public class From {

        @SerializedName("image")
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

    public class MemberAsk {

        @SerializedName("meeting_date")
        private String meetingDate;
        @SerializedName("name")
        private String name;
        @SerializedName("deparment")
        private String deparment;
        @SerializedName("conmpany")
        private String conmpany;
        @SerializedName("reason")
        private String reason;
        @SerializedName("from")
        private From from;

        public String getMeetingDate() {
            return meetingDate;
        }

        public void setMeetingDate(String meetingDate) {
            this.meetingDate = meetingDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDeparment() {
            return deparment;
        }

        public void setDeparment(String deparment) {
            this.deparment = deparment;
        }

        public String getConmpany() {
            return conmpany;
        }

        public void setConmpany(String conmpany) {
            this.conmpany = conmpany;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public From getFrom() {
            return from;
        }

        public void setFrom(From from) {
            this.from = from;
        }

    }

}
