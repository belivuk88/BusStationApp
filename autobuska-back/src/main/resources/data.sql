INSERT INTO prevoznik (id, naziv, adresa, pib) VALUES (1, '7 jul', 'Hajduk Veljkova bb. Sabac', '25469872');
INSERT INTO prevoznik (id, naziv, adresa, pib) VALUES (2, 'Lasta' , 'Nemanjina 52, Beograd', '45789652');
INSERT INTO prevoznik (id, naziv, adresa, pib) VALUES (3, 'Vojvodina', 'Futoska 105, Novi Sad','35647896');
INSERT INTO prevoznik (id, naziv, adresa, pib) VALUES (4, 'Raketa', 'Kralja Aleksandra 78, Uzice','56478902');

INSERT INTO linija (id, broj_mesta, cena_karte, destinacija, vreme_polaska, prevoznik_id)  VALUES (1, 15, 250.0, 'Beograd', '2021-01-21 20:00', 2);
INSERT INTO linija (id, broj_mesta, cena_karte, destinacija, vreme_polaska, prevoznik_id)  VALUES (2, 20, 300.0, 'Novi Sad', '2021-01-18 18:00', 3);
INSERT INTO linija (id, broj_mesta, cena_karte, destinacija, vreme_polaska, prevoznik_id)  VALUES (3, 24, 200.0, 'Valjevo', '2021-01-15 17:30', 4);
INSERT INTO linija (id, broj_mesta, cena_karte, destinacija, vreme_polaska, prevoznik_id)  VALUES (4, 18, 450.0, 'Šabac', '2021-01-07 19:00', 1);
INSERT INTO linija (id, broj_mesta, cena_karte, destinacija, vreme_polaska, prevoznik_id)  VALUES (5, 30, 500.0, 'Loznica', '2021-01-21 21:00', 2);

INSERT INTO rezervacija (id, broj_putnika, linija_id) VALUES (1, 3, 1);
INSERT INTO rezervacija (id, broj_putnika, linija_id) VALUES (2, 39, 1);

INSERT INTO `user` (id, username, password, role)
              VALUES (1,'miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','ADMIN');
INSERT INTO `user` (id, username, password, role)
              VALUES (2,'tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','KORISNIK');
INSERT INTO `user` (id, username, password, role)
              VALUES (3,'petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','KORISNIK');