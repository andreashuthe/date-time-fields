package org.vaadin.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by andreas_h on 05.06.17.
 */
@Entity
@Audited
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column (name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Column (name="REMARK")
    @Getter @Setter private String remark;

    @Column (name="FROM_DATE")
    @Getter @Setter private Long fromDate;

    @Column (name="TO_DATE")
    @Getter @Setter private Long toDate;

}
