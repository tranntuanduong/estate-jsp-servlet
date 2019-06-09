use estate4month2019;

CREATE TABLE role (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	code VARCHAR(255) NOT NULL,
	
	createddate TIMESTAMP NULL,
	modifieddate TIMESTAMP NULL,
	createdby VARCHAR(255) NULL,
	modifiedby VARCHAR(255) NULL
);

CREATE TABLE user (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	
	username VARCHAR(150) NOT NULL,
	password VARCHAR(150) NOT NULL,
	fullname VARCHAR(150) NULL,
	status int NOT NULL,
	
	createddate TIMESTAMP NULL,
	modifieddate TIMESTAMP NULL,
	createdby VARCHAR(255) NULL,
	modifiedby VARCHAR(255) NULL
);

CREATE TABLE user_role (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	roleid BIGINT NOT NULL,
	userid BIGINT NOT NULL
);

ALTER TABLE user_role ADD CONSTRAINT fk_userrole_role FOREIGN KEY (roleid) REFERENCES role(id);
ALTER TABLE user_role ADD CONSTRAINT fk_userrole_user FOREIGN KEY  (userid) REFERENCES user(id);

CREATE TABLE building (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	
	name VARCHAR(255) NULL,
	numberofbasement INT NULL,
	buildingarea INT NULL,
	district VARCHAR(100) NULL,
	ward VARCHAR(100) NULL,
	street VARCHAR(100) NULL,
	structure VARCHAR(100) NULL,
	costrent INT NULL,
	costdescription TEXT NULL,
	servicecost VARCHAR(255) NULL,
	carcost VARCHAR(255) NULL,
	motorbikecost VARCHAR(255) NULL,
	overtimecost VARCHAR(255) NULL,
	electricitycost VARCHAR(255) NULL,
	deposit VARCHAR(255) NULL,
	payment VARCHAR(255) NULL,
	timerent VARCHAR(255) NULL,
	timedecorator VARCHAR(255) NULL,
	managername VARCHAR(255) NULL,
	managerphone VARCHAR(255) NULL,
	
	createddate TIMESTAMP NULL,
	modifieddate TIMESTAMP NULL,
	createdby VARCHAR(255) NULL,
	modifiedby VARCHAR(255) NULL
);

CREATE TABLE district (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	
	name VARCHAR(255) NOT NULL,
	code VARCHAR(255) NOT NULL,
	
	createddate TIMESTAMP NULL,
	modifieddate TIMESTAMP NULL,
	createdby VARCHAR(255) NULL,
	modifiedby VARCHAR(255) NULL
);

CREATE TABLE assignmentbuilding (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	
	buildingid BIGINT NOT NULL,
	userid BIGINT NOT NULL
);

ALTER TABLE assignmentbuilding ADD CONSTRAINT fk_assignment_user FOREIGN KEY (userid) REFERENCES user(id);
ALTER TABLE assignmentbuilding ADD CONSTRAINT fk_assignment_building FOREIGN KEY  (buildingid) REFERENCES building(id);






CREATE TABLE buildingtype (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,	
	buildingtype VARCHAR(255) NULL
);

CREATE TABLE building_buildingtype (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,	
	buildingid BIGINT NOT NULL,
	buildingtypeid BIGINT NOT NULL
);

ALTER TABLE building_buildingtype ADD CONSTRAINT fk_buildingbuildingtype_building FOREIGN KEY (buildingid) REFERENCES building(id);
ALTER TABLE building_buildingtype ADD CONSTRAINT fk_buildingbuildingtype_buildingtype FOREIGN KEY  (buildingid) REFERENCES buildingtype(id);


























