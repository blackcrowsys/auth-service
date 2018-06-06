CREATE TABLE ORGANISATION(
    ID				        VARCHAR(250) NOT NULL,
    NAME                    VARCHAR(50) NOT NULL,
    STREET                  VARCHAR(100),
    TOWN                    VARCHAR(50),
    COUNTY                  VARCHAR(30),
    POSTCODE                VARCHAR(15),
    PRIMARY KEY(ID)
);

CREATE TABLE LOGIN(
    ID                  	VARCHAR(250) NOT NULL,
    USERNAME                VARCHAR(100) NOT NULL UNIQUE,
    PASSWORD                VARCHAR(250),
    ORGANISATION_ID         VARCHAR(250) NOT NULL,
    ACCOUNTNONEXPIRED       BOOLEAN DEFAULT TRUE,
    ACCOUNTNONLOCKED        BOOLEAN DEFAULT TRUE,
    CREDENTIALSNONEXPIRED   BOOLEAN DEFAULT TRUE,
    ENABLED                 BOOLEAN DEFAULT TRUE,
    FIRSTNAME               VARCHAR(250),
    SURNAME                 VARCHAR(250),
    TYPE                    VARCHAR(250),
    EMAIL                   VARCHAR(250),
    PRIMARY KEY(ID),
    FOREIGN KEY(ORGANISATION_ID) REFERENCES ORGANISATION(ID)
);