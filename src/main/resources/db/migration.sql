CREATE EXTENSION Postgis;

CREATE TABLE orders (
            order_id VARCHAR(100) PRIMARY KEY,
            area_id BIGINT NOT NULL,
            city_id BIGINT NOT NULL,
            assign_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
            received_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
            created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
            location GEOGRAPHY(POINT,4326)
            from_restaurant_location GEOGRAPHY(POINT,4326)
);

CREATE TABLE delivery_boys (
            id VARCHAR(100) PRIMARY KEY,
            location GEOGRAPHY(POINT,4326),
            created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX delivery_boys_gix ON delivery_boys USING GIST (Geography(location));

INSERT INTO delivery_boys (id, location) VALUES ('22822a54-0873-11e7-808a-9801a7b20ef6',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('22822a54-0873-11e7-808a-9801a7b20ef6',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('22822a54-0873-11e7-808a-9801a7b20ef7',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('22822a54-0873-11e7-808a-9801a7b20ef8',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('22822a54-0873-11e7-808a-9801a7b20ef9',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('22822a54-0873-11e7-808a-9801a7b20e10',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('22822a54-0873-11e7-808a-9801a7b20e11',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('22822a54-0873-11e7-808a-9801a7b20e12',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('22822a54-0873-11e7-808a-9801a7b20e13',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('22822a54-0873-11e7-808a-9801a7b20e14',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));

insert into orders (order_id, area_id, city_id, location)
values ('22822a54-0873-11e7-808a-9801a7b20e15', 7, 1, ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
insert into orders (order_id, area_id, city_id, location)
values ('22822a54-0873-11e7-808a-9801a7b20e16', 7, 1, ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
insert into orders (order_id, area_id, city_id, location)
values ('22822a54-0873-11e7-808a-9801a7b20e17', 7, 1, ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
insert into orders (order_id, area_id, city_id, location)
values ('22822a54-0873-11e7-808a-9801a7b20e18', 7, 1, ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
insert into orders (order_id, area_id, city_id, location)
values ('22822a54-0873-11e7-808a-9801a7b20e19', 7, 1, ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
insert into orders (order_id, area_id, city_id, location)
values ('22822a54-0873-11e7-808a-9801a7b20e20', 7, 1, ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
insert into orders (order_id, area_id, city_id, location)
values ('22822a54-0873-11e7-808a-9801a7b20e21', 7, 1, ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
insert into orders (order_id, area_id, city_id, location)
values ('22822a54-0873-11e7-808a-9801a7b20e22', 7, 1, ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
insert into orders (order_id, area_id, city_id, location)
values ('22822a54-0873-11e7-808a-9801a7b20e23', 7, 1, ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
insert into orders (order_id, area_id, city_id, location)
values ('22822a54-0873-11e7-808a-9801a7b20e24', 7, 1, ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
insert into orders (order_id, area_id, city_id, location)
values ('22822a54-0873-11e7-808a-9801a7b20e25', 7, 1, ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
insert into orders (order_id, area_id, city_id, location)
values ('22822a54-0873-11e7-808a-9801a7b20e26', 7, 1, ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
insert into orders (order_id, area_id, city_id, location)
values ('22822a54-0873-11e7-808a-9801a7b20e27', 7, 1, ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
insert into orders (order_id, area_id, city_id, location)
values ('22822a54-0873-11e7-808a-9801a7b20e28', 7, 1, ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));


                                                                    ;
