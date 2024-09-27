package com.avengersblog.Data.Repository;

import com.avengersblog.Data.Model.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation,Long> {
    Confirmation findByToken(String Token);
}
