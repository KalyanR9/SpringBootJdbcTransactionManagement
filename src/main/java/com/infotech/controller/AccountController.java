package com.infotech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotech.dao.exception.InsufficientAccountBalanceException;
import com.infotech.model.RequestData;
import com.infotech.service.BankService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/")
@Api(value = "Transfer Amount")
public class AccountController {

	@Autowired
	private BankService bankService;

	@ApiOperation(value = "transfer amount", response = List.class)
	@PostMapping(value = "transfer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void list(@RequestBody RequestData requestData) throws InsufficientAccountBalanceException {
		bankService.transferFund(requestData.getAccountNoFrom(), requestData.getAccountNoTo(), requestData.getAmount());
	}

}
