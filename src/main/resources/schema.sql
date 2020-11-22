CREATE TABLE profile (
    profile_id serial PRIMARY KEY,
    fullname VARCHAR (50) NOT NULL,
    created_on TIMESTAMP NOT NULL
);

CREATE TABLE profile_view (
    profile_view_id serial,
    actor_id int not null,
    object_id int not null,
    created_on TIMESTAMP not null,
    PRIMARY KEY (profile_view_id, created_on)
) PARTITION BY RANGE (created_on);

CREATE TABLE profile_view_2020_05 PARTITION OF profile_view
FOR VALUES FROM ('2020-05-01') TO ('2020-06-01');

CREATE TABLE profile_view_2020_06 PARTITION OF profile_view
FOR VALUES FROM ('2020-06-01') TO ('2020-07-01');

CREATE TABLE profile_view_2020_07 PARTITION OF profile_view
FOR VALUES FROM ('2020-07-01') TO ('2020-08-01');

CREATE TABLE profile_view_2020_08 PARTITION OF profile_view
FOR VALUES FROM ('2020-08-01') TO ('2020-09-01');

CREATE TABLE profile_view_2020_09 PARTITION OF profile_view
FOR VALUES FROM ('2020-09-01') TO ('2020-10-01');

CREATE TABLE profile_view_2020_10 PARTITION OF profile_view
FOR VALUES FROM ('2020-10-01') TO ('2020-11-01');

CREATE TABLE profile_view_2020_11 PARTITION OF profile_view
FOR VALUES FROM ('2020-11-01') TO ('2020-12-01');

