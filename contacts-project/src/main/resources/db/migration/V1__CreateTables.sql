CREATE TABLE IF NOT EXISTS "users" (
    "id" SERIAL PRIMARY KEY,
    "first_name" varchar not null,
    "last_name" varchar not null,
    "username" varchar UNIQUE not null,
    "email" varchar UNIQUE not null,
    "password" varchar not null,
    "role_id" bigint not null,
    "time_created" TIMESTAMP,
    "time_updated" TIMESTAMP
);

CREATE TABLE IF NOT EXISTS "role" (
    "id" SERIAL PRIMARY KEY,
    "type" varchar not null
);

CREATE TABLE "contact_type" (
    "id" SERIAL PRIMARY KEY,
    "type_name" varchar,
    "time_created" TIMESTAMP,
    "time_updated" TIMESTAMP
);

CREATE TABLE "contacts" (
    "id" SERIAL PRIMARY KEY,
    "first_name" varchar not null,
    "last_name" varchar not null,
    "email" varchar not null,
    "phone_number" varchar not null,
    "type_id" bigint not null,
    "user_id" bigint not null,
    "time_created" TIMESTAMP,
    "time_updated" TIMESTAMP
);

ALTER TABLE "users" ADD FOREIGN KEY ("role_id") REFERENCES "role" ("id");
ALTER TABLE "contacts" ADD FOREIGN KEY ("type_id") REFERENCES "contact_type" ("id");
ALTER TABLE "contacts" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");