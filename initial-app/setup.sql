CREATE DATABLE itsa; 

CREATE TABLE "public"."user" ("id" serial,"name" text,"username" text,"password" text, PRIMARY KEY ("id"));
CREATE TABLE "public"."mask" ("id" serial,"name" text,"description" text,"quantity" text,"price" text,"image" text, PRIMARY KEY ("id"));
CREATE TABLE "public"."cart" ("id" serial,"userId" text,"maskId" text,"quantity" text, PRIMARY KEY ("id"));

INSERT INTO public.mask (id, name, description, quantity, price, image) VALUES (1, 'Basic Mask', 'Basic mask for for when you are feeling unwell', null, '0.40', '/images/mask01.png');
INSERT INTO public.mask (id, name, description, quantity, price, image) VALUES (2, 'Anti Dusk Facial Mask', 'Keeps dust away from your face', null, '0.20', '/images/mask02.png');
INSERT INTO public.mask (id, name, description, quantity, price, image) VALUES (3, 'Surgical Mask', 'For the professionals', null, '1.20', '/images/mask03.png');
INSERT INTO public.mask (id, name, description, quantity, price, image) VALUES (4, 'Surgical Mask (Green)', 'For the professionals', null, '1.20', '/images/mask04.png');
INSERT INTO public."user" (id, name, username, password) VALUES (1, 'kz', 'likz', 'password');