package org.vaadin.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.demo.model.Appointment;

/**
 * Created by andreas_h on 05.06.17.
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
