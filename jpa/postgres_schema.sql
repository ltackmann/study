--
-- PostgreSQL database dump
--

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: addresses; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE addresses (
    id bigint NOT NULL,
    door_number character varying(255),
    street_name character varying(255) NOT NULL,
    floor_number character varying(255),
    zip character varying(255),
    street_number character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    countries_id bigint NOT NULL
);


ALTER TABLE public.addresses OWNER TO dbuser;

--
-- Name: audit_log; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE audit_log (
    id bigint NOT NULL,
    action character varying(255) NOT NULL,
    target character varying(255) NOT NULL,
    audit_time timestamp without time zone NOT NULL,
    audit_users_id bigint NOT NULL
);


ALTER TABLE public.audit_log OWNER TO dbuser;

--
-- Name: books; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE books (
    id bigint NOT NULL,
    isbn character varying(255) NOT NULL,
    pages integer NOT NULL,
    subtitle character varying(255),
    author character varying(255) NOT NULL
);


ALTER TABLE public.books OWNER TO dbuser;

--
-- Name: categories; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE categories (
    id bigint NOT NULL,
    name character varying(20) NOT NULL,
    description text
);


ALTER TABLE public.categories OWNER TO dbuser;

--
-- Name: countries; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE countries (
    id bigint NOT NULL,
    code character varying(2) NOT NULL
);


ALTER TABLE public.countries OWNER TO dbuser;

--
-- Name: employee_details; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE employee_details (
    emp_id bigint NOT NULL,
    account character varying(255) NOT NULL,
    emp_type character varying(255) NOT NULL,
    salary numeric(38,0) NOT NULL,
    min_budget numeric(38,0) NOT NULL,
    countries_id bigint NOT NULL
);


ALTER TABLE public.employee_details OWNER TO dbuser;

--
-- Name: login_log; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE login_log (
    id bigint NOT NULL,
    login_date timestamp without time zone NOT NULL,
    users_id bigint NOT NULL
);


ALTER TABLE public.login_log OWNER TO dbuser;

--
-- Name: music; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE music (
    id bigint NOT NULL,
    playtime integer NOT NULL,
    artist character varying(255) NOT NULL,
    tracks integer NOT NULL
);


ALTER TABLE public.music OWNER TO dbuser;

--
-- Name: order_items; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE order_items (
    discount numeric(38,0),
    unit_price numeric(38,0) NOT NULL,
    quantity integer NOT NULL,
    products_id bigint NOT NULL,
    orders_id bigint NOT NULL
);


ALTER TABLE public.order_items OWNER TO dbuser;

--
-- Name: orders; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE orders (
    id bigint NOT NULL,
    order_status integer NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    employee_id bigint NOT NULL,
    customer_id bigint NOT NULL
);


ALTER TABLE public.orders OWNER TO dbuser;

--
-- Name: product_has_categories; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE product_has_categories (
    products_id bigint NOT NULL,
    categories_id bigint NOT NULL
);


ALTER TABLE public.product_has_categories OWNER TO dbuser;

--
-- Name: products; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE products (
    id bigint NOT NULL,
    dtype character varying(31),
    unit_price numeric(38,0) NOT NULL,
    product_name character varying(255) NOT NULL,
    description text,
    image bytea,
    release_date date NOT NULL,
    suppliers_id bigint NOT NULL
);


ALTER TABLE public.products OWNER TO dbuser;

--
-- Name: sequence; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE sequence (
    seq_name character varying(50) NOT NULL,
    seq_count numeric(38,0)
);


ALTER TABLE public.sequence OWNER TO dbuser;

--
-- Name: software; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE software (
    id bigint NOT NULL,
    download_url character varying(255),
    vendor character varying(255),
    manual_url character varying(255),
    version character varying(255) NOT NULL
);


ALTER TABLE public.software OWNER TO dbuser;

--
-- Name: suppliers; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE suppliers (
    id bigint NOT NULL,
    contact_person_name character varying(50) NOT NULL,
    url character varying(255),
    name character varying(255) NOT NULL,
    addresses_id bigint NOT NULL
);


ALTER TABLE public.suppliers OWNER TO dbuser;

--
-- Name: users; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE users (
    id bigint NOT NULL,
    user_type character varying(31),
    active boolean NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    addresses_id bigint NOT NULL,
    phone_number character varying(255)
);


ALTER TABLE public.users OWNER TO dbuser;

--
-- Name: get_user(character varying); Type: FUNCTION; Schema: public; Owner: dbuser
--

CREATE FUNCTION get_user(v_username character varying) RETURNS refcursor
    AS $$
DECLARE 
        -- user users%ROWTYPE
	user_ref refcursor;
BEGIN
	OPEN user_ref FOR SELECT * FROM users WHERE username = v_username;
	RETURN user_ref;
END;
$$
    LANGUAGE plpgsql;


