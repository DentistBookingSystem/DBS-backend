package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.componentform.AccountAndViolationTimes;
import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.model.AccountDTO;
import com.rade.dentistbookingsystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/admin/account")
public class AccountAdminController {
    @Autowired
    AccountService accountService;

    @GetMapping("violated/page/{page}")
    public List<AccountAndViolationTimes> getViolatedAccounts(@PathVariable int page){
        Pageable pageable = PageRequest.of(page - 1, 3);
        return accountService.findViolatedAccountsAndViolationTimes(pageable);
    }

    @GetMapping("accountDetail/{phone}")
    public Account findByPhone(@PathVariable String phone) {
        return accountService.view(phone);
    }


    @GetMapping("list/{roleId}/{status}/phone={phone}")
    public List<Account> getAccountList(@PathVariable(name = "roleId") int roleId, @PathVariable(name = "status") short status, @PathVariable(name = "phone") String phone){
        if(phone != null && phone.length() > 0) phone = "%" + phone + "%";
           return  accountService.getAccountList(roleId, status, phone);
    }


    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody AccountDTO accountDTO) throws Exception {

            final int ROLE_STAFF = 3;
            Account account = accountService.registerNewUserAccount(accountDTO, ROLE_STAFF);
            if(account != null)
                return ResponseEntity.ok("Register successfully");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not register");
    }

    @PostMapping("profile/edit")
    public ResponseEntity<?> editStaff(@RequestBody AccountDTO accountDTO) throws Exception {
        Account account = accountService.edit(accountDTO);
        if(account != null)
            return ResponseEntity.ok("Edit successfully");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not Edit");
    }

    @GetMapping("remove/{id}")
    public ResponseEntity<?> removeStaff(@PathVariable Integer id){
        try {
            Account account = accountService.findId(id);
            if(account.getRole().getId() == 3) accountService.checkAccount(2, id);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.ok("Remove Successfully");



    }

}
