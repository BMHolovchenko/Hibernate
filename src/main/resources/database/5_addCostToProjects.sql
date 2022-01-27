alter table projects add column cost integer default 0;
update projects set cost = '15000' where (id = '1');
update projects set cost = '20000' where (id = '2');
update projects set cost = '17000' where (id = '3');
update projects set cost = '12000' where (id = '4');
update projects set cost = '17000' where (id = '5');
update projects set cost = '11000' where (id = '6');