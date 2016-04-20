DELETE FROM physical_status;

INSERT INTO physical_status (id,code,name) VALUES (1,'OS','On schedule');
INSERT INTO physical_status (id,code,name) VALUES (2,'BS','Behind schedule');
INSERT INTO physical_status (id,code,name) VALUES (3,'AS','Ahead of schedule');
INSERT INTO physical_status (id,code,name) VALUES (4,'TE','Terminated');
INSERT INTO physical_status (id,code,name) VALUES (5,'SU','Suspended');
INSERT INTO physical_status (id,code,name) VALUES (6,'IN','Closed with incomplete output ');
