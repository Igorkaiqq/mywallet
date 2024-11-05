package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long roleId;

        private String nome;

        public enum Values {
                ADMIN(1),
                BASIC(2);

                long roleId;

                Values(long roleId) {
                        this.roleId = roleId;
                }

                public long getRoleId() {
                        return roleId;
                }

        }

}
