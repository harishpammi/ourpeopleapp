package com.ourpeople.ourpeople.models;

/**
 * Created by hareesh.pammi on 30/10/16.
 */
public class RegisterApiBody {
    private String first_name;
    private String phone;
    private String email;
    private String father_name;
    private String mother_name;
    private String spouse_name;
    private String address;
    private String community_id;

    public RegisterApiBody(String first_name, String phone, String email, String father_name, String mother_name, String spouse_name, String address, String community_id) {
        this.first_name = first_name;
        this.phone = phone;
        this.email = email;
        this.father_name = father_name;
        this.mother_name = mother_name;
        this.spouse_name = spouse_name;
        this.address = address;
        this.community_id = community_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public String getSpouse_name() {
        return spouse_name;
    }

    public void setSpouse_name(String spouse_name) {
        this.spouse_name = spouse_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(String community_id) {
        this.community_id = community_id;
    }
}
