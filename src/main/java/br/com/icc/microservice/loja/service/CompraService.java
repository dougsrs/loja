package br.com.icc.microservice.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.icc.microservice.loja.client.FornecedorClient;
import br.com.icc.microservice.loja.controller.dto.CompraDTO;
import br.com.icc.microservice.loja.controller.dto.InfoFornecedorDTO;

@Service
public class CompraService {

//	@Autowired 
//	private RestTemplate client;
	
	@Autowired
	private FornecedorClient fornecedorClient;
	
	@Autowired
	private org.springframework.cloud.client.discovery.DiscoveryClient eurekaClient;

	public void realizaCompra(CompraDTO compra) {

//		ResponseEntity<InfoFornecedorDTO> exchange = 
//				client.exchange("http://fornecedor/info/" + compra.getEndereco().getEstado(), 
//				HttpMethod.GET, null, InfoFornecedorDTO.class);
//		
//		System.out.println(exchange.getBody().getEndereco());

		eurekaClient.getInstances("fornecedor")
			.stream()
			.forEach(fornecedor -> {
				System.out.println("localhost:"+fornecedor.getPort());
			});


		InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		
		System.out.println(info.getEndereco());
		
	}

}
