use Unijobs;

insert into cursos (nome) values('Engenharia de Software'), ('Arquitetura'), ('Direito'), ('Contabilidade'), ('Administracao');
select * from cursos;

insert into universidades (nome, cnpj) values ('Uniamerica', '18715633000141'), ('UTFPR', '	75101873000190');
select * from universidades;

insert into cursos_universidade(id_universidade, id_curso) values (1, 1), (1, 2), (1, 3), (2, 3), (2, 4), (2, 5);
select * from cursos_universidade;

select * from universidades 
join cursos_universidade using (id_universidade) 
join cursos using (id_curso) 
order By universidades.nome;

insert into tipos_usuarios(nome) values ('Administrador Sistema'), ('Administrador Universidade'), ('Vendedor');
select * from tipos_usuarios; 

insert into usuarios(email, senha, nome, celular, ra, id_universidade, id_cursos, id_tipousuario) 
VALUES 
('nelson@gmail.com.br', 'tomaladaca', 'Nelson Baez', '988322026', 666666, 1, 1, 1),
('milene@gmail.com.br', 'testeteste', 'Milene Danelli', '564654561', 541364, 2, 1, 1),
('lio@gmail.com.br', 'jotajota', 'Lio Croons', '564877123', 123457, 1, 2, 2),
('jorgin@gmail.com.br', 'jorginhoemprestaa12', 'Jorge', '123123123', 444466, 2, 2, 2),
('seuze@gmail.com.br', 'nomedafilha123', 'Seu Zé', '77777777', 777777, 1, 1, 3);

select usuarios.*, universidades.nome as universidade, cursos.nome as curso, tipos_usuarios.nome as tipo_usuario 
from usuarios 
join universidades using (id_universidade) 
join cursos on id_curso = id_cursos 
join tipos_usuarios using(id_tipousuario);

select * from tipos_produto;
insert into tipos_produto (nome, descricao)
 values 
 ('Eletrônicos', 'celulares, computadores, câmeras, caixas de som'),
 ('Roupas', 'Camiasas, sapatos, calças');
 
 select * from tipos_servico;
insert into tipos_servico (nome, descricao)
 values 
 ('Manutenção em Computadores', 'celulares, computadores, câmeras, caixas de som');

select * from anuncios;
INSERT INTO anuncios (titulo, descricao, preco, miniatura, ativo, prazo, id_usuario, id_tipoProduto, id_tipoServico)
VALUES 
('Camisa', 'Camisas com imagens aleatórias a seu pedido', 20.00, 'https://www.google.com/url?sa=i&url=https%3', 1, 15, 2, 2, null),
('Bermuda', 'Bermudas jeans, moletom.', 60.00, 'https://www.google.com/ur', 1, 15, 3, 2, null),
('Jogos', 'de ps4, xbox usados', 120.00, 'https://www.google.com/imgres?imgurl=https%3A%2F%2Fstatic.netshoes.com.br%2Fprodutos%2', 1, 5, 1, 1, null),
('Formatação de PC', 'Formato computadores, notebooks etc...', 50.00, 'umlinkqualquer', 1, 7, 1, null, 1);


