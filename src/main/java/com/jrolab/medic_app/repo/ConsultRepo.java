package com.jrolab.medic_app.repo;

import com.jrolab.medic_app.dto.ConsultProcDTO;
import com.jrolab.medic_app.dto.IConsultDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jrolab.medic_app.model.Consult;

import java.util.List;
import java.time.LocalDateTime;
public interface ConsultRepo extends GenericRepo<Consult, Integer> {

    @Query("FROM Consult c WHERE c.patient.dni = :dni OR LOWER(c.patient.firstName) LIKE %:fullname% OR LOWER(c.patient.lastName) LIKE %:fullname%")
    List<Consult> search(@Param("dni") String dni, @Param("fullname") String fullname);
     // >= | <
    // 01-08-24 | 29-08-24 +1

    @Query("FROM Consult c WHERE c.consultDate BETWEEN :date1 AND :date2")
    List<Consult> searchByDates(@Param("date1") LocalDateTime date1, @Param("date2") LocalDateTime date2);

    @Query(value = "select * from fn_list()", nativeQuery = true)
    List<Object[]> callProcedureOrFunctionNative();

    @Query(value = "select * from fn_list()", nativeQuery = true)
    List<IConsultDTO> callProcedureOrFunctionProjection();

}
