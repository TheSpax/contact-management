ALTER TABLE users ADD COLUMN uid uuid NOT NULL UNIQUE DEFAULT (gen_random_uuid());
ALTER TABLE role ADD COLUMN uid uuid NOT NULL UNIQUE DEFAULT (gen_random_uuid());
ALTER TABLE contact_type ADD COLUMN uid uuid NOT NULL UNIQUE DEFAULT (gen_random_uuid());
ALTER TABLE contacts ADD COLUMN uid uuid NOT NULL UNIQUE DEFAULT (gen_random_uuid());