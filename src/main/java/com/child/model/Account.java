package com.child.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean isActive;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_address",joinColumns = {
            @JoinColumn(name = "user_id",referencedColumnName = "id")
    },inverseJoinColumns = @JoinColumn(name = "address_id",referencedColumnName = "id"))
    private Set<Address> address;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "account",cascade = CascadeType.ALL)
    private Set<Child> child;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",joinColumns = {
            @JoinColumn(name = "user_id",referencedColumnName = "id")
    },inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Set<Role> roles;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "account",cascade = CascadeType.ALL)
    private Set<VerifyAccount> verifyAccounts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<VerifyAccount> getVerifyAccounts() {
        return verifyAccounts;
    }

    public void setVerifyAccounts(Set<VerifyAccount> verifyAccounts) {
        this.verifyAccounts = verifyAccounts;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    public Set<Child> getChild() {
        return child;
    }

    public void setChild(Set<Child> child) {
        this.child = child;
    }

    public Set<Role> addRole(Role role){
        if (roles==null)
            roles=new HashSet<Role>();
        roles.add(role);
        return roles;
    }

    public Set<Address> addAddress(Address add){
        if (address==null)
            address=new HashSet<Address>();
        address.add(add);
        return address;
    }

    public Set<Child> addChild(Child childs){
        if (child==null)
            child=new HashSet<Child>();
        child.add(childs);
        return child;
    }
}
