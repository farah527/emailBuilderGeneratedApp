package com.fastcode.emailtest2.domain.core.theme;

import javax.persistence.*;
import java.time.*;
import com.fastcode.emailtest2.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theme")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Theme extends AbstractEntity {

    @Basic
    @Column(name = "fonts", nullable = true)
    private String fonts;

    @Basic
    @Column(name = "icons", nullable = true)
    private String icons;

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Basic
    @Column(name = "lightness", nullable = true)
    private String lightness;

    @Basic
    @Column(name = "name", nullable = false,length =255)
    private String name;

    @Basic
    @Column(name = "palette", nullable = true)
    private String palette;


}



