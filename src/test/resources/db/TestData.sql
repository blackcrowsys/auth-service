insert into ORGANISATION(ID, NAME, STREET, TOWN, COUNTY, POSTCODE) values
('5331440c-4fbe-4ce6-9a7b-61a96f95d465', 'Crow Hotel', 'North Road', 'Hounslow', 'Middlesex', 'TW4 6YY');

insert into LOGIN (ID, USERNAME, PASSWORD, ORGANISATION_ID, FIRSTNAME, SURNAME, TYPE, EMAIL) values
('bdd6f2a4-75d7-48c5-a881-2e82b8c5c88b', 'RM201', '{bcrypt}$2a$10$6h3WdL1lm0C2.OMIsYcubOVgnud6HCd0sLPL0QuPEqax.pK3kabzK', '5331440c-4fbe-4ce6-9a7b-61a96f95d465', '201', 'Crow Hotel', 'ROOM', null),
('2c16077b-5bec-48ef-98d6-327e3a32301a', 'testuser', '{bcrypt}$2a$10$Xur.DdObso6YD5ckWaWFB.NHz.qwnh4PzOstJK3MZ/XaqbrC4m8KO', '5331440c-4fbe-4ce6-9a7b-61a96f95d465', 'Test', 'User', 'STAFF', 'ramindur.singh@blackcrowsys.com');
