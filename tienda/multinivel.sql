
create table estadospedidos (
id serial primary key,
descripcion varchar(10)
);

create table tiposdocumento (
id serial primary key,
descripcion varchar(10)
);

create table categorias (
id serial primary key,
nombre varchar(10),
descripcion varchar(255)
);


create table productos (
id serial primary key,
cate_id integer,
referencia varchar(10),
nombre varchar(50),
descripcion varchar(255),
precio_unitario numeric(19,2),
unidades_disponibles numeric(19,2),
constraint productos_categorias foreign key (cate_id) references categorias(id)  
);

create table clientes (
id serial primary key,
tido_id integer,
nombre varchar(50),
apellidos varchar(50),
documentos varchar(50),
estado varchar(1),
constraint clientes_tiposdoc foreign key (tido_id) references tiposdocumento(id)  
);


create table pedidos (
id serial primary key,
clie_id integer,
espe_id integer,
fecha timestamp,
total numeric(19,2),
constraint pedidos_clientes foreign key (clie_id) references clientes(id),
constraint pedidos_estadospedidos foreign key (espe_id) references estadospedidos(id)
);


create table detallepedido (
id serial primary key,
pedi_id integer,
prod_id integer,
cantidad numeric(19,2),
valor numeric(19,2),
constraint detallepedido_pedidos foreign key (pedi_id) references pedidos(id),
constraint detallepedido_productos foreign key (prod_id) references productos(id)
);



