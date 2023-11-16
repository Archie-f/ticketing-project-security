INSERT INTO roles(description) VALUES('Admin');
INSERT INTO roles(description) VALUES('Manager');
INSERT INTO roles(description) VALUES('Employee');

INSERT INTO users(first_name,last_name,enabled,gender,role_id) VALUES('Phoebe','Buffay',true,'FEMALE',(SELECT r.id FROM roles r WHERE r.description = 'Admin'));
INSERT INTO users(first_name,last_name,enabled,gender,role_id) VALUES('Monica','Geller',true,'FEMALE',(SELECT r.id FROM roles r WHERE r.description = 'Manager'));
INSERT INTO users(first_name,last_name,enabled,gender,role_id) VALUES('Ross','Geller',true,'MALE',(SELECT r.id FROM roles r WHERE r.description = 'Manager'));
INSERT INTO users(first_name,last_name,enabled,gender,role_id) VALUES('Rachel','Green',true,'FEMALE',(SELECT r.id FROM roles r WHERE r.description = 'Employee'));
INSERT INTO users(first_name,last_name,enabled,gender,role_id) VALUES('Chandler','Bing',true,'MALE',(SELECT r.id FROM roles r WHERE r.description = 'Employee'));
INSERT INTO users(first_name,last_name,enabled,gender,role_id) VALUES('Joey','Tribiani',true,'MALE',(SELECT r.id FROM roles r WHERE r.description = 'Employee'));
