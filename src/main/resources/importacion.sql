/*rol administrador*/4
INSERT INTO `role` (`ID`,`DESCRIPTION`,`NAME`) VALUES (1,'Usuario interno','INTERNO');

/*usuario administrador*/
INSERT INTO `user` (`ID`,`EMAIL`,`LANGUAGE`,`LOGIN`,`NAME`,`PASSWORD`,`ROLE`) VALUES (1,NULL,'S','admin','Eugenio','1c650ca8404206f61c02c35d0a1aa9a0',1);

/*projects*/
INSERT INTO `project` (`ID`,`DESCRIPTION`,`NAME`) VALUES (1,'Proyecto prueba1','Proyect prueba');
INSERT INTO `project` (`ID`,`DESCRIPTION`,`NAME`) VALUES (2,'Proyecto prueba 2','Proyecto pruebas 2');

/*releases*/
INSERT INTO `releases` (`ID`,`DESCRIPTION`,`NAME`,`ID_PROJECT`,`LIST_ORDER`) VALUES (5,'Release 1','Release 1',1,1);
INSERT INTO `releases` (`ID`,`DESCRIPTION`,`NAME`,`ID_PROJECT`,`LIST_ORDER`) VALUES (6,'Release 2','Release 2',1,2);
INSERT INTO `releases` (`ID`,`DESCRIPTION`,`NAME`,`ID_PROJECT`,`LIST_ORDER`) VALUES (7,'Release 3','Release 3',1,3);
INSERT INTO `releases` (`ID`,`DESCRIPTION`,`NAME`,`ID_PROJECT`,`LIST_ORDER`) VALUES (8,'Release 4 ','Release 4 ',1,4);
INSERT INTO `releases` (`ID`,`DESCRIPTION`,`NAME`,`ID_PROJECT`,`LIST_ORDER`) VALUES (9,'Release 5','Release 5',1,5);

/*sprints*/
INSERT INTO `sprint` (`ID`,`DESCRIPTION`,`NAME`,`LIST_ORDER`,`ID_RELEASE`,`START_DATE`,`FINISH_DATE`) VALUES (5,'Despliegue de la primera version con mantenimientos genericos y testeable','Srpint 1',1,5,'2012-09-10 00:00:00','2012-09-21 00:00:00');
INSERT INTO `sprint` (`ID`,`DESCRIPTION`,`NAME`,`LIST_ORDER`,`ID_RELEASE`,`START_DATE`,`FINISH_DATE`) VALUES (6,'Version con mantenimiento de usuarios y entornos','Sprint 2',2,5,'2012-09-24 00:00:00','2012-10-05 00:00:00');
INSERT INTO `sprint` (`ID`,`DESCRIPTION`,`NAME`,`LIST_ORDER`,`ID_RELEASE`,`START_DATE`,`FINISH_DATE`) VALUES (7,'Version con pantallas de consulta de una instalacion','Srpint 3',3,5,'2012-10-08 00:00:00','2012-10-19 00:00:00');
INSERT INTO `sprint` (`ID`,`DESCRIPTION`,`NAME`,`LIST_ORDER`,`ID_RELEASE`,`START_DATE`,`FINISH_DATE`) VALUES (8,'Version con mantenimientos de sistemas, procesos y proyectos','Sprint 4',4,5,'2012-10-22 00:00:00','2012-11-02 00:00:00');


/*tasks*/
INSERT INTO `task` (`ID`,`DESCRIPTION`,`NAME`,`LIST_ORDER`,`ID_SPRINT`,`ESTIMATED_HOURS`,`REAL_HOURS`) VALUES (1,'Configurar sistema de integracion continua','Configurar sistema de integracion continua',1,5,8,0);
INSERT INTO `task` (`ID`,`DESCRIPTION`,`NAME`,`LIST_ORDER`,`ID_SPRINT`,`ESTIMATED_HOURS`,`REAL_HOURS`) VALUES (2,'Crear una estructura de aplicacion compilable','Crear una estructura de aplicacion compilable',2,5,20,0);
INSERT INTO `task` (`ID`,`DESCRIPTION`,`NAME`,`LIST_ORDER`,`ID_SPRINT`,`ESTIMATED_HOURS`,`REAL_HOURS`) VALUES (3,'Realizar los mantenimientos genéricos y reutilizables','Realizar los mantenimientos genericos y reutilizables',3,5,15,0);
