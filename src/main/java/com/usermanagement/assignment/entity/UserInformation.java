package com.usermanagement.assignment.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import java.util.*;
/*
 * @created 04/03/2025 - 15:54
 * @project assignment
 * @author Swati Madaan
 * Entity for storing Profile information where contact information and address
 * information details are embedded
 */

@Entity
@Table(name="USER_INFORMATION")
@Data
public class UserInformation {
    /**
     * userId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    /**
     * firstName
     */
    @Column
    private String firstName;
    /**
     * lastName
     */
    @Column
    private String lastName;
    /**
     * title
     */
    @Column
    private String title;
    /**
     * dob
     */
    @Column
    private Date dob;
    /**
     * addresses associated with the user
     */
    @OneToMany(mappedBy = "userInformation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserAddress> userAddressesList;
    /**
     * email addresses associated with the user
     */
    @OneToMany(mappedBy = "userInformation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private @NotNull List<UserEmail> emailList;
    /**
     * phone numbers associated with the user
     */
    @OneToMany(mappedBy = "userInformation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserPhone> phoneNumbers;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.title + " " + this.firstName + " " + this.lastName + "\n");
        sb.append("DOB" + " " + this.dob + "\n");
        for(UserEmail email: this.emailList){
            sb.append("email" + " " + email.getEmailAddress() + "\n");
        }
        for(UserPhone phone: this.phoneNumbers){
            sb.append("Phone" + " " + phone.getPhoneType() + " " + phone.getPhoneNumber() + "\n");
        }
        for(UserAddress addr: this.userAddressesList){
            sb.append("Address" + " " + addr.getAddressType() + " " +
                    addr.getAddressLineOne() + " " + addr.getAddressLineTwo() + " "
                    +addr.getCity() +" " + addr.getCounty() + " " + addr.getPostcode() + "\n");
        }
        return sb.toString();
    }
}
