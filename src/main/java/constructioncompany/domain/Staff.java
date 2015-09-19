package constructioncompany.domain;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by Yusiry Davids on 4/23/2015.
 */
@Entity
public class Staff implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String IDnumber;
    @Embedded
    private String addressCode;
   // @OneToOne(cascade = CascadeType.ALL)
   // @JoinColumn(name="payCode")
    private String payCode;
    @Column(unique = true)
    private String staffCode;

    public String getStaffCode() {
        return staffCode;
    }



    public Staff() {
    }

    public Staff(Builder builder){
        this.id = builder.id;
        this.staffCode = builder.staffCode;
        this.name = builder.name;
        this.surname = builder.surname;
        this.phone = builder.phone;
        this.IDnumber = builder.IDnumber;
        this.addressCode = builder.addressCode;
        this.payCode = builder.payCode;
    }

    public static class Builder{
        private int id;
        private String name;
        private String surname;
        private String phone;
        private String IDnumber;
        private String addressCode;
        private String payCode;
        private String staffCode;

        public Builder(String code){
            this.staffCode = code;
        }

        public Builder id(int value){
            this.id = value;
            return this;
        }

        public Builder name(String value){
            this.name = value;
            return this;
        }

        public Builder surname(String value){
            this.surname = value;
            return this;
        }

        public Builder phone(String value){
            this.phone = value;
            return this;
        }

        public Builder IDnumber(String value){
            this.IDnumber = value;
            return this;
        }

        public Builder addressCode(String value){
            this.addressCode = value;
            return this;
        }

        public Builder payCode(String value){
            this.payCode = value;
            return this;
        }

        public Builder copy(Staff value){
            this.id = value.id;
            this.staffCode = value.staffCode;
            this.name = value.name;
            this.surname = value.surname;
            this.phone = value.phone;
            this.IDnumber = value.IDnumber;
            this.addressCode = value.addressCode;
            this.payCode = value.payCode;
            return this;
        }

        public Staff build(){
            return new Staff(this);
        }
    }


    public String getAddressCode() {
        return addressCode;
    }

    public int getId() {
        return id;

    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(String IDnumber) {
        this.IDnumber = IDnumber;
    }

    public String getAddressID() {
        return addressCode;
    }

    public void setAddressID(String addressID) {
        this.addressCode = addressID;
    }


}
