    package com.example.demo.entity;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotBlank;

    import java.util.HashSet;
    import java.util.Set;

    @Entity
    @Table(name = "users")
    public class User extends BaseEntity {

        @NotBlank
        @Column(nullable = false, unique = true)
        private String email;

        @NotBlank
        @Column(nullable = false)
        private String password;

        @NotBlank
        @Column(nullable = false)
        private String name;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
        )
        private Set<Role> roles = new HashSet<>();

        // getters & setters

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Set<Role> getRoles() {
            return roles;
        }

        public void setRoles(Set<Role> roles) {
            this.roles = roles;
        }
    }
