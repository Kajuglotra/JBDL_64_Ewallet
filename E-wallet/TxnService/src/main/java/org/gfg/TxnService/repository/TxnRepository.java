package org.gfg.TxnService.repository;

import jakarta.transaction.Transactional;
import org.gfg.TxnService.model.Txn;
import org.gfg.TxnService.model.TxnStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TxnRepository extends JpaRepository<Txn, Integer> {

    @Transactional

    @Modifying
    @Query("update Txn t set t.txnStatus=:status , t.message= :message where t.txnId=:txnId")
    void updateTxnStatus(String txnId, TxnStatus status,String message);

    Page<Txn> findBySenderId(String sender, Pageable pageable);
}
