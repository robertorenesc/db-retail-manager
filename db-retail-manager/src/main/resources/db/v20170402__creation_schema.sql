CREATE TABLE SHOP (
	ID INT NOT NULL IDENTITY,
	NAME varchar(25) NOT NULL,
	CONSTRAINT SHOP_PK PRIMARY KEY (ID),
	CONSTRAINT SHOP_UK UNIQUE (NAME)
);

CREATE TABLE ADDRESS (
	ID INT NOT NULL IDENTITY,
	NUMBER VARCHAR NOT NULL,
	POST_CODE VARCHAR(25) NOT NULL,
	LATITUDE VARCHAR(50) NOT NULL,
	LONGITUDE VARCHAR(50) NOT NULL,
	SHOP_ID INT NOT NULL,
	ACTIVE BOOL NOT NULL,
	CONSTRAINT ADDRESS_PK PRIMARY KEY (ID),
	CONSTRAINT ADDRESS_SHOP_FK FOREIGN KEY (SHOP_ID) REFERENCES SHOP(ID)
);