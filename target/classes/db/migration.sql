CREATE EXTENSION Postgis;

CREATE TABLE orders (
            order_id VARCHAR(100) PRIMARY KEY,
            area_id BIGINT NOT NULL,
            city_id BIGINT NOT NULL,
            assign_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
            received_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
            created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
            location GEOGRAPHY(POINT,4326)
);

CREATE TABLE delivery_boys (
            id VARCHAR(100) PRIMARY KEY,
            location GEOGRAPHY(POINT,4326),
            created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX delivery_boys_gix ON delivery_boys USING GIST (Geography(location));

INSERT INTO delivery_boys (id, location) VALUES ('ec7c7ff8-9e64-44ac-9a88-9865f22a3c24',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('ec7c7ff8-9e64-44ac-9a88-9865f22a3c24',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('sdadsdas-asda-wwsd',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('sdadsdas-asda-wwsd',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('sdadsdas-asda-wwsd',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('sdadsdas-asda-wwsd',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('sdadsdas-asda-wwsd',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('sdadsdas-asda-wwsd',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('sdadsdas-asda-wwsd',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
INSERT INTO delivery_boys (id, location) VALUES ('sdadsdas-asda-wwsd',
ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));

select * from delivery_boys where ST_DWithin(Geography(location), Geography(ST_MakePoint(12.0, 12.0)), 4000);
insert into orders (order_id, area_id, city_id, location) values ('adasd', 7, 1, 500, ST_SetSRID(ST_MakePoint('12.2312', '19.209'), 4326));
select id, ST_X(location::geometry), ST_Y(location::geometry) from delivery_boys                                                                           ;
