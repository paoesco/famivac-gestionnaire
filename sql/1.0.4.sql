alter table ACCOMPAGNATEUR add column EMAIL varchar(255);

alter table VOYAGE drop column ACCOMPAGNATEUR_ID;

create table VOYAGE_ACCOMPAGNATEUR (VOYAGE_ID int8 not null, ACCOMPAGNATEUR_ID int8 not null)
alter table VOYAGE_ACCOMPAGNATEUR add constraint FK_d65wa5e58aucod8qaxcu1vqqj foreign key (ACCOMPAGNATEUR_ID) references ACCOMPAGNATEUR
alter table VOYAGE_ACCOMPAGNATEUR add constraint FK_1xbim917thrw81xt8a664xf1x foreign key (VOYAGE_ID) references Voyage
