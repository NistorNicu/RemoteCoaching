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
    FOREIGN KEY (group_id) REFERENCES groups(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);