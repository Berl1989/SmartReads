package com.sp.smartreads.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sp.smartreads.model.AccountReadingDto;
import com.sp.smartreads.model.ElectricityRead;
import com.sp.smartreads.model.GasRead;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerAccountDaoTest {

	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private CustomerAccountDao customerAccountDao;
    
	@Test
	public void accountWith_MultipleElectricityAndGasReadings() throws ParseException {
		AccountReadingDto accountReadingDto = customerAccountDao.getLatestAccountReading("SI_1987");
		assertThat(accountReadingDto.getAccountNumber(), equalTo("SI_1987"));
		
		GasRead gasRead = accountReadingDto.getGasRead();
		assertThat(gasRead.getGasReading(), equalTo(250.0F));
		assertThat(gasRead.getGasReadingDate(), equalTo(parseDate("2019-02-01")));
		assertThat(gasRead.getGasMeterId(), equalTo("SGM_135"));
		
		ElectricityRead electricityRead = accountReadingDto.getElectricityRead();
		assertThat(electricityRead.getElectricityReading(), equalTo(150.0F));
		assertThat(electricityRead.getElectricityReadingDate(), equalTo(parseDate("2019-02-01")));
		assertThat(electricityRead.getElectricityMeterId(), equalTo("SEM_156"));
		
	}
	
	@Test
	public void accountWith_SingleElectricityReading_And_MultipleGasReadings() throws ParseException {
		AccountReadingDto accountReadingDto = customerAccountDao.getLatestAccountReading("BN_987");
		assertThat(accountReadingDto.getAccountNumber(), equalTo("BN_987"));
		
		GasRead gasRead = accountReadingDto.getGasRead();
		assertThat(gasRead.getGasReading(), equalTo(2500.0F));
		assertThat(gasRead.getGasReadingDate(), equalTo(parseDate("2019-02-01")));
		assertThat(gasRead.getGasMeterId(), equalTo("SGM_235"));
		
		ElectricityRead electricityRead = accountReadingDto.getElectricityRead();
		assertThat(electricityRead.getElectricityReading(), equalTo(1000.0F));
		assertThat(electricityRead.getElectricityReadingDate(), equalTo(parseDate("2019-01-01")));
		assertThat(electricityRead.getElectricityMeterId(), equalTo("SEM_256"));
		
	}
	
	@Test
	public void accountWith_OnlyElectricityReading() throws ParseException {
		AccountReadingDto accountReadingDto = customerAccountDao.getLatestAccountReading("VV_111");
		assertThat(accountReadingDto.getAccountNumber(), equalTo("VV_111"));
		
		ElectricityRead electricityRead = accountReadingDto.getElectricityRead();
		assertThat(electricityRead.getElectricityReading(), equalTo(112.5F));
		assertThat(electricityRead.getElectricityReadingDate(), equalTo(parseDate("2019-03-01")));
		assertThat(electricityRead.getElectricityMeterId(), equalTo("SEM_1112"));
		
		assertThat(accountReadingDto.getGasRead(), is(IsNull.nullValue()));
		
	}
	
	@Test
	public void accountWith_OnlyGasReading() throws ParseException {
		AccountReadingDto accountReadingDto = customerAccountDao.getLatestAccountReading("KC_123");
		assertThat(accountReadingDto.getAccountNumber(), equalTo("KC_123"));
		
		GasRead gasRead = accountReadingDto.getGasRead();
		assertThat(gasRead.getGasReading(), equalTo(25.78F));
		assertThat(gasRead.getGasReadingDate(), equalTo(parseDate("2019-03-01")));
		assertThat(gasRead.getGasMeterId(), equalTo("SEM_1231"));
		
		assertThat(accountReadingDto.getElectricityRead(), is(IsNull.nullValue()));
		
	}
	
	@Test
	public void accountWith_NoReading_NewAccount() throws ParseException {
		AccountReadingDto accountReadingDto = customerAccountDao.getLatestAccountReading("WT_123");
		assertThat(accountReadingDto.getAccountNumber(), equalTo("WT_123"));
		assertThat(accountReadingDto.getGasRead(), is(IsNull.nullValue()));
		assertThat(accountReadingDto.getElectricityRead(), is(IsNull.nullValue()));
		
	}
	
	@Test
	public void invalidAccountNumberReturnsNullDtoObject() throws ParseException {
		AccountReadingDto accountReadingDto = customerAccountDao.getLatestAccountReading("ABC_109");
		assertThat(accountReadingDto, is(IsNull.nullValue()));		
	}
	
	
	private Date parseDate(String dateValue) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.parse(dateValue);
	}
}