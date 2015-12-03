CREATE DATABASE IF NOT EXISTS Management_App_DB;
drop DATABASE Management_App_DB



-- Table GroupsTable
CREATE TABLE groups (
    id int  NOT NULL  AUTO_INCREMENT,
    name varchar(255)  NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

-- Table RolesTable
CREATE TABLE roles (
    id int  NOT NULL  AUTO_INCREMENT,
    role_name varchar(255)  NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

-- Table UsersTable
CREATE TABLE users (
    id int  NOT NULL  AUTO_INCREMENT,
    user_name varchar(255)  NOT NULL UNIQUE,
    email varchar(255)  NOT NULL UNIQUE,
    group_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (group_id) REFERENCES groups(id)
);

-- Table GroupsRolesTable
CREATE TABLE group_role (
    group_id int  NOT NULL,
    role_id int  NOT NULL,
    PRIMARY KEY (group_id, role_id),
    FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- triger before insert users table 
DELIMITER $$
create trigger prevent_empty_strings_insertion_users before insert on users
     for each row
     begin
     if new.user_name = '' then set new.user_name = NULL;
     ELSEIF new.email = '' THEN SET new.email = NULL;
     end if;
     end;$$
DELIMITER ;

-- triger before update users table 
DELIMITER $$
create trigger prevent_empty_strings_update_users before insert on users
     for each row
     begin
     if new.user_name = '' then set new.user_name = NULL;
     ELSEIF new.email = '' THEN SET new.email = NULL;
     end if;
     end;$$
DELIMITER ;

-- triger before insert roles table 
DELIMITER $$
create trigger prevent_empty_strings_insertion_roles before insert on roles
     for each row
     begin
     if new.role_name = '' then set new.role_name = NULL;
     end if;
     end;$$
DELIMITER ;
-- triger before update roles table 
DELIMITER $$
create trigger prevent_empty_strings_update_roles before update on roles
     for each row
     begin
     if new.role_name = '' then set new.role_name = NULL;
     end if;
     end;$$
DELIMITER ;

-- triger before insert groups table 
DELIMITER $$
create trigger prevent_empty_strings_insertion_groups before insert on groups
     for each row
     begin
     if new.name = '' then set new.name = NULL;
     end if;
     end;$$
DELIMITER ;
-- triger before update groups table 
DELIMITER $$
create trigger prevent_empty_strings_update_groups before update on groups
     for each row
     begin
     if new.name = '' then set new.name = NULL;
     end if;
     end;$$
DELIMITER ;