ALTER FUNCTION public.get_user(v_username character varying) OWNER TO dbuser;

--
-- Name: addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (id);


--
-- Name: audit_log_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY audit_log
    ADD CONSTRAINT audit_log_pkey PRIMARY KEY (id);


--
-- Name: books_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY books
    ADD CONSTRAINT books_pkey PRIMARY KEY (id);


--
-- Name: categories_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- Name: countries_code_key; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY countries
    ADD CONSTRAINT countries_code_key UNIQUE (code);


--
-- Name: countries_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY countries
    ADD CONSTRAINT countries_pkey PRIMARY KEY (id);


--
-- Name: employee_details_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY employee_details
    ADD CONSTRAINT employee_details_pkey PRIMARY KEY (emp_id);


--
-- Name: login_log_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY login_log
    ADD CONSTRAINT login_log_pkey PRIMARY KEY (id);


--
-- Name: music_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY music
    ADD CONSTRAINT music_pkey PRIMARY KEY (id);


--
-- Name: order_items_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY order_items
    ADD CONSTRAINT order_items_pkey PRIMARY KEY (products_id, orders_id);


--
-- Name: orders_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: product_has_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY product_has_categories
    ADD CONSTRAINT product_has_categories_pkey PRIMARY KEY (products_id, categories_id);


--
-- Name: products_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: sequence_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY sequence
    ADD CONSTRAINT sequence_pkey PRIMARY KEY (seq_name);


--
-- Name: software_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY software
    ADD CONSTRAINT software_pkey PRIMARY KEY (id);


--
-- Name: suppliers_name_key; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY suppliers
    ADD CONSTRAINT suppliers_name_key UNIQUE (name);


--
-- Name: suppliers_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY suppliers
    ADD CONSTRAINT suppliers_pkey PRIMARY KEY (id);


--
-- Name: unq_products_0; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY products
    ADD CONSTRAINT unq_products_0 UNIQUE (product_name, suppliers_id);


--
-- Name: users_email_key; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users_username_key; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: fk_addresses_countries_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY addresses
    ADD CONSTRAINT fk_addresses_countries_id FOREIGN KEY (countries_id) REFERENCES countries(id);


--
-- Name: fk_audit_log_audit_users_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY audit_log
    ADD CONSTRAINT fk_audit_log_audit_users_id FOREIGN KEY (audit_users_id) REFERENCES users(id);


--
-- Name: fk_books_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY books
    ADD CONSTRAINT fk_books_id FOREIGN KEY (id) REFERENCES products(id);


--
-- Name: fk_employee_details_countries_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY employee_details
    ADD CONSTRAINT fk_employee_details_countries_id FOREIGN KEY (countries_id) REFERENCES countries(id);


--
-- Name: fk_employee_details_emp_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY employee_details
    ADD CONSTRAINT fk_employee_details_emp_id FOREIGN KEY (emp_id) REFERENCES users(id);


--
-- Name: fk_login_log_users_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY login_log
    ADD CONSTRAINT fk_login_log_users_id FOREIGN KEY (users_id) REFERENCES users(id);


--
-- Name: fk_music_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY music
    ADD CONSTRAINT fk_music_id FOREIGN KEY (id) REFERENCES products(id);


--
-- Name: fk_order_items_orders_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY order_items
    ADD CONSTRAINT fk_order_items_orders_id FOREIGN KEY (orders_id) REFERENCES orders(id);


--
-- Name: fk_order_items_products_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY order_items
    ADD CONSTRAINT fk_order_items_products_id FOREIGN KEY (products_id) REFERENCES products(id);


--
-- Name: fk_orders_customer_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_orders_customer_id FOREIGN KEY (customer_id) REFERENCES users(id);


--
-- Name: fk_orders_employee_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_orders_employee_id FOREIGN KEY (employee_id) REFERENCES users(id);


--
-- Name: fk_product_has_categories_categories_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY product_has_categories
    ADD CONSTRAINT fk_product_has_categories_categories_id FOREIGN KEY (categories_id) REFERENCES categories(id);


--
-- Name: fk_product_has_categories_products_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY product_has_categories
    ADD CONSTRAINT fk_product_has_categories_products_id FOREIGN KEY (products_id) REFERENCES products(id);


--
-- Name: fk_products_suppliers_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_products_suppliers_id FOREIGN KEY (suppliers_id) REFERENCES suppliers(id);


--
-- Name: fk_software_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY software
    ADD CONSTRAINT fk_software_id FOREIGN KEY (id) REFERENCES products(id);


--
-- Name: fk_suppliers_addresses_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY suppliers
    ADD CONSTRAINT fk_suppliers_addresses_id FOREIGN KEY (addresses_id) REFERENCES addresses(id);


--
-- Name: fk_users_addresses_id; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_addresses_id FOREIGN KEY (addresses_id) REFERENCES addresses(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

