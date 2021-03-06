package com.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import com.cursomc.domain.*;
import com.cursomc.domain.enums.EstadoPagamento;
import com.cursomc.dtos.ClienteResponseDTO;
import com.cursomc.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cursomc.domain.enums.TipoCliente;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	private final CategoriaRepository categoriaRepository;
	
	private final ProdutoRepository produtoRepository;
	
	private final CidadeRepository cidadeRepository;
	
	private final EstadoRepository estadoRepository;
	
	private final ClienteRepository clienteRepository;
	
	private final EnderecoRepository enderecoRepository;

	private final PedidoRepository pedidoRepository;

	private final PagamentoRepository pagamentoRepository;

	private final ItemPedidoRepository itemPedidoRepository;

	public CursomcApplication(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
							  CidadeRepository cidadeRepository, EstadoRepository estadoRepository,
							  ClienteRepository clienteRepository, EnderecoRepository enderecoRepository,
							  PedidoRepository pedidoRepository,PagamentoRepository pagamentoRepository,
							  ItemPedidoRepository itemPedidoRepository) {
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
		this.cidadeRepository = cidadeRepository;
		this.estadoRepository = estadoRepository;
		this.clienteRepository = clienteRepository;
		this.enderecoRepository = enderecoRepository;
		this.pedidoRepository = pedidoRepository;
		this.pagamentoRepository = pagamentoRepository;
		this.itemPedidoRepository = itemPedidoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null, "Inform??tica");
		Categoria cat2 = new Categoria(null, "Escrit??rio");
		Categoria cat3 = new Categoria(null, "Cama Mesa e Banho");
		Categoria cat4 = new Categoria(null, "Higiene do Lar");
		Categoria cat5 = new Categoria(null, "Constru????o Civil");
		Categoria cat6 = new Categoria(null, "Alimentos");
		Categoria cat7 = new Categoria(null, "Brinquedos");
		Categoria cat8 = new Categoria(null, "Eletrodom??sticos");
		Categoria cat9 = new Categoria(null, "Decora????o");
		Categoria cat10 = new Categoria(null, "Roupas");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritorio", 200.00);
		Produto p5 = new Produto(null, "Tv true Color", 400.00);
		Produto p6 = new Produto(null, "Tv smart", 500.00);
		Produto p7 = new Produto(null, "Monitor de press??o", 5012.00);
		Produto p8 = new Produto(null, "Cadeira do Presidente", 200.00);
		Produto p9 = new Produto(null, "Note book DellSpin", 5326.00);
		Produto p10 = new Produto(null, "Cama Box", 5000.00);
		Produto p11 = new Produto(null, "Chuveiro eletrico", 456.00);
		Produto p12 = new Produto(null, "50 kg de cimento", 50.00);


		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3, p5, p6, p8));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		cat5.getProdutos().add(p12);
		cat8.getProdutos().addAll(Arrays.asList(p4, p7, p9, p10, p11, p12));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p5.getCategorias().addAll(Arrays.asList(cat1));
		p6.getCategorias().addAll(Arrays.asList(cat1));
		p8.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat8));
		p7.getCategorias().addAll(Arrays.asList(cat8));
		p9.getCategorias().addAll(Arrays.asList(cat8));
		p10.getCategorias().addAll(Arrays.asList(cat8));
		p11.getCategorias().addAll(Arrays.asList(cat8));
		p12.getCategorias().addAll(Arrays.asList(cat8));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5,
				cat6, cat7, cat8, cat9, cat10));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "S??o Paulo");

		Cidade c1 = new Cidade(null, "Uberl??ndia", est1);
		Cidade c2 = new Cidade(null, "S??o Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA, "senha");

		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

		System.out.println(new ClienteResponseDTO(cli1));
		
	}
	
	

}
