package com.sergo.wic.entities;

import com.sergo.wic.entities.enums.RegisteredBy;
import com.sergo.wic.entities.enums.RegistrationState;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "registrations")
public class Registration {

    public Registration(){
//        this.state = RegistrationState.CREATED;
    }

    public Registration(String login, String contact, String code) {
        this.login = login;
        this.contact = contact;
        this.code = code;
    }

    public Registration(String login, String code, String contact, Long userId) {
        this.login = login;
        this.code = code;
        this.contact = contact;
        this.userId = userId;
    }

    public Registration(String login, String code, String contact,String regId, Long userId) {
        this.login = login;
        this.code = code;
        this.contact = contact;
        this.userId = userId;
        this.regId = regId;
    }

    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    private long id;
    @Column(name = "reg_id")
    private String regId;
    @Column(name = "login")
    private String login;
    @Column(name = "address")
    private String address;
    @Column(name = "contact")
    private String contact;
    @Column(name = "code")
    private String code;
    @Column(name ="alexa_rank")
    private String alexaRank;
    @Column(name = "reason_of_refuse")
    private String reasonOfRefuse;
    @Column(name = "user_id")
    private Long userId;
//, nullable = false, columnDefinition = "REGISTRATION_STATE DEFAULT 'CREATED'"
//    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private RegistrationState state;

    @Enumerated(EnumType.STRING)
    private RegisteredBy registeredBy;

    @Column(name = "is_checked")
    private Boolean isChecked;

    public RegistrationState getState() {
        return state;
    }

    public void setState(RegistrationState state) {

        this.state = state;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public RegisteredBy getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(RegisteredBy registeredBy) {
        this.registeredBy = registeredBy;
    }

    public String getReasonOfRefuse() {
        return reasonOfRefuse;
    }

    public void setReasonOfRefuse(String reasonOfRefuse) {
        this.reasonOfRefuse = reasonOfRefuse;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean isChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public String getAlexaRank() {
        return alexaRank;
    }

    public void setAlexaRank(String alexaRank) {
        this.alexaRank = alexaRank;
    }

    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return id == that.id &&
                this.address.equals(((Registration) o).getAddress())
               &this.login.equals(((Registration) o).getLogin())
               &this.contact.equals(((Registration)o).getContact()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, contact);
    }
}
