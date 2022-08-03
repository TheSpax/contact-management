CREATE TABLE IF NOT EXISTS "users" (
    "id" BIGSERIAL PRIMARY KEY,
    "first_name" varchar not null,
    "last_name" varchar not null,
    "username" varchar UNIQUE not null,
    "email" varchar UNIQUE not null,
    "password" varchar not null,
    "role_id" bigint not null,
    "time_created" TIMESTAMP not null,
    "time_updated" TIMESTAMP not null
);

CREATE TABLE IF NOT EXISTS "role" (
    "id" BIGSERIAL PRIMARY KEY,
    "type" varchar not null
);

CREATE TABLE "contact_type" (
    "id" BIGSERIAL PRIMARY KEY,
    "type_name" varchar not null,
    "time_created" TIMESTAMP not null,
    "time_updated" TIMESTAMP not null
);

CREATE TABLE "contacts" (
    "id" BIGSERIAL PRIMARY KEY,
    "first_name" varchar not null,
    "last_name" varchar not null,
    "email" varchar not null,
    "phone_number" varchar not null,
    "type_id" bigint not null,
    "user_id" bigint not null,
    "time_created" TIMESTAMP not null,
    "time_updated" TIMESTAMP not null
);

ALTER TABLE "users" ADD FOREIGN KEY ("role_id") REFERENCES "role" ("id");
ALTER TABLE "contacts" ADD FOREIGN KEY ("type_id") REFERENCES "contact_type" ("id");
ALTER TABLE "contacts" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");