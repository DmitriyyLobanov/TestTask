package repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private User user;
    private Long userId;
    private String userName;
    private String userPassword;

    public Session(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public Session(User user, String userName, String userPassword) {
        this.user = user;
        this.userName = userName;
        this.userPassword = userPassword;
    }
}
