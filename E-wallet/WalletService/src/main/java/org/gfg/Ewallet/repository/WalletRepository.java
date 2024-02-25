package org.gfg.Ewallet.repository;

import org.gfg.Ewallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    Wallet findByContact(String contact);
    @Transactional
    @Modifying
    @Query("update Wallet w set w.balance = w.balance+:amount where w.contact = :contact")
    void updateWallet(String contact, Double amount);
}
