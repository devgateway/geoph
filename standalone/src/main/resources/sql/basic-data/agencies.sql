DELETE FROM agency;

INSERT INTO agency (discriminator,id,code,name,funding_code,type_id) VALUES ('funding_agency',1,'ADB','Asian Development Bank','ADB',null);
INSERT INTO agency (discriminator,id,code,name,funding_code,type_id) VALUES ('funding_agency',2,'Germany','Germany','Others',null);
INSERT INTO agency (discriminator,id,code,name,funding_code,type_id) VALUES ('funding_agency',3,'GOJ/JICA','Japan International Cooperation Agency','GOJ/JICA',null);
INSERT INTO agency (discriminator,id,code,name,funding_code,type_id) VALUES ('funding_agency',4,'WB','World Bank','WB',null);
INSERT INTO agency (discriminator,id,code,name,funding_code,type_id) VALUES ('implementing_agency',5,'DPWH','DPWH',null,4);
INSERT INTO agency (discriminator,id,code,name,funding_code,type_id) VALUES ('implementing_agency',6,'DA','DA',null,4);
INSERT INTO agency (discriminator,id,code,name,funding_code,type_id) VALUES ('implementing_agency',7,'NIA','NIA',null,4);
INSERT INTO agency (discriminator,id,code,name,funding_code,type_id) VALUES ('implementing_agency',8,'LBP','LBP',null,1);
INSERT INTO agency (discriminator,id,code,name,funding_code,type_id) VALUES ('implementing_agency',9,'DPWH','MWSS',null,2);
