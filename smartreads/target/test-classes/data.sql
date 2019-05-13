--accountWith_MultipleElectricityAndGasReadings
insert into customer (customer_id, first_name, last_name, date_of_birth, address, post_code) 
values (1, 'Susan', 'Inglis' , TO_DATE('19-Aug-1979'), '17 Saltmarket Road', 'G1 5PQ');
insert into account (account_id, account_number, customer_id) 
values (1, 'SI_1987', 1);
insert into electricity_reading (electricity_reading_id, account_id, meter_id, reading_value, reading_date, is_latest_reading, reading_number) 
values (1, 1, 'SEM_156', 100, TO_DATE('01-Jan-2019'), FALSE, 1);
insert into electricity_reading (electricity_reading_id, account_id, meter_id, reading_value, reading_date, is_latest_reading, reading_number) 
values (2, 1, 'SEM_156', 150, TO_DATE('01-Feb-2019'), TRUE, 2);

insert into gas_reading (gas_reading_id, account_id, meter_id, reading_value, reading_date, is_latest_reading, reading_number) 
values (1, 1, 'SGM_135', 200, TO_DATE('01-Jan-2019'), FALSE, 1);
insert into gas_reading (gas_reading_id, account_id, meter_id, reading_value, reading_date, is_latest_reading, reading_number) 
values (2, 1, 'SGM_135', 250, TO_DATE('01-Feb-2019'), TRUE, 2);


--accountWith_SingleElectricityReading_And_MultipleGasReadings
insert into customer (customer_id, first_name, last_name, date_of_birth, address, post_code) 
values (2, 'Berlin', 'Nadar' , TO_DATE('29-Mar-1989'), '25 St Andrews Square', 'G1 5PQ');
insert into account (account_id, account_number, customer_id) 
values (2, 'BN_987', 2);

insert into electricity_reading (electricity_reading_id, account_id, meter_id, reading_value, reading_date, is_latest_reading, reading_number) 
values (101, 2, 'SEM_256', 1000, TO_DATE('01-Jan-2019'), TRUE, 1);

insert into gas_reading (gas_reading_id, account_id, meter_id, reading_value, reading_date, is_latest_reading, reading_number) 
values (101, 2, 'SGM_235', 2000, TO_DATE('01-Jan-2019'), FALSE, 1);
insert into gas_reading (gas_reading_id, account_id, meter_id, reading_value, reading_date, is_latest_reading, reading_number) 
values (102, 2, 'SGM_235', 2500, TO_DATE('01-Feb-2019'), TRUE, 2);

--accountWith_OnlyElectricityReading
insert into customer (customer_id, first_name, last_name, date_of_birth, address, post_code) 
values (3, 'Vijay', 'V' , TO_DATE('06-Dec-1984'), '21 St Andrews Square', 'G1 5PQ');
insert into account (account_id, account_number, customer_id) 
values (3, 'VV_111', 3);

insert into electricity_reading (electricity_reading_id, account_id, meter_id, reading_value, reading_date, is_latest_reading, reading_number) 
values (301, 3, 'SEM_1112', 112.5, TO_DATE('01-Mar-2019'), TRUE, 1);

--accountWith_OnlyGasReading
insert into customer (customer_id, first_name, last_name, date_of_birth, address, post_code) 
values (4, 'Kim', 'Clark' , TO_DATE('06-Dec-1981'), '20 St Andrews Square', 'G1 5PQ');
insert into account (account_id, account_number, customer_id) 
values (4, 'KC_123', 4);

insert into gas_reading (gas_reading_id, account_id, meter_id, reading_value, reading_date, is_latest_reading, reading_number) 
values (401, 4, 'SEM_1231', 25.78, TO_DATE('01-Mar-2019'), TRUE, 1);

--accountWith_noReading
insert into customer (customer_id, first_name, last_name, date_of_birth, address, post_code) 
values (5, 'William', 'Templeton' , TO_DATE('06-Dec-1971'), '19 St Andrews Square', 'G1 5PQ');
insert into account (account_id, account_number, customer_id) 
values (5, 'WT_123', 5);
