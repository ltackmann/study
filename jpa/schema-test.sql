create sequence hibernate_sequence start with 1 increment by 1
create table ADDRESSES (id bigint not null, city varchar(255) not null, DOOR_NUMBER varchar(255), FLOOR_NUMBER varchar(255), STREET_NAME varchar(255) not null, STREET_NUMBER varchar(255) not null, zip varchar(255), COUNTRIES_ID bigint not null, primary key (id))
create table AUDIT_LOG (id bigint not null, AUDIT_TIME timestamp not null, action varchar(255) not null, target varchar(255) not null, AUDIT_USERS_ID bigint not null, primary key (id))
create table BOOKS (author varchar(255) not null, isbn varchar(255) not null, PAGES integer not null, subTitle varchar(255), id bigint not null, primary key (id))
create table CATEGORIES (id bigint not null, NAME varchar(20) not null, description clob(255) not null, primary key (id))
create table COUNTRIES (id bigint not null, code varchar(2) not null, name varchar(255) not null, primary key (id))
create table EMPLOYEE_DETAILS (ACCOUNT varchar(255) not null, EMP_TYPE varchar(255) not null, MIN_BUDGET numeric(19,2) not null, salary numeric(19,2) not null, COUNTRIES_ID bigint not null, EMP_ID bigint not null, primary key (EMP_ID))
create table LOGIN_LOG (id bigint not null, LOGIN_DATE timestamp not null, USERS_ID bigint not null, primary key (id))
create table Music (artist varchar(255) not null, playtime integer not null, tracks integer not null, id bigint not null, primary key (id))
create table ORDER_ITEMS (ORDERS_ID bigint not null, PRODUCTS_ID bigint not null, discount numeric(19,2), quantity integer not null, UNIT_PRICE numeric(19,2) not null, primary key (ORDERS_ID, PRODUCTS_ID))
create table ORDERS (id bigint not null, CREATION_DATE timestamp not null, ORDER_STATUS integer not null, customer_id bigint not null, employee_id bigint not null, primary key (id))
create table PRODUCT_HAS_CATEGORIES (PRODUCTS_ID bigint not null, CATEGORIES_ID bigint not null, primary key (PRODUCTS_ID, CATEGORIES_ID))
create table PRODUCTS (id bigint not null, description clob(255), image blob(255), PRODUCT_NAME varchar(255) not null, RELEASE_DATE date not null, UNIT_PRICE numeric(19,2) not null, SUPPLIERS_ID bigint not null, primary key (id))
create table Software (DOWNLOAD_URL varchar(255), MANUAL_URL varchar(255), vendor varchar(255), version varchar(255) not null, id bigint not null, primary key (id))
create table SUPPLIERS (id bigint not null, CONTACT_PERSON_NAME varchar(50) not null, name varchar(255) not null, url varchar(255), ADDRESSES_ID bigint not null, primary key (id))
create table USERS (USER_TYPE integer not null, id bigint not null, active boolean not null, CREATION_DATE timestamp not null, email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, username varchar(255) not null, PHONE_NUMBER varchar(255), ADDRESSES_ID bigint not null, primary key (id))
alter table ADDRESSES add constraint FKn731usc6ka0s0hmwj9h9yqmb2 foreign key (COUNTRIES_ID) references COUNTRIES
alter table AUDIT_LOG add constraint FKsq0qhe37hfl1o6qkm3l27ghv3 foreign key (AUDIT_USERS_ID) references USERS
alter table BOOKS add constraint FK1x7dfxdereqktdydiihux5v9w foreign key (id) references PRODUCTS
alter table COUNTRIES add constraint UK_58xm3nhaqtmyyynjmqui2xj0u unique (code)
alter table COUNTRIES add constraint UK_bnqj16x40slnn8hxdi0uqodvf unique (name)
alter table EMPLOYEE_DETAILS add constraint FKieqw2mb4n36uv0iafa3kvp43g foreign key (COUNTRIES_ID) references COUNTRIES
alter table EMPLOYEE_DETAILS add constraint FKvkub7tr9ikalyp2nth1qkpht foreign key (EMP_ID) references USERS
alter table LOGIN_LOG add constraint FKkesg98k6s6fbdh52t0ewrrohw foreign key (USERS_ID) references USERS
alter table Music add constraint FKsueuj3083ydntngpy0ltgyg8c foreign key (id) references PRODUCTS
alter table ORDER_ITEMS add constraint FK6tytfo3mhfo558lgd953xjg4o foreign key (ORDERS_ID) references ORDERS
alter table ORDER_ITEMS add constraint FKoaxbskyn2l1vcbriym3nrfunc foreign key (PRODUCTS_ID) references PRODUCTS
alter table ORDERS add constraint FKm3ufos31bau5kja4wxp2urxgg foreign key (customer_id) references USERS
alter table ORDERS add constraint FK3twar2a6gqtykt39fxhuy5s7v foreign key (employee_id) references USERS
alter table PRODUCT_HAS_CATEGORIES add constraint FKe9h0gwutbpypsqd3pymqtlpsw foreign key (CATEGORIES_ID) references CATEGORIES
alter table PRODUCT_HAS_CATEGORIES add constraint FKf3fsb04jv1dkh4v8gyjsu5a1 foreign key (PRODUCTS_ID) references PRODUCTS
alter table PRODUCTS add constraint UKjhprgkmri59rnrdfb176w8mqk unique (PRODUCT_NAME, SUPPLIERS_ID)
alter table PRODUCTS add constraint FKq5c1mmeljcynsx4n4ru3qshxd foreign key (SUPPLIERS_ID) references SUPPLIERS
alter table Software add constraint FKlljcl9rv1v4frlomlw5opifqk foreign key (id) references PRODUCTS
alter table SUPPLIERS add constraint UK_1obqy34g62xci6g36bm2ufq0l unique (name)
alter table SUPPLIERS add constraint FKcmsec8wm8mmfv53eyyrrcybjb foreign key (ADDRESSES_ID) references ADDRESSES
alter table USERS add constraint UK_avh1b2ec82audum2lyjx2p1ws unique (email)
alter table USERS add constraint UK_dc4eq7plr20fdhq528twsak1b unique (username)
alter table USERS add constraint FKgkcxmrjd29400rnh54auil3oy foreign key (ADDRESSES_ID) references ADDRESSES
