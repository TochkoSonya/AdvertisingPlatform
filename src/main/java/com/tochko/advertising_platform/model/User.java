package com.tochko.advertising_platform.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String userName;
    private String password;
    private String email;
    private Role role;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<Comment> comments;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<Announcement> announcements;

}
