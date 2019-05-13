package com.sp.smartreads.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.sp.smartreads.dao.CustomerAccountDao;
import com.sp.smartreads.entity.ElectricityReading;
import com.sp.smartreads.entity.GasReading;
import com.sp.smartreads.model.AccountReadingDto;
import com.sp.smartreads.model.ElectricityRead;
import com.sp.smartreads.model.GasRead;

@RunWith(SpringRunner.class)
public class CustomerAccountServiceTest {

	@TestConfiguration
    static class CustomerAccountServiceTestContextConfiguration {
  
        @Bean
        public CustomerAccountService customerAccountService() {
            return new CustomerAccountServiceImpl();
        }
    }
	
	@Autowired
    private CustomerAccountService customerAccountService;
 
    @MockBean
    private CustomerAccountDao customerDao;
    
    @Before
    public void setUp() {
    	Date currentDate = new Date();
    	GasReading gasReading =  new GasReading();
    	gasReading.setMeterId("GM_123");
    	gasReading.setReadingDate(new java.sql.Date(currentDate.getTime()));
    	gasReading.setReadingValue(111.11f);
    	
    	ElectricityReading electricityReading =  new ElectricityReading();
    	electricityReading.setMeterId("EM_123");
    	electricityReading.setReadingDate(new java.sql.Date(currentDate.getTime()));
    	electricityReading.setReadingValue(222.22f);
    	
    	AccountReadingDto accountReadingDto = new AccountReadingDto("ACC_109", gasReading, electricityReading);
     
        Mockito.when(customerDao.getLatestAccountReading("ACC_109"))
          .thenReturn(accountReadingDto);
    }
    
    @Test
    public void whenValidCustomer_returnReadings() {
        String accountNumber = "ACC_109";
        AccountReadingDto accountReadingDto = customerAccountService.getLatestAccountReading(accountNumber);      
        assertThat(accountReadingDto.getAccountNumber(), equalTo(accountNumber)); 
        
        GasRead gasRead = accountReadingDto.getGasRead();
        assertThat(gasRead.getGasReading(), equalTo(111.11F));
		assertThat(gasRead.getGasMeterId(), equalTo("GM_123"));		
		ElectricityRead electricityRead = accountReadingDto.getElectricityRead();
		assertThat(electricityRead.getElectricityReading(), equalTo(222.22F));
		assertThat(electricityRead.getElectricityMeterId(), equalTo("EM_123"));
     }
    
    @Test(expected = EntityNotFoundException.class)
    public void whenInValidCustomer_throwException() {
        String accountNumber = "INVALID_109";
        customerAccountService.getLatestAccountReading(accountNumber);  
     }
}
