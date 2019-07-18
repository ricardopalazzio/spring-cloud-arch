package ninja.tuxtech.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.Version;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "User")
@DynamicUpdate
@DynamicInsert
@NamedEntityGraph( name = "userWithRoles"  , attributeNodes =  @NamedAttributeNode(value = "roles"))
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @JsonIgnore
    private String facebookHash;
    @JsonIgnore
    private String googleHash;
    @NotNull
    @Size(min = 2)
    private String username;
    private String password;
    @Column(unique = true)
    @Email
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST , CascadeType.REMOVE})
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID" , foreignKey = @ForeignKey(name = "user_role_user_fk")) }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID", foreignKey = @ForeignKey(name = "user_role_rolefk")) })
    private Set<Role> roles;

    @Version
    @NotNull
    private long version;

    private LocalDateTime creationDate;

    private LocalDateTime lastModifiedDate;

    public User(@NotNull @Size(min = 2) String username, String password, @Email String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {
    }

    public Optional<UUID> getId() {
        return Optional.ofNullable(id);
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public Optional<String> getFacebookHash() {
        return Optional.ofNullable(facebookHash);
    }

    public void setFacebookHash(String facebookHash) {
        this.facebookHash = facebookHash;
    }

    public Optional<String> getGoogleHash() {
        return Optional.ofNullable(googleHash);
    }

    public void setGoogleHash(String googleHash) {
        this.googleHash = googleHash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
