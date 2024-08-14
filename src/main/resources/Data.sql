
insert into CARD_TYPES (description) values('Suerte'),('Destino');


insert into CARD_CLASSIFICATIONS(classification) values('Marche preso'),('Sal de la Carcel'),( 'Cobrar o pagar'), ('Cobre de todos'), ('Desplazarse a Casilla'), ('Avanzar retroceder'),( 'Pago por chacra'),('Pague o levante');


insert into CARDS (cardType_id, cardCalssification_id, description,displacement, moveToSquare, amount) values(1,5,'Haga un paseo hasta la Bodega, si pasa por salida cobre 5000.', null, 16, null), ( 1, 2, 'Habeas Corpus concedido, con esta tarjeta sale usted gratuitamente de la Comisaría o véndela.', null, null, null),( 1, 3, 'Gano en las carreras cobre 3000.', null, null,3000),             (1 ,6 , 'Vuelva 3 pasos atrás.', -3, null ,null), (1 ,7 ,'Por compra de semilla pague al Banco 800 por cada chacra. 4000 por cada estancia.', null, null, -800), (1 ,3 , 'Multa por exceso de velocidad. Pague 300.', null, null, -300), (1 ,3, 'Cobre 1000 por intereses bancarios', null, null, 1000), (1, 5,'Siga hasta Salta, Zona Norte. Si pasa por la salida cobre 5000', null, 13, null), ( 1 , 3	, 'Multa caminera. Pague 400', null, null, -400), ( 1, 3, 'Pague 3000 por gastos colegiales' ,null, null, -3000),( 1, 5, 'Siga hasta Santa Fe Zona Norte. Si pasa por la salida cobre 5000',  null, 26 , null), ( 1 , 5 , 'Siga hasta Buenos Aires Zona Norte', null, 40, null), ( 1, 1,  'Marche preso directamente' ,null, 14, null), ( 1, 3, 'Ha ganado la grande. Cobre 10000',  null, null, 10000), ( 1, 7, 'Sus propiedades tienen que ser reparadas. Pague al Banco 500 por cada chacra y 2500 por cada estancia', null, null,-500), ( 1,5,  'Siga hasta la salida', null, 0, null), ( 2,4, 'Es su cumpleaños. Cobre 200 de cada jugador', null, null, 200), (2, 3, 'Ha ganado un concurso agrícola. Cobre 2000', null, null, 2000), ( 2, 5, 'Siga hasta la salida', null, 0, null), ( 2, 5, 'Vuelve atrás hasta Formosa Zona sur', null, 1, null), ( 2, 3, 'Hereda 2000', null, null, 2000), (2, 8, 'Pague 200 o levante una tarjeta de SUERTE', null, null, 200), ( 2 , 3, 'Devolución de impuesto cobre 400', null, null, 400), (2, 3, 'Pague su póliza de seguro con 1000', null, null, -1000), (2,3, 'Error en los cálculos del Banco. Cobre 4000', null, null, 4000), ( 2, 3, 'Ha obtenido un segundo premio de belleza. Cobre 200', null, null, 200), (2, 3, 'Ha ganado un concurso agrícola. Cobre 2000', null, null, 2000), (2, 1, 'Marche preso directamente', null, 14, null), (2, 2, 'Con esta tarjeta sale usted de la Comisaría. Consérvala hasta utilizarla o venderla',null, null, null), (2, 3, '5% de interés sobre cédulas hipotecarias. Cobre 500', null, null, 500), (2,  3,'Gastos de Farmacia. Pague 1000', null, null, -1000);


insert into ZONES (zone ) values('Norte'), ('Centro'), ('Sur');

insert into PROVINCES(province) values('Formosa'),('Rio Negro'), ('Salta'), ('Mendoza'),('Santa Fe'), ('Tucumán'), ('Córdoba'), ('Buenos Aires');

