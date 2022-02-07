CREATE TABLE CUSTOMERS(
    customerID int,
    firstname varchar(255),
    lastname varchar(255),
    age int,
    PRIMARY KEY (customerID)

);

CREATE TABLE ORDERS(
    orderID int ,
    orderNum int,
    customerID int,
    PRIMARY KEY (orderID),
    FOREIGN KEY (customerID) REFERENCES CUSTOMERS(customerID)
);

CREATE TABLE FOOD(
    foodID int,
    appetizer varchar(255),
    entree varchar(255),
    dessert varchar(255),
    orderID int,
    PRIMARY KEY (foodID),
    FOREIGN KEY (orderID) REFERENCES ORDERS(orderID)
);