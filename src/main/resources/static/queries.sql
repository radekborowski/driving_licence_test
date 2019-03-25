/*ROLES*/
insert into driving_licence_test.role(role_id, role) values (1, 'ADMIN');
insert into driving_licence_test.role(role_id,role) values (2, 'USER');

/*users*/
insert into driving_licence_test.user(id,active, name, password)
values (1,1,'ADMIN','$2a$10$ioJyc3RGGs3W7RC0KeO19ucU2C0mJsckLiQ470MPlQ8H2JBRRid6e');
insert into driving_licence_test.user(id,active, name, password)
values (2,1,'USER','$2a$10$3kKVRSRABXY/n.wDuu15C.2QKktIUvJPzUJcDY3XCtwW9coNAm/AW');

/*ADD ROLE TO USER*/
insert into driving_licence_test.user_role(user_id, role_id) values (1,1);
insert into driving_licence_test.user_role(user_id, role_id) values (1,2);
insert into driving_licence_test.user_role(user_id, role_id) values (2,2);