insert into PROPERTY_TYPES(type) values('Campos'), ('Empresas'), ('Ferrocarril');
insert into PROPERTIES(description,property_Type_id, province_id,zone_id, rentValue, buyValue, farmBuyValue, farmRentValue,numberOfZonesInProvince ) values (null,			1,				1,		3,				40,					1000,				1000,		200,3), (null,			1,				1,       2,				40,					1000,				1000,		200,3), (null,			1,				1,      1,				80,					1200,				1000,		400,3), (null,			1,				2,		3,				110,				2000,				1000,		570,2	), (null,			1,				2,		1,				150,				2200,				1000,		750,2), ('Compañía petrolera',	2,				null,	null,			100,				3800,				null,		null,null), (null,			1,				3,		3,				200,				2600,				1500,		1000,3), (null,			1,				3,		2,				200,				2600,				1500,		1000,3), ('Ferrocarril General Belgrano',		3,				null,   null,			500,				3600,				null,		null,null), (null,			1,				3,		1,				230,				3000,				1500,		1150,3), ('Bodega',			2,				null,	null,			100,				3800,				null,		null,null), (null,			1,				4,		3,				250,				3400,				2000,		1350,3), ('Ferrocarril San Martín',		3,				null, null,				500,				3600,				null,		null,null), (null,				1,				4,		2,				250,				3400,				2000,		1350,3), (null,				1,				4,		1,				300,				3800,				2000,		1500,3), ('Mitre',		3,				null,	null,			500,				3600,				null,		null,null), (null,				1,				5,		3,				350,				4200,				2500,		1700,3);
insert into PROPERTIES(description,property_Type_id, province_id,zone_id, rentValue, buyValue, farmBuyValue, farmRentValue,numberOfZonesInProvince ) values (null,				1,				5,		2,				350,				4200,				2500,		1700,3);
insert into PROPERTIES(description,property_Type_id, province_id,zone_id, rentValue, buyValue, farmBuyValue, farmRentValue,numberOfZonesInProvince ) values (null,				1,				5,		1,				400,				4600,				2500,		2000,3);
insert into PROPERTIES(description,property_Type_id, province_id,zone_id, rentValue, buyValue, farmBuyValue, farmRentValue,numberOfZonesInProvince ) values ('Ferrocarril Urquiza',			3,				null, null,				500,				3600,				null,		null,null);
insert into PROPERTIES(description,property_Type_id, province_id,zone_id, rentValue, buyValue, farmBuyValue, farmRentValue,numberOfZonesInProvince ) values (null,				1,				6,		3,				400,				5000,				3000,		2200,2);
insert into PROPERTIES(description,property_Type_id, province_id,zone_id, rentValue, buyValue, farmBuyValue, farmRentValue,numberOfZonesInProvince ) values (null,				1,				6,		1,				450,				5400,				3000,		2400,2);
insert into PROPERTIES(description,property_Type_id, province_id,zone_id, rentValue, buyValue, farmBuyValue, farmRentValue,numberOfZonesInProvince ) values ('Ingenio',			2,				null,	null,			100,				3800,				null,		null,null);
insert into PROPERTIES(description,property_Type_id, province_id,zone_id, rentValue, buyValue, farmBuyValue, farmRentValue,numberOfZonesInProvince ) values (null,				1,				7,		3,				500,				6000,				3000,		2500,3);
insert into PROPERTIES(description,property_Type_id, province_id,zone_id, rentValue, buyValue, farmBuyValue, farmRentValue,numberOfZonesInProvince ) values (null,				1,				7,		2,				450,				6000,				3000,		2400,3);
insert into PROPERTIES(description,property_Type_id, province_id,zone_id, rentValue, buyValue, farmBuyValue, farmRentValue,numberOfZonesInProvince ) values (null,				1,				7,		1,				550,				6400,				3000,		2850,3);
insert into PROPERTIES(description,property_Type_id, province_id,zone_id, rentValue, buyValue, farmBuyValue, farmRentValue,numberOfZonesInProvince ) values (null,				1,				8,		3,				650,				7000,				4000,		3300,3);
insert into PROPERTIES(description,property_Type_id, province_id,zone_id, rentValue, buyValue, farmBuyValue, farmRentValue,numberOfZonesInProvince ) values (null,				1,				8,		2,				650,				7000,				4000,		3300,3);
insert into PROPERTIES(description,property_Type_id, province_id,zone_id, rentValue, buyValue, farmBuyValue, farmRentValue,numberOfZonesInProvince ) values (null,				1,				8,		1,				1000,				7600,				4000,		4000,3);

insert into SQUARE_TYPES (description) values('Propiedades'), ('Suerte'), ('Destino'), ('Carcel'), ('Impuestos'), ('Salida'),('Descanso'),('Marche Preso'),('Libre Estacionamiento'), ('Premio');

insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	1,			1,			1,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	2,			1,			2,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	3,			1,			3,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	4,			5,			null,		-5000	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	5,			1,			4,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	6,			1,			5,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	7,			10,			null,		2500	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	8,			1,			6,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	9,			1,			7,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	10,			3,			null,		null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	11,			1,			8,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	12,			1,			9,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	13,			1,			10,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	14,			4,			null,		null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	15,			2,			null,		null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	16,			1,			11,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	17,			1,			12,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	18,			1,			13,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	19,			1,			14,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	20,			1,			15,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	21,			7,			null,		null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	22,			1,			16,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	23,			1,			17,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	24,			1,			18,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	25,			3,			null,		null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	26,			1,			19,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	27,			1,			20,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	28,			9,			null,		null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	29,			1,			21,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	30,			1,			22,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	31,			1,			23,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	32,			1,			24,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	33,			1,			25,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	34,			1,			26,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	35,			8,			null,		null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	36,			2,			null,		null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	37,			1,			27,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values ( 38,			3,			null,		null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values ( 39,			1,			28,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	40,			1,			29,			null	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values (	41,			5,			null,		-2000	);
insert into SQUARES(number,squareType_id, property_id, prizeAmount ) values ( 0,			6,			null,		null	);

insert into USERS(name, password) values ('pepe','1234');

insert into PLAYERS_TYPES(type)values ('human'),('conservative'),('moderate'),('agressive');
insert into DIFFICULTIES(description) values('EASY'),('MEDIUM'),('HARD');
insert into GAMES(initial_balance,victory_Amount,difficulty_id) values(1000,500000,1);
insert into PLAYERS(name,balance,numberDoubleDiceThrown,playerType_id,user_id,game_id,inJail,restCount,position_id) values ('pepe',1000,0,1,1,1,false,0,1);
