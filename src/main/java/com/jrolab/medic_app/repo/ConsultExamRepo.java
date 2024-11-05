package com.jrolab.medic_app.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jrolab.medic_app.model.ConsultExam;
import com.jrolab.medic_app.model.ConsultExamPK;

public interface ConsultExamRepo extends GenericRepo<ConsultExam, ConsultExamPK> {
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO consult_exam(id_consult, id_exam)VALUES(:idConsult, :idExam)", nativeQuery = true)
    Integer saveExam(@Param("idConsult") Integer idConsult, @Param("idExam") Integer idExam);
}
