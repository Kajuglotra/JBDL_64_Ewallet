package org.gfg.Ewallet.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gfg.Ewallet.model.Wallet;
import org.gfg.Ewallet.repository.WalletRepository;
import org.gfg.Utils.CommonConstants;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TxnInitiatedConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private KafkaTemplate kafkaTemplate;


    @KafkaListener(topics = CommonConstants.TXN_INITIATED_TOPIC, groupId = "wallet-group")
    public void updateWallet(String msg) throws JsonProcessingException {
        JSONObject jsonObject = objectMapper.readValue(msg, JSONObject.class);

        String sender = (String) jsonObject.get(CommonConstants.TXN_INITIATED_TOPIC_SENDER);
        String receiver = (String) jsonObject.get(CommonConstants.TXN_INITIATED_TOPIC_RECEIVER);
        String amount = (String) jsonObject.get(CommonConstants.TXN_INITIATED_TOPIC_AMOUNT);
        String txnId = (String) jsonObject.get(CommonConstants.TXN_INITIATED_TOPIC_TXNID);

        Wallet senderWallet = walletRepository.findByContact(sender);
        Wallet receiverWallet = walletRepository.findByContact(receiver);

        String message = "txn is is initiated state";
        String status = "pending";

        if(senderWallet == null){
            message = "sender wallet is not associated with us";
            status= "failure";
        }else if(receiverWallet == null){
            message = "receiver wallet is not associated with us";
            status= "failure";
        }else if(Double.valueOf(amount) > senderWallet.getBalance()){
            message = "sender wallet amount is lesser than the amount for which he wants to make a txn for ";
            status= "failure";
        }else{

            walletRepository.updateWallet(sender, -Double.valueOf(amount));
            walletRepository.updateWallet(receiver, Double.valueOf(amount));
            message = "txn is success";
            status = "success";
        }
        //kafka published

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put(CommonConstants.TXN_UPDATED_TOPIC_MESSAGE, message);
        jsonObject1.put(CommonConstants.TXN_UPDATED_TOPIC_STATUS , status);
        jsonObject1.put(CommonConstants.TXN_UPDATED_TOPIC_TXNID , txnId);

        kafkaTemplate.send(CommonConstants.TXN_UPDATED_TOPIC, jsonObject1);

    }
}
