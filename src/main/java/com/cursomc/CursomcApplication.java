package com.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cursomc.domain.Categoria;
import com.cursomc.domain.Cidade;
import com.cursomc.domain.Cliente;
import com.cursomc.domain.Endereco;
import com.cursomc.domain.Estado;
import com.cursomc.domain.Produto;
import com.cursomc.domain.enums.TipoCliente;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.repositories.CidadeRepository;
import com.cursomc.repositories.ClienteRepository;
import com.cursomc.repositories.EnderecoRepository;
import com.cursomc.repositories.EstadoRepository;
import com.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 30.00);
		
		Estado minas = new Estado(null, "Minas Gerais");
		Estado saoPaulo = new Estado(null, "São Paulo");
		
		Cidade capitalSp  = new Cidade(null, "São Paulo Capital");
		Cidade campinas = new Cidade(null, "Campinas");
		
		Cidade uberlandia = new Cidade(null, "Uberlândia");
		Cidade beloHorizonte = new Cidade(null, "Belo Horizonte");
		
		uberlandia.setEstado(minas);
		beloHorizonte.setEstado(minas);
		
		campinas.setEstado(saoPaulo);
		capitalSp.setEstado(saoPaulo);
		
		minas.getCidades().addAll(Arrays.asList(uberlandia, beloHorizonte));
		saoPaulo.getCidades().addAll(Arrays.asList(capitalSp, campinas));
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		repository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(saoPaulo, minas));
		cidadeRepository.saveAll(Arrays.asList(capitalSp, beloHorizonte, uberlandia, campinas));

		Cliente maria = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		maria.getTelefones().addAll(Arrays.asList("9898989", "5465465"));
		Endereco endereco1 = new Endereco(null, "Rua das Flores", "300", "s/n", "jardim", "38220834", maria, uberlandia);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Centro", "38777012", maria, capitalSp);
		maria.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		
		clienteRepository.saveAll(Arrays.asList(maria));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
		
	}
	
	

}
