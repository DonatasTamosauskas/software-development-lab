CREATE TABLE "Premises" (
	"id" serial NOT NULL,
	"city" varchar(255) NOT NULL,
	"street" varchar(255) NOT NULL,
	"house_number" varchar(255) NOT NULL,
	"apartament" varchar(255) NOT NULL,
	"owner" integer NOT NULL,
	"type" varchar(255) NOT NULL,
	CONSTRAINT "Premises_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Party" (
	"id" serial NOT NULL,
	"name" varchar(255) NOT NULL,
	"government_id" varchar(255) NOT NULL,
	"birth_date" DATE NOT NULL,
	"private" BOOLEAN NOT NULL,
	CONSTRAINT "Party_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "LeaseAgreement" (
	"appartment_id" integer NOT NULL,
	"party_id" integer NOT NULL,
	"start_date" DATE NOT NULL,
	"end_date" DATE,
	"primary_leaser" BOOLEAN NOT NULL,
	CONSTRAINT "LeaseAgreement_pk" PRIMARY KEY ("appartment_id","party_id","start_date")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "Premises" ADD CONSTRAINT "Premises_fk0" FOREIGN KEY ("owner") REFERENCES "Party"("id");


ALTER TABLE "LeaseAgreement" ADD CONSTRAINT "LeaseAgreement_fk0" FOREIGN KEY ("appartment_id") REFERENCES "Premises"("id");
ALTER TABLE "LeaseAgreement" ADD CONSTRAINT "LeaseAgreement_fk1" FOREIGN KEY ("party_id") REFERENCES "Party"("id");

