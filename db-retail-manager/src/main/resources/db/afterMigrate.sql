-- Init default data

INSERT INTO SHOP(NAME) VALUES('City Hall Shop');
INSERT INTO SHOP(NAME) VALUES('Hyde Park Shop');
INSERT INTO SHOP(NAME) VALUES('Paddington Shop');

INSERT INTO ADDRESS(NUMBER,POST_CODE,LATITUDE,LONGITUDE,SHOP_ID)
VALUES('The Queens Walk','SE1 2AA','51.504721','-0.078804',1);
INSERT INTO ADDRESS(NUMBER,POST_CODE,LATITUDE,LONGITUDE,SHOP_ID)
VALUES('Knightsbridge','SW1X 7LY','51.503208','-0.152899',2);
INSERT INTO ADDRESS(NUMBER,POST_CODE,LATITUDE,LONGITUDE,SHOP_ID)
VALUES('Paddington Green','W2 1HB','51.515593','-0.176654',3);