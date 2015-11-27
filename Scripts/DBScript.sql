CREATE DATABASE IF NOT EXISTS ManagementAppDB;



-- Table GroupsTable
CREATE TABLE GroupsTable (
    groupID int  NOT NULL  AUTO_INCREMENT,
    name varchar(255)  NOT NULL,
    PRIMARY KEY (GroupID)
);

-- Table RolesTable
CREATE TABLE RolesTable (
    roleID int  NOT NULL  AUTO_INCREMENT,
    role varchar(255)  NOT NULL,
    PRIMARY KEY (RoleID)
);

-- Table UsersTable
CREATE TABLE UsersTable (
    userID int  NOT NULL  AUTO_INCREMENT,
    userName varchar(255)  NOT NULL UNIQUE,
    email varchar(255)  NOT NULL UNIQUE,
    PRIMARY KEY (UserID)
);

-- Table MembeshipsTable
CREATE TABLE MembeshipsTable (
    membershipID int  NOT NULL  AUTO_INCREMENT,
    userID int  NOT NULL,
    groupID int  NOT NULL,
    roleID int  NOT NULL,
    PRIMARY KEY (membershipID,userID,groupID),
    FOREIGN KEY (userID) REFERENCES UsersTable(userID),
    FOREIGN KEY (groupID) REFERENCES GroupsTable(groupID),
    FOREIGN KEY (roleID) REFERENCES RolesTable(roleID)
);