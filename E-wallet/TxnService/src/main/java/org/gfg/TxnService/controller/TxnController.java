package org.gfg.TxnService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.gfg.TxnService.model.Txn;
import org.gfg.TxnService.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/txn")
public class TxnController {

    @Autowired
    private TxnService txnService;

    @PostMapping("/initTxn")
    public String createUser(@RequestParam("amount") String amount,
                             @RequestParam("receiver") String receiver,
                             @RequestParam("purpose") String purpose) throws JsonProcessingException {
    UserDetails userDetails =  (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return txnService.initTxn(userDetails.getUsername(), receiver, amount, purpose);

    }

    @GetMapping("/getTxns")
    public List<Txn> getTxns(@RequestParam("page") int page,
                             @RequestParam("size") int size){

      UserDetails userDetails =  (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return txnService.getTxns(userDetails.getUsername(), page, size);

    }

}
