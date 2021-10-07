create table "Genres"
(
"id" serial primary key,
"name" text default '' not null
);

create table "Countries"
(
"id" serial primary key,
"name" text default '' not null
);

create table "Languages"
(
"id" serial primary key,
"name" text default '' not null
);

create table "Creators"
(
"id" serial primary key,
"country_id" integer references "Countries"("id"),
"name" text default '' not null
);

create table "MusicalCompositions"
(
"id" serial primary key,
"language_id" integer references "Languages"("id"),
"composer_id" integer references "Creators"("id"),
"singer_id" integer references "Creators"("id"),
"name" text default '' not null,
"year" date not null
);

create table "Cenre_MusicalCompositions"
(
"id" serial primary key,
"genre_id" integer references "Genres"("id"),
"musicalCompositions_id" integer references "MusicalCompositions"("id")
);