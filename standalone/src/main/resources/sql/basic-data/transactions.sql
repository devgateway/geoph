DELETE FROM transaction;

INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('grant',1,3000000.0,{ts '2015-08-15 00:00:00.0'},null,2,1,1);
	
INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('grant',2,2500000.0,{ts '2015-09-15 00:00:00.0'},null,2,1,2);
	
INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('grant',3,1800000.0,{ts '2015-10-15 00:00:00.0'},null,2,2,2);

INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('grant',4,1500000.0,{ts '2015-09-15 00:00:00.0'},null,2,2,1);
	
INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('grant',5,920000.0,{ts '2015-10-15 00:00:00.0'},null,2,2,2);

INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('grant',6,2000000.0,{ts '2015-08-15 00:00:00.0'},null,2,3,1);
	
INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('grant',7,2400000.0,{ts '2015-09-15 00:00:00.0'},null,2,3,2);
	
INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('grant',8,1500000.0,{ts '2015-10-15 00:00:00.0'},null,2,3,3);	

INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('loan',9,2000000.0,{ts '2015-08-15 00:00:00.0'},null,1,4,1);
	
INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('loan',10,2400000.0,{ts '2015-09-15 00:00:00.0'},null,1,4,2);
	
INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('loan',11,1500000.0,{ts '2015-10-15 00:00:00.0'},null,1,4,3);	

INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('loan',12,1800000.0,{ts '2015-10-15 00:00:00.0'},null,1,1,2);
	
INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('loan',13,1700000.0,{ts '2015-08-15 00:00:00.0'},null,1,5,1);
	
INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('loan',14,1200000.0,{ts '2015-09-15 00:00:00.0'},null,1,5,2);
	
INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('loan',15,1100000.0,{ts '2015-10-15 00:00:00.0'},null,1,5,3);	
	
INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('pmc',16,1600000.0,{ts '2015-10-15 00:00:00.0'},45,3,6,3);
	
INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('pmc',17,1600000.0,{ts '2015-10-15 00:00:00.0'},15,3,7,2);
	
INSERT INTO transaction (discriminator,id,amount,date,funding_support,flow_type_id,project_id,transaction_type_id) 
	VALUES ('pmc',18,1600000.0,{ts '2015-10-15 00:00:00.0'},15,3,7,3);
