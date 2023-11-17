INSERT INTO roles(description) VALUES('Admin');
INSERT INTO roles(description) VALUES('Manager');
INSERT INTO roles(description) VALUES('Employee');

INSERT INTO users(first_name,last_name,enabled,gender,role_id,is_deleted) VALUES('Phoebe','Buffay',true,'FEMALE',(SELECT r.id FROM roles r WHERE r.description = 'Admin'),false);
INSERT INTO users(first_name,last_name,enabled,gender,role_id,is_deleted) VALUES('Monica','Geller',true,'FEMALE',(SELECT r.id FROM roles r WHERE r.description = 'Manager'),false);
INSERT INTO users(first_name,last_name,enabled,gender,role_id,is_deleted) VALUES('Ross','Geller',true,'MALE',(SELECT r.id FROM roles r WHERE r.description = 'Manager'),false);
INSERT INTO users(first_name,last_name,enabled,gender,role_id,is_deleted) VALUES('Rachel','Green',true,'FEMALE',(SELECT r.id FROM roles r WHERE r.description = 'Employee'),false);
INSERT INTO users(first_name,last_name,enabled,gender,role_id,is_deleted) VALUES('Chandler','Bing',true,'MALE',(SELECT r.id FROM roles r WHERE r.description = 'Employee'),false);
INSERT INTO users(first_name,last_name,enabled,gender,role_id,is_deleted) VALUES('Joey','Tribiani',true,'MALE',(SELECT r.id FROM roles r WHERE r.description = 'Employee'),false);
