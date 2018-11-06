package imt.org.web.weatheradmindataviewer.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "id_user", nullable = false)
    private int id;

    @Column(name = "login_user", nullable = false)
    private String login;

    @Column(name = "password_user", nullable = false)
    private String password;


}
